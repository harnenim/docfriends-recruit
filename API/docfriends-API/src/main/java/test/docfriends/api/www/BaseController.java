package test.docfriends.api.www;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.slf4j.Logger;
import org.springframework.web.bind.annotation.SessionAttributes;

@SessionAttributes("session")
public class BaseController {

	protected Logger logger;
	
	protected void init(Logger logger) {
		this.logger = logger;
	}
	
	protected String redirectUrl(String message) {
		try {
			return "redirect:/error.html?message=" + URLEncoder.encode(message, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return "redirect:/error.html?message=unknown";
		}
	}
}
