/**
 * Created by RobertLorentz on 13/05/15.
 */
public class Points {
private int points;

    private static Points instance = null;

    public static Points getInstance(){
        if(instance == null){
            instance = new Points();
        }
        return instance;
    }
    public int getPoints(){
        return points;
    }
    public void incrementPoints(){
        points++;
    }
    public void addPoints(int points){
        this.points+=points;
    }
    public void decrementPoints(){
        points--;
    }
}
