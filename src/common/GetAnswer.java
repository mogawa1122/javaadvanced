package common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GetAnswer {

	/**
	 *対象のワードが含まれた、区切り文字で囲まれた文字列を取得し集計するメソッド
	 * @param content 	タグ間の選択肢がまとまった文字列。
	 * @return answerMap text番号ごとの回答のMap形式データ
	*/
	
	public Map<String, ArrayList<String>> getAnswer(String content) {
		
		// 1行ずつ読み込む準備
		String nextString = "";
		Extraction ext = new Extraction();
		BufferedReader br = new BufferedReader(new StringReader(content));
		
		//return用map （key:text番号、value（配列[0:タグ種類　1:タグ内容]))
		Map<String, ArrayList<String>> answerMap = new HashMap<String, ArrayList<String>>();

   		try {	    

   			String line = br.readLine();
   			while (line != null) {
   				
   				//タグがEOFなら処理終了
   				if (line.contains("EOF")) {
   					break;
   				}
   				
   				//タグ名を取得（itemタグ前提）
   				String tagText = ext.extContent(line, "{", "}");
   				//itemタグの番号を取得
   				String tagTextContent = ext.extContent(line, "{" + tagText + "}", "{/" + tagText + "/}");	
   				
   				//次のデータを取得
   				line = br.readLine();
   				
   				//タグ名を取得
   				String tagName = ext.extContent(line, "{", "}");
   				//タグ内容を取得
   				String tagContent = ext.extContent(line, "{" + tagName + "}", "{/" + tagName + "/}");	
   				

   				// value部の配列
   				ArrayList<String> list = new ArrayList<String>();
   				list.add(tagName);
   				list.add(tagContent);
   				// mapに追加（例：itemが1の選択肢がsingleAnswerで4のとき -> 1:[singleAnswer, 4]）
   				answerMap.put(tagTextContent, list);
   				
   				line = br.readLine();
   			}
   		} catch(IOException e) {
  	    		System.out.println(e);
   		}
   		
//   	    for (String key : answerMap.keySet()) {
//   	        System.out.println(key + ":" + answerMap.get(key));
//   	    }
   		
   	    //一人分の回答mapをreturn
   		return answerMap;
	}
	
}
