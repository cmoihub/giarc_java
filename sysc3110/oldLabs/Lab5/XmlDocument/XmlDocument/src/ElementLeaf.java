
public class ElementLeaf implements IElement
{
	private String name = "";
	private String value = "";

	public ElementLeaf(String elementName)
	{
		name = elementName;
	}

	@Override
	public String getName() 
	{
		return name;
	}
	
	public String getValue()
	{
		return value;
	}
	
	public void setValue(String val)
	{
		value = val;
	}
	
	@Override
	public String ToString()
	{
		return "\t<" + getName()  + ">" + getValue() + "</" + getName() + "> \n";
	}

}
