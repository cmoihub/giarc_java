
import java.util.*;

import javax.swing.DefaultListModel;


public class AddressBook extends Observable
{
	public DefaultListModel<BuddyInfo> ContactList;

	public AddressBook()
	{
		ContactList = new DefaultListModel<BuddyInfo>();
	}

	public boolean AddBuddy(String firstName, String lastName, int id)
	{
		if(FindIndex(firstName) == -1 )
		{
			ContactList.addElement(new BuddyInfo(firstName, lastName, id));
			return true;
		}
		return false;
	}

	public boolean AddBuddy(BuddyInfo buddy)
	{
		if(FindIndex(buddy.GetFirstName()) == -1 )
		{
			ContactList.addElement(buddy);
			return true;
		}
		return false;
	}

	public boolean RemoveBuddy(String firstName)
	{
		int removeIndex = FindIndex(firstName);
		if(removeIndex != -1)
		{
			ContactList.remove(removeIndex);
			setChanged();
			notifyObservers();
			return true;
		}
		else
		{
			return false;
		}
	}

	public boolean RemoveBuddy(int index)
	{
		if(index >= 0 && index < ContactList.size())
		{
			ContactList.remove(index);
			setChanged();
			notifyObservers();
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public boolean RemoveBuddyById(int id)
	{
		for (int i = 0; i < ContactList.size(); i++) 
		{
			BuddyInfo buddy = ContactList.get(i);
			if(buddy.GetId() == id)
			{
				RemoveBuddy(i);
				setChanged();
				notifyObservers();
				return true;
			}
		}
		return false;
	}

	public int NumberOfContacts()
	{
		return ContactList.size();
	}

	private int FindIndex(String firstName)
	{
		for (int i = 0; i < ContactList.size(); i++) 
		{
			BuddyInfo buddy = ContactList.get(i);
			if(buddy.GetFirstName().equalsIgnoreCase(firstName))
			{
				return i;
			}
		}
		return -1;
	}
	
	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < ContactList.size(); i++) 
		{
			
			sb.append(ContactList.getElementAt(i).toString());
		}
		return sb.toString();
	}


	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		GuiBook gui = new GuiBook();
	}
}
