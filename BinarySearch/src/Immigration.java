/**
 * 입국심사
 * https://programmers.co.kr/learn/courses/30/lessons/43238
 * 
 * @author Minchae Gwon
 * @date 2021.2.6
 */

import java.util.Arrays;

public class Immigration {

	public static void main(String[] args) {
		int[] times = {7, 10};
		
		System.out.println(solution(6, times));
	}
	
	public static long solution(int n, int[] times) {
		Arrays.sort(times);
		
		long low = times[0]; //첫번째 인덱스를 최소값으로 잡음
		long high = (long) times[times.length - 1] * (long) n; //맨 뒤에 있는 값에 인원 수를 곱한 값이 가장 많이 걸리는 시간임
		
		long mid = 0;
		
		while (low < high) {
			mid = (low + high) / 2;
			
			long people = 0;
			
			//mid 시간동안 심사한 인원 구하기
			for (int time : times) {
				people += mid / time;
			}
			
			//n명보다 적을 경우 더 많은 시간이 소요된다는 의미이므로 mid + 1을 해줌
			if (people < n) {
				low = mid + 1;
			}
			else { //mid 시간동안 심사한 인원이 n명 이상일 경우 high = mid을 통해 탐색 범위를 줄여줌
				high = mid;
			}
		}
		
        return low;
    }

}
