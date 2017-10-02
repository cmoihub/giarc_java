import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class AddressBookController implements ActionListener
{

	private static final long serialVersionUID = 6569838723611182586L;
	private AddressBook book;
	
	public AddressBookController(AddressBook ab) 
	{
		System.out.println("CREATE CONTROLLER");
		book = ab;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) 
	{
		System.out.println(arg0.getActionCommand());
		if(arg0.getActionCommand() == "Delete")
		{
			
		}
		else if(arg0.getActionCommand() == "Edit")
		{
			
		}
	}

}
