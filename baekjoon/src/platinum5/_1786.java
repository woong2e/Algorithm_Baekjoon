package platinum5;

import java.io.*;
import java.util.*;

/*브루트포스 알고리즘을 이용하여 String 매치 여부를 확인 할 경우 String매치 kmp알고리즘fail 배열
  O(pattern.length()*text.length())의 시간복잡도를 가지게 된다. 따라서 fail 배열을 이용하여
  시간복잡도가 O(pattern.length() + text.length())으로 선형시간의 알고리즘을 만들 수 있다.
 */

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

    /*
    근본적으로 getFail함수도 kmp알고리즘을 이용하여 구현한다.
    getFail함수는 kmp알고리즘을 이용하여 미리 사전에 패턴에 대한 String매치 여부를 저장한다.
    kmp알고리즘은 fail 배열을 가지고 진행도중 pattern과 text이 매치되지 않을 경우 text의 인덱스를 뒤돌아 가지 않도록 한다. 미리 기록해둔 fail 배열에서
    이전 인덱스까지 확인한 부분중 다음으로 text의 suffix와 pattern의 preffix가 같은 곳의 pattern의 인덱스를 불러온다.
    그렇게 함으로써 선형시간의 알고리즘을 구현할 수 있다.
     */
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
