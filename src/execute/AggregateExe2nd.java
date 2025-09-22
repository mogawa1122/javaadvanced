package execute;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import aggregate.AggregateTags;
import common.DisplayAnswers2nd;
import common.FileRead;
import common.GetAnswer;

public class AggregateExe2nd {

	public static void main(String[] args) {
	
   		String stItem = AggregateTags.valueOf("ITEM").toString();
   		String stSAns = AggregateTags.valueOf("SINGLEANSWER").toString();
   		String stMAns = AggregateTags.valueOf("MULTIANSWER").toString();
   		String stUAns = AggregateTags.valueOf("USERINPUT").toString();
   		String stEOF = AggregateTags.valueOf("EOF").toString();
   		
   		//　問題ファイル（surveyフォルダ）より問題番号一覧を取得
   		FileRead surveyFr = new FileRead();
   		List<String> surveyList = surveyFr.fileNameFromFolder("survey");

   		//　集計用配列への添字<->ファイル名変換
   		Map<String, Integer> surveyFileNameMap = new LinkedHashMap<String, Integer>();
   		int idx=0;
   		for (String surveyFileName : surveyList) {
   			// ファイル名の拡張子以外（質問番号）を取得しmapに設定
   			surveyFileNameMap.put(surveyFileName.substring(0,surveyFileName.lastIndexOf('.')), idx++);
   		}
   		
//   		for (String item : surveyFileNameMap.keySet()) {
//   			System.out.println("item:"+item+"getitem:"+surveyFileNameMap.get(item));
//   		}
//   		
   		//内容確認
   	    for (String key : surveyFileNameMap.keySet()) {
   	        System.out.println(key + ":" + surveyFileNameMap.get(key));
   	    }
   	    
   		//　問題ファイル（aggregateフォルダ）より問題番号一覧を取得
   		FileRead aggregateFr = new FileRead();
   		List<String> aggregateList = aggregateFr.fileNameFromFolder("aggregate");
   		
   		// 変数定義
   		String target = "";
   		String content = "";
   		//配列要素を50で定義（0は使用しない）
   		int arrayRow = 51;
   		int arrayCol = 51;
   		
   		//回答データ受け取り用Map
  		Map<String, ArrayList<String>> resultMap = new HashMap<String, ArrayList<String>>();
  		//回答カウント用配列：行列の各要素50の配列を設定
  		int[][] resultArray = new int[arrayRow][arrayCol]; 
  		
		//対象データが無くなるまで繰り返す
		for (int i=0; i<aggregateList.size(); i++){
			//１件取得
			content = aggregateFr.fileContent("aggregate", aggregateList.get(i)).replace(",", "\n");
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
		        //回答を集計用配列に設定
		        for (String answer : answers) {
		        	if (answerNo.get(0).equals("userInput")) {
		        	} else {
//		        		System.out.println("item:" + item+" "+surveyFileNameMap.get(item));
		        		resultArray[surveyFileNameMap.get(item)][Integer.parseInt(answer)] += 1;
		        	}
		        }
		    }
		}

        for (int j=0;j<10;j++) {
        	System.out.print("["+j+"]");
        	for (int k=0;k<10;k++) {
        		System.out.print(" " + resultArray[j][k]);
        	}
        	System.out.println();
        }
		
		//集計結果表示	
		DisplayAnswers2nd da2 = new DisplayAnswers2nd();
		da2.displayAnswers(surveyFileNameMap,resultArray);
	}

}
