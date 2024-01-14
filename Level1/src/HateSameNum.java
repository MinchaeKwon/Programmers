/**
 * 같은 숫자는 싫어
 * https://school.programmers.co.kr/learn/courses/30/lessons/12906?language=java
 * 
 * @author minchae
 * @date 2024. 1. 14.
 */

import java.util.Stack;

public class HateSameNum {

	public static void main(String[] args) {
		int[] arr = { 1, 1, 3, 3, 0, 1, 1 };
		
		System.out.println(solution(arr).toString());
	}

	public static Stack<Integer> solution(int[] arr) {
		Stack<Integer> stack = new Stack<>();
		
		for (int n : arr) {
			if (stack.isEmpty() || stack.peek() != n) {
				stack.push(n);
			}
		}
		
		return stack;
	}

}
