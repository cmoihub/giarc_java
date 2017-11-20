package main;

public class Model {
	
	private String text;
	private String name;
	private String surname;
	
	public Model(){
		
	}
	
	/**
	 * 
	 * @param name_
	 */
	public void setName(String name_){
		name = name_;
	}
	
	/**
	 * 
	 * @param surname_
	 */
	public void setSurname(String surname_){
		surname = surname_;
	}
	
	/**
	 * 
	 * @param text_
	 */
	public void setText(String text_){
		text = text_;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getText(){
		return text;
	}

	/**
	 * 
	 * @param format
	 */
	public void reformat(String format)
	{
		switch(format){
		case "ACM":
			System.out.println("acm");
			setText(name.substring(0,1) + ". " + surname);
			break;
		case "IEEE":
			System.out.println("ieee");
			setText(name + " " + surname);
		default:
			break;
		}
	}
}
