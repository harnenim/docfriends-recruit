package test.docfriends.ps;

public class Main {
	
	/*
	 * ���ڿ� ���ڰ� ���� q��� ���ڿ��� �ִµ�
	 * �ش� ���ڿ����� ������ �迭�� numbers��� �Ҷ�
	 * ���� ���� ���ڰ� numbers�� a��°, ���� ū ���ڰ� numbers�� b��°�� �ִٰ� �Ѵ�.
	 * a + b�� ���� ���Ͽ���.
	 */
	public static int solve(String q) {
		int min = Integer.MAX_VALUE, max = -1; // int max�� �Ѿ�� �������� ����
		int minPos = 0, maxPos = 0;
		
		int pos = 1; // 0��°���� ����
		int now = 0;
		boolean wasNum = false; // ��� ���� ���ڿ����� ����
		
		for (char c : q.toCharArray()) {
			if (c >= '0' && c <= '9') {
				// ������ ���
				if (!wasNum) {
					wasNum = true;
					
				}
				now = 10 * now + (c - '0');
				
			} else {
				// ������ ���
				if (wasNum) {
					// ���ڿ��� ���ڷ� �Ѿ���� ���
					// �������� ���� ���� ó��
					System.out.println(pos + "��°�� " + now);
					System.out.println(now + " < " + min + " ??");
					if (now < min) {
						System.out.println("min");
						min = now;
						minPos = pos;
					}
					if (now > max) { // ������ ���(������ ���) min/max �� �� �ؾ� �ϹǷ� else if�� �� ��
						System.out.println("max");
						max = now;
						maxPos = pos;
					}
					
					// �� ���� ����
					wasNum = false;
					now = 0;
					pos++;
				}
			}
		}
		
		// �������� ���ڷ� ���� ��� ó�� �ʿ�
		
		return minPos + maxPos;
	}
	
	public static void main(String[] args) {
		String q = "ab23c4d56e78f9g12h34i5j12k45l67n89m99o1k123p456q567r768s890t67u456v345w234x23y239z";
		int output = solve(q);
		System.out.println(output);
	}

}
