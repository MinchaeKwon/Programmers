/**
 * 거리두기 확인하기
 * https://school.programmers.co.kr/learn/courses/30/lessons/81302
 * 
 * @author Minchae
 * @date 2023. 11. 27.
 */

import java.util.LinkedList;
import java.util.Queue;

class Pos {
	int x;
	int y;
	
	public Pos(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class Distancing {
	
	// 상하좌우
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	static char[][] map;

	public static void main(String[] args) {
		String[][] places = { { "POOOP", "OXXOX", "OPXPX", "OOXOX", "POXXP" },
				{ "POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOPP" }, { "PXOPX", "OXOXP", "OXPOX", "OXXOP", "PXPOX" },
				{ "OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO" }, { "PXPXP", "XPXPX", "PXPXP", "XPXPX", "PXPXP" } };

		int[] result = solution(places);

		for (int n : result) {
			System.out.print(n + " ");
		}
	}
	
	public static int[] solution(String[][] places) {
        int[] answer = new int[places.length];
        
        for (int i = 0; i < places.length; i++) {
        	map = new char[5][5];
        	
        	for (int x = 0; x < 5; x++) {
        		String[] s = places[i];
        		
        		for (int y = 0; y < 5; y++) {
        			map[x][y] = s[x].charAt(y);
        		}
        	}
        	
        	boolean result = true;
        	
        	for (int x = 0; x < 5; x++) {
        		for (int y = 0; y < 5; y++) {
        			if (map[x][y] == 'P') {
        				// 사람이 앉아있는 경우에만 bfs 탐색
        				if (!bfs(x, y)) {
            				result = false;
            				break;
            			}
        			}
        		}
        	}
        	
        	answer[i] = result ? 1 : 0;
        }
        
        return answer;
    }
	
	private static boolean bfs(int x, int y) {
		Queue<Pos> q = new LinkedList<>();
		q.add(new Pos(x, y));
		
		while (!q.isEmpty()) {
			Pos cur = q.poll();
			
			for (int i = 0; i < 4; i++) {
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];
				
				// 범위를 벗어나거나 출발점인 경우 다음으로 넘어감
				if (nx < 0 || nx >= 5 || ny < 0 || ny >= 5 || (nx == x && ny == y)) {
					continue;
				}
				
				int dist = Math.abs(nx - x) + Math.abs(ny - y);
				
				if (map[nx][ny] == 'P' && dist <= 2) {
					// 사람이 앉아있고 거리가 2이하인 경우 거리두기가 지켜지지 않았기 때문에 false 반환
					return false;
				} else if (map[nx][ny] == 'O' && dist < 2) {
					// 빈 자리이고 거리가 2미만인 경우 다음 칸을 확인하기 위해 큐에 값 추가
					q.add(new Pos(nx, ny));
				}
			}
		}
		
		return true;
	}

}
