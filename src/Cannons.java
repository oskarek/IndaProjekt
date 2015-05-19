import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

/**
 * Created by RobertLorentz on 18/05/15.
 */
public class Cannons implements PlayingFieldItem{
    private Rectangle firstBody, secondBody;
    private float firstXPosition, firstYPosition, secondXPosition, secondYPosition;
    private int ammo;

    public Cannons(float firstXPosition, float firstYPosition, float secondXPosition, float secondYPosition, int ammo){
        this.firstXPosition = firstXPosition; this.firstYPosition = firstYPosition; this.ammo = ammo;
        this.secondXPosition = secondXPosition; this.secondYPosition = secondYPosition;
        firstBody = new Rectangle(firstXPosition,firstYPosition,4,7);
        secondBody = new Rectangle(secondXPosition,secondYPosition,4,7);
    }

    @Override
    public void draw(Graphics g) {
        g.fill(firstBody);
        g.fill(secondBody);
    }

    public void setX(float xPos1, float xPos2){
        firstBody = new Rectangle(xPos1,firstYPosition,4,7);
        secondBody = new Rectangle(xPos2,secondYPosition,4,7);
    }

    public void decrementAmmo(){
        ammo--;
    }
    public int getAmmo(){ return ammo; }
}
