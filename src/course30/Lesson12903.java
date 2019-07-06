package course30;

public class Lesson12903 {
	public static void main(String[] args) {
		Lesson12903 l = new Lesson12903();
		l.solution("nhi");
	}
	public String solution(String s) {
        int length = s.length();
        int pivot = length >> 1; 
        String answer = (length%2)==1 ?String.valueOf( s.charAt(pivot)) : s.charAt(pivot-1)+ "" +s.charAt(pivot);

        return answer;
	}
}
