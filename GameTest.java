import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.Random;

/**
 * GameTest - a class to test the zuul game engine.
 *
 * @author  William H. Hooper
 * @version 2018-11-19
 */
public class GameTest
{
    private Game game1;
    private Console console1; 

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
        System.out.println("\f");
        game1 = new Game();
        console1 = new Console(game1);
        String message = game1.readMessages();
        System.out.print(message + "> ");
    }

    @Test
    public void start()
    {
        assertEquals(false, game1.finished());
    }

    @Test
    public void map()
    {
        assertEquals(true, console1.testCommand("go east", "cabin"));
        assertEquals(true, console1.testCommand("go south", "forest"));
        assertEquals(true, console1.testCommand("go west", "lake"));
        assertEquals(true, console1.testCommand("go west", "forest"));
        assertEquals(true, console1.testCommand("go south", "courtyard"));
        assertEquals(true, console1.testCommand("go straight", "castle"));
        // assertEquals(true, console1.testCommand("go up", "room"));
        assertEquals(true, console1.testCommand("go down", "dungeon"));
        // assertEquals(true, console1.testCommand("go west", "hiddenroom"));
        // assertEquals(true, console1.testCommand("go south", "forest3"));
        // assertEquals(true, console1.testCommand("go east", "cellar"));
        // assertEquals(true, console1.testCommand("go north", "dungeon"));
        assertEquals(true, console1.testCommand("go east", "room"));
        assertEquals(true, console1.testCommand("go down", "tunnel"));
        assertEquals(true, console1.testCommand("go down", "library"));
        // assertEquals(true, console1.testCommand("go north", "tunnel"));
    }
    

    @Test
    public void noDoor()
    {
        assertEquals(true, console1.testCommand("go north", "no door!"));
    }

    @Test
    public void quit()
    {
        console1.testCommand("quit");
        assertEquals(true, game1.finished());
        assertEquals(false, console1.testCommand("go North", "doorway"));
        assertEquals(true, console1.testCommand("anything", "game is over"));
    }

    @Test
    public void help()
    {
        String string1 = console1.getResponse("help");
        assertNotNull(string1);
        assertEquals(true, string1.contains("go"));
        assertEquals(true, string1.contains("quit"));
        assertEquals(true, string1.contains("help"));
        assertEquals(true, string1.contains("search"));
        assertEquals(true, string1.contains("collect"));
        assertEquals(true, string1.contains("back"));
        assertEquals(true, string1.contains("look"));
    }

    @Test
    public void back()
    {
        assertEquals(true, console1.testCommand("go south", "lake"));
        assertEquals(true, console1.testCommand("go west", "forest"));
        assertEquals(true, console1.testCommand("back", "lake"));
    }

    @Test
    public void backback()
    {
        assertEquals(true, console1.testCommand("go east", "cabin"));
        assertEquals(true, console1.testCommand("go south", "forest"));
        assertEquals(true, console1.testCommand("go west", "lake"));
        assertEquals(true, console1.testCommand("back", "forest"));
        assertEquals(true, console1.testCommand("back back", "cabin"));
        assertEquals(true, console1.testCommand("go south", "forest"));
        // assertEquals(true, console1.testCommand("back back", "outside"));
        assertEquals(false, console1.testCommand("back back", "outside"));
    }

    @Test
    public void items()
    {
        // console1.testCommand("take Kanadia");
        // assertEquals(true, console1.testCommand("take", "Kanadia"));
        // assertEquals(true, console1.testCommand("take", "Worchestershire"));
        // assertEquals(true, console1.testCommand("go east", "cabin"));
        // assertEquals(true, console1.testCommand("take", "knight"));
        // assertEquals(false, console1.testCommand("take", "door"));
        // assertEquals(true, console1.testCommand("go south", "forest"));
        // assertEquals(true, console1.testCommand("take", "Lord Phish"));
        // assertEquals(true, console1.testCommand("go west", "lake"));
        // assertEquals(true, console1.testCommand("go west", "forest"));
        // assertEquals(true, console1.testCommand("go south", "courtyard"));
        // assertEquals(true, console1.testCommand("go straight", "castle"));
        // assertEquals(true, console1.testCommand("go up", "room"));
        // assertEquals(false, console1.testCommand("take", "Yoncelot"));
    }
    
    @Test
    public void endToStart()
    {
        assertEquals(true, console1.testCommand("go south", "lake"));
        assertEquals(true, console1.testCommand("go west", "forest"));
        assertEquals(true, console1.testCommand("go south", "courtyard"));
        assertEquals(true, console1.testCommand("go straight", "castle"));
        assertEquals(true, console1.testCommand("go down", "dungeon"));
        assertEquals(true, console1.testCommand("go east", "room"));
        assertEquals(true, console1.testCommand("go down", "tunnel"));
        assertEquals(true, console1.testCommand("go down", "library"));
        assertEquals(true, console1.testCommand("back", "tunnel"));
        assertEquals(true, console1.testCommand("back", "room"));
        assertEquals(true, console1.testCommand("back", "dungeon"));
        assertEquals(true, console1.testCommand("back", "castle"));
        assertEquals(true, console1.testCommand("back", "courtyard"));
        assertEquals(true, console1.testCommand("back", "forest"));
        assertEquals(true, console1.testCommand("back", "lake"));
        assertEquals(true, console1.testCommand("back", "cemetery"));
    }

    @Test
    public void look()
    {
        assertEquals(true, console1.testCommand("look", "cemetery"));
        assertEquals(true, console1.testCommand("go east", "cabin"));
        assertEquals(true, console1.testCommand("look", "cabin"));
        assertEquals(true, console1.testCommand("go south", "forest"));
        assertEquals(true, console1.testCommand("look", "forest"));
    }

    @Test
    public void forage()
    {
        assertEquals(true, console1.testCommand("search", "Kanadia"));
        assertEquals(true, console1.testCommand("search", "Worchestershire"));
        assertEquals(true, console1.testCommand("go south", "lake"));
        assertEquals(true, console1.testCommand("search", "goldfish"));
        assertEquals(true, console1.testCommand("go east", "forest"));
        assertEquals(true, console1.testCommand("search", "war"));
        assertEquals(true, console1.testCommand("search", "Lord Phish"));
    }

    @Test
    public void item()
    {
        assertEquals(true, console1.testCommand("take Kanadia", "Kanadia has been added to your inventory."));
        assertEquals(true, console1.testCommand("take Worchestershire", "Worchestershire has been added to your inventory."));
        assertEquals(true, console1.testCommand("go east", "cabin"));
        assertEquals(true, console1.testCommand("take knight", "knight has been added to your inventory."));
        assertEquals(true, console1.testCommand("take door", "Fragment not found"));
        assertEquals(true, console1.testCommand("go south", "forest"));
        assertEquals(true, console1.testCommand("take Lord Phish", "Lord Phish has been added to your inventory."));
        assertEquals(true, console1.testCommand("go west", "lake"));
        assertEquals(true, console1.testCommand("go west", "forest"));
        assertEquals(true, console1.testCommand("go south", "courtyard"));
        assertEquals(true, console1.testCommand("go straight", "castle"));
        assertEquals(true, console1.testCommand("go up", "room"));
        assertEquals(true, console1.testCommand("take Yoncelot", "Fragment not found"));
    }

    @Test
    public void takeMemories()
    {
        console1.testCommand("go south");
        console1.testCommand("go east");
        console1.testCommand("go west");
        console1.testCommand("go west");
        console1.testCommand("go south");
        console1.testCommand("go straight");
        console1.testCommand("go down");
        console1.testCommand("go down");
        console1.testCommand("go east");
        console1.testCommand("go down");
        console1.testCommand("go down");
        console1.testCommand("search");
        assertEquals(true, console1.testCommand("take memories", "You need to collect all your memories"));
    }

    @Test
    public void character()
    {
        assertEquals(true, console1.testCommand("go south", "Sir Phish"));
        assertEquals(false, console1.testCommand("go east", "Sir Phish"));
    }
}


























