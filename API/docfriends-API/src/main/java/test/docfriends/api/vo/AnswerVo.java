package test.docfriends.api.vo;

import org.apache.commons.collections.map.CaseInsensitiveMap;

public class AnswerVo extends Vo {

	private long question;
	private String content;
	
	@Override
	public CaseInsensitiveMap toMap() {
		CaseInsensitiveMap map = super.toMap();
		map.put("question", question);
		map.put("content", content);
		return map;
	}
	
}
