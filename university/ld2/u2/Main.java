//251RDB010 Vladislavs Lazdāns

package university.ld2.u2;

import java.util.Scanner;

class Main {
    static int blueCheck(double x, double y) { //check if point is blue and only here im using <= and >= because blue is high priority
        if (x >= 2 && x <= 8 && y >= 7) {
            if (x <= 5) {
                if (y <= x + 5) return 0;
            } else {
                if (y <= -x + 15) return 0;
            }
        }
        return 1;
    }
  
    static int greenCheck(double x, double y) { // check if point is green
        if (x > 4 && x < 6 && y > 8) {
            double dx = x - 5;
            double dy = y - 8;
            if (dx * dx + dy * dy < 1) {
                return 0;
            }
        }
        return 1;
    }

    static int redCheck(double x, double y) { // check if red is green
        if (x >= 3 && x <= 7 && y >= 3 && y < 7) { // here i used >= <= because red > white
            if (!(x > 4 && x < 6 && y > 4 && y < 6)) {
                return 0;
            }
        }
        return 1;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double x, y;
        
        // asumed that x < 0 and y < 0 is correct and this is white
        try {
            x = sc.nextDouble();
            y = sc.nextDouble();
        } catch (Exception e) {
            System.out.println("input-output error");
            sc.close();
            return;
        }
        sc.close();

        int blue = blueCheck(x, y);
        int green = greenCheck(x, y);
        int red = redCheck(x, y);

        if (green == 0) {
            System.out.println("green");
        } else if (red == 0) {
            System.out.println("red");
        } else if (blue == 0) {
            System.out.println("blue");
        } else {
            System.out.println("white");
        }
    }
}