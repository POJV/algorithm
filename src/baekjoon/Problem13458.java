package baekjoon;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class Problem13458 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] testPlace = new int[n+1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 1 ; i <= n; i++) {
			testPlace[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		int b = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		long answer = 0;
		for(int i = 1; i <= n ; i++) {
			testPlace[i] -= b;
			if(testPlace[i] < 0) testPlace[i] = 0;
			answer++;
			answer += (long)testPlace[i]/c;
			if(testPlace[i] % c != 0) {answer ++;}
		}
		System.out.println(answer);
	}
}
