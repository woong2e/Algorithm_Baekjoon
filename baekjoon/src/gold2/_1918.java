package gold2;

import java.io.*;
import java.util.Stack;
/*
우선순위를 비교해서 stack을 이용해 연산자를 꺼내오는 방식으로 한다.
괄호')'가 오는경우 스택에 마지막에 '('이 남아 있으므로 '('을 꼭 pop해주어야 한다.
 */

public class _1918 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String infixNotation = br.readLine();
        String postfixNotation = changeToPostfixNotation(infixNotation);

        bw.write(postfixNotation);
        bw.flush();

        br.close();
        bw.close();
    }

    static String changeToPostfixNotation(String infixNotation) {
        StringBuilder sb = new StringBuilder();
        Stack<Character> stack = new Stack<>();
        char[] arr = infixNotation.toCharArray();

        for (int i = 0; i < infixNotation.length(); i++) {
            int p = priority(arr[i]);

            if (arr[i] == '+' || arr[i] == '-' || arr[i] == '*' || arr[i] == '/') {
                while (!stack.isEmpty() && priority(stack.peek()) >= p) {
                    sb.append(stack.pop());
                }
                stack.push(arr[i]);
            } else if (arr[i] == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    sb.append(stack.pop());
                }
                stack.pop();
            } else if (arr[i] == '(') {
                stack.push('(');
            } else {
                sb.append(arr[i]);
            }
        }
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }

        return sb.toString();
    }

    static int priority(char c) {
        switch (c) {
            case '*':
            case '/': return 2;
            case '+':
            case '-': return 1;
        }
        return 0;
    }
}
