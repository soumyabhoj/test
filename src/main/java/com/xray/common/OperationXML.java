package com.xray.common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Enumeration;
import java.util.Properties;

import javax.swing.JOptionPane;

public class OperationXML {


	public static String filetoString(File inFile) {
		 String value = null;
		 try {

			 if (!inFile.isFile()) {
				 System.out.println("Parameter is not an existing file");
				 return null;
			 }
			 // String newFile = inFile.getAbsolutePath()+ inFile.getName();
			 //Construct the new file that will later be renamed to the original filename. 
			 File tempFile = new File(inFile + "_temp");

			 BufferedReader br = new BufferedReader(new FileReader(inFile));
			 PrintWriter pw = new PrintWriter(new FileWriter(tempFile));

			 String line = null;

			 String lineToRemove = "<apiCall";

			 //Read from the original file and write to the new 
			 //unless content matches data to be removed.
			 while ((line = br.readLine()) != null) {
				 if (line.trim().contains(lineToRemove)) {

					 String newline = line + "</apiCall>";
					 pw.write(newline);

					 pw.flush();
				 }
			 }

			 pw.close();
			 br.close();

			 value = new String(Files.readAllBytes(Paths.get(inFile+"_temp")), StandardCharsets.UTF_8);

			 //Delete the original file
			 if (!tempFile.delete()) {
				 System.out.println("Could not delete file");
				 return null;
			 } 

		 } catch (FileNotFoundException ex) {
          ex.printStackTrace();
        } catch (IOException ex) {
          ex.printStackTrace();
        }
    
    return value;
     }
	
	
}