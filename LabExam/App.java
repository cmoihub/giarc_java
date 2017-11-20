import javax.swing.*;
import java.awt.GridLayout;
import java.awt.event.*;

@SuppressWarnings("serial")
public class App  extends JFrame implements ActionListener{
	private JRadioButton ieeeButton;
	private JRadioButton acmButton;
	private ButtonGroup group;
	private JTextField firstName;
	private JTextField lastName;

	private String newline = "\n";
	
	public App(){
		final JTextArea fullName = new JTextArea();
		
		ieeeButton = new JRadioButton("IEEE");
		acmButton = new JRadioButton("ACM");
		
		group = new ButtonGroup();
		group.add(ieeeButton);
		group.add(acmButton);
		
		firstName = new JTextField(20);
		lastName = new JTextField(20);
		
		this.setSize(400,200);
		this.setLayout(new GridLayout(4,2));
		//this.pack();
		JTextArea first = new JTextArea ("First Name");
		first.setEditable(false);
		JTextArea last = new JTextArea ("Last Name");
		last.setEditable(false);
		
		this.add(first);
		this.add(firstName);
		this.add(last);
		this.add(lastName);
		this.add(ieeeButton);
		this.add(acmButton);
		this.add(fullName);

		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//ieeeButton.setMnemonic(KeyEvent.VK_I);
		ieeeButton.setActionCommand("ieee");
		ieeeButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				fullName.setText(null);
				String initial = firstName.getText().substring(0,1) + ".";	
				fullName.append(initial + " " + lastName.getText());
			}
		}
				);
		
		//acmButton.setMnemonic(KeyEvent.VK_A);
		acmButton.setActionCommand("acm");
		acmButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				fullName.setText(null);
				fullName.append(firstName.getText() + " " + lastName.getText());
			}
		}
				);
		
		//You must hit enter after typing in these fields
		
		firstName.setActionCommand("first");
		firstName.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				JTextArea textArea = new JTextArea();
				String text = firstName.getText();
				textArea.append(text  + newline);
				lastName.selectAll();
			}
		}
				);
		
		lastName.setActionCommand("last");
		lastName.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				JTextArea textArea = new JTextArea();
				String text = firstName.getText();
				textArea.append(text  + newline);
				lastName.selectAll();
			}
		}
				);
		
	}
	
	public static void main(String[] args) 
	{
		System.out.println("Babak is a god");
		new App();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub	
	}
}
