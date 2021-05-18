/**
 * 전화번호 목록
 * https://programmers.co.kr/learn/courses/30/lessons/42577
 * 
 * @author minchae
 * @date 2021.5.18.
 */

import java.util.Arrays;

public class PhoneNumberList {

	public static void main(String[] args) {
		String[] s1 = {"119", "97674223", "1195524421"};
		String[] s2 = {"123","456","789"};
		String[] s3 = {"12","123","1235","567","88"};
		
		System.out.println(solution(s1));
		System.out.println(solution(s2));
		System.out.println(solution(s3));

	}
	
	public static boolean solution(String[] phone_book) {
		Arrays.sort(phone_book);
		for (String i : phone_book) {
			System.out.print(i + " ");
		}
		
		for (int i = 0; i < phone_book.length - 1; i++) {
			// 특정 문자열로 시작하는지 확인하기 위한 startWith를 사용해야함
			if (phone_book[i + 1].startsWith(phone_book[i])) {
				return false;
			}
			
			// 입력이 '2, 32'인 경우 32에서 2가 접두어가 아닌데도 2를 포함하고 있기때문에 false가 반환됨 -> 틀린 코드
//			if (phone_book[i + 1].contains(phone_book[i])) {
//				return false;
//			}
			
		}
		
		return true;
     
    }

}
