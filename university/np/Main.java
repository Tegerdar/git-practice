package university.np;

// 251RDB010 Vladislavs Lazdāns 15. grupa

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

// TODO check whole prog on file path

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
                    String data = sc.nextLine().trim();
                    add(data);
                    break;

                case "del":
                    int id = sc.nextInt(); 
                    del(id);
                    break;
                
                case "edit":
                    data = sc.nextLine().trim();
                    edit(data);
                    break;

                case "print":
                    print();
                    break;
                
                case "sort":
                    //sort();
                    break;
                
                case "find":
                    int price = sc.nextInt();
                    //find(price);
                    break;
                
                case "avg":
                    //avg();
                    break;

                case "exit":
					System.out.println("good bye");
					break;

				default:
					System.out.println("unknown command");
					break;
			}
		}

		sc.close();
    }

    public static void del(int id) {
        // TODO before sending chande just on db.csv
        String file = "/home/vlad/git-practice/university/np/db.csv";

        List<String> lines = new ArrayList<>();

        lines = readFile(file);

        // System.out.println(e.getMessage()); alredy implemented in readFile
        if (lines == null) {
            return;
        }

        if (id - 100 < 0) {
            System.out.println("wrong id");
            return;
        }

        int lineNum;

        if ((lineNum = getLineNum(id, lines)) == -1) {
            System.out.println("wrong id");
            return;
        }

        // removes line
        lines.remove(lineNum);

        rewriteFile(lines, file);

        System.out.println("deleted");
    }

    public static int getLineNum(int id, List<String> lines) {
        int lineNum = 1;

        for (String line : lines) {
            String[] parts = line.split(";");
            int lineId = Integer.parseInt(parts[0]);

            if (lineId == id) {
                return (lineNum-1);
            }

            ++lineNum;
        }

        return -1;
    }

    public static List<String> readFile(String file) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String currentLine;
            while ((currentLine = br.readLine()) != null) {
                lines.add(currentLine);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return null;
        }
        return lines;
    }

    public static void rewriteFile(List<String> lines, String file) {
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

        String[] parts = line.split(";");

        int id = Integer.parseInt(parts[0]);
        String city = parts[1];
        String date = parts[2];
        int days = Integer.parseInt(parts[3]);
        double price = Double.parseDouble(parts[4]);
        String vehicle = parts[5];

        // after check on the style print new line in db.csv
        if (checkLine(line, 'a')) {
            try (FileWriter writer = new FileWriter(file, true)) {
                writer.append(id + ";");

                String[] words = city.split(" ");
                StringBuilder formatted = new StringBuilder();

                for (String word : words) {
                    if (!word.isEmpty()) {
                        formatted.append(Character.toUpperCase(word.charAt(0)))
                             .append(word.substring(1))
                             .append(" ");
                    }
                }

                city = formatted.toString().trim();

                writer.append(city + ";");
                writer.append(date + ";");
                writer.append(days + ";");
                writer.append(String.format("%.2f;", price));
                writer.append(vehicle.toUpperCase());
                writer.append("\n");

                System.out.println("added");
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
    //TODO
    public static boolean checkLine(String line, char status) {
        // status: a - add, e - edit
        String[] parts = line.split(";");

        // TODO before sending chande just on db.csv
        String file = "/home/vlad/git-practice/university/np/db.csv";

        if (status == 'a' || parts[3].length() != 0) {
            try {
                Integer.parseInt(parts[3]);
            } catch (NumberFormatException e) {
               System.out.println("wrong day count");
                return false;
            }
        }
        
        if (status == 'a' || parts[4].length() != 0) {
            try {
                Double.parseDouble(parts[4]);
            } catch (NumberFormatException e) {
                System.out.println("wrong price");
                return false;
            }
        }

        List<String> lines = new ArrayList<>();
        lines = readFile(file);

        int id = Integer.parseInt(parts[0]);
        String date = parts[2];
        String vehicle = parts[5].toUpperCase();

        if (parts.length != 6) {
            System.out.println("wrong field count");
            return false;
        }

        if (status == 'a') {
            if (id - 100 < 0) {
                System.out.println("wrong id");
                return false;
            } else if (getLineNum(id, lines) != -1) {
                System.out.println("wrong id");
                return false;
            }
        } else if (status == 'e') {
            if (id - 100 < 0) {
                System.out.println("wrong id");
                return false;
            } else if (getLineNum(id, lines) == -1) {
                System.out.println("wrong id");
                return false;
            }
        }

        if (status == 'a' || date.length() != 0) {
            String[] partsDate = date.split("/");

            int dayDate = Integer.parseInt(partsDate[0]);
            int monthDate = Integer.parseInt(partsDate[1]);

            if (dayDate < 1 || dayDate > 31) { 
                System.out.println("wrong date");
                return false;
            } else if (monthDate < 1 || monthDate > 12) {
                System.out.println("wrong date");
                return false;
            }
        }

        if (status == 'a' || vehicle.length() != 0) {
            if (!vehicle.equals("TRAIN") && !vehicle.equals("PLANE") && 
                !vehicle.equals("BUS") && !vehicle.equals("BOAT")) {
                    System.out.println("wrong vehicle");
                    return false;
            }
        }

        return true;
    }

    public static void print() {
        // TODO before sending chande just on db.csv
        String file = "/home/vlad/git-practice/university/np/db.csv";
        int symbCount = 60;
        int idSymbCount = 4;
        int citySymbCount = 21;
        int dateSymbCount = 11;
        int daysSymbCount = 6;
        int priceSymbCount = 10;
        int vehicleSymbCount = 8;

        
        System.out.println("-".repeat(symbCount));
        printLine(idSymbCount, citySymbCount, dateSymbCount, daysSymbCount, priceSymbCount, vehicleSymbCount,
                  "ID", "City", "Date", "Days", "Price", "Vehicle");
        System.out.println("-".repeat(symbCount));

        List<String> lines = new ArrayList<>();
        lines = readFile(file);

        for (String line : lines) {
            String[] parts = line.split(";");
            printLine(idSymbCount, citySymbCount, dateSymbCount, daysSymbCount, priceSymbCount, vehicleSymbCount,
                      parts[0], parts[1], parts[2], parts[3], parts[4], parts[5]);
        }

        System.out.println("-".repeat(symbCount));
    }

    public static void printLine(int idSymbCount, int citySymbCount, int dateSymbCount,
                                 int daysSymbCount, int priceSymbCount, int vehicleSymbCount,
                                 String id, String city, String date, String days, String price, String vehicle) {

        String statusLine = id + " ".repeat(idSymbCount-id.length()) + 
                            city + " ".repeat(citySymbCount-city.length()) + 
                            date + " ".repeat(dateSymbCount-date.length()) + 
                            " ".repeat(daysSymbCount-days.length()) + days + 
                            " ".repeat(priceSymbCount-price.length()) + price + 
                            " ".repeat(vehicleSymbCount-vehicle.length()) + vehicle;
                            
        System.out.println(statusLine);
    }

    public static void edit(String line) {
        // TODO before sending chande just on db.csv
        String file = "/home/vlad/git-practice/university/np/db.csv";
        
        List<String> lines = new ArrayList<>();
        lines = readFile(file);

        String[] parts = line.split(";");

        int id = Integer.parseInt(parts[0]);
        String city = parts[1];
        String date = parts[2];
        String days = parts[3];
        String price = parts[4];
        String vehicle = parts[5];

        if (checkLine(line, 'e')) {
            int lineNum = getLineNum(id, lines);
            String oldLine = lines.get(lineNum);
            String[] oldParts = oldLine.split(";");

            String[] newParts = new String[6];
            newParts[0] = String.format("%d", id);

            if (city.length() != 0 && city != null) {
                String[] words = city.split(" ");
                StringBuilder formatted = new StringBuilder();

                for (String word : words) {
                    if (!word.isEmpty()) {
                        formatted.append(Character.toUpperCase(word.charAt(0)))
                             .append(word.substring(1))
                             .append(" ");
                    }
                }

                city = formatted.toString().trim();
                newParts[1] = city;
            } else {
                newParts[1] = oldParts[1];
            }

            if (date.length() != 0) {
                newParts[2] = date;
            } else {
                newParts[2] = oldParts[2];
            }

            if (days.length() != 0) {
                newParts[3] = days;
            } else {
                newParts[3] = oldParts[3];
            }

            if (price.length() != 0) {
                double priceNum = Double.parseDouble(price);
                newParts[4] = String.format("%.2f", priceNum);
            } else {
                newParts[4] = oldParts[4];
            }

            if (vehicle.length() != 0) {
                newParts[5] = vehicle;
            } else {
                newParts[5] = oldParts[5];
            }

            String newLine = String.join(";", newParts);

            lines.set(lineNum, newLine);

            rewriteFile(lines, file);
            System.out.println("changed");
        }
    }
}