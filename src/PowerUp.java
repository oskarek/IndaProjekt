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
public class PowerUp implements PlayingFieldItem {

    private HashMap<String,Image> powerupImages;
    private float xPosition;
    private float yPosition;
    private PowerUpType currentPowerup;
    private Image currentImage;
    private float fallingSpeed;
    private enum PowerUpType {
        slowBall, fastBall, smallBoard, bigBoard,
    }
    private ArrayList<PowerUpType> types;
    private Rectangle hitBox;
    private float width; private float height;

    public PowerUp(int xPosition,int yPosition,int randomNum) throws SlickException {
        //powerupImages = new HashMap<>();
       // powerupImages.put("standard",new Image("res/UIButtons/powerup1.png"));
        currentImage = new Image("res/UIButtons/brick1.png");
        this.xPosition = xPosition; this.yPosition = yPosition;
        float width = currentImage.getWidth();float height = currentImage.getHeight();
        hitBox = new Rectangle(xPosition,yPosition,width,height);

        //get the according powerup with the random number
        types = new ArrayList<>();
        Collections.addAll(types, PowerUpType.values());
        currentPowerup = types.get(randomNum);

    }
    @Override
    public void draw(Graphics g) {
        g.drawImage(currentImage, xPosition, yPosition);
        g.fill(hitBox);
    }

    public void invokePowerup(Ball ball, Board board){
        switch(currentPowerup){
            case slowBall:
                ball.setSpeed(ball.getSpeed()/2);
                break;
            case fastBall:
                ball.setSpeed(ball.getSpeed()*2);
                break;
            case smallBoard:
                board.setLength(board.getLength()/2);
                break;
            case bigBoard:
                board.setLength(board.getLength()*2);
                break;

        }
    }
    public void setY(float yPosition){
        this.yPosition = yPosition;
    }
    public void updateHitBox(){
        hitBox = new Rectangle(xPosition,yPosition,width,height);
    }
    public float getY(){ return yPosition; }
    public float getX(){ return xPosition; }
    public Rectangle getHitBox(){ return hitBox; }
    public int getNumberOfPowerups(){ return types.size();}
}
