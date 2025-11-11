package university.ld3.u2;

import java.util.Scanner;

class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    double sum = 0.0, count = 0.0;

    while (true) {
        int num = sc.nextInt();
        if (num == 0)
            break;
        
        if(num > 0) {
            sum += num;
            count++;
        }    
    }
    
    sc.close();

    if (count > 0) {
        System.out.printf("%.2f", (sum / count));
    } else if (sum == 0) {
        System.out.println(sum);
    } else {
        System.out.println("No positive numbers entered.");
    }
  }
}