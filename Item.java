/**
 * Write a description of class Item here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Item
{
    private String description;
    private int bit;
    private boolean food;
    
    /**
     * Constructor for objects of class Item
     */
    public Item(String e, int mem, boolean isFood)
    {
        description = e;
        bit = mem;
        food = isFood;
    }
    
    /**
     * Returns item description
     */
    public String toString()
    {
        return description;
    }
    
    /**
     * Returns memoriy size
     */
    public int getBit()
    {
        return bit;
    }
    
    /**
     * Checks if item is food 
     */
     public boolean isFood()
    {
        return food;
    }
}
