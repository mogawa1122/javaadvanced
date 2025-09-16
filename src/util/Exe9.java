package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import execute.Extraction;
import survey.SurveyTags;

public class Exe9 {

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
   	    				
   	    				// 選択肢Mapを作成（key:選択肢番号、value（配列[0:質問内容　1:next]))
   	    				int branchIdx = textSCJ.indexOf(".");
   	    				String branch = textSCJ.substring(0, branchIdx);
   	    				// value部の配列
   	    				ArrayList<String> listSCJ = new ArrayList<String>();
   	    				listSCJ.add(textSCJ);
   	    				listSCJ.add(nextString);
   	    				// mapに追加
   	    				mapSCJ.put(branch, listSCJ);

   	    				line = br.readLine();
   	    			}
   	    			
   	    			// 選択肢出力
	    			for (String key : mapSCJ.keySet()) {
   	    				ArrayList<String> dispSCJ = new ArrayList<String>();  
   	    				dispSCJ = mapSCJ.get(key);
	    				System.out.println(dispSCJ.get(0) + " next:" + dispSCJ.get(1));
   	    			}
	    			System.out.println("選択肢を入力してくさい：");
	    			
	    			// 入力チェック
	    		    String regex = "[1-5]"; 
	    			InputCheck ic = new InputCheck(); 
	    			String inputSelect = scanner.next();
	    			
	    			// 正常に入力するまでループ			
	    			while (!ic.inputCheck(inputSelect, regex)) {
//	    				System.out.println(inputSelect + " " + ic.inputCheck(inputSelect, regex));
	    				System.out.println("入力エラーです。再入力してください：");
	    				inputSelect = scanner.next();
	    			}
	    			// 次の質問へのジャンプ番号を取得
	    			nextString = mapSCJ.get(inputSelect).get(1);
				
   	    		} catch(IOException e) {
   	   	    		System.out.println(e);
   	    		}
   	    		
			// multipleChoice
			} else if (target.contains(stMC)) {
				// multipleChoiceの選択肢部を取得
	    		String contentMC = ext.extContent(target, "{multipleChoice}\n", "{/multipleChoice}");
				System.out.println(contentMC);
				
    			System.out.println("選択肢を入力してください（最大回答数3　例：1,2,3：");
    			
    			// 入力チェック
    		    String regex = "^[1-7{1}&,]{0,4}[1-7]{1}$";; 
    			InputCheck ic = new InputCheck(); 
    			String inputSelect = scanner.next();
    			
    			// 正常に入力するまでループ			
    			while (!ic.inputCheck(inputSelect, regex)) {
//    				System.out.println(inputSelect + " " + ic.inputCheck(inputSelect, regex));
    				System.out.println("入力エラーです。再入力してください：");
    				inputSelect = scanner.next();
    			}
			
			}
			
			// nextタグのコンテンツを改行コードを削除し取得
			nextNo = Integer.parseInt(nextString.replace("\n", ""));
//			System.out.println("next: " + nextNo);
			
		}
		scanner.close();
		
	}
}

