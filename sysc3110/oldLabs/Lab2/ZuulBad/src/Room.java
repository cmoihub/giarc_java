import java.util.ArrayList;
import java.util.HashMap;

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
 * @version 2011.07.31
 */
public class Room 
{
	public String description;
	private ArrayList<Item> Items;
	private HashMap<String, Room> exits;

	/**
	 * Create a room described "description". Initially, it has
	 * no exits. "description" is something like "a kitchen" or
	 * "an open court yard".
	 * @param description The room's description.
	 */
	public Room(String description) 
	{
		this.description = description;
		exits = new HashMap<String, Room>();
		Items = new ArrayList<Item>();
	}

	/**
	 * Define the exits of this room.  Every direction either leads
	 * to another room or is null (no exit there).
	 * @param north The north exit.
	 * @param east The east east.
	 * @param south The south exit.
	 * @param west The west exit.
	 */
	public void setExit(String direction, Room exitRoom) 
	{
		exits.put(direction, exitRoom);
	}

	/**
	 * @return The description of the room.
	 */
	public String getDescription()
	{
		return description;
	}

	public Room GetRoom(String room)
	{
		return exits.get(room);
	}

	public String GetFullDescription() 
	{
		String fullDescription =  "You are " + getDescription() + ".\n";
		fullDescription += "This room has " + NumberOfItemsInRoom() + " item(s) inside.\n";
		for (Item item : Items) 
		{
			fullDescription += "   "+ item.GetFullDescription() + " \n";
		}
		fullDescription += getAvailableExits();
		return fullDescription;

	}

	public void AddItem(String description, int weightInLbs)
	{
		Items.add(new Item(description, weightInLbs));
	}
	
	public Item GetItem()
	{
		return Items.get(Items.size()-1);
	}
	
	public Item RemoveItem()
	{
		return Items.remove(Items.size()-1);
	}
	public int NumberOfItemsInRoom()
	{
		return Items.size();
	}
	
	private String getAvailableExits() 
	{
		String exitsString = "Exits: ";
		if(GetRoom(Helper.NORTH) != null) {
			exitsString += Helper.NORTH + " ";
		}
		if(GetRoom(Helper.EAST) != null) {
			exitsString += Helper.EAST + " ";
		}
		if(GetRoom(Helper.SOUTH) != null) {
			exitsString += Helper.SOUTH + " ";
		}
		if(GetRoom(Helper.WEST) != null) {
			exitsString += Helper.WEST + " ";
		}
		if(GetRoom(Helper.UP) != null) {
			exitsString += Helper.UP + " ";
		}
		if(GetRoom(Helper.DOWN) != null) {
			exitsString += Helper.DOWN + " ";
		}
		return exitsString;
	}

	public boolean HasExit()
	{
		return exits.size() > 0;
	}
}
