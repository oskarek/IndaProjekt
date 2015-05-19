import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Rectangle;

/**
 * Created by oskarek on 2015-05-13.
 */
public class Board implements PlayingFieldItem {
    private float xPos, yPos, length, height;
    private Rectangle body;
    private Circle leftEdge, rightEdge;
    private Image boardImage, boardImageDouble, boardImageHalf;
    private Image currentBoardImage;
    private int speed;
    private int score;
    private boolean activeCannons;
    private Cannons cannons;

    public Board(float x, float y, float length, float height) throws SlickException {
        xPos = x; yPos = y; this.length = length; this.height = height;
        body = new Rectangle(x+height/2,y,length-height,height);
        leftEdge = new Circle(x+height/2,y+height/2,height/2);
        rightEdge = new Circle(x+length-height/2,y+height/2,height/2);
        boardImage = new Image("res/UIButtons/board.png");
        boardImageDouble = new Image("res/UIButtons/board_x2.png");
        boardImageHalf = new Image("res/UIButtons/board_half.png");
        currentBoardImage = boardImage;
        speed = 8;
    }

    public void draw(Graphics g) {
        g.fill(body);
        g.fill(leftEdge);
        g.fill(rightEdge);
        g.drawImage(currentBoardImage,xPos,yPos);
        if(activeCannons){
            cannons.draw(g);
        }
    }

    public float getX() {
        return xPos;
    }

    public float getY() {
        return yPos;
    }

    public void setX(float x) {
        leftEdge.setX(x);
        body.setX(x + height / 2);
        rightEdge.setX(x + length - height);
        xPos = x;
        if(activeCannons){
            cannons.setX(x+height/2,x+length-height/2);
        }
    }

    public void setY(float y) {
        leftEdge.setY(y);
        body.setY(y);
        rightEdge.setY(y);
        yPos = y;
    }

    public void setLength(float newLength) {
        float xCenterPos = xPos+length/2;
        length = newLength;
        float newxPos = xCenterPos-length/2;
        body = new Rectangle(newxPos+height/2,yPos,length-height,height);
        leftEdge = new Circle(newxPos+height/2,yPos+height/2,height/2);
        rightEdge = new Circle(newxPos-height/2,yPos+height/2,height/2);
        setX(newxPos);
    }

    public void doubleLength() {
        setLength(160);
        currentBoardImage = boardImageDouble;
    }

    public void normalLength() {
        setLength(80);
        currentBoardImage = boardImage;
    }

    public void halfLength() {
        setLength(40);
        currentBoardImage = boardImageHalf;
    }

    public float getLength() {
        return length;
    }

    public Circle getLeftEdge() {
        return leftEdge;
    }

    public Circle getRightEdge() {
        return rightEdge;
    }

    public Rectangle getBody() {
        return body;
    }

    public int getSpeed(){
        return speed;
    }

    public void setSpeed(int speed){
        this.speed = speed;
    }

    public Image getBoardImage(){
        return boardImage;
    }

    public void incrementScore(){ score++;}
    public int getScore(){ return score; }

    public void addCannons(){
        activeCannons = true;
        cannons = new Cannons(xPos+height/2,yPos-height,xPos+length-height/2-4,yPos-height,3);
    }
    public void removeCannons(){
        activeCannons = false;
        cannons = null;
    }
    public boolean ableToShoot(){
        if(cannons != null){
            if(cannons.getAmmo() > 0){
                cannons.decrementAmmo();
                return true;
            }
        }
        return false;
    }
    public float getXPosFirstCannon(){ return xPos+height/2;}
    public float getYPosFirstCannon(){ return yPos-height; }
    public float getXPosSecondCannon(){ return xPos+length-height/2-4; }
    public float getYPosSecondCannon(){ return yPos-height; }
}
