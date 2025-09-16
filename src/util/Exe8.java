package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import execute.Extraction;
import survey.SurveyTags;

public class Exe8 {

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
   	    				ArrayList<String> listSCJ = new ArrayList<String>();  
   	    				listSCJ = mapSCJ.get(key);
	    				System.out.println(listSCJ.get(0) + " next:" + listSCJ.get(1));
   	    			}
	    			System.out.println("入力：");
	    			
	    			// 入力例の正規表現設定
	    		    String regexSingle = "[1-5]"; 
	    		    Pattern patternSingle = Pattern.compile(regexSingle);
	    			
	    			String inputSelect = scanner.next();
	    			
	    			// 入力内容をマッチング
	    			Matcher matcherSingle = patternSingle.matcher(inputSelect);
	    			if (matcherSingle.matches()) {
	    				System.out.println(inputSelect + " OK");
		    			nextString = mapSCJ.get(inputSelect).get(1);
	    			} else {
	    				System.out.println(inputSelect + " NG");
	    			}
				
   	    		} catch(IOException e) {
   	   	    		System.out.println(e);
   	    		}
   	    		
			// multipleChoice
			} else if (target.contains(stMC)) {
				// multipleChoiceの選択肢部を取得
	    		String contentMC = ext.extContent(target, "{multipleChoice}\n", "{/multipleChoice}");
				System.out.println(contentMC);
				
    			System.out.println("入力（最大回答数3　例：1,2,3：");
    			
    			// 入力例の正規表現設定
    		    String regexMulti = "^[1-7{1}&,]{0,4}[1-7]{1}$"; 
    		    Pattern patternMulti = Pattern.compile(regexMulti);
    			
    			String inputSelect = scanner.next();
    			
    			// 入力内容をマッチング
    			Matcher matcherMulti = patternMulti.matcher(inputSelect);
    			if (matcherMulti.matches()) {
    				System.out.println(inputSelect + " OK");
    			} else {
    				System.out.println(inputSelect + " NG");
    			}
			
			}
			
			// nextタグのコンテンツを改行コードを削除し取得
			nextNo = Integer.parseInt(nextString.replace("\n", ""));
			System.out.println("next: " + nextNo);
			
		}
		scanner.close();
		
	}
}

