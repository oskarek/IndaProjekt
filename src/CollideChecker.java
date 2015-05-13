import org.newdawn.slick.geom.Circle;

/**
 * A class to analyze the collisions in the game.
 * Created by oskarek on 2015-05-13.
 */
public class CollideChecker {

    public Speed getSpeedAfterCollision(Ball ball, Circle collideCircle) {
        float xSpeed = (float) ((ball.getSpeed()*(ball.getCenterX()-collideCircle.getCenterX())) /
                (Math.sqrt(Math.pow(ball.getCenterX()-collideCircle.getCenterX(),2) +
                Math.pow(ball.getCenterY()-collideCircle.getCenterY(),2))));

        float ySpeed = (float) ((ball.getSpeed()*(ball.getCenterY()-collideCircle.getCenterY())) /
                (Math.sqrt(Math.pow(ball.getCenterX()-collideCircle.getCenterX(),2) +
                Math.pow(ball.getCenterY()-collideCircle.getCenterY(),2))));
        return new Speed(xSpeed, ySpeed);
    }
    /*public Speed getSpeedAfterCollision2(Ball ball, Board board){
        float ySpeed = (float) ball.getySpeed()/(Math.abs(ball.getX()+ball.getRadius()-(board.getX()+board.getLength()/2)+3));
        float xSpeed = ball.getxSpeed();
        return new Speed(xSpeed,ySpeed);
    }
    */
}