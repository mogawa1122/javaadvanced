package util.bkup;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import survey.SurveyTags;
import util.GetTagInfo;
import util.TextPreparationSurveyBK;

public class Exe2 {

	public static void main(String[] args) {

   		SurveyTags stText = SurveyTags.valueOf("TEXT");
   		SurveyTags stNext = SurveyTags.valueOf("NEXT");
   		SurveyTags stSCJ = SurveyTags.valueOf("SINGLECHOICEJUMP");
   		SurveyTags stMC = SurveyTags.valueOf("MULTIPLECHOICE");
   		SurveyTags stEOF = SurveyTags.valueOf("EOF");
		
   		TextPreparationSurveyBK tps = new TextPreparationSurveyBK(); 
   		
   		// JSONオブジェクト定義
        ObjectMapper objectMapper = new ObjectMapper();
        // ルートJSONオブジェクトを作成
        ObjectNode rootNode = objectMapper.createObjectNode();

   		   
   	    int i=0;
   	    while (i < tps.getSize()) {
   	        // JSON子オブジェクトを作成
   	   		ObjectNode questionNode = objectMapper.createObjectNode();
   	    	
   	    	for(SurveyTags tagStatement:SurveyTags.values()) {
   	    	
   	   	    	if (tagStatement.equals(stEOF)) break; 
   	    		
   	    		String content = tps.getContent(i);
   	    		GetTagInfo tc = new GetTagInfo();
   	    		String tagContent = tc.getTagContent(tagStatement.toString(), content);
   	  
//   	    		System.out.println("tag:" + tagStatement.toString());
//   	    		System.out.println("tagContent:" + tagContent + "\n");
  
   	    		// オブジェクトを作成
   	    		questionNode.put(tagStatement.toString(), tagContent.replace("\n", ""));
   	    	    		

   	    		//入力テスト
//   	    		int next=0;
//   	    		DispCtrl dc = new DispCtrl();
//   	    		int returnNumber = dc.dispSentence(tagContent.toString(), next);
//   	    		System.out.println("return:" + returnNumber);

//   	    		if (tagName.equals(stText.toString())) {
//   					System.out.println("処理：text");
//   					String tagContent = tc.getTagContent(tagName, content);
//   					System.out.println("tagContent:" + tagContent);
//   	    		}
//   	    		
   	    		if (tagStatement.toString().equals(stSCJ.toString())) {
   					System.out.println("処理：singleChoiceJump");
   					GetTagInfo tcChild = new GetTagInfo();
   					String tagContentChild = tcChild.getTagContent(tagStatement.toString(), content);
   					System.out.println("tagContChild:" + tagContentChild);
   	    		}
//   	    		
//   	    		if (tagName.equals(stMC.toString())) {
//   					System.out.println("処理：multipleChoice");
//   	    		}
//   	    		
//   	    		if (tagName.equals(stNext.toString())) {
//   					System.out.println("処理：next");
//   					String tagContent = tc.getTagContent(tagName, content);
//   					System.out.println("tagContent:" + tagContent);
//   	    		}
//   	    		
//   	    		System.out.println("tag:" + tagStatement + " cont:" + tagContent );
//   	    		if (tagStatement.equals(stNext.toString()) && tagContent=="") {
//   					System.out.println("質問終了");
//   	    		}
   	 
   	    	}
   	    	
	        // "問題"オブジェクトをルートオブジェクトに追加
	        rootNode.set(Integer.valueOf(i).toString(), questionNode);
	        
	    	i++;
   		}
   	    
        // JSON文字列に変換
        String jsonString = null;
        try {
            jsonString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(rootNode);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 結果のJSON出力
//        System.out.println(jsonString);
        
        // ファイルへ書き出し
        try {
        	objectMapper.writeValue(new File("output.json"), rootNode);
//        	objectMapper.writeValue(new File("output.json"), jsonString);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
   	}


}

