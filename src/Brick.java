import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * A brick in the game.
 *
 * Created by oskarek on 2015-05-07.
 */
public class Brick implements PlayingFieldItem {
    private int width;
    private int height;
    private int xPosition;
    private int yPosition;
    private int lives;
    private Image brickImage;

    public Brick() throws SlickException{
        brickImage = new Image("res/UIButtons/brick1.png");
        width = brickImage.getWidth();
        height = brickImage.getHeight();
    }

    public int getyPosition() {
        return yPosition;
    }

    public int getxPosition() {
        return xPosition;
    }

    public void setyPosition(int yPosition) {
        this.yPosition = yPosition;
    }

    public void setxPosition(int xPosition) {
        this.xPosition = xPosition;
    }

    public Image getBrickImage(){
        return brickImage;
    }

    public void setBrickImage(Image image){
        brickImage = image;
    }
}

