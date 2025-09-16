package common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DisplayChoice {

	/**
	 *対象のワードが含まれた、区切り文字で囲まれた文字列を取得するメソッド
	 * @param content 	タグ間の選択肢がまとまった文字列。
	 * @param tag 		singleChoiceJumpタグかmultiChoiceタグかのパラーメーター。
	 * @return choiceMap 選択肢を分割したMap形式データ
	*/
	
	public Map<String, ArrayList<String>> displayChoice(String content, String tag) {
		
		// 1行ずつ読み込む準備
		String nextString = "";
		Extraction ext = new Extraction();
		BufferedReader br = new BufferedReader(new StringReader(content));
		Map<String, ArrayList<String>> choiceMap = new HashMap<String, ArrayList<String>>();

   		try {	    

   			String line = br.readLine();
   			while (line != null) {
   				
   				String textChoice = "";
   				
   				// singleChoiceJumpタグなら選択肢とnextを取得
   				if (tag.equals("SCJ")) {
   					// 行先頭の次質問indexを取得
   					nextString = ext.extContent(line, "{", "}");  	    				
   					// 選択肢の表示部を取得
   					textChoice = ext.extContent(line, "}", "{/");
   					
   				// multiChoiceならそのまま追加（nextが無い）
   				} else  if(tag.equals("MC")) {
   					// 入力値をそのまま表示
   					textChoice = line;
   				}
   				
   				// 選択肢Mapを作成（key:選択肢番号、value（配列[0:質問内容　1:next]))
   				int branchIdx = textChoice.indexOf(".");
   				String branch = textChoice.substring(0, branchIdx);

   				// value部の配列
   				ArrayList<String> list = new ArrayList<String>();
   				list.add(textChoice);
   				list.add(nextString);
   				// mapに追加
   				choiceMap.put(branch, list);

   				line = br.readLine();
   			}
   		} catch(IOException e) {
  	    		System.out.println(e);
   		}
   		
   		return choiceMap;
	}
	
}
