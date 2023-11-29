/**
 * 표 편집
 * https://school.programmers.co.kr/learn/courses/30/lessons/81303
 * 
 * @author Minchae
 * @date 2023. 11. 29.
 */

import java.util.Stack;

class Node {
	Node prev = null;
	Node next = null;
	boolean isDelete = false;
}

public class EditTable {

	public static void main(String[] args) {
		int n = 8;
		int k = 2;
		String[] cmd = {"D 2","C","U 3","C","D 4","C","U 2","Z","Z"};

		System.out.println(solution(n, k, cmd));
	}
	
	public static String solution(int n, int k, String[] cmd) {
		Node[] nodes = new Node[n];
		
		nodes[0] = new Node();
		
		for (int i = 1; i < n; i++) {
			nodes[i] = new Node();
			nodes[i].prev = nodes[i - 1];
			nodes[i - 1].next = nodes[i];
		}
		
		Stack<Node> delete = new Stack<>(); // 복구를 위해 스택을 사용
		
		Node cur = nodes[k]; // 현재 노드
		
		for (String s : cmd) {
			char order = s.charAt(0);
			
			switch (order) {
			case 'U':
				int cnt = Integer.parseInt(s.split(" ")[1]);
				
				// cnt만큼 위로 올라감 -> prev를 선택하면 됨
				for (int i = 0; i < cnt; i++) {
					cur = cur.prev;
				}
				
				break;
			case 'D':
				cnt = Integer.parseInt(s.split(" ")[1]);
				
				// cnt만큼 아래로 내려감 -> next를 선택하면 됨
				for (int i = 0; i < cnt; i++) {
					cur = cur.next;
				}
				
				break;
			case 'C':
				cur.isDelete = true;
				
				delete.add(cur);
				
				Node prev = cur.prev;
				Node next = cur.next;
				
				if (prev != null) {
					prev.next = next; // 현재 이전 노드의 다음 노드를 현재 다음 노드로 변경
				}
				
				if (next != null) {
					next.prev = prev; // 현재 다음 노드의 이전 노드를 현재 이전 노드로 변경
					cur = next; // 다음 행을 선택
				} else {
					// 마지막 행인 경우 next가 null이기 때문에 이전 행을 선택함
					cur = prev;
				}
				
				break;
			case 'Z':
				Node node = delete.pop(); // 가장 최근에 삭제된 노드 가져옴
				node.isDelete = false;
				
				prev = node.prev;
				next = node.next;
				
				if (prev != null) {
					prev.next = node; // 이전 노드의 다음 노드를 삭제된 노드로 복구
				}
				
				if (next != null) {
					next.prev = node; // 다음 노드의 이전 노드를 삭제된 노드로 복구
				}
				
				break;
			
			}
		}
		
		StringBuilder sb = new StringBuilder();
        
        for (Node node : nodes) {
        	sb.append(node.isDelete ? "X" : "O");
        }
        
        return sb.toString();
    }

}
