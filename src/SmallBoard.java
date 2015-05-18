import org.newdawn.slick.SlickException;

/**
 * Created by oskarek on 2015-05-18.
 */
public class SmallBoard extends PowerUp {
    private Board board;
    public SmallBoard(int xPosition, int yPosition, Board board) throws SlickException {
        super(xPosition, yPosition);
        this.board = board;
    }
    public void invoke() {
        board.halfLength();
    }
    public void reverse() {
        board.normalLength();
    }
}
