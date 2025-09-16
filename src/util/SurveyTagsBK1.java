package util;

public enum SurveyTagsBK1 {

	TEXT("text"),
	NEXT("next"),
	SINGLECHOICEJUMP("singleChoiceJump");

	private String tagName;

	private SurveyTagsBK1(String tagName){
		this.tagName = tagName;
	}

	public String getSurveyTagName(){
		return tagName;
	}
	
	@Override
	public String toString() {
		return name().toLowerCase().replace("choicejump", "ChoiceJump");
	}
	
}
