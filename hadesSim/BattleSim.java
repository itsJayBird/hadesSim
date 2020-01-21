package hadesSim;
import java.util.Scanner;

public class BattleSim {

	private String shipStats;
	
	private Battleship side1;
	private Battleship side2;
	
	private int bsOneLv;
	private String bsOneWeaponType;
	private int bsOneWeaponLevel;
	private String bsOneShieldType;
	private int bsOneShieldLevel;
	private double bsOneHealthMultiplier;
	private double bsOneShieldMultiplier;
	
	private int bsTwoLv;
	private String bsTwoWeaponType;
	private int bsTwoWeaponLevel;
	private String bsTwoShieldType;
	private int bsTwoShieldLevel;
	private double bsTwoHealthMultiplier;
	private double bsTwoShieldMultiplier;
	
	private boolean rng;
	
	static BattleSim now;
	
	public BattleSim() {
		
	}
	
	public static void main(String[] args) {
		
		now.takeUserInput();
		now.makeShips();
		now.setRNG();
		
		//System.out.println("bs1 stats: " + ((bs1.getHull() * bsOneHealthMultiplier) + (bs1.getShield() * bsOneShieldMultiplier)) + " " + bs1.getWeapon() + " " + bs1.getMaxLaser());
		//System.out.println("bs2 stats: " + ((bs2.getHull() * bsTwoHealthMultiplier) + (bs2.getShield() * bsTwoShieldMultiplier)) +  " " + bs2.getWeapon() + " " + bs2.getMaxLaser());
		
		BattleMath sim = new BattleMath(now.getSide1(), now.getSide2());

		sim.doMath();
		
		System.out.println(sim.determineWinner() + " " + sim.determineWinPct() + "% of the time!");
		System.out.println("Side 1 attacks first " + sim.findNumHeads() + "/" + "1000 times!");
		
	}
	
	@SuppressWarnings("resource")
	private void takeUserInput() {
		
		Scanner in = new Scanner(System.in);
		in.useDelimiter("\n");
		boolean incorrectInput = false;
		
		System.out.println("  Weapons   |   Shields");
		System.out.println(" Batt - bat | Omega   - omg \n Las  - las | Passive - pas \n Mass - mas | Delta   - dlt \n            | Mirror  - mir");
		System.out.println("ex: lv 4 bs with omega 4 and batt 5 vs lv 4 bs with delta 3 and laser 6 \n BS4:OMG4:BAT5 V BS4:DLT3:LAS5");
		
		
		do {
			
			String init = in.next();
			init = init.replaceAll("\\s+","");
			init = init.toUpperCase();
			now.shipStats = init;
		
			try {
				now.makeShips();
			}
			catch(Exception e) {
				System.out.println("Incorrect input! Try Again!");
				incorrectInput = true;
			}
		}while(incorrectInput == true);
		
	}
	
	@SuppressWarnings("resource")
	private void setRNG() {
		
		Scanner in = new Scanner(System.in);
		
		System.out.println("Want to randomize who attacks first? Y/N");
		String randomizer = in.next();
		randomizer = randomizer.toUpperCase();
		
		if(randomizer.contains("Y")) {
			rng = true;
		} else if(randomizer.contains("N")) {
			rng = false;
		}
		
	}
	
