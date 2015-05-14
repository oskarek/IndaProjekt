import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Circle;

import java.util.ArrayList;

/**
 * This class is responsible for dealing with everything
 * concerning the collisions in the game.
 *
 * Created by oskarek on 2015-05-13.
 */
public class CollideChecker {
    private GameContainer container;
    private int floorPos, ceilingPos, leftwallPos, rightWallPos;

    public CollideChecker(GameContainer container) {
        this.container = container;
        floorPos = container.getHeight();
        ceilingPos = 0;
        leftwallPos = 0;
        rightWallPos = container.getWidth();
    }

    /**
     * Checks if the ball has collides with something and, if it has,
     * updates its speed and direction accordingly.
     * @param ball The ball.
     * @param board The
     */
    public void checkBallCollisions(Ball ball, Board board, ArrayList<Brick> bricks) {
        // check if the ball has hit any of the bricks
        for(Brick brick : bricks){
            Direction d = getSpeedAfterCollisionWithBrick(ball, brick);
            if(d != null){
                switch(d){
                    case VERTICAL:
                        ball.reverseVerticalSpeed();
                        break;
                    case HORIZONTAL:
                        ball.reverseHorizontalSpeed();
                        break;
                }
            }
        }

        // check if the ball has hit the floor or celing
        if (ball.getMaxY() >= floorPos) {
            ball.setY(floorPos - 2*ball.getRadius());
            ball.reverseVerticalSpeed();
        } else if (ball.getY() <= ceilingPos) {
            ball.setY(ceilingPos);
            ball.reverseVerticalSpeed();
        }

        // check if the ball has hit one of the walls
        if (ball.getMaxX() >= rightWallPos) {
            ball.setX(rightWallPos - 2 * ball.getRadius());
            ball.reverseHorizontalSpeed();
        } else if (ball.getX() <= leftwallPos) {
            ball.setX(leftwallPos);
            ball.reverseHorizontalSpeed();
        }

        // check if the ball has hit the board
        if (ball.intersects(board.getBody())) {
            ball.setY(board.getY() - 2 * ball.getRadius2());
            ball.reverseVerticalSpeed();
        } else if (ball.intersects(board.getLeftEdge())) {
            float angle = getDirectionAfterCollisionWithCircle(ball, board.getLeftEdge());
            ball.setDirection(angle);
        } else if (ball.intersects(board.getRightEdge())) {
            float angle = getDirectionAfterCollisionWithCircle(ball, board.getRightEdge());
            ball.setDirection(angle);
        }
    }

    /**
     * Returns the new direction (measured as an angle between it and the x-axis)
     * at which the ball should move after a collision with another circle,
     * based on the angle they collide.
     * @param ball The ball.
     * @param collideCircle The circle the ball collides with.
     * @return The new direction measured as an angle between it and the x-axis.
     */
    private float getDirectionAfterCollisionWithCircle(Ball ball, Circle collideCircle) {
        float dy = ball.getCenterY()-collideCircle.getCenterY();
        float dx = ball.getCenterX()-collideCircle.getCenterX();
        float angle;
        float straightOut;

        // special cases
        if (dx==0) {
            // collision straight from the bottom
            if (dy > 0) straightOut = (float) ((3 * Math.PI) / 2);
            // collision straight from the top
            else straightOut = (float) ((Math.PI) / 2);
        } else if (dy==0) {
            // collision straight from the right
            if (dx>0) straightOut = 0;
            // collision straight from the left
            else straightOut = (float) Math.PI;
        }

        // more general cases
        else if (dx>0 && dy<0) {
            // collision from the top right
            straightOut = (float) Math.atan(Math.abs(dy/dx));
        } else if (dx<0 && dy<0) {
            // collision from the top left
            straightOut = (float) (Math.PI - Math.atan(Math.abs(dy/dx)));
        } else if (dx<0 && dy>0) {
            // collision from the bottom left
            straightOut = (float) (Math.PI + Math.atan(Math.abs(dy/dx)));
        } else {
            // collision from the bottom right
            straightOut = (float) (2*Math.PI - Math.atan(Math.abs(dy/dx)));
        }
        float diff = reverseDirection(ball.getDirection())-straightOut;
        angle = straightOut-diff;
        if (angle<0) angle = (float) (2*Math.PI+angle);
        return angle;
    }

    /**
     * Reverse a given direction.
     * @param dir The direction.
     * @return The reversed deriction.
     */
    private float reverseDirection(float dir) {
        if (dir < Math.PI) return (float) (dir+Math.PI);
        else return (float) (dir-Math.PI);
    }

    /*public Speed getSpeedAfterCollision2(Ball ball, Board board){
        float Y = (float) ball.getY()/(Math.abs(ball.getX()+ball.getRadius()-(board.getX()+board.getLength()/2)+3));
        float X = ball.getX();
        return new Speed(X,Y);
    }
    */

    /**
     * Returns the speed (and direction) at which the ball should move after a collision
     * with a brick, based on the angle they collide.
     * @param ball The ball.
     * @param brick The brick the ball collides with.
     * @return A Speed object containing the new x- and Y.
     */
    private Direction getSpeedAfterCollisionWithBrick(Ball ball, Brick brick) {
        if (ball.intersects(brick.getSouthLine()) || ball.intersects(brick.getNorthLine())) {
            //Decrease the life of the brick and give the player a point.
            brick.decrementLife(); Points.getInstance().incrementPoints();
            return Direction.VERTICAL;
        } else if (ball.intersects(brick.getEastLine()) || ball.intersects(brick.getWestLine())) {
            //Decrease the life of the brick and give the player a point.
            brick.decrementLife(); Points.getInstance().incrementPoints();
            return Direction.HORIZONTAL;
        }
        return null;
    }
}