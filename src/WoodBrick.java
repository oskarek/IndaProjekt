import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * A wood brick.
 *
 * Created by oskarek on 2015-05-17.
 */
public class WoodBrick extends Brick {

    public WoodBrick(int xPosition, int yPosition) throws SlickException {
        super(xPosition, yPosition, 2);
        Image i = new Image("res/UIButtons/brick_wood_2.png");
        Image i2 = new Image("res/UIButtons/brick_wood.png");
        super.addImage(i,1);
        super.addImage(i2,2);
        super.createHitBox();
    }
}
