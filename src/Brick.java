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

    public Brick(int xPosition,int yPosition,int lives) throws SlickException{
        brickImage = new Image("res/UIButtons/brick1.png");
        width = brickImage.getWidth();
        height = brickImage.getHeight();
        this.xPosition = xPosition; this.yPosition = yPosition; this.lives = lives;

        //draws lines around the brick
        northLine = new Line(xPosition+1,yPosition,xPosition+width-1,yPosition);
        southLine = new Line(xPosition+1,yPosition+height,xPosition+width-1,yPosition+height);
        westLine = new Line(xPosition,yPosition,xPosition,yPosition+height);
        eastLine = new Line(xPosition+width,yPosition,xPosition+width,yPosition+height);
    }

    public void draw(Graphics g){
        g.drawImage(brickImage,xPosition,yPosition);
        g.draw(northLine); g.draw(southLine); g.draw(westLine); g.draw(eastLine);
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
    public Line getNorthLine(){ return northLine; }
    public Line getSouthLine(){ return southLine; }
    public Line getWestLine(){ return westLine; }
    public Line getEastLine(){ return eastLine; }
    public Image getBrickImage(){
        return brickImage;
    }
    public int getWidth(){return width;}
    public int getHeight(){return height;}
    public int getLives(){ return lives;}
    public void decrementLife(){lives--;}

    public void setBrickImage(Image image){
        brickImage = image;
    }
}

