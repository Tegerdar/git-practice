package university.np;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static Scanner sc;
    public static void main(String[] args) {
        sc = new Scanner(System.in);
		String cmd = "";

		while (!cmd.equals("exit")) {
			System.out.print("command:> ");
			cmd = sc.next();

			switch (cmd) {
                case "add":
                    // TODO add correct format
                    System.out.println("Print a line, should be formated XXX:");
                    String line = sc.nextLine();
                    add(line);
                    break;
                    
				case "exit":
					System.out.println("good bye");
					break;

                case "del":
                    del();
                    break;

                case "print":
                    print();
                    break;

				default:
					System.out.println("unknown command");
					break;
			}
		}

		sc.close();
    }

    public static void del() {
        // TODO before sending chande just on db.csv
        String file = "/home/vlad/git-practice/university/np/db.csv"; 
        System.out.print("Select line: ");
        int lineNum = sc.nextInt();

        // Load all lines from CSV file into a list
        List<String> lines = new ArrayList<>();

        // read this lines
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String currentLine;
            while ((currentLine = br.readLine()) != null) {
                lines.add(currentLine); // add every line to list
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return;
        }

        // check if line exist and lineNum positive
        if (lineNum <= 0 || lineNum > lines.size()) {
            System.out.println("Line does not exist!");
            return; // exit method if invalid number
        }

        // removes line
        lines.remove(lineNum - 1);

        // rewrites file by using lines where there is no lineNum line
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            // Write all remaining lines back to the file
            for (String line : lines) {
                bw.write(line);
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return;
        }
    }

    public static void add(String line) {
        // TODO before sending chande just on db.csv
        String file = "/home/vlad/git-practice/university/np/db.csv"; 
        // after check on the style print new line in db.csv
        if (checkAdd(line)) {
            try (FileWriter writer = new FileWriter(file, true)) {
                writer.append(line);
                writer.append("\n");
            } catch (IOException e) {
                // Handle any file-related errors
                e.printStackTrace();
            }
        } else {
            // TODO print correct style
            System.out.println("Incorrect line style, should be: XXX");
            return;
        }
    }
    
    // TODO check if the line have correct style
    public static boolean checkAdd(String line) {
        return true;
    }

    public static void print() {
        // TODO before sending chande just on db.csv
        String file = "/home/vlad/git-practice/university/np/db.csv";
         try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String currentLine;
            int lineNum = 1;
            
            // print each line
            while ((currentLine = br.readLine()) != null) {
                System.out.println(lineNum + ": " + currentLine);
                lineNum++;
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}