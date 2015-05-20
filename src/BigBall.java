import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * Created by RobertLorentz on 18/05/15.
 */
public class BigBall extends PowerUp {
    private Ball ball;
    public BigBall(int xPosition, int yPosition, Ball ball) throws SlickException {
        super(xPosition, yPosition);
        super.setImage(new Image("res/UIElements/powerup_bigball.png"));
        super.setDuration(500);
        this.ball = ball;
    }

    @Override
    public void invoke() {
        ball.setBigSize();
    }

    @Override
    public void reverse() {
        ball.setNormalSize();
    }
}
