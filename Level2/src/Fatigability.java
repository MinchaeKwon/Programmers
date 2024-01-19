/**
 * 피로도
 * https://school.programmers.co.kr/learn/courses/30/lessons/87946
 * 
 * @author minchae
 * @date 2024. 1. 19.
 */

public class Fatigability {
	
	static boolean[] visited;
	static int answer;

	public static void main(String[] args) {
		int k = 80;
		int[][] dungeons = {{80, 20}, {50, 40}, {30, 1}};

		System.out.println(solution(k, dungeons));
	}
	
	public static int solution(int k, int[][] dungeons) {
		visited = new boolean[dungeons.length];
		
        backtracking(0, k, dungeons);
        
        return answer;
    }
	
	private static void backtracking(int depth, int k, int[][] dungeons) {
		for (int i = 0; i < dungeons.length; i++) {
			// 해당 던전을 방문하지 않았고, 던전의 필요 피로도와 같거나 높은 경우
			if (!visited[i] && k >= dungeons[i][0]) {
				visited[i] = true;
				backtracking(depth + 1, k - dungeons[i][1], dungeons);
				visited[i] = false;
			}
		}
		
		// 던전을 방문할 수 있는 경우에만 재귀호출을 하기 때문에 마지막에 방문할 수 있는 던전의 수를 구함
		answer = Math.max(depth, answer);
	}

}
