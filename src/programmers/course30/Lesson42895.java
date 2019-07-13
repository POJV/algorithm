package programmers.course30;
import java.util.*;

public class Lesson42895 {
    public static void main(String[] args) {
        int N = 5;
        int number = 12;
//        int number = 23;
//        int number = 31168;
        Lesson42895 lesson = new Lesson42895();
        long start = System.currentTimeMillis();
        System.out.println(lesson.solution(N, number));
        long end = System.currentTimeMillis();
        System.out.println(end-start);
    }
    
    
    static final int PLUS = 1;
    static final int MINUS = 2;
    static final int MULTIPLY = 3;
    static final int DIVIDE= 4;
    static List<Number> list = new ArrayList<>();
    static Map<Integer, Integer> map = new HashMap<>(); 
    public int solution(int N, int number) {
        int answer = 0;
        PriorityQueue<Number> q = new PriorityQueue<>();
        
        int counter = 1;
        Number n = new Number(N, counter);
        q.add(n);
        list.add(n);
        map.put(N, counter);
        
        int NN = N;
        while(NN <= number) {
        	NN = NN * 10 + N;
        	Number temp = new Number(NN, ++counter);
        	list.add(temp);
        	q.add(temp);
        	map.put(NN, counter);
        }
        
        boolean found = false;
        while(!q.isEmpty()) {
            Number current = q.poll();
            if(current.count == 9) {
            	break;
            }
            
            if(current.value == number) {
            	answer = current.count;
            	found = true;
            	break;
            }
            
            if( !map.containsKey(n.value) || n.count < map.get(n.value) ){
            	list.add(current);
            	map.put(n.value, n.count);            	
            }
            Collections.sort(list);
            for(Number each : list) {
            	if(each.count + current.count > 9) break;
            	
        		addQueue(operate(each, current, PLUS), q);
        		
        		addQueue(operate(each, current, MINUS), q);
        		addQueue(operate(current, each, MINUS), q);
        		
        		addQueue(operate(each, current, MULTIPLY), q);
        		
        		addQueue(operate(current, each, DIVIDE), q);        			
                addQueue(operate(each, current, DIVIDE), q);        			
            }
        }
        
        return found  ? answer : -1;
    }
    void addQueue(Number n, Queue<Number> q) {
    	if( n.value > 0 && n.count < 9 ) {
			q.add(n);
		}
    }
    Number operate(Number one, Number another, int operator) {
    	Number number = new Number(5, 1);
        switch(operator){
            case PLUS:
            	number.value= one.value + another.value;
                break;
            case MINUS:
            	number.value= one.value - another.value;
                break;
            case MULTIPLY:
            	number.value= one.value * another.value;
            	number.units.get(MULTIPLY).add(number);
                break;
            case DIVIDE:
                if(another.value == 0)  return number;
            	number.value= one.value / another.value;
            	number.units.get(DIVIDE).add(number);
                break;
        }
        
        number.count = one.count + another.count;
    	number.units.get(MULTIPLY).addAll(one.units.get(MULTIPLY));
    	number.units.get(MULTIPLY).addAll(another.units.get(MULTIPLY));
    	number.units.get(DIVIDE).addAll(one.units.get(DIVIDE));
    	number.units.get(DIVIDE).addAll(another.units.get(DIVIDE));
    	
    	if( operator==PLUS || operator == MINUS) {
    		if( one.units.get(MULTIPLY).size() > 0 &&  another.units.get(MULTIPLY).size() > 0 ) {
    			number.count--;
    		}
    		if( one.units.get(DIVIDE).size() > 0 && another.units.get(DIVIDE).size() > 0 ) {
    			number.count--;    			
    		}
    	}
    	return number;
    }

    class Number implements Comparable<Number>{
        int value;
        int count;
        Map<Integer, List<Number>> units;
        
        public Number(int value, int count) {
            super();
            this.value = value;
            this.count = count;
            units = new HashMap<>();
            units.put(MULTIPLY, new ArrayList<>());
            units.put(DIVIDE, new ArrayList<>());
        }
        @Override
        public int compareTo(Number o) {
            return this.count - o.count;
        }
		@Override
		public String toString() {
			return "Number [value=" + value + ", count=" + count + "]";
		}
        public void print() {
        	for(Map.Entry<Integer, List<Number>> entry : this.units.entrySet()) {
        		System.out.print(entry.getKey()== MULTIPLY ? "MULTIPLY :" : "DIVIDE :");
        		System.out.println(entry.getValue().toString());
			}
        }
    }
}