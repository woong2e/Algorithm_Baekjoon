package gold5;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class _1011 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int numTestCase = Integer.parseInt(br.readLine());
        while (numTestCase-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int count = FlyMeToTheAlphaCentauri(start, end);

            bw.write(count + "\n");
            bw.flush();
        }
        br.close();
        bw.close();
    }

    static int FlyMeToTheAlphaCentauri(int start, int end) {
        int dist = end - start;
        int max = (int)Math.sqrt(dist);

        if(max == Math.sqrt(dist)) {
            return max * 2 - 1;
        }
        else if(dist <= max * max + max) {
            return max * 2;
        }
        else {
            return max * 2 + 1;
        }
    }
}
