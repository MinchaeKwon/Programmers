/**
 * 퍼즐 조각 채우기
 * https://programmers.co.kr/learn/courses/30/lessons/84021
 * 
 * @author minchae
 * @date 2021. 8. 18.
 */



public class Week03 {

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
		
		
        int answer = -1;
        return answer;
    }

}
