/**
 * 경주로 건설
 * https://programmers.co.kr/learn/courses/30/lessons/67259
 * 
 * @author Minchae Gwon
 * @date 2021.1.31
 */

import java.util.LinkedList;
import java.util.Queue;

class Track {
	int x;
	int y;
	int dir;
	int cost;
	
	public Track(int x, int y, int dir, int cost) {
		this.x = x;
		this.y = y;
		this.dir = dir;
		this.cost = cost;
	}
}

public class TrackConstruction {

	public static void main(String[] args) {
		int[][] b1 = {{0,0,0}, {0,0,0}, {0,0,0}};
		int[][] b2 = {{0,0,0,0,0,0,0,1}, {0,0,0,0,0,0,0,0}, {0,0,0,0,0,1,0,0}, {0,0,0,0,1,0,0,0}, 
					{0,0,0,1,0,0,0,1}, {0,0,1,0,0,0,1,0}, {0,1,0,0,0,1,0,0}, {1,0,0,0,0,0,0,0}};
		int[][] b3 = {{0,0,1,0}, {0,0,0,0}, {0,1,0,1}, {1,0,0,0}};
		int[][] b4 = {{0,0,0,0,0,0}, {0,1,1,1,1,0}, {0,0,1,0,0,0}, {1,0,0,1,0,1}, {0,1,0,0,0,1}, {0,0,0,0,0,0}};

		System.out.println(solution(b1));
		System.out.println(solution(b2));
		System.out.println(solution(b3));
		System.out.println(solution(b4));
		
	}
	
	static int[] dx = {-1, 1, 0, 0}; //상하
	static int[] dy = {0, 0, -1, 1}; //좌우
	static int answer;
	
	public static int solution(int[][] board) {
		answer = Integer.MAX_VALUE;
		
		getTrackCost(board, 0, 0, -1, 0);
		
        return answer;
    }
	
	//bfs 사용
	public static void getTrackCost(int[][] board, int x, int y, int dir, int cost) {
		Queue<Track> q = new LinkedList<>();
		int n = board.length;
		
//		boolean[][] visited = new boolean[n][n];
//		visited[x][y] = true;
		
		q.add(new Track(x, y, dir, cost));
		
		while (!q.isEmpty()) {
			Track track = q.poll();
			
			//목적점에 도착한 경우 최소 비용을 구해줌
			if (track.x == n - 1 && track.y == n - 1) {
				answer = Math.min(answer, track.cost);
			}
			
			for (int i = 0; i < 4; i++) {
				int tx = track.x + dx[i];
				int ty = track.y + dy[i];
				
				//상하좌우를 보는데 범위를 벗어나지 않고 해당하는 곳이 벽이 아닐 경우(도로 연결이 가능한 경우)
				if (tx >= 0 && tx < n && ty >= 0 && ty < n && board[tx][ty] != 1) {
					//방법1 -> 밑의 코드(2-1)를 합친 것
					
					//상:0, 하:1, 좌:2, 우:3 -> 큐에서 꺼낸 도로의 방향이 i와 같으면 직선 도로, 다르면 코너
					//직선 도로일 경우 && 해당 위치의 비용이 없거나(아직 방문하지 않았다는 의미), 100원을 추가한 값보다 크다면 더 작은 값인 track.cost + 100를 넣어줌
					if ((track.dir == -1 || track.dir == i) && (board[tx][ty] == 0 || board[tx][ty] >= track.cost + 100)) {
						q.add(new Track(tx, ty, i, track.cost + 100));
						board[tx][ty] = track.cost + 100;
					}
					//코너일 경우  && 해당 위치의 비용이 없거나(아직 방문하지 않았다는 의미), 600원을 추가한 값보다 크다면 더 작은 값인 track.cost + 600를 넣어줌
					else if (track.dir != i && (board[tx][ty] == 0 || board[tx][ty] >= track.cost + 600)) {
						q.add(new Track(tx, ty, i, track.cost + 600));
						board[tx][ty] = track.cost + 600;
					}
					
					
					/* 방법 2
					int trackCost = 0;
					
					//상:0, 하:1, 좌:2, 우:3 -> 큐에서 꺼낸 도로의 방향이 i와 같으면 직선 도로임
					//직선 도로일 경우에는 100원 추가
					if (track.dir == -1 || track.dir == i) {
						trackCost = track.cost + 100;
					}
					else { //코너일 경우에는 500원이 추가되므로 600원을 더해줌
						trackCost = track.cost + 600;
					}
					
					//방법 2-1
					//해당 위치의 비용이 없거나(아직 방문하지 않았다는 의미) trackCost보다 값이 큰 경우 trackCost를 해당 위치에 넣어줌
					if (board[tx][ty] == 0 || board[tx][ty] >= trackCost) {
						q.add(new Track(tx, ty, i, trackCost));
						board[tx][ty] = trackCost;
					}
					
					//방법 2-2
					//방문한 곳이어도 비용이 덜 든다면 다시 방문할 수 있기 때문에 방문여부를 알 필요가 없다 -> visited 배열을 사용할 필요가 없음
					//방문하지 않은 곳이거나 어느 특정 도로까지의 비용이 trackCost보다 값이 큰 경우 해당 도로에 trackCost를 넣어줌 -> 최소 비용을 구해야하기 때문
					if (!visited[tx][ty] || board[tx][ty] >= trackCost) {
						if (!visited[tx][ty]) {
							visited[tx][ty] = true;
						}
						
						//이미 방문한 곳이어도 비용이 적게 들 수 있기 때문에 방문 여부에 상관없이 해당 위치를 큐에 넣어줌
						q.add(new Track(tx, ty, i, trackCost));
						board[tx][ty] = trackCost;
					}
					*/
					
				}
			}
		}
		
 	}
	

}
