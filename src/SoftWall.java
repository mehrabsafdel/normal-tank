import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * this class is a soft wall class that has the image
 * and the health and the location and can destroyed
 * by our tank
 */
public class SoftWall extends GameObject {
    public Location location;
    public Location mainLoc;
    public BufferedImage image;
    public int health;

    /**
     * @param location is the location of class
     */
    public SoftWall(Location location) {
        super(location);
        super.setFull(true);
        super.canBulletGo = false;
        health = 16;
        this.location = location;
        try {
            image = ImageIO.read(new File("softWall.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * draw the soft wall
     * @param g2d
     * @param x map x
     * @param y map y
     * @param state game state
     */
    public void draw(Graphics2D g2d, int x, int y, GameState state) {
        if (health < 16 && health > 12) {
            try {
                image = ImageIO.read(new File("softWall.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (health <= 12 && health > 8) {
            try {
                image = ImageIO.read(new File("softWall1.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (health <= 8 && health > 4) {
            try {
                image = ImageIO.read(new File("softWall2.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (health <= 4 && health > 0) {
            try {
                image = ImageIO.read(new File("softWall3.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        g2d.drawImage(image, location.locX - x, location.locY - y, null);
        mainLoc = new Location(location.locX - x, location.locY - y);
        //System.out.println(location.locX/100 - x);
        super.setMainLoc(mainLoc);
    }

    /**
     * this method shows that our tank attacked to it
     * @param damage is the amount of attacked to objects
     */
    public void attacked(int damage) {
        try {
            Sound.playSoftWallSound();
        } catch (IOException e) {
            e.printStackTrace();
        }
        health -= damage;
        if (health <= 0) {
            try {
                image = ImageIO.read(new File("Area.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            super.setFull(false);
            super.canBulletGo = true;
        }

    }
}
