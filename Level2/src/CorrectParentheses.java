/**
 * 올바른 괄호
 * https://school.programmers.co.kr/learn/courses/30/lessons/12909
 * 
 * @author minchae
 * @date 2024. 1. 15.
 */

import java.util.Stack;

public class CorrectParentheses {

	public static void main(String[] args) {
		String s = "()()";
		
		System.out.println(solution(s));

	}
	
	public static boolean solution(String s) {
		// 첫 번째 방법 - 스택 사용
        Stack<Character> stack = new Stack<>();
        
        for (int i = 0; i < s.length(); i++) {
        	Character c = s.charAt(i);
        	
        	if (c == '(') {
        		stack.push(c);
        	} else {
        		// 닫는 괄호인데 스택이 비어있는 경우 - 올바른 괄호가 아님 (여는 괄호가 없는 것이기 때문)
        		if (stack.isEmpty()) {
        			return false;
        		}
        		
        		stack.pop(); // 스택이 비어있지 않은 경우 여는 괄호 스택에서 빼줌
        	}
        }
        
        return stack.isEmpty();
        
        // 두 번째 방법
//		int cnt = 0;
//		
//		for (int i = 0; i < s.length(); i++) {
//			Character c = s.charAt(i);
//			
//			if (c == '(') {
//				cnt++;
//			} else {
//				cnt--;
//			}
//			
//			if (cnt < 0) {
//				return false;
//			}
//		}
//		
//		return cnt == 0;
    }

}
