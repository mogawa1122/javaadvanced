package util;

/**
 * タグの情報を取得するするクラス
 */
public class GetTagInfo {

	/**
	* タグ内容を取得
	* @param tagName	タグ名称
	* @param content		Dataの処理対象オブジェクト
	* @return tagContent	タグに囲まれた内容
	*/
	public String getTagContent(String content) {
		
		// タグ開始位置確認
		String beginStr = "{";
		int beginIndex = content.indexOf(beginStr);

		// タグ終了位置確認
		String endStr = "}";
		int endIndex = content.indexOf(endStr);
		
		// タグに囲まれたコンテンツを取得、タグが存在しないときはnullを返す
		String tagName = "";
		if ((beginIndex==-1) || (endIndex==-1)) {
			tagName = "";
		} else {
			tagName = content.substring(beginIndex+beginStr.length(), endIndex);
		}		
		
		return tagName;
	}


}
