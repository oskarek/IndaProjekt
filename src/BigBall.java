import org.newdawn.slick.SlickException;

/**
 * Created by RobertLorentz on 18/05/15.
 */
public class BigBall extends PowerUp {
    private Ball ball;
    public BigBall(int xPosition, int yPosition, Ball ball) throws SlickException {
        super(xPosition, yPosition);
        this.ball = ball;
    }

    @Override
    public void invoke() {
        ball.fourTimesOriginalSize();
    }

    @Override
    public void reverse() {
        ball.normalSize();
    }
}
