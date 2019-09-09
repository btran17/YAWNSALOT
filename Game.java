import java.util.Stack;
import java.util.Random;
import java.util.ArrayList;
import java.util.List;
/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */

public class Game 
{
    private Parser parser;
    // private Room currentRoom;
    // private Stack<Room> previousRoom;
    private Player player;
    private Random gen;

    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        this(new Random());
    }

    /**
     * Overloaded constructor
     */
    public Game(Random g) 
    {
        player = new Player();
        createRooms();
        parser = new Parser();
        // previousRoom = new Stack<Room>();
        gen = g;
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room outside, forest, lake, castle, library, dungeon, forest3,
        cabin, hiddenroom, cellar, tunnel, room, courtyard, forest2,
        room1;

        // create the rooms
        outside = new Room("in a cemetery"); 
        forest = new Room("now in a forest");
        forest2 = new Room("now in another forest");
        forest3 = new Room("now in another forest again");
        lake = new Room("now at a lake");
        castle = new Room("at the castle");
        library = new Room("in the library");
        cabin = new Room("at the cabin");
        hiddenroom = new Room("in a hidden room");
        cellar = new Room("in the celler");
        tunnel = new Room("in a tunnel");
        room = new Room("in a room");
        room1 = new Room("in a room");
        courtyard = new Room("in the courtyard");
        dungeon = new Room("in the dungeon");

        // assign images
        outside.setImage("graveyard.jpg");
        cabin.setImage("cabin.jpg");
        forest.setImage("forest2.jpg");
        forest2.setImage("cscape.jpg");
        forest3.setImage("forest3.jpg");
        lake.setImage("lake.png");
        courtyard.setImage("courtyardgate.jpg");
        castle.setImage("castle.jpg");
        room.setImage("room1.jpg");
        room1.setImage("room2.jpg");
        tunnel.setImage("tunnel.jpg");
        hiddenroom.setImage("image.jpg");
        dungeon.setImage("1.jpg");
        cellar.setImage("cellar.jpg");
        library.setImage("lib.jpg");

        //initialize exits
        outside.setExit("east", cabin);
        outside.setExit("south", lake);
        cabin.setExit("west", outside);
        cabin.setExit("south", forest);
        forest.setExit("north", cabin);
        forest.setExit("west", lake);
        lake.setExit("north", outside);
        lake.setExit("east", forest);
        lake.setExit("west", forest2);
        forest2.setExit("east", lake);
        forest2.setExit("south", courtyard);
        courtyard.setExit("north", forest2);
        courtyard.setExit("straight", castle);
        castle.setExit("backward", courtyard);
        castle.setExit("up", room);
        room.setExit("down", castle);
        castle.setExit("down", dungeon);
        dungeon.setExit("up", castle);
        dungeon.setExit("west", hiddenroom);
        hiddenroom.setExit("east", dungeon);
        hiddenroom.setExit("south", forest3);
        forest3.setExit("north", hiddenroom);
        forest3.setExit("east", cellar);
        cellar.setExit("west", forest);
        cellar.setExit("north", dungeon);
        dungeon.setExit("east", room1);
        room1.setExit("west", dungeon);
        room1.setExit("down", tunnel);
        tunnel.setExit("north", room1);
        tunnel.setExit("down", library);
        library.setExit("north", tunnel);

        // currentRoom = outside;  // start game outside
        player.setRoom(outside);

        cabin.addItem(new Item("knight", 1, false));
        forest.addItem(new Item("war", 1,false));
        forest.addItem(new Item("magic cookie",1, true));
        forest.addItem(new Item("Lord Phish", 1, false));
        outside.addItem(new Item("Kanadia", 1, false));
        outside.addItem(new Item("Worchestershire",1, false));
        lake.addItem(new Item("goldfish with a boombox",1, false));

        forest2.addItem(new Item("dance",1, false));
        courtyard.addItem(new Item("Princess Nuklea",1, false));
        castle.addItem(new Item("explosion",1, false));
        castle.addItem(new Item("emotional",1, false));
        forest2.addItem(new Item("magic cookie",1, true));

        cellar.addItem(new Item("true love",1, false));
        forest3.addItem(new Item("nuke",1, false));
        forest3.addItem(new Item("magic cookie",1, true));

        dungeon.addItem(new Item("experiment",1, false));
        hiddenroom.addItem(new Item("DDR",1, false));
        room1.addItem(new Item("peace agreement",1, false));

        library.addItem(new Item("memories", 0, true));

        //Create character
        // Character Phish = new Character("Sir Phish", "I'm a goldfish.");
        lake.addCharacter(new Character("Sir Phish", "You found me!\n" +
                "I'm a goldfish with a boombox. I was your teacher."));
        courtyard.addCharacter(new Character("Sir Phish", "I shall follow you periodically."));
        cellar.addCharacter(new Character("Sir Phish", "Breakdance to the jams of my boombox."));
        dungeon.addCharacter(new Character("Bool Jae", "Dungeon's aren't my thing."));
        hiddenroom.addCharacter(new Character("Nuklea", "How chivalrous you were."));
        room1.addCharacter(new Character("Sir Phish", "It was a great adventure."));
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        println();
        println("Welcome to the Adventures of Yoncelot!");
        println("Your name is Yoncelot. You are dead!");
        println("Type 'help' if you need help.");
        println();
        println("You are " + player.getRoom().getLongDescription());
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private void processCommand(Command command) 
    {
        if(command.isUnknown()) {
            println("I don't know what you mean...");
            return;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help")) {
            printHelp();
        }
        else if (commandWord.equals("go")) {
            goRoom(command);
        }
        else if (commandWord.equals("quit")) {
            quit(command);
        }
        else if (commandWord.equals("search")) {
            forage(command);
        }
        else if (commandWord.equals("look")) {
            look();
        }
        else if (commandWord.equals("back")) {
            goBack();
        }
        else if (commandWord.equals("take")) {
            take(command);
        }
        else if (commandWord.equals("drop")) {
            drop(command);
        }
        else if (commandWord.equals("eat")){
            eat(command);
        }
        else if (commandWord.equals("items")){
            checkInventory();
        }
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        println("You need to 'search' for your memory fragments.");
        println("When you find your \"memories\" eat them.");
        println();
        println(parser.getCommands2());
    }

    /** 
     * Try to go in one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            println("Go where?");
            return;
        }

        String direction = command.getSecondWord().toLowerCase();

        // Try to leave current room.
        // Room nextRoom = currentRoom.getExit(direction);

        // if (nextRoom == null) {
        // println("There is no door!");
        // }
        // else {
        // previousRoom.push(currentRoom);
        // currentRoom = nextRoom;
        // println("You are " + currentRoom.getLongDescription());
        // }
        println(player.goRoom(direction));
    }

    /**
     * Take a certain item.
     * @param command The item to be processed.
     */
    private void take(Command command) {
        if(!command.hasSecondWord()) {
            println("Take what?");
            return;
        }
        String item = command.getSecondWord();
        Room currentRoom = player.getRoom();
        Item frag = currentRoom.getFragment(item);
        if(player.getStorage() < player.getMaxStorage()){
            if(player.getStorage() == 15 && item != "memories"){
                if(currentRoom.getFragment(item) != null){
                    player.addItem(currentRoom.getFragment(item));
                    println(currentRoom.getFragment(item).toString() + 
                        " has been added to your inventory.");
                    currentRoom.removeItem(currentRoom.getFragment(item));
                }
                else{
                    println("Fragment not found");
                }
            }
            else{
                println("You need to collect all your memories.");
            }
        }
        else{
            println("You need more memory storage");
        }
    }

    /** 
     * Drop a specified item.
     * @param command The item to be processed.
     */
    private void drop(Command command) {
        if(!command.hasSecondWord()) {
            println("Which item do you want to drop?");
            return;
        }
        String item = command.getSecondWord().toLowerCase();
        String dropped = player.drop(item);
        println(dropped);
    }

    /**
     * List all items in the player's inventory.
     */
    private void checkInventory()
    {
        println("Total Memory: " + player.getStorage() + " " 
            + player.getMaxStorage() + " MB");
        println(player.getInventory());
    }

    /**
     * Eat one food item from the player's invetory to increase hunger.
     */
    private void eat(Command command)
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            println("Eat what?");
            return;
        }

        String item = command.getSecondWord().toLowerCase();

        if(item.equals("cookie")) {
            println(player.magicCookie(item));
            return;
        }

        if(item.equals("memories")) {
            println(player.getStory1());
            return;

        }

        println(player.eat(item));
    }

    /**
     *  Go back to previous room travelled
     */
    private void goBack()
    {
        // if(previousRoom.empty()) {
        // println("You have nowhere to go back!");
        // } 
        // else {
        // currentRoom = previousRoom.pop();
        // println("You are " + currentRoom.getLongDescription());
        // }
        println(player.goBack());
    }

    /**
     * Prints the current location
     */
    private void look()
    {
        println("You are " + player.getRoom().getLongDescription());
    }

    /**
     * Searches area
     */
    private void forage(Command command) 
    {
        println(player.getRoom().getFragments());
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private void quit(Command command) 
    {
        if(command.hasSecondWord()) {
            println("Quit what?");
            return;
        }

        wantToQuit = true;  // signal that we want to quit
    }

    /****************************************************************
     * If you want to launch an Applet
     ****************************************************************/

    /**
     * @return an Image from the current room
     * @see Image
     */
    public String getImage()
    {
        return player.getRoom().getImage();
    }

    /**
     * @return an audio clip from the current room
     * @see AudioClip
     */
    public String getAudio()
    {
        return player.getRoom().getAudio();
    }

    /****************************************************************
     * Variables & Methods added 2018-04-16 by William H. Hooper
     ****************************************************************/

    private String messages;
    private boolean wantToQuit;

    /**
     * Initialize the new variables and begin the game.
     */
    private void start()
    {
        messages = "";
        wantToQuit = false;
        printWelcome();
    }

    /**
     * process commands or queries to the game
     * @param input user-supplied input
     */
    public void processInput(String input)
    {
        if(finished()) {
            println("This game is over.  Please go away.");
            return;
        }

        Command command = parser.getCommand(input);
        processCommand(command);
    }

    /**
     * clear and return the output messages
     * @return current contents of the messages.
     */
    public String readMessages()
    {
        if(messages == null) {
            start();
        }
        String oldMessages = messages;
        messages = "";
        return oldMessages;
    }

    /**
     * @return true when the game is over.
     */
    public boolean finished()
    {
        return wantToQuit;
    }

    /**
     * add a message to the output list.
     * @param message the string to be displayed
     */
    private void print(String message)
    {
        messages += message;
    }

    /**
     * add a message to the output list, 
     * followed by newline.
     * @param message the string to be displayed
     * @see readMessages
     */
    private void println(String message)
    {
        print(message + "\n");
    }

    /**
     * add a blank line to the output list.
     */
    private void println()
    {
        println("");
    }
}
