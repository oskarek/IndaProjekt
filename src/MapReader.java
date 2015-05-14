import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Reads the input file map and creates bricks out of the text in the file.
 * Created by RobertLorentz on 11/05/15.
 */
public class MapReader{
    private HashMap<Integer, String> mapMap;

    public MapReader(){
        mapMap = new HashMap<>();
        mapMap.put(1,"res/maps/map1.txt");
        //mapMap.put(2,"res/maps/map2.txt");
    }


    public ArrayList<ArrayList<Integer>> readMap(int mapNum) {
        //get the map according to the number given in the parameter
        String File = mapMap.get(mapNum);

        //assign all values in the text file into arrays which are then given into brickinfo
        ArrayList<ArrayList<Integer>> brickInfo = new ArrayList<>();
        ArrayList<Integer> xPositions = new ArrayList<>();
        ArrayList<Integer> yPositions = new ArrayList<>();
        ArrayList<Integer> levels = new ArrayList<>();
        try (BufferedReader file = new BufferedReader(new FileReader(File));) {
            file.readLine();
            for (String currentLine=file.readLine(); currentLine!=null; currentLine=file.readLine()) {
                int currentxPos = Integer.parseInt(currentLine.split("\\s+")[0]); xPositions.add(currentxPos);
                int currentyPos = Integer.parseInt(currentLine.split("\\s+")[1]); yPositions.add(currentyPos);
                int currentLevel = Integer.parseInt(currentLine.split("\\s+")[2]); levels.add(currentLevel);
            }
            brickInfo.add(xPositions); brickInfo.add(yPositions); brickInfo.add(levels);

        } catch (IOException e) {
            System.err.println("IOException: " + e.getMessage());

        }
        return brickInfo;
    }







}