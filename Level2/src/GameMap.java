/**
 * 게임 맵 최단거리
 * https://school.programmers.co.kr/learn/courses/30/lessons/1844
 * 
 * @author minchae
 * @date 2024. 1. 22.
 */

import java.util.LinkedList;
import java.util.Queue;

public class GameMap {
	
	static class Pos {
		int x;
		int y;
		int cnt;
		
		public Pos(int x, int y, int cnt) {
			this.x = x;
			this.y = y;
			this.cnt = cnt;
		}
	}

	// 동서남북 (좌우하상)
	static int[] dx = {0, 0, 1, -1};
	static int[] dy = {1, -1, 0, 0};
	
	static int n, m;

	public static void main(String[] args) {
		int[][] maps = { { 1, 0, 1, 1, 1 }, { 1, 0, 1, 0, 1 }, { 1, 0, 1, 1, 1 }, { 1, 1, 1, 0, 1 },
				{ 0, 0, 0, 0, 1 } };

		System.out.println(solution(maps));
	}

	public static int solution(int[][] maps) {
		n = maps.length;
		m = maps[0].length;
		
		return bfs(maps);
	}
	
	private static int bfs(int[][] maps) {
		Queue<Pos> q = new LinkedList<>();
		boolean[][] visited = new boolean[n][m];
		
		q.add(new Pos(0, 0, 1));
		visited[0][0] = true;
		
		while (!q.isEmpty()) {
			Pos cur = q.poll();
			
			if (cur.x == n - 1 && cur.y == m - 1) {
				return cur.cnt;
			}
			
			for (int i = 0; i < 4; i++) {
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];
				
				// 범위를 벗어나는 경우 다음으로 넘어감
				if (nx < 0 || nx >= n || ny < 0 || ny >= m) {
					continue;
				}
				
				// 이미 방문했거나, 벽인 경우 다음으로 넘어감
				if (visited[nx][ny] || maps[nx][ny] == 0) {
					continue;
				}

				q.add(new Pos(nx, ny, cur.cnt + 1));
				visited[nx][ny] = true;
			}
		}
		
		return -1;
	}

}
