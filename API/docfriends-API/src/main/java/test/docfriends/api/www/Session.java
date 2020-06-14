package test.docfriends.api.www;

import org.apache.commons.collections.map.CaseInsensitiveMap;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import test.docfriends.encrypt.RSAUtil;
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
	
	private RSAUtil rsa = null;
	private int rsaRecallCount = 0;
	private Thread rsaClearer = null;
	
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
	
	public RSAUtil getRsa() {
		return rsa;
	}
	public void setRsa(RSAUtil rsa) {
		this.rsa = rsa;
	}
	public int getRsaRecallCount() {
		return rsaRecallCount;
	}
	public int setRsaRecallCount(int rsaRecallCount) {
		return this.rsaRecallCount = rsaRecallCount;
	}
	public int addRsaRecallCount() {
		return ++this.rsaRecallCount;
	}
	public Thread getRsaClearer() {
		return rsaClearer;
	}
	public void setRsaClearer(Thread rsaClearer) {
		this.rsaClearer = rsaClearer;
	}
	
}
