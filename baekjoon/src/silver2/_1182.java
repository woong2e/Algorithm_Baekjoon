package silver2;

import java.io.*;
import java.util.StringTokenizer;

public class _1182 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer stInt = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(stInt.nextToken());
        int S = Integer.parseInt(stInt.nextToken());

        int[] sequence = new int[N];
        StringTokenizer stSequence = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            sequence[i] = Integer.parseInt(stSequence.nextToken());
        }
        int cnt = countSubsequenceSumEqualToS(sequence, N, S);
        bw.write(String.valueOf(cnt));
        bw.flush();

        br.close();
        bw.close();
    }

    static int countSubsequenceSumEqualToS(int[] sequence, int N, int S) {
        int theNumberOfSubsequence =  (int)Math.pow(2, N);
        int[] mem = new int[theNumberOfSubsequence];
        for (int i = 1; i <= N; i++) {
            int[] tmp = new int[theNumberOfSubsequence];
            for (int j = 0; j < (int)Math.pow(2, i)/2; j++) {
                tmp[j] = mem[j];
                tmp[j+(int)Math.pow(2, i)/2] = mem[j] + sequence[i-1];
            }
            mem = tmp.clone();
        }
        int cnt;
        if (S == 0) {
            cnt = -1;
        } else {
            cnt = 0;
        }
        for (int i = 0; i < theNumberOfSubsequence; i++) {
            if (mem[i] == S) {
                cnt++;
            }
        }
        return cnt;
    }
}
