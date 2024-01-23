/**
 * 단어 변환
 * https://school.programmers.co.kr/learn/courses/30/lessons/43163
 * 
 * @author minchae
 * @date 2024. 1. 23.
 */

public class WordTranslation {
	
	static boolean[] visited;
	static int answer;

	public static void main(String[] args) {
		String begin = "hit";
		String target = "cog";
		String[] words = {"hot", "dot", "dog", "lot", "log", "cog"};

		System.out.println(solution(begin, target, words));
	}
	
	public static int solution(String begin, String target, String[] words) {
		visited = new boolean[words.length];
		
        dfs(0, begin, target, words);
		
        return answer;
    }
	
	private static void dfs(int depth, String begin, String target, String[] words) {
		if (begin.equals(target)) {
			answer = depth;
			return;
		}
		
		for (int i = 0; i < words.length; i++) {
			if (visited[i]) {
				continue;
			}
			
			int cnt = 0;
			
			for (int j = 0; j < begin.length(); j++) {
				if (begin.charAt(j) == words[i].charAt(j)) {
					cnt++;
				}
			}
			
			// 한 글자 빼고 모두 같은 경우 해당 글자로 바꿀 수 있음
			if (cnt == begin.length() - 1) {
				visited[i] = true;
				dfs(depth + 1, words[i], target, words);
				visited[i] = false;
			}
			
		}
	}
	
	/*
	 * BFS
	 * 맨 처음 큐에 begin 넣고 words[i]와 비교하면서 변환 가능한 경우 해당 단어를 큐에 넣어서 횟수를 증가시킴 
	 * 그러다 target과 같은 경우 해당 횟수를 반환하면 됨
	 */

}
