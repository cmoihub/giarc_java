import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.*;

public class GuiBook  extends JFrame implements ActionListener
{
	private static final long serialVersionUID = 281173259372442797L;
	private AddressBook book;
	int id = 0;
	public GuiBook()
	{
		JFrame jf = new JFrame("AddressBook");
		jf.setSize(400, 400);
		JMenuBar bar = new JMenuBar();
		jf.setJMenuBar(bar);

		JMenu addressBook = new JMenu( "AddressBook" );
		bar.add( addressBook );
		JMenuItem item;
		item = new JMenuItem ( "Create" );
		item.addActionListener( this );
		addressBook.add(item);
		item = new JMenuItem ( "Save" );
		item.addActionListener( this );
		addressBook.add(item);
		item = new JMenuItem ( "Display" );
		item.addActionListener( this );
		addressBook.add(item);
		
		JMenu buddyInfo = new JMenu( "BuddyInfo" );
		bar.add( buddyInfo );
		item = new JMenuItem ( "Add" );
		item.addActionListener( this );
		buddyInfo.add(item);
		
		jf.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) 
	{
		if(arg0.getActionCommand() == "Create")
		{
			book = new AddressBook();
		}
		else if(arg0.getActionCommand() == "Save")
		{
			String s = book.toString();
			System.out.println(s);
			try 
			{
				BufferedWriter out = new BufferedWriter(new FileWriter("M:\\SYSC3110\\Labs\\Lab5\\addressBookText.txt"));
				out.write(s);
				out.flush();
				out.close();
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
		else if(arg0.getActionCommand() == "Display")
		{
			AddressBookView view = new AddressBookView(book);
		}
		else if(arg0.getActionCommand() == "Add")
		{
			String firstName = "";
			while(firstName == null || firstName == "")
			{
				firstName = (String)JOptionPane.showInputDialog(
	                    this,
	                    "Enter the first name\n",
	                    "Customized Dialog", JOptionPane.PLAIN_MESSAGE,
	                    null,
	                    null,
	                    "");
			}
			
			String lastName = "";
			while(lastName == null || lastName == "")
			{
				lastName = (String)JOptionPane.showInputDialog(
	                    this,
	                    "Enter the last name\n",
	                    "Customized Dialog", JOptionPane.PLAIN_MESSAGE,
	                    null,
	                    null,
	                    "");
			}
			
			book.AddBuddy(firstName, lastName, id);
			id++;
		}
		else
		{
			
			try 
			{
				throw new Exception("Command --" + arg0.getActionCommand() + "-- is not implemented.");
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}

		}
	}

}
