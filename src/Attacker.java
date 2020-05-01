/**
 * this class is the parent of some class
 * that has a field for saving the health amount
 * @author Alireza Khosrojerdi & Mehrab Safdel
 */

public class Attacker {
    private int health;

    /**
     * @param health is the health amount
     */
    public Attacker(int health) {
        this.health = health;
    }

    /**
     * empty constructor
     */
    public Attacker() {

    }

    /**
     * @param health is the health
     */
    public void setHealth(int health) {
        this.health = health;
    }

    /**
     * @return the health amount
     */
    public int getHealth() {
        return health;
    }

    /**
     * this method check the life of an attacker
     * @return
     */
    public boolean isAlive() {
        if (health > 0)
            return true;
        return false;
    }

}
