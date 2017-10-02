/** SYSC 2101 - Prof-Student-TA Example
 * 
 *
 */

import java.util.Date;
import java.util.Observable;
import java.util.Observer;

public class TeachingAssistant implements Observer
{
	private String name;
	private Date midterm;

	public TeachingAssistant(String aName)
	{
		this.name = aName;
	}

	public String getName() 
	{
		return this.name;
	}

	public void proctor(Date date)
	{
		this.midterm = date;
		System.out.println(name + " : I have to proctor a midterm on " + this.midterm);
	}

	public void postpone(Date date)
	{
		this.midterm = date;
		System.out.println(name + " : Now the midterm is on " + this.midterm);
	}

	@Override
	public void update(Observable o, Object arg)
	{
		Prof p = (Prof)o;
		if(midterm != null && p.getMidterm().after(midterm))
		{
			postpone(p.getMidterm());
		}
		else
		{
			proctor(p.getMidterm());
		}
	}
}
