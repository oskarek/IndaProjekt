import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
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

    public static void main(String[] args) throws SlickException, LWJGLException {
        AppGameContainer app = new AppGameContainer(new MainGame("Test app"));

        DisplayMode[] modes = Display.getAvailableDisplayModes();
        for (DisplayMode mode : modes) {
            System.out.println();
            System.out.print("Height: ");
            System.out.println(mode.getHeight());
            System.out.print("Width: ");
            System.out.println(mode.getWidth());
            System.out.print("Fullscreen capable: ");
            System.out.println(mode.isFullscreenCapable());
        }

        app.setDisplayMode(1000, 600, false);

        app.start();
    }
}
