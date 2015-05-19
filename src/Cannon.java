import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

/**
 * Created by RobertLorentz on 18/05/15.
 */
public class Cannon implements PlayingFieldItem{
    private Rectangle body;
    private float xPosition, yPosition, height, width;

    public Cannon(float xPosition, float yPosition, float height, float width){
        this.xPosition = xPosition; this.yPosition = yPosition; this.height = height; this.width = width;
        body = new Rectangle(xPosition,yPosition,width,height);

    }

    @Override
    public void draw(Graphics g) {
        g.fill(body);
    }

    public void setX(float xPos1){
        body = new Rectangle(xPos1,yPosition,width,height);
    }

}
