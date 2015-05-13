import org.newdawn.slick.*;
import org.newdawn.slick.geom.RoundedRectangle;

/**
 * SKELETON CODE
 * The board that you control in the game.
 *
 * Created by oskarek on 2015-05-07.
 */
public class Board extends RoundedRectangle {
    private PowerUp currentPowerUp;
    private Image boardImage;
    private int speed;

    public Board(float x, float y, float width, float height, float cornerRadius) throws SlickException {
        super(x, y, width, height, cornerRadius);
        boardImage = new Image("res/UIButtons/board.png");
        speed = 3;
    }

    public int getSpeed(){
        return speed;
    }

    public void setSpeed(int speed){
        this.speed = speed;
    }

    public Image getBoardImage(){
        return boardImage;
    }
}