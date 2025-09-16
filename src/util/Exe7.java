package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import survey.SurveyTags;

public class Exe7 {

	public static void main(String[] args) {

   		String stText = SurveyTags.valueOf("TEXT").toString();
   		String stNext = SurveyTags.valueOf("NEXT").toString();
   		String stSCJ = SurveyTags.valueOf("SINGLECHOICEJUMP").toString();
   		String stMC = SurveyTags.valueOf("MULTIPLECHOICE").toString();
   		String stEOF = SurveyTags.valueOf("EOF").toString();
   		
   		// 変数定義
   		String target = "";
   		String content = "";
   		String nextString = "";
   		int nextNo = 0;
   		
		Scanner scanner = new Scanner(System.in);
   		
   		// データ準備
   		TextPreparationSurveyBK tps = new TextPreparationSurveyBK(); 
   		Extraction ext = new Extraction();
   		
   		// 質問リストの口上（contentList[0]データ）を取得
		target = tps.getContent(nextNo);

		// textタグのコンテンツを取得
		content = ext.extContent(target, "{"+stText+"}", "{/"+stText+"}");	
		System.out.println(content);
		// nextタグのコンテンツを改行コードを削除し取得
		nextString = ext.extContent(target, "{"+stNext+"}", "{/"+stNext+"}");
		nextNo = Integer.parseInt(nextString.replace("\n", ""));

		while (true) {
			
			// 次リスト番号のデータを取得
			target = tps.getContent(nextNo);
			
			// textタグのコンテンツを取得
			content = ext.extContent(target, "{"+stText+"}", "{/"+stText+"}");	
			System.out.println(content);
			
			// EOFタグがあればアンケート終了
			if (target.contains(stEOF)) {
				break;
			}
		
			// nextタグのコンテンツを取得
			nextString = ext.extContent(target, "{"+stNext+"}", "{/"+stNext+"}");
			
			// singleChoiceJump
			if (target.contains(stSCJ)) {
				
				// singleChoiceJumpの選択肢部を取得
	    		String contentSCJ = ext.extContent(target, "{singleChoiceJump}\n", "{/singleChoiceJump}");
   	    		
	    		// 1行ずつ読み込む準備
	    		BufferedReader br = new BufferedReader(new StringReader(contentSCJ));
	    		Map<String, ArrayList<String>> mapSCJ = new HashMap<String, ArrayList<String>>();

   	    		try {	    			
   	    			String line = br.readLine();
   	    			while (line != null) {
   	    				// 行先頭の次質問indexを取得
   	    				nextString = ext.extContent(line, "{", "}");  	    				
   	    				// 選択肢の表示部を取得
   	    				String textSCJ = ext.extContent(line, "}", "{/");
   	    				
   	    				// 選択肢Mapを作成（key:選択肢番号、value（key:
   	    				int branchIdx = textSCJ.indexOf(".");
   	    				String branch = textSCJ.substring(0, branchIdx);
   	    				ArrayList<String> listSCJ = new ArrayList<String>();
   	    				listSCJ.add(textSCJ);
   	    				listSCJ.add(nextString);
   	    				mapSCJ.put(branch, listSCJ);

   	    				line = br.readLine();
   	    			}
   	    			
   	    			// 選択肢出力
	    			for (String key : mapSCJ.keySet()) {
   	    				ArrayList<String> listSCJ = new ArrayList<String>();  
   	    				listSCJ = mapSCJ.get(key);
	    				System.out.println(listSCJ.get(0) + " next:" + listSCJ.get(1));
   	    			}
	    			System.out.println("入力：");
	    			String inputSelect = scanner.next();
	    			nextString = mapSCJ.get(inputSelect).get(1);
//	    			nextString = "3";
//	    			DispCtrl dc = new DispCtrl();
//	    			String inputSelect = dc.dispSentence("入力：");
//	    			nextString = mapSCJ.get(inputSelect).get(1);
				
   	    		} catch(IOException e) {
   	   	    		System.out.println(e);
   	    		}
   	    		
			// multipleChoice
			} else if (target.contains(stMC)) {
				// multipleChoiceの選択肢部を取得
	    		String contentMC = ext.extContent(target, "{multipleChoice}\n", "{/multipleChoice}");
				System.out.println(contentMC);
				
    			System.out.println("入力：");
    			String inputSelect = scanner.next();
    			System.out.println(inputSelect);
				
//    			DispCtrl dc2 = new DispCtrl();
//    			String inputSelect2 = dc2.dispSentence("入力2：");
//				System.out.println(inputSelect2);
			
			}
			
			// nextタグのコンテンツを改行コードを削除し取得
			nextNo = Integer.parseInt(nextString.replace("\n", ""));
			System.out.println("next: " + nextNo);
			
		}
		scanner.close();
		
	}
}

