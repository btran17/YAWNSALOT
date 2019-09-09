
/**
 * Write a description of class Character here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Character
{
    private String name;
    private String dialogue;
    private boolean hasSpoken;
    /**
     * Constructor for objects of class Character
     */
    public Character(String name, String dialogue)
    {
        this.name = name;
        this.dialogue = dialogue;
        this.hasSpoken = false;
    }

    /**
     * @return the name of character
     */
    public String getName() {
        return name;
    }

    /**
     * Define the name of character
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the dialogue
     */
    public String getDialogue() {
        return dialogue;
    }

    /**
     * Define the dialogue of character when meeting
     * @param dialogue the dialogue to set
     */
    public void setDialogue(String dialogue) {
        this.dialogue = dialogue;
    }

    /**
     * Check if character has spoken or not
     * @return the hasSpoken
     */
    public boolean hasSpoken() {
        return hasSpoken;
    }

    /**
     * Define if character has spoken or not
     * @param hasSpoken  the hasSpoken to set
     */
    public void setHasSpoken(boolean hasSpoken) {
        this.hasSpoken = hasSpoken;
    }
}
