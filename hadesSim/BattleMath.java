package hadesSim;

public class BattleMath {
	private Battleship ship1;
	private Battleship ship2;
	private boolean RNG;
	private double bs1MirrorMult;
	private double bs2MirrorMult;
	private double bs1Hull; // these will get updated after laser method goes through
	private double bs2Hull; // will replace original hull with hull after laser method
	private int bs1WeaponDMG; // these value gets changed based on weapon
	private int bs2WeaponDMG; // once it checks for laser and goes through ramp up it will replace initial value with max value
	private int coinIsHeads;
	private int s1Wins;
	private int s2Wins;

	public BattleMath(Battleship firstShip, Battleship secondShip, boolean rng) {
		ship1 = firstShip;
		ship2 = secondShip;
		RNG = rng;
	}

	public void doMath() {
		startFight();
	}

	private void startFight() {
		checkMirror(); // this sets bsMirrorMult on both ships
		for(int i = 0; i < 1000; i++) {
			setBS1Hull(); // first we reset hull to the hull passed down from ships
			setBS2Hull();
			resetBS1WeaponDMG(); // then reset weapon value to original ship weapon values
			resetBS2WeaponDMG();
			if(ship1.getWeaponType().contains("LAS")==true || ship2.getWeaponType().contains("LAS")==true) {
				checkLaser(getBS1Hull(), getBS2Hull()); // this is laser ramp up damage
			}
			double ship1Hull = getBS1Hull(); // these update the hull damage after laser method goes through
			double ship2Hull = getBS2Hull();
			// this bit of code determines who attacks first if RNG is enabled
			if(getRNG()==true) {
				if(isHeads()==true) {
					ship1Hull = ship1Hull - getBS2WeaponDMG() + (getBS1WeaponDMG() * getBS2MirrorMult());
					coinIsHeads++;
				} else {
					ship2Hull = ship2Hull - getBS1WeaponDMG() + (getBS2WeaponDMG() * getBS1MirrorMult());
				}
			}
			// this loop runs until one ship dies
			while(ship1Hull >= 0 && ship2Hull >= 0) {
				ship1Hull = ship1Hull - getBS2WeaponDMG() + (getBS1WeaponDMG() * getBS2MirrorMult());
				ship2Hull = ship2Hull - getBS1WeaponDMG() + (getBS2WeaponDMG() * getBS1MirrorMult());
			}
			if(ship1Hull <= 0) s2Wins++;
			if(ship2Hull <= 0) s1Wins++;
		}
	}

	private void checkMirror() {
		// this method will check if either ship has mirror
		// if it has mirror the respective value gets updated
		bs1MirrorMult = 0.0;
		bs2MirrorMult = 0.0;
		if(ship1.getShieldType().contains("MIR")==true) {
			bs1MirrorMult = ship1.getReflectDamage();
		}
		if(ship2.getShieldType().contains("MIR")==true) {
			bs2MirrorMult = ship2.getReflectDamage();
		}
	}

