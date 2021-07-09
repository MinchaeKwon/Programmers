/**
 * Level 2 힙 - 더 맵게
 * https://programmers.co.kr/learn/courses/30/lessons/42626
 * 
 * @author minchae
 * @date 2021. 7. 9.
 */

import java.util.PriorityQueue;

public class Spicy {

	public static void main(String[] args) {
		int[] scoville = {1, 2, 3, 9, 10, 12};
		int k = 7;
		
		System.out.println(solution(scoville, k));
	}
	
	public static int solution(int[] scoville, int K) {
		// 스코빌 지수를 기준으로 오름차순 정렬
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		
		for (int s : scoville) {
			pq.add(s);
		}
		
		int answer = 0; // 음식을 섞은 횟수
		
		// 큐의 맨 처음에 있는 값(스코빌 지수가 가장 낮은 음식)이 K보다 작은 동안에만 반복
		while (pq.peek() < K) {
			// K보다 작은데 우선순위 큐의 사이즈가 1인 경우에는 음식을 섞을 수 없기 때문에 return -1을 해줌
			if (pq.size() == 1) {
				return -1;
			}
			
			int food1 = pq.poll(); // 스코빌 지수가 가장 낮은 음식
			int food2 = pq.poll(); // 두 번째로 스코빌 지수가 낮은 음식
			
			// 두 개의 음식을 섞고 큐에 다시 추가
			int mix = food1 + (food2 * 2);
			pq.add(mix);
			
			answer++;
		}
		
        return answer;
    }

}
