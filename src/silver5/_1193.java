package silver5;

import java.io.*;

public class _1193 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int number = Integer.parseInt(br.readLine());

        int num = number;
        int n = 1;
        while (num > 0) {
            num -= n;
            n++;
        }
        n--;
        num += n;
        int numerator, denominator;
        if (n % 2 == 0) {
            numerator = 1;
            denominator = n;
            for (int i = 0; i < num - 1; i++) {
                numerator++;
                denominator--;
            }
        } else {
            numerator = n;
            denominator = 1;
            for (int i = 0; i < num - 1; i++) {
                numerator--;
                denominator++;
            }
        }

        bw.write(numerator + "/" + denominator);
        bw.flush();

        br.close();
        bw.close();
    }
}
