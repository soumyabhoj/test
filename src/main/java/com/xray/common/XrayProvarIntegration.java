package com.xray.common;

import java.io.File;
import java.util.List;

import com.xray.jsonbuild.JSONRequest;

public class XrayProvarIntegration {

    
    public static void main(String[] args) throws Exception {
    	
    	String filePath = FileDetails.getPropertyValueByKey("filesPath");
    	File directory = new File(filePath);
    	List<File> fileList = ReadFileNames.readFileNames(directory);
    	
    	for (File file : fileList) {
			JSONRequest.jsonRequest(file);
				
		}
	
    }
   
}