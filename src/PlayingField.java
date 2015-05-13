import org.newdawn.slick.*;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.RoundedRectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * The main playing field for the game.
 *
 * Created by oskarek on 2015-05-07.
 */
public class PlayingField extends BasicGameState {
    private int contWidth, contHeight;
    private static final int DIRECTION_LEFT = -1;
    private static final int DIRECTION_RIGHT = 1;
    Board board;
    Ball ball;
    ArrayList<Brick> bricks;


    public enum Direction {
        NORTH, SOUTH, WEST, EAST;
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

        board = new Board(0,contHeight-30,50,10,4);
        ball = new Ball(80,80,10);
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

    public void drawBricks(Graphics g) throws SlickException {
        for (Brick brick : bricks) {
            brick.draw(g);
        }
    }

    public void drawBall(Graphics g) throws SlickException {
        g.fill(ball);
    }

    private void updateBallPos()throws SlickException {
        int leftwallPos = 0; int ceilingPos = 0;
        float newxPos = (ball.getX()+ball.getxSpeed());
        float newyPos = (ball.getY()+ball.getySpeed());
        ball.setLocation(newxPos, newyPos);

        for(Brick brick : bricks){
            Direction d = brick.checkCollision(ball);
            if(d != null){
                switch(d){
                    case NORTH:
                        ball.reverseYSpeed();
                }
            }
        }

        if (ball.getMaxY() >= contHeight) {
            ball.setY(contHeight - 2*ball.getRadius());
            ball.reverseYSpeed();
        }
        if (ball.getY() <= ceilingPos) {
            ball.setY(ceilingPos);
            ball.reverseYSpeed();
        }
        if (ball.getMaxX() >= contWidth) {
            ball.setX(contWidth - 2 * ball.getRadius());
            ball.reverseXSpeed();
        }
        if (ball.getX() <= leftwallPos) {
            ball.setX(leftwallPos);
            ball.reverseXSpeed();
        }
        if (ball.intersects(board)) {
            ball.reverseYSpeed();
        }
    }

    public void drawBoard(Graphics g) throws SlickException {
        g.fill(board);
    }

    private void updateBoardPos(int direction) throws SlickException {
        float currentxPos = board.getX();
        float boardLength = board.getWidth();
        int speed = board.getSpeed();
        if(direction == DIRECTION_LEFT){
            if(currentxPos > 0) {
                float xPos = currentxPos - board.getSpeed();
                board.setX(xPos);
            }
        }
        if(direction == DIRECTION_RIGHT){
            if(currentxPos+boardLength < contWidth) {
                float xPos;
                if(currentxPos+5 >= contWidth){
                    xPos = contWidth-boardLength;
                } else {
                    xPos = currentxPos + board.getSpeed();

                }
                board.setX(xPos);
            }
        }
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        drawBoard(g);
        drawBall(g);
        drawBricks(g);
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        Input input = container.getInput();
        if (input.isKeyDown(Input.KEY_LEFT)) {
            updateBoardPos(DIRECTION_LEFT);
        }
        if (input.isKeyDown(Input.KEY_RIGHT)) {
            updateBoardPos(DIRECTION_RIGHT);
        }
        if (input.isKeyPressed(Input.KEY_ESCAPE)) {
            game.enterState(0, new FadeOutTransition(), new FadeInTransition());
        }
        updateBallPos();
    }
}