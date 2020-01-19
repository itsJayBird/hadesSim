package hadesSim;
import java.util.Scanner;

public class BattleSim {

	static String shipStats;
	
	static int bsOneLv;
	static char bsOneWeaponType;
	static int bsOneWeaponLevel;
	static char bsOneShieldType;
	static int bsOneShieldLevel;
	
	static int bsTwoLv;
	static char bsTwoWeaponType;
	static int bsTwoWeaponLevel;
	static char bsTwoShieldType;
	static int bsTwoShieldLevel;
	
	public BattleSim() {
		
	}
	
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		in.useDelimiter("\n");
		System.out.println("  Weapons | Shields");
		System.out.println(" Batt - b | Omega - b \n Las  - l | Passive - p \n");
		System.out.println("ex: lv 4 bs with omega 4 and batt 5 vs lv 4 bs with delta 3 and laser 6 \n BS4:O4:B5,BS4:D3:L5");
		String init = in.next();
		init = init.replaceAll("\\s+","");
		init = init.toUpperCase();
		shipStats = init;
		
		makeShips();
		
		Battleship bs1 = new Battleship(bsOneLv, bsOneWeaponType, bsOneWeaponLevel, bsOneShieldType, bsOneShieldLevel);
		Battleship bs2 = new Battleship(bsTwoLv, bsTwoWeaponType, bsTwoWeaponLevel, bsTwoShieldType, bsTwoShieldLevel);
	
		bs1.setWeapon(bs1);
		bs1.setShield(bs1);
		bs2.setWeapon(bs2);
		bs2.setShield(bs2);
		
		System.out.println("bs1 stats: " + bs1.hullStrength + " " + bs1.weaponStrength);
		System.out.println("bs2 stats: " + bs2.hullStrength + " " + bs2.weaponStrength);
		
		BattleMath sim = new BattleMath(bs1, bs2);
		
		/*
		if(bs1.weaponType == 'L') {
			sim.battleMathLaser1();
		}
		*/
		
		sim.doMathAt100();
		sim.doMathAt50();
	}
	
	
	static void makeShips() {
		
		// example input BS6:O5:L5,BS5:P3:B5
		
		// Separates the two ships
		String delim1 = ",";
		String[] a = shipStats.split(delim1);
		String s1 = a[0];
		String s2 = a[1];
		
		// determining first ships attributes
		String delim2 = ":";
		String[] ship1 = s1.split(delim2);
		
		String bs1 = ship1[0];
		String lv = bs1.substring(2);
		int bsLevel = Integer.parseInt(lv);
		
		String shield1 = ship1[1];
		char shieldType = shield1.charAt(0);
		int shieldLevel = 1;
		int weaponLevel = 1;
		
		if(shield1.length() == 2) {
			String shlv = shield1.substring(1);
			shieldLevel = Integer.parseInt(shlv);
		}
		
		if(shield1.length() == 3) {
			String shieldLv = shield1.substring(1,3);
			shieldLevel = Integer.parseInt(shieldLv);
		}
		
		String weapon1 = ship1[2];
		char weaponType = weapon1.charAt(0);
		
		if(weapon1.length() == 2) {
			String wlv = weapon1.substring(1);
			weaponLevel = Integer.parseInt(wlv);
		}
		
		if(weapon1.length() == 3) {
			String weaponLv = weapon1.substring(1, 3);
			weaponLevel = Integer.parseInt(weaponLv);
		}
		
		bsOneLv = bsLevel;
		bsOneShieldType = shieldType;
		bsOneShieldLevel = shieldLevel;
		bsOneWeaponType = weaponType;
		bsOneWeaponLevel = weaponLevel;
		
		
		
		// determine ship 2 attributes
		String[] ship2 = s2.split(delim2);
		
		String bs2 = ship2[0];
		String lv2 = bs2.substring(2);
		int bsLevel2 = Integer.parseInt(lv2);
		
		String shield2 = ship2[1];
		char shieldType2 = shield2.charAt(0);
		int shieldLevel2 = 1;
		int weaponLevel2 = 1;
		
		if(shield2.length() == 2) {
			String shlv2 = shield2.substring(1);
			shieldLevel2 = Integer.parseInt(shlv2);
		}
		
		if(shield2.length() == 3) {
			String shieldLv2 = shield2.substring(1,3);
			shieldLevel2 = Integer.parseInt(shieldLv2);
		}
		
		String weapon2 = ship2[2];
		char weaponType2 = weapon2.charAt(0);
		
		if(weapon2.length() == 2) {
			String wlv2 = weapon2.substring(1);
			weaponLevel2 = Integer.parseInt(wlv2);
		}
		
		if(weapon2.length() == 3) {
			String weaponLv2 = weapon2.substring(1, 3);
			weaponLevel2 = Integer.parseInt(weaponLv2);
		}
		
		bsTwoLv = bsLevel2;
		bsTwoShieldType = shieldType2;
		bsTwoShieldLevel = shieldLevel2;
		bsTwoWeaponType = weaponType2;
		bsTwoWeaponLevel = weaponLevel2;
		
		
	}

	
}

