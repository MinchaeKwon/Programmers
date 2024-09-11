/**
 * 가사 검색
 * https://school.programmers.co.kr/learn/courses/30/lessons/60060
 * 
 * @author minchae
 * @date 2024. 9. 11.
 */

import java.util.*;

public class LyricSearch {
	
	static class Node {
		HashMap<Character, Node> child;
		int cnt;
		
		public Node() {
			child = new HashMap<>();
		}
	}
	
	static class Trie {
		Node front;
		Node back;
		
		public Trie() {
			this.front = new Node();
			this.back = new Node();
		}
		
		public void insert(String word) {
			insertFront(word);
			insertBack(word);
		}
		
		private void insertFront(String word) {
			Node node = front;
			
			for (int i = 0; i < word.length(); i++) {
				char c = word.charAt(i);
				
				node = node.child.computeIfAbsent(c, key -> new Node());
				node.cnt++;
			}
		}
		
		private void insertBack(String word) {
			Node node = back;
			
			for (int i = word.length() - 1; i >= 0; i--) {
				char c = word.charAt(i);
				
				node = node.child.computeIfAbsent(c, key -> new Node());
				node.cnt++;
			}
		}
		
		public int search(String query) {
			if (query.charAt(0) == '?') {
				return searchBack(query);
			}
			
			return searchFront(query);
		}
		
		private int searchFront(String query) {
			Node node = front;
			
			for (int i = 0; i < query.length(); i++) {
				char c = query.charAt(i);
				
				if (c == '?') {
					break;
				}
				
				if (node.child.containsKey(c)) {
					node = node.child.get(c);
				} else {
					return 0;
				}
			}
			
			return node.cnt;
		}
		
		private int searchBack(String query) {
			Node node = back;
			
			for (int i = query.length() - 1; i >= 0; i--) {
				char c = query.charAt(i);
				
				if (c == '?') {
					break;
				}
				
				if (node.child.containsKey(c)) {
					node = node.child.get(c);
				} else {
					return 0;
				}
			}
			
			return node.cnt;
		}
	}

	public static void main(String[] args) {
		String[] words = {"frodo", "front", "frost", "frozen", "frame", "kakao"};
		String[] queries = {"fro??", "????o", "fr???", "fro???", "pro?"};
		
		System.out.println(Arrays.toString(solution(words, queries)));
	}
	
	public static int[] solution(String[] words, String[] queries) {
		Trie[] trie = new Trie[1000001];
		
		for (String word : words) {
			int len = word.length();
			
			if (trie[len] == null) {
				trie[len] = new Trie();
			}
			
			trie[len].insert(word);
		}
		
        int[] answer = new int[queries.length];
        
        for (int i = 0; i < queries.length; i++) {
        	int len = queries[i].length();
        	
        	if (trie[len] == null) {
        		continue;
        	}
        	
        	answer[i] = trie[len].search(queries[i]);
        }
        
        return answer;
    }

}
