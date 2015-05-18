import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * A metal brick.
 *
 * Created by oskarek on 2015-05-16.
 */
public class MetalBrick extends Brick {

    public MetalBrick(int xPosition, int yPosition) throws SlickException {
        super(xPosition, yPosition, 3);
        Image i = new Image("res/UIButtons/brick_metal_3alt.png");
        Image i2 = new Image("res/UIButtons/brick_metal_2.png");
        Image i3 = new Image("res/UIButtons/brick_metal.png");
        super.addImage(i,1);
        super.addImage(i2,2);
        super.addImage(i3,3);
        super.createHitBox();
    }
}
