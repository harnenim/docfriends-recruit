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

	public MemberVo login(String email, String pw, String ip) {
		try {
			MemberVo member = mapper.get(email);
			if (member != null) {
				boolean isValid = member.isValidPw(pw);
				if (isValid) {
					return member;
				}
				// 로그인 성공 여부 logging
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
