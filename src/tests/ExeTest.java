package tests;

import survey.SurveyTags;

public class ExeTest {

	public static void main(String[] args) {

   		SurveyTags stText = SurveyTags.valueOf("TEXT");
   		SurveyTags stNext = SurveyTags.valueOf("NEXT");
   		SurveyTags stSCJ = SurveyTags.valueOf("SINGLECHOICEJUMP");
   		SurveyTags stMC = SurveyTags.valueOf("MULTIPLECHOICE");
   		SurveyTags stEOF = SurveyTags.valueOf("EOF");
		
   		System.out.println("stText:" + stText.toString());
   		System.out.println("stNext:" + stNext.toString());
   		System.out.println("stSCJ:" + stSCJ.toString());
   		System.out.println("stMC:" + stMC.toString());
   		System.out.println("stEOF:" + stEOF.toString());
   		
   		
		//タグ取得テスト
		TextPreparationSurveyBK tps = new TextPreparationSurveyBK();
//    	System.out.println("size:"+tps.getSize());
//    	
//    	for(SurveyTags sTag : SurveyTags.values()) {
//    		System.out.println(sTag);
//    	}
//    	
//    	for(AggregateTags aTag : AggregateTags.values()) {
//    		System.out.println(aTag);
//    	}
//
//    	//enumからtext文字列を取得
//    	SurveyTags tx = SurveyTags.valueOf("TEXT");
//    	System.out.println("name:"+tx);
//    	
    	
//    	//入力リストの件数を取得
//    	for (int i=0; i<tps.getSize(); i++) {
//    		System.out.println("content:"+tps.getContent(i));
//    	}

//    	for (int i=0; i<tps.getSize(); i++) {
    	int i=0;
    	while (i<tps.getSize()) {
    	
    		//textタグの開始タグ位置を取得
    		String beginTextStr = stText.toString();
    		int beginTextIndex = tps.getContent(i).indexOf(beginTextStr);
//    		System.out.println("str:"+beginTextStr+" idx:"+beginTextIndex);

    		//	textタグの終了タグ位置を取得
    		String endTextStr = "/"+stText;
    		int endTextIndex = tps.getContent(i).indexOf(endTextStr) + endTextStr.length();
//    		System.out.println("str:"+endTextStr+" idx:"+endTextIndex);

    		//textタグの内容のみ出力
    		//"text}"分ポインタを5後ろに、"/text"分ポインタを6前に移動
//    		String textContent = tps.getContent(i).substring(beginTextIndex + 5, endTextIndex - 6);
//    		System.out.println("tps_text:"+textContent);
//
//    		String content = tps.getContent(i).substring(endTextIndex+1);
//    		GetTagInfo tc = new GetTagInfo();
//    		String tagName = tc.getTagName(content);
//    		System.out.println("tagname:" + tagName);
//    	
//    		if (tagName.equals(stSCJ.toString())) {
//				System.out.println("処理：singleChoiceJump");
//				String tagContent = tc.getTagContent(tagName, content);
//				System.out.println("tagContent:" + tagContent);
//    		}
//    		
//    		if (tagName.equals(stMC.toString())) {
//				System.out.println("処理：multipleChoice");
//    		}
//    		
//    		if (tagName.equals(stNext.toString())) {
//				System.out.println("処理：next");
//				String tagContent = tc.getTagContent(tagName, content);
//				System.out.println("tagContent:" + tagContent);
//    		}
//    		
//    		if (tagName.equals(stEOF.toString())) {
//				System.out.println("処理：EOF");
//    		}
// 
//    		i++;
    	}
	}

}

