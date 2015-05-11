import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * A class with various tools for highscores.
 *
 * Created by oskarek on 2015-05-11.
 */
public class HighscoreTool {
    private static final String FILEPATH = "res/highscores/highscores.txt";


    public boolean isHighscore(int score) {
        try(BufferedReader file = new BufferedReader(new FileReader(FILEPATH));) {
            for (String currentLine=file.readLine(); currentLine!=null; currentLine=file.readLine()) {
                int currentScore = Integer.parseInt(currentLine.split("\\s+")[1]);
                if (currentScore <= score) {
                    return true;
                }
            }
        } catch (IOException e) {
            System.err.println("Failed to read the highscore file.");
        }

        return false;
    }

    public void insertHighscore(int score) {

    }
}
