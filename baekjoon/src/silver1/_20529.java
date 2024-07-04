package silver1;

import java.io.*;
import java.util.*;

/*
그냥 brute force 알고리즘 같은데 시간, 메모리 초과를 고려해야 한다. mbti는 E/I, N/S, T/F, J/P,
총 16가지로 구성되어 있으므로 33명 이상일 경우 심리적 거리의 최소값이 0일 수 밖에 없다. 이 점을 이용해서 시간초과 부분을 잡아냈다.
 */

public class _20529 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int numTestCase = Integer.parseInt(br.readLine());

        while (numTestCase-- > 0) {
            int num = Integer.parseInt(br.readLine());

            String[] mbti = new String[num];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < num; i++) {
                mbti[i] = st.nextToken();
            }

            int psychicDistance;

            if (num > 33) {
                psychicDistance = 0;
            } else {
                psychicDistance = findPsychicDistance(mbti, num);
            }

            bw.write(psychicDistance + "\n");
            bw.flush();
        }

        br.close();
        bw.close();
    }

    static int findPsychicDistance(String[] mbti, int num) {
        int minDist = Integer.MAX_VALUE;
        for (int i = 0; i < num - 2; i++) {
            for (int j = i + 1; j < num - 1; j++) {
                for (int k = j + 1; k < num; k++) {
                    int tmp = 0;
                    if (mbti[i] == mbti[j] && mbti[i] == mbti[k]) {
                        return 0;
                    }
                    for (int m = 0; m < 4; m++) {
                        if (mbti[i].charAt(m) != mbti[j].charAt(m)) {
                            tmp++;
                        }
                        if (mbti[j].charAt(m) != mbti[k].charAt(m)) {
                            tmp++;
                        }
                        if (mbti[i].charAt(m) != mbti[k].charAt(m)) {
                            tmp++;
                        }
                    }
                    if (minDist > tmp) {
                        minDist = tmp;
                    }
                }
            }
        }

        return minDist;
    }
}
