/**
 * 프렌즈4블록
 * https://programmers.co.kr/learn/courses/30/lessons/17679
 * 
 * @author Minchae Gwon
 * @date 2021.1.14
 * 
 * 입력으로 블록의 첫 배치가 주어졌을 때, 지워지는 블록은 모두 몇 개인지 판단하는 프로그램을 제작하라.
 */

public class Friends4Block {

	static boolean flag = true;
	
	public static void main(String[] args) {
		String[] board1 = {"CCBDE", "AAADE", "AAABF", "CCBBF"};
		String[] board2 = {"TTTANT", "RRFACC", "RRRFCC", "TRRRAA", "TTMMMF", "TMMTTJ"};
		
		System.out.println(solution(4, 5, board1));
		flag = true;
		System.out.println(solution(6, 6, board2));
		
	}
	
	public static int solution(int m, int n, String[] board) {
		String[][] block = new String[m][n]; //캐릭터의 좌표를 알기위해 2차원 배열로 저장함
		
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				block[i][j] = board[i].substring(j, j + 1);
			}
		}
		
		int answer = 0;
		
		while (flag) {
			answer += getDeleteBlock(m, n, block);
		}
		
        return answer;
    }
	
	//사라질 수 있는 블록 체크 후에 삭제함
	public static int getDeleteBlock(int m, int n, String[][] block) {
		int result = 0;
		boolean[][] isCheck = new boolean[m][n]; //삭제되는 블록 좌표 저장 후 삭제하기 위한 배열
		
		//같은 블록이 있는지 확인
		for (int i = 0; i < m - 1; i++) {
			for (int j = 0; j < n - 1; j ++) {
				//삭제된 좌표가 아니고, (현재 좌표,오른쪽, 아래, 오른쪽 대각선)의 캐릭터가 다 같은지 확인
				String cur = block[i][j];
				if (!cur.equals(".") && cur.equals(block[i][j + 1]) 
						&& cur.equals(block[i + 1][j]) && cur.equals(block[i + 1][j + 1])) {
					isCheck[i][j] = true;
					isCheck[i][j + 1] = true; //오른쪽
					isCheck[i + 1][j] = true; //아래
					isCheck[i + 1][j + 1] = true; //오른쪽 대각선
				}
			}
		}
		
		//블록 삭제하면서 바로 삭제된 부분 채워줌
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) 
				if (isCheck[i][j]) {
					block[i][j] = ".";
					result++;
		
					for (int k = i; k > 0; k--) {
						String tmp = block[k][j];
						block[k][j] = block[k - 1][j];
						block[k - 1][j] = tmp;
					}
				}
		}
		
//		for (int i = 0; i < m; i++) {
//			for (int j = 0; j < n; j++) {
//				System.out.print(block[i][j] + " ");
//			}
//			System.out.println();
//		}
//		
//		System.out.println("result - " + result);
		
		if (result == 0) {
			flag = false;
		}
		
		return result;
		
	}

}
