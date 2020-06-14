package test.docfriends.api.vo;

import org.apache.commons.collections.map.CaseInsensitiveMap;

public class QuestionVo extends Vo {

	private String title;
	private String content;
	private String tag;
	private String source;
	private int answerCount;
	
	@Override
	public CaseInsensitiveMap toMap() {
		CaseInsensitiveMap map = super.toMap();
		map.put("title", title);
		map.put("content", content);
		map.put("tag", tag);
		map.put("source", source);
		map.put("answerCount", answerCount);
		return map;
	}
	
}
