package gui_mvc;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.*;

import main.CitationFormat;
import main.Model;

public class View extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7533151960250587021L;
//	private JButton button1;
//	private JButton button2;
//	private JButton button3;
	
	private JPanel panel;
	private JPanel radioPanel;
	private JPanel namePanel;
	private JPanel surnamePanel;
	private JPanel textPanel;
	private JPanel container;
	
//	private JMenuBar menu;
	
	private JRadioButton ieee;
	private JRadioButton acm;
	
	private JTextField nameField;
	private JTextField surnameField;
	private JTextArea textArea;
	
	private JLabel nameLabel;
	private JLabel surnameLabel;
	
	public KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
	
	private Model model;
//	private static final String _ieee = "IEEE";
//	private static final String _acm = "ACM";
	
	private ButtonGroup radioButtons;
	public View(Model model_) {
		super("SYSC 3110 Lab Exam");
		model = model_;
		initializeSwing();
		
//		container.setLayout(new BoxLayout(container, BoxLayout.LINE_AXIS));
		container.add(panel);
		
		
//		menu.add(button1);
//		menu.add(button2);
//		menu.add(button3);
		
		getIeee().setMnemonic(KeyEvent.VK_0);
		getIeee().setActionCommand(CitationFormat.ieee.toString());
		
		getAcm().setMnemonic(KeyEvent.VK_1);
		getAcm().setActionCommand(CitationFormat.acm.toString());
		
		namePanel.add(nameLabel);
		namePanel.add(nameField);
		panel.add(namePanel);
		
		surnamePanel.add(surnameLabel);
		surnamePanel.add(surnameField);
		panel.add(surnamePanel);
		
		radioPanel.add(getIeee());
		radioPanel.add(getAcm());
		panel.add(radioPanel);
		
		textPanel.add(textArea);
		panel.add(textPanel);
		
//		this.setJMenuBar(menu);
		this.add(container);
		
//		setup(this);
	}
	
	/**
	 * initialize gui parts from the swing library
	 */
	private void initializeSwing(){
//		button1 = new JButton("button1");
//		button2 = new JButton("button2");
//		button3 = new JButton("button3");
		surnamePanel = new Panel(1,2);
		namePanel = new Panel(1,2);
		radioPanel = new Panel(1,2);
		textPanel = new Panel(1,2);
		panel = new Panel(4,2);
		container = new JPanel();
		
		nameField = new JTextField(20);
		surnameField = new JTextField(20);
		textArea = new JTextArea(5,20);
		textArea.setEditable(false);
		
		setIeee(new JRadioButton(CitationFormat.ieee.toString(), true));
		setAcm(new JRadioButton(CitationFormat.acm.toString()));
		
		radioButtons = new ButtonGroup();
		radioButtons.add(getIeee());
		radioButtons.add(getAcm());
		
		nameLabel = new JLabel("First Name");
		surnameLabel = new JLabel("Last Name");
//		menu = new JMenuBar();
	}
	
	/**
	 * this function applies desired settings to a jframe
	 *
	 * @param frame
	 */
	protected void setup(JFrame frame){
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(500,500);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	protected JTextField getSurnameField(){
		return surnameField;
	}
	
	protected JTextField getNameField(){
		return nameField;
	}
	
	protected void setTextArea(String text){
		textArea.setText(text);
	}
	
	protected void addIEEEActionListener(ActionListener listener){getIeee().addActionListener(listener);}
	protected void addACMActionListener(ActionListener listener){getAcm().addActionListener(listener);}
	protected void addNameActionListener(ActionListener listener){nameField.addActionListener(listener);}
	protected void addSurnameActionListener(ActionListener listener){surnameField.addActionListener(listener);}
	
	public JRadioButton getIeee() {
		return ieee;
	}

	public void setIeee(JRadioButton ieee) {
		this.ieee = ieee;
	}

	public JRadioButton getAcm() {
		return acm;
	}

	public void setAcm(JRadioButton acm) {
		this.acm = acm;
	}

	private class Panel extends JPanel{
		Panel(int rows, int columns){
			super(new GridLayout(rows, columns));
		}
	}
}