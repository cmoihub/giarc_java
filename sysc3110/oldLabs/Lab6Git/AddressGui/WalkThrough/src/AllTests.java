import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
	TestMovieListWithEmptyList.class, 
	TestMovieListWithOneMovie.class, 
	TestMovieListWithTwoMovies.class})
public class AllTests 
{


	public static void main(String[] args) 
	{
	}
}
