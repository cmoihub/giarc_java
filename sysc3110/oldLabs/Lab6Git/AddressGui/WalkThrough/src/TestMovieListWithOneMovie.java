import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestMovieListWithOneMovie 
{
	private MovieList movieList = null;
	private Movie starWars = null;

	@Before
	public void setUp()
	{
		starWars = new Movie("Star Wars");
		movieList = new MovieList();
		movieList.add(starWars);
	}

	@Test
	public void testSize() 
	{
		Assert.assertEquals("Size of one item list should be 1.", 1, movieList.size());
	}
}
