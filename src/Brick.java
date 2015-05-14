import org.newdawn.slick.*;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Line;
import org.newdawn.slick.geom.Rectangle;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * A brick in the game.
 *
 * Created by oskarek on 2015-05-07.
 */
public class Brick implements PlayingFieldItem {
    private int width;
    private int height;
    private int xPosition;
    private int yPosition;
    private int lives;
    private HashMap<Integer, Image> brickImages = new HashMap<>();
    private Line northLine,southLine, westLine, eastLine;

    public Brick(int xPosition,int yPosition,int lives) throws SlickException{
        this.xPosition = xPosition; this.yPosition = yPosition; this.lives = lives;
        brickImages.put(4,new Image("res/UIButtons/brick1.png"));
        brickImages.put(3,new Image("res/UIButtons/brick_green.png"));
        brickImages.put(2,new Image("res/UIButtons/brick_yellow.png"));
        brickImages.put(1,new Image("res/UIButtons/brick_red.png"));
        brickImages.put(0,new Image("res/UIButtons/brick_red.png"));
        width = brickImages.get(getLives()).getWidth();
        height = brickImages.get(getLives()).getHeight();

        //draws lines around the brick
        northLine = new Line(xPosition+1,yPosition,xPosition+width-1,yPosition);
        southLine = new Line(xPosition+1,yPosition+height,xPosition+width-1,yPosition+height);
        westLine = new Line(xPosition,yPosition,xPosition,yPosition+height);
        eastLine = new Line(xPosition+width,yPosition,xPosition+width,yPosition+height);
    }

    public void draw(Graphics g){
        Image brickImage = brickImages.get(getLives());
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
        return brickImages.get(getLives());
    }
    public int getWidth(){return width;}
    public int getHeight(){return height;}
    public int getLives(){ return lives;}
    public void decrementLife(){lives--;}

}

