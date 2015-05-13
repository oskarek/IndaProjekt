import org.newdawn.slick.*;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.gui.ComponentListener;

import javax.swing.*;
import java.awt.*;
import java.awt.Font;
import java.awt.event.ComponentEvent;
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
    private Cursor textCursor;
    private Cursor defaultCursor;
    private TextField tx;

    public WinningScreen(){

    }

    @Override
    public int getID() {
        return 4;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        Font awtFont = new Font("Times New Roman", Font.BOLD, 18);
        font = new TrueTypeFont(awtFont, true);
        // textCursor = new Cursor(Cursor.TEXT_CURSOR); defaultCursor = Cursor.getDefaultCursor();

        tx = new TextField(container, font, container.getWidth()/2 - 175, container.getHeight()/2 - 30, 350, 30);
        tx.setBackgroundColor(Color.white);
        tx.setMaxLength(20);
        tx.setTextColor(Color.black);
        tx.setText("Skriv in ditt namn här");  //Gör för alla språk!!
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        tx.render(container, g);
        g.drawString("Grattis du har vunnit spelet, poäng: " + Points.getInstance().getPoints()
                , container.getWidth()/2-120, container.getHeight()/2-80);
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
                System.out.println("Please write your name");
                return;
            }
            tx.deactivate();
            HighscoreTool hl = new HighscoreTool();
            hl.insertScore(Points.getInstance().getPoints(),name);
            game.enterState(2, new FadeOutTransition(), new FadeInTransition());
        }
    }

}


