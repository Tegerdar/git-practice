package university.ld2.u3;

//251RDB010 Vladislavs Lazdāns

import java.util.Scanner;

public class Main {
	static int checkCircle(double x, double y) {
		if ((x - 3) * (x - 3) + (y - 5) * (y - 5) <= 4) {
			return 0;
		} else {
			return 1;
		}
	}
	
	static int checkTriangle(double x, double y) {
		if (y <= 5 && y >= (-x + 10) && x >= 5 && x <= 9) {
			return 0;
		} else {
			return 1;
		}
	}

	static int checkRectangleInTriangle(double x, double y) {
		if (y > 3 && y < 4 && x > 7 && x < 8) {
			return 1;
		} else {
			return 0;
		}
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("251RDB010 Vladislavs Lazdāns");
		System.out.println("3. variants");
				
		double x, y;
		
		try {
			System.out.print("x=");
            x = sc.nextDouble();
			System.out.print("y=");
            y = sc.nextDouble();
        } catch (Exception e) {
            System.out.println("input-output error");
            sc.close();
            return;
        }
		
		sc.close();
		
		System.out.println("result:");

		if (checkCircle(x, y)== 0) {
			System.out.println("grey");
		} else if (checkTriangle(x, y) + checkRectangleInTriangle(x, y) == 0) {
			System.out.println("grey");
		} else {
			System.out.println("white");
		}
	}

}

