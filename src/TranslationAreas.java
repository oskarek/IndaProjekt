/**
 * Enum for translation areas in the game.
 * Created by oskarek on 2015-05-13.
 */
public enum TranslationAreas {
    START_BUTTON("start_button"),
    CONTINUE_BUTTON("continue_button"),
    HIGHSCORES_BUTTON("highscores_button"),
    POWERUPS_BUTTON("powerups_button"),
    QUIT_BUTTON("quit_button"),
    BACK_BUTTON("back_button"),
    INPUT_FIELD("input_field"),
    PAUSE_STRING("pause_string"),
    SMALLBOARD_DESCRIPTION("smallboard_description"),
    BIGBOARD_DESCRIPTION("bigboard_description"),
    BIGBALL_DESCRIPTION("bigball_description"),
    FASTBALL_DESCRIPTION("fastball_description"),
    SLOWBALL_DESCRIPTION("slowball_description"),
    LASER_DESCRIPTION("laser_description"),
    CANNON_DESCRIPTION("cannon_description"),
    CONGRATULATIONS_MESSAGE("congratulations_message"),
    GAMEOVER_MESSAGE("gameover_message"),
    ENTERNAME_MESSAGE("entername_message"),
    ENTERNAME_PROMPT("entername_prompt");

    private String identifier;

    TranslationAreas(String identifier) {
        this.identifier = identifier;
    }

    public String getIdentifier() {
        return identifier;
    }
}
