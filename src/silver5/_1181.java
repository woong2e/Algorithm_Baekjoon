package silver5;

import java.io.*;

import java.util.Arrays;

public class _1181 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int testCase = Integer.parseInt(br.readLine());

        String[] words = new String[testCase];
        for (int i = 0; i < testCase; i++) {
            words[i] = br.readLine();
        }

        Arrays.sort(words, (o1, o2) -> {
            if (o1.length() - o2.length() == 0) {
                return o1.compareTo(o2);
            }
            return o1.length() - o2.length();
        });
        printWords(words);
        br.close();
    }



    static void printWords(String[] words) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int i = 0; i < words.length; i++) {
            if (i + 1 != words.length) {
                if (words[i].equals(words[i+1])) {
                    continue;
                }
            }
            bw.write(words[i] + "\n");
        }
        bw.flush();
        bw.close();
    }
}
