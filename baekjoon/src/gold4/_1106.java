package gold4;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
DP
memoization을 할 1차원 배열필요
적어도 C명을 늘리기 위해 C+(최대로 증가시킬 수 있는 고객수)까지의 비용을 구해봐야 최솟값을 알 수 있다.
 */

public class _1106 {

    private static final int MIN_INT = Integer.MIN_VALUE;
    private static final int MAX_INT = Integer.MAX_VALUE;
    private static final int MINUS_ONE = -1;

    public static void main(String[ ] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String CN = br.readLine();

        int C = Integer.parseInt(CN.split(" ")[0]);
        int N = Integer.parseInt(CN.split(" ")[1]);

        List<Info> infos = new ArrayList<>();

        int maxCustomer = MIN_INT;
        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            Info info = new Info(Integer.parseInt(input.split(" ")[0]), Integer.parseInt(input.split(" ")[1]));
            infos.add(info);
            if (info.customer > maxCustomer) {
                maxCustomer = info.customer;
            }
        }

        String minCost = promote(C, maxCustomer, infos);

        bw.write(minCost);
        bw.flush();

        br.close();
        bw.close();
    }

    private static String promote(int C, int maxCustomer, List<Info> infos) {
        int[] memCost = new int[C + maxCustomer];
        Arrays.fill(memCost, MINUS_ONE);
        memCost[0] = 0;

        for (int i = 1; i < C + maxCustomer; i++) {
            if (memCost[i] == MINUS_ONE) {
                memCost[i] = MAX_INT;
            }
            for (Info info : infos) {
                int idx = i - info.customer;
                if (idx < 0 || memCost[idx] == MINUS_ONE) {
                    continue;
                }

                if (memCost[i] > memCost[idx] + info.cost) {
                    memCost[i] = memCost[idx] + info.cost;
                }
            }
            if (memCost[i] == MAX_INT) {
                memCost[i] = MINUS_ONE;
            }
        }

        int minCost = MAX_INT;
        for (int i = C; i < C + maxCustomer; i++) {
            if (memCost[i] != -1 && minCost > memCost[i]) {
                minCost = memCost[i];
            }
        }

        return String.valueOf(minCost);
    }

    static class Info {
        int cost;
        int customer;

        private Info(int cost, int customer) {
            this.cost = cost;
            this.customer = customer;
        }
    }
}
