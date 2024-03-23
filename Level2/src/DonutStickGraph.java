/**
 * 도넛과 막대 그래프
 * https://school.programmers.co.kr/learn/courses/30/lessons/258711
 * 
 * @author minchae
 * @date 2024. 3. 23.
 */

import java.util.ArrayList;
import java.util.Collections;

public class DonutStickGraph {
	
	static ArrayList<Integer>[] list;
	static boolean[] visited;
	static int[] incoming; // 특정 정점에 들어오는 간선 개수 저장

	public static void main(String[] args) {
		 int[][] edges = {{2, 3}, {4, 3}, {1, 1}, {2, 1}};
//	      int[][] edges = {{4, 11}, {1, 12}, {8, 3}, {12, 7}, {4, 2}, {7, 11}, {4, 8}, {9, 6}, {10, 11}, {6, 10}, {3, 5}, {11, 1}, {5, 3}, {11, 9}, {3,8}};

		 int[] result = solution(edges);
		 
		 for (int n : result) {
			 System.out.print(n + " ");
		 }
	}
	
	public static int[] solution(int[][] edges) {
		int max = 0;
		
		for (int i = 0; i < edges.length; i++) {
			max = Math.max(max, Math.max(edges[i][0], edges[i][1]));
		}
		
		list = new ArrayList[max + 1];
		visited = new boolean[max + 1];
		incoming = new int[max + 1];
		
		for (int i = 0; i < list.length; i++) {
			list[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < edges.length; i++) {
			int a = edges[i][0];
			int b = edges[i][1];
			
			list[a].add(b);
			incoming[b]++;
		}
		
		int start = findStart();
		visited[start] = true;
		
		int total = list[start].size();
		
		removeStart(start);
		
		int stick = countStick(start);
		int eight = countEight();
		int donut = total - stick - eight;
		
        int[] answer = {start, donut, stick, eight};
        return answer;
    }
	
	// 시작 정점 찾기
	private static int findStart() {
		int result = -1;
		
		for (int i = 1; i < list.length; i++) {
			if (list[i].size() >= 2 && incoming[i] == 0) {
				result = i;
				break;
			}
		}
		
		return result;
	}
	
	// 시작 정점 연결 끊기
	private static void removeStart(int start) {
		for (int n : list[start]) {
			incoming[n]--;
		}
		
		list[start] = new ArrayList<>();
	}
	
	// 막대 그래프 찾기
	private static int countStick(int start) {
		int cnt = 0;
		
		for (int i = 1; i < list.length; i++) {
			if (i == start) {
				continue;
			}
			
			if (list[i].isEmpty()) {
				visited[i] = true;
				cnt++;
			}
		}
		
		return cnt;
	}
	
	// 8자 모양 그래프 찾기
	private static int countEight() {
		int cnt = 0;
		
		for (int i = 1; i < list.length; i++) {
			if (visited[i]) {
				continue;
			}
			
			if (list[i].size() == 2 && incoming[i] == 2) {
				visited[i] = true;
				cnt++;
			}
		}
		
		return cnt;
	}

}
