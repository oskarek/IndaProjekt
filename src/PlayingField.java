import org.newdawn.slick.*;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import java.util.ArrayList;

/**
 * SKELETON CODE
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

    @Override
    public int getID() {
        return 1;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        // dimensions of the container
        contHeight = container.getHeight();
        contWidth = container.getWidth();
        board = new Board(); board.setyPos(contHeight-30);
        ball = new Ball();
        bricks = new ArrayList<>();

        bricks.add(new Brick());

    }

    public void drawBricks(Graphics g) throws SlickException {
        for(int i=0; i < bricks.size();i++){
            Image brickImage = bricks.get(i).getBrickImage();
            int xPos = bricks.get(i).getxPosition();
            int yPos = bricks.get(i).getyPosition();
            g.drawImage(brickImage,xPos,yPos);
        }
    }

    public void drawBall(Graphics g) throws SlickException {
        Image ballImage = ball.getBallImage();
        int xPos = ball.getxPosition();
        int yPos = ball.getyPosition();
        g.drawImage(ballImage, xPos, yPos);
    }

    private void updateBallPos()throws SlickException {
        int leftwallPos = 0; int ceilingPos = 0;
        int xPos = (ball.getxPosition()+ball.getxSpeed());
        int yPos = (ball.getyPosition()+ball.getySpeed());
        ball.setyPosition(yPos); ball.setxPosition(xPos);

        if(ball.getyPosition() >= (contHeight - ball.getDiameter())){
            ball.setyPosition(contHeight - ball.getDiameter());
            ball.setySpeed(-ball.getySpeed());
        }
        if(ball.getyPosition() <= (ceilingPos)){
            ball.setyPosition(ceilingPos);
            ball.setySpeed(-ball.getySpeed());
        }
        if(ball.getxPosition() >= (contWidth - ball.getDiameter())){
            ball.setxPosition(contWidth - ball.getDiameter());
            ball.setxSpeed(-ball.getxSpeed());
        }
        if(ball.getxPosition() <= (leftwallPos)){
            ball.setxPosition(leftwallPos);
            ball.setxSpeed(-ball.getxSpeed());
        }
        //if(ball.getxPosition() > board.getxPos()-ball.getDiameter() && ball.getxPosition() < (board.getxPos()+board.getLength())){
          //  if(ball.getyPosition()+ball.getDiameter() > board.getyPos() &&
         //           ball.getyPosition()+ball.getDiameter() < board.getyPos()+board.getHeight() ){
        //        ball.setxSpeed(-ball.getxSpeed());
        //    }
        //}

        //if(ball.getxPosition() > board.getxPos() && ball.getxPosition() < board.getxPos()+1){
        //    if(ball.getyPosition()+ ball.getDiameter() > board.getyPos()) {
        //        ball.setxSpeed(-ball.getxSpeed());
        //    }
        //}

        if(ball.getxPosition() > board.getxPos()-2 && ball.getxPosition() < (board.getxPos()+board.getLength()+2)){
            if (ball.getyPosition()+ball.getDiameter() > board.getyPos()) {
                ball.setySpeed(-ball.getySpeed());
            }
        }
    }

    public void drawBoard(Graphics g) throws SlickException {
        Image boardImage = board.getBoardImage();
        int xPos = board.getxPos();
        int yPos = board.getyPos();
        g.drawImage(boardImage, xPos, yPos);
    }

    private void updateBoardPos(int direction) throws SlickException {
        int currentxPos = board.getxPos();
        int boardLength = board.getLength();
        int speed = board.getSpeed();
        if(direction == DIRECTION_LEFT){
            if(currentxPos > 0) {
                int xPos = currentxPos - board.getSpeed();
                board.setxPos(xPos);
            }
        }
        if(direction == DIRECTION_RIGHT){
            if(currentxPos+boardLength < contWidth) {
                int xPos;
                if(currentxPos+5 >= contWidth){
                    xPos = contWidth-boardLength;
                } else {
                    xPos = currentxPos + board.getSpeed();

                }
                board.setxPos(xPos);
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
        updateBallPos();
    }
}