package execute;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import aggregate.AggregateTags;
import aggregate.TextPreparationAggregate;
import common.DisplayAnswers;
import common.GetAnswer;

public class AggregateExe {

	public static void main(String[] args) {
	
   		String stItem = AggregateTags.valueOf("ITEM").toString();
   		String stSAns = AggregateTags.valueOf("SINGLEANSWER").toString();
   		String stMAns = AggregateTags.valueOf("MULTIANSWER").toString();
   		String stEOF = AggregateTags.valueOf("EOF").toString();
   		
   		// 変数定義
   		String target = "";
   		String content = "";
   		//配列要素を20で定義（0は使用しない）
   		int arrayRow = 21;
   		int arrayCol = 21;
   		
   		//回答データ設定
   		TextPreparationAggregate tpa = new TextPreparationAggregate();
   		//回答データ受け取り用Map
  		Map<String, ArrayList<String>> resultMap = new HashMap<String, ArrayList<String>>();
  		
  		//回答カウント用配列：行列の各要素20の配列を設定
  		int[][] resultArray = new int[arrayRow][arrayCol]; 
  		
		//対象データが無くなるまで繰り返す
		for (int i=0; i<tpa.getSize(); i++){
			//１件取得
			content = tpa.getContent(i);
			//回答別選択肢取得
			GetAnswer ga = new GetAnswer();
			resultMap = ga.getAnswer(content);
			
			//一人分のデータの回答項目を配列にカウントアップ
		    for (String item : resultMap.keySet()) {
		    	//回答Mapで問題番号（itemタグ）をkeyとしたvalueデータを取得
		        ArrayList<String> answerNo = new ArrayList<String>();
		        answerNo = resultMap.get(item);
	        
		        //回答部を取得し、カウント用配列でカウントアップ
		        String answerStr = answerNo.get(1);
		        //multiAnswerの場合は空白区切りで複数取得
		        String[] answers = answerStr.split(" ");
		        for (String answer : answers) {
		            resultArray[Integer.parseInt(item)][Integer.parseInt(answer)] += 1;
		        }
		    }
		}

		//集計結果表示
		DisplayAnswers da = new DisplayAnswers();
		da.displayAnswers(resultArray);
	}

}
