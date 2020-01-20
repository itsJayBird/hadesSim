package hadesSim;

public class Battleship {

	int hullStrength;
	int shieldStrength;
	char weaponType;
	int weaponLvl;
	char shieldType;
	int shieldLevel;
	int weaponStrength;
	int maxLasStr;
	double reflectDamage;


		public Battleship ( int bsLv, char weapon, int weaponLv, char shield, int shieldLv ) {
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
			thisShip.weaponStrength = a.weaponStrength;
			thisShip.maxLasStr = a.maxLaserStrength;
			
		}
		
		public void setShield(Battleship thisShip) {
			
			Peripherals a = new Peripherals();
			thisShip.shieldStrength = a.setShield(thisShip.shieldType, thisShip.shieldLevel);
			
			if(thisShip.shieldType == 'M') {
				thisShip.reflectDamage = a.mirrorReflect;
			}
			
		}

}
