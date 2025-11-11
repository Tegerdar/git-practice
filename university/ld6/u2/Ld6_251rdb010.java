package university.ld6.u2;

import java.util.Scanner;

public class Ld6_251rdb010 {
    public static void main(String[] args) {
        int length = 10;
        int[][] arr = new int[length][length];

        System.out.println("Vladislavs Lazdāns 15 251RDB010");
        System.out.printf("Ievadiet uzdevuma numuru (1 vai 2): ");  

        makeArray(arr, length);
    }

    public static void makeArray(int[][] arr, int length) {
        int state;
        try (Scanner sc = new Scanner(System.in)) {
            try {
                state = sc.nextInt();
            } catch (Exception e) {
                System.out.println("Incorrect input");
                return;
            }

            switch (state) {
                case 1 -> {
                    int x = -8;
                    int changes = 0;
                    for (int j = 0; j < length; ++j) {
                        for (int i = length - 1 - j; i < length && changes < 3; ++i) {
                            arr[i][j] = i + j + x;
                            ++changes;
                        }
                        x += changes;
                        changes = 0;
                    }
                    printArray(arr, length);
                }
                case 2 -> {
                    for (int j = 0; j < length; ++j) {
                        for (int i = length - 1 - j; i >= 0; --i) {
                            arr[i][j] = length - i - j;
                        }
                    }
                    printArray(arr, length);
                }
                default -> System.out.println("Incorrect input");
            }
        }
    }

    public static void printArray(int[][] arr, int length) {
        for (int i = 0; i < length; ++i) {
            for (int j = 0; j < length; ++j) {
                System.out.printf("%3d", arr[i][j]);
            }
            System.out.println();
        }
    }
}

