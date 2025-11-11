package university.ld2.u1;

//251RDB010 Vladislavs Lazdāns

import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();
        int c = sc.nextInt();
        sc.close();

        int big, s1, s2;

        if (a >= b) {
            if (a >= c) {
                big = a;
                s1 = b;
                s2 = c;
            } else {
                big = c;
                s1 = b;
                s2 = a;
            }
        } else {
            if (b >= c) {
                big = b;
                s1 = a;
                s2 = c;
            } else {
                big = c;
                s1 = a;
                s2 = b;
            }
        }

        if (s1 + s2 > big) {
            if (s1 == s2 && s2 == big) {
                System.out.println("equilateral triangle");
            } else if (s1 == s2 || s1 == big || s2 == big) {
                System.out.println("isosceles triangle");
            } else if (s1 * s1 + s2 * s2 == big * big) {
                System.out.println("right-angled triangle");
            } else {
                System.out.println("scalene");
            }
        } else {
            System.out.println("Triangle does not exist");
        }
    }
}