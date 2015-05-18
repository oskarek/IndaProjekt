import org.newdawn.slick.SlickException;

/**
 * Created by oskarek on 2015-05-18.
 */
public class SlowBall extends PowerUp {
    private Ball ball;
    public SlowBall(int xPosition, int yPosition, Ball ball) throws SlickException {
        super(xPosition, yPosition);
        this.ball = ball;
    }
    public void invoke(){
        ball.halfSpeed();
    }
    public void reverse() { ball.doubleSpeed(); }
}
