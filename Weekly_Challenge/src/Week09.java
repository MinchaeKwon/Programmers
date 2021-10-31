/**
 * 전력망을 둘로 나누기 https://programmers.co.kr/learn/courses/30/lessons/86971
 * 
 * @author minchae
 * @date 2021. 10. 31.
 */

public class Week09 {

	static int[] parent;

	public static void main(String[] args) {
		int n1 = 9;
		int[][] w1 = { { 1, 3 }, { 2, 3 }, { 3, 4 }, { 4, 5 }, { 4, 6 }, { 4, 7 }, { 7, 8 }, { 7, 9 } };

		int n2 = 4;
		int[][] w2 = { { 1, 2 }, { 2, 3 }, { 3, 4 } };

		int n3 = 7;
		int[][] w3 = { { 1, 2 }, { 2, 7 }, { 3, 7 }, { 3, 4 }, { 4, 5 }, { 6, 7 } };

		System.out.println(solution(n1, w1));
		System.out.println(solution(n2, w2));
		System.out.println(solution(n3, w3));
	}

	public static int solution(int n, int[][] wires) {
		parent = new int[n + 1];

		int answer = -1;
		return answer;
	}

	// x가 속하는 부모 노드(최상위 노드)를 찾음
	public static int find(int x) {
		if (parent[x] == x) {
			return x;
		} else {
			return parent[x] = find(parent[x]);
		}
	}

	// 두 개의 노드가 속한 집합을 합침(연결함)
	public static boolean union(int x, int y) {
		// 통로의 시작점과 끝점의 최상위 노드를 찾음
		x = find(x);
		y = find(y);

		// 최상위 노드가 같지 않을 경우 union
		if (x != y) {
			parent[x] = y;
			return true;
		}

		return false;
	}

}
