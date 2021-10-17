/**
 * 복서 정렬하기
 * https://programmers.co.kr/learn/courses/30/lessons/85002
 * 
 * @author minchae
 * @date 2021. 10. 17.
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

class Boxer implements Comparable<Boxer> {
	int num;
	int weight;
	Double winningRate;
	int heavyWin;
	
	public Boxer(int num, int weight, double winningRate, int heavyWin) {
		this.num = num;
		this.weight = weight;
		this.winningRate = winningRate;
		this.heavyWin = heavyWin;
	}
	
	// 승률을 기준으로 내림차순 정렬
	@Override
	public int compareTo(Boxer o) {
		// 승률을 기준으로 내림차순 정렬
//		if (!this.winningRate.equals(o.winningRate)) {
//			return o.winningRate.compareTo(this.winningRate);
//		}
//		
//		// 자신보다 몸무게가 무거운 복서를 이긴 횟수를 기준으로 내림차순 정렬
//		if (this.heavyWin != o.heavyWin) {
//			return o.heavyWin - this.heavyWin;
//		}
//		
//		// 복서의 몸무게를 기준으로 내림차순 정렬
//		if (this.weight != o.weight) {
//			return o.weight - this.weight;
//		}
//		
//		// 복서 번호를 기준으로 오름차순 정렬
//		return this.num - o.num;
		
		// 승률이 같을 경우 자신보다 몸무게가 무거운 복서를 이긴 횟수를 기준으로 내림차순 정렬
		if (this.winningRate.equals(o.winningRate)) {
			// 자신보다 몸무게가 무거운 복서를 이긴 횟수가 같을 경우 복서의 몸무게를 기준으로 내림차순 정렬
			if (this.heavyWin == o.heavyWin) {
				// 몸무게가 같을 경우 복서 번호를 기준으로 오름차순 정렬
				if (this.weight == o.weight) {
					return this.num - o.num;
				}
				
				return o.weight - this.weight;
			}
			
			return o.heavyWin - this.heavyWin;
		}
		
		return o.winningRate.compareTo(this.winningRate);
	}
}

public class Week06 {
	
	public static void main(String[] args) {
		int[] w = {60, 70, 60};
		String[] h = {"NNN", "NNN", "NNN"};
		
		System.out.println(Arrays.toString(solution(w, h)));
	}
	
	public static int[] solution(int[] weights, String[] head2head) {
		ArrayList<Boxer> boxerList = new ArrayList<>();
		
		for (int i = 0; i < weights.length; i++) {
			int count = 0;
			int win = 0;
			int heavyWin = 0;
			
			for (int j = 0; j < weights.length; j++) {
				char score = head2head[i].charAt(j);
				
				if (score != 'N') {
					count++; // 경기 개수 증가
					
					if (score == 'W') {
						win++; // 이긴 횟수 증가
						
						if (weights[i] < weights[j]) {
							heavyWin++; // 자신보다 몸무게가 무거운 복서를 이긴 횟수 증가
						}
					}
				}
			}
			
			double winningRate = 0;
			
			// 승률 계산 (전적이 있을 경우에만 계산)
			if (count > 0) {
				winningRate = (double) win / (double) count;	
			}
			
			boxerList.add(new Boxer(i + 1, weights[i], winningRate, heavyWin));
			
		}
		
		Collections.sort(boxerList); // 복서 정렬하기
		
        int[] answer = new int[weights.length];
        
        for (int i = 0; i < answer.length; i++) {
        	answer[i] = boxerList.get(i).num;
        }
        
        return answer;
    }
	
}
