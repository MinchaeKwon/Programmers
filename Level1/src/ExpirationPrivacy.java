/**
 * 개인정보 수집 유효기간
 * https://school.programmers.co.kr/learn/courses/30/lessons/150370
 * 
 * @author Minchae
 * @date 2023. 11. 26.
 */

import java.util.ArrayList;
import java.util.HashMap;

public class ExpirationPrivacy {

	public static void main(String[] args) {
		String today = "2022.05.19";
		String[] terms = {"A 6", "B 12", "C 3"};
		String[] privacies = {"2021.05.02 A", "2021.07.01 B", "2022.02.19 C", "2022.02.20 C"};

		int[] result = solution(today, terms, privacies);
		
		for (int n : result) {
			System.out.print(n + " ");
		}
	}
	
	public static int[] solution(String today, String[] terms, String[] privacies) {
		HashMap<String, Integer> hm = new HashMap<>(); // 각 약관 별로 유효기간 저장
		
		for (String s : terms) {
			String[] term = s.split(" ");
			
			hm.put(term[0], Integer.parseInt(term[1]));
		}
		
		// 오늘 날짜
		String[] todayArr = today.split("\\.");
		
		int year = Integer.parseInt(todayArr[0]);
		int month = Integer.parseInt(todayArr[1]);
		int day = Integer.parseInt(todayArr[2]);
		
		ArrayList<Integer> answer = new ArrayList<>();
		
		for (int i = 0; i < privacies.length; i++) {
			String[] privacy = privacies[i].split(" ");
			
			String date = privacy[0];
			int expiration = hm.get(privacy[1]) * 28;
			
			String[] dateArr = date.split("\\.");
			
			int pass = (year - Integer.parseInt(dateArr[0])) * 12 * 28
					+ (month - Integer.parseInt(dateArr[1])) * 28
					+ (day - Integer.parseInt(dateArr[2]));
			
			if (pass >= expiration) {
				answer.add(i + 1);
			}
		}
		
		return answer.stream().mapToInt(i -> i).toArray();
    }

}
