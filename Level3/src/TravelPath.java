/**
 * 여행경로
 * https://school.programmers.co.kr/learn/courses/30/lessons/43164
 * 
 * @author minchae
 * @date 2024. 1. 25.
 */

import java.util.ArrayList;
import java.util.Collections;

public class TravelPath {
	
	static boolean[] visited;
	static ArrayList<String> result = new ArrayList<>();

	public static void main(String[] args) {
		String[][] tickets = {{"ICN", "JFK"}, {"HND", "IAD"}, {"JFK", "HND"}};

		String[] result = solution(tickets);
		
		for (String s : result) {
			System.out.print(s + " ");
		}
	}
	
	public static String[] solution(String[][] tickets) {
		visited = new boolean[tickets.length];
		
		dfs(0, "ICN", "ICN", tickets);
		
		Collections.sort(result); // 알파벳 순으로 정렬
		
        return result.get(0).split(" ");
    }
	
	private static void dfs(int depth, String start, String path, String[][] tickets) {
		if (depth == tickets.length) {
			result.add(path);
			return;
		}
		
		for (int i = 0; i < tickets.length; i++) {
			// 아직 방문하지 않았고, 현재 출발지와 탐색하려는 항공권의 출발지와 같은 경우
			if (!visited[i] && start.equals(tickets[i][0])) {
				visited[i] = true;
				dfs(depth + 1, tickets[i][1], path + " " + tickets[i][1], tickets);
				visited[i] = false;
			}
		}
	}

}
