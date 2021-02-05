/**
 * 징검다리 건너기
 * https://programmers.co.kr/learn/courses/30/lessons/64062
 * 
 * @author Minchae Gwon
 * @date 2021.2.5
 */

public class SteppingStone {

	public static void main(String[] args) {
		int[] stone = {2, 4, 5, 3, 2, 1, 4, 2, 5, 1};
		int k = 3;
		
		System.out.println(solution(stone, k));

	}
	
	public static int solution(int[] stones, int k) {
		int low = Integer.MAX_VALUE;
		int high = Integer.MIN_VALUE;
		
		for (int s : stones) {
			low = Math.min(low, s); //징검다리를 건널 수 있는 친구들의 최소값
			high = Math.max(high, s); //징검다리를 건널 수 있는 친구들의 최대값
		}
        
		if (low == high) {
			return low;
		}
		
        //low와 high가 같아질때까지 반복
        //첫번째 방법
        while (low < high) {
        	int mid = (low + high) / 2;
    		
        	//mid명까지는 지나갈 수 있을때 한명을 추가해도 지나갈 수 있는지 확인하기 위해 +1을 해줌
        	if (isPossible(stones, mid, k)) {
        		low = mid + 1;
        	} else {
        		high = mid;
        	}
        }
        
        return low - 1;
        
        //두번째 방법
//        while (low < high) {
//        	int mid = (low + high + 1) / 2;
//    		
//        	if (isPossible(stones, mid, k)) {
//        		low = mid;
//        	} else {
//        		high = mid - 1;
//        	}
//        }
//        
//        return low;
        
    }
	
	public static boolean isPossible(int[] stones, int mid, int k) {
		int jump = 0;
		
		for (int s : stones) {
			//0일때는 건너뛰지 않고 지나갈 수 있다는 의미이기 때문에 =를 붙이지 않고 마이너스일때만 jump++ 해줌
			if (s - mid < 0) { // if (s < mid)
				jump++;
			} else {
				jump = 0;
			}
			
			if (jump == k) {
				return false;
			}
		}
		
		return true;
	}

}
