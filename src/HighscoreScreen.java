import org.newdawn.slick.*;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.gui.MouseOverArea;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
import java.awt.Font;
import org.newdawn.slick.util.ResourceLoader;
import java.io.InputStream;
import java.util.ArrayList;


/**
 * Game state with highscore screen.
 *
 * Created by RobertLorentz on 11/05/15.
 */
public class HighscoreScreen extends BasicGameState {
    private MouseOverArea backButtonArea;
    private ArrayList<String> highscoreList;
    private HighscoreTool highscoreTool;
    private TrueTypeFont font;
    private TrueTypeFont font2;

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
        highscoreTool = new HighscoreTool();
        highscoreList = highscoreTool.returnScore();

        // load a default java font
        Font awtFont = new Font("Times New Roman", Font.BOLD, 24);
        font = new TrueTypeFont(awtFont, true);

        // load font from a .ttf file
        try {
            InputStream inputStream = ResourceLoader.getResourceAsStream("res/fonts/caricature.ttf");

            Font awtFont2 = Font.createFont(Font.TRUETYPE_FONT, inputStream);
            awtFont2 = awtFont2.deriveFont(24f); // set font size
            font2 = new TrueTypeFont(awtFont2, true);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        // render the button
        backButtonArea.render(container,g);

        g.drawImage(new Image("res/UIButtons/star.png"),120, -100);
        int x = 420; int y = 130;
        for (int i = 0; i < highscoreList.size();i++) {
            String currentScore = highscoreList.get(i);
            font.drawString(x,y,i+1 + ": " + currentScore,Color.black);
            y+=30;
        }
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
