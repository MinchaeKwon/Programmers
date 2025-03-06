/**
 * 완전범죄
 * https://school.programmers.co.kr/learn/courses/30/lessons/389480
 *
 * @author minchae
 * @date 2025. 3. 5.
 *
 * 문제 풀이
 * - 일단 b가 다 훔친다고 생각하고 거기서 m미만으로 줄여가기
 * - 그런데 시간초과 발생 -> 어떻게 시간초과를 줄일까..
 * - dp..? 근데 어떻게 쓸지 모르겠다.
 * 
 * - dp 2차원 배열 선언 -> dp[a][b] 
 *
 * 시간 복잡도
 * O()
 *
 * 실행 시간
 *  ms
 */

public class Crime {

	public static void main(String[] args) {

	}
	
	static int answer = Integer.MAX_VALUE;
    static boolean[][][] visited;

    public static int solution(int[][] info, int n, int m) {
        
        
        int bSum = 0;
        
        for (int[] cnt : info) {
        	bSum += cnt[1];
        }
        
        visited = new boolean[info.length][n][bSum];
        visited[0][0][0] = true;
        
        select(0, 0, bSum, info, n, m);

        return answer == Integer.MAX_VALUE ? -1 : answer;
    }

    // 시간초과 나는 코드
    private static void select(int depth, int aCnt, int bCnt, int[][] info, int n, int m) {
        // 이미 최솟값을 초과한다면 종료
        if (aCnt >= answer) {
            return;
        }
        
        // 둘 다 흔적이 초과하지 않는 경우 a의 흔적 개수 갱신
        if (depth == info.length && aCnt < n && bCnt < m) {
        	answer = Math.min(answer, aCnt);
        	return;
        }
        
        // b가 훔치는 경우는 그대로 둠
        select(depth + 1, aCnt, bCnt, info, n, m);
        
        // a가 훔치는 경우
        select(depth + 1, aCnt + info[depth][0], bCnt - info[depth][1], info, n, m);
    }

}
