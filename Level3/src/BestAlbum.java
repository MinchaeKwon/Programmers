/**
 * 베스트앨범
 * https://programmers.co.kr/learn/courses/30/lessons/42579
 * 
 * @author minchae
 * @date 2021.5.18.
 */

import java.util.HashMap;

public class BestAlbum {

	public static void main(String[] args) {
		String[] g = {"classic", "pop", "classic", "classic", "pop"};
		int[] p = {500, 600, 150, 800, 2500};
		
		System.out.println(solution(g, p));

	}
	
	public static int[] solution(String[] genres, int[] plays) {
		HashMap<String, Integer> hs = new HashMap<String, Integer>();
		
		for (int i = 0; i < genres.length; i++) {
			hs.put(genres[i], hs.getOrDefault(genres[i], 0) + plays[i]);
		}
		
        int[] answer = {};
        
        for (String key : hs.keySet()) {
        	
        }
        
        return answer;
    }

}
