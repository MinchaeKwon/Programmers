/**
 * 두 큐 합 같게 만들기
 * https://school.programmers.co.kr/learn/courses/30/lessons/118667
 * 
 * @author Minchae
 * @date 2023. 11. 23.
 */

import java.util.LinkedList;
import java.util.Queue;

public class QueueSum {
	
	public static void main(String[] args) {
        int[] queue1 = {3, 2, 7, 2};
        int[] queue2 = {4, 6, 5, 1};

        System.out.println(solution(queue1, queue2));
    }

    public static int solution(int[] queue1, int[] queue2) {
        int answer = 0;
        
        Queue<Integer> q1 = new LinkedList<>();
        Queue<Integer> q2 = new LinkedList<>();
        
        long total = 0;
        long q1Sum = 0;
        
        for (int i = 0; i < queue1.length; i++) {
        	total += queue1[i] + queue2[i];
        	q1Sum += queue1[i];
        	
        	q1.add(queue1[i]);
        	q2.add(queue2[i]);
        }
        
        // 합이 홀수인 경우에는 두 큐의 합을 같게 만들 수 없음
        if (total % 2 == 1) {
        	return -1;
        }
        
        long mid = total / 2;
        
        // 큐1의 합이 중간값과 같아질 때까지 반복
        while (q1Sum != mid) {
        	if (answer > (queue1.length + queue2.length) * 2) {
        		return -1;
        	}
        	
        	if (q1Sum > mid) { // 중간값보다 큐1의 합이 더 큰 경
        		int value = q1.poll();
        		
        		q1Sum -= value; // 큐2에 넣어줄거니까 -해줌
        		q2.add(value); // 큐2에 큐1에서 뺀 원소 값을 넣어줌
        	} else {
        		int value = q2.poll();
        		
        		q1Sum += value; // 큐1에 추가하는 것이기 때문에 +해줌
        		q1.add(value); // 큐2에서 뺀 원소 값을 큐1에 넣어줌
        	}
        	
        	answer++;
        }

        return answer;
    }

}

