/**
 * 약수의 합
 * https://programmers.co.kr/learn/courses/30/lessons/12928
 * 
 * @author minchae
 * @date 2021.5.27.
 */

public class SumDivisor {

	public static void main(String[] args) {
		System.out.println(solution(12));
		System.out.println(solution(5));

	}
	
	public static int solution(int n) {
        int answer = 0;
        
        // 첫 번째 방법
//        for (int i = 1; i <= n; i++) {
//        	if (n % i == 0) {
//        		answer += i;
//        	}
//        }
//        return answer;
        
        // 두 번째 방법
        // 가장 큰 약수는 n을 2로 나누었을 때 나머지가 0인 경우이므로 n / 2까지만 반복함
        for(int i = 1; i <= n / 2; i++){
	          if(n % i == 0) {
	        	  answer += i;
	          }
	    }
        // 자기 자신도 약수이기 때문에 n을 더해준 값을 return
        return answer + n;
    }

}
