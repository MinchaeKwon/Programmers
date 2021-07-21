/**
 * Level 2 정렬 - H-Index
 * https://programmers.co.kr/learn/courses/30/lessons/42747
 * 
 * @author minchae
 * @date 2021. 7. 21.
 */

import java.util.Arrays;

public class HIndex {

	public static void main(String[] args) {
		int[] c = {3, 0, 6, 1, 5};
		System.out.println(solution(c));
	}

	public static int solution(int[] citations) {
		Arrays.sort(citations); // 오름차순 정렬
		
		for (int i = 0; i < citations.length; i++) {
			int h = citations.length - i;
			
			// 인용 횟수가 h편 이상인지 확인
			if (citations[i] >= h) {
				return h;
			}
		}
		
        return 0;
    }
	
}
