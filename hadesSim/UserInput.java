package hadesSim;
import java.util.Scanner;
import hadesSim.Battleship;

public class UserInput {
    //vars for setting up final ship stats, ship stats will be input
    //side1, side2 is going to be the final ship stats transfered over to math class
    private String shipStats;
    private Battleship side1;
    private Battleship side2;

    // stats to build the first ship
    // bsHull is going to be final hull value getting passed to the ship 
    // all other vars are to build Battleship( int bsLv, String weapon, int weaponLv, String shield, int shieldLv )
    private int bsOneLv;
    private String bsOneWeaponType;
    private int bsOneWeaponLevel;
    private String bsOneShieldType;
    private int bsOneShieldLevel;
    private double bsOneHealthMultiplier;
    private double bsOneShieldMultiplier;
    private double bsOneHull;

    // vars to build ship 2
    private int bsTwoLv;
    private String bsTwoWeaponType;
    private int bsTwoWeaponLevel;
    private String bsTwoShieldType;
    private int bsTwoShieldLevel;
    private double bsTwoHealthMultiplier;
    private double bsTwoShieldMultiplier;
    private double bsTwoHull;

    //passes if rng is enabled
    private boolean rng;

    @SuppressWarnings("resource")
    public void takeUserInput() {	
        Scanner in = new Scanner(System.in);
        in.useDelimiter("\n");
        String init = in.next();
        //takes input, removes spaces, and sets all to uppercase for parsing
        init = init.replaceAll("\\s+","");
        init = init.toUpperCase();
        shipStats = init;
        if(shipStats.contains("BREAKME")) {
            testCases();
        }
    }

    @SuppressWarnings({ "null", "resource" })
    public void setRNG() {
        Scanner in = new Scanner(System.in);
        System.out.println("Want to randomize who attacks first? Y/N");
        String randomizer = in.next();
        randomizer = randomizer.toUpperCase();

        if(randomizer.contains("Y")==true) {
            rng = true;
        } else if(randomizer.contains("N")==true) {
            rng = false;
        } else {
            rng = (Boolean) null;
        }	
    }

