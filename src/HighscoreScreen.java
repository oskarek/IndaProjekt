import org.newdawn.slick.*;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
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
    private LangFileReader langReader;
    private Button backButton;
    private ArrayList<String> highscoreList;
    private TrueTypeFont font;
    private TrueTypeFont font2;

    @Override
    public int getID() { return 2; }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        langReader = new LangFileReader();
        // load a default java font
        Font awtFont = new Font("Times New Roman", Font.BOLD, 18);
        font = new TrueTypeFont(awtFont, true);

        createButtons(container);
        HighscoreTool highscoreTool = new HighscoreTool();
        highscoreList = highscoreTool.returnScore();

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

    public void createButtons(GameContainer container) throws SlickException {
        Image backButtonImage = new Image("res/UIButtons/backbutton.png");
        Image backButtonPressed = new Image("res/UIButtons/backbutton_pressed.png");
        int width = backButtonImage.getWidth();
        int height = backButtonImage.getHeight();
        int yPos = container.getHeight()-height-10;
        int xPos = container.getWidth()-width-10;
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
        // render the button
        backButton.render(container, g);

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
        if (backButton.isMouseOver() && input.isMousePressed(Input.MOUSE_LEFT_BUTTON)
                || input.isKeyPressed(Input.KEY_ESCAPE)) {
            game.enterState(0, new FadeOutTransition(), new FadeInTransition());
        }
    }
}
