import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

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
    @Override
    public int getID() {
        return 1;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        // dimensions of the container
        board = new Board();
        contHeight = container.getHeight();
        contWidth = container.getWidth();


    }

    public void createBricks(GameContainer container) throws SlickException {

    }

    public void createBall(GameContainer container) throws SlickException {

    }

    private void updateBallPos(Graphics g)throws SlickException {

    }

    public void drawBoard(Graphics g) throws SlickException {
        Image boardImage = board.getBoardImage();
        int xPos = board.getxPos();
        int yPos = contHeight-30;
        g.drawImage(boardImage, xPos, yPos);
    }

    private void updateBoardPos(int direction) throws SlickException {
        if(direction == DIRECTION_LEFT){
            int currentxPos = board.getxPos();
            if(currentxPos > 0) {
                int xPos = currentxPos - 5;
                board.setxPos(xPos);
            }
        }
        if(direction == DIRECTION_RIGHT){
            int boardLength = board.getLength();
            int currentxPos = board.getxPos();
            if(currentxPos+boardLength < contWidth) {
                int xPos;
                if(currentxPos+5 >= contWidth){
                    xPos = contWidth-boardLength;
                } else {
                    xPos = currentxPos + 5;

                }
                board.setxPos(xPos);
            }
        }
    }


    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        drawBoard(g);
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
    }
}