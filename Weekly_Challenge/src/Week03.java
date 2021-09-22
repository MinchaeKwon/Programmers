/**
 * 퍼즐 조각 채우기
 * https://programmers.co.kr/learn/courses/30/lessons/84021
 * 
 * @author minchae
 * @date 2021. 9. 19.
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

class Point implements Comparable<Point> {
	int x;
	int y;
	
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public int compareTo(Point o) {
		if (this.x < o.x) {
			return -1;
		} else if (this.x > o.x) {
			return 1;
		} else {
			if (this.y < o.y) {
				return -1;
			} else if (this.y > o.y) {
				return 1;
			}
		}
		
		return 0;
	}
}

public class Week03 {

	static int[] dx = {-1, 0 , 1, 0};
 	static int[] dy = {0, 1, 0, -1};
 	
 	static int boardSize;
 	static ArrayList<ArrayList<Point>> empty = new ArrayList<>(); // 게임 보드의 빈 공간 저장
 	static ArrayList<ArrayList<Point>> block = new ArrayList<>(); // 테이블의 블록 저장
 	static boolean[][] visited;
	
	public static void main(String[] args) {
		int[][] g1 = {{1, 1, 0, 0, 1, 0}, {0, 0, 1, 0, 1, 0}, {0, 1, 1, 0, 0, 1}, 
				{1, 1, 0, 1, 1, 1}, {1, 0, 0, 0, 1, 0}, {0, 1, 1, 1, 0, 0}};
		int[][] t1 = {{1, 0, 0, 1, 1, 0}, {1, 0, 1, 0, 1, 0}, {0, 1, 1, 0, 1, 1}, 
				{0, 0, 1, 0, 0, 0}, {1, 1, 0, 1, 1, 0}, {0, 1, 0, 0, 0, 0}};
		
		int[][] g2 = {{0, 0, 0}, {1, 1, 0}, {1, 1, 1}};
		int[][] t2 = {{1, 1, 1}, {1, 0, 0}, {0, 0, 0}};
		
		System.out.println(solution(g1, t1));
//		System.out.println(solution(g2, t2));
	}

    public static int solution(int[][] game_board, int[][] table) {
        int answer = -1;

        boolean[][] visitedTable = new boolean[table.length][table.length];
        boolean[][] visitedBoard = new boolean[game_board.length][game_board.length];

        for (int i = 0; i < table.length; i++){
            for (int j = 0; j < table.length; j++){

                if (table[i][j] == 1 && !visitedTable[i][j]){
                    bfs(i, j, visitedTable, table, 1, block);
                }

                if (game_board[i][j] == 0 && !visitedBoard[i][j]){
                    bfs(i, j, visitedBoard, game_board, 0, empty);
                }
            }
        }

        answer = findBlock(block, empty);

        return answer;
    }

    public static int findBlock(ArrayList<ArrayList<Point>> board, ArrayList<ArrayList<Point>> table){
        int size = 0;
        int tableLen = table.size();
        int boardLen = board.size();
        boolean[] visitedBoard = new boolean[boardLen];
        for (int i = 0; i < tableLen; i++){
            ArrayList<Point> tablePoints = table.get(i);
            for (int j = 0; j < boardLen; j++){
                ArrayList<Point> boardPoints = board.get(j);

                if (tablePoints.size() == boardPoints.size() && !visitedBoard[j]){ //좌표 개수 같을때
                    if(isRotate(boardPoints, tablePoints)){ //회전해서 맞는지 확인
                        size += tablePoints.size();
                        visitedBoard[j] = true;
                        break;
                    }
                }
            }

        }

        return size;
    }

    public static boolean isRotate(ArrayList<Point> board, ArrayList<Point> table){
        boolean isCollect = false;

        for (int i = 0; i < 4; i++){ //table퍼즐 0, 90, 180, 270도 회전
        
            int nearZeroX = table.get(0).x;
            int nearZeroY = table.get(0).y;

            for (int j = 0; j < table.size(); j++){
                table.get(j).x -= nearZeroX;
                table.get(j).y -= nearZeroY;
            }


            boolean isCollectPoint = true;
            for (int j = 0; j < board.size(); j++){ //좌표 비교
                Point boardPoint = board.get(j);
                Point tablePoint = table.get(j);

                if (boardPoint.x != tablePoint.x || boardPoint.y != tablePoint.y){
                    isCollectPoint = false;
                    break;
                }
            }

            if (isCollectPoint){
            	return true;
            } else{ //90도 회전 : x,y -> y, -x
                for(int j = 0; j < table.size(); j++){
                    int temp = table.get(j).x;
                    table.get(j).x = table.get(j).y;
                    table.get(j).y = -temp;
                }
                Collections.sort(table);
            }
        }

        return false;

    }



    public static void bfs(int currentX, int currentY, boolean[][] visited, int[][] graph, int blockOrEmpty, ArrayList<ArrayList<Point>> list){

        Queue<Point> queue = new LinkedList<>();
        ArrayList<Point> subList = new ArrayList<>();
        
        visited[currentX][currentY] = true;
        queue.add(new Point(currentX, currentY));
        subList.add(new Point(currentX-currentX, currentY-currentY));

        while (!queue.isEmpty()){
            Point pop = queue.poll();
            int popX = pop.x;
            int popY = pop.y;

            for (int i = 0; i < 4; i++){
                int nextX = popX + dx[i];
                int nextY = popY + dy[i];
                if (nextX < 0 || nextX >= graph.length || nextY < 0 || nextY >= graph.length){
                    continue;
                }
                if (!visited[nextX][nextY] && graph[nextX][nextY] == blockOrEmpty){

                    visited[nextX][nextY] = true;
                    queue.add(new Point(nextX, nextY));
                    subList.add(new Point(nextX-currentX, nextY-currentY));
                }
            }
        }
        
        Collections.sort(subList);
        
        list.add(subList);
    }
	
	
//	public static int solution(int[][] game_board, int[][] table) {
//		boardSize = game_board.length;
//		
//		visited = new boolean[boardSize][boardSize];
//		
//		// 게임 보드의 빈 공간 체크
//		for (int i = 0; i < boardSize; i++) {
//			for (int j = 0; j <boardSize; j++) {
//				if (game_board[i][j] == 0 && !visited[i][j]) {
//					empty.add(check(game_board, i, j, 0));
//				}
//			}
//		}
//		
//		for (int i = 0; i < boardSize; i++) {
//			Arrays.fill(visited[i], false);	
//		}
//		
//		// 테이블의 블록 체크
//		for (int i = 0; i < boardSize; i++) {
//			for (int j = 0; j < boardSize; j++) {
//				if (table[i][j] == 1 && !visited[i][j]) {
//					block.add(check(table, i, j, 1));
//				}
//			}
//		}
//		
//		System.out.println("빈 공간: " + empty.size() + ", 블록: " + block.size());
//		
//		boolean[] visitedBoard = new boolean[empty.size()];
//		int answer = 0;
//		
//		// 게임 보드의 빈 공간과 테이블의 블록만큼 반복하면서 빈 공간에 블록을 채움
//		for (int i = 0; i < block.size(); i++) {
//			ArrayList<Point> blockCheck = block.get(i);
//			
//			for (int j = 0; j < empty.size(); j++) {
//				ArrayList<Point> emptyCheck = empty.get(j);
//				
//				// 빈 공간과 블록의 개수가 같고 방문한 적이 없는 곳일 경우
//				if (emptyCheck.size() == blockCheck.size() && !visitedBoard[j]) {
//					// 빈 공간에 블록이 들어가는지 확인
//					if (isRotate(emptyCheck, blockCheck)) {
//						System.out.println(1111);
//						answer += blockCheck.size();
//						visitedBoard[j] = true;
//						
//						break;
//					}
//				}
//			}
//		}
//		
//        return answer;
//    }
//	
//	// 게임 보드의 빈 공간과 테이블의 블록을 찾아내는 메소드
//	// 탐색할 배열, 탐색할 인덱스, 게임보드와 테이블 구분하는 변수
//	public static ArrayList<Point> check(int[][] board, int x, int y, int type) {
//		Queue<Point> q = new LinkedList<>();
//		ArrayList<Point> result = new ArrayList<>();
//		
//		q.add(new Point(x, y));
//		visited[x][y] = true;
//		result.add(new Point(0, 0));
//		
//		while (!q.isEmpty()) {
//			Point cur = q.poll();
//			
//			for (int i = 0; i < 4; i++) {
//				int nx = cur.x + dx[i];
//				int ny = cur.y + dy[i];
//				
//				if (nx >= 0 && nx < boardSize && ny >= 0 && ny < boardSize) {
//					if (!visited[nx][ny] && board[nx][ny] == type) {
//						q.add(new Point(nx, ny));
//						visited[nx][ny] = true;
//						result.add(new Point(nx - x, ny - y));
//					}
//				}
//				
//			}
//		}
//		
//		Collections.sort(result);
//		     
//		return result;
//	}
//	
//	// 블록을 회전시키면서 게임 보드에 들어가는지 확인하는 메소드
//	public static boolean isRotate(ArrayList<Point> empty, ArrayList<Point> block) {
//		boolean isCollect = true;
//		
//		// 90도씩 회전 시키기
//		for (int i = 0; i < 4; i++) {
//			int zeroX = block.get(0).x;
//			int zeroY = block.get(0).y;
//			
//			// (0, 0)을 기준으로 블록 좌표를 변경
//			for (int j = 0; j < block.size(); j++) {
//				block.get(j).x -= zeroX;
//				block.get(j).y -= zeroY;
//			}
//			
//			for (int j = 0; j < empty.size(); j++) {
//				Point emptyPoint = empty.get(j);
//				Point blockPoint = block.get(j);
//				
//				if (emptyPoint.x != blockPoint.x || emptyPoint.y != blockPoint.y) {
////					System.out.println("블록 맞지 않음");
//					isCollect = false;
//					break;
//				}
//			}
//			
//			if (isCollect) {
//				return true;
////				break;
//			}
//			else {
//				// 90도 회전 : (x, y) -> (y, -x)
//				for (int j = 0; j < block.size(); j++) {
//					int temp = block.get(j).x;
//					
//					block.get(j).x = block.get(j).y;
//					block.get(j).y = -temp;
//				}
//				
//				Collections.sort(block);
//			}
//			
//		}
//		
//		return false;
////		return isCollect;
//	}
	
}


//import java.util.*;
//
//public class Week03 {
//
//    static int[] dx = {-1, 0, 1, 0};
//    static int[] dy = {0, 1, 0, -1};
//
//    public static void main(String[] args) {
//		int[][] g1 = {{1, 1, 0, 0, 1, 0}, {0, 0, 1, 0, 1, 0}, {0, 1, 1, 0, 0, 1}, 
//				{1, 1, 0, 1, 1, 1}, {1, 0, 0, 0, 1, 0}, {0, 1, 1, 1, 0, 0}};
//		int[][] t1 = {{1, 0, 0, 1, 1, 0}, {1, 0, 1, 0, 1, 0}, {0, 1, 1, 0, 1, 1}, 
//				{0, 0, 1, 0, 0, 0}, {1, 1, 0, 1, 1, 0}, {0, 1, 0, 0, 0, 0}};
//		
//		int[][] g2 = {{0, 0, 0}, {1, 1, 0}, {1, 1, 1}};
//		int[][] t2 = {{1, 1, 1}, {1, 0, 0}, {0, 0, 0}};
//		
//		System.out.println(solution(g1, t1));
////		System.out.println(solution(g2, t2));
//	}
//    
//    public static int solution(int[][] game_board, int[][] table) {
//        int answer = -1;
//
//        boolean[][] visitedTable = new boolean[table.length][table.length];
//        boolean[][] visitedBoard = new boolean[game_board.length][game_board.length];
//        List<List<int[]>> boardList = new ArrayList<>();
//        List<List<int[]>> tableList = new ArrayList<>();
//
//        for (int i = 0; i < table.length; i++){
//            for (int j = 0; j < table.length; j++){
//
//                if (table[i][j] == 1 && !visitedTable[i][j]){
//                    bfs(i, j, visitedTable, table, 1, tableList);
//                }
//
//                if (game_board[i][j] == 0 && !visitedBoard[i][j]){
//                    bfs(i, j, visitedBoard, game_board, 0, boardList);
//                }
//            }
//        }
//
//        answer = findBlock(boardList, tableList);
//
//        return answer;
//    }
//
//    public static int findBlock(List<List<int[]>> board, List<List<int[]>> table){
//        int size = 0;
//        int tableLen = table.size();
//        int boardLen = board.size();
//        boolean[] visitedBoard = new boolean[boardLen];
//        for (int i = 0; i < tableLen; i++){
//            List<int[]> tablePoints = table.get(i);
//            for (int j = 0; j < boardLen; j++){
//                List<int[]> boardPoints = board.get(j);
//
//                if (tablePoints.size() == boardPoints.size() && !visitedBoard[j]){ //좌표 개수 같을때
//                    if(isRotate(boardPoints, tablePoints)){ //회전해서 맞는지 확인
//                        size += tablePoints.size();
//                        visitedBoard[j] = true;
//                        break;
//                    }
//                }
//            }
//
//        }
//
//        return size;
//    }
//
//    public static boolean isRotate(List<int[]> board, List<int[]> table){
//        boolean isCollect = false;
//
//        board.sort((o1, o2) -> {
//            return o1[0] > o2[0]?1 : o1[0] < o2[0]?-1 : Integer.compare(o1[1], o2[1]);
//        });
//
//        for (int i = 0; i < 4; i++){ //table퍼즐 0, 90, 180, 270도 회전
//
//            table.sort((o1, o2) -> {
//                return o1[0] > o2[0]?1 : o1[0] < o2[0]?-1 : Integer.compare(o1[1], o2[1]);
//            });
//            int nearZeroX = table.get(0)[0];
//            int nearZeroY = table.get(0)[1];
//
//            for (int j = 0; j < table.size(); j++){
//                table.get(j)[0] -= nearZeroX;
//                table.get(j)[1] -= nearZeroY;
//            }
//
//
//            boolean isCollectPoint = true;
//            for (int j = 0; j < board.size(); j++){ //좌표 비교
//                int[] boardPoint = board.get(j);
//                int[] tablePoint = table.get(j);
//
//                if (boardPoint[0] != tablePoint[0] || boardPoint[1] != tablePoint[1]){
//                    isCollectPoint = false;
//                    break;
//                }
//            }
//
//            if (isCollectPoint){
//                isCollect = true;
//                break;
//            } else{ //90도 회전 : x,y -> y, -x
//                for(int j = 0; j < table.size(); j++){
//                    int temp = table.get(j)[0];
//                    table.get(j)[0] = table.get(j)[1];
//                    table.get(j)[1] = -temp;
//                }
//            }
//        }
//        return isCollect;
//
//    }
//
//
//
//    public static void bfs(int currentX, int currentY, boolean[][] visited, int[][] graph,
//                      int blockOrEmpty, List<List<int[]>> list){
//
//        Queue<int[]> queue = new LinkedList<>();
//        visited[currentX][currentY] = true;
//        queue.add(new int[]{currentX, currentY});
//        List<int[]> subList = new ArrayList<>();
//        subList.add(new int[]{currentX-currentX, currentY-currentY});
//
//        while (!queue.isEmpty()){
//            int[] pop = queue.remove();
//            int popX = pop[0];
//            int popY = pop[1];
//
//            for (int i = 0; i < 4; i++){
//                int nextX = popX + dx[i];
//                int nextY = popY + dy[i];
//                if (nextX < 0 || nextX >= graph.length || nextY < 0 || nextY >= graph.length){
//                    continue;
//                }
//                if (!visited[nextX][nextY] && graph[nextX][nextY] == blockOrEmpty){
//
//                    visited[nextX][nextY] = true;
//                    queue.add(new int[]{nextX, nextY});
//                    subList.add(new int[]{nextX-currentX, nextY-currentY});
//                }
//            }
//        }
//        list.add(subList);
//    }
//}
