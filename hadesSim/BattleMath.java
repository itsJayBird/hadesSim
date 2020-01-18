package hadesSim;

public class BattleMath {
	
	static int bsOneHullStr;
	static int bsOneWeaponStr;
	static int bsOneLaserMax;
	static char bsOneLas;
	
	static int bsTwoHullStr;
	static int bsTwoWeaponStr;
	static int bsTwoLaserMax;
	static char bsTwoLas;
	

	public BattleMath(Battleship firstShip, Battleship secondShip) {
		bsOneHullStr = firstShip.hullStrength;
		bsOneWeaponStr = firstShip.weaponStrength;
		bsOneLas = firstShip.weaponType;
		bsOneLaserMax = firstShip.maxLasStr;
		
		bsTwoHullStr = secondShip.hullStrength;
		bsTwoWeaponStr = secondShip.weaponStrength;
		bsTwoLas = secondShip.weaponType;
		bsTwoLaserMax = secondShip.maxLasStr;
	}
	

	public void battleMathLaser1() {
		
		int s1Wins = 0;
		int s2Wins = 0;
		int lasIncrease = (bsOneLaserMax - bsOneWeaponStr) / 45;
		
		for(int i = 0; i <= 1000; i++) {
			double bsOneHull = Math.random() * bsOneHullStr;
			double bsTwoHull = Math.random() * bsTwoHullStr;
			
			//double bsOneHull = bsOneHullStr;
			//double bsTwoHull = bsTwoHullStr;
			
			for(int k = 0; k < 45; k++) {
				//System.out.println(bsTwoHull);
				bsOneHull = bsOneHull - bsTwoWeaponStr;
				bsTwoHull = bsTwoHull - (bsOneWeaponStr + (lasIncrease * k));
			}
			
			while(bsOneHull > 0 && bsTwoHull > 0){
				
				//System.out.println(bsOneHull + " " + bsTwoHull);
				bsOneHull = bsOneHull - bsTwoWeaponStr;
				bsTwoHull = bsTwoHull - bsOneLaserMax;
				
				if(bsOneHull <= 0) {
					s2Wins++;
					} 
				if(bsTwoHull <= 0) {
					s1Wins++;
					}
			
				}
			}
		
		if(s1Wins > s2Wins) {
			double winAvg = Math.round(100 * (s1Wins/1000.0));
			System.out.println("Side 1 Wins " + winAvg + "% of the time (" + s1Wins + "/" + s2Wins + ")");
		} else if(s2Wins > s1Wins) {
			double winAvg = Math.round(100 * (s2Wins/1000.0));
			System.out.println("Side 2 Wins " + winAvg + "% of the time (" + s1Wins + "/" + s2Wins + ")");
		} else if( s1Wins == s2Wins) {
			System.out.println("Tie!");
		}
		
		
	}
	
	public void battleMath() {
		int s1Wins = 0;
		int s2Wins = 0;
		
		for(int i = 0; i <= 1000; i++) {
			double bsOneHull = Math.random() * bsOneHullStr;
			double bsTwoHull = Math.random() * bsTwoHullStr;
			
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
		
		if(s1Wins > s2Wins) {
			double winAvg = Math.round(100 * (s1Wins/1000.0));
			System.out.println("Side 1 Wins " + winAvg + "% of the time (" + s1Wins + "/" + s2Wins + ")");
		} else if(s2Wins > s1Wins) {
			double winAvg = Math.round(100 * (s2Wins/1000.0));
			System.out.println("Side 2 Wins " + winAvg + "% of the time (" + s1Wins + "/" + s2Wins + ")");
		} else if( s1Wins == s2Wins) {
			System.out.println("Tie!");
		}
		
	}
	
}