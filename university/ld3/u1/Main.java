package university.ld3.u1;

// 251RDBR010 Vladislavs Lazdāns

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        double x, dx, xg;
        Scanner sc = new Scanner(System.in);

        System.out.println("from: ");
        x = sc.nextDouble();

        System.out.println("to: ");
        xg = sc.nextDouble();

        System.out.println("step: ");
        dx = sc.nextDouble();

        while (x <= xg + dx / 2) {
            if (x - 1 <= 0.00001)
                System.out.printf("%.2f\tnot defined\n", x);
            else
                System.out.printf("%.2f\t%.2f\n", x, ((x + 1) * (x + 1) / Math.sqrt(x - 1)));
            x += dx;
        }
        sc.close();
    }
}
