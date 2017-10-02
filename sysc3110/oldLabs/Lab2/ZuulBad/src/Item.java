/**
 * 
 */

/**
 * @author jaspreetsanghra
 *
 */
public class Item
{
	private String Description;
	private int WeightInLbs;
	
	public Item(String description, int weightInLbs)
	{
		Description = description;
		WeightInLbs = weightInLbs;
	}
	
	public int GetWeight()
	{
		return WeightInLbs;
	}
	
	public String GetDescription()
	{
		return Description;
	}
	
	public String GetFullDescription()
	{
		return "There is " + Description + " that is " + WeightInLbs + " lbs. ";
	}

}
