package test.docfriends.api.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import test.docfriends.api.mapper.AnswerMapper;
import test.docfriends.api.mapper.QuestionMapper;
import test.docfriends.api.vo.AnswerVo;

@Service
public class AnswerService {
	
	@Resource AnswerMapper mapper;
	@Resource QuestionMapper questionMapper;

	public AnswerVo get(long key) {
		try {
			return mapper.get(key);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<AnswerVo> getListOfQuestion(long questionKey) {
		try {
			System.out.println("getListOfQuestion");
			return mapper.getListOfQuestion(questionKey);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/*
	public int insert() {
		...
		questionMapper.updateAnswerCount
		...
	}
	*/

}
