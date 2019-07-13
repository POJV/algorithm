package programmers.course30;

import java.util.Arrays;
import java.util.PriorityQueue;

public class Lesson42895WrongDraft {
	public static void main(String[] args) {
		int N = 5;
		int number = 23;
		Lesson42895WrongDraft lesson = new Lesson42895WrongDraft();
		System.out.println(lesson.solution(N, number));
	}
    public int solution(int N, int number) {
        int answer = 0;
        int max = N;
        PriorityQueue<Number> q = new PriorityQueue<>();
        int counter = 1;
        while(max < number) {
        	max = max * 10 + N;
        	++counter;
        }
        Number [] nArr = new Number[--counter+1];
        while(max > 0 ) {
        	nArr[counter] = new Number(max, counter+1);
        	q.add(nArr[counter--]);
        	max /= 10;
        }
        System.out.println(Arrays.toString(nArr));
        boolean found = false;
        while(!q.isEmpty()) {
        	Number current = q.poll();
        	if(current.count > 8) break;
        	if(current.value == number && current.count < 9) {
        		answer = current.count;
        		found = true;
        		System.out.println(current);
        		break;
        	}
        	
        	for(Number each : nArr) {
        		for( int i =1; i <= 4; i ++) {
            		q.add(new Number(current, each, i));        			
            	}
        	}
        	
        }
        return found  ? answer : -1;
    }
    class Number implements Comparable<Number>{
    	int value;
    	int count;
    	String operation="";
		public Number(int value, int count) {
			super();
			this.value = value;
			this.count = count;
			this.operation = String.valueOf(value);
		}
		public Number(Number prev, Number num, int operator) {
			switch(operator){
	    	case 1:
	    		value= prev.value + num.value;
	    		operation = prev.operation + "+" + num.value;
	    		count = prev.count + num.count;
	    		break;
	    	case 2:
	    		value= prev.value - num.value;
	    		operation = prev.operation + "-" + num.value;
	    		count = prev.count + num.count;
	    		break;
	    	case 3:
	    		value= prev.value * num.value;
	    		operation = "("+prev.operation + ")*" + num.value;
	    		count = prev.count + num.count;
	    		break;
	    	default:
	    		value= prev.value / num.value;
	    		operation = "("+prev.operation + ")/" + num.value;
	    		count = prev.count + num.count;
	    		break;
	    	}
			if( operator == 1 || operator == 2) {
				if(prev.operation.contains("/") && num.operation.contains("/")) count--;
				if(prev.operation.contains("*") && num.operation.contains("*")) count--;
			}
		}
		@Override
		public int compareTo(Number o) {
			return this.count - o.count;
		}
		@Override
		public String toString() {
			return "Number [value=" + value + ", count=" + count + ", operation=" + operation + "]";
		}
    }
}