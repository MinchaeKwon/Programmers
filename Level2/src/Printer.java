/**
 * Level 2 스택/큐 - 프린터
 * https://programmers.co.kr/learn/courses/30/lessons/42587
 *  
 * @author minchae
 * @date 2021. 7. 3.
 */

import java.util.Comparator;
import java.util.PriorityQueue;

public class Printer {

	public static void main(String[] args) {
		int[] p1 = {2, 1, 3, 2};
		int l1 = 2;
		System.out.println(solution(p1, l1));
		
		int[] p2 = {1, 1, 9, 1, 1, 1};
		int l2 = 0;
		System.out.println(solution(p2, l2));
	}
	
	public static int solution(int[] priorities, int location) {
		// 중요도를 기준으로 내림차순 정렬
		PriorityQueue<Integer> q = new PriorityQueue<>(Comparator.reverseOrder());
		
		for (int p : priorities) {
			q.add(p);
		}
		
		int answer = 0;
		
		while (!q.isEmpty()) {
			for (int i = 0; i < priorities.length; i++) {
				// 큐 맨 앞에 있는 중요도와 priorities[i]가 같은 경우 순서 증가시킴
				if (q.peek() == priorities[i]) {
					answer++;
					q.poll();
					
					// 현재 위치와 요청한 문서의 위치가 같을 경우 바로 return
					if (i == location) {
						return answer;
					}
				}
			}
		}
		
        return answer;
    }

}
