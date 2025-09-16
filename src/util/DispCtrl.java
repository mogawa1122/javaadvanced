package util;

import java.util.Scanner;

public class DispCtrl {
	
	/**
	* タグ内容を取得
	* @param diapSentence	表示文字列
	* @param nextNumber		入力質問番号
	* @return nextNumber	次の質問番号
	*/
	public String dispSentence(String dispSentence) {
		// 標準出力への表示
		System.out.println(dispSentence);

		// 標準入力からの読み取り
		Scanner scanner = new Scanner(System.in);
		String select = scanner.next();
		scanner.close();

		return select; 
	}
}