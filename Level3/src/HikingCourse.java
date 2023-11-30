/**
 * 등산코스 정하기
 * https://school.programmers.co.kr/learn/courses/30/lessons/118669
 * 
 * @author Minchae
 * @date 2023. 12. 1.
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class HikingCourse {
	
	static class Node {
		int e;
		int w;
		
		public Node(int e, int w) {
			this.e = e;
			this.w = w;
		}
	}
	
	static ArrayList<Node>[] nodes;

	public static void main(String[] args) {
		int n = 6;
		int[][] paths = {{1, 2, 3}, {2, 3, 5}, {2, 4, 2}, {2, 5, 4}, {3, 4, 4}, {4, 5, 3}, {4, 6, 1}, {5, 6, 1}};
		int[] gates = {1, 3};
		int[] summits = {5};

		int[] result = solution(n, paths, gates, summits);
		
		for (int answer : result) {
			System.out.print(answer + " ");
		}
	}
	
	public static int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
		nodes = new ArrayList[n + 1];
		
		for (int i = 1; i <= n; i++) {
			nodes[i] = new ArrayList<>();
		}
		
		ArrayList<Integer> gateList = new ArrayList<>();
		
		for (int gate : gates) {
			gateList.add(gate);
		}
		
		ArrayList<Integer> summitList = new ArrayList<>();
		
		for (int summit : summits) {
			summitList.add(summit);
		}
		
		for (int[] path : paths) {
			int s = path[0]; // 시작점
			int e = path[1]; // 도착점
			int c = path[2]; // 시간
			
			// 출입구이거나 산봉우리인 경우 단방향만 저장
			if (gateList.contains(s) || summitList.contains(e)) {
				nodes[s].add(new Node(e, c));
			} else if (gateList.contains(e) || summitList.contains(s)) {
				nodes[e].add(new Node(s, c));
			} else {
				nodes[s].add(new Node(e, c));
				nodes[e].add(new Node(s, c));
			}
		}
		
        return dijkstra(n, gates, summits);
    }
	
	private static int[] dijkstra(int n, int[] gates, int[] summits) {
		Queue<Node> q = new LinkedList<>();
		int[] intensity = new int[n + 1];
		
		Arrays.fill(intensity, Integer.MAX_VALUE);
		
		// 출입구를 큐에 넣음 (경로가 출입구부터 시작하기 때문)
		for (int gate : gates) {
			q.add(new Node(gate, 0));
			intensity[gate] = 0;
		}
		
		while (!q.isEmpty()) {
			Node cur = q.poll();
			
			// 현재 가중치가 저장된 가중치보다 큰 경우 다음으로 넘어감
			if (cur.w > intensity[cur.e]) {
				continue;
			}
			
			// 최소 갱신
			for (Node next : nodes[cur.e]) {
				// intensity는 휴식 없이 이동해야 하는 시간 중 가장 긴 시간이기 때문에 max 함수를 사용함
				int dis = Math.max(intensity[cur.e], next.w);
				
				if (dis < intensity[next.e]) {
					intensity[next.e] = dis;
					q.add(new Node(next.e, dis));
				}
			}
		}
		
		int minSummit = Integer.MAX_VALUE;
		int minIntensity = Integer.MAX_VALUE;
		
		Arrays.sort(summits); // intensity가 최소가 되는 등산코스가 여러 개라면 그중 산봉우리의 번호가 가장 낮은 등산코스를 선택하기 때문에 정렬 해줌
		
		for (int summit : summits) {
			if (intensity[summit] < minIntensity) {
				minSummit = summit;
				minIntensity = intensity[summit];
			}
		}
		
		return new int[] {minSummit, minIntensity};
	}

}
