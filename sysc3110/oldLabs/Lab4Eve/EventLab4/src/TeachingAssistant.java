/** SYSC 2101 - Prof-Student-TA Example
 * 
 *
 */

import java.util.Date;

public class TeachingAssistant implements ICourseListener
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
	public void midtermAnnounced(CourseEvent e)
	{
		proctor(e.getMidtermDate());
	}

	@Override
	public void midtermPostponed(CourseEvent e) 
	{
		postpone(e.getMidtermDate());
	}

}
