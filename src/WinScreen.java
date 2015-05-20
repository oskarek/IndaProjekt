import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/**
 * Screen that is displayed when you win.
 *
 * Created by oskarek on 2015-05-20.
 */
public class WinScreen extends EndScreen {
    @Override
    public int getID() {
        return super.getID();
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        LangFileReader langReader = new LangFileReader();
        String langFile = langReader.getCurrentLanguageFileName();
        String congratulations = langReader.getString(TranslationAreas.CONGRATULATIONS_MESSAGE, langFile);
        super.addMessage(congratulations);
        super.init(container, game);
    }
}
