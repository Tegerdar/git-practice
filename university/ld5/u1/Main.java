package university.ld5.u1;

// 251RDBR010 Vladislavs Lazdāns Robots

import java.util.Scanner;

public class Main {
     public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] a, b, c;
        int countA, countB;

        System.out.print("length of array a: ");
        countA = sc.nextInt();
        a = new int[countA];

        System.out.println("input items:");
        for (int i = 0; i < a.length; i++) {
            a[i] = sc.nextInt();
        }
        
        System.out.print("length of array b: ");
        countB = sc.nextInt();

        b = new int[countB];
        System.out.println("input items:");
        for (int i = 0; i < b.length; i++) {
            b[i] = sc.nextInt();
        }

        sc.close();

        System.out.println("array a:");
        for (int x: a) System.out.print(x + " ");
        System.out.println();

        System.out.println("array b:");
        for (int x: b) System.out.print(x + " ");
        System.out.println();

        c = new int[a.length + b.length];

        // place your code here
        // 1. check if arrays a and b are sorted

        int SORT = 0;
        for (int i = 1; i < countA; ++i) 
            if ( a[i] < a[i-1])
                ++SORT;

        for (int i = 1; i < countB; ++i) 
            if ( b[i] < b[i-1])
                ++SORT;
        
        if (SORT > 0) {
            System.err.println("invalid input data ");
            return;
        }

        // 2. merge arrays a and b into array 

        int i = 0, j = 0, k = 0;

        while (i < countA && j < countB) {
            if (a[i] <= b[j]) {
                c[k++] = a[i++];
            } else {
                c[k++] = b[j++];
            }
        }

        while (i < countA) {
            c[k++] = a[i++];
        }

        while (j < countB) {
            c[k++] = b[j++];
        }

        System.out.println("result:");
        for (int x: c) System.out.print(x + " ");
    }
}