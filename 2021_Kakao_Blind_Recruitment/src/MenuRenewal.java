/**
 * 메뉴 리뉴얼
 * https://programmers.co.kr/learn/courses/30/lessons/72411
 * 
 * @author Minchae Gwon
 * @date 2021.2.4
 * 
 * orders에 있는 메뉴들을 course에 들어있는 단품메뉴 갯수만큼 조합을 구하고 조합을 구할때마다 HashMap에 추가
 * -> 갯수를 세고 2개 이상인 메뉴조합만 출력(ArrayList에 넣고 출력)
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

public class MenuRenewal {

	static HashMap<String, Integer> menuComb;
	
	public static void main(String[] args) {
		String[][] orders = {{"ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"}, {"ABCDE", "AB", "CD", "ADE", "XYZ", "XYZ", "ACD"}, {"XYZ", "XWY", "WXA"}};
		int[][] course = {{2, 3, 4}, {2, 3, 5}, {2, 3, 4}};
	
		for (int i = 0; i < 3; i++) {
			System.out.println(solution(orders[i], course[i]));
		}
		
	}
	
	public static ArrayList<String> solution(String[] orders, int[] course) {
		//결과를 보면 조합메뉴들이 오름차순으로 되어있으므로 반환해야 하므로 각 메뉴를 미리 정렬함 -> 조합을 구하기와 해당 조합의 개수 세기가 쉬워짐
		for (int i = 0; i < orders.length; i++) {
			//메뉴 정렬할 때 처음에 했던 방법
//			String[] menu = orders[i].split("");
//			Arrays.sort(menu);
//			
//			//orders 배열에 메뉴가 정렬되어서 들어가게됨
//			String tmp = "";
//			for (int j = 0; j < menu.length; j++) {
//				tmp += menu[j];
//			}
//			orders[i] = tmp;
			
			//코드가 길어서 다시 생각해본 방법 -> 문자열을 char형 배열로 바꿔주는 메소드를 사용함
			char[] menu = orders[i].toCharArray();
			Arrays.sort(menu);
			orders[i] = String.valueOf(menu);
			
		}
		
		ArrayList<String> answer = new ArrayList<>();
		
		//course에 들어있는 단품메뉴들의 개수만큼 조합 구하기
		for (int i = 0; i < course.length; i++) {
			menuComb = new HashMap<>(); //course[i]만큼 뽑힌 단품메뉴가 몇번 주문되었는지 확인하기 위한 HashMap
			
			for (int j = 0; j < orders.length; j++) {
				boolean[] visited = new boolean[orders[j].length()];
				comb(orders[j], visited, 0, course[i]);
			}
			
			//course[i] 개수만큼 뽑힌 단품메뉴 조합 중 가장 많이 주문된 단품메뉴 조합 횟수를 구함
			int max = Integer.MIN_VALUE;
			for (String key : menuComb.keySet()) {
				max = Math.max(max, menuComb.get(key));
			}
			
			//위에서 구한 가장 많이 주문된 횟수와 일치한 조합을 리스트에 추가
			for (String key : menuComb.keySet()) {
				//최소 2명 이상의 손님이 주문 했을 경우 && 가장 많이 주문된 단품메뉴 조합 횟수와 일치하는 조합일 경우
				if (menuComb.get(key) >= 2 && menuComb.get(key) == max) {
					answer.add(key);
				}
			}
		
		}
		
		//오름차순으로 정렬
		Collections.sort(answer);
		
        return answer;
    }
	
	//백트래킹을 이용한 조합
	public static void comb(String order, boolean[] visited, int start, int r) {
		if (r == 0) {
			String c = "";
			
			for (int i = 0; i < order.length(); i++) {
				if (visited[i]) {
					c += order.charAt(i) + "";
				}
			}
			
			if (!menuComb.containsKey(c)) {
				menuComb.put(c, 1);
			} else {
				menuComb.put(c, menuComb.get(c) + 1);
			}
			
			return;
			
		} else {
			for (int i = start; i < order.length(); i++) {
				visited[i] = true;
				comb(order, visited, i + 1, r - 1);
				visited[i] = false;
			}
		}
	}

}
