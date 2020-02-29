package com.company;

import java.lang.reflect.Array;
import java.util.*;

public class Laba3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in).useLocale(Locale.US);
        String s = scanner.nextLine();
        s = s.replaceAll("[(]", "( ");
        s = s.replaceAll("[)]", " )");
        String[] arr = s.split(" ");
        Stack<String> symbols = new Stack<>();
        ArrayList<String> num = new ArrayList<>();
        String[] pr1 = {"^"}; String[] pr2 = {"*", "/"}; String[] pr3 = {"+", "-"}; String[] sk = {"(", ")"}; int x;
        ArrayList p1 = new ArrayList<String>(Arrays.asList(pr1));
        ArrayList p2 = new ArrayList<String>(Arrays.asList(pr2));
        ArrayList p3 = new ArrayList<String>(Arrays.asList(pr3));
        ArrayList pl = new ArrayList<String>(Arrays.asList(sk));
        for (String value : arr) {
            try{
                x = Integer.parseInt(value);
                num.add(value);
            } catch (NumberFormatException e) {
                if(symbols.isEmpty()){
                    symbols.add(value);
                }
                else if(p1.indexOf(value) != -1){
                    if(symbols.peek().equals("^")){
                        num.add(value);
                    }else{
                        symbols.add(value);
                    }
                }else if(p2.indexOf(value) != -1){
                    if(p2.indexOf(symbols.peek()) != -1 || p1.indexOf(symbols.peek()) != -1){
                        num.add(symbols.peek());
                        symbols.pop();
                        symbols.add(value);
                    }else{
                        symbols.add(value);
                    }
                }else if(p3.indexOf(value) != -1){
                    if(pl.indexOf(symbols.peek()) != -1){
                        symbols.add(value);
                    }else{
                        num.add(symbols.peek());
                        symbols.pop();
                        symbols.add(value);
                    }
                }else{
                    if(value.equals("(")){
                        symbols.add(value);
                    }else{
                        String last = symbols.peek();
                        while (!last.equals("(")){
                            num.add(last);
                            symbols.pop();
                            last = symbols.peek();
                        }
                        symbols.pop();
                    }
                }
            }
        }
        while (!symbols.isEmpty()) {
            num.add(symbols.peek());
            symbols.pop();
        }
        System.out.println(num.toString());
        int i = 0, idx;
        ArrayList<Integer> ops = new ArrayList<>();
        for(int j = 0; j < num.size(); j++){
            if(!num.get(j).matches("-?\\d+")){
                ops.add(j);
            }
        }

        for(int j : ops){
            idx = j - 2 * i;
            String temp = num.get(idx);
            switch (temp){
                case "+":
                    num.set(idx, String.valueOf(Integer.parseInt(num.get(idx - 1)) + Integer.parseInt(num.get(idx - 2))));
                    num.remove(idx-  1); num.remove(idx - 2);
                    break;
                case "-":
                    num.set(idx, String.valueOf(Integer.parseInt(num.get(idx - 2)) - Integer.parseInt(num.get(idx - 1))));
                    num.remove(idx-  1); num.remove(idx - 2);
                    break;
                case "*":
                    num.set(idx, String.valueOf(Integer.parseInt(num.get(idx - 1)) * Integer.parseInt(num.get(idx - 2))));
                    num.remove(idx-  1); num.remove(idx - 2);
                    break;
                case "/":
                    num.set(idx, String.valueOf(Integer.parseInt(num.get(idx - 2)) / Integer.parseInt(num.get(idx - 1))));
                    num.remove(idx-  1); num.remove(idx - 2);
                    break;
                case "^":
                    num.set(idx, String.valueOf((int)Math.pow(Integer.parseInt(num.get(idx - 2)), Integer.parseInt(num.get(idx - 1)))));
                    num.remove(idx-  1); num.remove(idx - 2);
                    break;
            }
            i++;
        }
        System.out.println(num.get(0));
    }
}
