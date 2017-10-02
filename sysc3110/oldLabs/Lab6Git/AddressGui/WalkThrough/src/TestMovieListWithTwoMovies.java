import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestMovieListWithTwoMovies
{
	private MovieList movieList = null;
	private Movie starWars = null;
	private Movie starTrek = null;
	private Movie stargate = null;

	@Before
	public void setUp() 
	{
		starWars = new Movie("Star Wars");
		starTrek = new Movie("Star Trek");
		stargate = new Movie("StarGate");
		movieList = new MovieList();
		movieList.add(starWars);
		movieList.add(starTrek);
	}

	@Test
	public void testSizeAfterAddingTwo() 
	{
		Assert.assertEquals("Size of a two item list should be 2.", 2, movieList.size());
	}

	@Test
	public void testContents()
	{
		Assert.assertTrue("List should contain starWars.", movieList.contains(starWars));
		Assert.assertTrue("List should contain starTrek.", movieList.contains(starTrek));
		Assert.assertFalse("List should not contain stargate.",
				movieList.contains(stargate));
	}

}
