import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.*;

import javax.swing.*;

public class GuiBook  extends JFrame implements ActionListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private AddressBook book;
	int id = 0;
	private JFrame jf;
	private JMenuBar bar;
	private JMenuItem item;
	JMenu addressBook;
	JMenu buddyInfo;
	JPanel jp;
	public Gui()
	{
		jp = new JPanel();
		
		initFrame("AddressBook");
		initMenuBar();
		jp.setLayout(new FlowLayout());
		jf.add(jp);

		addressBook = initMenu("AddressBook");
			
		buddyInfo = initMenu("BuddyInfo");
		
	}
	
	void setupGUI() {
		setupAddressBook();

		setupBuddyInfo();
		
		jf.setVisible(true);
	}
	
	private void setupAddressBook() {
		addItem("Create", addressBook, this);
		addItem("Save", addressBook, this);
		addItem("Display", addressBook, this);
	}
	
	private void setupBuddyInfo() {
		addItem("Add", buddyInfo, this);
	}

	
	private void initFrame(String frameName) {
		jf = new JFrame(frameName);
		jf.setSize(400, 400);
		jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
	}
	
	private void initMenuBar() {
		bar = new JMenuBar();
		jf.setJMenuBar(bar);
	}
	
	private void addItem(String itemName, JMenu menu, Gui gui) {
		item = new JMenuItem (itemName);
		item.addActionListener( this );
		menu.add(item);
	}

	private JMenu initMenu(String menuName) {
		if(bar == null) initMenuBar();
		JMenu menu = new JMenu(menuName);
		bar.add( menu);
		return menu;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) 
	{
		System.out.println(arg0.getActionCommand());
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
				BufferedWriter out = new BufferedWriter(new FileWriter("M:\\SYSC3110\\Lab4\\addressBookText.txt"));
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
			JTextArea textArea = new JTextArea(5, 5);
			JScrollPane scrollPane = new JScrollPane(textArea); 
			textArea.setEditable(false);
			
			textArea.append(book.toString());
			textArea.setVisible(true);
			scrollPane.setVisible(true);
			jp.add(textArea);
			jf.setVisible(true);
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
