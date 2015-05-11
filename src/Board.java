/**
 * SKELETON CODE
 * The board that you control in the game.
 *
 * Created by oskarek on 2015-05-07.
 */
public class Board extends PlayingFieldItem {
    private PowerUp currentPowerUp;
    private int length;

    public int getLength(){
        return length;
    }

    public void setLength(int length){
        this.length = length;
    }
}
