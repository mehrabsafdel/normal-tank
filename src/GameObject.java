import java.awt.*;

/**
 * this class is for saving all the game objects and
 * parent of them and has location like every object in game has
 *
 * @author mehrab and AR.khosrojerdi
 */
public class GameObject {
    Location location;
    Location mainLoc;
    boolean isFull;
    boolean canBulletGo;

    /**
     * @return the main loc of object
     */
    public Location getMainLoc() {
        return mainLoc;
    }

    /**
     * @param mainLoc is the main loc of objects
     */
    public void setMainLoc(Location mainLoc) {
        this.mainLoc = mainLoc;
    }

    /**
     * @param full is the typeof full of object
     */
    public void setFull(boolean full) {
        isFull = full;
    }

    /**
     * @param canBulletGo is the type of can bullet go field
     */
    public void setCanBulletGo(boolean canBulletGo) {
        this.canBulletGo = canBulletGo;
    }

    /**
     * @param location is the location of class
     */
    public GameObject(Location location) {
        this.location = location;
        mainLoc = location;
    }

    /**
     * empty constructor
     */
    public GameObject() {

    }

    /**
     * @return the location of class
     */
    public Location getLocation() {
        return location;
    }

    /**
     * @param location is the location of class
     */
    public void setLocation(Location location) {
        this.location = location;
    }

    /**
     * draw the object
     * @param g2d
     * @param x map x
     * @param y map y
     * @param state game state
     */
    public void draw(Graphics2D g2d, int x, int y, GameState state) {

    }

    /**
     * @param damage is the amount of attacked to objects
     */
    public void attacked(int damage) {

    }
}
