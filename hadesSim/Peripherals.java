package hadesSim;

public class Peripherals {
	
	private int shieldStrength;
	private int weaponStrength;
	
	public Peripherals() {
		
	}
	
	public int setShield(char shieldType, int shieldLevel) {
		
		switch(shieldType) {
		
			case 'O':
				shieldType(shieldType, shieldLevel);
				break;
				
			case 'P':
				shieldType(shieldType, shieldLevel);
				break;
		
		}
		
		
		return shieldStrength;
	}
	
	public int setWeapon(char weaponType, int weaponLevel) {
		
		switch(weaponType) {
		
			case 'B':
				weaponType(weaponType, weaponLevel);
			
			case 'L':
				weaponType(weaponType, weaponLevel);
		}
		
		return weaponStrength;
	}
	
	private void shieldType(char shield, int shieldLevel) {
		
		switch(shield) {
		
			case 'O':
				
				int[] omegaStrength = { 0, 8000, 9000, 10000, 11000, 12000, 13000, 14000, 15500, 17000, 18500, 20000, 21500 };
				
				shieldStrength = omegaStrength[shieldLevel];
			break;
			
			case 'P':
				
				int[] passiveStrength = { 0, 5000, 6000, 7000, 8000, 9000, 10000, 11500, 13000, 14500, 16000, 17500, 1900 };
				
				shieldStrength = passiveStrength[shieldLevel];
			break;
		}
		
		
	}
	
	private void weaponType(char weapon, int weaponLevel) {
		
		switch(weapon) {
		
			case 'B':
		
				int[] battStr = { 0, 100, 120, 140, 160, 180, 210, 250, 285, 315, 340, 365, 390 };
			
				weaponStrength = battStr[weaponLevel];
			break;
		
			case 'L':
		
				int[] lasStr = { 0, 80, 200, 90, 240, 100, 280, 120, 325, 140, 360, 160, 400, 180, 
						 500, 200, 600, 225, 700, 250, 800, 275, 900, 300, 1000 };
		
				weaponStrength = lasStr[weaponLevel];
			break;
			}
		}	

}
