package silver1;

import java.io.*;
import java.util.StringTokenizer;

public class _1149 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int number = Integer.parseInt(br.readLine());

        int [][] price = new int[number][3];
        for (int i = 0; i < number; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                price[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int minimumPrice = findMinimumPrice(number, price);

        bw.write(String.valueOf(minimumPrice));
        bw.flush();

        br.close();
        bw.close();
    }

    static int findMinimumPrice(int number, int[][] price) {
        int[] prevMinimumPrice = price[0];
        int[] nextMinimumPrice = new int[3];
        for (int i = 1; i < number; i++) {
            for (int j = 0; j < 3; j++) {
                if (j == 0) {
                    nextMinimumPrice[j] =
                            Math.min(prevMinimumPrice[1]+price[i][j], prevMinimumPrice[2]+price[i][j]);
                } else if (j == 1) {
                    nextMinimumPrice[j] =
                            Math.min(prevMinimumPrice[0]+price[i][j], prevMinimumPrice[2]+price[i][j]);
                } else {
                    nextMinimumPrice[j] =
                            Math.min(prevMinimumPrice[0]+price[i][j], prevMinimumPrice[1]+price[i][j]);
                }
            }
            prevMinimumPrice = nextMinimumPrice.clone();
        }
        return Math.min(Math.min(prevMinimumPrice[0], prevMinimumPrice[1]), prevMinimumPrice[2]);
    }
}
