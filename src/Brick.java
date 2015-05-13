import org.newdawn.slick.*;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Line;
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
    private Line northLine,southLine, westLine, eastLine;

    public Brick(int x,int y,int lives) throws SlickException{
        brickImage = new Image("res/UIButtons/brick1.png");
        width = brickImage.getWidth();
        height = brickImage.getHeight();
        xPosition = x; yPosition = y; this.lives = lives;
        hitbox = new Rectangle(xPosition,yPosition,width,height);
        northLine = new Line(xPosition,yPosition,xPosition+width,yPosition);
        southLine = new Line(xPosition,yPosition+height,xPosition+width,yPosition+height);
        westLine = new Line(xPosition,yPosition,xPosition,yPosition+height);
        eastLine = new Line(xPosition+width,yPosition,xPosition+width,yPosition+height);
    }

    public void draw(Graphics g){
        //g.drawImage(brickImage,xPosition,yPosition);
        if(lives >= 0) {
            g.draw(northLine);
            g.draw(southLine);
            g.draw(westLine);
            g.draw(eastLine);
        }
    }

    public PlayingField.Direction checkCollision(Ball ball){
        if(ball.intersects(southLine) || ball.intersects(northLine)){
            lives--;
            return PlayingField.Direction.VERTICAL;
        } else if(ball.intersects(westLine) || ball.intersects(eastLine)){
            lives--;
            return PlayingField.Direction.HORIZONTAL;
        }

        return null;


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

