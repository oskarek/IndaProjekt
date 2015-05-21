import org.newdawn.slick.*;
import org.newdawn.slick.Font;
import org.newdawn.slick.state.StateBasedGame;

import java.awt.*;

/**
 * The main class that initiates the game.
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
        addState(new LanguageSelectionScreen());
        addState(new MainMenu());
        addState(new PlayingField());
        addState(new HighscoreScreen());
        addState(new PowerupsScreen());
        addState(new EndScreen());
    }

    public static void main(String[] args) throws SlickException {
        AppGameContainer app = new AppGameContainer(new MainGame("Brick Breaker"));

        app.setVSync(true);
        app.setTargetFrameRate(100);
        app.setDisplayMode(1000, 600, false);

        app.start();

        java.awt.Font awtFont = new java.awt.Font("Times New Roman", java.awt.Font.BOLD, 20);
        Font font = new TrueTypeFont(awtFont, true);
        app.setDefaultFont(font);
    }
}
