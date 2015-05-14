import org.newdawn.slick.Graphics;

/**
 * Interface for all items that appear on the playing field of the game.
 *
 * Created by oskarek on 2015-05-07.
 */
public interface PlayingFieldItem {
    /**
     * Draw the item on the screen.
     * @param g The Graphics object that performs the drawing.
     */
    void draw(Graphics g);
}
