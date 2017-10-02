import java.util.Date;
import java.util.EventObject;

public class CourseEvent extends EventObject
{
	private static final long serialVersionUID = -2132486495472302457L;
	
	private Date midtermDate;
	
	public CourseEvent(Object obj) 
	{
		super(obj);
	}
	
	public Date getMidtermDate()
	{
		return midtermDate;
	}
	
	public void setMidtermDate(Date date)
	{
		midtermDate = date;
	}
	

}
