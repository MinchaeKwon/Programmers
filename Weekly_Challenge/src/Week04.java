/**
 * 직업군 추천하기
 * https://programmers.co.kr/learn/courses/30/lessons/84325
 * 
 * @author minchae
 * @date 2021. 9. 20.
 */

import java.util.HashMap;

public class Week04 {

	static HashMap<String, Integer> map = new HashMap<>();

	public static void main(String[] args) {
		String[] t1 = { "SI JAVA JAVASCRIPT SQL PYTHON C#", "CONTENTS JAVASCRIPT JAVA PYTHON SQL C++",
				"HARDWARE C C++ PYTHON JAVA JAVASCRIPT", "PORTAL JAVA JAVASCRIPT PYTHON KOTLIN PHP",
				"GAME C++ C# JAVASCRIPT C JAVA" };
		String[] l1 = { "PYTHON", "C++", "SQL" };
		int[] p1 = { 7, 5, 5 };

		String[] t2 = { "SI JAVA JAVASCRIPT SQL PYTHON C#", "CONTENTS JAVASCRIPT JAVA PYTHON SQL C++",
				"HARDWARE C C++ PYTHON JAVA JAVASCRIPT", "PORTAL JAVA JAVASCRIPT PYTHON KOTLIN PHP",
				"GAME C++ C# JAVASCRIPT C JAVA" };
		String[] l2 = { "JAVA", "JAVASCRIPT" };
		int[] p2 = { 7, 5 };

		System.out.println(solution(t1, l1, p1));
		System.out.println(solution(t2, l2, p2));
	}

	public static String solution(String[] table, String[] languages, int[] preference) {
		HashMap<String, Integer> hs = new HashMap<>();

		// 개발자가 사용하는 언어와 선호도 저장
		for (int i = 0; i < languages.length; i++) {
			hs.put(languages[i], preference[i]);
		}

		String answer = "";
		int max = 0;

		for (int i = 0; i < table.length; i++) {
			String[] job = table[i].split(" ");
			String title = job[0];

			int sum = 0;

			for (int j = 1; j < job.length; j++) {
				String lan = job[j];
				int point = 0;
				
				if (j == 1) {
					point = job.length;
				} else {
					point = job.length - j;
				}
				
				// 해당 언어가 개발자가 사용하는 언어일 경우
				if (hs.containsKey(lan)) {
					sum += hs.get(lan) * point;
				}
			}

			if (max <= sum) {
				// 점수가 같으면 사전순으로 비교 -> 음수일 경우는 사전순으로 더 빠른 것이기 때문에 넘어감
				if (max == sum) {
					if (answer.compareTo(title) < 0) {
						continue;
					}
				}

				max = sum;
				answer = title;
			}

		}

		return answer;
	}
	
}
