import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * Created by RobertLorentz on 19/05/15.
 */
public class LaserCannon extends Cannon {
    private Image image;
    float xPosition, yPosition;
    public LaserCannon(float xPosition, float yPosition) throws SlickException {
        super(xPosition, yPosition);
        super.setCannonImage(new Image("res/UIButtons/lasercannon.png"));
        this.xPosition=xPosition;this.yPosition=yPosition;
    }
}
