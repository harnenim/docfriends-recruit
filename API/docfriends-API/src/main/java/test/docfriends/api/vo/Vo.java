package test.docfriends.api.vo;

import java.util.Map;

import org.apache.commons.collections.map.CaseInsensitiveMap;

import lombok.Data;

public abstract @Data class Vo {

	private long   key  ;
	private long   fuser;
	private long   fdate;
	private String fip  ;
	private long   luser;
	private long   ldate;
	private String lip  ;
	
	public Map<String, Object> toMap() {
		CaseInsensitiveMap map = new CaseInsensitiveMap();
		map.put("key", key);
		map.put("fuser", fuser);
		map.put("fdate", fdate);
		map.put("fip", fip);
		map.put("luser", luser);
		map.put("ldate", ldate);
		map.put("lip", lip);
		return map;
	}
	
}
