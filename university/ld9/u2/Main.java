package university.ld9.u2;

// 251RDB010 Vladislavs Lazdāns 15

import java.util.LinkedList;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Main {
  public static void main(String[] args) {
    System.out.printf("251RDB010 Vladislavs Lazdāns\n251rdb035 Eduards Zemturis\n");

    Scanner sc = new Scanner(System.in);
    List<Integer> list = new LinkedList<Integer>();
    
    int number;
    while ((number = sc.nextInt()) != 0) {
        list.add(number);
    }

    System.out.println("result:");
    
    for (int n : list) {
        System.out.print(n + " ");
    }
    System.out.println();

    for (int i = 0; i < list.size(); ++i) {
        if (list.get(i) <= 0) {
	        list.remove(i);
            --i;
        }
    }

    for (int n : list) {
        System.out.print(n + " ");
    }
    System.out.println();

    sc.close();
  }
}
