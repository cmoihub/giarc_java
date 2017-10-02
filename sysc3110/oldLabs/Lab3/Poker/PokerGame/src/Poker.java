import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author JaspreetSanghra
 * @studentId 100893501
 */
public class Poker 
{
	public static final String QUIT_OPTION = "--'q' to exit--";
	private ArrayList<Hand> hands;

	private int totalHands;

	public Poker()
	{
		hands = new ArrayList<Hand>();
	}

	/**
	 * 
	 */
	public void play() 
	{
		Scanner reader = new Scanner(System.in);

		int handsWanted = -1;
		while(handsWanted < 0)
		{
			System.out.println("How many hands are there?  " + QUIT_OPTION);

			try
			{
				String input = reader.nextLine();
				checkToStop(input);
				handsWanted = Integer.parseInt(input);
			}
			catch(Exception ex)
			{
				handsWanted = -1;
			}
		}

		addHands(handsWanted, reader);
		reader.close();
	}

	private void addHands(int handsWanted, Scanner reader) 
	{
		String handCode = "";
		for (int i = 1; i <= handsWanted; i++)
		{
			boolean validHand = false;
			while(!validHand)
			{
				try
				{
					System.out.println("Enter the cards for hand [" + i + "].  " + QUIT_OPTION);
					String input = reader.nextLine();
					checkToStop(input);
					handCode = input;
					addHand(handCode);
					validHand = true;
				}
				catch(Exception ex)
				{
					System.out.println();
					System.out.println(ex.getMessage());
					System.out.println();
					validHand = false;
				}
			}
		}
		totalHands = handsWanted;
		
		endGame();
	}

	private void addHand(String handCode) 
	{
		Hand currentHand = new Hand(handCode);
		hands.add(currentHand);
		System.out.println(" Hand Strength: " + currentHand.getKind());
	}

	private void checkToStop(String input) 
	{
		if(input.toLowerCase().equals("q"))
		{
			endGame();
		}
	}

	/**
	 * 
	 */
	private void endGame() 
	{
		System.out.println();
		System.out.println("Thanks for playing.");
		System.exit(0);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		Poker poker = new Poker();
		poker.play();
	

	}

}
