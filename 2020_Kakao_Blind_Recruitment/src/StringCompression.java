/**
 * 문자열 압축
 * https://programmers.co.kr/learn/courses/30/lessons/60057
 * 
 * @author Minchae Gwon
 * @date 2021.1.15
 * 
 * 압축할 문자열 s가 매개변수로 주어질 때, 1개 이상 단위로 문자열을 잘라 압축하여 표현한 문자열 중 
 * 가장 짧은 것의 길이를 return 하도록 solution 함수를 완성해주세요.
 */


public class StringCompression {

	public static void main(String[] args) {
		String[] s = {"aabbaccc", "ababcdcdababcdcd", "abcabcdede", "abcabcabcabcdededededede", "xababcdcdababcdcd"};
		
		for (int i = 0; i < s.length; i++) {
			System.out.println(solution(s[i]));
		}

	}
	
	public static int solution(String s) {	
		int answer = s.length();
		
		for (int i = 1; i <= s.length() / 2; i++) {
			String result = ""; //압축한 문자열
			String tmp = ""; //비교할 문자열
			int len = 1; //반복되는 문자열의 개수
			
			int j = 0;
			while (j + i <= s.length()) {
				String cut = s.substring(j, j + i);
				
				if (cut.equals(tmp)) {
					len++;
				} else {
					//문자가 반복되지 않아 한번만 나타난 경우 1은 생략하기 때문에 len이 1이상일때만 result에 추가해줌
					if (len > 1) {
						result += len;
					}
					result += tmp;
					tmp = cut;
					len = 1;
				}
				
				j += i;
			}
			
			if (len > 1) {
				result += len;
			}
			result += tmp;
			
			//size만큼 잘라지지 않는 나머지 문자열을 추가
			if (j < s.length()) {
				result += s.substring(j);
			}
			
			answer = Math.min(answer, result.length());
		}
		
        return answer;
    }

}
