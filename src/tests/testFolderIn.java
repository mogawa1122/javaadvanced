package tests;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class testFolderIn {
    public static void main(String[] args) {
    	 Path folderPath = Paths.get("files/Survey/");

        try (DirectoryStream<Path> surveyStream = Files.newDirectoryStream(folderPath)) {
            for (Path file : surveyStream) {
                // 各ファイルに対して行う処理をここに記述
                if (Files.isRegularFile(file)) { 
                    System.out.println(file.getFileName());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } 
    }
}