import org.newdawn.slick.*;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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

    private LangFileReader langReader;
    private ArrayList<Button> buttons;
    private int contWidth, contHeight;

    @Override
    public int getID() {
        return 0;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        langReader = new LangFileReader();
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
        ArrayList<Image> buttonImages = new ArrayList<>();
        ArrayList<Image> pressedButtons = new ArrayList<>();
        buttons = new ArrayList<>();

        // add buttons and pressed buttons images to ArrayLists
        buttonImages.add(new Image("res/UIButtons/startbutton.png"));
        pressedButtons.add(new Image("res/UIButtons/startbutton_pressed.png"));
        buttonImages.add(new Image("res/UIButtons/highscorebutton.png"));
        pressedButtons.add(new Image("res/UIButtons/highscorebutton_pressed.png"));
        buttonImages.add(new Image("res/UIButtons/powerupsbutton.png"));
        pressedButtons.add(new Image("res/UIButtons/powerupsbutton_pressed.png"));
        buttonImages.add(new Image("res/UIButtons/quitbutton.png"));
        pressedButtons.add(new Image("res/UIButtons/quitbutton_pressed.png"));

        // create the buttons
        int lastYBottomPos = 50;
        for (int i = 0; i < buttonImages.size(); i++) {
            Image buttonImage = buttonImages.get(i);

            // dimensions of the button
            int width = buttonImage.getWidth();
            int height = buttonImage.getHeight();

            int xCentered = contWidth/2-width/2;
            Button button = new Button(container,buttonImage,xCentered,lastYBottomPos+30);
            buttons.add(button);

            // darken the buttons when they're hovered/pressed
            button.setMouseOverColor(Color.lightGray);
            button.setMouseDownColor(Color.lightGray);

            // load the pressed button images as "mouseDownImage"
            button.setMouseDownImage(pressedButtons.get(i));

            lastYBottomPos = button.getY()+height;
        }
    }

    /**
     * Add labels to the buttons, based on the current selected language.
     */
    public void addButtonLabels() {
        String currLangFileName = "lang/currentlang.txt";
        String langFile = null;
        try (BufferedReader file = new BufferedReader(new FileReader(currLangFileName))) {
            langFile = file.readLine();
        } catch (FileNotFoundException e) {
            System.err.println("Couldn't find the currentlang file.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (langFile == null) {
            throw new RuntimeException("The currentlang file is empty.");
        }

        for (int i = 0; i < buttons.size(); i++) {
            String label;
            switch (i) {
                case 0: label = langReader.getString(TranslationAreas.START_BUTTON,langFile);
                    break;
                case 1: label = langReader.getString(TranslationAreas.HIGHSCORES_BUTTON,langFile);
                    break;
                case 2: label = langReader.getString(TranslationAreas.POWERUPS_BUTTON,langFile);
                    break;
                case 3: label = langReader.getString(TranslationAreas.QUIT_BUTTON,langFile);
                    break;
                default: label = langReader.getString(TranslationAreas.START_BUTTON,langFile);
            }

            buttons.get(i).setLabel(label,24);
        }

    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        // render all the buttons
        for (Button button : buttons) {
            button.render(container, g);
        }
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        Input input = container.getInput();
        // check if the start button has been pressed, and if it has:
        // switch to the playingField state
        if (buttons.get(START_BUTTON).isMouseOver() && input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
            PlayingField playingField = new PlayingField();
            playingField.setTimer(container);
            game.enterState(1, new FadeOutTransition(), new FadeInTransition());
        }
        // check if the quit button has been pressed, and if it has:
        // terminate the app
        if (buttons.get(QUIT_BUTTON).isMouseOver() && input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
            container.exit();
        }
        // check if the highscore button has been pressed, and if it has:
        // switch to the highscore state
        if (buttons.get(HIGHSCORE_BUTTON).isMouseOver() && input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
            game.enterState(2, new FadeOutTransition(), new FadeInTransition());
        }
        // check if the powerups button has been pressed, and if it has:
        // switch to the powerups state
        if (buttons.get(POWERUPS_BUTTON).isMouseOver() && input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
            game.enterState(3, new FadeOutTransition(), new FadeInTransition());
        }
    }
}
