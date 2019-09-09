import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  The exits are labelled north, 
 * east, south, west.  For each direction, the room stores a reference
 * to the neighboring room, or null if there is no exit in that direction.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */
public class Room 
{
    private String description;
    private String imageName;
    private String audioName;

    private HashMap<String, Room> map;
    private ArrayList<Item> memories;
    
    private Character fish;
    
    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description) 
    {
        this.description = description;
        memories = new ArrayList<>();
        map = new HashMap<>();
    }

    /**
     * Define the exits of this room. 
     * @param direction The direction of the exit
     * @param neighbor The room in the given direction
     */
    public void setExit(String direction, Room neighbor)
    {
        map.put(direction, neighbor);
    }

    /**
     * Return the room that is reached if we go from this room in
     * direction "direction." If there is no room in that direction,
     * return null. 
     * @param direction The exit's direction
     * @return The room in the given direction
     */
    public Room getExit(String direction)
    {
        return map.get(direction);
    }

    /**
     * Return a description of the room's exits,
     * for example, "Exits: north west."
     * @return A description of the available exits
     */
    public String getExitString()
    {
        String exits = "";
        for(String e : map.keySet())
        {
            exits += e + " "; 
        }
        return exits;
    }

    /**
     * @return The description of the room.
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * returns the description of the room and attributes
     */
    public String getLongDescription()
    {
        return description + "\n" + "Exits: " 
        + getExitString() + "\n" 
        + "Floating around are: "; // + getFragments();
    }

    /**
     * adds item to memories
     */
    public void addItem(Item e)
    {
        memories.add(e);
    }

    /**
     * Returns the memory fragment
     */
    public String getFragments()
    {
        String r = " ";
        String s = " ";
        for(Item m : memories) {
            r += "\"" + m + "\"" + "  ";
        }
        return r;
    }

    /**
     * returns the description of the item
     */
    public Item getFragment(String partial)
    {
        for(Item m : memories) {
            String description = m.toString();
            if(description.contains(partial)) {
                return m;
            }
        }
        return null;
    }

    /**
     * removes the item
     */
    public void removeItem(Item e)
    {
        memories.remove(e);
    }

    /**
     * returns the item description
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
     * Add a character to the room
     * @param person
     */
    public void addCharacter(Character fish) {
        this.fish = fish;
    }

    /**
     * @return the character of the room
     */
    public Character getCharacter() {
        return this.fish;
    }    


    /*************************************************************
     * added by William H. Hooper, 2006-11-28
     *************************************************************/
    /**
     * @return a String, which hopefully contains the file name
     * of an Image file.
     */
    public String getImage()
    {
        return imageName;
    }

    /**
     * associate an image with this room
     * @param filename a String containing a file.
     * The file <b>must</b> reside in the media directory, 
     * and must be in a format viewable in the Java AWT.
     * Readable formats include: 
     * PNG, JPG (RGB color scheme only), GIF
     */
    public void setImage(String filename)
    {
        imageName = "media/" + filename;
    }

    /**
     * @return a String, which hopefully contains the file name
     * of an audio file.
     */
    public String getAudio()
    {
        return audioName;
    }

    /**
     * associate an audio clip with this room
     * @param filename a String containing a file.
     * The file <b>must</b> reside in the media directory, 
     * and must be in a format palyable in the Java AWT.
     * Readable formats include: 
     * WAV, AU.
     */
    public void setAudio(String filename)
    {
        audioName = "media/" + filename;
    }
}
