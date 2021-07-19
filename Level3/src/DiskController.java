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
		// 소요 시간을 기준으로 오름차순 정렬
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
		int time = 0; // 특정 작업 끝난 시간
        int answer = 0;
        
        while (cnt < jobs.length) {
        	while (idx < jobs.length && jobs[idx][0] <= time) {
        		pq.add(new Job(jobs[idx][0], jobs[idx][1]));
        	}
        	
        	if (!pq.isEmpty()) {
        		time = jobs[idx][0];
        	}
        	else {
        		Job job = pq.poll();
        		
        		answer += time + job.request + job.working;
        		time += job.working;
        		cnt++;
        	}
        }
        
        return answer / jobs.length;
    }

}
