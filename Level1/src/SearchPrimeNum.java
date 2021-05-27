/**
 * 소수 찾기
 * https://programmers.co.kr/learn/courses/30/lessons/12921
 * 
 * @author minchae
 * @date 2021.5.27.
 */

public class SearchPrimeNum {

	public static void main(String[] args) {
		System.out.println(solution(10));
		System.out.println(solution(5));

	}

	public static int solution(int n) {
		int answer = 0;

		// 첫 번째 방법
//
//		for (int i = 2; i <= n; i++) {
//			boolean isPrime = true;
//
//			// 제곱근까지만 검사
//			for (int j = 2; j <= Math.sqrt(i); j++) {
//				if (i % j == 0) {
//					isPrime = false;
//					break;
//				}
//			}
//
//			if (isPrime) {
//				System.out.print(i + " ");
//				answer++;
//			}
//
//		}
//		System.out.println();

		// 두 번째 방법 - 에라토스테네스의 체 사용
		boolean[] isPrime = new boolean[n + 1];

		// 2 ~ n까지를 소수로 표시
		for (int i = 2; i <= n; i++) {
			isPrime[i] = true;
		}

		// 각 숫자의 배수들을 지움
		for (int i = 2; (i * i) <= n; i++) {
			// isPrime[i]가 true이면 i 이후의 i 배수는 약수로 i를 가지고 있는 것이므로 i 이후의 i 배수에 대해 false를 넣어줘야함
			if (isPrime[i]) {
				// i*i 미만은 이미 처리되었기 때문에 j의 시작값은 i*i로 개선 가능해짐 -> 2*j에서 i*i로 변경
				for (int j = (i * i); j <= n; j += i) {
					isPrime[j] = false;
				}
			}
		}

		for (int i = 2; i <= n; i++) {
			if (isPrime[i]) {
				answer++;
			}
		}

		// 세 번째 방법 - 에라토스테네스의 체 사용
//		boolean[] prime = new boolean[n + 1];
//        
//        for (int i = 2; i <= n; i++) {
//        	prime[i] = true;
//        }
//        
//        // 2부터 시작해서 해당 숫자의 배수를 false로 함
//        for (int i = 2; i <= n; i++) {
//            if (prime[i]) {
//                for (int j = 2 * i; j <= n; j += i) {
//                	prime[j] = false;
//                }
//            }
//        }
//        
//        for (int i = 2; i <= n; i++) {
//            if (prime[i]) {
//                answer++;
//            }
//        }

		return answer;

	}

}
