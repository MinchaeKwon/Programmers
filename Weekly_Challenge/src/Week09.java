/**
 * 전력망을 둘로 나누기
 * https://programmers.co.kr/learn/courses/30/lessons/86971
 * 
 * @author minchae
 * @date 2022. 1. 27.
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
		
		int answer = 100;
		
		for (int i = 0; i < wires.length; i++) {
			// parent 초기화
			for (int j = 0; j < n + 1; j++) {
				parent[j] = j;
			}
			
			// 간선 하나를 끊으면서 두 개의 트리로 나눔
			for (int j = 0; j < wires.length; j++) {
				if (j == i) {
					continue;
				}
				
				union(wires[j][0], wires[j][1]);
			}
			
			// 1번 노드에 연결되어 있는 노드의 개수를 구함
			int cnt = 0;
			for (int j = 0; j < n + 1; j++) {
				if (find(parent[j]) == 1) {
					cnt++;
				}
			}
			
			answer = Math.min(answer, Math.abs(n - cnt * 2)); // (n - cnt * 2) == (cnt - (n - cnt))
		}

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
	public static void union(int x, int y) {
		// 간선의 시작점과 끝점의 최상위 노드를 찾음
		x = find(x);
        y = find(y);
        
        // 최상위 노드가 같지 않을 경우 union : 부모 노드 값이 작은 쪽을 큰 쪽의 부모 노드로 저장(더 작은 쪽을 부모로 갖도록 함)
        if (x < y) {
        	parent[y] = x;
        } else {
        	parent[x] = y;
        }
	}

}
