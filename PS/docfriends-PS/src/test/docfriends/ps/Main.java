package test.docfriends.ps;

public class Main {
	
	/*
	 * 숫자와 문자가 섞인 q라는 문자열이 있는데
	 * 해당 문자열에서 숫자의 배열을 numbers라고 할때
	 * 가장 작은 숫자가 numbers의 a번째, 가장 큰 숫자가 numbers의 b번째에 있다고 한다.
	 * a + b의 합을 구하여라.
	 */
	public static int solve(String q) {
		int min = Integer.MAX_VALUE, max = -1; // int max는 넘어가지 않으리라 가정
		int minPos = 0, maxPos = 0;
		
		int pos = 0; // 0번째부터 시작?
		int now = 0;
		boolean wasNum = false; // 방금 전이 숫자였는지 여부
		
		// 마지막이 숫자로 끝날 경우 처리를 위해, 뒤에 문자 하나 붙임
		char last = q.charAt(q.length() - 1);
		if (last >= '0' && last <= '9') {
			q += "z";
		}
		
		for (char c : q.toCharArray()) {
			if (c >= '0' && c <= '9') {
				// 숫자일 경우
				if (!wasNum) {
					wasNum = true;
					
				}
				now = 10 * now + (c - '0');
				
			} else {
				// 문자일 경우
				if (wasNum) {
					// 숫자에서 문자로 넘어왔을 경우
					// 기존까지 구한 숫자 처리
					if (now < min) {
						min = now;
						minPos = pos;
					}
					if (now > max) { // 최초일 경우(유일할 경우) min/max 둘 다 해야 하므로 else if로 안 함
						max = now;
						maxPos = pos;
					}
					
					// 새 숫자 시작
					wasNum = false;
					now = 0;
					pos++;
				}
			}
		}
		
		return minPos + maxPos;
	}
	
	public static void main(String[] args) {
		String q = "ab23c4d56e78f9g12h34i5j12k45l67n89m99o1k123p456q567r768s890t67u456v345w234x23y239z";
//		String q = "ab23c4d56e78f9g12h34i5j12k45l67n89m99o1k123p456q567r768s890";
		int output = solve(q);
		System.out.println(output);
	}

}
