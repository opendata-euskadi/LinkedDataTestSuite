package es.eurohelp.ldts;

import java.util.HashMap;
import java.util.Map;

public class LinkedDataRequestBean {
	
	private String method = "";
	private String accept = "";
	private String baseUri = "";
	private String pathUri;
	private Map<String, String> parameters = new HashMap<String, String>();
	private int status;
	private int testIndex = 0;
	private int runningGroupID = 0;
	private String name = "";
	private String location = "";
	private String comment = "";
	private String responseString = "";
	

	public LinkedDataRequestBean(String method, String accept, String baseUri, String pathUri, String name, String comment,Map<String, String> parameters) {
		super();
		this.method = method;
		this.accept = accept;
		this.baseUri = baseUri;
		this.parameters = parameters;
		this.pathUri = pathUri;
		this.name = name;
		this.comment = comment;
	}
	
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getAccept() {
		return accept;
	}
	public void setAccept(String accept) {
		this.accept = accept;
	}
	
	public Map<String, String> getParameters() {
		return parameters;
	}
	public void setParameters(Map<String, String> parameters) {
		this.parameters = parameters;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
		
	public int getTestIndex() {
		return testIndex;
	}

	public void setTestIndex(int testIndex) {
		this.testIndex = testIndex;
	}

	public String getTestName(){
		return this.method + generateNameFromUrl(this.accept) + generateNameFromUrl(this.pathUri) + this.name; 
	}

	public String getCompleteUri(){
		return this.baseUri + this.pathUri;
	}
	
	private static String generateNameFromUrl(String url){

	    // Replace useless chareacters with UNDERSCORE
	    String uniqueName = url.replace("://", "_").replace(".", "_").replace("/", "_");
	    return uniqueName;
	}

	public String getResponseString() {
		return responseString;
	}

	public void setResponseString(String responseString) {
		this.responseString = responseString;
	}
	
	public int getRunningGroupID() {
		return runningGroupID;
	}

	public void setRunningGroupID(int runningGroupID) {
		this.runningGroupID = runningGroupID;
	}

	@Override
	public String toString() {
		return "LinkedDataRequestBean [name=" + name + "]";
	}
	
}
