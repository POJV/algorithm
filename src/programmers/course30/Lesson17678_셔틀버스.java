package programmers.course30;

import java.util.Arrays;

public class Lesson17678_셔틀버스 {
	
	public static void main(String[] args) {
		//testcase1
//		int n = 1;
//		int t = 1;
//		int m = 5;
//		String[] timetable = {"08:00", "08:01", "08:02", "08:03"};
		
		//testcase2
//		int n = 2;
//		int t = 10;
//		int m = 2;
//		String[] timetable = {"09:10", "09:09", "08:00"};
		
//		//testcase3
//		int n = 2;
//		int t = 1;
//		int m = 2;
//		String[] timetable = { "09:00", "09:00", "09:00", "09:00" };
		
//		//testcase4
//		int n = 1;
//		int t = 1;
//		int m = 5;
//		String[] timetable = { "00:01", "00:01", "00:01", "00:01", "00:01" };
		
		//testcase5
		int n = 1;
		int t = 1;
		int m = 1;
		String[] timetable = { "23:59" };
		
		//testcase6
//		int n = 10;
//		int t = 60;
//		int m = 45;
//		String[] timetable = { "23:59", "23:59","23:59","23:59","23:59","23:59","23:59","23:59","23:59","23:59","23:59","23:59","23:59","23:59","23:59","23:59" };
		
		Lesson17678_셔틀버스 bus = new Lesson17678_셔틀버스();
		String answer = bus.solution(n, t, m, timetable);
		System.out.println(answer);
	}
	
	public String solution(int n, int t, int m, String[] timetable) {
		int start = convertToIntMinutes("09:00");
		int end = start + (n - 1) * t;
		int lastBusTime = convertToIntMinutes("23:59");
		end = end > lastBusTime ? lastBusTime : end;

		int leftSeat = m;
		int lastDepartureTime = start;
		int lastDepartureCrewIndex = -1;
		Arrays.sort(timetable);
		String[] copy = Arrays.copyOf(timetable, timetable.length);
		
		for( int i = start; i <= end; i += t) {
			int pointer = 0;
			leftSeat = m;
			lastDepartureTime = i;
			while( pointer < m ) {
				if (pointer < copy.length && convertToIntMinutes(copy[pointer]) <= i) { // 탈수 있음 탄다.
					lastDepartureCrewIndex++;
					pointer++;
				} else {
					break; // 더이상 탈수 없으면 빠져 나온다. 
				}
			}
			if (pointer < copy.length) { // 아직 대기 크루가 있음  
				copy = Arrays.copyOfRange(copy, pointer, copy.length);	
			}
			leftSeat = m - pointer;
		}
		int konArrivedAt = lastDepartureTime;
		if( leftSeat == 0 && lastDepartureCrewIndex >= 0 ) konArrivedAt = convertToIntMinutes(timetable[lastDepartureCrewIndex]) - 1;
		return convertToString(konArrivedAt);
	}
	public String convertToString(int minutes) {
		int hours = minutes / 60;
		minutes -= 60 * hours;
		
		String time = String.format("%02d:%02d", hours, minutes);
		
		return time; 
	}
	public int convertToIntMinutes(String time) {
		String[] timeArr = time.split(":");
		int hours = Integer.parseInt(timeArr[0]);
		int minutes = Integer.parseInt(timeArr[1]);
		return minutes += hours * 60;
		
	}

}
