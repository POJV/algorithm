package programmers.course30;

import java.util.Arrays;

public class Lesson42586_기능개발 {

	public static void main(String[] args) {
		
		Lesson42586_기능개발 l = new Lesson42586_기능개발();
		int[] progress = new int[]{93, 30, 55};
		int[] speeds = {1, 30, 5};
//		int[] progress = new int[]{99, 99, 99};
//		int[] speeds = {100, 100, 100};
		
		System.out.println(Arrays.toString(l.solution(progress, speeds)));
	}
	
	 public int[] solution(int[] progresses, int[] speeds){
	       int[] answer = {};

	       int[][] schedule = new int [progresses.length][1];
	       int days = 0;
	       boolean allCompleted = false;
	       while( !allCompleted ) {
	           allCompleted = true;
	           days++;
	           for(int i = 0 ; i < progresses.length; i++ ) {
	               if( schedule[i][0] != 0 ) {
	                   continue;
	               }
	               progresses[i] += speeds[i];
	               if (progresses[i] >= 100){
	                   schedule[i][0] = days;
	               } else {
	                  allCompleted = false;
	               }
	           }

	       }

	       int[] features = new int [progresses.length];
	       int size = 0;
	       for(int i = 0; i < progresses.length; i++ ){
	           features[size] = countFeatures(i, schedule);
	           i += features[size]-1;
	           size++;
	       }
	       answer = Arrays.copyOf(features, size);
	       return answer;
	   }

	   int countFeatures(int workOrder, int[][] schedule) {
	       int days = schedule[workOrder][0];
	       int count = 0;
	       for(int i = workOrder; i < schedule.length; i++){
	           if( schedule[i][0] <= days ) {
	               count++;
	           } else {
	               break;
	           }
	       }
	       return count;
	   }
}
