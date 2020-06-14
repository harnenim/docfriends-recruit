package test.docfriends.api.vo;

import java.util.Map;

public class QuestionVo extends Vo {

	private String title;
	private String content;
	private String tag;
	private String source;
	private int answerCount;
	
	@Override
	public Map<String, Object> toMap() {
		Map<String, Object> map = super.toMap();
		map.put("title", title);
		map.put("content", content);
		map.put("tag", tag);
		map.put("source", source);
		map.put("answerCount", answerCount);
		return map;
	}
	
}
