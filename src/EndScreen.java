import org.newdawn.slick.*;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
import org.newdawn.slick.gui.TextField;

import java.awt.Font;

/**
 * Created by RobertLorentz on 13/05/15.
 */
public abstract class EndScreen extends BasicGameState {
    private TrueTypeFont font;
    private TextField tx;
    private String message, typeName, typeNamePrompt;

    @Override
    public int getID() {
        return 4;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        Font awtFont = new Font("Times New Roman", Font.BOLD, 18);
        font = new TrueTypeFont(awtFont, true);

        LangFileReader langReader = new LangFileReader();
        String langFile = langReader.getCurrentLanguageFileName();
        typeName = langReader.getString(TranslationAreas.ENTERNAME_MESSAGE, langFile);
        typeNamePrompt = langReader.getString(TranslationAreas.ENTERNAME_PROMPT, langFile);
        int txWidth = 350;
        int txHeight = 30;
        int txX = container.getWidth()/2-txWidth/2;
        int txY = container.getHeight()/2-txHeight;
        tx = new TextField(container, font, txX, txY, txWidth, txHeight);
        tx.setBackgroundColor(Color.white);
        tx.setMaxLength(20);
        tx.setTextColor(Color.black);
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        tx.render(container, g);
        Font awtFont = new Font("Times New Roman", Font.BOLD, 22);
        TrueTypeFont newFont = new TrueTypeFont(awtFont, true);
        int messageWidth = newFont.getWidth(message);
        int typeNameWidth = newFont.getWidth(typeName);
        font.drawString(container.getWidth()/2 - messageWidth/2, container.getHeight()/2 - 90,
                message + " " + Points.getInstance().getPoints());
        font.drawString(container.getWidth()/2-typeNameWidth/2,container.getHeight()/2-60, typeName);
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        Input input = container.getInput();

        if (input.isKeyPressed(Input.KEY_ESCAPE)) {
            game.enterState(0, new FadeOutTransition(), new FadeInTransition());
        }
        if (input.isKeyPressed(Input.KEY_ESCAPE)) {
            container.exit();
        }
        if(input.isKeyPressed(Input.KEY_ENTER)){
            String name = tx.getText();
            if(name == null){
                System.out.println(typeNamePrompt);
                return;
            }
            tx.deactivate();
            HighscoreTool hl = new HighscoreTool();
            hl.insertScore(Points.getInstance().getPoints(), name);
            HighscoreScreen highscoreScreen = (HighscoreScreen) game.getState(2);
            PlayingField playingField = (PlayingField) game.getState(1);
            highscoreScreen.newScoreAdded(Points.getInstance().getPoints(), name);
            highscoreScreen.updateHighscoreList();
            Points.getInstance().emptyPoints();
            playingField.init(container,game);
            game.enterState(2, new FadeOutTransition(), new FadeInTransition());
        }
    }

    protected void addMessage(String message) {
        this.message = message;
    }
}


