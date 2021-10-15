/**
 * 모음 사전
 * https://programmers.co.kr/learn/courses/30/lessons/84512
 * 
 * @author minchae
 * @date 2021. 10. 15.
 */

public class Week05 {

	public static void main(String[] args) {
		String s = "AAAAE";
		System.out.println(solution(s));
	}
	
	public static int solution(String word) {
		char[] alpha = {'A', 'E', 'I', 'O', 'U'}; // 사전에 있는 알파벳
		int[] diff = {781, 156, 31, 6, 1}; // -1하고 5로 나눈 값 (각각 시작하는 문자를 기준으로 781만큼 떨어져 있음: ex) E는 782번째부터 시작)
		
		int answer = 0;
		
		for (int i = 0; i < word.length(); i++) {
			for (int j = 0; j < alpha.length; j++) {
				if (word.charAt(i) == alpha[j]) {
					answer += diff[i] * j + 1; // (이전에 지나온 단어 개수 * 사전에서 지나온 알파벳 개수) + 1 == 해당 알파벳의 순서 + 1
				}
			}
		}
		
        return answer;
    }
	
}
