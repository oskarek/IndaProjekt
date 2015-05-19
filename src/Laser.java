import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;

/**
 * Created by RobertLorentz on 18/05/15.
 */
public class Laser extends Circle implements PlayingFieldItem {
    private Image laserImage;
    public Laser(float centerPointX, float centerPointY, float radius) throws SlickException {
        super(centerPointX,centerPointY,radius);
        laserImage = new Image("res/UIButtons/laser.png");
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(laserImage, super.getX(), super.getY());
    }
    public void setY(float y){super.setY(y);}
    public float getY(){ return super.getY(); }

}
