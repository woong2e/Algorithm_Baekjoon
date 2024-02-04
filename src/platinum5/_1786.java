package platinum5;

import java.io.*;
import java.util.*;

public class _1786 {
    static int cnt;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String text = br.readLine();
        String pattern = br.readLine();

        cnt = 0;
        Iterator it = kmp(text, pattern).iterator();

        bw.write(cnt + "\n");
        while (it.hasNext()) {
            bw.write(it.next() + " ");
        }
        bw.flush();

        br.close();
        bw.close();
    }

    static List<Integer> kmp(String text, String pattern) {
        List<Integer> ans = new ArrayList<>();
        int[] fail = getFail(pattern);

        int j = 0;
        for (int i = 0; i < text.length(); i++) {
            while (j > 0 && text.charAt(i) != pattern.charAt(j)) {
                j = fail[j - 1];
            }
            if (text.charAt(i) == pattern.charAt(j)) {
                if (j == pattern.length() - 1) {
                    ans.add(i + 1 - j);
                    cnt++;
                    j = fail[j];
                } else {
                    j++;
                }
            }
        }
        return ans;
    }

    static int[] getFail(String pattern) {
        int[] fail = new int[pattern.length()];
        int j = 0;
        for (int i = 1; i < pattern.length(); i++) {
            while (j > 0 && pattern.charAt(i) != pattern.charAt(j)) {
                j = fail[j - 1];
            }
            if (pattern.charAt(i) == pattern.charAt(j)) {
                fail[i] = ++j;
            }
        }

        return fail;
    }
}
