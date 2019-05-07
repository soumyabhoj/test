package com.xray.jsonbuild;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xray.common.FileDetails;
import com.xray.common.OperationXML;

public class JSONRequest {
	
    public static String TEST_XML_STRING;

	public static void jsonRequest(File file) throws Exception {
		if(file.getName().endsWith(".testcases")) {
			
			TEST_XML_STRING = OperationXML.filetoString(file);
			TEST_XML_STRING =TEST_XML_STRING.replace("\\\\", "/");

			ObjectMapper jsonMapper = new ObjectMapper();
		    try {
		        JSONObject xmlJSONObj = XML.toJSONObject(TEST_XML_STRING);
		        
		        // read the transformed xml to json 
		        JSONArray jsonArray = xmlJSONObj.getJSONArray("apiCall");
		        XMLobject xmlObject = new XMLobject();

		        // create the json object for Steps 
		        Steps steps = new Steps();
		        StringBuffer genricDesc = new StringBuffer();
		        int index = 0;
		        for (int i=0; i < jsonArray.length(); i++) {
		        	xmlObject = jsonMapper.readValue(jsonArray.get(i).toString(), XMLobject.class);
		        	if (!xmlObject.getName().equals("Given") && !xmlObject.getName().equals("When") && !xmlObject.getName().equals("Then") && !xmlObject.getName().equals("And")) {
		        		Step step = new Step();
		        		step.setIndex(index);
		        		step.setStep(xmlObject.getName());
		        		step.setData(xmlObject.getTitle());
		        		step.setResult(xmlObject.getTitle());
		        		steps.getsteps().add(step);
		        		genricDesc.append(xmlObject.getTitle()).append("\n") ;
		            	index++;
		        	}

		        }
		        
		        Fields fields = new Fields();
		        if(FileDetails.getPropertyValueByKey("testCaseType").equals("Generic"))
		        	fields.setGenericStep(genricDesc.toString());	
		        else
		        	fields.setTestSteps(steps);
		        
		        String projectKey = FileDetails.getPropertyValueByKey("projectKey");
		        String issue= FileDetails.getPropertyValueByKey("issuetype");
		        String testCase=FileDetails.getPropertyValueByKey("testCaseType");
		        
		        Project project = new Project();
		        project.setKey(projectKey);
		        
		        IssueType issueType = new IssueType();
		        issueType.setName(issue);
		        
		        TestCaseTypeCustomField testCaseType = new TestCaseTypeCustomField();
		        testCaseType.setValue(testCase);
		        
		        fields.setSummary(file.getName().replace(".testcases", ""));
		        fields.setDescription(file.getName().replace(".testcases", ""));
		        
		        fields.setIssuetype(issueType);
		        fields.setTestCaseType(testCaseType);
		        fields.setProject(project);
		        String jsonInString = jsonMapper.writeValueAsString(fields);
		        jsonInString = jsonInString.replace("testCaseType", FileDetails.getPropertyValueByKey("TestCaseTypeCustomField")).replace("genericStep", FileDetails.getPropertyValueByKey("genericStepCustomField"));		     
		        XrayPostJson.sendPostRequest(jsonInString);
		      
		    } catch (JSONException je) {
		        je.printStackTrace();
		    } catch (JsonParseException e) {
				e.printStackTrace();
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}

}
