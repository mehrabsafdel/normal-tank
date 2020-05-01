import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * this class is for saving an object that show
 * the end of the each chapter
 * and can attacked but can not attacking
 * to the other objects
 */
public class RedButtun extends GameObject {

    public Location location;
    public Location mainLoc;
    public BufferedImage image;
    int a, b;
    public int health;
    public boolean isAlive;

    /**
     * @param location is the location of class
     */
    public RedButtun(Location location) {
        super(location);
        mainLoc = location;
        super.setFull(true);
        super.setCanBulletGo(false);
        isAlive = true;
        this.location = location;
        health = 15;
        try {
            image = ImageIO.read(new File("redButtun.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * draw the re buttun
     * @param g2d
     * @param x map x
     * @param y map y
     * @param state game state
     */
    public void draw(Graphics2D g2d, int x, int y, GameState state) {
        a = location.locX - x;
        b = location.locY - y;
        if (isAlive) {
            g2d.drawImage(image, a, b, null);
            super.setMainLoc(new Location(a, b));
            mainLoc = new Location(a, b);

        } else {
            try {
                image = ImageIO.read(new File("redButtunDead.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            mainLoc = new Location(a, b);
            g2d.drawImage(image, a, b, null);
        }
    }

    /**
     * attacked to this object
     * @param damage is the amount of attacked to objects
     */
    @Override
    public void attacked(int damage) {
        try {
            Sound.playRecosh();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // System.out.println("asnfkjsdbfjsdnbfj");
        super.attacked(damage);
        health -= damage;
        if (health <= 0)
            isAlive = false;
    }
}
