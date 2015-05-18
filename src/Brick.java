import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Line;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by oskarek on 2015-05-16.
 */
public abstract class Brick implements PlayingFieldItem {
    private int width;
    private int height;
    private int xPosition;
    private int yPosition;
    private int lives;
    private HashMap<Integer, Image> brickImages = new HashMap<>();
    private Line northLine, southLine, westLine, eastLine;
    private HashSet<Circle> corners;

    public Brick(int xPosition, int yPosition, int lives) throws SlickException {
        this.xPosition = xPosition; this.yPosition = yPosition; this.lives = lives;
    }

    /**
     * Create the hitbox for the brick.
     */
    protected void createHitBox() {
        width = brickImages.get(1).getWidth();
        height = brickImages.get(1).getHeight();

        //creates lines around the brick
        northLine = new Line(xPosition+2,yPosition,xPosition+width-2,yPosition);
        southLine = new Line(xPosition+2,yPosition+height,xPosition+width-2,yPosition+height);
        westLine = new Line(xPosition,yPosition+2,xPosition,yPosition+height-2);
        eastLine = new Line(xPosition+width,yPosition+2,xPosition+width,yPosition+height-2);

        // creates circles in the corners
        Circle topLeftCirc = new Circle(xPosition + 2, yPosition + 2, 2);
        Circle topRightCirc = new Circle(xPosition + width - 2, yPosition + 2, 2);
        Circle bottomLeftCirc = new Circle(xPosition + 2, yPosition + height - 2, 2);
        Circle bottomRightCirc = new Circle(xPosition + width - 2, yPosition + height - 2, 2);

        corners = new HashSet<>();
        corners.add(topLeftCirc); corners.add(topRightCirc);
        corners.add(bottomLeftCirc); corners.add(bottomRightCirc);
    }

    public void draw(Graphics g){
        Image brickImage = brickImages.get(lives);
        g.drawImage(brickImage,xPosition,yPosition);
        //g.draw(northLine); g.draw(southLine); g.draw(westLine); g.draw(eastLine);
    }

    /**
     * Add the images representing the different livestages.
     * @param image The image.
     * @param livesLeft The number of lives left that will be associated with this image.
     */
    protected void addImage(Image image, int livesLeft) {
        brickImages.put(livesLeft,image);
    }

    public HashSet<Circle> getCorners() {
        return corners;
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
        return brickImages.get(lives);
    }
    public int getWidth(){return width;}
    public int getHeight(){return height;}
    public int getLives(){ return lives;}
    public void decrementLife(){lives--;}
}
