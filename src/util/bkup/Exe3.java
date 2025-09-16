package util.bkup;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

import survey.SurveyTags;
import util.GetTagInfo;
import util.TextPreparationSurveyBK;

public class Exe3 {

	public static void main(String[] args) {

   		SurveyTags stText = SurveyTags.valueOf("TEXT");
   		SurveyTags stNext = SurveyTags.valueOf("NEXT");
   		SurveyTags stSCJ = SurveyTags.valueOf("SINGLECHOICEJUMP");
   		SurveyTags stMC = SurveyTags.valueOf("MULTIPLECHOICE");
   		SurveyTags stEOF = SurveyTags.valueOf("EOF");
		
   		TextPreparationSurveyBK tps = new TextPreparationSurveyBK(); 
   		   
   	    int i=0;
   	    while (i < tps.getSize()) {
   	    	
   	    	try {
   	    		String content = tps.getContent(i);
//   	    		System.out.println("content: " + content);
   	    		BufferedReader br = new BufferedReader(new StringReader(content));
   	 
   	    		String line = br.readLine();
   	    		while (line != null) {
   	    			System.out.println(line);
   	    			
   	    			GetTagInfo tc = new GetTagInfo();
   	    			String tagContent = tc.getTagContent(line);
   	    			System.out.println("tagContent:" + tagContent);
   	    			
   	    			line = br.readLine();
   	    		}
   	    		br.close();
   	    	} catch (IOException e) {
   	    		System.out.println(e);
   	    	}
   	    	
   	    	
//   	    	for(SurveyTags tagStatement:SurveyTags.values()) {
//   	    	
//   	   	    	if (tagStatement.equals(stEOF)) break; 
//   	    		
//   	   	    	// データを１件読込
//   	    		String content = tps.getContent(i);
//   	    		
//   	    		
//   	    		
//   	    		GetTagInfo tc = new GetTagInfo();
//   	    		String tagContent = tc.getTagContent(tagStatement.toString(), content);
//   	  
//   	    		System.out.println("tag:" + tagStatement.toString());
//   	    		System.out.println("tagContent:" + tagContent + "\n");
  
   	    	    		

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
//   	    		if (tagStatement.toString().equals(stSCJ.toString())) {
//   					System.out.println("処理：singleChoiceJump");
//   					GetTagInfo tcChild = new GetTagInfo();
//   					String tagContentChild = tcChild.getTagContent(tagStatement.toString(), content);
//   					System.out.println("tagContChild:" + tagContentChild);
//   	    		}
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
//   	 
//   	    	}
//	        
	    	i++;
   		}
   	    

   	}


}

