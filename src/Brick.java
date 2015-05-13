import org.newdawn.slick.*;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;

import java.awt.*;

/**
 * A brick in the game.
 *
 * Created by oskarek on 2015-05-07.
 */
public class Brick {
    private int width;
    private int height;
    private int xPosition;
    private int yPosition;
    private int lives;
    private Image brickImage;
    private Rectangle hitbox;

    public Brick(int x,int y,int lives, Rectangle rectangle) throws SlickException{
        brickImage = new Image("res/UIButtons/brick1.png");
        width = brickImage.getWidth();
        height = brickImage.getHeight();
        xPosition = x; y = yPosition; this.lives = lives;
        hitbox = rectangle;
    }

    public void draw(Graphics g){
        g.drawImage(brickImage,xPosition,yPosition);
    }

    public int getY() {
        return yPosition;
    }

    public int getX() {
        return xPosition;
    }

    public void setyPosition(int yPosition) {
        this.yPosition = yPosition;
    }

    public void setxPosition(int xPosition) {
        this.xPosition = xPosition;
    }

    public Image getBrickImage(){
        return brickImage;
    }
    public int getWidth(){return width;}
    public int getHeight(){return height;}

    public void setBrickImage(Image image){
        brickImage = image;
    }
}

