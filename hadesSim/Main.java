package hadesSim;

import java.util.Scanner;

public class Main {

	@SuppressWarnings({ "resource", "null" })
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		boolean again = true;
		boolean hasError = false;
		
		while(again == true){
			
			BattleSim.main(args);
			
			System.out.println("\nSimulate again? Y/N");
			
			do {
				
				hasError = false;
			
				String input = in.next();
				input = input.toUpperCase();
			
				if(input.contains("N")) {
					again = false;
				} else if(input.contains("Y") == false) {
					System.out.println("Input Error, Try again!");
					hasError = true;
				}
				
			}while(hasError == true);
			
		}

	}
}
