/**
 * 미로 탈출 명령어
 * https://school.programmers.co.kr/learn/courses/30/lessons/150365
 * 
 * @author Minchae
 * @date 2023. 12. 2.
 */

import java.util.LinkedList;
import java.util.Queue;

public class MazeEscapeCommand {
	
	static class Pos {
		int x;
		int y;
		int d;
		String route;
		
		public Pos(int x, int y, int d, String route) {
			this.x = x;
			this.y = y;
			this.d = d;
			this.route = route;
		}
	}
	
	// 하좌우상 -> 문자열 사전순으로 dlru이기 때문
	static int[] dx = {1, 0, 0, -1};
	static int[] dy = {0, -1, 1, 0};
	
	static StringBuilder route;
	static String answer;

	public static void main(String[] args) {
		int n = 3;
		int m = 4;
		int x = 2;
		int y = 3;
		int r = 3;
		int c = 1;
		int k = 5;

		System.out.println(solution(n, m, x, y, r, c, k));
	}

	public static String solution(int n, int m, int x, int y, int r, int c, int k) {
		route = new StringBuilder();
		
		int dist = getDist(x, y, r, c);
		
		// 맨 처음 시작점에서 도착점까지의 거리와 k의 값이 홀수이거나 짝수가 아니거나 k가 최단거리보다 짧을 경우 탈출할 수 없음
		if ((k - dist) % 2 == 1 || k < dist) {
			return "impossible";
		}
		
		dfs(x, y, 0, r, c, n, m, k);
		
		return answer;
		
//		return bfs(n, m, x, y, r, c, k);
    }
	
	// dfs 탐색 -> 시간 더 빠름
	private static void dfs(int depth, int x, int y, int r, int c, int n, int m, int k) {
		// 경로를 찾은 경우 더이상 탐색하지 않기 위해 return
		if (answer != null) {
			return;
		}
		
		int dist = getDist(x, y, r, c);
		
		if (k - depth - dist < 0) {
			return;
		}
		
		if (depth == k) {
			if (x == r && y == c) {
				answer = route.toString();
			}
			
			return;
		}
		
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if (nx <= 0 || nx > n || ny <= 0 || ny > m) {
				continue;
			}
			
			route.append(getDir(i));
			dfs(nx, ny, depth + 1, r, c, n, m, k);
			route.delete(depth, depth + 1);
		}
	}
	
	// (x, y)에서 (r, c)까지의 거리 구하기
	private static int getDist(int x, int y, int r, int c) {
		return Math.abs(x - r) + Math.abs(y - c);
	}
	
	// bfs 탐색
	private static String bfs(int n, int m, int x, int y, int r, int c, int k) {
		/* visited를 3차원 배열로 사용하는 이유
		 * - 특정 칸을 여러 번 방문할 수 있기 때문에 이동거리를 확인하면서 특정 이동 거리에 해당 칸을 방문했는지 확인하기 위함
		 * - 특정 이동 거리에 해당 칸을 여러 번 방문할 필요는 없음, 다른 이동 거리일 때는 방문 가능하게 만들기 위해 3차원 배열 사용
		 *  */
		
		Queue<Pos> q = new LinkedList<>();
		boolean[][][] visited = new boolean[n + 1][m + 1][k + 1];
		
		q.add(new Pos(x, y, 0, ""));
		visited[x][y][0] = true;
		
		while (!q.isEmpty()) {
			Pos cur = q.poll();
			
			if (cur.x == r && cur.y == c && cur.d == k) {
				return cur.route;
			}
			
			if (cur.d == k) {
				continue;
			}
			
			for (int i = 0; i < 4; i++) {
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];
				
				if (nx <= 0 || nx > n || ny <= 0 || ny > m || visited[nx][ny][cur.d + 1]) {
					continue;
				}
				
				q.add(new Pos(nx, ny, cur.d + 1, cur.route + getDir(i)));
				visited[nx][ny][cur.d + 1] = true;;
			}
		}
		
        
        return "impossible";
	}
	
	// 방향 문자열 구하기
		private static String getDir(int dir) {
			if (dir == 0) {
				return "d";
			}
			
			if (dir == 1) {
				return "l";
			}
			
			if (dir == 2) {
				return "r";
			}
			
			if (dir == 3) {
				return "u";
			}
			
			return "";
		}
	
}
