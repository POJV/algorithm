package programmers.course30;
import java.util.*;

public class Lesson42587_프린터 {
    public static void main(String[] args) {
//    	int[] priorities = new int[]{2, 1, 3, 2};
//    	int location = 2;
    	int[] priorities = new int[]{1, 1, 9, 1, 1, 1};
    	int location = 0;
        Lesson42587_프린터 lesson = new Lesson42587_프린터();
        long start = System.currentTimeMillis();
        System.out.println(lesson.solution(priorities, location));
        long end = System.currentTimeMillis();
        System.out.println(end-start);
    }
    
    
   
    public int solution(int[] priorities, int location) {
    	// 꺼낸다
    	// 프린트 할 수 있는지 판단한다 -> 남아 있는 대기 목록의 최댓값과 같아야 한다.  
    	// 내가 찾는앤지 판단한다 -> 처음 로케이션 정보를 기록해야 하고, 현재 위치도 알아야 한다. 
    	// 몇번째로 인쇄되는지 본다 -> 프린트 할 때마다 카운트를 샌다. 
    	Queue<Document> queue = new LinkedList<>();
    	for(int i = 0; i < priorities.length; i++) {
    		queue.add(new Document(i, priorities[i]));
    	}
    	Arrays.sort(priorities);
    	int pointer = priorities.length-1 ;
    	int count = 0;
    	while(!queue.isEmpty()) {
    		Document current = queue.poll();
    		if( current.order == priorities[pointer]) {
    			pointer--;
    			count++;
    			if( current.location == location ) {
    				break;
    			}
    		} else {
    			queue.add(current);
    		}
    	}
    	return count;
    }
    class Document {
    	int location;
    	int order;
		public Document(int location, int order) {
			super();
			this.location = location;
			this.order = order;
		}
    }
   
}