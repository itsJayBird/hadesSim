package hadesSim;

public class BattleMath {
	// vars that get passed along from the battleship class
	// these will be used to perform the math operations of the fights
	private double bsOneHullStr;
	private double bsOneWeaponStr;
	private double bsOneLaserMax;
	private String bsOneLas; // needs to be renamed bsWeapon 
	private String bsOneShield;
	private double bsOneMirror;
	private double bsTwoHullStr;
	private double bsTwoWeaponStr;
	private double bsTwoLaserMax;
	private String bsTwoLas; // needs to be renamed as well
	private String bsTwoShield;
	private double bsTwoMirror;
	// this bit is for tracking win/loss
	private int s1Wins;
	private int s2Wins;
	private int coinIsHeads;
	private boolean RNG; // this bit passes if rng is enabled or not

	public BattleMath(Battleship firstShip, Battleship secondShip, boolean rng) {
		bsOneHullStr = firstShip.getFinalHull();
		bsOneWeaponStr = firstShip.getWeapon();
		bsOneLas = firstShip.getWeaponType();
		bsOneLaserMax = firstShip.getMaxLaser();
		bsOneShield = firstShip.getShieldType();
		bsOneMirror = firstShip.getReflectDamage();
		bsTwoHullStr = secondShip.getFinalHull();
		bsTwoWeaponStr = secondShip.getWeapon();
		bsTwoLas = secondShip.getWeaponType();
		bsTwoLaserMax = secondShip.getMaxLaser();
		bsTwoShield = secondShip.getShieldType();
		bsTwoMirror = secondShip.getReflectDamage();
		RNG = rng;
	}
	
	public void doMath() {
		// this will test if either ship has laser installed
		if(bsOneLas.contains("LAS") == true || bsTwoLas.contains("LAS") == true) {
			battleMathLaser1();
		} else {
			battleMath();
		}
	}
	
