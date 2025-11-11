package university.ld7.u1;

// 251RDB010 Vladislavs Lazdāns

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        float x;
        try (Scanner sc = new Scanner(System.in)) {
            x = sc.nextFloat();
        }

        float guess = x/2;

        if ((guess - x/guess) > 0.001) {
            guess = sqrtCustom(x, guess);
        }
        
        System.out.printf("%.2f", guess);
    }

    public static float sqrtCustom(float x, float guess) {
        if (Math.abs(guess - x / guess) > 0.001) {
            guess = (guess + x / guess) / 2;
            guess = sqrtCustom(x, guess);
        }
        return guess;
    }
}
