package RPN;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Stack;
import java.util.StringTokenizer;

public class RPNStacker {
    public static void main(String[] args) throws IOException {
        Path path = Path.of("src/Calc1.stk");
        String content = new String(Files.readAllBytes(path));
        StringTokenizer st = new StringTokenizer(content, "\r\n");
        Stack<String> stack = new Stack<>();
        while (st.hasMoreTokens()) {
            String token = st.nextToken();
            if (token.equals("+")) {
                String a = stack.pop();
                String b = stack.pop();
                if (!a.matches("[0-9]+(\\.[0-9]+)?")) {
                    throw new IllegalArgumentException("Error: Unexpected character: " + a);
                }else{
                    if (b.matches("[0-9]+(\\.[0-9]+)?")){
                        System.out.println(new Token(TokenType.NUM, a));
                        System.out.println(new Token(TokenType.NUM, b));
                        System.out.println(new Token(TokenType.PLUS, token));
                        stack.push(String.valueOf(Double.parseDouble(a) + Double.parseDouble(b)));
                    } else {
                        throw new IllegalArgumentException("Error: Unexpected character: " + b);
                    }
                }

            } else if (token.equals("*")) {
                String a = stack.pop();
                String b = stack.pop();
                if (!a.matches("[0-9]+(\\.[0-9]+)?")){
                    throw new IllegalArgumentException("Error: Unexpected character: " + a);
                }else{
                    if (b.matches("[0-9]+(\\.[0-9]+)?")){
                        System.out.println(new Token(TokenType.NUM, a));
                        System.out.println(new Token(TokenType.NUM, b));
                        System.out.println(new Token(TokenType.STAR, token));
                        stack.push(String.valueOf(Double.parseDouble(a) * Double.parseDouble(b)));
                    } else {
                        throw new IllegalArgumentException("Error: Unexpected character: " + b);
                    }
                }
            } else if (token.equals("-")) {
                String a = stack.pop();
                String b = stack.pop();
                if (!a.matches("[0-9]+(\\.[0-9]+)?")){
                    throw new IllegalArgumentException("Error: Unexpected character: " + a);
                }else{
                    if (b.matches("[0-9]+(\\.[0-9]+)?")){
                        System.out.println(new Token(TokenType.NUM, a));
                        System.out.println(new Token(TokenType.NUM, b));
                        System.out.println(new Token(TokenType.MINUS, token));
                        stack.push(String.valueOf(Double.parseDouble(a) - Double.parseDouble(b)));
                    } else {
                        throw new IllegalArgumentException("Error: Unexpected character: " + b);
                    }
                }
            } else if (token.equals("/")) {
                String a = stack.pop();
                String b = stack.pop();
                if (!a.matches("[0-9]+(\\.[0-9]+)?")){
                    throw new IllegalArgumentException("Error: Unexpected character: " + a);
                }else{
                    if (b.matches("[0-9]+(\\.[0-9]+)?")){
                        System.out.println(new Token(TokenType.NUM, a));
                        System.out.println(new Token(TokenType.NUM, b));
                        System.out.println(new Token(TokenType.SLASH, token));
                        stack.push(String.valueOf(Double.parseDouble(a) / Double.parseDouble(b)));
                    } else {
                        throw new IllegalArgumentException("Error: Unexpected character: " + b);
                    }
                }
            } else {
                stack.push(token);
            }

        }
        System.out.println("Saida: "+stack.pop());
    }
}
