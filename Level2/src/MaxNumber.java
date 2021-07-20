/**
 * Level 2 정렬 - 가장 큰 수
 * https://programmers.co.kr/learn/courses/30/lessons/42746
 * 
 * @author minchae
 * @date 2021. 7. 20.
 */

import java.util.Arrays;

public class MaxNumber {

	public static void main(String[] args) {
		int[] n1 = {6, 10, 2};
		int[] n2 = {3, 30, 34, 5, 9};

		System.out.println(solution(n1));
		System.out.println(solution(n2));
	}
	
	public static String solution(int[] numbers) {
		String[] result = new String[numbers.length];
		
		for (int i = 0; i < numbers.length; i++) {
			result[i] = String.valueOf(numbers[i]);
		}
		
		// 내림차순 정렬
		Arrays.sort(result, (o1, o2) -> (o2 + o1).compareTo(o1 + o2));
		
		// numbers 배열에 0만 들어있을 경우 -> ex) {0, 0, 0}
		// 답이 000으로 나오게 되므로 배열의 첫 번째 값이 0이라면 0을 return
		if (result[0].equals("0")) {
			return "0";
		}
		
		String answer = "";
		
		for (String num : result) {
			answer += num;
		}
		
        return answer;
    }

}
