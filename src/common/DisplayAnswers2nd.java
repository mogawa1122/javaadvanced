package common;

import java.util.Formatter;
import java.util.Iterator;
import java.util.Map;

public class DisplayAnswers2nd {

	/**
	 *対象のワードが含まれた、区切り文字で囲まれた文字列を取得するメソッド
	 * @param resultArray[][] 	アンケート集計結果
	 * @return なし（アンケート結果を出力して終了）
	*/
	
    public void displayAnswers(Map<String, Integer> surveyFileNameMap, int resultArray[][]) {
    	
    	String firstKey="";
    	
        Iterator<String> keyIterator = surveyFileNameMap.keySet().iterator();
        if (keyIterator.hasNext()) {
            firstKey = keyIterator.next();
            System.out.println("先頭のキー: " + firstKey);
        }
    	
    	FileRead fr = new FileRead();
    	String content = fr.fileContent("survey", firstKey+".txt");
    	System.out.println(content);
    	
    	System.out.println("■アンケート集計結果\n");
    	Extraction ext = new Extraction();
    	String q1 = ext.extContent(content,"{text}","{/text");
    	System.out.println(q1.replace(",","\n"));
    	
//   		System.out.println("■アンケート集計結果\n");
//    	for (int i=0; i<QUESTIONS.length; i++) {
//    		System.out.println(QUESTIONS[i]);
//    		
//    		switch(i) {
//    		case 0:
//    			for (int j=0; j<SINGLE_CHOICES.length; j++) {
//    				System.out.println(formattedMessage(SINGLE_CHOICES[j], resultArray[i+1][j+1]));
//    			};
//    			System.out.println();
//    			break;
//    		case 1:
//    			for (int j=0; j<MULTI_CHOICES_GOOD.length; j++) {
//    				System.out.println(formattedMessage(MULTI_CHOICES_GOOD[j], resultArray[i+1][j+1]));
//    			};
//    			System.out.println();
//    			break;
//    		case 2:
//    			for (int j=0; j<MULTI_CHOICES_BAD.length; j++) {
//    				System.out.println(formattedMessage(MULTI_CHOICES_BAD[j], resultArray[i+1][j+1]));
//    			};
//    			System.out.println();
//    		}
//    	}
//    	System.out.println("\n以上");

    }
    
	/**
	 *対象のワードが含まれた、区切り文字で囲まれた文字列を取得するメソッド
	 * @param message 			アンケート選択肢
	 * @param count 			アンケート集計数
	 * @return formattedMessage フォーマット済アンケート結果
	*/
    
    public String formattedMessage(String message, int count) {

		Formatter formatter = new Formatter();
		formatter.format("\t%-20s\t%3d件", message, count);
		String formattedMessage = formatter.toString();
		formatter.close();
    	
    	return formattedMessage;
    }
}
