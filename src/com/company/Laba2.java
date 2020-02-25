package com.company;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Laba2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in).useLocale(Locale.US);
        int n1 = 30, n2 = 100, n3 = 500, n4 = 1000;
        System.out.println("Введите количество купюр каждого номинала через пробел");
        int k1 = scanner.nextInt(), k2 = scanner.nextInt(), k3 = scanner.nextInt(), k4 = scanner.nextInt();
        System.out.println("Введите сумму, которую надо выдать");
        int sum = scanner.nextInt();
        boolean f = false;
        ArrayList<Integer> a = new ArrayList<>();
        while (sum > 0){
            if(sum == n1){
                if(k1 > 0) {
                    a.add(n1);
                    break;
                }else{
                    System.out.println("Невозможно выдать такую сумму");
                    f = true;
                    break;
                }
            }
            if(sum < 60 || (k1 == 0 && k2 == 0 && k3 == 0 && k4 == 0)){
                System.out.println("Невозможно выдать такую сумму");
                f = true;
                break;
            }
            if(n1 * k1 + n2 * k2 + n3 * k3 + n4 * k4 < sum){
                System.out.println("Невозможно выдать такую сумму");
                f = true;
                break;
            }
            while(sum % 100 != 0 && k1 > 0){
                sum -= n1;
                a.add(n1);
                k1--;
            }
            if(sum >= n4){
                if(sum - n4 > n1 && k4 > 0 && (sum - n4) % n1 == 0) {
                    sum -= n4;
                    a.add(n4);
                    k4--;
                }else if(k3 > 0){
                    sum -= n3;
                    a.add(n3);
                    k3--;
                }else if(k2 > 0){
                    sum -= n2;
                    a.add(n2);
                    k2--;
                }else{
                    sum -= n1;
                    a.add(n1);
                    k1--;
                }
            }else if(sum >= n3 && k3 > 0){
                if(sum - n3 > n1){
                    sum -= n3;
                    a.add(n3);
                    k3--;
                }else if(k2 > 0){
                    sum -= n2;
                    a.add(n2);
                    k2--;
                }else{
                    sum -= n1;
                    a.add(n1);
                    k1--;
                }
            }else if(sum >= n2 && k2 > 0){
                if(sum - n2 <= n2 && (sum - n2) % 30 == 0){
                    sum -= n2;
                    a.add(n2);
                    k2--;
                }else if(sum - n2 >= 120 || sum - n2 == n2){
                    sum -= n2;
                    a.add(n2);
                    k2--;
                }else{
                    sum -= n1;
                    a.add(n1);
                    k1--;
                }
            }else if(k1 > 0){
                sum -= n1;
                a.add(n1);
                k1--;
            }
        }
        if(!f){
            System.out.println("Выданные купюры");
            for (Integer integer : a) {
                System.out.print(integer + " ");
            }
        }
    }
}
