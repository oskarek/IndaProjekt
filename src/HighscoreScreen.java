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

import java.io.*;
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
    private boolean newScoreAdded;
    private String newScore;
    private TrueTypeFont font2;

    @Override
    public int getID() { return 2; }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        langReader = new LangFileReader();
        // load a default java font
        Font awtFont = new Font("Times New Roman", Font.BOLD, 18);
        font = new TrueTypeFont(awtFont, true);
        newScoreAdded = false;
        updateHighscoreList();
        createButtons(container);


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
    public void updateHighscoreList(){
        HighscoreTool highscoreTool = new HighscoreTool();
        highscoreList = highscoreTool.returnScore();
    }

    public void createButtons(GameContainer container) throws SlickException {
        Image backButtonImage = new Image("res/UIElements/backbutton.png");
        Image backButtonPressed = new Image("res/UIElements/backbutton_pressed.png");
        int width = backButtonImage.getWidth();
        int height = backButtonImage.getHeight();
        int yPos = container.getHeight()-height-10;
        int xPos = container.getWidth()-width-10;
        String label = langReader.getString(TranslationAreas.BACK_BUTTON,"sv.txt");
        backButton = new Button(container,backButtonImage,xPos,yPos);

        // darken the back button when it's hovered/pressed
        backButton.setMouseOverColor(Color.lightGray);
        backButton.setMouseDownColor(Color.lightGray);

        // load the pressed button images as "back button pressed"
        backButton.setMouseDownImage(backButtonPressed);
    }

    public void addLabels() {
        String currLangFileName = "lang/currentlang.txt";
        String langFile = langReader.getCurrentLanguageFileName();

        String label = langReader.getString(TranslationAreas.BACK_BUTTON,langFile);
        backButton.setLabel(label,18);
    }

    public void newScoreAdded(int score, String name) {
        newScoreAdded = true;
        newScore = score + " " + name;
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        // render the button
        backButton.render(container, g);

        g.drawImage(new Image("res/UIElements/star.png"),120, -100);
        int x = 420; int y = 130;
        boolean newScoreWritten = false;
        for (int i = 0; i < highscoreList.size();i++) {
            String currentScore = highscoreList.get(i);
            Color color;
            if (currentScore.equals(newScore) && (!newScoreWritten)) {
                color = Color.red;
                newScoreWritten = true;
            } else {
                color = Color.black;
            }
            font.drawString(x,y,i+1 + ": " + currentScore,color);
            y+=30;
        }
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        Input input = container.getInput();
        if (backButton.isMouseOver() && input.isMousePressed(Input.MOUSE_LEFT_BUTTON)
                || input.isKeyPressed(Input.KEY_ESCAPE)) {
            newScoreAdded = false;
            newScore = null;
            game.enterState(0, new FadeOutTransition(), new FadeInTransition());
        }
    }
}
