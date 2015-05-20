import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by oskarek on 2015-05-13.
 */
public class LangFileReader {
    private static final String LANGPATH = "lang/";

    public String getString(TranslationAreas area, String langFileName) {
        String stringToLookFor = area.getIdentifier();
        try (BufferedReader file = new BufferedReader(new FileReader(LANGPATH+langFileName))) {
            for (String currentLine=file.readLine(); currentLine!=null; currentLine=file.readLine()) {
                String[] parts = currentLine.split("=");
                if (parts[0].trim().equals(stringToLookFor)) {
                    file.close();
                    return parts[1];
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Couldn't find the language file.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("Couldn't find a translation string.");
    }

    /**
     * Get the name of the file containing the translations to the
     * currently selected language.
     * @return
     */
    public String getCurrentLanguageFileName() {
        String currLangFileName = "lang/currentlang.txt";
        String langFile = null;
        try (BufferedReader file = new BufferedReader(new FileReader(currLangFileName))) {
            langFile = file.readLine();
        } catch (FileNotFoundException e) {
            System.err.println("Couldn't find the currentlang file.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (langFile == null) {
            throw new RuntimeException("The currentlang file is empty.");
        }
        return langFile;
    }
}
