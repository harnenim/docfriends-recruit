package test.docfriends.ps;

public class Main {
	
	/*
	 * ���ڿ� ���ڰ� ���� q��� ���ڿ��� �ִµ�
	 * �ش� ���ڿ����� ������ �迭�� numbers��� �Ҷ�
	 * ���� ���� ���ڰ� numbers�� a��°, ���� ū ���ڰ� numbers�� b��°�� �ִٰ� �Ѵ�.
	 * a + b�� ���� ���Ͽ���.
	 */
	public static int solve(String q) {
		int min = -1, max = Integer.MAX_VALUE; // int max�� �Ѿ�� �������� ����
		int minPos, maxPos;
		
		int pos = 0;
		int now = 0;
		
		for (char c : q.toCharArray()) {
			if (c >= '0' && c <= '9') {
				// ������ ���
				now = 10 * now + ('c' - '0');
				
			} else {
				// �ƴ� ��� �������� ���� ���� ó��
				
				// �� ���� ����
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
