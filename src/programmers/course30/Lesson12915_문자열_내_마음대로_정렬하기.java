package programmers.course30;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Lesson12915_문자열_내_마음대로_정렬하기 {
	public static void main(String[] args) {
//		String[] strings = {"car", "bed", "sun"};
//		int n = 1;
		String[] strings = {"abce", "abcd", "cdx"};
		int n = 2;
		Lesson12915_문자열_내_마음대로_정렬하기 lesson = new Lesson12915_문자열_내_마음대로_정렬하기();
		System.out.println(Arrays.toString(lesson.solution(strings, n)));
	}
	public String[] solution(String[] strings, int n) {
		ArrayList<String>[] map = new ArrayList[26]; 
		for(int i = 0; i<=('z'-'a'); i++) {
			map[i] = new ArrayList<>();
		}
		
		for( String s : strings ) {
			map[s.charAt(n)-'a'].add(s);
		}
		
		String[] answer = new String[strings.length];
		int index = 0 ;
		for(int i = 0; i<= ('z'-'a'); i++) {
			if( map[i].size() == 0 ) continue;
			Collections.sort(map[i]);
			for( String s : map[i] ) {
				answer[index++] = s;
			}
		}
		
		return answer;
	}
}
