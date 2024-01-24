/**
 * 아이템 줍기
 * https://school.programmers.co.kr/learn/courses/30/lessons/87694
 * 
 * @author minchae
 * @date 2024. 1. 24.
 */

import java.util.LinkedList;
import java.util.Queue;

public class PickItem {
	
	static class Pair {
		int x;
		int y;
		int cnt;
		
		public Pair(int x, int y, int cnt) {
			this.x = x;
			this.y = y;
			this.cnt = cnt;
		}
	}
	
	// 상하좌우
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	static int[][] map = new int[101][101];

	public static void main(String[] args) {
		int[][] rectangle = { { 1, 1, 7, 4 }, { 3, 2, 5, 5 }, { 4, 3, 6, 9 }, { 2, 6, 8, 8 } };
		int characterX = 1;
		int characterY = 3;
		int itemX = 7;
		int itemY = 8;
		
		System.out.println(solution(rectangle, characterX, characterY, itemX, itemY));
	}
	
	public static int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        for (int i = 0; i < rectangle.length; i++) {
        	drawMap(rectangle[i][0] * 2, rectangle[i][1] * 2, rectangle[i][2] * 2, rectangle[i][3] * 2);
        }
        
        return bfs(characterX * 2, characterY * 2, itemX * 2, itemY * 2);
    }
	
	private static void drawMap(int x1, int y1, int x2, int y2) {
		for (int i = x1; i <= x2; i++) {
			for (int j = y1; j <= y2; j++) {
				// 다른 직사각형과 겹치는 경우
				if (map[i][j] == 2) {
					continue;
				}
				
				map[i][j] = 2;
				
				if (i == x1 || i == x2 || j == y1 || j == y2) {
					map[i][j] = 1; // 테두리는 1로 저장
				}
			}
		}
	}
	
	private static int bfs(int sx, int sy, int ex, int ey) {
		Queue<Pair> q = new LinkedList<>();
		boolean[][] visited = new boolean[101][101];
		
		q.add(new Pair(sx, sy, 0));
		visited[sx][sy] = true;
		
		while (!q.isEmpty()) {
			Pair cur = q.poll();
			
			if (cur.x == ex && cur.y == ey) {
				return cur.cnt / 2; // 맵의 크기를 2배로 해줬기 때문에 /2를 해줌
			}
			
			for (int i = 0; i < 4; i++) {
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];
				
				if (isRange(nx, ny) && !visited[nx][ny] && map[nx][ny] == 1) {
					q.add(new Pair(nx, ny, cur.cnt + 1));
					visited[nx][ny] = true;
				}
			}
		}
		
		return 0;
	}
	
	private static boolean isRange(int x, int y) {
		return x >= 0 && x < 101 && y >= 0 && y < 101;
	}

}
