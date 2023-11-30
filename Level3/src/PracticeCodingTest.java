/**
 * 코딩테스트 연습
 * https://school.programmers.co.kr/learn/courses/30/lessons/118668
 * 
 * @author Minchae
 * @date 2023. 11. 30.
 */

public class PracticeCodingTest {

	public static void main(String[] args) {
		int alp = 10;
		int cop = 10;
		int[][] problems = { { 10, 15, 2, 1, 2 }, { 20, 20, 3, 3, 4 } };

		System.out.println(solution(alp, cop, problems));
	}
	
	public static int solution(int alp, int cop, int[][] problems) {
        // 모든 문제를 풀 수 있는 알고력과 코딩력을 구함
        int alpGoal = 0;
        int copGoal = 0;
        
        for (int[] problem : problems) {
        	alpGoal = Math.max(problem[0], alpGoal);
        	copGoal = Math.max(problem[1], copGoal);
        }
        
        // 주어진 알고력과 코딩력이 목표치인 경우
        if (alp >= alpGoal && cop >= copGoal) {
        	return 0;
        }
        
        // 런타임에러 방지하기 위해 현재 알고력이 목표 알고력보다 높은 경우 목표 알고력을 맞춰줌
        if (alp >= alpGoal) {
        	alp = alpGoal;
        }
        
        if (cop >= copGoal) {
        	cop = copGoal;
        }
        
        int[][] dp = new int[alpGoal + 2][copGoal + 2];
        
        for (int i = alp; i <= alpGoal; i++) {
        	for (int j = cop; j <= copGoal; j++) {
        		dp[i][j] = Integer.MAX_VALUE;
        	}
        }
        
        dp[alp][cop] = 0; // 초기 알고력, 코딩력이 있는 곳은 시간이 0임
        
        for (int i = alp; i <= alpGoal; i++) {
        	for (int j = cop; j <= copGoal; j++) {
        		// 1시간 공부해서 알고력, 코딩력 높이기
        		dp[i + 1][j] = Math.min(dp[i + 1][j], dp[i][j] + 1);
        		dp[i][j + 1] = Math.min(dp[i][j + 1], dp[i][j] + 1);
        		
        		for (int[] problem : problems) {
        			int alpReq = problem[0];
        			int copReq = problem[1];
        			int alpRwd = problem[2];
        			int copRwd = problem[3];
        			int cost = problem[4];
        			
        			// 현재 문제를 풀 수 있는 경우
        			if (i >= alpReq && j >= copReq) {
        				if (i + alpRwd > alpGoal && j + copRwd > copGoal) { // 알고력, 코딩력이 목표치를 넘긴 경우
        					dp[alpGoal][copGoal] = Math.min(dp[alpGoal][copGoal], dp[i][j] + cost);
        				}
        				else if (i + alpRwd > alpGoal) { // 알고력이 목표치를 넘긴 경우
        					dp[alpGoal][j + copRwd] = Math.min(dp[alpGoal][j + copRwd], dp[i][j] + cost);
        				}
        				else if (j + copRwd > copGoal) { // 코딩력이 목표치를 넘긴 경우
        					dp[i + alpRwd][copGoal] = Math.min(dp[i + alpRwd][copGoal], dp[i][j] + cost);
        				}
        				else if (i + alpRwd <= alpGoal && j + copRwd <= copGoal) { // 둘 다 목표치를 초과하지 않는 경우
        					dp[i + alpRwd][j + copRwd] = Math.min(dp[i + alpRwd][j + copRwd], dp[i][j] + cost);
        				}
        			}
        		}
        	}
        }
        
        return dp[alpGoal][copGoal];
    }

}
