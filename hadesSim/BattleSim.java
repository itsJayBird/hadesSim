package hadesSim;

public class BattleSim {
	
	public BattleSim() {
		
	}
	
	public static void main(String[] args) {
		UserInput a = new UserInput();
		boolean hasError = false;
		
		System.out.println("\nInput in form of BSLVL:SHIELDLVL:WEAPONLVL V BSLVL:SHIELDLVL:WEAPONLVL \n");
		System.out.println("Modify shield or battleship hull using @XX \n");
		System.out.println("Example:  BS4@75:OMG10:BAT8 V BS5:DLT11%75:LAS9");
		
		do {			
			hasError = false;
			a.takeUserInput();
			
			try {
				a.makeShips();
			}
			catch(ArrayIndexOutOfBoundsException e) {
				System.out.println("Input error, Try again!");
				hasError = true;
			}			
		}while(hasError == true);
	
		a.makeShips();
		
		do {
			hasError = false;
			try {
				a.setRNG();
			}
			catch(NullPointerException e){
				System.out.println("Invalid input, Try again!");
				hasError = true;
			}
			
		}while(hasError == true);
		
		BattleMath sim = new BattleMath(a.getSide1(), a.getSide2(), a.getRNG());

		sim.doMath();
		
		System.out.println(sim.determineWinner() + " " + sim.determineWinPct() + "% of the time!");
		
		if(a.getRNG() == true) System.out.println("Side 1 attacks first " + sim.findNumHeads() + "/" + "1000 times!");
		
		
	}

	
}