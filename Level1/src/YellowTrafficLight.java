/**
 * Level 1 - 노란불 신호등
 * https://school.programmers.co.kr/learn/courses/30/lessons/468371
 * 
 * @author minchae
 * @date 2026. 11. 9.
 */

public class YellowTrafficLight {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public int solution(int[][] signals) {
        int len = signals.length;
        int maxTime = 1; // 완전 탐색하기 위한 최대 시간 -> 모든 신호등 주기의 최소 공배수
        
        for (int i = 0; i < len; i++) {
            int time = signals[i][0] + signals[i][1] + signals[i][2];
            maxTime = lcm(maxTime, time);
        }
        
        for (int t = 1; t <= maxTime; t++) {
            boolean yellow = true;
            
            for (int i = 0; i < len; i++) {
                int g = signals[i][0];
                int y = signals[i][1];
                int r = signals[i][2];
                
                int period = g + y + r;
                int position = (t - 1) % period; // 현재 주기 위치
                
                // 노란불 구간이 아닌 경우
                if (!(position >= g && position < g + y)) {
                    yellow = false;
                    break;
                }
            }
            
            if (yellow) {
                return t;
            }
        }
        
        return -1;
    }
    
    // 최소 공배수 구하기
    private int lcm(int a, int b) {
        return (a * b) / gcd(a, b);
    }
    
    // 최대 공약수 구하기
    private int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        
        return gcd(b, a %b);
    }

}
