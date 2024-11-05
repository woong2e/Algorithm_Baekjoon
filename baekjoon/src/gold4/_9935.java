package gold4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;

/*
문자열 부분 매칭 문제
Stack을 사용하여 문자를 넣으면서 폭발 문자열 끝에서부터 역순으로 처음까지 매칭이 되는지 여부를 파악하는 것이 관건이다.
매칭이 안될경우 다시 원래 스택에 넣어주기 위해 pop()한 문자를 저장할 스택을 하나 더 운영한다.
 */

public class _9935 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String string = br.readLine();
        String explosion = br.readLine();

        String remainingString = getRemainingString(string, explosion);

        if (!remainingString.isEmpty()) {
            bw.write(remainingString);
        } else {
            bw.write("FRULA");
        }
        bw.flush();

        br.close();
        bw.close();
    }

    private static String getRemainingString(String string, String explosion) {
        Stack<Character> stack = new Stack<>();
        Stack<Character> tmp = new Stack<>();

        boolean isMatching = true;

        for (char c : string.toCharArray()) {
            stack.push(c);
            for (int i = explosion.length() - 1; i >= 0; i--) {
                if (!stack.isEmpty()) {
                    char element = stack.peek();
                    if (element == explosion.charAt(i)) {
                        stack.pop();
                        tmp.push(element);
                    } else {
                        isMatching = false;
                        break;
                    }
                } else {
                    isMatching = false;
                    break;
                }
            }

            if (!isMatching) {
                while(!tmp.isEmpty()) {
                    stack.push(tmp.pop());
                }
            } else {
                tmp.clear();
            }
            isMatching = true;
        }

        StringBuilder sb = new StringBuilder();
        while(!stack.isEmpty()) {
            sb.append(stack.pop());
        }

        return sb.reverse().toString();
    }
}
