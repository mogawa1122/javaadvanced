package tests;

import java.util.List;

import common.FileRead;

public class testFileRead {

	public static void main(String[] args) {
		
		FileRead fr = new FileRead();
		List<String> fileList = fr.fileNameFromFolder("survey");
		
//		for (String fileName : fileList) {
//			System.out.println(fr.fileContent(fileName));		
//		}

		System.out.println(fr.fileContent("survey", "0_1.txt"));
	}

}
