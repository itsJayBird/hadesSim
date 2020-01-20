package hadesSim;

public class BattleMath {
	
	private double bsOneHullStr;
	private double bsOneWeaponStr;
	private double bsOneLaserMax;
	private char bsOneLas;
	private char bsOneShield;
	private double bsOneMirror;
	
	private double bsTwoHullStr;
	private double bsTwoWeaponStr;
	private double bsTwoLaserMax;
	private char bsTwoLas;
	private char bsTwoShield;
	private double bsTwoMirror;
	
	private int s1Wins;
	private int s2Wins;
	private int coinIsHeads;
	
	public BattleMath() {
		
	}

	public BattleMath(Battleship firstShip, Battleship secondShip) {
		bsOneHullStr = (firstShip.hullStrength * BattleSim.bsOneHealthMultiplier) + 
						(firstShip.shieldStrength * BattleSim.bsOneShieldMultiplier);
		bsOneWeaponStr = firstShip.weaponStrength;
		bsOneLas = firstShip.weaponType;
		bsOneLaserMax = firstShip.maxLasStr;;
		bsOneShield = firstShip.shieldType;
		bsOneMirror = firstShip.reflectDamage;
		
		
		bsTwoHullStr = (secondShip.hullStrength * BattleSim.bsTwoHealthMultiplier) +
						(secondShip.shieldStrength * BattleSim.bsTwoShieldMultiplier);
		bsTwoWeaponStr = secondShip.weaponStrength;
		bsTwoLas = secondShip.weaponType;
		bsTwoLaserMax = secondShip.maxLasStr;
		bsTwoShield = secondShip.shieldType;
		bsTwoMirror = secondShip.reflectDamage;
	}
	
	public void doMath() {
		
		if(bsOneLas == 'L' || bsTwoLas == 'L') {
			battleMathLaser1();
		} else {
			battleMath();
		}
		
	}
	
	private void battleMath() {
		
		if(bsOneShield == 'M' || bsTwoShield == 'M') {
			
			bsTwoWeaponStr = (bsTwoWeaponStr + (bsOneWeaponStr * bsTwoMirror));
			bsOneWeaponStr = (bsOneWeaponStr + (bsTwoWeaponStr * bsOneMirror));
			
		} else if(bsTwoShield == 'M') {
			
			bsTwoWeaponStr = (bsTwoWeaponStr + (bsOneWeaponStr * bsTwoMirror));
			
		} else if(bsOneShield == 'M') {
			
			bsOneWeaponStr = (bsOneWeaponStr + (bsTwoWeaponStr * bsOneMirror));
			
		}
		
		for(int i = 0; i < 1000; i++) {
		
			double bsOneHull = bsOneHullStr;
			double bsTwoHull = bsTwoHullStr;
			
			
			if(BattleSim.rng == true) {
				if(isHeads() == true) {
					bsOneHull = bsOneHull - bsTwoWeaponStr;
					coinIsHeads++;
				} else {
					bsTwoHull = bsTwoHull - bsOneWeaponStr;
				}
			}
			
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
	
	
	private boolean isHeads() {
		
		boolean isHeads = false;
		
		if(Math.random() < 0.5) {
			isHeads = true;
		}
		
		return isHeads;
		
	}

	public void battleMathLaser1() {

		double lasIncrease = 0;
		
		if(bsOneLas == 'L') {
			lasIncrease = (bsOneLaserMax - bsOneWeaponStr) / 45;
		}
		
		if(bsTwoLas == 'L') {
			lasIncrease = (bsTwoLaserMax - bsTwoWeaponStr) / 45;
		}
		
		if(bsOneShield == 'M' || bsTwoShield == 'M') {
			
			bsTwoWeaponStr = (bsTwoWeaponStr + (bsOneWeaponStr * bsTwoMirror));
			bsOneWeaponStr = (bsOneWeaponStr + (bsTwoWeaponStr * bsOneMirror));
			bsTwoLaserMax = (bsTwoLaserMax + (bsOneWeaponStr * bsTwoMirror));
			bsOneLaserMax = (bsOneLaserMax + (bsTwoWeaponStr * bsOneMirror));
			
		} else if(bsTwoShield == 'M') {
			
			bsTwoWeaponStr = (bsTwoWeaponStr + (bsOneWeaponStr * bsTwoMirror));
			bsTwoLaserMax = (bsTwoLaserMax + (bsOneWeaponStr * bsTwoMirror));
			
		} else if(bsOneShield == 'M') {
			
			bsOneWeaponStr = (bsOneWeaponStr + (bsTwoWeaponStr * bsOneMirror));
			bsOneLaserMax = (bsOneLaserMax + (bsTwoWeaponStr * bsOneMirror));
			
		}
		
		for(int i = 0; i <= 1000; i++) {
			
			double bsOneHull = bsOneHullStr;
			double bsTwoHull = bsTwoHullStr;
		
		if(BattleSim.rng == true) {
			if(isHeads() == true) {
				bsOneHull = bsOneHull - bsTwoWeaponStr;
				coinIsHeads++;
			} else {
				bsTwoHull = bsTwoHull - bsOneWeaponStr;
			}
		}
			
			if(bsOneLas == 'L' && bsTwoLas == 'L') {
			
				for(int k = 0; k < 30; k++) {
					bsOneHull = bsOneHull - (bsTwoWeaponStr + (lasIncrease * (k + 1)));
					bsTwoHull = bsTwoHull - (bsOneWeaponStr + (lasIncrease * (k + 1)));
					
					if(bsOneHull <= 0 || bsTwoHull <= 0) {
						k = 46;
					}
				}
				
				while(bsOneHull > 0 && bsTwoHull > 0){
					
					bsOneHull = bsOneHull - bsTwoLaserMax;
					bsTwoHull = bsTwoHull - bsOneLaserMax;
				
				}
				
			}
			
			if(bsOneLas == 'L') {
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
			
			if(bsTwoLas == 'L') {
				for(int k = 0; k < 30; k++) {
					bsOneHull = bsOneHull - (bsTwoWeaponStr + (lasIncrease * (k + 1)));
					bsTwoHull = bsTwoHull - bsOneWeaponStr;
					
					if(bsOneHull <= 0 || bsTwoHull <= 0) {
						k = 46;
					}
				}
				
				while(bsOneHull > 0 && bsTwoHull > 0){
					
					bsOneHull = bsOneHull - bsTwoLaserMax;
					bsTwoHull = bsTwoHull - bsOneWeaponStr;
				
				}
			}
				
				
				if(bsOneHull <= 0) {
					s2Wins++;
					} 
				if(bsTwoHull <= 0) {
					s1Wins++;
					}
			
			
			}	
		
	}
	
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