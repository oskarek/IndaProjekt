import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Reads the input file map and creates bricks out of the text in the file.
 * Created by RobertLorentz on 11/05/15.
 */
public class MapReader{

public MapReader(String file){
    try {
        BufferedReader br = new BufferedReader(new FileReader(file));
    } catch (IOException e) {
        System.err.println("IOException: " + e.getMessage());
    } catch (FileNotFoundException e){

    }


}




}

