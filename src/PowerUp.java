import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

/**
 * A power.up in the game.
 * Created by oskarek on 2015-05-07.
 */
public abstract class PowerUp implements PlayingFieldItem {

    private float xPosition;
    private float yPosition;
    private int duration;
    private Image image;
    private Rectangle hitBox;
    private float width; private float height;

    public PowerUp(int xPosition,int yPosition) throws SlickException {
        this.xPosition = xPosition; this.yPosition = yPosition;
        width = image.getWidth();height = image.getHeight();
        hitBox = new Rectangle(xPosition,yPosition,width,height);
    }
    @Override
    public void draw(Graphics g) {
        g.drawImage(image, xPosition, yPosition);
    }

    /**
     * Get the duration that this PowerUp will be active.
     * @return The duration.
     */
    public int getDuration() {
        return duration;
    }

    /**
     * Set the duration that this PowerUp will be active.
     * @param duration The duration.
     */
    protected void setDuration(int duration) {
        this.duration = duration;
    }

    /**
     * Get the image of this powerup.
     * @return The image.
     */
    public Image getImage() {
        return image;
    }

    /**
     * Set the image of this powerup.
     * @param image The image.
     */
    protected void setImage(Image image) {
        this.image = image;
    }

    public abstract void invoke() throws SlickException;
    public abstract void reverse();

    public void setY(float yPosition){
        this.yPosition = yPosition;
    }
    public void updateHitBox(){ hitBox = new Rectangle(xPosition,yPosition,width,height); }
    public float getY(){ return yPosition; }
    public float getX(){ return xPosition; }
    public Rectangle getHitBox(){ return hitBox; }
}
