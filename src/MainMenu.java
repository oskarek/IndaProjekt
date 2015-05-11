import org.newdawn.slick.*;
import org.newdawn.slick.gui.MouseOverArea;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import java.util.ArrayList;

/**
 * The main menu, which you are greeted with when you open the game.
 *
 * Created by oskarek on 2015-05-10.
 */
public class MainMenu extends BasicGameState {
    private static final int START_BUTTON = 0;
    private static final int HIGHSCORE_BUTTON = 1;
    private static final int POWERUPS_BUTTON = 2;
    private static final int QUIT_BUTTON = 3;

    private ArrayList<MouseOverArea> buttonAreas;
    private int contWidth, contHeight;

    @Override
    public int getID() {
        return 0;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        // dimensions of the container
        contHeight = container.getHeight();
        contWidth = container.getWidth();

        createButtons(container);
    }

    /**
     * Create the buttons for the menu.
     * @param container The container that hold the game.
     * @throws SlickException if the button image files can't be found.
     */
    private void createButtons(GameContainer container) throws SlickException {
        ArrayList<Image> buttons = new ArrayList<>();
        ArrayList<Image> pressedButtons = new ArrayList<>();
        buttonAreas = new ArrayList<>();

        // add buttons and pressed buttons images to ArrayLists
        buttons.add(new Image("res/UIButtons/startbutton.png"));
        pressedButtons.add(new Image("res/UIButtons/startbutton_pressed.png"));
        buttons.add(new Image("res/UIButtons/highscorebutton.png"));
        pressedButtons.add(new Image("res/UIButtons/highscorebutton_pressed.png"));
        buttons.add(new Image("res/UIButtons/powerupsbutton.png"));
        pressedButtons.add(new Image("res/UIButtons/powerupsbutton_pressed.png"));
        buttons.add(new Image("res/UIButtons/quitbutton.png"));
        pressedButtons.add(new Image("res/UIButtons/quitbutton_pressed.png"));

        // turn the images into buttons
        int lastYBottomPos = 50;
        for (int i = 0; i < buttons.size(); i++) {
            Image button = buttons.get(i);

            // dimensions of the button
            int width = button.getWidth();
            int height = button.getHeight();

            int xCentered = contWidth/2-width/2;
            MouseOverArea buttonArea = new MouseOverArea(container,button,xCentered,lastYBottomPos+30);
            buttonAreas.add(buttonArea);

            // darken the buttons when they're hovered/pressed
            buttonArea.setMouseOverColor(Color.lightGray);
            buttonArea.setMouseDownColor(Color.lightGray);

            // load the pressed button images as "mouseDownImage"
            buttonArea.setMouseDownImage(pressedButtons.get(i));

            lastYBottomPos += (30+height);
        }
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        // render all the buttons
        for (MouseOverArea buttonArea : buttonAreas) {
            buttonArea.render(container,g);
        }
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        Input input = container.getInput();
        // check if the start button has been pressed, and if it has:
        // switch to the playingField state
        if (buttonAreas.get(START_BUTTON).isMouseOver() && input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
            game.enterState(1, new FadeOutTransition(), new FadeInTransition());
        }
        // check if the quit button has been pressed, and if it has:
        // terminate the app
        if (buttonAreas.get(QUIT_BUTTON).isMouseOver() && input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
            container.exit();
        }

        if (buttonAreas.get(HIGHSCORE_BUTTON).isMouseOver() && input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
            game.enterState(2, new FadeOutTransition(), new FadeInTransition());
        }

        if (buttonAreas.get(POWERUPS_BUTTON).isMouseOver() && input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
            game.enterState(3, new FadeOutTransition(), new FadeInTransition());
        }
    }
}
