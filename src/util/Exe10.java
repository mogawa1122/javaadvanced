package util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import execute.DisplayChoice;
import execute.Extraction;
import execute.InputCheck;
import survey.SurveyTags;
import survey.TextPreparationSurvey;

public class Exe10 {

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
   		
   		// 回答表示用リスト
   		ArrayList<String> answerList = new ArrayList<String>();
   		String answer = "";
   		
   		// 入力用クラス
		Scanner scanner = new Scanner(System.in);
   		
   		// データ準備
   		TextPreparationSurvey tps = new TextPreparationSurvey(); 
   		Extraction ext = new Extraction();
   		
   		// 質問リストの口上（contentList[0]データ）を取得
		target = tps.getContent(nextNo);

		// textタグのコンテンツを取得
		content = ext.extContent(target, "{"+stText+"}", "{/"+stText+"}");	
		System.out.println(content);
		// nextタグのコンテンツを改行コードを削除し取得
		nextString = ext.extContent(target, "{"+stNext+"}", "{/"+stNext+"}");
		nextNo = Integer.parseInt(nextString.replace("\n", ""));

		// EOFタグが取得されるまで繰り返す
		while (true) {
			
			// 次リスト番号のデータを取得
			target = tps.getContent(nextNo);
			
			// textタグのコンテンツを取得
			content = ext.extContent(target, "{"+stText+"}", "{/"+stText+"}");	
			System.out.println(content);
			answer = content;
			
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
   	    		

	    		// 選択肢出力
	    		DisplayChoice ds = new DisplayChoice();
	    		for (String key : ds.displayChoice(contentSCJ, "SCJ").keySet()) {
	    			ArrayList<String> dispSCJ = new ArrayList<String>();  
	    			dispSCJ = ds.displayChoice(contentSCJ, "SCJ").get(key);
//	    			System.out.println(dispSCJ.get(0) + " next:" + dispSCJ.get(1));
	    			System.out.println(dispSCJ.get(0));
	    		}
	    		System.out.println("\n選択肢を入力してくさい：");

	    		// singleChoiceJump入力チェック用正規表現を設定しチェック
	    		String regex = "[1-5]"; 
	    		InputCheck ic = new InputCheck(); 
	    		String inputSelect = scanner.next();

	    		// 正常に入力するまでループ			
	    		while (!ic.inputCheck(inputSelect, regex)) {
	    			System.out.println("入力エラーです。再入力してください：");
	    			inputSelect = scanner.next();
	    		}
	    		// 次の質問へのジャンプ番号を取得
	    		nextString = ds.displayChoice(contentSCJ, "SCJ").get(inputSelect).get(1);
				
	    		// 回答文を作成し表示用リストに保存
	    		answer = answer + "【回答】 "   
	    				 + ds.displayChoice(contentSCJ, "SCJ").get(inputSelect).get(0);
	    		answerList.add(answer);		 
   	    		
			// multipleChoice
			} else if (target.contains(stMC)) {
				
				// 重複チェック用ツール
	   	        Set<String> dupCheck = new HashSet<String>();
	   	        boolean isAddOK = true;

				// multipleChoiceの選択肢部を取得
	    		String contentMC = ext.extContent(target, "{multipleChoice}\n", "{/multipleChoice}");
				
	    		// 選択肢出力
				DisplayChoice ds = new DisplayChoice();
    			for (String key : ds.displayChoice(contentMC, "MC").keySet()) {
	    				ArrayList<String> dispMC = new ArrayList<String>();  
	    				dispMC = ds.displayChoice(contentMC, "MC").get(key);
    				System.out.println(dispMC.get(0));
	    		}
	    		
    			System.out.println("\n選択肢を入力してください（最大回答数3 例：1,2,3）");
    			
    			// multiChoice入力チェック用正規表現を設定しチェック
    		    String regex = "^[1-7{1}&,]{0,4}[1-7]{1}$";; 
    			InputCheck ic = new InputCheck(); 
    			String inputSelect = scanner.next();
    			
    			// 正常に入力するまでループ			
    			while (!ic.inputCheck(inputSelect, regex)) {
    				System.out.println("入力エラーです。再入力してください：");
    				inputSelect = scanner.next();
    			}
    			
    	        String[] splitAnswers = inputSelect.split(",");
    	        for (String splitAns : splitAnswers) {
    	    		// 回答文を作成
        	        isAddOK = dupCheck.add(splitAns);
//        	        System.out.println("dup: " + isDuplicate + "ans: " + splitAns);
        	        if (isAddOK) {
        	        	answer = answer + "【回答】 "   
        	        			+ ds.displayChoice(contentMC, "MC").get(splitAns).get(0) + "\n";
            	        isAddOK = true;
        	        }
    	        }
    	        
    	        // 回答表示用リストに追加
	        	answerList.add(answer);	


			}
			
			// nextタグのコンテンツを改行コードを削除し取得
			nextNo = Integer.parseInt(nextString.replace("\n", ""));
			answer = "";
			
		}
		scanner.close();
		
		// コンソール画面クリア
		System.out.print("\033[H\033[2J");
	    System.out.flush();
		
	    // 回答出力
		System.out.println("今回の回答です");
		answerList.forEach(ans -> System.out.println(ans));
		System.out.println("以上");
	}
}

