/**
 * 순위 검색
 * https://programmers.co.kr/learn/courses/30/lessons/72412
 * 
 * @author minchae
 * @date 2022. 3. 2.
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

public class RankingSearch {

	static HashMap<String, ArrayList<Integer>> map;
	
	public static void main(String[] args) {
		String[] info = {"java backend junior pizza 150", "python frontend senior chicken 210", "python frontend senior chicken 150", 
				"cpp backend senior pizza 260", "java backend junior chicken 80", "python backend senior chicken 50"};
		
		String[] query = {"java and backend and junior and pizza 100", "python and frontend and senior and chicken 200",
				"cpp and - and senior and pizza 250", "- and backend and senior and - 150", "- and - and - and chicken 100", "- and - and - and - 150"};
		
		System.out.println(Arrays.toString(solution(info, query)));
	}
	
	public static int[] solution(String[] info, String[] query) {
		map = new HashMap<>();
		
		for (String s : info) {
			comb(s.split(" "), 0, ""); // 공백 제거
		}
		
		// 점수 오름차순 정렬
		for (String key : map.keySet()) {
			Collections.sort(map.get(key));
		}
		
		ArrayList<Integer> answer = new ArrayList<>();
		
		for (String s : query) {
			String[] split = s.replaceAll(" and ", "").split(" "); // 조건과 점수를 구분
			answer.add(binarySearch(split[0], Integer.parseInt(split[1])));
		}
		
        return answer.stream().mapToInt(Integer::intValue).toArray();
    }
	
	// 모든 경우의 수에 해당하는 점수 구하기
	public static void comb(String[] infoArr, int depth, String str) {
		if (depth == 4) {
			int score = Integer.parseInt(infoArr[4]);
			
			if (map.containsKey(str)) {
				map.get(str).add(score);
			} else {
				ArrayList<Integer> list = new ArrayList<>();
				list.add(score);
				map.put(str, list);
			}
			
			return;
		}
		
		comb(infoArr, depth + 1, str + "-");
		comb(infoArr, depth + 1, str + infoArr[depth]);
	}
	
	// 이분탐색
	public static int binarySearch(String key, int score) {
		// 해당 조건을 만족하는 점수가 없을 경우에는 return 0
		if (!map.containsKey(key)) {
			 return 0;
		}
		
		ArrayList<Integer> list = map.get(key);
		
		int start = 0;
		int end = list.size() - 1;
		
		while (start <= end) {
			int mid = (start + end) / 2;
			
			if (list.get(mid) < score) {
				start = mid + 1;
			} else {
				end = mid - 1;
			}
		}
		
		return list.size() - start;
	}

}
