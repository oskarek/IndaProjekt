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
    private int height;
    private Image boardImage;
    private int xPos;
    private int yPos;
    private int speed;

    public Board() throws SlickException {
        boardImage = new Image("res/UIButtons/board.png");
        length = boardImage.getWidth();
        height = boardImage.getHeight();
        speed = 3;
    };

    public int getSpeed(){
        return speed;
    }

    public void setSpeed(int speed){
        this.speed = speed;
    }

    public int getLength(){
        return length;
    }
    public void setLength(int length){
        this.length = length;
    }

    public int getHeight(){
        return height;
    }

    public int getyPos(){
        return yPos;
    }

    public void setyPos(int yPos){
        this.yPos = yPos;
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
