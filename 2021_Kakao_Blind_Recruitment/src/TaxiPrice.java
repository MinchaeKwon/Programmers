/**
 * 합승 택시 요금
 * https://programmers.co.kr/learn/courses/30/lessons/72413
 * 
 * @author Minchae Gwon
 * @date 2021.2.3
 */

public class TaxiPrice {
	
	static final int INF = 99999999;

	public static void main(String[] args) {
		int[][] f1 = {{4, 1, 10}, {3, 5, 24}, {5, 6, 2}, {3, 1, 41}, {5, 1, 24}, {4, 6, 50}, {2, 4, 66}, {2, 3, 22}, {1, 6, 25}};
		int[][] f2 = {{5, 7, 9}, {4, 6, 4}, {3, 6, 1}, {3, 2, 3}, {2, 1, 6}};
		int[][] f3 = {{2, 6 ,6}, {6, 3, 7}, {4, 6, 7}, {6, 5, 11}, {2, 5, 12}, {5, 3, 20}, {2, 4, 8}, {4, 3, 9}};
		
		System.out.println(solution(6, 4, 6, 2, f1));
		System.out.println(solution(7, 3, 4, 1, f2));
		System.out.println(solution(6, 4, 5, 6, f3));
		
	}

	public static int solution(int n, int s, int a, int b, int[][] fares) {
		int[][] map = new int[n + 1][n + 1];
		
		//초기값 넣어줌
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				//자기 자신으로의 경로 비용은 0
				if (i == j) {
					map[i][j] = 0;
				} else { //아직 비용을 모르는 상태이기 때문에 INF
					map[i][j] = INF;
				}
			}
		}
		
		//비용을 넣어줌
		for (int[] f : fares) {
			map[f[0]][f[1]] = f[2];
			map[f[1]][f[0]] = f[2];
		}
		
		//각각 모든 경로에 대해 최단경로를 만들어줌
		for (int k = 1; k <= n; k++) { //k는 거쳐가는 지점
			for (int i = 1; i <= n; i++) { //i 시작지점
				for (int j = 1; j <= n; j++) { //j는 도착지점
					if (map[i][j] > map[i][k] + map[k][j]) {
						map[i][j] = map[i][k] + map[k][j];
					}
				}
			}
		}
		
		int answer = Integer.MAX_VALUE;
		
		for (int i = 1; i <= n; i++) {
			answer = Math.min(answer, map[s][i] + map[i][a] + map[i][b]);
		}
		
        return answer;
    }
	
}
