/**
 * A ball in the game
 *
 * Created by RobertLorentz on 08/05/15.
 */
public class Ball extends PlayingFieldItem {
    private int velocity;

    public int getVelocity() {
        return velocity;
    }

    public void setVelocity(int velocity) {
        this.velocity = velocity;
    }
}
