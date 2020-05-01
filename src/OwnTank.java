import java.io.IOException;

/**
 * this class is for saving our tank info
 * and we can attack and attacked and upgrade
 * our tank by proper method in this class
 *
 * @author Alireza Khosrojerdi & Mehrab Safdel
 */

public class OwnTank extends Attacker {
    public boolean isFirstDamage;
    public int firstDamage;
    public int secondDamage;
    public int attackingDamage;
    public boolean isAlive;
    public int numberOfFirstBullet;
    public int numberOfSecondBullet;
    public boolean isUP;
    public boolean haveShield;

    /**
     * this constructor initialize the fields
     * @param moodOfGame is the mode of game like easy normal or hard
     */
    public OwnTank(int moodOfGame) {
        super(25);
        if (moodOfGame == 1) {
            System.out.println(1);
            super.setHealth(25);
            numberOfFirstBullet = 80;
            numberOfSecondBullet = 300;
        } else if (moodOfGame == 2) {
            System.out.println(2);
            super.setHealth(20);
            numberOfFirstBullet = 60;
            numberOfSecondBullet = 250;
        } else if (moodOfGame == 3) {
            System.out.println(3);
            super.setHealth(15);
            numberOfFirstBullet = 50;
            numberOfSecondBullet = 150;
        }
        firstDamage = 5;
        secondDamage = 1;
        attackingDamage = 5;
        isFirstDamage = true;
        haveShield = false;
        isAlive = true;
    }

    /**
     * by this mehtod you can change the tank gun
     */
    public void changeAttack() {
        if (isFirstDamage) {
            attackingDamage = secondDamage;
            isFirstDamage = false;
        } else {
            attackingDamage = firstDamage;
            isFirstDamage = true;
        }
    }

    /**
     * recive 50 number of first gun bullets
     */
    public void reciveFirstBullet() {
        numberOfFirstBullet += 50;
    }

    /**
     * recive 200 number of seconf gun bullets
     */
    public void reciveSecondBullet() {
        numberOfSecondBullet += 200;
    }

    /**
     * recive shield that never attacked anymore
     */
    public void reciveShield(){
        haveShield = true;
    }

    /**
     * set the health full
     */
    public void reciveAidKit() {
        if (super.getHealth() < 5)
            super.setHealth(10);
        else if (super.getHealth() < 10)
            super.setHealth(15);
        else if (super.getHealth() < 15)
            super.setHealth(20);
        else if (super.getHealth() < 25)
            super.setHealth(25);
    }

    /**
     * update using gun
     * image and the damage of that
     */
    public void updateGun() {
        if (isFirstDamage) {
            firstDamage += 5;
            attackingDamage = firstDamage;
            isUP = true;
        } else {
            secondDamage += 1;
            attackingDamage = secondDamage;
        }

    }

    /**
     * this method attack to the enemies or any objects
     * @param attackedDamage
     */
    public void attacked(int attackedDamage) {
        if (!haveShield) {
            setHealth(getHealth() - attackedDamage);
            if (getHealth() <= 0) isAlive = false;
            System.out.println(attackedDamage);
        }
    }

    /**
     * @return attackingDamage
     */
    public int getAttackingDamage() {
        return attackingDamage;
    }
}