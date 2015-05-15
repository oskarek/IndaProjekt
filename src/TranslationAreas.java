/**
 * Enum for translation areas in the game.
 * Created by oskarek on 2015-05-13.
 */
public enum TranslationAreas {
    START_BUTTON("start_button"),
    HIGHSCORES_BUTTON("highscores_button"),
    POWERUPS_BUTTON("powerups_button"),
    QUIT_BUTTON("quit_button"),
    BACK_BUTTON("back_button"),
    INPUT_FIELD("input_field");

    private String identifier;

    TranslationAreas(String identifier) {
        this.identifier = identifier;
    }

    public String getIdentifier() {
        return identifier;
    }
}
