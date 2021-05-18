/**
 * 소수 만들기
 * https://programmers.co.kr/learn/courses/30/lessons/12977
 * 
 * @author minchae
 * @date 2021.5.18.
 */

public class PrimeNumber {

	public static void main(String[] args) {
		int[] n1 = {1, 2, 3, 4};
		int[] n2 = {1, 2, 7, 6, 4};

		System.out.println(solution(n1));
		System.out.println(solution(n2));
	}
	
	public static int solution(int[] nums) {
        int answer = 0;

        for (int i = 0; i < nums.length; i++) {
        	for (int j = i + 1; j < nums.length; j++) {
        		for (int k = j + 1; k < nums.length; k++) {
        			int sum = nums[i] + nums[j] + nums[k];
        			
        			if (isPrime(sum)) {
                		answer++;
                	}
        		}
        	}
        }

        return answer;
    }
	
	public static boolean isPrime(int n) {
		for (int i = 2; i < n; i++) {
			// n이 아닌 수와 나누어 떨어진다면 소수가 아님
			if (n % i == 0) {
				return false;
			}
		}
		
		return true;
	}

}
