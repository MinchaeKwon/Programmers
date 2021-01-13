/**
 * 뉴스 클러스터링
 * https://programmers.co.kr/learn/courses/30/lessons/17677
 * 
 * @author Minchae Gwon
 * @date 2021.1.12
 * 
 * 입력으로 들어온 두 문자열의 자카드 유사도를 출력한다.
 * 유사도 값은 0에서 1 사이의 실수이므로, 이를 다루기 쉽도록 65536을 곱한 후에 소수점 아래를 버리고 정수부만 출력한다.
 */

import java.util.ArrayList;

public class NewsClustering {

	public static void main(String[] args) {
		String[] str1 = {"FRANCE", "handshake", "aa1+aa2", "E=M*C^2"};
		String[] str2 = {"french", "shake hands", "AAAA12", "	e=m*c^2"};
		
		for (int i = 0; i < str1.length; i++) {
			System.out.println(solution(str1[i], str2[i]));
		}
	}
	
	public static int solution(String str1, String str2) {
		//대문자와 소문자의 차이는 무시하므로 소문자로 바꾸고 사용함
		str1 = str1.toLowerCase();
		str2 = str2.toLowerCase();
		
		ArrayList<String> list1 = new ArrayList<>();
		ArrayList<String> list2 = new ArrayList<>();
		
		for (int i = 0; i < str1.length() - 1; i++) {
			String cut = str1.substring(i, i + 2);
			
			//영문자로 되어있을때만 추가함
			if (cut.matches("^[a-z]*$")) {
				list1.add(cut);
			}
		}
		
		for (int i = 0; i < str2.length() - 1; i++) {
			String cut = str2.substring(i, i + 2);
			
			if (cut.matches("^[a-z]*$")) {
				list2.add(cut);
			}
		}
		
		int answer = getJaccard(list1, list2);
		
		return answer;
    }
	
	public static int getJaccard(ArrayList<String> list1, ArrayList<String> list2) {
		int result;
		
		//두 집합이 공집합인 경우
		if (list1.size() == 0 && list2.size() == 0) {
			result = 65536;
		} else {
			double intersection = 0;
			double union = 0;

			
			//교집합, 합집합 개수 구하는 첫번째 방법
//			for (int i = 0; i < list1.size(); i++) {
//				String check = list1.get(i);
//				
//				if (list2.contains(check)) {
//					intersection++;
//					list2.remove(check);
//					/* 공통된 원소를 제거해줘야 교집합의 개수를 정확하게 알 수 있음
//					 * -> ex) list1이 (aa, aa, aa), list2가 (aa, aa)일 경우 list2 원소를 제거해주지 않으면
//					 * 교집합 개수가 3이 된다.(원래 교집합 개수는 2여야함) 그래서 값을 삭제해야 정학한 교집합 개수를 알 수 있다.
//					 */
//				}
//				union++; //포함되지 않을 때 list1의 크기만큼 합집합 수를 증가시킴
//			}
//			
//			/* list1과 list2의 공통되는 원소의 개수는 이미 합집합에 추가된 상태고,
//			 * 위의 if문에서 list2에서 list1과 공통된 원소는 삭제했기 때문에 list2에는 현재 list1과 공통된 원소를 제외한 나머지 원소만 남아있음
//			 * list2에서의 남은 원소 개수만큼 합집합 수를 증가시킴
//			 */
//			for (int i = 0; i < list2.size(); i++) {
//				union++;
//			}
			
			//교집합, 합집합 개수 구하는 두번째 방법
			union = list2.size() + list1.size();
			for (int i = 0; i < list1.size(); i++) {
				String check = list1.get(i);
				
				if (list2.contains(check)) {
					intersection++;
					list2.remove(check);
					/* 공통된 원소를 제거해줘야 교집합의 개수를 정확하게 알 수 있음
					 * -> ex) list1이 (aa, aa, aa), list2가 (aa, aa)일 경우 list2 원소를 제거해주지 않으면
					 * 교집합 개수가 3이 된다.(원래 교집합 개수는 2여야함) 그래서 값을 삭제해야 정학한 교집합 개수를 알 수 있다.
					 */
				}
			}
			
			//모든 집합의 개수를 더한 후 교집합의 개수를 빼면 합집합의 개수가 나옴
			union -= intersection;
			
			//자카드 유사도 구하기
			result = (int)(65536 * (intersection / union));
		}

		return result;
	}

}
