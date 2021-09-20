/**
 * 직업군 추천하기
 * https://programmers.co.kr/learn/courses/30/lessons/84325
 * 
 * @author minchae
 * @date 2021. 9. 20.
 */

import java.util.HashMap;

public class Week04 {
	
	public static void main(String[] args) {
		String[] t1 = {"SI JAVA JAVASCRIPT SQL PYTHON C#", "CONTENTS JAVASCRIPT JAVA PYTHON SQL C++", 
				"HARDWARE C C++ PYTHON JAVA JAVASCRIPT", "PORTAL JAVA JAVASCRIPT PYTHON KOTLIN PHP", "GAME C++ C# JAVASCRIPT C JAVA"};
		String[] l1 = {"PYTHON", "C++", "SQL"};
		int[] p1 = {7, 5, 5};
		
		String[] t2 = {"SI JAVA JAVASCRIPT SQL PYTHON C#", "CONTENTS JAVASCRIPT JAVA PYTHON SQL C++", "HARDWARE C C++ PYTHON JAVA JAVASCRIPT", 
				"PORTAL JAVA JAVASCRIPT PYTHON KOTLIN PHP", "GAME C++ C# JAVASCRIPT C JAVA"};
		String[] l2 = {"JAVA", "JAVASCRIPT"};
		int[] p2 = {7, 5};
		
		System.out.println(solution(t1, l1, p1));
		System.out.println(solution(t2, l2, p2));
	}
	
	public static String solution(String[] table, String[] languages, int[] preference) {
		HashMap<String, Integer> hs = new HashMap<>();
		
		// 사용하는 언어와 선호도 저장
		for (int i = 0; i < languages.length; i++) {
			hs.put(languages[i], preference[i]);
		}
		
		for (int i = 0; i < table.length; i++) {
			String[] job = table[i].split(" ");
			String title = job[0];
			
			int sum = 0;
			
			for (int j = 1; j < job.length; j++) {
				String lan = job[j];
				
				if (hs.containsKey(lan)) {
					sum += hs.get(lan);
				}
				
			}
			
		}
		
		
        String answer = "";
        return answer;
    }
}
