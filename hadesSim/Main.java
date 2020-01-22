package hadesSim;

import java.util.Scanner;

public class Main {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		boolean again = true;
		
		while(again == true){
			
			BattleSim.main(args);
			
			System.out.println("Simulate again? Y/N");
			String input = in.next();
			input = input.toUpperCase();
			
			if(input.contains("Y")) {
				again = true;
			} else if(input.contains("N")) {
				again = false;
			}
			
		}

	}
}
