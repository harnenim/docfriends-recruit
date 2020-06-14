package test.docfriends.api.spring;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RespMap extends HashMap<String, Object> {

	private static final long serialVersionUID = -3569898438281563889L;
	
	private List<String> error = new ArrayList<>();

	public RespMap() {
		put("error", error);
	}
	
	public RespMap addError(String message) {
		error.add(message);
		return this;
	}
	public boolean hasError() {
		return error.size() > 0;
	}
	
	public RespMap setSuccess() {
		put("success", true);
		return this;
	}
	
}
