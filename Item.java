
/**
 * Write a description of class Item here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Item
{
    // instance variables - replace the example below with your own
    public String description;
    public String weight;

    /**
     * Constructor for objects of class Item.
     */
    public Item(String description, String weight)
    {
       this.description = description;
       this.weight = weight;
    }
    /**
     * Returns the description of this item.
     */
     public String getItemDescription()
    {
        return description;
    }
    
    /**
     * Returns this item's weight.
     */
    public String getItemWeight()
    {
        return weight;
    }
}
