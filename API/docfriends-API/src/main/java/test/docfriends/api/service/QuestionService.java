package test.docfriends.api.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import test.docfriends.api.mapper.QuestionMapper;
import test.docfriends.api.vo.QuestionVo;

@Service
public class QuestionService {
	
	@Resource QuestionMapper mapper;

	public QuestionVo get(long key) {
		try {
			return mapper.get(key);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<QuestionVo> getList(int pagesize, int page) {
		try {
			return mapper.getList(page * pagesize, pagesize);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}


}
