package survey;

public enum SurveyTags {

	TEXT,
	NEXT,
	SINGLECHOICEJUMP,
	MULTIPLECHOICE,
	EOF;
	
	@Override
	//enumは定数表示が基本なので小文字化を基本にタグ名化する
	public String toString() {
	
		String sTagName;
		if (name()=="EOF") {
			sTagName = "EOF";
		}
		else {
			sTagName = name().toLowerCase().replace("choice", "Choice").replace("jump", "Jump");
		}
	
		return sTagName;
		
	}
}
