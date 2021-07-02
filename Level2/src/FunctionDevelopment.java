/**
 * Level2 스택/큐 - 기능개발
 * https://programmers.co.kr/learn/courses/30/lessons/42586
 * 
 * @author minchae
 * @date 2021. 7. 2.
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class FunctionDevelopment {

	public static void main(String[] args) {
		int[] p1 = {93, 30, 55};
		int[] s1 = {1, 30, 5};
		
		System.out.println(Arrays.toString(solution(p1, s1)));
		
		int[] p2 = {95, 90, 99, 99, 80, 99};
		int[] s2 = {1, 1, 1, 1, 1, 1};
		
		System.out.println(Arrays.toString(solution(p2, s2)));
	}
	
	public static int[] solution(int[] progresses, int[] speeds) {
		Queue<Integer> q = new LinkedList<>(); // 기능개발이 완료되는 시간을 저장
		
		for (int i = 0; i < progresses.length; i++) {
			int remain = (100 - progresses[i]) % speeds[i];
			int day = (100 - progresses[i]) / speeds[i];
			
			q.add(remain == 0 ? day : day + 1);
		}
		
		ArrayList<Integer> result = new ArrayList<>();
		
		while (!q.isEmpty()) {
			int function = 1;
			int cur = q.poll();
			
			// 어떤 기능을 개발하는데 다음 기능보다 더 오래 걸릴 경 해당 기능을 배포할 때 다음 기능도 같이 배포해야 하므로 function++을 해줌
			while (!q.isEmpty() && q.peek() <= cur) {
				function++;
				q.poll(); // 다음 기능도 같이 배포되므로 큐에서 빼줌
			}
			
			result.add(function);
		}
		
        int[] answer = result.stream().mapToInt(i -> i).toArray();
        return answer;
    }
}
