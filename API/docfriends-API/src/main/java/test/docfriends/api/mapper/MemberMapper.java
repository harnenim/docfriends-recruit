package test.docfriends.api.mapper;

import org.apache.ibatis.annotations.Mapper;

import test.docfriends.api.vo.MemberVo;

@Mapper
public interface MemberMapper {

	MemberVo get(String email) throws Exception;
	MemberVo getByKey(long key) throws Exception;
	
}
