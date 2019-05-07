package com.xray.jsonbuild;

public class Fields {

    public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public IssueType getIssuetype() {
		return issuetype;
	}
	public void setIssuetype(IssueType issuetype) {
		this.issuetype = issuetype;
	}
	public TestCaseTypeCustomField getTestCaseType() {
		return testCaseType;
	}
	public void setTestCaseType(TestCaseTypeCustomField testCaseType) {
		this.testCaseType = testCaseType;
	}
	public Steps getTestSteps() {
		return testSteps;
	}
	public void setTestSteps(Steps testSteps) {
		this.testSteps = testSteps;
	}
	public String getGenericStep() {
		return genericStep;
	}
	public void setGenericStep(String genericStep) {
		this.genericStep = genericStep;
	}
	private Project project;
    private String summary;
    private String description;
    private IssueType issuetype;
    private TestCaseTypeCustomField testCaseType;             
    private Steps testSteps;
    private String genericStep;
    
	
}
