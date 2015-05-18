import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import java.util.ArrayList;
import java.util.Random;

/**
 * A regular brick.
 *
 * Created by oskarek on 2015-05-17.
 */
public class RegularBrick extends Brick {

    public RegularBrick(int xPosition, int yPosition) throws SlickException {
        super(xPosition, yPosition, 1);
        ArrayList<Image> images = new ArrayList<>();
        images.add(new Image("res/UIButtons/brick_blue.png"));
        images.add(new Image("res/UIButtons/brick_green.png"));
        images.add(new Image("res/UIButtons/brick_red.png"));
        images.add(new Image("res/UIButtons/brick_yellow.png"));
        Random rand = new Random();
        Image i = images.get(rand.nextInt(images.size()));
        super.addImage(i,1);
        super.createHitBox();
    }
}
