package tests;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExtractionExe {

//    public static void main(String[] args) {
//        String text = "これは{start}"
//        		+ "抽出したい文字列"
//        		+ "{/start}"
//        		+ "です。また、"
//        		+ "{start}"
//        		+ "別の文字列"
//        		+ "{/start}"
//        		+ "も抽出できます。";
//        String startTag = "{start}";
//        String endTag = "{/start}";
//
//        // 正規表現パターンを作成
//        String regex = Pattern.quote(startTag) + "(.*?)" + Pattern.quote(endTag);
//        Pattern pattern = Pattern.compile(regex);
//        Matcher matcher = pattern.matcher(text);
//
//        // マッチする部分を抽出
//        while (matcher.find()) {
//            System.out.println(matcher.group(1)); // group(1)でタグ間の文字列を取得
//        }
//	if (Objects.isNull(content) || content.isEmpty()) break;
//    }
	
    public static void main(String[] args) {
        
		TextPreparationSurveyBK tps = new TextPreparationSurveyBK(); 
		String target = tps.getContent(4);
//		System.out.println(content);

//        String stEOF = "{EOF}";
//		if (target.contains(stEOF)) {
//			System.out.println("EOFだよ");
//		};
		
        String startTag = "{EOF}";
        String endTag = "{/EOF}";
		
        // 正規表現パターンを作成
        String regex = Pattern.quote(startTag) + "([\\s\\S]*?)" + Pattern.quote(endTag);
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(target);
//        System.out.println(regex);

        // マッチする部分を抽出
        while (matcher.find()) {
            System.out.println(matcher.group(1)); // group(1)でタグ間の文字列を取得
        }
    }

}
