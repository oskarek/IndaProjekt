import org.newdawn.slick.*;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import javax.swing.*;
import java.awt.*;
import java.awt.Font;
import java.awt.event.ComponentListener;
import java.awt.Component;

/**
 * Created by RobertLorentz on 13/05/15.
 */
public class WinningScreen extends BasicGameState {
    private Button inputButton;
    private LangFileReader langReader;
    private Langs lang;
    private TrueTypeFont font;
    private TextField nameInput;
    private Component component;
    private Cursor textCursor;
    private Cursor defaultCursor;

    @Override
    public int getID() {
        return 4;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        Font awtFont = new Font("Times New Roman", Font.BOLD, 18);
        font = new TrueTypeFont(awtFont, true);
        lang = Langs.SWEDISH; langReader = new LangFileReader();
        createButtons(container);
        textCursor = new Cursor(Cursor.TEXT_CURSOR);
        defaultCursor = Cursor.getDefaultCursor();

        component = new Component() {
            public void setCursor(Cursor cursor) {
                super.setCursor(cursor);
            }
        };




    }
    public void createButtons(GameContainer container) throws SlickException {
        Image inputButtonImage = new Image("res/UIButtons/inputbutton.png");
        Image inputButtonPressedImage = new Image("res/UIButtons/inputbutton_pressed.png");
        int yPos = container.getHeight()/2-inputButtonImage.getHeight()/2;
        int xPos = container.getWidth()/2-inputButtonImage.getWidth()/2;
        String label = langReader.getString(TranslationAreas.INPUT_BUTTON,lang);
        inputButton = new Button(container,inputButtonImage,xPos,yPos,label,font);

        // darken the back button when it's hovered/pressed
        inputButton.setMouseOverColor(Color.lightGray);
        inputButton.setMouseDownColor(Color.lightGray);


        // load the pressed button images as "back button pressed"
        inputButton.setMouseDownImage(inputButtonPressedImage);
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        inputButton.render(container,g);
        //g.drawString("Grattis du har vunnit bögjävel",container.getWidth()/2,container.getHeight()/2);
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
        if(inputButton.isMouseOver()){
            component.setCursor(textCursor);
        }
    }
}


