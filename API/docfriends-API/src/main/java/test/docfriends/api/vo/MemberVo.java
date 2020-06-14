package test.docfriends.api.vo;

import org.apache.commons.collections.map.CaseInsensitiveMap;

import lombok.Data;
import test.docfriends.encrypt.SHAUtil;

public @Data class MemberVo {

	private long   key  ;
	private String email;
	private String password;
	private String name;
	private String image;
	private long   fdate;
	private String fip  ;
	private long   luser;
	private long   ldate;
	private String lip  ;
	
	public boolean isValidPw(String pw) {
		System.out.println("isValidPw");
		String shaPw = SHAUtil.encodeBase64(pw, "docfriends");
		System.out.println(shaPw);
		return this.password.equals(shaPw);
	}
	
	public CaseInsensitiveMap toMap() {
		CaseInsensitiveMap map = new CaseInsensitiveMap();
		map.put("key", key);
		map.put("email", email);
//		map.put("password", password); 출력X
		map.put("name", name);
		map.put("image", image);
		map.put("fdate", fdate);
		map.put("fip", fip);
		map.put("luser", luser);
		map.put("ldate", ldate);
		map.put("lip", lip);
		return map;
	}
	
}
