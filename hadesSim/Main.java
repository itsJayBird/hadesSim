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
				}
			
				try {
					if(input.contains("N") == false && input.contains("Y") == false) {
						again = (Boolean) null;
					}
				}
				catch(NullPointerException e) {
					System.out.println("Incorrect input, Try again!\n");
					hasError = true;
				}
				
			}while(hasError == true);
			
		}

	}
}
