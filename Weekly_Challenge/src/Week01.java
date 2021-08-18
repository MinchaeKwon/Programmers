/**
 * 부족한 금액 계산하기
 * https://programmers.co.kr/learn/courses/30/lessons/82612
 * 
 * @author minchae
 * @date 2021. 8. 18.
 */

public class Week01 {
	
	public static void main(String[] args) {
		System.out.println(solution(3, 20, 4));
	}
	
	public static long solution(int price, int money, int count) {
		long total = 0;
		
		for (int i = 1; i <= count; i++) {
			total += price * i;
		}
		
		// 부족한 금액 계산
        long answer = total - money;

        return answer > 0 ? answer : 0;
        
    }
}
