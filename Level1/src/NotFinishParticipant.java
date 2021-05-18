/**
 * 완주하지 못한 선수
 * https://programmers.co.kr/learn/courses/30/lessons/42576
 * 
 * @author minchae
 * @date 2021.5.18.
 */

import java.util.Arrays;
import java.util.HashMap;

public class NotFinishParticipant {

	public static void main(String[] args) {
		String[] p1 = {"leo", "kiki", "eden"};
		String[] c1 = {"eden", "kiki"};
		
		String[] p2 = {"marina", "josipa", "nikola", "vinko", "filipa"};
		String[] c2 = {"josipa", "filipa", "marina", "nikola"};
		
		String[] p3 = {"mislav", "stanko", "mislav", "ana"};
		String[] c3 = {"stanko", "ana", "mislav"};
		
		System.out.println(solution(p1, c1));
		System.out.println(solution(p2, c2));
		System.out.println(solution(p3, c3));

	}
	
	public static String solution(String[] participant, String[] completion) {
		
		// 방법 1 -> 해시 이용 X
		Arrays.sort(participant);
		Arrays.sort(completion);
		
		for (int i = 0; i < completion.length; i++) {
			if (!participant[i].equals(completion[i])) {
				return participant[i];
			}
		}
		
		return participant[completion.length];
		
		// 방법 2 -> 해시 이용함
//		HashMap<String, Integer> hs = new HashMap<String, Integer>();
//		
//		for (String s : participant) {
//			hs.put(s, hs.getOrDefault(s, 0) + 1);
//		}
//		
//		for (String s : completion) {
//			// 방법 2-1
////			if (hs.get(s) - 1 == 0) {
////				hs.remove(s);
////			}
//			
//			// 방법 2-2
//			hs.put(s, hs.get(s) - 1);
//		}
//		
//		String answer = "";
//		for (String key : hs.keySet()) {
//			// 방법 2-1
////			answer = key;
//			
//			// 방법 2-2
//			if (hs.get(key) == 1) {
//				answer = key;
//			}
//		}
//		
//		return answer;
		
    }

}
