/**
 * 베스트앨범
 * https://programmers.co.kr/learn/courses/30/lessons/42579
 * 
 * @author minchae
 * @date 2021. 6. 28.
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

class Song implements Comparable<Song> {
	int id;
	String genre;
	int play;
	
	public Song(int id, String genre, int play) {
		this.id = id;
		this.genre = genre;
		this.play = play;
	}

	// 재생 횟수를 기준으로 내림차순 정렬, 재생 횟수가 같을 경우 고유 번호가 낮은 순으로 정렬
	@Override
	public int compareTo(Song o) {
		if (this.play == o.play) {
			return this.id - o.id;
		}
		
		return o.play - this.play;
	}	
}

public class BestAlbum {

	public static void main(String[] args) {
		String[] g = {"classic", "pop", "classic", "classic", "pop"};
		int[] p = {500, 600, 150, 800, 2500};
		
		int[] result = solution(g, p);
		
		for (int idx : result) {
			System.out.print(idx + " ");
		}

	}
	
	public static int[] solution(String[] genres, int[] plays) {
		HashMap<String, Integer> genreMap = new HashMap<String, Integer>();
		ArrayList<Song> songList = new ArrayList<>();
		
		// 장르 별로 재생 횟수를 HashMap에 저장, 노래 정보를 ArrayList에 저장
		for (int i = 0; i < genres.length; i++) {
			genreMap.put(genres[i], genreMap.getOrDefault(genres[i], 0) + plays[i]);
			songList.add(new Song(i, genres[i], plays[i]));
		}
		
		// 노래 수록 기준에 따라 정렬
		Collections.sort(songList, new Comparator<Song>() {
			@Override
			public int compare(Song o1, Song o2) {
				// 장르가 같을 경우 재생 횟수를 기준으로 내림차순 정렬
				if (o1.genre.equals(o2.genre)) {
					return o1.compareTo(o2);
				}
				else { // 장르가 다를 경우 더 많이 재생된 장르를 기준으로 내림차순 정렬
					return genreMap.get(o2.genre) - genreMap.get(o1.genre);
				}
			}
			
		});
		
		// 특정 장르 노래가 2개 있는지 확인하기 위한 HashMap
		HashMap<String, Integer> bestMap = new HashMap<String, Integer>();
		ArrayList<Integer> result = new ArrayList<>();
		
        for (Song song : songList) {
        	// 특정 장르의 노래가 베스트 앨범에 포함되어있지 않을 경우
        	if (!bestMap.containsKey(song.genre)) {
        		bestMap.put(song.genre, 1);
        		result.add(song.id);
        	}
        	else {
        		// 포함되어있고, 해당 장르 노래가 1개만 있을 경우 베스트 앨범에 추가
        		if (bestMap.get(song.genre) < 2) {
        			bestMap.put(song.genre, bestMap.get(song.genre) + 1);
        			result.add(song.id);
        		}
        	}
        }
        
        int[] answer = result.stream().mapToInt(Integer::intValue).toArray();;
        
        return answer;
    }

}
