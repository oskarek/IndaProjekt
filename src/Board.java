import org.newdawn.slick.*;

/**
 * SKELETON CODE
 * The board that you control in the game.
 *
 * Created by oskarek on 2015-05-07.
 */
public class Board extends PlayingFieldItem {
    private PowerUp currentPowerUp;
    private int length;
    private Image boardImage;
    private int xPos;

    public Board() throws SlickException {
        boardImage = new Image("res/UIButtons/board.png");
        length = boardImage.getWidth();
    };

    public int getLength(){
        return length;
    }
    public void setLength(int length){
        this.length = length;
    }

    public int getxPos(){
        return xPos;
    }

    public void setxPos(int xPos){
        this.xPos = xPos;
    }

    public Image getBoardImage(){
        return boardImage;
    }

    public void setBoardSize(Image image){
        boardImage = image;
    }
}
