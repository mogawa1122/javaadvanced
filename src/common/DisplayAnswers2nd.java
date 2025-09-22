package common;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Formatter;
import java.util.Iterator;
import java.util.Map;

import aggregate.AggregateTags;
import survey.SurveyTags;

public class DisplayAnswers2nd {

	/**
	 *対象のワードが含まれた、区切り文字で囲まれた文字列を取得するメソッド
	 * @param resultArray[][] 	アンケート集計結果
	 * @return なし（アンケート結果を出力して終了）
	*/
	
    @SuppressWarnings("unlikely-arg-type")
	public void displayAnswers(Map<String, Integer> surveyFileNameMap, int resultArray[][], ArrayList<String> userInputList) {
    	
  		String stSCJ = SurveyTags.valueOf("SINGLECHOICEJUMP").toString();
   		String stMC = SurveyTags.valueOf("MULTIPLECHOICE").toString();
   		String stUAns = AggregateTags.valueOf("USERINPUT").toString();
    	String stEOF = AggregateTags.valueOf("EOF").toString();
    	
    	// 変数定義
    	String fileNameKey="";
    	String content="";
    	System.out.println("■アンケート集計結果\n");
    	
    	// ファイル名がsurveyFileNameMapに無くなるまで内容を取得するループ
        Iterator<String> keyIterator = surveyFileNameMap.keySet().iterator();
        
        while (keyIterator.hasNext()) {
        	// surveyFileNameMapからファイル名を読込みフォルダ内のファイルの中身を取得
        	fileNameKey = keyIterator.next();
           	FileRead fr = new FileRead();
        	content = fr.fileContent("survey", fileNameKey+".txt");
        	
        	// fileNameから集計配列の添え字を取得
        	int answerNo = surveyFileNameMap.get(fileNameKey);
        	
        	// fileNameの先頭が0（例：0_1）の場合は集計対象ではないので読み飛ばす
        	String questionClass[] = fileNameKey.split("_");
        	if(questionClass[0].equals("0")) {
        		continue;
        	}
        	
        	//　EOFタグの存在するファイルは読み飛ばす
            if (content.contains(stEOF)) {
            	break;
            }
            Extraction ext = new Extraction();
        	String question = ext.extContent(content,"{text}","{/text");
        	System.out.println(question.replace(",","\n"));
        	
    		// 選択肢出力
    		DisplayChoice ds = new DisplayChoice();
    		
    		// singleChoiceJump項目出力
    		if (content.contains(stSCJ)) {
            	int i=1;
    			String questionDetail = ext.extContent(content,"{" + stSCJ + "}","{/" + stSCJ + "}");
            	
    			for (String key : ds.displayChoice(questionDetail, "SCJ").keySet()) {
    				ArrayList<String> dispSCJ = new ArrayList<String>();  
    				dispSCJ = ds.displayChoice(questionDetail, "SCJ").get(key);
    				
    				// 割合計算
    				int row4Sum[] = Arrays.copyOf(resultArray[answerNo], resultArray[answerNo].length);
    				int sum=0;
    				for (int r : row4Sum) {
    					sum += r;
    				}
    				double ratio = 0;
    				if (sum != 0) {
    					ratio =  Math.floor(100*resultArray[answerNo][i]/sum);
    				}
    				// 回答数を取得し表示
    				System.out.println(formattedMessage("SCJ", dispSCJ.get(0),  resultArray[answerNo][i++], ratio));
    			}
    		}
    		
    		// multiChoice項目出力
    		if (content.contains(stMC)) {
    			int j=1;
            	String questionDetail = ext.extContent(content,"{" + stMC + "}","{/" + stMC + "}");
    			for (String key : ds.displayChoice(questionDetail, "MC").keySet()) {
    				ArrayList<String> dispSCJ = new ArrayList<String>();  
    				dispSCJ = ds.displayChoice(questionDetail, "MC").get(key);
    				
    				// 割合計算
    				int row4Sum[] = Arrays.copyOf(resultArray[answerNo], resultArray[answerNo].length);
    				int sum=0;
    				for (int r : row4Sum) {
    					sum += r;
    				}
    				double ratio = 0;
    				if (sum != 0) {
    					ratio =  Math.floor(100*resultArray[answerNo][j]/sum);
    				}
    				
    				// 回答数を取得し表示
    				System.out.println(formattedMessage("MC", dispSCJ.get(0),  resultArray[answerNo][j++], ratio));
    			}
    		}
    		
    		if (content.contains(stUAns)) {
    			for (String comment : userInputList) {
    				System.out.println(comment);
    			}
    		}
    		
    		System.out.println();
        }
        System.out.println("\n以上");

    }
    
    
    
	/**
	 *対象のワードが含まれた、区切り文字で囲まれた文字列を取得するメソッド
	 * @param st	 			アンケートタグ（singleChoiceJump or multiChoice）
	 * @param message 			アンケート選択肢
	 * @param count 			アンケート集計数
	 * @param ratio 			アンケート集計割合
	 * @return formattedMessage フォーマット済アンケート結果
	*/
    
    public String formattedMessage(String st, String message, int count, double ratio) {


		DecimalFormat countFmt = new DecimalFormat("#,###");
		String stCount = countFmt.format(count).toString();
		
		DecimalFormat ratioFmt = new DecimalFormat("0.###");
		String stRatio = ratioFmt.format(ratio).toString();
		
		Formatter formatter = new Formatter();
		String stChoice = "";
		if (st.equals("SCJ")) {
			stChoice = formatter.format("%-15s", message).toString();
		}
		if (st.equals("MC")) {
			stChoice = formatter.format("%-25s", message).toString();	
		}
		formatter.close();
    	
		String formattedMessage = "\t" + stChoice + "\t" + stCount + "件" + "\t" + stRatio + "%";
    	return formattedMessage;
    }
    
    
    //未使用（使用しても文字数は合うが表示ずれが生じる）
	/**
	 *対象のワードが含まれた、区切り文字で囲まれた文字列を取得するメソッド
	 * @param target 			文字列
	 * @param length 			固定長
	 * @param padChar 			右埋め文字
	 * @return formattedMessage フォーマット済アンケート結果
	*/
    public static String padRight(String target, int length, char padChar) {
    	
        int targetLength = target.length();
        
        // 文字長>=固定長の場合
        if (targetLength >= length) {
            return target;
        }
        
        // 文字数<固定長の場合：指定文字で埋める
        StringBuilder sb = new StringBuilder(length);
        sb.append(target);
        for (int i = targetLength; i < length; i++) {
            sb.append(padChar);
        }

        return sb.toString();
    }
}
