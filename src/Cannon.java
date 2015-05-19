import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;

/**
 * Created by RobertLorentz on 18/05/15.
 */
public class Cannon implements PlayingFieldItem{

    private float xPosition, yPosition;
    private Image cannonImage;

    public Cannon(float xPosition, float yPosition){
        this.xPosition = xPosition; this.yPosition = yPosition;

    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(cannonImage,xPosition,yPosition);
    }
    public void setCannonImage(Image image){ cannonImage = image; }
    public void setX(float x){xPosition=x;}

}
