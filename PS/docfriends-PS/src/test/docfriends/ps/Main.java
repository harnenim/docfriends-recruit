package test.docfriends.ps;

public class Main {
	
	/*
	 * 숫자와 문자가 섞인 q라는 문자열이 있는데
	 * 해당 문자열에서 숫자의 배열을 numbers라고 할때
	 * 가장 작은 숫자가 numbers의 a번째, 가장 큰 숫자가 numbers의 b번째에 있다고 한다.
	 * a + b의 합을 구하여라.
	 */
	public static int solve(String q) {
		int min = -1, max = Integer.MAX_VALUE; // int max는 넘어가지 않으리라 가정
		int minPos, maxPos;
		
		int pos = 0;
		int now = 0;
		
		for (char c : q.toCharArray()) {
			if (c >= '0' && c <= '9') {
				// 숫자일 경우
				now = 10 * now + ('c' - '0');
				
			} else {
				// 아닐 경우 기존까지 구한 숫자 처리
				
				// 새 숫자 시작
				pos++;
			}
		}
		
		return 0;
	}
	
	public static void main(String[] args) {
		String q = "ab23c4d56e78f9g12h34i5j12k45l67n89m99o1k123p456q567r768s890t67u456v345w234x23y239z";
		int output = solve(q);
		System.out.println(output);
	}

}
