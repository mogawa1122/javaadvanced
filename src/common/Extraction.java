package common;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Extraction {

	/**
	 *対象のワードが含まれた、区切り文字で囲まれた文字列を取得するメソッド
	 * @param target 全ての文字が含まれる文字列。
	 * @param startTag 区切りの始まりを示す文字。
	 * @param endTag 区切りの終わりを示す文字。
	 * @return content タブ間文字列 
	*/
	public String extContent(String target , String startTag , String endTag) {
	    	
		// タグ内容抽出の正規表現パターンを作成
		String regex = Pattern.quote(startTag) + "([\\s\\S]*?)" + Pattern.quote(endTag);
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(target);

		// マッチする部分を抽出
		String content = "";
		if (matcher.find()) {
			// group(1)でタグ間の文字列を取得
			content = matcher.group(1);
		}

		return content;
	}
}
