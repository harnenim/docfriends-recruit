package test.docfriends.api.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.map.CaseInsensitiveMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import test.docfriends.api.www.Session;
import test.docfriends.api.service.AnswerService;
import test.docfriends.api.service.QuestionService;
import test.docfriends.api.spring.Params;
import test.docfriends.api.vo.AnswerVo;
import test.docfriends.api.vo.QuestionVo;
import test.docfriends.api.www.BaseController;

@RequestMapping("/question/**")
@RestController
public class QuestionController extends BaseController {

	@Autowired protected QuestionService service;
	@Autowired protected AnswerService answerService;
	
	private int pagesize = 10;
	
	@GetMapping("list")
	public List<CaseInsensitiveMap> list(Session session, Params params
			, @RequestParam(required = false, defaultValue = "0") int page
			) {
		List<CaseInsensitiveMap> result = new ArrayList<>();
		List<QuestionVo> list = service.getList(pagesize, page);
		for (QuestionVo item : list) {
			result.add(item.toMap());
		}
		return result;
	}
	
	@GetMapping("item/{key}")
	public CaseInsensitiveMap info(@PathVariable long key) {
		QuestionVo question = service.get(key);
		
		if (question == null) {
			return null;
		}
		
		CaseInsensitiveMap item = question.toMap();
		
		List<AnswerVo> listOfQuestion = answerService.getListOfQuestion(question.getKey());
		List<CaseInsensitiveMap> answerList = new ArrayList<>();
		for (AnswerVo answer : listOfQuestion) {
			answerList.add(answer.toMap());
		}
		item.put("answers", answerList);
		
		return item;
	}
	
}
