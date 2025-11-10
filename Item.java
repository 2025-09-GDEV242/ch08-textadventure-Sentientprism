import java.util.HashMap;
import java.util.Set;
/**
 * The Item class handles the functionality and distribution of items.
 *
 * @author Edmund Dougherty
 * @version 11/7/25
 */
public class Item
{
    private String description;
    private String weight;

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
    
    public String getItemString() // come back to this
    {
        String returnString = "You see there is a: "; 
        //Set<Item> theStuffThatIsInThisRoom = itemList.keySet();
        //returnString += getItemDescription() + " " + getItemWeight();
        //return returnString;
        //this code is a load of dirty barnacles FIX IT!!!!
        return returnString;
    }
}
