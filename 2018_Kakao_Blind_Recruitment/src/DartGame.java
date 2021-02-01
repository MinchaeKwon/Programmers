/**
 * 다트 게임
 * https://programmers.co.kr/learn/courses/30/lessons/17682
 * 
 * @author Minchae Gwon
 * @date 2021.2.2
 */

import java.util.Stack;

public class DartGame {

	public static void main(String[] args) {
		String[] s = {"1S2D*3T", "1D2S#10S", "1D2S0T", "1S*2T*3S", "1D#2S*3S", "1T2D3D#", "1D2S3T*"};
		
		for (int i = 0; i < s.length; i++) {
			System.out.println(solution(s[i]));
		}
	}
	
	public static int solution(String dartResult) {
        Stack<Integer> stack = new Stack<>();
        String dartNum = "";
        
        for (int i = 0; i < dartResult.length(); i++) {
        	String cur = String.valueOf(dartResult.charAt(i));
        	
        	//숫자일 경우 10이 나올수도 있기 때문에 나온 숫자를 +로 붙여줌
        	if (cur.matches("[0-9]")) {
        		dartNum += cur;
        	}
        	else {
        		switch (cur) {
        		case "S":
        			stack.push(Integer.parseInt(dartNum));
            		dartNum = "";
            		
        			break;
        		case "D":
        			stack.push((int) Math.pow(Integer.parseInt(dartNum), 2));
            		dartNum = "";
            		
        			break;
        		case "T":
        			stack.push((int) Math.pow(Integer.parseInt(dartNum), 3));
            		dartNum = "";
            		
        			break;
        		case "*":
        			//현재 얻은 점수를 2배로 만듦
        			int star = stack.pop() * 2;
            		
        			//스택이 비어있지 않다는 것은 바로 전에 얻은 점수가 있다는 뜻이기 때문에 해당 점수도 2배를 만듦
        			if (!stack.empty()) {
            			int prev = stack.pop() * 2;
            			stack.push(prev);
            		}
            		stack.push(star);
            		
        			break;
        		case "#":
        			int n = stack.pop() * -1;
            		stack.push(n);
            		
        			break;
        		}
        	}
        	
        }
        
        int answer = 0;
        
        while (!stack.empty()) {
        	answer += stack.pop();
        }
        
        return answer;
    }

}
