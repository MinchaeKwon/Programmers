/**
 * Level 4 - 올바른 괄호의 갯수
 * https://school.programmers.co.kr/learn/courses/30/lessons/12929
 * 
 * @author minchae
 * @date 2026. 11. 10.
 */

public class NumOfBraket {

	int answer;
    
    public int solution(int n) {
        answer = 0;
        
        dfs(0, 0, n);
        
        return answer;
    }
    
    private void dfs(int open, int close, int n) {
        // 괄호 다 사용한 경우
        if (open == n && close == n) {
            answer++;
            return;
        }
        
        if (open < n) {
            dfs(open + 1, close, n);
        }
        
        // 닫는 괄호는 여는 괄호 갯수보다 적을 경우에만 사용 가능
        if (close < open) {
            dfs(open, close + 1, n);
        }
    }
	
}