	private void makeShips() {
		
		// example input BS6:OMG5:LAS5VBS5:PAS3:BAT5
		// second input BS6@95:DLT4:BAT10VBS4@50:PAS10:LAS7
		
		// Separates the two ships
		String delim1 = "V";
		String[] a = now.shipStats.split(delim1);
		String s1 = a[0];
		String s2 = a[1];
		
		// determining first ships attributes 
		//start by separating each attribute
		String delim2 = ":";
		String[] ship1 = s1.split(delim2);
		
		// first chunk of input ex. BS6 OR BS6@50
		String bs1 = ship1[0];
		String lv = bs1.substring(2,3);
		int bsLevel = Integer.parseInt(lv);
		double bsHealth = 0;
		String bsHP = "100";
		
		// building shield string ex. OMG5 OR OMG10, substring pulls first 3 to assign type
		String shield1 = ship1[1];
		String shieldType = shield1.substring(0,3);
		int shieldLevel = 1;
		int weaponLevel = 1;
		double shieldHealth = 0;
		String shieldHP = "100";
		
		// building weapon string ex. BAT5 OR BAT10, substring pulls first 3 to assign type
		String weapon1 = ship1[2];
		String weaponType = weapon1.substring(0,3);
		
		// if input ex. BS6 without a health modifier, use first bit to set hp to 100%
		// if input ex. BS6@50 pulls the numbers after @ symbol to determine %hp
		if(bs1.contains("@") == false) {
			bsHealth = 100;
		} else if(bs1.contains("@")) {
			bsHP = bs1.substring((bs1.indexOf('@') + 1), bs1.length());
		}
		bsHealth = Integer.parseInt(bsHP) / 100.0;
		
		// if input ex. OMG4 uses first bit of code to take level
		// if input ex. OMG10 uses second bit to take the level
		if(shield1.length() == 4) {
			String shlv = shield1.substring(3,4);
			shieldLevel = Integer.parseInt(shlv);
		}
		
		if(shield1.length() == 5) {
			String shieldLv = shield1.substring(3,5);
			shieldLevel = Integer.parseInt(shieldLv);
		}
		
		// same as with hp, if no @ assums 100% hp on shield
		// if @ pulls the number after to set hp multiplier
		if(shield1.contains("@") == false) {
			shieldHealth = 100;
		} else if(shield1.contains("@")) {
			shieldHP = shield1.substring((shield1.indexOf('@') + 1), shield1.length());
		}
		shieldHealth = Integer.parseInt(shieldHP) / 100.0;
		
		// if input ex. BAT5 uses first bit to pull the level
		// if input ex. BAT10 uses second bit to pull level
		if(weapon1.length() == 4) {
			String wlv = weapon1.substring(3,4);
			weaponLevel = Integer.parseInt(wlv);
		}
		
		if(weapon1.length() == 5) {
			String weaponLv = weapon1.substring(4,5);
			weaponLevel = Integer.parseInt(weaponLv);
		}	
		
		
		// determine ship 2 attributes uses same logic as ship 1 values
		String[] ship2 = s2.split(delim2);
		
		String bs2 = ship2[0];
		String lv2 = bs2.substring(2,3);
		int bsLevel2 = Integer.parseInt(lv2);
		double bs2Health = 0;
		String bs2HP = "100";
		
		String shield2 = ship2[1];
		String shieldType2 = shield2.substring(0,3);
		int shieldLevel2 = 1;
		int weaponLevel2 = 1;
		double shield2Health = 0;
		String shield2HP = "100";
		
		String weapon2 = ship2[2];
		String weaponType2 = weapon2.substring(0,3);
		
		if(bs2.contains("@") == false) {
			bs2Health = 100;
		} else if(bs2.contains("@")) {
			bs2HP = bs2.substring((bs2.indexOf('@') + 1), bs2.length());
		}
		bs2Health = Integer.parseInt(bs2HP) / 100.0;
		
		if(shield2.length() == 4) {
			String shlv2 = shield2.substring(3,4);
			shieldLevel2 = Integer.parseInt(shlv2);
		}
		
		if(shield2.length() == 5) {
			String shieldLv2 = shield2.substring(3,5);
			shieldLevel2 = Integer.parseInt(shieldLv2);
		}
			
		if(shield2.contains("@") == false) {
			shield2Health = 100;
		} else if(shield2.contains("@")) {
			shield2HP = shield2.substring((shield2.indexOf('@') + 1), shield2.length());
		}
		shield2Health = Integer.parseInt(shield2HP) / 100.0;
		
		
		if(weapon2.length() == 4) {
			String wlv2 = weapon2.substring(3,4);
			weaponLevel2 = Integer.parseInt(wlv2);
		}
		
		if(weapon2.length() == 5) {
			String weaponLv2 = weapon2.substring(3,5);
			weaponLevel2 = Integer.parseInt(weaponLv2);
		}
		
		
		// setting all the values into memory
		now.bsOneLv = bsLevel;
		now.bsOneShieldType = shieldType;
		now.bsOneShieldLevel = shieldLevel;
		now.bsOneWeaponType = weaponType;
		now.bsOneWeaponLevel = weaponLevel;
		now.bsOneHealthMultiplier = bsHealth;
		now.bsOneShieldMultiplier = shieldHealth;
		
		now.bsTwoLv = bsLevel2;
		now.bsTwoShieldType = shieldType2;
		now.bsTwoShieldLevel = shieldLevel2;
		now.bsTwoWeaponType = weaponType2;
		now.bsTwoWeaponLevel = weaponLevel2;
		now.bsTwoHealthMultiplier = bs2Health;
		now.bsTwoShieldMultiplier = shield2Health;
		
		Battleship firstShip = new Battleship(now.getSide1Lv(), now.getSide1WeaponType(), now.getSide1WeaponLv(), now.getSide1ShieldType(), now.getSide1ShieldLv());
		Battleship secondShip = new Battleship(now.getSide2Lv(), now.getSide2WeaponType(), now.getSide2WeaponLv(), now.getSide2ShieldType(), now.getSide2ShieldLv());
	
		firstShip.setWeapon(firstShip);
		firstShip.setShield(firstShip);
		firstShip.setWeapon(firstShip);
		firstShip.setShield(firstShip);
		
		side1 = firstShip;
		side2 = secondShip;
		
	}

	public Battleship getSide1() {
		
		return side1;
		
	}
	
	public Battleship getSide2() {
		
		return side2;
		
	}
	
	public int getSide1Lv() {
		
		return bsOneLv;
		
	}
	
	public int getSide2Lv() {
		
		return bsTwoLv;
		
	}
	
	public int getSide1WeaponLv() {
		
		return bsOneWeaponLevel;
		
	}
	
	public int getSide2WeaponLv() {
		
		return bsTwoWeaponLevel;
		
	}
	
	public int getSide1ShieldLv() {
		
		return bsOneShieldLevel;
		
	}
	
	public int getSide2ShieldLv() {
	
		return bsTwoShieldLevel;
	
	}

	public String getSide1WeaponType() {
		
		return bsOneWeaponType;
		
	}
	
	public String getSide2WeaponType() {
		
		return bsTwoWeaponType;
		
	}
	
	public String getSide1ShieldType() {
		
		return bsOneShieldType;
		
	}
	
	public String getSide2ShieldType() {
		
		return bsTwoShieldType;
		
	}

	public double getSide1HealthMultiplier() {
		
		return bsOneHealthMultiplier;
		
	}
	
	public double getSide1ShieldMultiplier() {
		
		return bsOneShieldMultiplier;
		
	}

	public double getSide2HealthMultiplier() {
		
		return bsTwoHealthMultiplier;
		
	}

	public double getSide2ShieldMultiplier() {
		
		return bsTwoShieldMultiplier;
		
	}

	public boolean getRNG() {
		
		return rng;
		
	}
	
}