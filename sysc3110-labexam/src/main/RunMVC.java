package main;

import gui_mvc.Controller;
import gui_mvc.View;

public class RunMVC {
	public static void main(String[] args)
	{
		Model model = new Model();
		View view = new View(model);
		Controller controller = new Controller(view, model);
		controller.start();
	}
}