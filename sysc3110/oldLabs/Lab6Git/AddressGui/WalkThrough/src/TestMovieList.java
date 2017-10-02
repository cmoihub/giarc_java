/*import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

*//**
 * Deprecated
 *//*
public class TestMovieList
{
	private MovieList movieList = null;
	private Movie starWars = null;
	private Movie starTrek = null;
	private Movie stargate = null;
	
	@Before
	public void setUp()
	{
		movieList = new MovieList();
		starWars = new Movie("Star Wars");
		starTrek = new Movie("Star Trek");
		stargate = new Movie("StarGate");
	}
	
	@Test
	public void testEmptyListSize() 
	{
		Assert.assertEquals("Size of empty movie list should be 0.", 0, movieList.size());
	}
	
	@Test
	public void testSizeAfterAddingOne() 
	{
		movieList.add(starWars);
		Assert.assertEquals("Size of one item list should be 1.", 1, movieList.size());
	}
	
	public void testSizeAfterAddingTwo()
	{
		movieList.add(starWars);
		movieList.add(starTrek);
		Assert.assertEquals("Size of a two item list should be 2.", 2, movieList.size());
	}
	
	@Test
	public void testContents() 
	{
		movieList.add(starWars);
		movieList.add(starTrek);
		Assert.assertTrue("List should contain starWars.", movieList.contains(starWars));
		Assert.assertTrue("List should contain starTrek.", movieList.contains(starTrek));
		Assert.assertFalse("List should not contain stargate.",
				movieList.contains(stargate));
	}
}
*/