package hadesSim;

public class Weapons {

	static int weaponStr;
	static int lasMax;
	static int wLv;
	
		
		static void selectWeapon(int weaponLevel, char weaponType) {
			
			wLv = weaponLevel;
			
				switch( weaponType ) {
				
					case 'B':
						batt();
					break;
					
					case 'L':
						las();
					break;
			
				}
		}
	
		static void batt() {
			
			int battStr = 0;
			
			if( wLv <= 5) {
				battStr = 100 + ( 20 * ( wLv - 1 ) );
			}
			
			if( wLv == 6) {
				battStr = 210;
			}
			
			if( wLv == 7 || wLv == 8) {
				battStr = 250 + ( 35 * ( wLv - 7 ) );
			}
			
			if( wLv >= 9 ) {
				battStr = 315 + ( 25 * ( wLv - 9 ) );
			}
			
			weaponStr = battStr;
			
		}
		
		static void las() {
			
			int lasStr = 0;
			int maxLasStr = 0;
			
			if( wLv <= 3 ) {
				lasStr = 80 + ( 10 * ( wLv - 1 ) );
				maxLasStr = 200 + ( 40 * ( wLv - 1 ) );
			}
			
			if( wLv > 3 && wLv <= 8 ) {
				lasStr = 120 + ( 20 * ( wLv - 4 ) );
			}
			
			if( wLv == 4 ) {
				maxLasStr = 325;
			}
			
			if( wLv == 5 || wLv == 6) {
				maxLasStr = 360 + ( 40 * ( wLv - 5) );
			}
			
			if( wLv >= 7 ) {
				maxLasStr = 500 + ( 100 * ( wLv - 7 ) );
			}
			
			if( wLv > 8) {
				lasStr = 200 + ( 25 * ( wLv - 8 ) );
			}
			
			weaponStr = lasStr;
			lasMax = maxLasStr;
			
		}
		
}
