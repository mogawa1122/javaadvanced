package common;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputCheck {

	/**
	 *対象のワードが含まれた、区切り文字で囲まれた文字列を取得するメソッド
	 * @param inputSelect 入力された文字列。
	 * @param regex チェックする正規表現。
	 * @return checlResult 実行結果
	*/
	
	public boolean inputCheck(String inputSelect, String regex) {

		boolean checkResult = true;
		
		// 正規表現設定
	    Pattern patternSingle = Pattern.compile(regex);
		// 入力内容をマッチング
		Matcher matcher = patternSingle.matcher(inputSelect);
		
		// 内容チェック
		if (matcher.matches()) {
			checkResult = true;
		} else {
			checkResult = false;
		}
		
		return checkResult;
	}
}
