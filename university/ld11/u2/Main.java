package university.ld11.u2;

// 251RDB010 Vladislavs Lazdāns 15

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//================================================

class Student implements Serializable {
	public String name;
	public String surname;
	public int mark1, mark2, mark3;
	
	public Student(String name, String surname, int mark1, int mark2, int mark3) {
		this.name = name;
		this.surname = surname;
		this.mark1 = mark1;
		this.mark2 = mark2;
		this.mark3 = mark3;
	}
	
	public void print(int numurs) {
		System.out.printf("\n%-4d%-15s%-15s%-12d%-12d%-12d", numurs, name, surname, mark1, mark2, mark3);
	}
}

//================================================

public class Main {

	static Scanner sc = new Scanner(System.in);
	static String filename = "Students.dat";

	public static void main(String[] args) {
		int choise;
		String choiseStr;

		loop: while (true) {

			System.out.println("\n1) Create");
			System.out.println("2) Calculate");
			System.out.println("3) View");
			System.out.println("4) About");
			System.out.println("5) Exit");
			System.out.print("\nInput number from 1 till 5: ");
			
			choiseStr = sc.nextLine();
			
			try {
				choise = Integer.parseInt(choiseStr);
				if (choise < 1 || choise > 5) {
					throw new Exception();
				}
			}
			catch (Exception ex) {
				System.out.println("Error, please, input number from 1 till 5");
				continue;
			}
			

			switch (choise) {
			case 1:
				create();
				break;
			case 2:
				calculate();
				break;
			case 3:
				view();
				break;
			case 4:
				about();
				break;
			case 5:
				break loop;
			}
		}

		sc.close();
	}

	public static void create() {
		Student student;
		
		try {
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename));
			
			student = new Student("Andris", "Kokins", 5, 4, 3);
			out.writeObject(student);
			
			student = new Student("Maris", "Lauva", 9, 9, 9);
			out.writeObject(student);
			
			student = new Student("Edvards", "Ozols", 8, 7, 8);
			out.writeObject(student);
			
			student = new Student("Mara", "Liepa", 4, 2, 9);
			out.writeObject(student);
			
			student = new Student("Inga", "Lapsa", 7, 7, 7);
			out.writeObject(student);
			
			out.close();
			
			System.out.println("\nFile " + filename + " succesfully created");
		}
		catch (Exception ex) {
			System.out.println(ex.getMessage());
		}	
	}

	public static void calculate() {
        System.out.print("\nInput student id: ");
        int id;
        try {
            id = Integer.parseInt(sc.nextLine());
        } catch (Exception e) {
            System.out.println("Input error");
            return;
        }

        List<Student> fileContent = new ArrayList<>();
        
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            while (true) {
                try {
                    Student s = (Student) ois.readObject();
                    fileContent.add(s);
                } catch (EOFException e) {
                    break;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
            return;
        }

        if (id <= 0 || id > fileContent.size()) {
            System.out.println("no such student");
            return;
        }

        Student student = fileContent.get(id - 1);
        try {
            System.out.print("New mark 1: ");
            student.mark1 = Integer.parseInt(sc.nextLine());
            System.out.print("New mark 2: ");
            student.mark2 = Integer.parseInt(sc.nextLine());
            System.out.print("New mark 3: ");
            student.mark3 = Integer.parseInt(sc.nextLine());
        } catch (Exception e) {
            System.out.println("Input error");
            return;
        }

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            for (Student s : fileContent) {
                oos.writeObject(s);
            }   
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }
	}

	public static void view() {
		File f = new File (filename);
		if (!f.exists()) {
			System.out.println("The file does not exist, please use command \"Create\"");
			return;
		}
		
		System.out.println("\n-----------------------------------------------------------------------");
		System.out.printf("#   %-15s%-15s%-12s%-12s%-12s", "Name", "Surname", "Math", "Sport", "Programming");
		System.out.print("\n-----------------------------------------------------------------------");
		
		int numurs = 1;
		
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename));
			
			Student s;
		    boolean EOF = false;
		    
		    while (!EOF) {
		        try {
		            s = (Student) in.readObject();
		            s.print(numurs++);
		        } 
		        catch (EOFException e) {
		            EOF = true;
		        }
		    }
		    
		    in.close();  	
		}
		catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		
		System.out.println("\n-----------------------------------------------------------------------");
	}

	public static void about() {
		System.out.println("251RDB010 Vladislavs Lazdāns 15");
	}
}

//================================================
