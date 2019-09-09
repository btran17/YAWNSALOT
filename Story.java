
/**
 * Tells the story of yawnslot
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Story
{
   private String choice;
    private static final String[] s = {

            "\nCongrats to made it to the end!\n" +

            "\nIn the WAR between the the Empire of KANADIA and\n" +
            "the allied city states of WORCHESTERSHIRE, you were a\n" +
            "a KNIGHT named Yoncelot. (Pronounced Yawns-a-lot)\n" +
            "Kanadia has gained the upperhand in the war,\n" +
            "thus, Worchestershire decided to test an EXPERIMENT.\n" +

            "\nThe plan was to have a PEACE AGREEMENT with Kanadia\n" +
            "by offering the first PRINCESS NUKLEA to marry the prince Ernest\n" +
            "of Kanadia. Little did they know that they had planted a\n" +
            "NUKE in the arm of the princess. The bomb activates based on the\n" +
            "EMOTIONAL state of the princess and deactivates when she found TRUE LOVE.\n" +

            "\nIf she was content, it would remain inserted. However, upon marriage,\n" +
            "the princess was unstable and leaked radiation. The two countries\n" +
            "isolated the princess until a solution could be found.\n" +
            "You, Yoncelot, took this opportunity to win over the princess.\n" +
            "You left on a journey to court the princess.\n" +

            "\nAlong the way, you met LORD PHISH, a GOLDFISH WITH A BOOMBOX.\n" +
            "As he jammed by the lake, he told you the only way to please\n" +
            "the princess was to learn how to DANCE. You begged Lord Phish to teach\n" +
            "you how to dance to woo the princess. You danced day and night with\n" +
            "multiple DDR battles to increase you skill. \nUntil one day, you felt " +

            "confident to display your skills to the Princess.\n" +
            "You stumbled upon her castle, hearing her weep for miles.\n" +
            "You annouced that you are searching for love and displayed your\n" +
            "dance skills. The bomb activates based on the EMOTIONAL state of the \n" +
            "princess and deactivates when she found TRUE LOVE.\n" +
            "\n Unfornately, you exploded.\n"
        };
    /**
     * Constructor for objects of class Story
     */
    public Story()
    {
        choice = "\n Option: ";
    }
    
    /**
     * Returns story index
     * @param i Index position
     */
    public String getStory(int i) {
        return s[i];
    }
}
