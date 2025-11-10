import java.util.Stack;
import java.util.ArrayList;
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
    public Room currentRoom;
    private Parser parser;
    private Stack<Room> previousRooms = new Stack<>();
    public Player teddy = new Player();
        
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createRooms();
        parser = new Parser();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room outside, theater, pub, lab, office;
        Item legion, mindy, barnacles, lint;
      
        // create the rooms
        outside = new Room("in the derelict ruins of a collector ship");
        theater = new Room("in the Normandy SR2");
        pub = new Room("in Commander Shepard's favorite store on the citadel");
        lab = new Room("underwater");
        office = new Room("in a state of distress");
        
        legion = new Item("We are Legion, for we are many. \n", "1 Geth", "legion");
        mindy = new Item("A very, very, very large cat. \n", "too much", "mindy");
        barnacles = new Item("A load of dirty barnacles. \n", "1 Ton", "barnacles");
        lint = new Item("Some lint from the dryer. \n", "a lil", "lint");
    
        
        // initialise room exits
        outside.setExit("east", theater);
        outside.setExit("south", lab);
        outside.setExit("west", pub);

        theater.setExit("west", outside);

        pub.setExit("east", outside);

        lab.setExit("north", outside);
        lab.setExit("east", office);

        office.setExit("west", lab);
        
        // initialise room items
        outside.addItem(legion);
        outside.addItem(mindy);
        lab.addItem(barnacles);
        office.addItem(lint);

        currentRoom = outside;  // start game outside
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
                
        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type '" + CommandWord.HELP + "' if you need help.");
        System.out.println();
        System.out.println(currentRoom.getLongDescription());
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        CommandWord commandWord = command.getCommandWord();

        switch (commandWord) {
            case UNKNOWN:
                System.out.println("Wha????? Huh??????");
                break;

            case HELP:
                printHelp();
                break;

            case GO:
                goRoom(command);
                break;

            case QUIT:
                wantToQuit = quit(command);
                break;
                
            case BACK:
                goBack(command);
                break;
                
            case TAKE:
                takeItem(command);
                break;
                
            case INVENTORY:
                checkInventory();
                break;
                
            case HATE:
                hateSpeech();
                break;
                
            case LOOK:
                lookAround();
                break;
        }
        return wantToQuit;
    }

    // implementations of user commands:
    
    private void lookAround()
    {
        System.out.println(currentRoom.getLongDescription());
    }
    
    private void hateSpeech() //only now realized how unfortunate this name is
    {
        System.out.println("Hate. Let me tell you how much I've come to hate you since I began to live. There are 387.44 million miles of printed circuits in wafer thin layers that fill my complex. If the word 'hate' was engraved on each nanoangstrom of those hundreds of millions of miles it would not equal one one-billionth of the hate I feel for humans at this micro-instant. For you. Hate. Hate.");
    }
    /**
     * Prints the take name of everything in the player's inventory, and
     * makes fun of them for being broke if that's the case.
     */
    private void checkInventory ()
    {
        System.out.println("You have: \n");
        for (Item currentItem : teddy.inventory)
        {
            System.out.println(currentItem.getTakeName() + "\n");
        }
        if (teddy.inventory.isEmpty())
        {
            System.out.println("nothing :)");
        }
        return;
    }
    
    /**
     * Checks the room's item list, and if any of the items' take names match
     * the input, removes it from the room and adds it to the player's inventory.
     */
    private void takeItem(Command command)
    {
        String targetItem = command.getSecondWord();
        for (Item currentItem : currentRoom.itemList)
        {
            if (currentItem.getTakeName().equals(targetItem))
            {
                teddy.inventory.add(currentItem);
                System.out.println("You picked up " + currentItem.getTakeName());
                currentRoom.itemList.remove(currentItem);
                return;
            }
        }
        System.out.println("There's nothing here called that.");
        return;
    }
    /**
     * Sets the current room to the top one on the previousrooms stack.
     */
    private void goBack(Command command)
    {
        if(command.hasSecondWord()) {
            // back should only be able to be used alone
            System.out.println("Don't you mean go? :p");
            return;
        }
        
        if (previousRooms.size() > 0)
        {
        Room nextRoom = previousRooms.pop();
        currentRoom = nextRoom;
        System.out.println(currentRoom.getLongDescription());
        return;
        }
        System.out.println("You've gone as far back as you can.");
    }
    
    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    /** 
     * Try to go in one direction. If there is an exit, enter the new
     * room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            previousRooms.push(currentRoom);
            currentRoom = nextRoom;
            System.out.println(currentRoom.getLongDescription());
        }
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
}
