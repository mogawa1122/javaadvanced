package tests;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

public class testFileIn {
    public static void main(String[] args) {
        Path targetFile = Path.of("files/aggregate/0001.txt");
        // 処理終了後に自動的にStreamがclose()されるようにtry-with-resourcesを利用
        try (Stream<String> stream = Files.lines(targetFile, StandardCharsets.UTF_8)) {
            // 各行を[]で囲う
//            List<String> list = stream.map(line -> "[" + line + "]")
            List<String> list = stream.map(line -> line).toList();
            for (String line : list) {
                // 読み込んだ行に対して何らかの処理を行う
                // 今回はサンプルなので表示するだけ
                System.out.println(line);
            }
        } catch (IOException e) {
            // 業務コードでe.printStackTrace()を使うことはめったにありません。
            // 業務では各プロジェクトのルールに合わせて、例外の再スローやログの出力などを行ってください。
            e.printStackTrace();
        }

//    	Path p1 = Paths.get("");
//    	Path p2 = p1.toAbsolutePath();
//
//    	System.out.println(p2.toString());    
    }
}