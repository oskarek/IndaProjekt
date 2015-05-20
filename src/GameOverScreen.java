import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/**
 * Screen that is displayed when you lose.
 *
 * Created by oskarek on 2015-05-20.
 */
public class GameOverScreen extends EndScreen {
    @Override
    public int getID() {
        return 6;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        LangFileReader langReader = new LangFileReader();
        String langFile = langReader.getCurrentLanguageFileName();
        String gameOverMessage = langReader.getString(TranslationAreas.GAMEOVER_MESSAGE, langFile);
        super.addMessage(gameOverMessage);
        super.init(container, game);
    }
}
