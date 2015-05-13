import org.newdawn.slick.*;
import org.newdawn.slick.geom.Circle;

/**
 * A ball in the game
 *
 * Created by Robert Lorentz on 08/05/15.
 */
public class Ball extends Circle {
    private float xSpeed;
    private float ySpeed;
    private Image ballImage;

    public Ball(float centerPointX, float centerPointY, float radius) throws SlickException {
        super(centerPointX,centerPointY,radius);
        ballImage = new Image("res/UIButtons/ball.png");

        //initiating values
        xSpeed = -1;
        ySpeed = -1;
    }

    public float getxSpeed() {
        return xSpeed;
    }

    public float getySpeed() {
        return ySpeed;
    }

    public void setxSpeed(float xSpeed) {
        this.xSpeed = xSpeed;
    }

    public void setySpeed(float ySpeed) {
        this.ySpeed = ySpeed;
    }

    public void reverseXSpeed() {
        xSpeed *= -1;
    }

    public void reverseYSpeed() {
        ySpeed *= -1;
    }

    public Image getBallImage(){
        return ballImage;
    }
}