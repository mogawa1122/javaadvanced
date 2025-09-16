package util;

public enum AggregateTagsBK1 {

	ITEM("item"),
	SINGLEANSWER("singleAnswer"),
	MULTIANSWER("multiAnswer"),
	EOF("EOF");

	private String tagName;

	private AggregateTagsBK1(String tagName){
		this.tagName = tagName;
	}

	public String getAggregateTagName(){
		return tagName;
	}

	@Override
	public String toString() {
		return name().toLowerCase().replace("a", "A");
	}
	
}
