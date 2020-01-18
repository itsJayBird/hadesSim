package hadesSim;

public class Battleship {

	public int hullStrength;
	public char weaponType;
	public int weaponLvl;
	public char shieldType;
	public int shieldLevel;
	public int weaponStrength;
	public int maxLasStr;


		public Battleship ( int bsLv, char weapon, int weaponLv, char shield, int shieldLv ) {
			int[] bsHull = { 4200, 5000, 6000, 7500, 9000, 9500 };
			hullStrength = bsHull[ bsLv - 1 ];
			weaponType = weapon;
			weaponLvl = weaponLv;
			shieldType = shield;
			shieldLevel = shieldLv;
		}
		
		static void setWeapon(Battleship thisShip) {
			Weapons.selectWeapon(thisShip.weaponLvl, thisShip.weaponType);
			thisShip.weaponStrength = Weapons.weaponStr;
			thisShip.maxLasStr = Weapons.lasMax;
		}
		
		static void setShield(Battleship thisShip) {
			Shield.setShield(thisShip.shieldType, thisShip.shieldLevel);
			thisShip.hullStrength = thisShip.hullStrength + Shield.shieldStrength;
		}

}
