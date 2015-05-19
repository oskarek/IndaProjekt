import org.newdawn.slick.SlickException;

/**
 * Created by RobertLorentz on 19/05/15.
 */
public class LaserPowerUp extends PowerUp{
    private Board board;
    public LaserPowerUp(int x, int y, Board board) throws SlickException {
        super(x,y);
        super.setDuration(50);
        this.board = board;
    }

    @Override
    public void invoke() throws SlickException {
        board.addLaserCannon();
    }

    @Override
    public void reverse() {
        board.removeLaserCannon();
    }
}
