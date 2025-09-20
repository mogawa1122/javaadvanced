package common;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileRead {

    public List<String> fileNameFromFolder() {
    	
    	List<String> fileList = new ArrayList<String>();
    	Path folderPath = Paths.get("files/Survey/");

    	try (DirectoryStream<Path> surveyStream = Files.newDirectoryStream(folderPath)) {
    		for (Path file : surveyStream) {
    			if (Files.isRegularFile(file)) { 
    				fileList.add(file.getFileName().toString());
    			}
    		}
    	} catch (IOException e) {
    		e.printStackTrace();
    	} 
    	
        for(int i = 0; i < fileList.size(); i++) {
            System.out.println(fileList.get(i));
        }
    	
    	return fileList;
	}
    
    public String fileContent(String fileName) {
    	
    	String content = "";
    	
    	while (true) {
    		
    		Path targetFile = Path.of("files/survey/" + fileName);
    		try  {
//    			System.out.println(targetFile);
    			content = Files.readAllLines(targetFile, StandardCharsets.UTF_8).toString();
    		} catch (IOException e) {
    			e.printStackTrace();
    		}

    		return content;
    	}
    }
	
}
