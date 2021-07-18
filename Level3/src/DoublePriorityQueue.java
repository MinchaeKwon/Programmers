/**
 * 이중우선순위큐
 * https://programmers.co.kr/learn/courses/30/lessons/42628
 * 
 * @author minchae
 * @date 2021. 7. 18.
 */

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class DoublePriorityQueue {

	public static void main(String[] args) {
		String[] o1 = {"I 16","D 1"};
		String[] o2 = {"I 7","I 5","I -5","D -1"};
		
		System.out.println(Arrays.toString(solution(o1)));
		System.out.println(Arrays.toString(solution(o2)));
	}

	public static int[] solution(String[] operations) {
		PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
        
        for (String op : operations) {
        	StringTokenizer st = new StringTokenizer(op);
        	
        	String command = st.nextToken();
        	int data = Integer.parseInt(st.nextToken());
        	
        	switch (command) {
        	case "I":
        		// 데이터를 최소 힙, 최대 힙 두 개에 삽입
        		minHeap.add(data);
        		maxHeap.add(data);
        		
        		break;
        	case "D":
        		// 똑같은 데이터가 들어있기 때문에 두 개의 힙 중 한 개의 힙만 비어있는지 확인
        		if (!minHeap.isEmpty()) {
        			if (data == -1) {
        				// 최소 힙에서 최솟값 삭제 후 최대 힙에서도 같은 데이터를 삭제
            			int min = minHeap.poll();
            			maxHeap.remove(min);
            		}
            		else {
            			// 최대 힙에서 최댓값 삭제 후 최소 힙에서도 같은 데이터를 삭제
            			int max = maxHeap.poll();
            			minHeap.remove(max);
            		}
        		}
        		
        		break;
        	}
        }
        
        int[] answer = new int[2];
        
        answer[0] = maxHeap.isEmpty() ? 0 : maxHeap.poll();
        answer[1] = minHeap.isEmpty() ? 0 : minHeap.poll();
		
        return answer;
    }
	
}
