/**
 * Level 2 - 힌트 스테이지
 * https://school.programmers.co.kr/learn/courses/30/lessons/468377
 * 
 * @author minchae
 * @date 2026. 11. 10.
 */

public class HintStage {
	int n, answer;
    int[] hintCnt;
    
    public int solution(int[][] cost, int[][] hint) {
        n = cost.length;
        answer = Integer.MAX_VALUE;
        
        hintCnt = new int[n];
        
        dfs(0, cost, hint, 0);
        
        return answer;
    }
    
    private void dfs(int depth, int[][] cost, int[][] hint, int pay) {
        // 해결 비용이 최소 금액보다 넘어가면 종료 -> 가지치기
        if (answer <= pay) {
            return;
        }
        
        if (depth == n) {
            answer = pay;
            return;
        }
        
        // 각 스테이지에서 사용할 수 있는 힌트권 최대 개수는 n - 1개이기 때문에 hintCnt에 힌트권이 그보다 많으면 사용 불가
        int useHint = Math.min(hintCnt[depth], n - 1);
        int clearPay = cost[depth][useHint];
        
        // 힌트권 사지 않고 넘어갈 경우
        dfs(depth + 1, cost, hint, pay + clearPay);
        
        // 힌트 번들은 마지막 스테이지 전까지만 제공되므로 hint.length전까지만 힌트 구매 가능
        if (depth >= hint.length) {
            return;
        }
        
        int hintPay = hint[depth][0]; 
            
        // 힌트권 구매
        for (int i = 1; i < hint[depth].length; i++) {
            int stage = hint[depth][i] - 1;
            hintCnt[stage]++;
        }
            
        dfs(depth + 1, cost, hint, pay + clearPay + hintPay);
            
        // 원복
        for (int i = 1; i < hint[depth].length; i++) {
            int stage = hint[depth][i] - 1;
               hintCnt[stage]--;
        }
        
    }
}
