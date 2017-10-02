/** SYSC 2101 - Prof-Student-TA Example
 * 
 *
 */

import java.util.ArrayList;
import java.util.Date;

public class Prof
{
	private String name;
	private ArrayList<ICourseListener> courseListeners;
	private Date midterm;

	public Prof(String aName) 
	{
		this.name = aName;
		this.courseListeners = new ArrayList<ICourseListener>();
	}


	public String getName() 
	{
		return this.name;
	}
	
	public void addCourseListener(ICourseListener c)
	{
		courseListeners.add(c);
	}
	
	public void removeCourseListener(ICourseListener c)
	{
		courseListeners.remove(c);

	}

	public void setMidterm(Date date) 
	{
		midterm = date;
		CourseEvent e = new CourseEvent(this);
		e.setMidtermDate(date);
		for(ICourseListener c : courseListeners)
		{
			c.midtermAnnounced(e);
		}
	}
	
	public void postponeMidterm(Date date)
	{
		midterm = date;
		CourseEvent e = new CourseEvent(this);
		e.setMidtermDate(date);
		for(ICourseListener c : courseListeners)
		{
			c.midtermPostponed(e);
		}
	}

	public static void main(String[] args) 
	{

		Prof p = new Prof("Babak");
		Student s = new Student("Homer");
		Student s2 = new Student("Bart");
		TeachingAssistant ta = new TeachingAssistant("Michael");
	
		p.addCourseListener(s);
		p.addCourseListener(s2);
		p.addCourseListener(ta);
	
		Date midterm = new Date();
		p.setMidterm(midterm);
	
		p.postponeMidterm(new Date(midterm.getTime() + 1000000000));
	}

}
