package gold2;

import java.io.*;

/*
행렬 곱셈을 이용하여 피보나치 수 구하기,
엄청 큰 제곱을 하는 경우: 지수의 덧셈은 밑의 곱셈이라는 점을 생각하여 분할정복을 통해 횟수를 엄청나게 줄일 수 있음
ex) 2^1_000_000_006 = 2^500_000_003 * 2^500_000_003
 */
public class _11444 {
    private final static long[][] BASE = {{1, 1}, {1, 0}};
    private final static long DIVISOR = 1_000_000_007;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        long num = Long.parseLong(br.readLine());
        long[][] result = powMatrix(num - 1);

        bw.write(String.valueOf(result[0][0]));
        bw.flush();

        br.close();
        bw.close();
    }

    private static long[][] powMatrix(long num) {
        if (num <= 1) {
            return BASE;
        }

        long[][] tmp = powMatrix(num / 2);

        tmp = multiplyMatrix(tmp, tmp);

        if (num % 2 == 0) {
            return tmp;
        } else {
            return multiplyMatrix(tmp, BASE);
        }

    }

    private static long[][] multiplyMatrix(long[][] mat1, long[][] mat2) {
        long[][] res = new long[2][2];

        for(int i = 0; i < 2; i++){
            for(int j = 0; j < 2; j++){
                for(int k = 0; k < 2; k++){
                    res[i][j] += mat1[i][k] * mat2[k][j];
                    res[i][j] = res[i][j] % DIVISOR;
                }
            }
        }

        return res;
    }

}
