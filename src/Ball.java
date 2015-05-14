import org.newdawn.slick.*;
import org.newdawn.slick.geom.Circle;

/**
 * A ball in the game
 *
 * Created by Robert Lorentz on 08/05/15.
 */
public class Ball extends Circle implements PlayingFieldItem {
    private Circle baseShape;
    private float speed;
    private float xSpeed;
    private float ySpeed;
    private float angle;
    private Image ballImage;

    public Ball(float centerPointX, float centerPointY, float radius) /*throws SlickException*/ {
        super(centerPointX,centerPointY,radius);
        //ballImage = new Image("res/UIButtons/ball.png");

        //initiating values
        speed = (float)(Math.sqrt(Math.pow(4,2)+Math.pow(4,2)));
        angle = (float) ((Math.PI));
        xSpeed = 4;
        ySpeed = 2;
    }

    /**
     * Get the speed of the ball.
     * @return The speed of the ball.
     */
    public float getSpeed() {
        return speed;
    }

    /**
     * Set a speed for the ball.
     * @param speed The speed for the ball.
     */
    public void setSpeed(float speed) {
        this.speed = speed;
    }

    /**
     * Reverse the speed direction.
     */
    public void reverseSpeed() {
        if (angle < Math.PI) angle = (float) (angle+Math.PI);
        else angle = (float) (angle-Math.PI);
    }

    /**
     * Reverse the horizontal component of the speed.
     */
    public void reverseHorizontalSpeed() {
        xSpeed *= -1;
        if (angle <= Math.PI) angle = (float) (Math.PI-angle);
        else angle = (float) (3*Math.PI-angle);
    }

    /**
     * Reverse the vertical component of the speed.
     */
    public void reverseVerticalSpeed() {
        ySpeed *= -1;
        angle = (float) (2*Math.PI-angle);
    }

    /**
     * Get the direction of the ball, in angles from 0 to 2*pi.
     */
    public float getDirection() {
        return angle;
    }

    /**
     * Set the direction of the ball, between 0 and 2*pi radians.
     * @param angle The angle (in radians) between the direction of the ball and the x-axis.
     */
    public void setDirection(float angle) {
        this.angle = angle;
    }

    /**
     * Move the ball a given distance.
     * @param distance The distance to move the ball.
     */
    public void move(float distance) {
        float x = super.getX();
        float y = super.getY();
        float alpha = angle;
        if (angle>=0 && angle<Math.PI/2) {
            super.setX(x + (float) (distance*Math.cos(alpha)));
            super.setY(y + (float) -(distance*Math.sin(alpha)));
            return;
        }
        if (angle>=Math.PI/2 && angle<Math.PI) {
            alpha = (float) (Math.PI - alpha);
            super.setX(x + (float) -(distance*Math.cos(alpha)));
            super.setY(y + (float) -(distance*Math.sin(alpha)));
            return;
        }
        if (angle>=Math.PI && angle<3*Math.PI/2) {
            alpha = (float) (Math.PI + alpha);
            super.setX(x + (float) -(distance*Math.cos(alpha)));
            super.setY(y + (float) (distance*Math.sin(alpha)));
            return;
        }
        if (angle>=(3*Math.PI)/2 && angle<=2*Math.PI) {
            alpha = (float) (2*Math.PI - alpha);
            super.setX(x + (float) (distance*Math.cos(alpha)));
            super.setY(y + (float) (distance*Math.sin(alpha)));
            return;
        }
    }

    /**
     * Get the image that represents the ball.
     * @return An Image object representing this ball.
     */
    public Image getBallImage(){
        return ballImage;
    }

    /**
     * Return the underlying Circle object that forms this ball.
     * @return The underlying Circle object that forms this ball.
     */
    public Circle getBaseShape() {
        return baseShape;
    }

    @Override
    public void draw(Graphics g) {
        g.fill(this);
    }
}