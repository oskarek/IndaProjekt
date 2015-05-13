import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.gui.GUIContext;
import org.newdawn.slick.gui.MouseOverArea;

/**
 * Created by oskarek on 2015-05-13.
 */
public class Button extends MouseOverArea {
    private TrueTypeFont font;
    private String label;
    private int labelXPos;
    private int labelYPos;

    public Button(GUIContext container, Image image, int x, int y, String label, TrueTypeFont font) {
        super(container, image, x, y);
        this.font = font;
        this.label = label;
        calculateLabelPosition();
    }

    @Override
    public void render(GUIContext container, Graphics g) {
        super.render(container, g);
        font.drawString(labelXPos, labelYPos, label, Color.black);
    }

    private void calculateLabelPosition() {
        int buttonXPos = super.getX();
        int buttonYPos = super.getY();
        int buttonWidth = super.getWidth();
        int buttonHeight = super.getHeight();
        int labelWidth = font.getWidth(label);
        int labelHeight = font.getHeight();
        labelXPos = buttonXPos+(buttonWidth/2)-(labelWidth/2);
        labelYPos = buttonYPos+(buttonHeight/2)-(labelHeight/2);
    }
}
