package test.docfriends.api.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import test.docfriends.api.vo.AnswerVo;

@Mapper
public interface AnswerMapper {

	AnswerVo get(long key) throws Exception;
	List<AnswerVo> getListOfQuestion(long questionKey) throws Exception;
	
}
