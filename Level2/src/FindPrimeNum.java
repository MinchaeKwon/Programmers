/**
 * 소수 찾기
 * https://school.programmers.co.kr/learn/courses/30/lessons/42839
 * 
 * @author minchae
 * @date 2024. 1. 17.
 */

import java.util.HashSet;

public class FindPrimeNum {
	
	static char[] nums;
	static boolean[] visited;
	
	static HashSet<Integer> hs = new HashSet<>(); // 중복 제거를 위해 사용

	public static void main(String[] args) {
		String numbers = "1231";
		
		System.out.println(solution(numbers));
	}
	
	public static int solution(String numbers) {
		nums = numbers.toCharArray();
		
		visited = new boolean[nums.length];
		
		permulation(0, "");
		
        return hs.size();
    }
	
	// 조합 사용해서 만들 수 있는 수 구함
	private static void permulation(int depth, String n) {
		if (!n.isEmpty()) {
			int num = Integer.parseInt(n);
			
			if (isPrime(num)) {
				hs.add(num);
			}
		}
		
		// 종료 조건
		if (depth == nums.length) {
			return;
		}
		
		for (int i = 0; i < nums.length; i++) {
			if (!visited[i]) {
				visited[i] = true;
				permulation(depth + 1, n + nums[i]);
				visited[i] = false;
			}
		}
	}
	
	// 소수 판별
	private static boolean isPrime(int num) {
		if (num <= 1) {
			return false;
		}
		
		for (int i = 2; i <= Math.sqrt(num); i++) {
			if (num % i == 0) {
				return false;
			}
		}
		
		return true;
	}

}
