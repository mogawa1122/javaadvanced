package tests;

public class ExeInput {

	public static void main(String[] args) {

		String content = "cccccc{text}回答を入力：{/text}bbbbbbb";
		
		String beginStr = "{" + "text";
		System.out.println("begin:" + beginStr);
		int beginIndex = content.indexOf(beginStr);
		System.out.println("beginIndex:" + beginIndex);
		
		String endStr = "/" + "text" + "}";
		System.out.println("end:" + endStr);
		int endIndex = content.indexOf(endStr);
		System.out.println("endIndex:" + endIndex);
		
		System.out.println("str: " + 
				content.substring(beginIndex+beginStr.length()+1, endIndex-1));

		//入力テスト
		String msg="回答を入力：";
		int next=0;
		DispCtrl dc = new DispCtrl();
		int returnNumber = dc.dispSentence(content, next);
		System.out.println("return:" + returnNumber);
		
	}

}
