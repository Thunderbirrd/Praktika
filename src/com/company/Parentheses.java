package com.company;

import java.util.Scanner;
import java.util.Stack;

public class Parentheses {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String brackets = scanner.nextLine();
        Stack<Character> st = new Stack<>();
        boolean t = false;
        for (int i = 0; i < brackets.length(); i++){
            char c = brackets.charAt(i);
            if(c == '[' || c == '{' || c == '('){
                st.add(c);
            }else if(c == '}' || c == ']' || c == ')'){
                if(st.empty()){
                    t = true;
                    break;
                }
                switch (c){
                    case '}':
                        if(st.peek() == '{'){
                            st.pop();
                        }else {break;}
                        break;
                    case ']':
                        if(st.peek() == '['){
                            st.pop();
                        }else {break;}
                        break;
                    case ')':
                        if(st.peek() == '('){
                            st.pop();
                        }else {break;}
                        break;
                }
            }
        }

        if(st.empty() && !t){
            System.out.println("OK");
        }else{
            System.out.println("Не все скобки закрылись");
        }
    }
}
