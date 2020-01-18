package hadesSim;
import java.lang.reflect.Array;
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
	
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		in.useDelimiter("\n");
		System.out.println("  Weapons | Shields");
		System.out.println(" Batt - b | Alpha - a \n Las  - l | Delta - d \n Mass - m | Passive - p \n Dual - d | Omega - o "
							+ "\n Barr - r | Mirror - m \n Dart - t | Blast - b \n          | Area - r");
		System.out.println("ex: lv 4 bs with omega 4 and batt 5 vs lv 4 bs with delta 3 and laser 6 \n 4,O,4,B,5,4,D,3,L,6");
		String init = in.next();
		init = init.replaceAll("\\s+","");
		init = init.toUpperCase();
		shipStats = init;
		
		makeShips();
		
		Battleship bs1 = new Battleship(bsOneLv, bsOneWeaponType, bsOneWeaponLevel, bsOneShieldType, bsOneShieldLevel);
		Battleship bs2 = new Battleship(bsTwoLv, bsTwoWeaponType, bsTwoWeaponLevel, bsTwoShieldType, bsTwoShieldLevel);
		
		Battleship.setWeapon(bs1);
		Battleship.setWeapon(bs2);
		Battleship.setShield(bs1);
		Battleship.setShield(bs2);
		
		BattleMath sim = new BattleMath(bs1, bs2);
		if(bs1.weaponType == 'L') {
			sim.battleMathLaser1();
		}
		//sim.battleMath();
	}
	
	
	static void makeShips() {
		String delims = "[,]";
		String[] a = shipStats.split(delims);
		
		//char[] a = shipStats.toCharArray();
		
		// determining first ships attributes
		String bsLvl = a[0];
		int bsL = Integer.parseInt(bsLvl);
		String shieldType = a[1];
		String shieldLevel = a[2];
		int shieldL = Integer.parseInt(shieldLevel);
		String weaponType = a[3];
		String weaponLevel = a[4];
		int weaponL = Integer.parseInt(weaponLevel);
		
		bsOneLv = bsL;
		bsOneWeaponType = weaponType.charAt(0);
		bsOneWeaponLevel = weaponL;
		bsOneShieldType = shieldType.charAt(0);
		bsOneShieldLevel = shieldL;
		
		
		// determine ship 2 attributes
		String bsLvl2 = a[5];
		int bsL2 = Integer.parseInt(bsLvl2);
		String shieldType2 = a[6];
		String shieldLevel2 = a[7];
		int shieldL2 = Integer.parseInt(shieldLevel2);
		String weaponType2 = a[8];
		String weaponLevel2 = a[9];
		int weaponL2 = Integer.parseInt(weaponLevel2);
		
		bsTwoLv = bsL2;
		bsTwoWeaponType = weaponType2.charAt(0);
		bsTwoWeaponLevel = weaponL2;
		bsTwoShieldType = shieldType2.charAt(0);
		bsTwoShieldLevel = shieldL2;
		
		
	}

	
}

