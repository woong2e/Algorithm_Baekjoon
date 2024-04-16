package gold5;

import java.io.*;
import java.util.*;

/**
 * 이 문제는 백트래킹으로 풀 경우 시간초과가 발생하고, 물건을 분할 할 수 없어 그리디로 해결하면 안된다.
 * 따라서 경우의 수를 반복적으로 탐색하지 않기 위해
 * Memoization을 통해 이전까지의 최대 가치를 기록해두어 2차원 배열을 완성시키면 해결되는 DP문제이다.
 * Math.max(mem[i][j - 1], mem[i - item.getWeight()][j - 1] + item.getValue());
 * 0kg부터 시작하여 같은 무게일 때 이전 물건을 넣을 때와 현재 물건을 넣을 때 어느 경우가 더 큰 가치를 비교한다.
 * 배낭을 꽉 채웠을 때, 물건들이 각각 들어가는 경우를 살펴보면 된다.
 */

public class _12865 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        List<Item> items = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            StringTokenizer stItem = new StringTokenizer(br.readLine());
            items.add(new Item(Integer.parseInt(stItem.nextToken()), Integer.parseInt(stItem.nextToken())));
        }

        int maxValueSum = findMaxValueSum(items, N, K);

        bw.write(String.valueOf(maxValueSum));
        bw.flush();

        br.close();
        bw.close();
    }

    static int findMaxValueSum(List<Item> items, int N, int K) {
        int[][] mem = new int[K + 1][N + 1];
        int tmp;
        for (int i = 1; i < K + 1; i++) {
            for (int j = 1; j < N + 1; j++) {
                Item item = items.get(j - 1);
                if (i - item.getWeight() >= 0) {
                    tmp = mem[i][j];
                    mem[i][j] = Math.max(mem[i][j - 1], mem[i - item.getWeight()][j - 1] + item.getValue());
                } else {
                    mem[i][j] = mem[i][j - 1];
                }
            }
        }
        return mem[K][N];
    }
}

class Item {
    private final int weight;
    private final int value;

    public Item(int weight, int value) {
        this.weight = weight;
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public int getWeight() {
        return weight;
    }
}
