/**
 * Level 3 힙 - 디스크 컨트롤러
 * https://programmers.co.kr/learn/courses/30/lessons/42627
 * 
 * @author minchae
 * @date 2021. 7. 19.
 */

import java.util.Arrays;
import java.util.PriorityQueue;

class Job implements Comparable<Job> {
	int request;
	int working;
	
	public Job(int request, int working) {
		this.request = request;
		this.working = working;
	}

	@Override
	public int compareTo(Job o) {
		// 처리 시간을 기준으로 오름차순 정렬
		return this.working - o.working;
	}
}

public class DiskController {

	public static void main(String[] args) {
		int[][] j = {{0, 3}, {1, 9}, {2, 6}};
		System.out.println(solution(j));
	}
	
	public static int solution(int[][] jobs) {		
		// 요청 시간을 기준으로 오름차순 정렬
		Arrays.sort(jobs, ((o1, o2) -> o1[0]-o2[0]));
		
		PriorityQueue<Job> pq = new PriorityQueue<>();

		int cnt = 0; // 처리된 디스크
		int idx = 0; // jobs의 인덱스
		int time = 0; // 현재 시간(특정 작업 끝난 시간)
        int answer = 0;
        
        // 모든 작업이 처리될 때까지 반복
        while (cnt < jobs.length) {
        	// 특정 작업이 처리되는 동안 들어오는 요청을 큐에 넣어줌
        	while (idx < jobs.length && jobs[idx][0] <= time) {
        		pq.add(new Job(jobs[idx][0], jobs[idx][1]));
        		idx++;
        	}
        	
        	if (!pq.isEmpty()) {
        		Job job = pq.poll();
        		
        		// (처리 시간 + 작업이 끝난 시간 - 요청 시간)을 더함
        		answer += job.working + time - job.request;
        		time += job.working;
        		cnt++;
        	}
        	else {
        		// 큐가 비어있는 경우 -> 특정 작업이 끝날 때까지 아무 작업도 요청하지 않는 것
            	// time을 특정 작업의 요청 시간으로 함
        		time = jobs[idx][0];
        	}
        }
        
        return answer / jobs.length;
    }

}
