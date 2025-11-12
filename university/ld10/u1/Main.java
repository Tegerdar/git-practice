package university.ld10.u1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// 251RDB010 Vladislavs Lazdāns

public class Main {
	public static Scanner sc;
	public static void main(String[] args) {
		sc = new Scanner(System.in);
		String cmd = "";
		String filename = "";

		while (!cmd.equals("exit")) {
			System.out.print("command:> ");
			cmd = sc.next();

			switch (cmd) {
                case "print":
			        filename = sc.next();
                    print(filename);
                    break;
                    
				case "exit":
					System.out.println("good bye");
					break;

                case "format":
			        filename = sc.next();
                    format(filename);
                    break;

				default:
					System.out.println("unknown command");
					break;
			}
		}

		sc.close();
	}

	public static void print(String filename) {
        // TODO before sending chande just on db.csv
        String file = filename + ".txt";
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String currentLine;
            
            // print each line
            while ((currentLine = br.readLine()) != null) {
                System.out.println(currentLine);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

	public static void format(String filename) {
        String file = filename + ".txt";
        List<String> lines = new ArrayList<>();

        // Read lines and remove empty ones immediately
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String currentLine;
            while ((currentLine = br.readLine()) != null) {
                if (!currentLine.trim().isEmpty()) {
                    lines.add(currentLine);
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return;
        }

        int maxWidth = 0;
        for (String line : lines) {
            if (line.length() > maxWidth) {
                maxWidth = line.length();
            }
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            int lineCount = 0;
        
            for (String line : lines) {
                // Capitalize first letter
                line = line.substring(0, 1).toUpperCase() + line.substring(1);

                // Center the line
                int spacesTotal = maxWidth - line.length();
                int leftSpaces = spacesTotal / 2;
                int rightSpaces = spacesTotal - leftSpaces;
                String centeredLine = " ".repeat(leftSpaces) + line + " ".repeat(rightSpaces);
            
                bw.write(centeredLine);
                bw.newLine();
            
                lineCount++;
                
                if (lineCount % 2 == 0 && lineCount < lines.size()) {
                    bw.newLine();
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return;
        }
    }

}