package hadesSim;

public class BattleMath {
	
	private double bsOneHullStr;
	private double bsOneWeaponStr;
	private double bsOneLaserMax;
	private String bsOneLas;
	private String bsOneShield;
	private double bsOneMirror;
	
	private double bsTwoHullStr;
	private double bsTwoWeaponStr;
	private double bsTwoLaserMax;
	private String bsTwoLas;
	private String bsTwoShield;
	private double bsTwoMirror;
	
	private int s1Wins;
	private int s2Wins;
	private int coinIsHeads;
	
	public BattleMath() {
		
	}

	public BattleMath(Battleship firstShip, Battleship secondShip) {
		
		BattleSim now = new BattleSim();
		
		bsOneHullStr = (firstShip.getHull() * now.getSide1HealthMultiplier()) + 
						(firstShip.getShield() * now.getSide1ShieldMultiplier());
		bsOneWeaponStr = firstShip.getWeapon();
		bsOneLas = firstShip.getWeaponType();
		bsOneLaserMax = firstShip.getMaxLaser();
		bsOneShield = firstShip.getShieldType();
		bsOneMirror = firstShip.getReflectDamage();
		
		
		bsTwoHullStr = (secondShip.getHull() * now.getSide2HealthMultiplier()) +
						(secondShip.getShield() * now.getSide2ShieldMultiplier());
		bsTwoWeaponStr = secondShip.getWeapon();
		bsTwoLas = secondShip.getWeaponType();
		bsTwoLaserMax = secondShip.getMaxLaser();
		bsTwoShield = secondShip.getShieldType();
		bsTwoMirror = secondShip.getReflectDamage();
	}
	
	public void doMath() {
		
		if(bsOneLas == "LAS" || bsTwoLas == "LAS") {
			battleMathLaser1();
		} else {
			battleMath();
		}
		
	}
	
	private void battleMath() {
		
		BattleSim a = new BattleSim();
		
		if(bsOneShield == "MIR" || bsTwoShield == "MIR") {
			
			bsTwoWeaponStr = (bsTwoWeaponStr + (bsOneWeaponStr * bsTwoMirror));
			bsOneWeaponStr = (bsOneWeaponStr + (bsTwoWeaponStr * bsOneMirror));
			
		} else if(bsTwoShield == "MIR") {
			
			bsTwoWeaponStr = (bsTwoWeaponStr + (bsOneWeaponStr * bsTwoMirror));
			
		} else if(bsOneShield == "MIR") {
			
			bsOneWeaponStr = (bsOneWeaponStr + (bsTwoWeaponStr * bsOneMirror));
			
		}
		
		for(int i = 0; i < 1000; i++) {
		
			double bsOneHull = bsOneHullStr;
			double bsTwoHull = bsTwoHullStr;
			
			
			if(a.getRNG() == true) {
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

		BattleSim a = new BattleSim();
		
		double lasIncrease = 0;
		
		if(bsOneLas == "LAS") {
			lasIncrease = (bsOneLaserMax - bsOneWeaponStr) / 45;
		}
		
		if(bsTwoLas == "LAS") {
			lasIncrease = (bsTwoLaserMax - bsTwoWeaponStr) / 45;
		}
		
		if(bsOneShield == "MIR" || bsTwoShield == "MIR") {
			
			bsTwoWeaponStr = (bsTwoWeaponStr + (bsOneWeaponStr * bsTwoMirror));
			bsOneWeaponStr = (bsOneWeaponStr + (bsTwoWeaponStr * bsOneMirror));
			bsTwoLaserMax = (bsTwoLaserMax + (bsOneWeaponStr * bsTwoMirror));
			bsOneLaserMax = (bsOneLaserMax + (bsTwoWeaponStr * bsOneMirror));
			
		} else if(bsTwoShield == "MIR") {
			
			bsTwoWeaponStr = (bsTwoWeaponStr + (bsOneWeaponStr * bsTwoMirror));
			bsTwoLaserMax = (bsTwoLaserMax + (bsOneWeaponStr * bsTwoMirror));
			
		} else if(bsOneShield == "MIR") {
			
			bsOneWeaponStr = (bsOneWeaponStr + (bsTwoWeaponStr * bsOneMirror));
			bsOneLaserMax = (bsOneLaserMax + (bsTwoWeaponStr * bsOneMirror));
			
		}
		
		for(int i = 0; i <= 1000; i++) {
			
			double bsOneHull = bsOneHullStr;
			double bsTwoHull = bsTwoHullStr;
		
		if(a.getRNG() == true) {
			if(isHeads() == true) {
				bsOneHull = bsOneHull - bsTwoWeaponStr;
				coinIsHeads++;
			} else {
				bsTwoHull = bsTwoHull - bsOneWeaponStr;
			}
		}
			
			if(bsOneLas == "LAS" && bsTwoLas == "LAS") {
			
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
			
			if(bsOneLas == "LAS") {
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
			
			if(bsTwoLas == "LAS") {
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