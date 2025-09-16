package aggregate;

public enum AggregateTags {

	ITEM,
	SINGLEANSWER,
	MULTIANSWER,
	EOF;

	@Override
	//enumは定数表示が基本なので小文字化を基本にタグ名化する
	public String toString() {
		
		String aTagName;
		if (name()=="EOF") {
			aTagName = "EOF";
		}
		else {
			aTagName = name().toLowerCase().replace("answer", "Answer");
		}
		
		return aTagName;
	}
	
}
