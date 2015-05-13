import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Rectangle;

/**
 * Created by oskarek on 2015-05-13.
 */
public class Board {
    private float xPos, yPos, length, height;
    private Rectangle body;
    private Circle leftEdge, rightEdge;
    private PowerUp currentPowerUp;
    private Image boardImage;
    private int speed;
    private int score;

    public Board(float x, float y, float length, float height) throws SlickException {
        xPos = x; yPos = y; this.length = length; this.height = height;
        body = new Rectangle(x+height/2,y,length-height,height);
        leftEdge = new Circle(x+height/2,y+height/2,height/2);
        rightEdge = new Circle(x+length-height/2,y+height/2,height/2);
        boardImage = new Image("res/UIButtons/board.png");
        speed = 5;
    }

    public void draw(Graphics g) {
        g.fill(body);
        g.fill(leftEdge);
        g.fill(rightEdge);
    }

    public float getX() {
        return xPos;
    }

    public float getY() {
        return yPos;
    }

    public void setX(float x) {
        leftEdge.setX(x);
        body.setX(x + height/2);
        rightEdge.setX(x + length - height);
        xPos = x;
    }

    public void setY(float y) {
        leftEdge.setY(y);
        body.setY(y);
        rightEdge.setY(y);
        yPos = y;
    }

    public void setLength(float length) {
        body = new Rectangle(xPos+height/2,yPos,length-height,height);
        leftEdge = new Circle(xPos+height/2,yPos+height/2,height/2);
        rightEdge = new Circle(xPos+length-height/2,yPos+height/2,height/2);
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
}