	private void checkLaser(double shipOneHull, double shipTwoHull) {
		double ship1Hull = shipOneHull;
		double ship2Hull = shipTwoHull;
		int ship1LaserStep = (ship1.getMaxLaser() - ship1.getWeapon()) / 30;
		int ship2LaserStep = (ship2.getMaxLaser() - ship2.getWeapon()) / 30;
		// this series of if statements will determine if both ships have laser first
		// it then updates the hull values and weapon values for both ships before it moves onto the main battle method
		if(ship1.getWeaponType().contains("LAS")==true && ship2.getWeaponType().contains("LAS")==true) {
			for(int i = 0; i < 31; i++) {
				if(getRNG()==true) {
					if(isHeads()==true) {
						ship1Hull = ship1Hull - ship2.getWeapon() + (ship1LaserStep * i) + (ship1.getWeapon() * getBS2MirrorMult());
					} else {
						ship2Hull = ship2Hull - ship1.getWeapon() + (ship2.getWeapon() * getBS1MirrorMult());
					}
					while(ship1Hull >= 0 && ship2Hull >= 0) {
						ship1Hull = ship1Hull - ship2.getWeapon() + (ship1LaserStep * (i - 1)) + (ship1.getWeapon() * getBS2MirrorMult());
						ship2Hull = ship2Hull - ship1.getWeapon() + (ship2LaserStep * (i - 1)) + (ship2.getWeapon() * getBS1MirrorMult());
					}
				}
				if(ship1Hull <= 0 || ship2Hull <=0) i = 32; // this bit ends the for loop if one ship dies before laser is ramped up
			}
			setBS1WeaponDMG(ship1.getMaxLaser()); // setting weapons
			setBS2WeaponDMG(ship2.getMaxLaser()); 
			bs1Hull = ship1Hull; // setting hull
			bs2Hull = ship2Hull;
		}
		if(ship1.getWeaponType().contains("LAS")) {
			for(int i = 0; i < 31; i++) {
				ship1Hull = getBS1Hull();
				ship2Hull = getBS2Hull();
				bs1Hull = ship1Hull;
				bs2Hull = ship2Hull;
				if(getRNG()==true) {
					if(isHeads()==true) {
						ship1Hull = ship1Hull - ship2.getWeapon() + (ship1LaserStep * i) + (ship1.getWeapon() * getBS2MirrorMult());
					} else {
						ship2Hull = ship2Hull - ship1.getWeapon() + (ship2.getWeapon() * getBS1MirrorMult());
					}
				}
				while(ship1Hull >= 0 && ship2Hull >= 0) {
					ship1Hull = ship1Hull - ship2.getWeapon() + (ship1LaserStep * i) + (ship1.getWeapon() * getBS2MirrorMult());
					ship2Hull = ship2Hull - ship1.getWeapon() + (ship2.getWeapon() * getBS1MirrorMult());
				}
				if(ship1Hull <= 0 || ship2Hull <=0) i = 32;
			}
			setBS1WeaponDMG(ship1.getMaxLaser());
			setBS2WeaponDMG(ship2.getWeapon());
			bs1Hull = ship1Hull;
			bs2Hull = ship2Hull;
		}
		if(ship2.getWeaponType().contains("LAS")) {
			for(int i = 0; i < 31; i++) {
				ship1Hull = getBS1Hull();
				ship2Hull = getBS2Hull();
				if(getRNG()==true) {
					if(isHeads()==true) {
						ship1Hull = ship1Hull - ship2.getWeapon() + (ship1LaserStep * i) + (ship1.getWeapon() * getBS2MirrorMult());
					} else {
						ship2Hull = ship2Hull - ship1.getWeapon() + (ship2.getWeapon() * getBS1MirrorMult());
					}
				}
				while(ship1Hull >= 0 && ship2Hull >= 0) {
					ship1Hull = ship1Hull - ship2.getWeapon() + (ship1LaserStep * i) + (ship1.getWeapon() * getBS2MirrorMult());
					ship2Hull = ship2Hull - ship1.getWeapon() + (ship2.getWeapon() * getBS1MirrorMult());
				}
				if(ship1Hull <= 0 || ship2Hull <=0) i = 32;
			}
			setBS2WeaponDMG(ship2.getMaxLaser());
			setBS1WeaponDMG(ship1.getWeapon());
			bs1Hull = ship1Hull;
			bs2Hull = ship2Hull;
		}
	}
	// coin flipper 50/50 shot at being over or under 0.5 to determine heads/tails
	private boolean isHeads() {
		boolean isHeads = false;
		if(Math.random() < 0.5) {
			isHeads = true;
		}
		return isHeads;
	}
	// determines who wins and returns which side wins
	public String determineWinner() {
		String outcome = "";
		if(s1Wins > s2Wins) {
			outcome = "Side 1 wins";
		}
		if(s1Wins < s2Wins) {
			outcome = "Side 2 wins";
		}
		if(s1Wins == s2Wins) {
			outcome = "Tie";
		}
		return outcome;
	}
	public int findNumHeads() {
		return coinIsHeads;
	}
	// determines who wins and then returns how many times they won
	public double determineWinPct() {
		double winPct = 0;
		if(s1Wins > s2Wins) {
			winPct = 100 * (s1Wins / 1000.0);
		}
		if(s1Wins < s2Wins) {
			winPct = 100 * (s2Wins / 1000.0);
		}
		return winPct;
	}

	private void setBS1Hull() {
		bs1Hull = ship1.getFinalHull();
	}

	private void setBS2Hull() {
		bs2Hull = ship2.getFinalHull();
	}

	private double getBS1Hull() {
		return bs1Hull;
	}

	private double getBS2Hull() {
		return bs2Hull;
	}

	private double getBS1MirrorMult() {
		return bs1MirrorMult;
	}

	private double getBS2MirrorMult() {
		return bs2MirrorMult;
	}

	private boolean getRNG() {
		return RNG;
	}

	private void setBS1WeaponDMG(int newDamage) {
		bs1WeaponDMG = newDamage;
	}

	private void setBS2WeaponDMG(int newDamage) {
		bs2WeaponDMG = newDamage;
	}

	private void resetBS1WeaponDMG() {
		bs1WeaponDMG = ship1.getWeapon();
	}

	private void resetBS2WeaponDMG() {
		bs2WeaponDMG = ship2.getWeapon();
	}

	private int getBS1WeaponDMG() {
		return bs1WeaponDMG;
	}

	private int getBS2WeaponDMG() {
		return bs2WeaponDMG;
	}
}
