import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;

/**
 * Created by RobertLorentz on 18/05/15.
 */
public class Projectile extends Circle implements PlayingFieldItem {
    private Image projImage;
    private float yPosition;
    public Projectile(float centerPointX, float centerPointY, float radius) throws SlickException {
        super(centerPointX,centerPointY,radius);
        projImage = new Image("res/UIElements/projectile.png");
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(projImage, super.getX(), super.getY());
    }

    public void setY(float y){
        super.setY(y);
    }
    public float getY(){ return super.getY(); }

}
