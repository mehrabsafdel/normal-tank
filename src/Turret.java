import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * this class is a turret class that has the  tow image
 * and the health and the location and can destroyed
 * by our tank
 * and has the array of bullets that attack
 */
public class Turret extends GameObject {

    public Location location;
    public Location mainLoc;
    public BufferedImage image;
    public BufferedImage image1;
    ArrayList<Bullet> bullets;
    public float distantion;
    public double theta;
    int a, b;

    Long time;
    Long time2;
    boolean isEated;
    private int health;
    public boolean isAlive;

    /**
     * @param location is the location of class
     */
    public Turret(Location location) {
        super(location);
        mainLoc = location;
        super.setFull(true);
        super.setCanBulletGo(false);
        time = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis());
        time2 = -time;
        isAlive = true;
        isEated = false;
        this.location = location;
        health = 15;
        bullets = new ArrayList<>();
        try {
            image = ImageIO.read(new File("constant.png"));
            image1 = ImageIO.read(new File("artilleryGun.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * draw the turret
     * @param g2d
     * @param x map x
     * @param y map y
     * @param state game state
     */
    public void draw(Graphics2D g2d, int x, int y, GameState state) {
        a = location.locX - x;
        b = location.locY - y;
        if (isAlive) {
            if (state.locX < mainLoc.locX) {
                theta = Math.atan2((state.locY - b), (state.locX - a));
                distantion = (float) Math.sqrt((state.locX - a) * (state.locX - a) + (state.locY - b) * (state.locY - b));
                //System.out.println(distantion);

                if (distantion < 350) attack(g2d, new Location(location.locX - x, location.locY - y));
            }
            for (Bullet bullet : bullets) {
                bullet.secondDraw(g2d, image1);
            }

            g2d.drawImage(image, a, b, null);
            super.setMainLoc(new Location(a, b));
            mainLoc = new Location(a, b);
            AffineTransform tx20 = g2d.getTransform();
            AffineTransform tx22 = g2d.getTransform();
            tx20.rotate(theta, a + image1.getWidth() / 2, b + image1.getHeight() / 2);
            g2d.setTransform(tx20);
            g2d.drawImage(image1, a + 25, b, null);
            g2d.setTransform(tx22);
        } else {
            try {
                image = ImageIO.read(new File("constant2.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            mainLoc = new Location(a, b);
            g2d.drawImage(image, a, b, null);
        }
    }

    /**
     * this method shows that our tank attacked to this ibjects of this ckass
     * @param g2d
     * @param loc is the location that attack from that
     */
    private void attack(Graphics2D g2d, Location loc) {
        time = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis());
        if (time - time2 > 1) {
            try {
                Sound.playCannonSound();
            } catch (IOException e) {
                e.printStackTrace();
            }
            time2 = time;
            time = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis());
            bullets.add(new Bullet(loc, theta, true));
        }

    }

    /**
     * this method shows that our tank attacked to this objects of this class
     * @param damage is the amount of attacked to objects
     */
    @Override
    public void attacked(int damage) {
        System.out.println(damage);
        super.attacked(damage);
        health -= damage;
        if (health <= 0)
            isAlive = false;
    }
}
