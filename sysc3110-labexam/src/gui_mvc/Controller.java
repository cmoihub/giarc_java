package gui_mvc;

import java.awt.event.*;
import javax.swing.JFrame;

import main.CitationFormat;
import main.Model;

public class Controller {

	private View view;
	private Model model;
	public Controller(View view_, Model model_) {
		setView(view_);
		setModel(model_);
	}
	
	/**
	 * start program
	 */
	public void start(){
		addViewActionListeners();
		displayView();
	}

	/**
	 * set buttons of view to action listeners
	 */
	public void addViewActionListeners() {
		view.addIEEEActionListener(new IEEEActionListener());
		view.addACMActionListener(new ACMActionListener());
		view.addNameActionListener(new NameActionListener());
		view.addSurnameActionListener(new NameActionListener());
	}
	
	/**
	 * initialize view frame 
	 */
	public void displayView(){
		view.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		view.setSize(500,500);
		view.setLocationRelativeTo(null);
		view.setFocusable(true);
		view.setVisible(true);
	}

	public View getView() {
		return view;
	}

	public void setView(View view_) {
		view = view_;
	}
	
	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}
	
	private class NameActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String surname = view.getSurnameField().getText();
			String name = view.getNameField().getText();
			model.setName(name);
			model.setSurname(surname);
			model.setText(name + " " + surname);
			view.setTextArea(model.getText());
		}
	}
	
	private class ACMActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			model.reformat(CitationFormat.acm.toString());
			view.setTextArea(model.getText());
		}
	}
	
	private class IEEEActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {		
			model.reformat(CitationFormat.ieee.toString());
			view.setTextArea(model.getText());
		}
	}

}