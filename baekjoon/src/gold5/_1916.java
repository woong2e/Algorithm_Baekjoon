package gold5;

import java.io.*;
import java.util.*;
/*
가중치가 양수인 최단경로 알고리즘 - dijkstra
우선순위 큐, cost가 지금 현재의 cost보다 클 경우 continue 해줘야 함;
 */

public class _1916 {

    static class Bus implements Comparable<Bus> {
        int destination;
        int cost;

        public Bus(int destination, int cost) {
            this.destination = destination;
            this.cost = cost;
        }

        @Override
        public int compareTo(Bus bus) {
            return this.cost - bus.cost;
        }
    }

    private static final int MAX_VALUE = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        List<Bus>[] city = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            city[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int departure = Integer.parseInt(st.nextToken());
            int destination = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            city[departure].add(new Bus(destination, cost));
        }
        st = new StringTokenizer(br.readLine());

        int guganStart = Integer.parseInt(st.nextToken());
        int guganEnd = Integer.parseInt(st.nextToken());


        int minCost = dijkstra(city, N, guganStart, guganEnd);

        bw.write(String.valueOf(minCost));
        bw.flush();

        br.close();
        bw.close();
    }

    private static int dijkstra(List<Bus>[] city, int N, int guganStart, int guganEnd) {
        PriorityQueue<Bus> pq = new PriorityQueue<>();
        int[] cost = new int[N + 1];
        Arrays.fill(cost, MAX_VALUE);
        cost[guganStart] = 0;

        pq.add(new Bus(guganStart, 0));

        while (!pq.isEmpty()) {
            Bus tmp = pq.poll();
            int tmpDestination = tmp.destination;
            int tmpCost = tmp.cost;

            if (tmpCost > cost[tmpDestination]) {
                continue;
            }

            for (Bus bus : city[tmpDestination]) {
                int nextDestination = bus.destination;
                int nextCost = bus.cost + cost[tmpDestination];

                if (nextCost < cost[nextDestination]) {
                    cost[nextDestination] = nextCost;
                    pq.add(bus);
                }
            }
        }

        return cost[guganEnd];
    }
}
