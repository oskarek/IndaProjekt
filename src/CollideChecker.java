import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Circle;

import java.lang.reflect.Array;
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
     * updates its direction accordingly.
     * @param ball The ball.
     * @param board The board.
     * @param bricks The bricks.
     */
    public void checkBallCollisions(Ball ball, Board board, ArrayList<Brick> bricks) {
        float currDir = ball.getDirection();
        // check if the ball has hit any of the bricks
        for(Brick brick : bricks){
            boolean hitBrick = false;
            boolean hitCorner = false;
            /*for (Circle corner : brick.getCorners()) {
                if (ball.intersects(corner)) {
                    float angle = getDirectionAfterCollisionWithCircle(ball,corner);
                    ball.setDirection(angle);
                    hitCorner = true;
                    break;
                }
            }*/
            if (ball.intersects(brick.getTopLeftCorner())) {
                float angle = getDirectionAfterCollisionWithCircle(ball,brick.getTopLeftCorner());
                if ((!ball.hasPositiveXDirection()) && angle<reverseVerticalDirection(angle)) ball.reverseVerticalDirection();
                else if (ball.hasPositiveYDirection() && angle>reverseHorizontalDirection(angle)) ball.reverseHorizontalDirection();
                else ball.setDirection(angle);
                hitCorner = true;
            } else if (ball.intersects(brick.getTopRightCorner())) {
                float angle = getDirectionAfterCollisionWithCircle(ball,brick.getTopRightCorner());
                if (ball.hasPositiveXDirection() && angle>reverseVerticalDirection(angle) && angle<Math.PI) ball.reverseVerticalDirection();
                else if (ball.hasPositiveYDirection() && (angle<reverseHorizontalDirection(angle)
                || angle>Math.PI)) ball.reverseHorizontalDirection();
                else ball.setDirection(angle);
                hitCorner = true;
            } else if (ball.intersects(brick.getBottomLeftCorner())) {
                float angle = getDirectionAfterCollisionWithCircle(ball,brick.getBottomLeftCorner());
                if ((!ball.hasPositiveXDirection()) && angle>reverseVerticalDirection(angle)) ball.reverseVerticalDirection();
                else if ((!ball.hasPositiveYDirection()) && angle<reverseHorizontalDirection(angle)) ball.reverseHorizontalDirection();
                else ball.setDirection(angle);
                hitCorner = true;
            } else if (ball.intersects(brick.getBottomRightCorner())) {
                float angle = getDirectionAfterCollisionWithCircle(ball,brick.getBottomRightCorner());
                if (ball.hasPositiveXDirection() && angle<reverseVerticalDirection(angle) && angle>Math.PI) ball.reverseVerticalDirection();
                else if ((!ball.hasPositiveYDirection()) && angle>reverseHorizontalDirection(angle)) ball.reverseHorizontalDirection();
                else ball.setDirection(angle);
                hitCorner = true;
            }
            if (hitCorner) {
                brick.decrementLife(); Points.getInstance().addPoints(10);
                break;
            }
            if (ball.intersects(brick.getNorthLine()) && Math.PI<=currDir
                                                && currDir<=2*Math.PI) {
                ball.reverseVerticalDirection();
                hitBrick = true;
            } else if (ball.intersects(brick.getSouthLine()) && 0<=currDir
                                                && currDir<=Math.PI) {
                ball.reverseVerticalDirection();
                hitBrick = true;
            } else if (ball.intersects(brick.getWestLine()) && ((0<=currDir
                    && currDir<=(Math.PI)/2) || ((3*Math.PI)/2<=currDir
                    && currDir<=2*Math.PI))) {
                ball.reverseHorizontalDirection();
                hitBrick = true;
            } else if (ball.intersects(brick.getEastLine()) && (Math.PI)/2<=currDir
                    && currDir<=(3*Math.PI)/2) {
                ball.reverseHorizontalDirection();
                hitBrick = true;
            }
            if (hitBrick) {
                brick.decrementLife(); Points.getInstance().addPoints(10);
                break;
            }
        }

        // check if the ball has hit the floor or celing
        if (ball.getMaxY() >= floorPos) {
            ball.setY(floorPos - 2*ball.getRadius());
            ball.reverseVerticalDirection();
        } else if (ball.getY() <= ceilingPos) {
            ball.setY(ceilingPos);
            ball.reverseVerticalDirection();
        }

        // check if the ball has hit one of the walls
        if (ball.getMaxX() >= rightWallPos) {
            ball.setX(rightWallPos - 2 * ball.getRadius());
            ball.reverseHorizontalDirection();
        } else if (ball.getX() <= leftwallPos) {
            ball.setX(leftwallPos);
            ball.reverseHorizontalDirection();
        }

        // check if the ball has hit the board
        if (currDir>((Math.PI)/6) && currDir<((3*Math.PI)/6)) return;
        if (ball.intersects(board.getBody()) && currDir>Math.PI) {
            ball.reverseVerticalDirection();
        } else if (ball.intersects(board.getLeftEdge())) {
            float angle = getDirectionAfterCollisionWithCircle(ball, board.getLeftEdge());
            if (angle<((Math.PI)/2)) ball.reverseVerticalDirection();
            else ball.setDirection(angle);
        } else if (ball.intersects(board.getRightEdge())) {
            float angle = getDirectionAfterCollisionWithCircle(ball, board.getRightEdge());
            if (angle>((Math.PI)/2) && angle<Math.PI) ball.reverseVerticalDirection();
            else ball.setDirection(angle);
        }
    }

    public boolean checkPowerUpCollision(PowerUp powerUp, Board board){
        if(powerUp.getHitBox().intersects(board.getBody())){
            return true;
        }
        return false;
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
        if (angle<0) angle = (float) ((2*Math.PI)+angle);
        else if (angle>=2*Math.PI) angle = (float) (angle-(2*Math.PI));
        return angle;
    }

    public boolean checkProjectileCollision(ArrayList<Brick> bricks, Projectile projectile){
        for(Brick brick : bricks) {
            if (projectile.intersects(brick.getSouthLine()) || projectile.intersects(brick.getBottomLeftCorner())
                    || projectile.intersects(brick.getBottomRightCorner())) {
                brick.decrementLife(); Points.getInstance().addPoints(10);
                return true;
            }
        }
        return false;
    }

    public boolean checkLaserCollision(ArrayList<Brick> bricks, Laser laser){
        for(Brick brick : bricks) {
            if (laser.intersects(brick.getSouthLine()) || laser.intersects(brick.getBottomLeftCorner())
                    || laser.intersects(brick.getBottomRightCorner())) {
                brick.decrementLife(); Points.getInstance().addPoints(10);
                return true;
            }
        }
        return false;
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

    /**
     * Get the reverse of the horizontal component of the direction.
     */
    public float reverseHorizontalDirection(float angle) {
        if (angle <= Math.PI) return (float) (Math.PI-angle);
        else return (float) (3*Math.PI-angle);
    }

    /**
     * Get the reverse of the vertical component of a direction.
     */
    public float reverseVerticalDirection(float angle) {
        return (float) (2*Math.PI-angle);
    }

    /*public Speed getSpeedAfterCollision2(Ball ball, Board board){
        float Y = (float) ball.getY()/(Math.abs(ball.getX()+ball.getRadius()-(board.getX()+board.getLength()/2)+3));
        float X = ball.getX();
        return new Speed(X,Y);
    }
    */
}