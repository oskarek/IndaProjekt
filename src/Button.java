import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.gui.GUIContext;
import org.newdawn.slick.gui.MouseOverArea;

import java.awt.*;

/**
 * A button in the game.
 *
 * Created by oskarek on 2015-05-13.
 */
public class Button extends MouseOverArea {
    private TrueTypeFont font;
    private String label;
    private int labelXPos;
    private int labelYPos;

    /**
     * Constructor that creates a button with a given Image as representation.
     * @param container The container that contains the game.
     * @param image The image to use as the button representation.
     * @param x The x position for the top left corner of the button.
     * @param y The y position for the top left corner of the button.
     */
    public Button(GUIContext container, Image image, int x, int y) {
        super(container, image, x, y);
        font = null;
    }

    /**
     * Constructor that creates a button with a given Image as representation,
     * and a label string with a given size.
     * @param container The container that contains the game.
     * @param image The image to use as the button representation.
     * @param x The x position for the top left corner of the button.
     * @param y The y position for the top left corner of the button.
     * @param label The label to use for the button.
     * @param labelSize The size of the label-text.
     */
    public Button(GUIContext container, Image image, int x, int y, String label, int labelSize) {
        super(container, image, x, y);

        Font awtFont = new java.awt.Font("Times New Roman", Font.BOLD, labelSize);
        font = new TrueTypeFont(awtFont, true);
        this.label = label;
        calculateLabelPosition();
    }

    @Override
    public void render(GUIContext container, Graphics g) {
        super.render(container, g);
        if (font != null) font.drawString(labelXPos, labelYPos, label, Color.black);
    }

    /**
     * Set a label for the button.
     * @param label The string to use as a label.
     * @param labelSize The point size for the label.
     */
    public void setLabel(String label, int labelSize) {
        Font awtFont = new Font("Times New Roman", Font.BOLD, labelSize);
        font = new TrueTypeFont(awtFont, true);
        this.label = label;
        calculateLabelPosition();
    }

    /**
     * Calculate the position for the label so it is centered.
     */
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