    public void makeShips() {
        // example input BS6:OMG5:LAS5VBS5:PAS3:BAT5
        // second input BS6@95:DLT4:BAT10VBS4@50:PAS10:LAS7
        // Separates the two ships
        String delim1 = "V";
        String[] a = shipStats.split(delim1);
        String s1 = a[0];
        String s2 = a[1];

        // determining first ships attributes 
        // start by separating each attribute
        String delim2 = ":";
        String[] ship1 = s1.split(delim2);
        // first chunk of input ex. BS6 OR BS6@50
        String bs1 = ship1[0];
        //String lv = bs1.substring(2,3);
        //int bsLevel = Integer.parseInt(lv);
        String lv = "";
        double bsHealth = 0;
        // building shield string ex. OMG5 OR OMG10, substring pulls first 3 to assign type
        String shield1 = ship1[1];
        String shieldType = shield1.substring(0,3);
        int shieldLevel = 1;
        int weaponLevel = 1;
        double shieldHealth = 0;
        // building weapon string ex. BAT5 OR BAT10, substring pulls first 3 to assign type
        String weapon1 = ship1[2];
        String weaponType = weapon1.substring(0,3);
        // if input ex. BS6 without a health modifier, use first bit to set hp to 100%
        // if input ex. BS6@50 pulls the numbers after @ symbol to determine %hp
        if(bs1.contains("@") == false) {
            bsHealth = 100;
            lv = bs1.substring(2,bs1.length());
        } else if(bs1.contains("@")) {
            String bsHP = bs1.substring((bs1.indexOf('@') + 1), bs1.length());
            bsHealth = Integer.parseInt(bsHP);
            lv = bs1.substring(2, bs1.indexOf('@'));
            if(bsHealth > 100) bsHealth = 100; // this bit here will default % to 100 if something higher is input
        }
        int bsLevel = Integer.parseInt(lv);
        bsHealth = bsHealth / 100.0;
        // if input ex. OMG4 uses first bit of code to take level
        // if input ex. OMG10 uses second bit to take the level
        if(shield1.length() == 4) {
            String shlv = shield1.substring(3,4);
            shieldLevel = Integer.parseInt(shlv);
        }
        if(shield1.length() == 5) {
            String shieldLv = shield1.substring(3,shield1.length());
            shieldLevel = Integer.parseInt(shieldLv);
        }

        // same as with hp, if no @ assumes 100% hp on shield
        // if @ pulls the number after to set hp multiplier
        if(shield1.contains("@") == false) {
            shieldHealth = 100;
        } else if(shield1.contains("@")) {
            String shieldHP = shield1.substring((shield1.indexOf('@') + 1), shield1.length());
            shieldHealth = Integer.parseInt(shieldHP);
            if(shieldHealth > 100) shieldHealth = 100; // this bit defaults to 100% if inputted higher than that as well
        }
        shieldHealth = shieldHealth / 100.0;

        // if input ex. BAT5 uses first bit to pull the level
        // if input ex. BAT10 uses second bit to pull level
        if(weapon1.length() == 4) {
            String wlv = weapon1.substring(3,4);
            weaponLevel = Integer.parseInt(wlv);
        }
        if(weapon1.length() == 5) {
            String weaponLv = weapon1.substring(3,weapon1.length());
            weaponLevel = Integer.parseInt(weaponLv);
        }	
        // determine ship 2 attributes uses same logic as ship 1 values
        String[] ship2 = s2.split(delim2);
        String bs2 = ship2[0];
        //String lv2 = bs2.substring(2,3);
        //int bsLevel2 = Integer.parseInt(lv2);
        String lv2 = "";
        double bs2Health = 0;
        String shield2 = ship2[1];
        String shieldType2 = shield2.substring(0,3);
        int shieldLevel2 = 1;
        int weaponLevel2 = 1;
        double shield2Health = 0;
        String weapon2 = ship2[2];
        String weaponType2 = weapon2.substring(0,3);

        if(bs2.contains("@") == false) {
            bs2Health = 100;
            lv2 = bs2.substring(2, bs2.length());
        } else if(bs2.contains("@")) {
            String bs2HP = bs2.substring((bs2.indexOf('@') + 1), bs2.length());
            bs2Health = Integer.parseInt(bs2HP);
            lv2 = bs2.substring(2, bs2.indexOf('@'));
            if(bs2Health > 100) bs2Health = 100;
        }
        bs2Health = bs2Health / 100.0;
        int bsLevel2 = Integer.parseInt(lv2);

        if(shield2.length() == 4) {
            String shlv2 = shield2.substring(3,4);
            shieldLevel2 = Integer.parseInt(shlv2);
        }
        if(shield2.length() == 5) {
            String shieldLv2 = shield2.substring(3,shield2.length());
            shieldLevel2 = Integer.parseInt(shieldLv2);
        }

        if(shield2.contains("@") == false) {
            shield2Health = 100;
        } else if(shield2.contains("@")) {
            String shield2HP = shield2.substring((shield2.indexOf('@') + 1), shield2.length());
            shield2Health = Integer.parseInt(shield2HP);
            if(shield2Health > 100) shield2Health = 100;
        }
        shield2Health = shield2Health / 100.0;


        if(weapon2.length() == 4) {
            String wlv2 = weapon2.substring(3,4);
            weaponLevel2 = Integer.parseInt(wlv2);
        }
        if(weapon2.length() == 5) {
            String weaponLv2 = weapon2.substring(3,weapon2.length());
            weaponLevel2 = Integer.parseInt(weaponLv2);
        }

        // setting all the values into memory
        bsOneLv = bsLevel;
        bsOneShieldType = shieldType;
        bsOneShieldLevel = shieldLevel;
        bsOneWeaponType = weaponType;
        bsOneWeaponLevel = weaponLevel;
        bsOneHealthMultiplier = bsHealth;
        bsOneShieldMultiplier = shieldHealth;
        bsTwoLv = bsLevel2;
        bsTwoShieldType = shieldType2;
        bsTwoShieldLevel = shieldLevel2;
        bsTwoWeaponType = weaponType2;
        bsTwoWeaponLevel = weaponLevel2;
        bsTwoHealthMultiplier = bs2Health;
        bsTwoShieldMultiplier = shield2Health;

        // creates ships based on values inputted above
        Battleship firstShip = new Battleship(getSide1Lv(), getSide1WeaponType(), getSide1WeaponLv(), getSide1ShieldType(), getSide1ShieldLv());
        Battleship secondShip = new Battleship(getSide2Lv(), getSide2WeaponType(), getSide2WeaponLv(), getSide2ShieldType(), getSide2ShieldLv());
        // setting the weapon values from peripheral class to pass through the battleship class
        firstShip.setWeapon(firstShip);
        firstShip.setShield(firstShip);
        secondShip.setWeapon(secondShip);
        secondShip.setShield(secondShip);
        // setting the final hull strength
        bsOneHull = (firstShip.getHull() * bsHealth) + (firstShip.getShield() * shieldHealth);
        bsTwoHull = (secondShip.getHull() * bs2Health) + (secondShip.getShield() * shield2Health);
        firstShip.setFinalHull(bsOneHull);
        secondShip.setFinalHull(bsTwoHull);
        // passes full ship info to private var for return
        side1 = firstShip;
        side2 = secondShip;	
    }

    private void testCases() {
        UserInput testCase = new UserInput();
        String[] caseStudies = { "BS4@75:OMG10:BAT8VBS5:DLT11%75:LAS9", 
                                 "BSD:OMG10:BAT8VBS5:DLT11:LAS10" };
        for(int i = 0; i < caseStudies.length; i++) {
            try {
                testCase.shipStats = caseStudies[i];
                testCase.makeShips();
            }
            catch(ArrayIndexOutOfBoundsException e) {
                System.out.println("Case #" + i + " gave error!");
            }   
            catch(NumberFormatException e) {
                System.out.println("Case #" + i + " gave error!");
            }
            catch(NullPointerException e) {
                System.out.println("Case #" + i + " gave error!");
            }
        }
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
    public double getHullStr1() {	
        return bsOneHull;	
    }
    public double getHullStr2() {	
        return bsTwoHull;	
    }
}
