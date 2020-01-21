package hadesSim;
import java.util.Scanner;

public class BattleSim {

	static String shipStats;
	
	static int bsOneLv;
	static String bsOneWeaponType;
	static int bsOneWeaponLevel;
	static String bsOneShieldType;
	static int bsOneShieldLevel;
	static double bsOneHealthMultiplier;
	static double bsOneShieldMultiplier;
	
	static int bsTwoLv;
	static String bsTwoWeaponType;
	static int bsTwoWeaponLevel;
	static String bsTwoShieldType;
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
		boolean incorrectInput = false;
		
		System.out.println("  Weapons   |   Shields");
		System.out.println(" Batt - bat | Omega   - omg \n Las  - las | Passive - pas \n Mass - mas | Delta   - dlt \n            | Mirror  - mir");
		System.out.println("ex: lv 4 bs with omega 4 and batt 5 vs lv 4 bs with delta 3 and laser 6 \n BS4:OMG4:BAT5,BS4:DLT3:LAS5");
		
		
		do {
			
			String init = in.next();
			init = init.replaceAll("\\s+","");
			init = init.toUpperCase();
			shipStats = init;
		
			try {
				makeShips();
				@SuppressWarnings("unused")
				Battleship bs1 = new Battleship(bsOneLv, bsOneWeaponType, bsOneWeaponLevel, bsOneShieldType, bsOneShieldLevel);
				@SuppressWarnings("unused")
				Battleship bs2 = new Battleship(bsTwoLv, bsTwoWeaponType, bsTwoWeaponLevel, bsTwoShieldType, bsTwoShieldLevel);
			}
			catch(Exception e) {
				System.out.println("Incorrect input! Try again!");
				incorrectInput = true;
			}
		}while(incorrectInput == true);
		
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
		
		//System.out.println("bs1 stats: " + ((bs1.getHull() * bsOneHealthMultiplier) + (bs1.getShield() * bsOneShieldMultiplier)) + " " + bs1.getWeapon() + " " + bs1.getMaxLaser());
		//System.out.println("bs2 stats: " + ((bs2.getHull() * bsTwoHealthMultiplier) + (bs2.getShield() * bsTwoShieldMultiplier)) +  " " + bs2.getWeapon() + " " + bs2.getMaxLaser());
		
		BattleMath sim = new BattleMath(bs1, bs2);

		sim.doMath();
		
		System.out.println(sim.determineWinner() + " " + sim.determineWinPct() + "% of the time!");
		System.out.println("Side 1 attacks first " + sim.findNumHeads() + "/" + "1000 times!");
		
	}
	
	static void makeShips() {
		
		// example input BS6:O5:L5,BS5:P3:B5
		// second input BS6-95:D4:B10,BS4-50:P10:L7
		
		// Separates the two ships
		String delim1 = "V";
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
		
		if(bs1.contains("@") == false) {
			bsHealth = 100;
		} else if(bs1.contains("@")) {
			bsHP = bs1.substring((bs1.indexOf('@') + 1), bs1.length());
		}
		bsHealth = Integer.parseInt(bsHP) / 100.0;
		
		String shield1 = ship1[1];
		String shieldType = shield1.substring(0,3);
		int shieldLevel = 1;
		int weaponLevel = 1;
		
		if(shield1.length() == 4) {
			String shlv = shield1.substring(3,4);
			shieldLevel = Integer.parseInt(shlv);
		}
		
		if(shield1.length() == 5) {
			String shieldLv = shield1.substring(3,5);
			shieldLevel = Integer.parseInt(shieldLv);
		}
		
		double shieldHealth = 0;
		String shieldHP = "100";
		
		if(shield1.contains("@") == false) {
			shieldHealth = 100;
		} else if(shield1.contains("@")) {
			shieldHP = shield1.substring((shield1.indexOf('@') + 1), shield1.length());
		}
		shieldHealth = Integer.parseInt(shieldHP) / 100.0;
		
		String weapon1 = ship1[2];
		String weaponType = weapon1.substring(0,3);
		
		if(weapon1.length() == 4) {
			String wlv = weapon1.substring(3,4);
			weaponLevel = Integer.parseInt(wlv);
		}
		
		if(weapon1.length() == 5) {
			String weaponLv = weapon1.substring(4,5);
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
		String lv2 = bs2.substring(2,3);
		int bsLevel2 = Integer.parseInt(lv2);
		
		double bs2Health = 0;
		String bs2HP = "100";
		
		if(bs2.contains("@") == false) {
			bs2Health = 100;
		} else if(bs2.contains("@")) {
			bs2HP = bs2.substring((bs2.indexOf('@') + 1), bs2.length());
		}
		bs2Health = Integer.parseInt(bs2HP) / 100.0;
		
		String shield2 = ship2[1];
		String shieldType2 = shield2.substring(0,3);
		int shieldLevel2 = 1;
		int weaponLevel2 = 1;
		
		if(shield2.length() == 4) {
			String shlv2 = shield2.substring(3,4);
			shieldLevel2 = Integer.parseInt(shlv2);
		}
		
		if(shield2.length() == 5) {
			String shieldLv2 = shield2.substring(3,5);
			shieldLevel2 = Integer.parseInt(shieldLv2);
		}
		
		double shield2Health = 0;
		String shield2HP = "100";
		
		if(shield2.contains("@") == false) {
			shield2Health = 100;
		} else if(shield2.contains("@")) {
			shield2HP = shield2.substring((shield2.indexOf('@') + 1), shield2.length());
		}
		shield2Health = Integer.parseInt(shield2HP) / 100.0;
		
		String weapon2 = ship2[2];
		String weaponType2 = weapon2.substring(0,3);
		
		if(weapon2.length() == 4) {
			String wlv2 = weapon2.substring(3,4);
			weaponLevel2 = Integer.parseInt(wlv2);
		}
		
		if(weapon2.length() == 5) {
			String weaponLv2 = weapon2.substring(3,5);
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
