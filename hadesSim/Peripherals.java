package hadesSim;

public class Peripherals {
	
	int shieldStrength;
	int weaponStrength;
	int maxLaserStrength;
	double mirrorReflect;
	
	public Peripherals() {
		
	}
	
	public int setShield(char shieldType, int shieldLevel) {

		shieldType(shieldType, shieldLevel);
		
		return shieldStrength;
	}
	
	public void setWeapon(char weaponType, int weaponLevel) {
		
		weaponType(weaponType, weaponLevel);
				
	}
	
	private void shieldType(char shield, int shieldLevel) {
		
		switch(shield) {
		
			case 'O':
				
				int[] omegaStrength = { 0, 8000, 9000, 10000, 11000, 12000, 13000, 
										14000, 15500, 17000, 18500, 20000, 21500 };
				
				shieldStrength = omegaStrength[shieldLevel];
			break;
			
			case 'P':
				
				int[] passiveStrength = { 0, 5000, 6000, 7000, 8000, 9000, 10000, 
										  11500, 13000, 14500, 16000, 17500, 1900 };
				
				shieldStrength = passiveStrength[shieldLevel];
			break;
			
			case 'D':
				
				int[] deltaStrength = { 0, 3500, 3800, 4100, 4400, 4700, 5000, 
										5300, 5600, 5900, 6200, 6500, 6800 };
				
				shieldStrength = deltaStrength[shieldLevel];
			break;
			
			case 'M':
				
				int[] mirrorStrength = { 0, 6500, 7000, 7500, 8000, 8500, 9000, 
										10000, 11500, 13000, 14500, 16000, 18000 };
				
				double[] reflectDamage = { 0, 0.14, 0.16, 0.18, 0.2, 0.22, 0.24, 
										   0.26, 0.28, 0.30, 0.32, 0.34, 0.36 };
				
				shieldStrength = mirrorStrength[shieldLevel];
				mirrorReflect = reflectDamage[shieldLevel];
		}
		
		
	}
	
	private void weaponType(char weapon, int weaponLevel) {
		
		switch(weapon) {
		
			case 'B':
		
				int[] battStr = { 0, 100, 120, 140, 160, 180, 210, 
								  250, 285, 315, 340, 365, 390 };
			
				weaponStrength = battStr[weaponLevel];
			break;
		
			case 'L':
		
				int[] lasStr = { 0, 80, 90, 100, 120, 140, 160, 
								180, 200, 225, 250, 275, 300 };
				
				int[] lasMax = { 0, 200, 240, 280, 325, 360, 400, 
								500, 600, 700, 800, 900, 1000 }; 
		
				weaponStrength = lasStr[weaponLevel];
				maxLaserStrength = lasMax[weaponLevel];
				
			break;
			
			case 'M':
				
				int[] massStr = { 0, 60, 75, 90, 110, 120, 140, 
								  160, 180, 210, 240, 270, 300 };
				
				weaponStrength = massStr[weaponLevel];
				
			}
		}	

}
