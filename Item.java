import java.util.HashMap;
import java.util.Set;
/**
 * The Item class handles the functionality of items. Items have a description,
 * weight, and a take name.
 *
 * @author Edmund Dougherty
 * @version 11/7/25
 */
public class Item
{
    private String description;
    private String weight;
    private String takeName; //this is what the player needs to enter to take it

    /**
     * Constructor for objects of class Item.
     */
    public Item(String description, String weight, String takeName)
    {
       this.description = description;
       this.weight = weight;
       this.takeName = takeName;
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
    
    /**
     * Returns this item's take name.
     */
    public String getTakeName()
    {
        return takeName;
    }
}
