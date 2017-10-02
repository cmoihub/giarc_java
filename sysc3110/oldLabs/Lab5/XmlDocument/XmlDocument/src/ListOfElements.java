import java.util.*;

public class ListOfElements implements IElement
{
	private String name = "";
	private List<IElement> list;
	
	public ListOfElements(String elementName)
	{
		name = elementName;
		list = new ArrayList<IElement>();
	}

	@Override
	public String getName() 
	{
		return name;
	}
	
	public void addElement(IElement e)
	{
		list.add(e);
	}
	
	public boolean removeElement(int index)
	{
		if(index > 0 && index < getSize())
		{
			list.remove(index);
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public int getSize()
	{
		return list.size();
	}
	
	public String ToString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("<" + getName() + ">\n");
		for (IElement element : list) 
		{
			sb.append(element.ToString());
		}
		sb.append("</" + getName() + ">\n");
		return sb.toString();
	}
	
}
