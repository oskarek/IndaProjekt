import org.newdawn.slick.*;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import java.awt.Font;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by RobertLorentz on 11/05/15.
 */
public class PowerupsScreen extends BasicGameState {
    private Button backButton;
    private LangFileReader langReader;
    private String smallBoardString, bigBoardString, fastBallstring, slowBallString,
            bigBallString, laserString, cannonString;
    private TrueTypeFont font;

    @Override
    public int getID() { return 3; }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        Font awtFont = new Font("Times New Roman", java.awt.Font.BOLD, 18);
        font = new TrueTypeFont(awtFont, true);
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
        g.drawImage(new Image("res/UIButtons/powerup_smallboard.png"), 50, 50);
        font.drawString(110, 65, " - " + smallBoardString);
        g.drawImage(new Image("res/UIButtons/powerup_bigboard.png"), 50, 125);
        font.drawString(110, 140, " - " + bigBoardString);
        g.drawImage(new Image("res/UIButtons/powerup_slowball.png"), 50, 200);
        font.drawString(110, 215, " - " + slowBallString);
        g.drawImage(new Image("res/UIButtons/powerup_fastball.png"), 50, 275);
        font.drawString(110, 290, " - " + fastBallstring);
        g.drawImage(new Image("res/UIButtons/powerup_bigball.png"), 50, 350);
        font.drawString(110, 365, " - " + bigBallString);
        g.drawImage(new Image("res/UIButtons/powerup_laser.png"), 50, 425);
        font.drawString(110, 440, " - " + laserString);
        g.drawImage(new Image("res/UIButtons/powerup_cannon.png"), 50, 500);
        font.drawString(110, 515," - " + cannonString);

        backButton.render(container, g);
    }

    /**
     * Add the text to this state, depending on the current language selection.
     */
    public void addText() {
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
        addLabels(langFile);
        addDescriptions(langFile);
    }

    private void addDescriptions(String langFile) {
        smallBoardString = langReader.getString(TranslationAreas.SMALLBOARD_DESCRIPTION,langFile);
        bigBoardString = langReader.getString(TranslationAreas.BIGBOARD_DESCRIPTION,langFile);
        fastBallstring = langReader.getString(TranslationAreas.FASTBALL_DESCRIPTION,langFile);
        slowBallString = langReader.getString(TranslationAreas.SLOWBALL_DESCRIPTION,langFile);
        bigBallString = langReader.getString(TranslationAreas.BIGBALL_DESCRIPTION,langFile);
        laserString = langReader.getString(TranslationAreas.LASER_DESCRIPTION,langFile);
        cannonString = langReader.getString(TranslationAreas.CANNON_DESCRIPTION,langFile);
    }

    private void addLabels(String langFile) {
        String label = langReader.getString(TranslationAreas.BACK_BUTTON,langFile);
        backButton.setLabel(label,18);
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
