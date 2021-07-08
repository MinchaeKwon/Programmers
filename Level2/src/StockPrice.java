/**
 * Level 2 스택/큐 - 주식가격
 * https://programmers.co.kr/learn/courses/30/lessons/42584
 * 
 * @author minchae
 * @date 2021. 7. 8
 */

public class StockPrice {

	public static void main(String[] args) {
		int[] prices = {1, 2, 3, 2, 3};
		
		int[] result = solution(prices);
		
		for (int r : result) {
			System.out.print(r + " ");
		}
	}
	
	public static int[] solution(int[] prices) {
		int[] answer =  new int[prices.length];
		
		for (int i = 0; i < prices.length; i++) {
			for (int j = i + 1; j < prices.length; j++) {
				answer[i]++;
				
				// 가격이 떨어지는 경우
				if (prices[i] > prices[j]) {
					break;
				}
			}
		}
		
		return answer;
    }

}
