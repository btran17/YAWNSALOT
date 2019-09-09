import java.util.Stack;
import java.util.ArrayList;
import java.util.List;
/**
 * Write a description of class Player here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Player
{
    private Room currentRoom;
    private Stack<Room> previousRoom;
    private List<Item> memories;
    private int currentMem;
    private int maxStorage;
    private Story story;

    /**
     * Constructor for objects of class Player
     */
    public Player()
    {
        previousRoom = new Stack<Room>();
        memories = new ArrayList<>();
        currentMem = 0;
        maxStorage = 5; // 15 items total
        story = new Story();
    }

    /**
     * Returns the story
     */
    public String getStory1()
    {
        return story.getStory(0);
    }

    /**
     * Returns number in storage
     */
    public int getStorage()
    {
        int storage = 0;
        for(Item m : memories){
            storage += m.getBit();
        }
        return storage;
    }

    /**
     * Returns max storage
     */
    public int getMaxStorage()
    {
        return maxStorage;
    }

    /***
     *  Sets the room
     *  @param r The Room
     */
    public void setRoom(Room r)
    {
        currentRoom = r;
    }

    /**
     * Returns the room
     */
    public Room getRoom()
    {
        return currentRoom;
    }

    /** 
     * Try to go in one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     * @param direction Where the player goes
     */
    public String goRoom(String direction) 
    {
        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            return "There is no door!";
        }
        else {
            previousRoom.push(currentRoom);
            currentRoom = nextRoom;
            //Check if there are Character in the room
            Character p = currentRoom.getCharacter(); 
            if(p != null && !p.hasSpoken()) {
                return (p.getName() + ": " + p.getDialogue());
            }
            
            return "You are " + currentRoom.getLongDescription();
        }
    }

    /**
     *  Go back to previous room travelled
     */
    public String goBack()
    {
        if(previousRoom.empty()) {
            return ("You have nowhere to go back!");
        } 
        else {
            currentRoom = previousRoom.pop();
            return ("You are " + currentRoom.getLongDescription());
        }
    }

    /**
     * Add items to memory
     * @param e Item
     */
    public void addItem(Item e)
    {
        memories.add(e);
    }

    /**
     *  Return the items found
     */
    public String getFragments()
    {
        String r = " ";
        String s = " ";
        for(Item m : memories) {
            s = "\nYou find ";
            r += "\"" + m + "\"" + "  ";
        }
        return s + r;
    }

    /**
     * Returns the items given
     * @param partial name of Item
     */
    public Item getFragment(String partial)
    {
        for(Item m : memories) {
            if(m.toString().contains(partial)) {
                return m;
            }
        }
        return null;
    }

    /**
     *  Remove Item from collection
     *  @param e Item in collection
     */
    public void removeItem(Item e)
    {
        memories.remove(e);
        currentMem -= e.getBit();
    }

    /**
     * Return items in memory
     */
    public String getItemString()
    {
        if(memories.isEmpty()){
            return "";
        }
        String mString = "";
        for(Item it : memories){
            mString = "Fragment(s): " + memories.toString();
        }
        return mString;
    }

    /**
     * Get item in memory
     * @param description Name of item
     */
    public Item getItem(String description)
    {
        for(Item m : memories){
            if(m.toString().contains(description)){
                return m;
            }
        }
        return null;
    }

    /**
     * Adds to amount in memory
     */
    public int totalMem()
    {
        int sum = 0;
        for(Item m : memories){
            sum += m.getBit();
        }
        return sum;
    }

    /**
     *  Returns what is in the inventory
     */
    public String getInventory()
    {
        if(memories.isEmpty()){
            return "Your memory is empty.";
        } else {
            String message = "Your memory contains:\n";
            for(Item item : memories){
                message += ("~ " + item.toString() + "\n");
            }
            return message;
        }
    }

    /**
     * Returns item dropped
     * @param frag Item name
     */
    public String drop(String frag)
    {
        Item it = null;
        frag = frag.toLowerCase();
        for(Item m: memories) {
            if(m.toString().toLowerCase().contains(frag)) {
                it = m;
            }
        }

        if(it == null) {
            return "You do not have such memory";
        }

        removeItem(it);
        String n = it.toString();
        currentRoom.addItem(it);

        return "You placed the " + n + " " + currentRoom.getDescription();
    }

    /**
     * Returns item eaten
     * @param i Name of item
     */
    public String eat(String i){
        Item it = null;

        if(it == null) {
            return "You do not have this item in your inventory";
        }

        if(it.isFood() == false) {
            return "You can't eat that.";
        }

        memories.remove(it);
        return "You have eaten the " + i;
    }

    /**
     * Returns if magicCookie was eaten
     * @param item Name of item
     */
    public String magicCookie(String item)
    {
        Item i = null;

        for(Item m : memories) {
            String description = m.toString();
            if(description.contains("cookie")) {
                i = m;
            }
        }

        if(i == null) {
            return "You do not have this item in your inventory";
        }
        maxStorage += 5;
        memories.remove(i);

        return "You have increased memory storage." +
        "You can now hold " + maxStorage + " MB";

    }

    /**
     * Returns memory if in inventory
     * @param item - Name of Item
     */
    public String memory(String item)
    {
        Item i = null;

        for(Item m : memories) {
            String description = m.toString();
            if(description.contains("memories")) {
                i = m;
                return(getStory1());
            }

        }
        memories.remove(i);

        return "You do not have this item in your inventory";
    }

}
