/**
 * 타겟 넘버
 * https://school.programmers.co.kr/learn/courses/30/lessons/43165
 * 
 * @author minchae
 * @date 2024. 1. 20.
 */

public class TargetNumber {
	
	static int answer;

	public static void main(String[] args) {
		int[] numbers = {1, 1, 1, 1, 1};
		int target = 3;

		System.out.println(solution(numbers, target));
	}
	
	public static int solution(int[] numbers, int target) {
        dfs(0, 0, numbers, target);
        
        return answer;
    }
	
	private static void dfs(int depth, int result, int[] numbers, int target) {
		if (depth == numbers.length) {
			// 결과가 target과 같은 경우 개수 증가
			if (result == target) {
				answer++;
			}
			
			return;
		}
		
		dfs(depth + 1, result + numbers[depth], numbers, target); // 더하는 경우
		dfs(depth + 1, result - numbers[depth], numbers, target); // 빼는 경우
	}

}
