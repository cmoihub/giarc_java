package main;

public enum CitationFormat {
	ieee("IEEE"),acm("ACM");
	
	private String text;
	CitationFormat(String text_){
		text = text_;
	}
	
	public String toString(){
		return text;
	}
}
