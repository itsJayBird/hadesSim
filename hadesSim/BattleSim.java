package hadesSim;
import java.util.Scanner;

public class BattleSim {

	static String shipStats;
	
	static int bsOneLv;
	static char bsOneWeaponType;
	static int bsOneWeaponLevel;
	static char bsOneShieldType;
	static int bsOneShieldLevel;
	static double bsOneHealthMultiplier;
	static double bsOneShieldMultiplier;
	
	static int bsTwoLv;
	static char bsTwoWeaponType;
	static int bsTwoWeaponLevel;
	static char bsTwoShieldType;
	static int bsTwoShieldLevel;
	static double bsTwoHealthMultiplier;
	static double bsTwoShieldMultiplier;
	
	static boolean rng;
	
	public BattleSim() {
		
	}
	
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		in.useDelimiter("\n");
		System.out.println("  Weapons | Shields");
		System.out.println(" Batt - b | Omega - b \n Las  - l | Passive - p \n Mass - m | Delta - d \n          | Mirror - m");
		System.out.println("ex: lv 4 bs with omega 4 and batt 5 vs lv 4 bs with delta 3 and laser 6 \n BS4:O4:B5,BS4:D3:L5");
		String init = in.next();
		init = init.replaceAll("\\s+","");
		init = init.toUpperCase();
		shipStats = init;
		System.out.println("Want to randomize who attacks first? Y/N");
		String randomizer = in.next();
		randomizer = randomizer.toUpperCase();
		
		if(randomizer.contains("Y")) {
			rng = true;
		} else if(randomizer.contains("N")) {
			rng = false;
		}
		
		makeShips();
		
		Battleship bs1 = new Battleship(bsOneLv, bsOneWeaponType, bsOneWeaponLevel, bsOneShieldType, bsOneShieldLevel);
		Battleship bs2 = new Battleship(bsTwoLv, bsTwoWeaponType, bsTwoWeaponLevel, bsTwoShieldType, bsTwoShieldLevel);
	
		bs1.setWeapon(bs1);
		bs1.setShield(bs1);
		bs2.setWeapon(bs2);
		bs2.setShield(bs2);
		
		System.out.println("bs1 stats: " + ((bs1.hullStrength * bsOneHealthMultiplier) + (bs1.shieldStrength * bsOneShieldMultiplier)) + " " + bs1.weaponStrength + " " + bs1.maxLasStr);
		System.out.println("bs2 stats: " + ((bs2.hullStrength * bsTwoHealthMultiplier) + (bs2.shieldStrength * bsTwoShieldMultiplier)) +  " " + bs2.weaponStrength + " " + bs2.maxLasStr);
		
		BattleMath sim = new BattleMath(bs1, bs2);

		sim.doMath();
		
		System.out.println(sim.determineWinner() + " " + sim.determineWinPct() + "% of the time!");
		System.out.println("Side 1 attacks first " + sim.findNumHeads() + "/" + "1000 times!");
		
	}
	
	
	static void makeShips() {
		
		// example input BS6:O5:L5,BS5:P3:B5
		// second input BS6-95:D4:B10,BS4-50:P10:L7
		
		// Separates the two ships
		String delim1 = ",";
		String[] a = shipStats.split(delim1);
		String s1 = a[0];
		String s2 = a[1];
		
		// determining first ships attributes
		String delim2 = ":";
		String[] ship1 = s1.split(delim2);
		
		String bs1 = ship1[0];
		String lv = bs1.substring(2,3);
		int bsLevel = Integer.parseInt(lv);
		
		double bsHealth = 0;
		String bsHP = "100";
		
		if(bs1.contains("-") == false) {
			bsHealth = 100;
		} else if(bs1.contains("-")) {
			bsHP = bs1.substring((bs1.indexOf('-') + 1), bs1.length());
		}
		bsHealth = Integer.parseInt(bsHP) / 100.0;
		
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
		
		double shieldHealth = 0;
		String shieldHP = "100";
		
		if(shield1.contains("-") == false) {
			shieldHealth = 100;
		} else if(shield1.contains("-")) {
			shieldHP = shield1.substring((shield1.indexOf('-') + 1), shield1.length());
		}
		shieldHealth = Integer.parseInt(shieldHP) / 100.0;
		
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
		bsOneHealthMultiplier = bsHealth;
		bsOneShieldMultiplier = shieldHealth;
		
		
		
		// determine ship 2 attributes
		String[] ship2 = s2.split(delim2);
		
		String bs2 = ship2[0];
		String lv2 = bs2.substring(2);
		int bsLevel2 = Integer.parseInt(lv2);
		
		double bs2Health = 0;
		String bs2HP = "100";
		
		if(bs2.contains("-") == false) {
			bs2Health = 100;
		} else if(bs2.contains("-")) {
			bs2HP = bs2.substring((bs2.indexOf('-') + 1), bs2.length());
		}
		bs2Health = Integer.parseInt(bs2HP) / 100.0;
		
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
		
		double shield2Health = 0;
		String shield2HP = "100";
		
		if(shield2.contains("-") == false) {
			shield2Health = 100;
		} else if(shield2.contains("-")) {
			shield2HP = shield2.substring((shield2.indexOf('-') + 1), shield2.length());
		}
		shield2Health = Integer.parseInt(shield2HP) / 100.0;
		
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
		bsTwoHealthMultiplier = bs2Health;
		bsTwoShieldMultiplier = shield2Health;
		
		
	}

	
}

