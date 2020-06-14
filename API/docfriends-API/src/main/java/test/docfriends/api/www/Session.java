package test.docfriends.api.www;

import org.apache.commons.collections.map.CaseInsensitiveMap;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import test.docfriends.api.vo.MemberVo;

/**
 * 
 * Session.java
 *
 * <pre>
 * 사용자 세션
 * </pre>
 *
 * @author 송규호 
 * @date 2019. 4. 9.
 */
@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Session {
	
	private MemberVo userInfo = null;
	private CaseInsensitiveMap map = new CaseInsensitiveMap();
	
	public MemberVo getUserInfo() {
		return userInfo;
	}
	public void setUserInfo(MemberVo userInfo) {
		this.userInfo = userInfo;
	}
	
	public Object get(String key) {
		return map.get(key);
	}
	public void put(String key, Object value) {
		map.put(key, value);
	}
	
}
