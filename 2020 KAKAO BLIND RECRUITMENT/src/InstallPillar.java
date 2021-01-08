/*
 * 기둥과 보 설치
 * 2021.1.9
 * https://programmers.co.kr/learn/courses/30/lessons/60061
 * 
 * 벽면의 크기 n, 기둥과 보를 설치하거나 삭제하는 작업이 순서대로 담긴 2차원 배열 build_frame
 */

import java.util.ArrayList;

class Structure {
	int x;
	int y;
	int type;
	
	public Structure(int x, int y, int type) {
		this.x = x;
		this.y = y;
		this.type = type;
	}
}

class Solution {
	//기둥과 보가 같은 곳에 설치될 수 있기때문에 따로 관리할 수 있게 배열로 만듦
	static boolean[][] pillar;
	static boolean[][] bo;
	
	static final int PILLAR = 0;
	static final int BO = 1;
	static final int INSTALL = 1;
	static final int REMOVE = 0;
	
    public static int[][] solution(int n, int[][] build_frame) {
    	//구조물의 위치가 n까지 있을 수 있고 구조물 설치 확인을 할 때 -1이 나올수도 있어서 배열 크기를 [n + 2]로 함
        pillar = new boolean[n + 2][n + 2];
        bo = new boolean[n + 2][n + 2];
        
        ArrayList<Structure> list = new ArrayList<>(); //구조물 위치를 저장하기 위한 리스트
        
        for (int[] build : build_frame) {
        	int x = build[0] + 1; //+1을 해주는 이유는 기둥 설치 할 때 -1을 확인할수도 있기때문이다
        	int y = build[1] + 1; //보도 기둥과 마찬가지로 +1을 해줌
        	
        	if (build[2] == PILLAR) { //기둥일때
        		if (build[3] == INSTALL && isPillar(x, y)) { //x, y 위치에 기둥을 설치할 수 있으면
        			pillar[x][y] = true;
        		} else if (build[3] == REMOVE && isRemove(n, x, y, 0)) { //해당 위치의 기둥을 삭제할 수 있으면
        			pillar[x][y] = false;
        		}
        	} else { //보일때
        		if (build[3] == INSTALL && isBo(x, y)) { //x, y 위치에 보를 설치할 수 있으면
        			bo[x][y] = true;
        		} else if (build[3] == REMOVE && isRemove(n, x, y, 1)) { //해당 위치의 보를 삭제할 수 있으면
        			bo[x][y] = false;
        		}
        	}
        }
        
        //최종 구조물 위치를 리스트에 추가함
        for(int i = 1; i <= n + 1; i++){
            for(int j = 1; j <= n + 1; j++){
                if(pillar[i][j]){
                    list.add(new Structure(i - 1, j - 1, 0)); //실제 위치는 -1 해줘야함
                }
                if(bo[i][j]){
                    list.add(new Structure(i - 1, j - 1, 1));
                }
            }
        }
        
    	int[][] answer = new int[list.size()][3]; //x, y, 구조물 타입
    	
    	for (int i = 0; i < list.size(); i++) {
    		answer[i][0] = list.get(i).x;
    		answer[i][1] = list.get(i).y;
    		answer[i][2] = list.get(i).type;
    	}
    	
        return answer;
    }
    
    public static boolean isPillar(int x, int y) {
    	//바닥 위, 다른 기둥 위, 보의 한 쪽 끝 부분 위인지 확인
		return y == 1 || pillar[x][y - 1] || bo[x][y] || bo[x - 1][y];
	}
	
	public static boolean isBo(int x, int y) {
		//기둥 위, 양쪽 끝 부분이 다른 보와 동시에 연결되어 있는지 확인
		return pillar[x][y - 1] || pillar[x + 1][y - 1] || (bo[x - 1][y] && bo[x + 1][y]);
	}
	
	public static boolean isRemove(int n, int x, int y, int type) {
		boolean remove = true;
		
		//기둥이나 보를 임시로 삭제해봄
		if (type == PILLAR) {
			pillar[x][y] = false;
		} else {
			bo[x][y] = false;
		}
		
		//삭제한 뒤에도 규칙을 만족하는지 확인
		for (int i = 1; i <= n + 1; i++) {
			for (int j = 1; j <= n + 1; j++) {
				//일단 pillar[i][j]와 bo[i][j]로 해당 위치에 기둥이나 보가 있는지 확인
				//!isPillar(i, j)와 !isBo(i, j)를 사용해 규칙을 만족하지 않으면 remove를 false로 바꿈
				if (pillar[i][j] && !isPillar(i, j)) {
					remove = false;	
					break;
				}
				if (bo[i][j] && !isBo(i, j)) {
					remove = false;
					break;
				}
			}
		}
		
		//임시로 삭제했던 기둥이나 보를 원래대로 돌려놓음
		if (type == PILLAR) {
			pillar[x][y] = true;
		} else {
			bo[x][y] = true;
		}
		
		return remove; //삭제 가능하면 true 반환
	}
}

public class InstallPillar {

	public static void main(String[] args) {
//		int[][] build_frame = {{1,0,0,1}, {1,1,1,1}, {2,1,0,1}, {2,2,1,1}, {5,0,0,1}, {5,1,0,1}, {4,2,1,1}, {3,2,1,1}};
		int[][] build_frame = {{0, 0, 0, 1}, {2, 0, 0, 1}, {4, 0, 0, 1}, {0, 1, 1, 1}, {1, 1, 1, 1}, {2, 1, 1, 1}, {3, 1, 1, 1}, {2, 0, 0, 0}, {1, 1, 1, 0}, {2, 2, 0, 1}};
		
		int[][] answer = Solution.solution(5, build_frame);

		
		for (int i = 0; i < answer.length; i++) {
			System.out.println(answer[i][0] + ", " + answer[i][1] + ", " + answer[i][2]);
    	}
		
	}

}
