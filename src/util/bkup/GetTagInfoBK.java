package util.bkup;

/**
 * タグの情報を取得するするクラス
 */
public class GetTagInfoBK {

	/**
	* タグ内容を取得
	* @param tagName	タグ名称
	* @param content		Dataの処理対象オブジェクト
	* @return tagContent	タグに囲まれた内容
	*/
	public String getTagContent(String tagStatement, String content) {
		
		// タグ開始位置確認
		String beginStr = "{" + tagStatement;
		int beginIndex = content.indexOf(beginStr);

		// タグ終了位置確認
		String endStr = "/" + tagStatement + "}";
		int endIndex = content.indexOf(endStr);
		
		// タグに囲まれたコンテンツを取得、タグが存在しないときはnullを返す
		String tagContent = "";
		if ((beginIndex==-1) || (endIndex==-1)) {
			tagContent = "";
		} else {
			tagContent = content.substring(beginIndex+beginStr.length()+1, endIndex-1);
		}		
		
		return tagContent;
	}


}
