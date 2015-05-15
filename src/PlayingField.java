import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;

/**
 * The main playing field for the game.
 *
 * Created by oskarek on 2015-05-07.
 */
public class PlayingField extends BasicGameState {
    private int contWidth, contHeight;
    private CollideChecker collideChecker;
    private ArrayList<PlayingFieldItem> items;
    private Board board;
    private Ball ball;
    private ArrayList<Brick> bricks;
    private Iterator<Brick> it;
    private Timer timer;

    @Override
    public int getID() {
        return 1;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        // dimensions of the container
        contHeight = container.getHeight();
        contWidth = container.getWidth();

        collideChecker = new CollideChecker(container);
        items = new ArrayList<>();
        board = new Board(70,contHeight-30,80,15);
        float ballRadius = 10;
        float ballXPos = board.getX()+board.getLength()/2;
        float ballYPos = board.getY()-ballRadius;
        ball = new Ball(ballXPos,ballYPos,ballRadius);
        bricks = new ArrayList<>();
        items.add(ball);
        items.add(board);
        initBricks();
        items.addAll(bricks);
        Points.getInstance().addPoints(500);

        timer = new Timer();
        TimerTask timeTask = new TimerTask(){

            @Override
            public void run() {
                Points.getInstance().decrementPoints();
            }
        };
        timer.scheduleAtFixedRate(timeTask, 1000, 500);

    }

    public void initBricks() throws SlickException {
        MapReader mapReader = new MapReader();
        ArrayList<ArrayList<Integer>> mapOneInfo = mapReader.readMap(1);
        ArrayList<Integer> brickXPositions = mapOneInfo.get(0);
        ArrayList<Integer> brickYPositions = mapOneInfo.get(1);
        ArrayList<Integer> brickLevels = mapOneInfo.get(2);

        for(int i=0;i<brickXPositions.size();i++){
            int x = brickXPositions.get(i); int y = brickYPositions.get(i); int life = brickLevels.get(i);
            bricks.add(new Brick(x,y,life));
        }
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        for (PlayingFieldItem item : items) {
            item.draw(g);
        }
        g.drawString("Points : " + Points.getInstance().getPoints(),container.getWidth()-120,0);
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        // update the ball position
        updateBallPos(delta);

        //update all the bricks
        updateBricks();

        Input input = container.getInput();
        // move the board to the left if the left key is pressed
        if (input.isKeyDown(Input.KEY_LEFT)) {
            updateBoardPos(Direction.LEFT, delta);
        }
        // move the board to the right if the right key is pressed
        if (input.isKeyDown(Input.KEY_RIGHT)) {
            updateBoardPos(Direction.RIGHT, delta);
        }
        // go to the main menu if the escape key is pressed.
        if (input.isKeyPressed(Input.KEY_ESCAPE)) {
            game.enterState(0, new FadeOutTransition(), new FadeInTransition());
        }
        //check if the game has been beaten
        if(bricks.size() <= 0){
            timer.cancel();
            game.enterState(4,new FadeOutTransition(), new FadeInTransition());
        }
    }

    private void updateBallPos(int delta) throws SlickException {
        float dist = ball.getSpeed()*0.05f*delta;
        ball.move(dist);

        // check if the ball has collided with any object.
        collideChecker.checkBallCollisions(ball,board, bricks);
    }

    public void updateBricks(){
        it = bricks.iterator();
        while(it.hasNext()){
            if(it.next().getLives() <= 0) {
                items.removeAll(bricks);
                it.remove();
                items.addAll(bricks);
            }
        }
    }

    /**
     * Update the position of the board.
     * @param d The direction in which the board should move (left or right).
     * @param delta The delta value from the update method.
     */
    private void updateBoardPos(Direction d, int delta) {
        float currentxPos = board.getX();
        float boardLength = board.getLength();
        int speed = board.getSpeed();
        if(d == Direction.LEFT){
            if(currentxPos > 0) {
                float xPos = currentxPos - speed*0.05f*delta;
                board.setX(xPos);
            }
        }
        if(d == Direction.RIGHT){
            if(currentxPos+boardLength < contWidth) {
                float xPos;
                if(currentxPos+5 >= contWidth){
                    xPos = contWidth-boardLength;
                } else {
                    xPos = currentxPos + speed*0.05f*delta;

                }
                board.setX(xPos);
            }
        }
    }
}