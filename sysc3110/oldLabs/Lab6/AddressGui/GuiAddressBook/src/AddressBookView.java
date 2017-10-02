import java.util.Observable;
import java.util.Observer;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class AddressBookView extends JFrame implements Observer 
{
	private static final long serialVersionUID = -3343910527353248785L;
	private AddressBook book;
	private JList<BuddyInfo> list;
	private JFrame frame;
	private AddressBookController controller;
	private BuddyInfo selectedValue;
	

	public AddressBookView(AddressBook ab) 
	{
		System.out.println("CREATE VIEW");
		book = ab;
		controller = new AddressBookController(book);
		frame = new JFrame();
		JMenuBar bar = new JMenuBar();
		frame.setJMenuBar(bar);
		JMenu buddyInfo = new JMenu( "BuddyInfo" );
		bar.add( buddyInfo );
		JMenuItem item;
		item = new JMenuItem ( "Delete" );
		item.addActionListener(controller);
		buddyInfo.add(item);
		item = new JMenuItem ( "Edit" );
		item.addActionListener(controller);
		buddyInfo.add(item);
		System.out.println("CREATE LIST");
        showList(book.ContactList);
	}

	private void showList(DefaultListModel<BuddyInfo> entries) 
	{
		DefaultListModel<BuddyInfo> listModel = entries;
 
        //create the list
        list = new JList<BuddyInfo>(listModel);
        list.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e)
		    {
		        if(!e.getValueIsAdjusting()) {
		        	selectedValue = (BuddyInfo) list.getSelectedValue();
		            System.out.println(selectedValue.toString());
		        }
		    }
		});
        
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        frame.add(list);
         
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setTitle("JList Example");       
        frame.setSize(400,400);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
	}

	@Override
	public void update(Observable arg0, Object arg1) 
	{
		System.out.println("UPDATING");

	}
	

}
