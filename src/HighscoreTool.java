import java.io.*;
import java.util.ArrayList;

/**
 * A class with various tools for highscores.
 *
 * Created by oskarek on 2015-05-11.
 */
public class HighscoreTool {
    private static final String FILENAME = "res/highscores/highscores.txt";
    private static final String FILEPATH = "res/highscores/";


    public void insertScore(int score, String name) {
        ArrayList<String> entries = createNewOrder(score, name);
        updateFile(entries);
    }

    private ArrayList<String> createNewOrder(int score, String name) {
        ArrayList<String> newOrder = new ArrayList<>();
        try(BufferedReader file = new BufferedReader(new FileReader(FILENAME))) {
            for (String currentLine=file.readLine(); currentLine!=null; currentLine=file.readLine()) {
                int currentScore = Integer.parseInt(currentLine.split("\\s+")[0]);
                if (currentScore <= score) {
                    newOrder.add(score + " " + name);
                }
                newOrder.add(currentLine);
            }
            if (newOrder.isEmpty()) {
                newOrder.add(score + " " + name);
            }
            if (newOrder.size()>10) {
                newOrder.remove(10);
            }
        } catch (IOException e) {
            System.err.println("Failed to read the highscore file.");
        }
        return newOrder;
    }

    private void updateFile(ArrayList<String> entries) {
        System.out.println(entries.size());
        try(FileWriter writer = new FileWriter(FILENAME)) {
            for (int i = 0; i < entries.size(); i++) {
                String entry = entries.get(i);
                writer.write(entry);
                if (i != entries.size()-1) {
                    writer.write("\n");
                }
            }
        } catch (IOException e) {
            System.err.println("Failed to write the highscore file.");
        }
    }
}
