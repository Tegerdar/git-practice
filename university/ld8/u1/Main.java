package university.ld8.u1;

// 251rdb010 Vladislavs Lazdāns 15

import java.util.Scanner;
import java.lang.String;

public class Main {
    public static void main(String[] args)  {
        Scanner sc = new Scanner(System.in);
        boolean cont = true;
        while (cont) {
            String line = sc.nextLine();
            String[] parts = line.split("\\s+", 2);
            String operator = parts[0];
            switch (operator) {
                case "comp" : {
                    comp(parts[1]);
                    break;
                }
                case "decomp" : {
                    decomp(parts[1]);
                    break;
                }
                case "about" : {
                    System.out.println("251rdb010 Vladislavs Lazdāns 15");
                    break;
                }
                case "exit" : {
                    sc.close();
                    cont = false;
                    break;
                }

                default:
                System.out.println("wrong command");
                    break;
            }
        }
    }

    public static void comp(String input) {
        if (checkComp(input)) {
            int len = input.length();
            int[] encoded = new int[(len + 3) / 4 + 1];
            encoded[0] = (len + 3) / 4;
            int j = 1, compiled = 0, bits = 0;

            for (int i = 0; i < len; ++i) {
                char c = input.charAt(i);
                int v = 0;
                if (c == 'A') {
                    v = 0;
                }
                else if (c == 'C') {
                    v = 1;
                }
                else if (c == 'G') {
                    v = 2;
                }
                else if (c == 'T') {
                    v = 3;
                }
                compiled = (compiled << 2) | v;
                bits += 2;
                if (bits == 8) {
                    encoded[j++] = compiled;
                    compiled = 0;
                    bits = 0;
                }
            }

            if (bits > 0) {
                compiled <<= (8 - bits);
                encoded[j] = compiled;
            }

            for (int e = 0; e <= j; ++e) {
                System.out.printf("%X ", encoded[e]);
            }
            System.out.println();
        }
    }

    public static boolean checkComp(String input) {
        char c;
        for (int i = 0; i < input.length(); ++i) {
            c = input.charAt(i);
            if (c != 'A' && c != 'C' && c != 'G' && c != 'T') {
                System.out.println("wrong command format");
                return false;
            }
        }
        return true;
    }

    public static void decomp(String input) {
        String[] parts = input.trim().split("\\s+");
        int len = Integer.parseInt(parts[0]);
        if (checkDecomp(parts, len)) {
            int arr[] = new int[len+1];
            for (int i = 0; i < (len + 1); ++i) {
                arr[i] = Integer.parseInt(parts[i]);
                if (i > 0) {
                    System.out.print(Integer.toHexString(arr[i] & 0xFF).toUpperCase() + " ");
                }
            }
            System.out.println();

            char[] line = new char[arr[1]];
            int count = 0;
            int number = 2;
            do {
                if (count > line.length) {
                    System.err.println("wrong command format");
                    break;
                }

                for (int i = 6; i >= 0; i -= 2) {
                    int pair = (arr[number] >> i) & 0b11;
                    if (count == line.length) {
                        for (char c : line) {
                            System.out.print(c);
                        }
                        System.out.println();
                        return;
                    } else if (pair == 0) {
                        line[count] = 'A';
                    } else if (pair == 1) {
                        line[count] = 'C';
                    } else if (pair == 2) {
                        line[count] = 'G';
                    } else if (pair == 3) {
                        line[count] = 'T';
                    }
                    ++count;
                }

                ++number;
            } while (true);
        }
    }

    public static boolean checkDecomp(String[] parts, int len) {
        for (String part : parts) {
            try {
                Integer.parseInt(part);
            } catch (NumberFormatException e) {
                System.err.println("wrong command format");
                return false;
            }
        }

        if (len != (parts.length - 1)) {
            System.err.println("wrong command format");
            return false;
        }

        for (int i = 1; i < (len + 1); ++i) {
            int n = Integer.parseInt(parts[i]);
            if (n > 127 || n < -128) {
                System.err.println("wrong command format");
                return false;
            }
        }

        return true;
    }
}   
