package test.docfriends.api.vo;

import java.util.Map;

public class AnswerVo extends Vo {

	private long question;
	private String content;
	
	@Override
	public Map<String, Object> toMap() {
		Map<String, Object> map = super.toMap();
		map.put("question", question);
		map.put("content", content);
		return map;
	}
	
}
