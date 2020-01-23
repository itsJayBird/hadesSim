package hadesSim;

public class Peripherals {

    private int shieldStrength;
    private int weaponStrength;
    private int maxLaserStrength;
    private double mirrorReflect;

    public Peripherals() {}

    public void setShield(String shieldType, int shieldLevel) {
        shieldType(shieldType, shieldLevel);
    }

    public void setWeapon(String weaponType, int weaponLevel) {
        weaponType(weaponType, weaponLevel);
    }

    @SuppressWarnings("null")
    private void shieldType(String shield, int shieldLevel) {

        switch(shield) {	
        case "OMG":
            int[] omegaStrength = { 8000, 9000, 10000, 11000, 12000, 13000, 
                    14000, 15500, 17000, 18500, 20000, 21500 };
            shieldStrength = omegaStrength[shieldLevel - 1];
            break;
        case "PAS":
            int[] passiveStrength = { 5000, 6000, 7000, 8000, 9000, 10000, 
                    11500, 13000, 14500, 16000, 17500, 1900 };				
            shieldStrength = passiveStrength[shieldLevel - 1];
            break;
        case "DLT":
            int[] deltaStrength = { 3500, 3800, 4100, 4400, 4700, 5000, 
                    5300, 5600, 5900, 6200, 6500, 6800 };
            shieldStrength = deltaStrength[shieldLevel - 1];
            break;
        case "MIR":
            int[] mirrorStrength = { 6500, 7000, 7500, 8000, 8500, 9000, 
                    10000, 11500, 13000, 14500, 16000, 18000 };
            double[] reflectDamage = { 0.14, 0.16, 0.18, 0.2, 0.22, 0.24, 
                    0.26, 0.28, 0.30, 0.32, 0.34, 0.36 };
            shieldStrength = mirrorStrength[shieldLevel - 1];
            mirrorReflect = reflectDamage[shieldLevel - 1];
            break;
        default:
            shieldStrength = (Integer) null;
            break;
        }
    }

    @SuppressWarnings("null")
    private void weaponType(String weapon, int weaponLevel) {

        switch(weapon) {
        case "BAT":
            int[] battStr = { 100, 120, 140, 160, 180, 210, 
                    250, 285, 315, 340, 365, 390 };			
            weaponStrength = battStr[weaponLevel - 1];
            break;
        case "LAS":
            int[] lasStr = { 80, 90, 100, 120, 140, 160, 
                    180, 200, 225, 250, 275, 300 };
            int[] lasMax = { 200, 240, 280, 325, 360, 400, 
                    500, 600, 700, 800, 900, 1000 }; 
            weaponStrength = lasStr[weaponLevel - 1];
            maxLaserStrength = lasMax[weaponLevel - 1];
            break;
        case "MAS":
            int[] massStr = { 60, 75, 90, 110, 120, 140, 
                    160, 180, 210, 240, 270, 300 };
            weaponStrength = massStr[weaponLevel - 1];
            break;
        default:
            weaponStrength = (Integer)null;
            break;
        }
    }	

    public int getShieldStr() {		
        return shieldStrength;		
    }

    public int getWeaponStr() {		
        return weaponStrength;		
    }

    public int getMaxLaser() {		
        return maxLaserStrength;		
    }

    public double getMirror() {		
        return mirrorReflect;		
    }
}