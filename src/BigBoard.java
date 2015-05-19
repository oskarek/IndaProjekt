import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * Created by oskarek on 2015-05-18.
 */

public class BigBoard extends PowerUp {
    private Board board;
    public BigBoard(int xPosition, int yPosition, Board board) throws SlickException {
        super(xPosition, yPosition);
        super.setImage(new Image("res/UIButtons/powerup_bigboard.png"));
        super.setDuration(500);
        this.board = board;
    }
    public void invoke() {
        board.doubleLength();
    }
    public void reverse() {
        board.normalLength();
    }
}
