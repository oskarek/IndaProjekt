import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
import sun.java2d.pipe.DrawImage;

/**
 * Created by RobertLorentz on 11/05/15.
 */
public class PowerupsScreen extends BasicGameState {
    private Button backButton;
    private LangFileReader langReader;
    @Override
    public int getID() { return 3; }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        createButtons(container);
    }

    public void createButtons(GameContainer container) throws SlickException {
        Image backButtonImage = new Image("res/UIButtons/backbutton.png");
        Image backButtonPressed = new Image("res/UIButtons/backbutton_pressed.png");
        int width = backButtonImage.getWidth();
        int height = backButtonImage.getHeight();
        int yPos = container.getHeight()-height-10;
        int xPos = container.getWidth()-width-10;
        langReader = new LangFileReader();
        String label = langReader.getString(TranslationAreas.BACK_BUTTON,"sv.txt");
        backButton = new Button(container,backButtonImage,xPos,yPos,label,18);

        // darken the back button when it's hovered/pressed
        backButton.setMouseOverColor(Color.lightGray);
        backButton.setMouseDownColor(Color.lightGray);

        // load the pressed button images as "back button pressed"
        backButton.setMouseDownImage(backButtonPressed);
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        g.drawImage(new Image("res/UIButtons/board_half.png"), 50, 50);
        g.drawString(" - Halverar storleken på brädan", 100, 50);
        g.drawImage(new Image("res/UIButtons/board_x2.png"), 50, 100);
        g.drawString(" - Fördubblar storleken på brädan", 220, 100);
        g.drawImage(new Image("res/UIButtons/ball.png"), 50, 150);
        g.drawString(" - Halverar hastigheten på bollen", 75, 150);
        g.drawImage(new Image("res/UIButtons/ball.png"), 50, 200);
        g.drawString(" - Fördubblar hastigheten på bollen", 75, 200);
        backButton.render(container,g);
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        Input input = container.getInput();
        if (backButton.isMouseOver() && input.isMousePressed(Input.MOUSE_LEFT_BUTTON)
                || input.isKeyPressed(Input.KEY_ESCAPE)) {
            game.enterState(0, new FadeOutTransition(), new FadeInTransition());
        }
    }
}
