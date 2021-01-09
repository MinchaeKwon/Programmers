/**
 * 크레인 인형뽑기 게임
 * https://programmers.co.kr/learn/courses/30/lessons/64061
 * 
 * @author 권민채
 * @date 2020.4
 */

import java.util.*;

class Solution {
    public static int solution(int[][] board, int[] moves) {
        int answer = 0;
        
        Stack<Integer> stack = new Stack<>();
        
        for(int i = 0; i < moves.length; i++) {
            for(int j = 0; j < board.length; j++) {
                //뽑으려는 위치가 0이 아닐 때, board[j][moves[i] - 1]인 이유는 moves는 세로고, board는 가로이기 때문
                if(board[j][moves[i] - 1] != 0) { 
                    if(stack.isEmpty()) //스택이 비었다면
                        stack.push(board[j][moves[i] - 1]); //push
                    else { //스택이 안비었다면
                        if(stack.peek() == board[j][moves[i] - 1]) { //바구니의 인형과 넣으려는 인형이 같으면
                            stack.pop(); //pop
                            answer += 2; //사라진 인형 2개를 answer에 더함
                        }
                        else //바구니에 인형과 넣으려는 인형이 다르면
                            stack.push(board[j][moves[i] - 1]); //바구니에 push
                    }
                    board[j][moves[i] - 1] = 0; //크레인에서 뽑은거는 비어있도록 0으로 만들어줌
                    break;
                }
            }
        }
        
        return answer;
    }
}

public class CraneGame {
	public static void main(String[] args) {
		int[][] board = {{0, 0, 0, 0, 0}, {0, 0, 1, 0, 3}, {0, 2, 5, 0, 1}, {4, 2, 4, 4, 2}, {3, 5, 1, 3, 1}};
		int[] move = {1, 5, 3, 5, 1, 2, 1, 4};
		
		System.out.println(Solution.solution(board, move));

	}

}