	private void battleMath() {
		if(bsOneShield.contains("MIR") == true || bsTwoShield.contains("MIR") == true) {
			bsTwoWeaponStr = (bsTwoWeaponStr + (bsOneWeaponStr * bsTwoMirror));
			bsOneWeaponStr = (bsOneWeaponStr + (bsTwoWeaponStr * bsOneMirror));	
		} else if(bsTwoShield.contains("MIR")) {
			bsTwoWeaponStr = (bsTwoWeaponStr + (bsOneWeaponStr * bsTwoMirror));
		} else if(bsOneShield.contains("MIR")) {
			bsOneWeaponStr = (bsOneWeaponStr + (bsTwoWeaponStr * bsOneMirror));
		}
		// starts 1k simulations for the battle
		for(int i = 0; i < 1000; i++) {
			// this bit resets the hull at the start of the battle
			double bsOneHull = bsOneHullStr;
			double bsTwoHull = bsTwoHullStr;
			// this bit will let second ship attack first if heads
			// if tails will let first ship attack first
			if(RNG == true) {
				if(isHeads() == true) {
					bsOneHull = bsOneHull - bsTwoWeaponStr;
				} else {
					bsTwoHull = bsTwoHull - bsOneWeaponStr;
				}
			}
			// this bit has the ships attack each other until one reaches 0
			while(bsOneHull > 0 && bsTwoHull > 0){
					bsOneHull = bsOneHull - bsTwoWeaponStr;
					bsTwoHull = bsTwoHull - bsOneWeaponStr;
				if(bsOneHull <= 0) {
					s2Wins++;
					} 
				if(bsTwoHull <= 0) {
					s1Wins++;
					}
			}
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

	public void battleMathLaser1() {
		// this first bit determines the increment to reach max laser damage
		double lasIncrease = 0;
		if(bsOneLas.contains("LAS")) {
			lasIncrease = (bsOneLaserMax - bsOneWeaponStr) / 30;
		}
		
		if(bsTwoLas.contains("LAS")) {
			lasIncrease = (bsTwoLaserMax - bsTwoWeaponStr) / 30;
		}
		// this bit checks if either ship has mirror and assigns multiplier to that ships weapon
		// based on the other ships weapon strength
		if(bsOneShield.contains("MIR") == true && bsTwoShield.contains("MIR") == true) {
			bsTwoWeaponStr = (bsTwoWeaponStr + (bsOneWeaponStr * bsTwoMirror));
			bsOneWeaponStr = (bsOneWeaponStr + (bsTwoWeaponStr * bsOneMirror));
			bsTwoLaserMax = (bsTwoLaserMax + (bsOneWeaponStr * bsTwoMirror));
			bsOneLaserMax = (bsOneLaserMax + (bsTwoWeaponStr * bsOneMirror));	
		} else if(bsTwoShield.contains("MIR") == true) {
			bsTwoWeaponStr = (bsTwoWeaponStr + (bsOneWeaponStr * bsTwoMirror));
			bsTwoLaserMax = (bsTwoLaserMax + (bsOneWeaponStr * bsTwoMirror));
		} else if(bsOneShield.contains("MIR") == true) {
			bsOneWeaponStr = (bsOneWeaponStr + (bsTwoWeaponStr * bsOneMirror));
			bsOneLaserMax = (bsOneLaserMax + (bsTwoWeaponStr * bsOneMirror));
		}
		// starts simulation
		for(int i = 0; i <= 1000; i++) {
			double bsOneHull = bsOneHullStr;
			double bsTwoHull = bsTwoHullStr;
		if(RNG == true) {
			if(isHeads() == true) {
				bsOneHull = bsOneHull - bsTwoWeaponStr;
				coinIsHeads++;
			} else {
				bsTwoHull = bsTwoHull - bsOneWeaponStr;
			}
		}
			// if both ships have laser, ramp up damage for both
			// then battle til one reaches 0
			if(bsOneLas.contains("LAS") == true && bsTwoLas.contains("LAS") == true) {
				for(int k = 0; k < 30; k++) {
					bsOneHull = bsOneHull - (bsTwoWeaponStr + (lasIncrease * (k + 1)));
					bsTwoHull = bsTwoHull - (bsOneWeaponStr + (lasIncrease * (k + 1)));
					if(bsOneHull <= 0 || bsTwoHull <= 0) {
						k = 31;
					}
				}
				while(bsOneHull > 0 && bsTwoHull > 0){
					bsOneHull = bsOneHull - bsTwoLaserMax;
					bsTwoHull = bsTwoHull - bsOneLaserMax;
				}
			}
			// if first ship has laser, ramp up damage for it
			// then battle til one reaches 0
			if(bsOneLas.contains("LAS")) {
				for(int k = 0; k < 30; k++) {
					bsOneHull = bsOneHull - bsTwoWeaponStr;
					bsTwoHull = bsTwoHull - (bsOneWeaponStr + (lasIncrease * (k + 1)));
					
					if(bsOneHull <= 0 || bsTwoHull <= 0) {
						k = 46;
					}
				}
				while(bsOneHull > 0 && bsTwoHull > 0){
					bsOneHull = bsOneHull - bsTwoWeaponStr;
					bsTwoHull = bsTwoHull - bsOneLaserMax;
				}
			}
			// if only second ship has laser, ramp up damage
			// then battle til one reaches 0
			if(bsTwoLas.contains("LAS") == true) {
				for(int k = 0; k < 30; k++) {
					bsOneHull = bsOneHull - (bsTwoWeaponStr + (lasIncrease * (k + 1)));
					bsTwoHull = bsTwoHull - bsOneWeaponStr;
					if(bsOneHull <= 0 || bsTwoHull <= 0) {
						k = 31;
					}
				}
				while(bsOneHull > 0 && bsTwoHull > 0){
					bsOneHull = bsOneHull - bsTwoLaserMax;
					bsTwoHull = bsTwoHull - bsOneWeaponStr;
				}
			}
				// adds to counter based on who is alive
				if(bsOneHull <= 0) {
					s2Wins++;
					} 
				if(bsTwoHull <= 0) {
					s1Wins++;
					}
			}	
	}
	// determins who wins and returns which side wins
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
	// determins who wins and then returns how many times they won
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
}
