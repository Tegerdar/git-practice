package university.ld10.u2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("251RDB010 Vladislavs Lazdāns");
		System.out.println("251RDB035 Eduards Zemturis");
		
		String fileName;
		System.out.println("input file name:");
		fileName = sc.nextLine();
		sc.close();
        fileName += ".txt";
		
        System.out.println("result:");
        List<Integer> numbers = new ArrayList();
        List<String> names = new ArrayList();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String currentLine;
            
            while ((currentLine = br.readLine()) != null) {
                String[] parts = currentLine.split(" ");
                for (String part : parts) {
                    if (part.matches("\\d+")) {
                        numbers.add(Integer.parseInt(part));
                    } else {
                        names.add(part);
                    }
                }

                double avg = 0;
                for (double number : numbers) {
                    avg += number*1.0;
                }
                avg /= numbers.size()*1.0;

                int count = 0;

                for (int number : numbers) {
                    if (number < 4) {
                        ++count;
                    }
                }

                if (count >= 3) {
                    System.out.printf(String.join(" ", names));
                    System.out.printf(" %.2f\n", avg);
                }

                numbers.clear();
                names.clear();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

	}

}
