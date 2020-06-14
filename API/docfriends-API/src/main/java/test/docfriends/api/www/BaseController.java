package test.docfriends.api.www;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.SessionAttributes;

import test.docfriends.api.service.AnswerService;
import test.docfriends.api.service.MemberService;
import test.docfriends.api.service.QuestionService;

@SessionAttributes("session")
public class BaseController {

	@Autowired protected QuestionService questionService;
	@Autowired protected AnswerService answerService;
	@Autowired protected MemberService memberService;

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
