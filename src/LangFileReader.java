import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by oskarek on 2015-05-13.
 */
public class LangFileReader {
    private static final String LANGPATH = "lang/";

    public String getString(TranslationAreas area, Langs lang) {
        String fileName;
        switch (lang) {
            case ENGLISH: fileName = "en.txt";
                break;
            case SWEDISH: fileName = "sv.txt";
                break;
            case SPANISH: fileName = "sp.txt";
                break;
            default: throw new IllegalArgumentException("The lang variable passed to getString is not allowed.");
        }
        String stringToLookFor;
        switch (area) {
            case START_BUTTON: stringToLookFor = "start_button";
                break;
            case HIGHSCORES_BUTTON: stringToLookFor = "highscores_button";
                break;
            case POWERUPS_BUTTON: stringToLookFor = "powerups_button";
                break;
            case QUIT_BUTTON: stringToLookFor = "quit_button";
                break;
            case BACK_BUTTON: stringToLookFor = "back_button";
                break;
            case INPUT_BUTTON: stringToLookFor = "input_button";
                break;
            default: throw new IllegalArgumentException("The area variable passed to getString is not allowed.");
        }
        try (BufferedReader file = new BufferedReader(new FileReader(LANGPATH+fileName))) {
            for (String currentLine=file.readLine(); currentLine!=null; currentLine=file.readLine()) {
                String[] parts = currentLine.split(":");
                if (parts[0].trim().equals(stringToLookFor)) {
                    file.close();
                    return parts[1];
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("Couldn't find a translation string.");
    }
}
