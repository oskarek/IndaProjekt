import org.newdawn.slick.*;
import org.newdawn.slick.gui.MouseOverArea;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import java.util.ArrayList;

/**
 * Game state with highscore screen.
 *
 * Created by RobertLorentz on 11/05/15.
 */
public class HighscoreScreen extends BasicGameState {
    private MouseOverArea backButtonArea;

    @Override
    public int getID() { return 2; }

    public void createButtons(GameContainer container) throws SlickException {
        Image backButton = new Image("res/UIButtons/backbutton.png");
        Image backButtonPressed = new Image("res/UIButtons/backbutton_pressed.png");
        int width = backButton.getWidth();
        int height = backButton.getHeight();
        int yPos = container.getHeight()-height-10;
        int xPos = container.getWidth()-width-10;
        backButtonArea = new MouseOverArea(container,backButton,xPos,yPos);

        // darken the back button when it's hovered/pressed
        backButtonArea.setMouseOverColor(Color.lightGray);
        backButtonArea.setMouseDownColor(Color.lightGray);

        // load the pressed button images as "back button pressed"
        backButtonArea.setMouseDownImage(backButtonPressed);

    }
    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        createButtons(container);
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        // render the button
        backButtonArea.render(container,g);
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        Input input = container.getInput();

        if (backButtonArea.isMouseOver() && input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
            game.enterState(0, new FadeOutTransition(), new FadeInTransition());
        }
        if (input.isKeyPressed(Input.KEY_ESCAPE)) {
            game.enterState(0, new FadeOutTransition(), new FadeInTransition());
        }
    }
}
