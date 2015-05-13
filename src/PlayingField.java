import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * The main playing field for the game.
 *
 * Created by oskarek on 2015-05-07.
 */
public class PlayingField extends BasicGameState {
    private int contWidth, contHeight;
    private CollideChecker collideChecker;
    private Board board;
    private Ball ball;
    private ArrayList<Brick> bricks;
    private Iterator<Brick> it;

    public enum Direction {
        RIGHT, LEFT, VERTICAL, HORIZONTAL;
    }

    @Override
    public int getID() {
        return 1;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        // dimensions of the container
        contHeight = container.getHeight();
        contWidth = container.getWidth();
        collideChecker = new CollideChecker();
        board = new Board(0,contHeight-30,80,15);
        float ballRadius = 10;
        float ballXPos = board.getX()+board.getLength()/2;
        float ballYPos = board.getY()-ballRadius/2;
        ball = new Ball(ballXPos,ballYPos,ballRadius);
        bricks = new ArrayList<>();
        initBricks();
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
        drawBoard(g);
        drawBall(g);
        drawBricks(g);
    }

    /**
     * Draw the bricks on the screen.
     * @param g The Graphics object that is responsible for the drawing.
     */
    public void drawBricks(Graphics g) {
        for (Brick brick : bricks) {
            brick.draw(g);
        }
    }

    /**
     * Draw the ball on the screen.
     * @param g The Graphics object that is responsible for the drawing.
     */
    public void drawBall(Graphics g) {
        g.fill(ball);
    }

    /**
     * Draw the board on the screen.
     * @param g The Graphics object that is responsible for the drawing.
     */
    public void drawBoard(Graphics g) {
        board.draw(g);
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
            updateBoardPos(Direction.LEFT);
        }
        // move the board to the right if the right key is pressed
        if (input.isKeyDown(Input.KEY_RIGHT)) {
            updateBoardPos(Direction.RIGHT);
        }
        // go to the main menu if the escape key is pressed.
        if (input.isKeyPressed(Input.KEY_ESCAPE)) {
            game.enterState(0, new FadeOutTransition(), new FadeInTransition());
        }
        //check if the game has been beaten
        if(bricks.size() <= 0){
            game.enterState(4,new FadeOutTransition(), new FadeInTransition());
        }
    }

    private void updateBallPos(int delta) throws SlickException {
        int leftwallPos = 0;
        int rightWallPos = contWidth;
        int ceilingPos = 0;
        float newxPos = (ball.getX()+ball.getxSpeed()*0.05f*delta);
        float newyPos = (ball.getY()+ball.getySpeed()*0.05f*delta);
        ball.setLocation(newxPos, newyPos);

        for(Brick brick : bricks){
            Direction d = checkBrickCollision(brick);
            if(d != null){
                switch(d){
                    case VERTICAL:
                        ball.reverseYSpeed();
                        break;
                    case HORIZONTAL:
                        ball.reverseXSpeed();
                        break;
                }
            }
        }

        if (ball.getMaxY() >= contHeight) {
            ball.setY(contHeight - 2*ball.getRadius());
            ball.setLocation(newxPos,contHeight - 2*ball.getRadius());
            ball.reverseYSpeed();
        } else if (ball.getY() <= ceilingPos) {
            ball.setY(ceilingPos);
            ball.reverseYSpeed();
        }
        if (ball.getMaxX() >= rightWallPos) {
            ball.setX(contWidth - 2 * ball.getRadius());
            ball.reverseXSpeed();
        } else if (ball.getX() <= leftwallPos) {
            ball.setX(leftwallPos);
            ball.reverseXSpeed();
        }
        if (ball.intersects(board.getBody())) {
            ball.setY(board.getY()-2*ball.getRadius2());
            ball.reverseYSpeed();
        } else if (ball.intersects(board.getLeftEdge())) {
            Speed speed = collideChecker.getSpeedAfterCollision(ball, board.getLeftEdge());
            ball.setxSpeed(speed.getxSpeed());
            ball.setySpeed(speed.getySpeed());
        } else if (ball.intersects(board.getRightEdge())) {
            Speed speed = collideChecker.getSpeedAfterCollision(ball, board.getRightEdge());
            ball.setxSpeed(speed.getxSpeed());
            ball.setySpeed(speed.getySpeed());
        }
    }

    public void updateBricks(){
        it = bricks.iterator();
        while(it.hasNext()){
            if(it.next().getLives() <= 0){
                it.remove();
            }
        }
    }
    public Direction checkBrickCollision(Brick brick) {
        if (ball.intersects(brick.getSouthLine()) || ball.intersects(brick.getNorthLine())) {
            brick.decrementLife();
            return Direction.VERTICAL;
        } else if (ball.intersects(brick.getEastLine()) || ball.intersects(brick.getWestLine())) {
            brick.decrementLife();
            return Direction.HORIZONTAL;
        }
        return null;
    }

    private void updateBoardPos(Direction d) throws SlickException {
        float currentxPos = board.getX();
        float boardLength = board.getLength();
        int speed = board.getSpeed();
        if(d == Direction.LEFT){
            if(currentxPos > 0) {
                float xPos = currentxPos - speed;
                board.setX(xPos);
            }
        }
        if(d == Direction.RIGHT){
            if(currentxPos+boardLength < contWidth) {
                float xPos;
                if(currentxPos+5 >= contWidth){
                    xPos = contWidth-boardLength;
                } else {
                    xPos = currentxPos + speed;

                }
                board.setX(xPos);
            }
        }
    }
}