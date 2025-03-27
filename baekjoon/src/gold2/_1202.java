package gold2;

import java.io.*;
import java.util.*;
/*
그리디 알고리즘
이 문제에서의 핵심은 보석과 가방을 정렬하는 기준과 우선순위큐를 어떻게 활용할 것인지에 대한 것이다.
가방에 담을 수 있는 최대 무게가 작은 것부터 시작하여 각 가방마다 담을 수 있는 보석을 모두 우선순위큐에 담아
가장 비싼 보석을 선택하는 방식으로 해결하였다.
가방에 담을 수 있는 최대 무게가 작은 것부터 시작한 이유는 보석 무게가 작은 것은 최대 무게가 큰 가방에도 들어갈 수 있기 때문
 */

public class _1202 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String input = br.readLine();
        int N = Integer.parseInt(input.split(" ")[0]);
        int K = Integer.parseInt(input.split(" ")[1]);

        Jewel[] shop = new Jewel[N];
        for (int i = 0; i < N; i++) {
            int[] input2 = Arrays.stream(br.readLine().split(" "))
                            .mapToInt(Integer::parseInt)
                                    .toArray();
            shop[i] = new Jewel(input2[0], input2[1]);
        }
        Arrays.sort(shop, Comparator.comparing(jewel -> jewel.weight));

        int[] bags = new int[K];
        for (int i = 0; i < K; i++) {
            int maxWeight = Integer.parseInt(br.readLine());
            bags[i] = maxWeight;
        }
        Arrays.sort(bags);


        long maxPriceSum = steal(shop, bags);

        bw.write(String.valueOf(maxPriceSum));
        bw.flush();

        br.close();
        bw.close();
    }

    static long steal(Jewel[] shop, int[] bags) {
        PriorityQueue<Long> pq = new PriorityQueue<>(Collections.reverseOrder());
        long maxPriceSum = 0;
        int idx = 0;
        for (int bag : bags) {
            while (idx < shop.length && shop[idx].weight <= bag) {
                pq.offer(shop[idx].price);
                idx++;
            }

            if (!pq.isEmpty()) {
                maxPriceSum += pq.poll();
            }
        }

        return maxPriceSum;
    }

    static class Jewel {
        int weight;
        long price;

        public Jewel(int weight, long price) {
            this.weight = weight;
            this.price = price;
        }
    }
}
