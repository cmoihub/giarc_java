import java.awt.List;
import java.util.ArrayList;
import java.util.Collections;

/**
 * 
 */

/**
 * @author JaspreetSanghra
 * @studentId 100893501
 */
public class Hand 
{
	public enum Kind 
	{
		HIGH_CARD, 
		PAIR, 
		TWO_PAIR, 
		THREE_OF_A_KIND, 
		STRAIGHT, 
		FLUSH, 
		FULL_HOUSE, 
		FOUR_OF_A_KIND, 
		STRAIGHT_FLUSH
	}
	
	private Kind HandKind;

	private ArrayList<Card> cards;

	public Hand(String handCode)
	{
		handCode = handCode.trim().replaceAll(" +", " ");
		String[] cardCodes = handCode.split(" ");
		if(cardCodes.length != 5)
		{
			throw new IllegalArgumentException("ERROR: Please provide a full hand of 5 cards, only [" + cardCodes.length + "] cards found in [" + handCode + "].");
		}
		cards = new ArrayList<Card>(5);
		addCards(cardCodes);
		HandKind = kind();
	}

	private void addCards(String[] cardCodes) 
	{
		for (String card : cardCodes) 
		{
			addCard(card);
		}
	}

	private void addCard(String cardCode) 
	{
		cards.add(new Card(cardCode));
	}

	/**
	 * This method is already implemented and could be useful! 
	 * @returns the "kind" of the hand: flush, full house, etc.
	 */
	public Kind kind() 
	{
		if (isStraight() && isFlush()) return Kind.STRAIGHT_FLUSH;
		else if (hasNKind(4)) return Kind.FOUR_OF_A_KIND; 
		else if (hasNKind(3) && hasNKind(2)) return Kind.FULL_HOUSE;
		else if (isFlush()) return Kind.FLUSH;
		else if (isStraight()) return Kind.STRAIGHT;
		else if (hasNKind(3)) return Kind.THREE_OF_A_KIND;
		else if (isTwoPair()) return Kind.TWO_PAIR;
		else if (hasNKind(2)) return Kind.PAIR; 
		else return Kind.HIGH_CARD;
	}

	public String getKind()
	{
		return HandKind.toString();
	}
	
	/**
	 * @returns true if the hand has n cards of the same rank
	 * e.g., "TD TC TH 7C 7D" returns True for n=2 and n=3, and False for n=1 and n=4
	 */
	protected boolean hasNKind(int n) 
	{
		int count = 0;
		for (Card card : cards) 
		{
			int rank = card.getRank();
			for (Card card2 : cards) 
			{
				int rank2 = card2.getRank();
				if(rank == rank2)
				{
					count++;
				}
			}
			if(count == n)
			{
				return true;
			}
			count = 0;
		}
		return false;
	}

	/**
	 * Optional: you may skip this one. If so, just make it return False
	 * @returns true if the hand has two pairs
	 */
	public boolean isTwoPair()
	{
		return false;
	}   

	/**
	 * @returns true if the hand is a straight 
	 */
	public boolean isStraight() 
	{
		ArrayList<Integer> list = handRanks();
		Collections.sort(list);
		int i = 0;
		int prev = -1;
		
		//go through first 4 cards
		for(i = 0; i < 4; i++)
		{
			if(prev != -1 && prev + 1 != list.get(i))
			{
				//order is not continuous
				return false;
			}
			prev = list.get(i);
		}
		//check special case for A,2,3,4,5 straight
		if(prev == 5 && list.get(i) == Card.AllRanks.get("A"))
		{
			return true;
		}
		else
		{
			//check last card is continuous
			return prev + 1 == list.get(i);
		}
	}

	/**
	 * @returns true if the hand is a flush
	 */
	public boolean isFlush() 
	{
		boolean allSameSuit = true;
		Card.ValidSuits suit = cards.get(0).getSuit();
		for (Card card : cards)
		{
			if(card.getSuit() != suit)
			{
				allSameSuit = false;
			}
		}
		return allSameSuit;
	}

	/*@Override
	public int compareTo(Hand h)
	{
		//hint: delegate!
		//and don't worry about breaking ties
		return 0;
	}*/

	private ArrayList<Integer> handRanks()
	{
		ArrayList<Integer> ranks = new ArrayList<Integer>(5);
		for (int i = 0; i < 5; i++) 
		{
			ranks.add(cards.get(i).getRank());
		}
		return ranks;
	}

}
