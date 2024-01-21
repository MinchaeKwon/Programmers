/**
 * 네트워크
 * https://school.programmers.co.kr/learn/courses/30/lessons/43162
 * 
 * @author minchae
 * @date 2024. 1. 21.
 */

import java.util.LinkedList;
import java.util.Queue;

public class Network {
	
	static boolean[] visited;

	public static void main(String[] args) {
		int n = 3;
		int[][] computers = {{1, 1, 0}, {1, 1, 0}, {0, 0, 1}};
		
		System.out.println(solution(n, computers));
	}
	
	public static int solution(int n, int[][] computers) {
		visited = new boolean[n];
		
		int answer = 0;
		
        for (int i = 0; i < n; i++) {
        	if (!visited[i]) {
        		bfs(i, n, computers);
//        		dfs(i, n, computers);
        		
        		answer++;
        	}
        }
		
        return answer;
    }
	
	private static void bfs(int start, int n, int[][] computers) {
		Queue<Integer> q = new LinkedList<>();
		
		q.add(start);
		visited[start] = true;
		
		while (!q.isEmpty()) {
			int cur = q.poll();
			
			for (int i = 0; i < n; i++) {
				// 아직 방문하지 않았고, 서로 연결되어 있는 경우
				if (!visited[i] && computers[cur][i] == 1) {
					visited[i] = true;
					q.add(i);
				}
			}
		}
	}
	
	private static void dfs(int start, int n, int[][] computers) {
		visited[start] = true;
		
		for (int i = 0; i < n; i++) {
			if (!visited[i] && computers[start][i] == 1) {
				dfs(i, n, computers);
			}
		}
	}

}
