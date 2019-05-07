package com.xray.common;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ReadFileNames {
	
	 public static List<File> readFileNames(File directory) throws IOException {

		
			List<File> fileList = getList(directory);
			for (File file : fileList) {
				System.out.println(file.getName());
				
			}
			
			return fileList;

	 }
	 
	 public static List<File> getList(File directory) 
	    {
	       File[] list = directory.listFiles();
	       final List<File> files = Arrays.stream(list).filter(t->t.isFile()).collect(Collectors.toList());
	       List<File> directories = Arrays.stream(list).filter(t->!t.isFile()).collect(Collectors.toList());
	       directories.stream().forEach(t->files.addAll(getList(t)));
	       return files;
	    }
}

