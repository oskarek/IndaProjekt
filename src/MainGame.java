import org.lwjgl.opengl.Display;
import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;

/**
 * Created by oskarek on 2015-05-10.
 */
public class MainGame extends StateBasedGame {
    /**
     * Create a new basic game
     *
     * @param title The title for the game
     */
    public MainGame(String title) {
        super(title);
    }

    @Override
    public void initStatesList(GameContainer container) throws SlickException {
        addState(new MainMenu());
        addState(new PlayingField());
    }

    public static void main(String[] args) throws SlickException {
        AppGameContainer app = new AppGameContainer(new MainGame("Test app"));

        app.setDisplayMode(1440, 900, false);

        app.start();
    }
}
