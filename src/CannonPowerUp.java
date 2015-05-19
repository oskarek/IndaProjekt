import org.newdawn.slick.SlickException;

/**
 * Created by RobertLorentz on 18/05/15.
 */
public class CannonPowerUp extends PowerUp {
    private Board board;
    public CannonPowerUp(int xPosition, int yPosition, Board board) throws SlickException {
        super(xPosition, yPosition);
        super.setDuration(500);
        this.board = board;
    }

    @Override
    public void invoke() throws SlickException {
        board.addCannons();
    }

    @Override
    public void reverse() {
        board.removeCannons();
    }
}
