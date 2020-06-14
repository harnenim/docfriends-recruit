package test.docfriends.api.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import test.docfriends.api.mapper.MemberMapper;
import test.docfriends.api.vo.MemberVo;

@Service
public class MemberService {
	
	@Resource MemberMapper mapper;

	// 가입 시 필요
//	public boolean isExistEmail(String email) {
//	}

	public MemberVo get(String email) {
		try {
			return mapper.get(email);
		} catch (Exception e) {
			return null;
		}
	}
	
	public MemberVo getByKey(long key) {
		try {
			return mapper.getByKey(key);
		} catch (Exception e) {
			return null;
		}
	}


}
