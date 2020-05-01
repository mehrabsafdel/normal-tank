

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * this class is for a bullet that
 * save the all needed information of a bullet
 * and can attack to the objects in the game
 *
 */
public class Bullet {
    //proper fields
    public BufferedImage image;
    public Location location;
    public int speedX;
    public int speedY;
    public double theta;
    public boolean isFirstDamage;
    public boolean isAlive;
    public int damage;
    public Location firstLoc;

    /**
     * @param location is the first location of bullet that builded
     * @param theta is the thata that bullet must to go in this way
     * @param isFirstDamage is a boolean that show the type if bullet
     */
    public Bullet(Location location, double theta, boolean isFirstDamage) {
        this.location = location;
        firstLoc = location;
        this.theta = theta;
        this.isFirstDamage = isFirstDamage;
        speedX = (int) (20 * Math.cos(theta));
        speedY = (int) (20 * Math.sin(theta));
        if (isFirstDamage)
            damage = 5;
        else
            damage = 1;
        if (isFirstDamage) {
            damage = 5;
            try {
                image = ImageIO.read(new File("Enemy2Bullet.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            damage = 1;
            try {
                image = ImageIO.read(new File("Enemy3Bullet.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @param location is the location of bullet
     */
    public void setLocation(Location location) {
        this.location = location;
    }

    /**
     * this method draw the first type of bullet
     * @param g2d
     * @param image1 is the image of a tank that attack
     */
    public void firstDraw(Graphics2D g2d, BufferedImage image1, Location ownTankLoc) {

        AffineTransform tx20 = g2d.getTransform();
        AffineTransform tx22 = g2d.getTransform();
        tx20.rotate(theta, location.locX + image1.getWidth() / 2, location.locY + 18 + image1.getHeight() / 2);
        g2d.setTransform(tx20);
        if (isFirstDamage)
            g2d.drawImage(image, location.locX + 105, location.locY + 38, null);
        else
            g2d.drawImage(image, location.locX + 105, location.locY + 43, null);
        g2d.setTransform(tx22);
        setLocation(new Location(location.locX + speedX, location.locY + speedY));
    }

    /**
     * this method draw the second type of bullet
     * @param g2d
     * @param image1 is the image of a tank that attack
     */
    public void secondDraw(Graphics2D g2d, BufferedImage image1) {
//            try {
//                image = ImageIO.read(new File("Enemy2Bullet.png"));
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
        AffineTransform tx20 = g2d.getTransform();
        AffineTransform tx22 = g2d.getTransform();
        tx20.rotate(theta, location.locX + image1.getWidth() / 2, location.locY + 18 + image1.getHeight() / 2);
        g2d.setTransform(tx20);
        g2d.drawImage(image, location.locX + 112, location.locY + 80, null);
        g2d.setTransform(tx22);
        setLocation(new Location(location.locX + speedX, location.locY + speedY));
    }
}
