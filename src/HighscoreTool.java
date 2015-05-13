import java.io.*;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * A class with various tools for highscores.
 *
 * Created by oskarek on 2015-05-11.
 */
public class HighscoreTool {
    private static final String FILENAME = "res/highscores/highscores.txt";
    private static final String FILENAME_TEMP = "res/highscores/highscores_temp.txt";

    public ArrayList<String> returnScore() {
        ArrayList<String> highscoreList = new ArrayList<>();
        try(BufferedReader file = new BufferedReader(new FileReader(FILENAME))) {
            for(String currentLine=file.readLine();currentLine!=null;currentLine=file.readLine()) {
                highscoreList.add(currentLine);
            }
        } catch(IOException e){
            System.err.println("Failed to read the highscore file.");
        }
        return highscoreList;
    }

    public void insertScore(int score, String name) {
        try (
                BufferedReader file = new BufferedReader(new FileReader(FILENAME));
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
                        new FileOutputStream(FILENAME_TEMP), "utf-8"));
        ){
            int writtenLines = 0;
            boolean newScoreAdded = false;
            String currentLine = file.readLine();
            while (currentLine!=null) {
                int currentScore = Integer.parseInt(currentLine.split("\\s+")[0]);
                if (currentScore<=score && !newScoreAdded) {
                    writer.write(score + " " + name);
                    newScoreAdded = true;
                    writtenLines++;
                    if (writtenLines==10) break;
                    writer.newLine();
                }
                writer.write(currentLine);
                writtenLines++;
                currentLine = file.readLine();
                if (currentLine==null || writtenLines==10) break;
                writer.newLine();
            }
            if (writtenLines<10 && !newScoreAdded) {
                if (writtenLines!=0) writer.newLine();
                writer.write(score + " " + name);
            }
            Files.delete(Paths.get(FILENAME));
            Files.move(Paths.get(FILENAME_TEMP), Paths.get(FILENAME));
        } catch (FileNotFoundException e) {
            System.err.println("Failed to read the highscore file");
        } catch (IOException e) {
            System.err.println("Failed to create the highscore_temp file");
        }
    }
}
