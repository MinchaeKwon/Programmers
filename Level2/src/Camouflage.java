/**
 * 위장
 * https://programmers.co.kr/learn/courses/30/lessons/42578
 * 
 * @author minchae
 * @date 2021.5.18.
 */

import java.util.HashMap;

public class Camouflage {

	public static void main(String[] args) {
		String[][] c1 = {{"yellowhat", "headgear"}, {"bluesunglasses", "eyewear"}, {"green_turban", "headgear"}};
		String[][] c2 = {{"crowmask", "face"}, {"bluesunglasses", "face"}, {"smoky_makeup", "face"}};
		
		System.out.println(solution(c1));
		System.out.println(solution(c2));

	}
	
	public static int solution(String[][] clothes) {
		HashMap<String, Integer> hs = new HashMap<String, Integer>();
		
		for (int i = 0; i < clothes.length; i++) {
			hs.put(clothes[i][1], hs.getOrDefault(clothes[i][1], 0) + 1);
		}
		
		int answer = 1;
		
		for (int val : hs.values()) {
			answer *= val + 1; // (val + 1)을 곱해주는 이유 -> 아무것도 입지 않는 경우도 포함되기 때문
		}
		
		answer -= 1; // 하루에 최소 한 개 이상의 의상은 입기때문에 아무것도 입지 않는 경우는 빼줌 -> 한 번만 빼주면 됨
		
        return answer;
    }

}
