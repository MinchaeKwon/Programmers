/**
 * 오픈채팅방
 * https://programmers.co.kr/learn/courses/30/lessons/42888
 * 
 * @author Minchae Gwon
 * @date 2020.1.9
 * 
 * 채팅방에 들어오고 나가거나, 닉네임을 변경한 기록이 담긴 문자열 배열 record
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

class Record {
	int type; // 들어오면 0, 나가면 1 저장
	String id;
	
	public Record(int type, String id) {
		this.type = type;
		this.id = id;
	}
}

public class OpenChatting {

	public static void main(String[] args) {
		String[] record = {"Enter uid1234 Muzi", "Enter uid4567 Prodo","Leave uid1234","Enter uid1234 Prodo","Change uid4567 Ryan"};

		String[] result = solution(record);
		for (String s : result) {
			System.out.println(s);
		}
		
	}
	
	public static String[] solution(String[] record) {
    	HashMap<String, String> map = new HashMap<>();
    	ArrayList<Record> result = new ArrayList<>(); // 들어오고 나가는지와 uid를 저장하는 리스트
    	
    	for (String name : record) {
    		StringTokenizer st = new StringTokenizer(name);
    		
    		String start = st.nextToken();
    		String uid = st.nextToken();
    		String nick = null;
    		
			if (!start.equals("Leave")) {
    			nick = st.nextToken();	
    		}
    		
    		switch (start) {
	    		case "Enter":
	    			map.put(uid, nick);
	    			result.add(new Record(0, uid)); //들어오면 0 저장
	    			break;
	    		case "Change":
	    			map.put(uid, nick); // uid의 nickname이 변경됨
	    			break;
	    		case "Leave":
	    			result.add(new Record(1, uid)); //나가면 1 저장
	    			break;
    		}
    	}
    	
        String[] answer = new String[result.size()];
        
        for (int i = 0; i < result.size(); i++) {
        	Record r = result.get(i);
        	
        	switch (r.type) {
	        	case 0:
	        		answer[i] = map.get(r.id) + "님이 들어왔습니다.";
	        		break;
	        	case 1:
	        		answer[i] = map.get(r.id) + "님이 나갔습니다.";
	        		break;
        	}
        }
        
        return answer;
    }

}
