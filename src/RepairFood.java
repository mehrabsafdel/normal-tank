import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * this class is a box that has the
 * first aid kit an if our tank eat in
 * its health set to the full
 */
public class RepairFood extends GameObject {
    public Location location;
    public Location mainLoc;
    public BufferedImage image;
    boolean isEated;

    /**
     * @param location is the location of class
     */
    public RepairFood(Location location) {
        super(location);
        super.setFull(true);
        super.setCanBulletGo(false);
        isEated = false;
        this.location = location;
        try {
            image = ImageIO.read(new File("RepairFood.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * draw the repair food
     * @param g2d
     * @param x map x
     * @param y map y
     * @param state game state
     */
    public void draw(Graphics2D g2d, int x, int y, GameState state) {
        g2d.drawImage(image, location.locX - x, location.locY - y, null);
        mainLoc = new Location(location.locX - x, location.locY - y);
        //System.out.println(location.locX/100 - x);
        super.setMainLoc(mainLoc);
    }

    /**
     * this method shows that our tank eat this objects and
     * set the proper changes
     */
    public void eated() {
        try {
            Sound.playRepairFoodSound();
        } catch (IOException e) {
            e.printStackTrace();
        }

        super.setFull(false);
        super.setCanBulletGo(true);
        try {
            image = ImageIO.read(new File("Area.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
