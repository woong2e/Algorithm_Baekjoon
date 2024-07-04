package silver5;

import java.util.Scanner;

public class _1010 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numTestCase = sc.nextInt();
        while (numTestCase-- > 0) {
            int N = sc.nextInt();
            int M = sc.nextInt();

            int[][] biCoefficient = new int[M+1][N+1];

            fillBiCoefficient(biCoefficient, M, N);
            System.out.println(biCoefficient[M][N]);
        }
    }

    static void fillBiCoefficient(int[][] biCoefficient,  int M, int N) {
        for (int i = 0; i <= M; i++) {
            for (int j = 0; j <= Math.min(i, N); j++) {
                if (j == 0 || i == j) {
                    biCoefficient[i][j] = 1;
                } else {
                    biCoefficient[i][j] = biCoefficient[i-1][j-1] + biCoefficient[i-1][j];
                }
            }
        }
    }
}
