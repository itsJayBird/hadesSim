package hadesSim;

public class Shield {
	
	static int shieldLevel;
	static int shieldStrength;
	
	static void setShield(char shieldType, int shieldLv) {
		
		shieldLevel = shieldLv;
		
		switch ( shieldType ) {
			case 'O':
				omega();
			break;
		}
		
				
	}
	
	static void omega() {
		
		int shieldStr = 0;
		
		if(shieldLevel <= 7) {
			shieldStr = 8000 + ( 1000 * ( shieldLevel - 1) );
		}
		
		if(shieldLevel >= 8) {
			shieldStr = 15500 + ( 1500 * ( shieldLevel - 8 ) ); 
		}
		
		shieldStrength = shieldStr;
		
	}

}
