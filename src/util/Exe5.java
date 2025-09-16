package util;

import execute.Extraction;
import survey.SurveyTags;

public class Exe5 {

	public static void main(String[] args) {

   		String stText = SurveyTags.valueOf("TEXT").toString();
   		String stNext = SurveyTags.valueOf("NEXT").toString();
   		String stSCJ = SurveyTags.valueOf("SINGLECHOICEJUMP").toString();
   		String stMC = SurveyTags.valueOf("MULTIPLECHOICE").toString();
   		String stEOF = SurveyTags.valueOf("EOF").toString();
   		
   		// 変数定義
   		String target = "";
   		String content = "";
   		int nextNo = 0;
   		
   		// データ準備
   		TextPreparationSurveyBK tps = new TextPreparationSurveyBK(); 
   		Extraction ext = new Extraction();
   		
   		// 最初の口上を取得
		target = tps.getContent(nextNo);

		// タグ間のコンテンツを取得
		content = ext.extContent(target, "{"+stText+"}", "{/"+stText+"}");
		System.out.println(content);
		
		content = ext.extContent(target, "{"+stNext+"}", "{/"+stNext+"}");
		nextNo = Integer.parseInt(content.replace("\n", ""));
		System.out.println(nextNo);
		
		target = tps.getContent(nextNo);
		content = ext.extContent(target, "{"+stText+"}", "{/"+stText+"}");
		System.out.println(content);
	}
}

