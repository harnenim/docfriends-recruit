package test.docfriends.api.controller;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import test.docfriends.api.service.MemberService;
import test.docfriends.api.spring.RespMap;
import test.docfriends.api.vo.MemberVo;
import test.docfriends.api.www.BaseController;

@RestController
@RequestMapping("member/*")
public class MemberController extends BaseController {
	
	private MemberService service;
	
	@PostConstruct
	public void init() {
		service = memberService;
	}

	@PostMapping("login")
	public RespMap login(HttpServletRequest request
		,	@RequestParam(required = false) String email
		,	@RequestParam(required = false) String pw
			) {
		RespMap result = new RespMap();

		try {
			String ip = request.getRemoteAddr();
			MemberVo userInfo = service.login(email, pw, ip);
			
			if (userInfo == null) {
				return result.addError("로그인 실패");
			}
			
			result.setSuccess().put("info", userInfo.toMap());

		} catch(Exception e) {
			// 잘못된 요청
			e.printStackTrace();
		}
		
		return result;
	}
}
