import javafx.print.PageLayout;
import org.newdawn.slick.*;
import org.newdawn.slick.openal.StreamSound;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by oskarek on 2015-05-15.
 */
public class LanguageSelectionScreen extends BasicGameState {
    private static final int SWEDISH_FLAG = 0;
    private static final int USA_FLAG = 1;
    private static final int SPANISH_FLAG = 2;

    private ArrayList<Button> flagButtons;
    private String langSelection;

    @Override
    public int getID() {
        return 5;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {

        ArrayList<Image> buttonImages = new ArrayList<>();
        ArrayList<Image> pressedButtons = new ArrayList<>();
        flagButtons = new ArrayList<>();

        // add buttons and pressed buttons images to ArrayLists
        buttonImages.add(new Image("res/UIButtons/sv_flag.png"));
        pressedButtons.add(new Image("res/UIButtons/sv_flag_pressed.png"));
        buttonImages.add(new Image("res/UIButtons/usa_flag.png"));
        pressedButtons.add(new Image("res/UIButtons/usa_flag_pressed.png"));
        buttonImages.add(new Image("res/UIButtons/sp_flag.png"));
        pressedButtons.add(new Image("res/UIButtons/sp_flag_pressed.png"));

        // create the buttons
        int lastxMaxPos = 60;
        for (int i = 0; i < buttonImages.size(); i++) {
            Image buttonImage = buttonImages.get(i);

            // dimensions of the button
            int width = buttonImage.getWidth();
            int height = buttonImage.getHeight();

            int yCentered = container.getHeight()/2-height/2;
            Button button = new Button(container,buttonImage,lastxMaxPos+30,yCentered);
            flagButtons.add(button);

            // darken the buttons when they're hovered/pressed
            button.setMouseOverColor(Color.lightGray);
            button.setMouseDownColor(Color.lightGray);

            // load the pressed button images as "mouseDownImage"
            button.setMouseDownImage(pressedButtons.get(i));

            lastxMaxPos = button.getX()+width;
        }
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        for (Button button : flagButtons) {
            button.render(container,g);
        }
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        Input input = container.getInput();

        if (langSelection!=null) {
            MainMenu mainMenu = (MainMenu) game.getState(0);
            PlayingField playingField = (PlayingField) game.getState(1);
            mainMenu.addButtonLabels();
            playingField.initPauseString();
            game.enterState(mainMenu.getID(), new FadeOutTransition(), new FadeInTransition());
        }

        if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
            if (flagButtons.get(SWEDISH_FLAG).isMouseOver()) {
                langSelection = "sv.txt";
            } else if (flagButtons.get(USA_FLAG).isMouseOver()) {
                langSelection = "en.txt";
            } else if (flagButtons.get(SPANISH_FLAG).isMouseOver()) {
                langSelection = "sp.txt";
            } else return;

            try (BufferedWriter writer = new BufferedWriter(new FileWriter("lang/currentlang.txt"))) {
                writer.write(langSelection);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
