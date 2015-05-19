import org.newdawn.slick.SlickException;

/**
 * Created by oskarek on 2015-05-18.
 */
public class FastBall extends PowerUp {
    private Ball ball;
    public FastBall(int xPosition, int yPosition, Ball ball) throws SlickException {
        super(xPosition, yPosition);
        super.setDuration(500);
        this.ball = ball;
    }
    public void invoke(){
        ball.doubleSpeed();
    }
    public void reverse() { ball.halfSpeed(); }
}
