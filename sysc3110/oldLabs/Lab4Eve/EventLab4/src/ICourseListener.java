
public interface ICourseListener 
{

	public default void midtermAnnounced(CourseEvent e)
	{}

	public default void midtermPostponed(CourseEvent e)
	{}
	
}