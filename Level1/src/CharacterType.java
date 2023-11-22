/**
 * 성격 유형 검사하기
 * https://school.programmers.co.kr/learn/courses/30/lessons/118666?language=java
 * 
 * @author Minchae
 * @date 2023. 11. 22.
 */

import java.util.HashMap;

public class CharacterType {

	public static void main(String[] args) {
		String[] survey = {"AN", "CF", "MJ", "RT", "NA"};
		int[] choices = {5, 3, 2, 7, 5};

		System.out.println(solution(survey, choices));
	}
	
	public static String solution(String[] survey, int[] choices) {
		HashMap<Character, Integer> hm = new HashMap<>();
		
		for (int i = 0; i < survey.length; i++) {
			int score = choices[i];
			
			if (score < 4) {
				Character type = survey[i].charAt(0);
				hm.put(type, hm.getOrDefault(type, 0) + 4 - score);
			} else if (score > 4) {
				Character type = survey[i].charAt(1);
				hm.put(type, hm.getOrDefault(type, 0) - 4 + score);
			}
		}
        
        String answer = (hm.getOrDefault('R', 0) >= hm.getOrDefault('T', 0) ? "R" : "T")
        		+ (hm.getOrDefault('C', 0) >= hm.getOrDefault('F', 0) ? "C" : "F")
        		+ (hm.getOrDefault('J', 0) >= hm.getOrDefault('M', 0) ? "J" : "M")
        		+ (hm.getOrDefault('A', 0) >= hm.getOrDefault('N', 0) ? "A" : "N");
        
        return answer;
    }

}
