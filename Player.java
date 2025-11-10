import java.util.ArrayList;
/**
 * This class will handle information about the Player. It holds their
 * inventory and current room.
 *
 * @author Edmund Dougherty
 * @version 11/10/2025
 */
public class Player
{
    public Room currentRoom;
    public ArrayList<Item> inventory = new ArrayList<>();
    /**
     * Constructor for objects of class Player. Currently only here out of necessity.
     */
    public Player()
    {
    }
}
