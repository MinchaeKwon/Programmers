/**
 * 숫자 문자열과 영단어
 * https://school.programmers.co.kr/learn/courses/30/lessons/81301
 * 
 * @author Minchae
 * @date 2023. 11. 25.
 */

import java.util.HashMap;

public class NumberString {

	public static void main(String[] args) {
		String s = "one4seveneight";

		System.out.println(solution(s));
	}
	
	public static int solution(String s) {
		HashMap<String, Integer> hm = new HashMap<>();
		
		hm.put("zero", 0);
		hm.put("one", 1);
		hm.put("two", 2);
		hm.put("three", 3);
		hm.put("four", 4);
		hm.put("five", 5);
		hm.put("six", 6);
		hm.put("seven", 7);
		hm.put("eight", 8);
		hm.put("nine", 9);
		
        String answer = "";
        
        char[] arr = s.toCharArray();
        
        String word = "";
        
        for (char c : arr) {
        	if (Character.isDigit(c)) {
        		answer += c;
        	} else {
        		word += c;
        		
        		if (hm.containsKey(word)) {
        			answer += hm.get(word);
        			word = "";
        		}
        	}
        }
        
        return Integer.parseInt(answer);
    }

}
