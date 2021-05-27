/**
 * 예산
 * https://programmers.co.kr/learn/courses/30/lessons/12982
 * 
 * @author minchae
 * @date 2021.5.18.
 */

import java.util.Arrays;

public class Budget {

	public static void main(String[] args) {
		int[] d1 = {1,3,2,5,4};
		int b1 = 9;
		
		int[] d2 = {2,2,3,3};
		int b2 = 10;

		System.out.println(solution(d1, b1));
		System.out.println(solution(d2, b2));
		
	}
	
	public static int solution(int[] d, int budget) {
		Arrays.sort(d);
		
		int answer = 0;
		
		// 예산에서 각 부서의 신청 금액을 빼가면서 0보다 크거나 같을 경우에만 부서의 개수를 늘려줌
		// 0보다 작아지는 것은 예산을 초과하는 것
		for (int i = 0; i < d.length; i++) {
			budget -= d[i];
			
			if (budget >= 0) {
				answer++;
			} else {
				break;
			}
        }
		
        return answer;
    }

}
