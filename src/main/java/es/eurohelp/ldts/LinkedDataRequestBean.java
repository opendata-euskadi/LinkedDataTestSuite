package es.eurohelp.ldts;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpEntity;

public class LinkedDataRequestBean {
	
	private String method = "";
	private String accept = "";
	private String baseUri = "";
	private String pathUri;
	private Map<String, String> parameters = new HashMap<String, String>();
	private int status;
	private String name = "";

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LinkedDataRequestBean(String method, String accept, String baseUri, String pathUri, String name, Map<String, String> parameters) {
		super();
		this.method = method;
		this.accept = accept;
		this.baseUri = baseUri;
		this.parameters = parameters;
		this.pathUri = pathUri;
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
		
	public String getTestName(){
		return this.method + generateNameFromUrl(this.accept) + generateNameFromUrl(this.pathUri); 
	}

	public String getCompleteUri(){
		return this.baseUri + this.pathUri;
	}
	
	private static String generateNameFromUrl(String url){

	    // Replace useless chareacters with UNDERSCORE
	    String uniqueName = url.replace("://", "_").replace(".", "_").replace("/", "_");
	    return uniqueName;
	}
	
}
