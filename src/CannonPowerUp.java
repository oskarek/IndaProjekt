import org.newdawn.slick.SlickException;

/**
 * Created by RobertLorentz on 18/05/15.
 */
public class CannonPowerUp extends PowerUp {
    private Board board;
    public CannonPowerUp(int xPosition, int yPosition, Board board) throws SlickException {
        super(xPosition, yPosition);
        this.board = board;
    }

    @Override
    public void invoke() {
        board.addCannons();
    }

    @Override
    public void reverse() {
        board.removeCannons();
    }
}
