/**
 * A simple clas to bind together an 'xSpeed' and a 'ySpeed' value.
 * Created by oskarek on 2015-05-13.
 */
public class Speed {
    private float xSpeed, ySpeed;

    public Speed(float xSpeed, float ySpeed) {
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
    }

    public float getxSpeed() {
        return xSpeed;
    }

    public float getySpeed() {
        return ySpeed;
    }
}
