/**
 * Level 2 스택/큐 - 다리를 지나는 트럭
 * https://programmers.co.kr/learn/courses/30/lessons/42583
 * 
 * @author minchae
 * @date 2021. 7. 4.
 */

import java.util.LinkedList;
import java.util.Queue;

public class Truck {

	public static void main(String[] args) {
		int b1 = 2;
		int w1 = 10;
		int[] t1 = {7, 4, 5, 6};
		System.out.println(solution(b1, w1, t1));
		
		int b2 = 100;
		int w2 = 100;
		int[] t2 = {10};
		System.out.println(solution(b2, w2, t2));
		
		int b3 = 100;
		int w3 = 100;
		int[] t3 = {10, 10, 10, 10, 10, 10, 10, 10, 10, 10};
		System.out.println(solution(b3, w3, t3));
	}

	public static int solution(int bridge_length, int weight, int[] truck_weights) {
		Queue<Integer> q = new LinkedList<>();
		
		int answer = 0;
		int sum = 0; // 다리 위의 트럭 무게
		int idx = 0;
		
		// 모든 트럭을 다리에 다 올릴 때까지 반복
		while (idx < truck_weights.length) {			
			// 큐의 크기가 다리 길이랑 같은 경우(트럭이 다리를 다 건넜다는 의미)
			// 다리를 건넜기 때문에 다리 위 트럭 무게에서 해당 트럭 무게를 빼주고 큐에서도 빼줌
			if (q.size() == bridge_length) { 
				sum -= q.poll();
			}
			else { // 큐가 다 차지 않은 상태(다리 위에 트럭이 올라갈 수 있는 경우)일 경우 트럭이 다리 위에 올라갈 수 있는지 확인
				// weight(다리가 견딜 수 있는 무게)를 초과하는 경우
				// (현재 다리 위에 있는 트럭 무게 + 다리에 올라가야 하는 트럭 무게) > weight
				if (sum + truck_weights[idx] > weight) {
					// 이미 다리 위에 올라가있는 트럭이 다리를 건널 수 있도록 큐에 0을 추가하고 시간을 증가시킴
					q.add(0);
					answer++;
				}
				else {
					// weight보다 작은 경우 다리에 트럭이 올라갈 수 있으므로 다리에 트럭을 올림 -> 큐에 트럭을 추가
					q.add(truck_weights[idx]);
					sum += truck_weights[idx];
					answer++;
					
					idx++; // 다리에 트럭이 올라갈 경우 인덱스 증가
				}
			}
		}
		
		// 마지막 트럭이 올라가고 나서 반복문이 끝남 -> 해당 트럭이 다리를 지나가야 하므로 다리 길이를 더해줌
        return answer + bridge_length;
    }
	
}
