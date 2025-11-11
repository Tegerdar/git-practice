package university.ld6.u1;

import java.util.Scanner;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        int length = 5;
        int height = 7;
        double[][] arr = new double[height][length];

        int mode = getArray(arr, length, height);

        System.out.printf("mode (1, 2 or 3): %d\n", mode);
        printArray(arr, length, height);
        result(arr, length, height);
    }

    public static int getArray(double[][] arr, int length, int height) {
        Scanner sc = new Scanner(System.in);
        System.out.printf("mode (1, 2 or 3): ");
        int state = sc.nextInt();

        switch (state) {
            case 1 -> {
                for (int i = 0; i < height; ++i) {
                    for (int j = 0; j < length; ++j) {
                        arr[i][j] = sc.nextDouble();
                    }
                }
            }
            case 2 -> {
                Random rand = new Random();
                double[] values = {0, 0.5, 1};
                for (int i = 0; i < height; ++i) {
                    for (int j = 0; j < length; ++j) {
                        arr[i][j] = values[rand.nextInt(values.length)];
                    }
                }
            }
            case 3 -> {
                double[][] temp = {
                    {0.5, 0.5, 0.5, 0.5, 0.5},
                    {0, 1, 0, 1, 1},
                    {0.5, 1, 0.5, 0.5, 0},
                    {0, 0.5, 0, 0.5, 0},
                    {1, 1, 1, 1, 1},
                    {0, 0, 0, 0.5, 0.5},
                    {0, 0.5, 0, 0, 1}
                };
                for (int i = 0; i < height; ++i) {
                    for (int j = 0; j < length; ++j) {
                        arr[i][j] = temp[i][j];
                    }
                }
            }
        }
        
        return state;
    }

    public static void printArray(double[][] arr, int length, int height) {
        for (int i = 0; i < height; ++i) {
            for (int j = 0; j < length; ++j) {
                System.out.printf("%.1f ", arr[i][j]);
            }
            System.out.println();
        }
    }

    public static void result(double[][] arr, int length, int height) {
        int count = 0;
        System.out.println("result:");
        System.out.printf("numbers: ");

        for (int i = 0; i < height; ++i) {
            boolean noLosses = true;
            for (int j = 0; j < length; ++j) {
                if (arr[i][j] == 0) {
                    noLosses = false;
                    break;
                }
            }
            if (noLosses) {
                System.out.printf("%d ", i + 1);
                ++count;
            }
        }

        System.out.printf("\ncount: %d\n", count);
    }
}

