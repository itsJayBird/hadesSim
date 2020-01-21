package hadesSim;

public class BattleSim {
	
	public BattleSim() {
		
	}
	
	public static void main(String[] args) {
		
		UserInput a = new UserInput();
		
		a.takeUserInput();
		a.makeShips();
		a.setRNG();
		
		//System.out.println("bs1 stats: " + ((bs1.getHull() * bsOneHealthMultiplier) + (bs1.getShield() * bsOneShieldMultiplier)) + " " + bs1.getWeapon() + " " + bs1.getMaxLaser());
		//System.out.println("bs2 stats: " + ((bs2.getHull() * bsTwoHealthMultiplier) + (bs2.getShield() * bsTwoShieldMultiplier)) +  " " + bs2.getWeapon() + " " + bs2.getMaxLaser());
		
		BattleMath sim = new BattleMath(a.getSide1(), a.getSide2());

		sim.doMath();
		
		System.out.println(sim.determineWinner() + " " + sim.determineWinPct() + "% of the time!");
		System.out.println("Side 1 attacks first " + sim.findNumHeads() + "/" + "1000 times!");
		
	}
	
}