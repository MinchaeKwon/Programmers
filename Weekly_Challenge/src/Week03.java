/**
 * 퍼즐 조각 채우기
 * https://programmers.co.kr/learn/courses/30/lessons/84021
 * 
 * @author minchae
 * @date 2021. 9. 19.
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

class Point {
	int x;
	int y;
	
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class Week03 {

	static int[] dx = {-1, 0 , 1, 0};
 	static int[] dy = {0, 1, 0, -1};
 	
 	static int boardSize;
 	static ArrayList<Point> empty = new ArrayList<>();
 	static ArrayList<Point> block = new ArrayList<>();
 	static boolean[][] visited;
	
	public static void main(String[] args) {
		int[][] g1 = {{1, 1, 0, 0, 1, 0}, {0, 0, 1, 0, 1, 0}, {0, 1, 1, 0, 0, 1}, 
				{1, 1, 0, 1, 1, 1}, {1, 0, 0, 0, 1, 0}, {0, 1, 1, 1, 0, 0}};
		int[][] t1 = {{1, 0, 0, 1, 1, 0}, {1, 0, 1, 0, 1, 0}, {0, 1, 1, 0, 1, 1}, 
				{0, 0, 1, 0, 0, 0}, {1, 1, 0, 1, 1, 0}, {0, 1, 0, 0, 0, 0}};
		
		int[][] g2 = {{0, 0, 0}, {1, 1, 0}, {1, 1, 1}};
		int[][] t2 = {{1, 1, 1}, {1, 0, 0}, {0, 0, 0}};
		
		System.out.println(solution(g1, t1));
		System.out.println(solution(g2, t2));
	}
	
	public static int solution(int[][] game_board, int[][] table) {
		boardSize = game_board.length;
		
		visited = new boolean[boardSize][boardSize];
		
		// 게임 보드의 빈 공간 체크
		for (int i = 0; i < boardSize; i++) {
			for (int j = 0; j <boardSize; j++) {
				if (game_board[i][j] == 0 && !visited[i][j]) {
					empty.add(check(game_board, i, j, 0));
				}
			}
		}
		
		Arrays.fill(visited, false);
		
		// 테이블의 블록 체크
		for (int i = 0; i < boardSize; i++) {
			for (int j = 0; j < boardSize; j++) {
				if (table[i][j] == 1 && !visited[i][j]) {
					block.add(check(table, i, j, 1));
				}
			}
		}
		
		
        int answer = -1;
        return answer;
    }
	
	// 탐색할 배열, 탐색할 인덱스, 게임보드와 테이블 구분하는 변수
	public static Point check(int[][] board, int x, int y, int type) {
		Queue<Point> q = new LinkedList<>();
		
		q.add(new Point(x, y));
		visited[x][y] = true;
		
		while (!q.isEmpty()) {
			Point cur = q.poll();
			
			for (int i = 0; i < 4; i++) {
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];
				
				if (nx >= 0 && nx < boardSize && ny >= 0 && ny < boardSize) {
					if (board[nx][ny] == type && !visited[nx][ny]) {
						q.add(new Point(nx, ny));
						visited[nx][ny] = true;
					}
				}
				
			}
		}
		
		
		
		return null;
	}
	
}
