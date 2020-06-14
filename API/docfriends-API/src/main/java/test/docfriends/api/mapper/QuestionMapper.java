package test.docfriends.api.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import test.docfriends.api.vo.QuestionVo;

@Mapper
public interface QuestionMapper {

	QuestionVo get(long key) throws Exception;
	List<QuestionVo> getList(int pagestart, int pagesize) throws Exception;
	
}
