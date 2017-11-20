package tests;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import gui_mvc.*;
import main.Model;

public class ControllerTest {
	private Model model;
	private View view;
	private Controller controller;
	@Before
	public void setUp() throws Exception {
		model = new Model();
		view = new View(model);
		controller = new Controller(view, model);
	}

	@Test
	public void testControllerInitialization() {
		assert(controller!=null);
	}
	
	@Test
	public void testControllerView(){
		assert(controller.getView()!=null);
	}
	
	@Test
	public void testControllerModel(){
		assert(controller.getModel()!=null);
		
	}
}
