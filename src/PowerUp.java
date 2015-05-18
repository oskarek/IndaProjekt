import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * A power.up in the game.
 * Created by oskarek on 2015-05-07.
 */
public abstract class PowerUp implements PlayingFieldItem {

    private float xPosition;
    private float yPosition;
    private Image currentImage;
    private Rectangle hitBox;
    private float width; private float height;

    public PowerUp(int xPosition,int yPosition) throws SlickException {
        currentImage = new Image("res/UIButtons/powerup1.png");
        this.xPosition = xPosition; this.yPosition = yPosition;
        width = currentImage.getWidth();height = currentImage.getHeight();
        hitBox = new Rectangle(xPosition,yPosition,width,height);
    }
    @Override
    public void draw(Graphics g) {
        g.drawImage(currentImage, xPosition, yPosition);
    }

    public abstract void invoke();
    public abstract void reverse();
    public void setY(float yPosition){
        this.yPosition = yPosition;
    }
    public void updateHitBox(){ hitBox = new Rectangle(xPosition,yPosition,width,height); }
    public float getY(){ return yPosition; }
    public float getX(){ return xPosition; }
    public Rectangle getHitBox(){ return hitBox; }
}
