/**
 * 길 찾기 게임
 * https://programmers.co.kr/learn/courses/30/lessons/42892
 * 
 * @author Minchae Gwon
 * @date 2021.1.12
 * 
 * 곤경에 빠진 카카오 프렌즈를 위해 이진트리를 구성하는 노드들의 좌표가 담긴 배열 nodeinfo가 매개변수로 주어질 때,
 * 노드들로 구성된 이진트리를 전위 순회, 후위 순회한 결과를 2차원 배열에 순서대로 담아 return 하도록 solution 함수를 완성하자.
 * 
 * nodeinfo는 이진트리를 구성하는 각 노드의 좌표가 1번 노드부터 순서대로 들어있는 2차원 배열이다.
 * 
 */

import java.util.ArrayList;
import java.util.Collections;

class Node implements Comparable<Node> {
	int x; //x좌표
	int y; //y좌표
	int data; //노드 값
	Node left;
	Node right;
	
	public Node(int x, int y, int data) {
		this.x = x;
		this.y = y;
		this.data = data;
	}
	
	@Override
	public int compareTo(Node node) {
		//y는 내림차순, x는 오름차순으로 정렬		
		//1을 리턴시키면 오름차순 정렬이 되며, -1을 리턴시키면 내림차순 정렬이 됨
		if (this.y == node.y) {
			return this.x - node.x;
		} else {
			return node.y - this.y;
		}
		
//		if (this.y == node.y) {
//			if (this.x > node.x) {
//				return 1;
//			} else {
//				return -1;
//			}
//		} else if (this.y < node.y) {
//			return 1;
//		} else {
//			return -1;
		
	}
}

public class SearchPath {
	
	static int[][] answer;
	static int idx = 0; //answer의 자리 값을 증가시키면서 전위, 후위순회 값을 넣어주기 위해 사용하는 변수
	
	public static void main(String[] args) {
		int[][] nodeInfo = {{5, 3}, {11, 5}, {13, 3}, {3, 5}, {6, 1}, {1, 3}, {8, 6}, {7, 2}, {2, 2}};
		solution(nodeInfo);
		
		for (int i = 0; i < answer.length; i++) {
			for (int j = 0; j < answer[0].length; j++) {
				System.out.print(answer[i][j] + "  ");
			}
			System.out.println();
		}
	}
	
	public static int[][] solution(int[][] nodeinfo) {
		int n = nodeinfo.length;
		ArrayList<Node> node = new ArrayList<>();
		
		for (int i = 0; i < n; i++) {
			node.add(new Node(nodeinfo[i][0], nodeinfo[i][1], i + 1));
		}
		
		//정렬
		Collections.sort(node);
		
		Node root = node.get(0); //y값이 제일 큰 것이 루트 노드
		for (int i = 1; i < n; i++) {
			makeTree(root, node.get(i));
		}
		
        answer = new int[2][n];
        
        preorder(root);
        idx = 0;
        postorder(root);
        
        return answer;
    }
	
	public static void makeTree(Node parent, Node child) {
		//부모의 x값보다 작으면 왼쪽 자식, 크면 오른쪽 자식노드임
		if (parent.x > child.x) {
			if (parent.left == null) { //현재 왼쪽 자식에 노드가 없을 경우에만 추가
				parent.left = child;	
			} else { //왼쪽 노드가 null이 아니라면 재귀를 통해서 들어갈 자리 찾음
				makeTree(parent.left, child);
			}
		} else {
			if (parent.right == null) {
				parent.right = child;
			} else {
				makeTree(parent.right, child);
			}
		}
	}
	
	public static void preorder(Node root) {
		answer[0][idx++] = root.data;
		if (root.left != null) preorder(root.left);
		if (root.right != null) preorder(root.right);
	}
	
	public static void postorder(Node root) {
		if (root.left != null) postorder(root.left);
		if (root.right != null) postorder(root.right);
		answer[1][idx++] = root.data;
	}
}
