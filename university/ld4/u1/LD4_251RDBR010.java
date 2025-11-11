package university.ld4.u1;

// 251RBR010 Vladislavs Lazdāns 15. grupa

import java.util.Scanner;

class LD4_251RDBR010 {
    public static double y(double v, double g, double t, double a) {
        double y = v * t * Math.sin(a) - (g * t * t) / 2;
        return y;
    }
    
    public static double x(double v, double t, double a) {
        double x = v * t * Math.cos(a);
        return x;
    }
    
    public static void main(String[] args) {
        System.out.println("251RBR010 Vladislavs Lazdāns 15");
        System.out.print("v0 = ");
        Scanner sc = new Scanner(System.in);
        double v = 0;
        try {
            v = sc.nextDouble();
        } catch (Exception e) {
            System.out.println("Invalid input.");
            sc.close();
            return;
        }
        
        double g = 9.81;
        double t = 0.05;
        double a = Math.toRadians(35);
        boolean targetDestroyed = false;
        
        System.out.println("result:");
        System.out.printf("t\tx\ty\n");
        
        double Y = y(v, g, t, a);
        double X = x(v, t, a);
        
        while (((X <= 11 && Y >= 0) || (Y >= -4 && X <= 20 && X >= 11)) && !targetDestroyed) {
            
            if (!targetDestroyed && X >= 17 && X <= 19 && Y >= -7 && Y <= -2) {
                System.out.printf("%.2f\t%.3f\t%.3f\n", t, X, Y);
                System.out.println("the target was destroyed");
                targetDestroyed = true;
            } else {
                System.out.printf("%.2f\t%.3f\t%.3f\n", t, X, Y);
            }
            
            t += 0.05;
            Y = y(v, g, t, a);
            X = x(v, t, a);
        }

        if (Y <= -4 || X >= 20 || X <= 11) {
            System.out.printf("%.2f\t%.3f\t%.3f\n", t, X, Y);
            System.out.println("shot off the target");
        }
        
        sc.close();
    }
}