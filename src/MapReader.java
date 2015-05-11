import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Reads the input file map and creates bricks out of the text in the file.
 * Created by RobertLorentz on 11/05/15.
 */
public class MapReader{
    private final static String MAP_1 = "res/maps/map1.txt";


public void readMap(int map) {

    ArrayList<Integer> xPositions = new ArrayList<>();
    ArrayList<Integer> yPositions = new ArrayList<>();
    ArrayList<Integer> levels = new ArrayList<>();
    try (BufferedReader file = new BufferedReader(new FileReader(MAP_1));) {
        file.readLine();
        for (String currentLine=file.readLine(); currentLine!=null; currentLine=file.readLine()) {
            int currentxPos = Integer.parseInt(currentLine.split("\\s+")[0]); xPositions.add(currentxPos);
            int currentyPos = Integer.parseInt(currentLine.split("\\s+")[1]); yPositions.add(currentyPos);
            int currentLevel = Integer.parseInt(currentLine.split("\\s+")[2]); levels.add(currentLevel);
        }

    } catch (IOException e) {
        System.err.println("IOException: " + e.getMessage());
    }
}






}

