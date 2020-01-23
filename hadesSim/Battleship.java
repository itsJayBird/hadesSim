package hadesSim;

public class Battleship {
	// hull strength will get replaced with finalHull, this could be shortened potentially
	// replace hullStrength with the finalHull after setting multipliers in the input class
	// these are the vars that other classes will want to know to function
	private int hullStrength;
	private int shieldStrength;
	private String weaponType;
	private int weaponLvl;
	private String shieldType;
	private int shieldLevel;
	private int weaponStrength;
	private int maxLasStr; // value of max laser strength if the ship has laser for math purposes
	private double reflectDamage; // value of the multiplier used from Mirror shield 
	private double finalHull;

	public Battleship ( int bsLv, String weapon, int weaponLv, String shield, int shieldLv ) {
		int[] bsHull = { 0, 4200, 5000, 6000, 7500, 9000, 9500 };
		hullStrength = bsHull[ bsLv ];
		weaponType = weapon;
		weaponLvl = weaponLv;
		shieldType = shield;
		shieldLevel = shieldLv;
	}

	public void setWeapon(Battleship thisShip) {
		Peripherals a = new Peripherals();
		a.setWeapon(thisShip.weaponType, thisShip.weaponLvl);
		thisShip.weaponStrength = a.getWeaponStr();
		thisShip.maxLasStr = a.getMaxLaser();
	}

	public void setShield(Battleship thisShip) {
		Peripherals a = new Peripherals();
		a.setShield(thisShip.shieldType, thisShip.shieldLevel);
		thisShip.shieldStrength = a.getShieldStr();

		if(thisShip.shieldType == "MIR") {
			thisShip.reflectDamage = a.getMirror();
		}
	}

	public void setFinalHull(double hull) {			
		finalHull = hull;
	}

	public double getFinalHull() {			
		return finalHull;			
	}
	public String getWeaponType() {			
		return weaponType;			
	}
	public String getShieldType() {			
		return shieldType;			
	}
	public int getHull() {			
		return hullStrength;			
	}
	public int getShield() {			
		return shieldStrength;			
	}
	public int getWeapon() {			
		return weaponStrength;			
	}
	public int getMaxLaser() {
		return maxLasStr;
	}
	public double getReflectDamage() {
		return reflectDamage;
	}
}