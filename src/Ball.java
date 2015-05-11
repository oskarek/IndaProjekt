import org.newdawn.slick.*;
/**
 * A ball in the game
 *
 * Created by Robert Lorentz on 08/05/15.
 */
public class Ball extends PlayingFieldItem {
    private int xSpeed;
    private int ySpeed;
    private int yPosition;
    private int xPosition;
    private int diameter;
    private Image ballImage;

    public Ball() throws SlickException {
        ballImage = new Image("res/UIButtons/ball.png");

        //initiating values
        xSpeed = -1;
        ySpeed = -1;
        diameter = ballImage.getWidth();
        xPosition = 300;
        yPosition = 10;
    }

    public int getxSpeed() {
        return xSpeed;
    }

    public int getySpeed() {
        return ySpeed;
    }

    public int getyPosition() {
        return yPosition;
    }

    public int getxPosition() {
        return xPosition;
    }

    public int getDiameter(){
        return diameter;
    }

    public void setxSpeed(int xSpeed) {
        this.xSpeed = xSpeed;
    }

    public void setySpeed(int ySpeed) {
        this.ySpeed = ySpeed;
    }

    public void setyPosition(int yPosition) {
        this.yPosition = yPosition;
    }

    public void setxPosition(int xPosition) {
        this.xPosition = xPosition;
    }

    public void setDiameter(int diameter){
        this.diameter = diameter;
    }

    public Image getBallImage(){
        return ballImage;
    }
}
