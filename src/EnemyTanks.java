import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * this class is a tank that enemies tank
 * and can attack and attacked like a real tank
 *
 * @author Alireza Khosrojerdi & Mehrab Safdel
 */


public class EnemyTanks extends GameObject {

    public int damage;
    public int health;
    public BufferedImage image;
    public BufferedImage image1;
    public Location location;
    public boolean isAlive;
    public double theta;
    public float distantion;
    ArrayList<Bullet> bullets;
    Long time;
    Long time2;
    int a, b;

    /**
     * the constructor of class
     * @param location is the location of class
     * @param model is the model of tank
     */
    public EnemyTanks(Location location, int model) {
        super(location);
        super.setFull(true);
        super.canBulletGo = false;
        time = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis());
        time2 = -time;
        this.location = location;
        isAlive = true;
        bullets = new ArrayList<>();
        if (model == 1) {
            damage = 5;
            health = 10;
        } else {
            damage = 1;
            health = 5;
        }
    }

    /**
     * draw the tank
     * @param g2d
     * @param x is the x of map
     * @param y is the y of map
     * @param state is the state of game
     */
    public void draw(Graphics2D g2d, int x, int y, GameState state) {
        a = location.locX - x;
        b = location.locY - y;
        theta = Math.atan2((state.locY - b), (state.locX - a));
        distantion = (float) Math.sqrt((state.locX - a) * (state.locX - a) + (state.locY - b) * (state.locY - b));
        //System.out.println(distantion);
        if (damage == 1) {
            try {
                image = ImageIO.read(new File("BigEnemy.png"));
                image1 = ImageIO.read(new File("BigEnemyGun.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                image = ImageIO.read(new File("SmallEnemyBody.png"));
                image1 = ImageIO.read(new File("SmallEnemyGun.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (distantion < 500 && distantion > 150) {
            if (b < state.locY && check(state, 0, 10)) {
                location.locY += 3;
            } else if (b > state.locY && check(state, 0, -10)) {
                location.locY -= 3;
            }
            if (a < state.locX && check(state, 10, 0)) {
                location.locX += 3;
            } else if (a > state.locX && check(state, -10, 0)) {
                location.locX -= 3;
            }
        }

        if (distantion < 450)
            attack(g2d, new Location(location.locX - x, location.locY - y));

        for (Bullet bullet : bullets) {
            bullet.secondDraw(g2d, image1);
        }

        g2d.drawImage(image, a, b, null);
        super.setMainLoc(new Location(a, b));
        AffineTransform tx20 = g2d.getTransform();
        AffineTransform tx22 = g2d.getTransform();
        tx20.rotate(theta, a + image1.getWidth() / 2, b + image1.getHeight() / 2);
        g2d.setTransform(tx20);
        g2d.drawImage(image1, a + 25, b + 30, null);
        g2d.setTransform(tx22);
    }

    /**
     *this method check the enemies bullets attacking
     * @param state is the state of game
     * @param x is the x of map
     * @param y is the y of map
     * @return if bulet attacking to something return true
     */
    private boolean check(GameState state, int x, int y) {
        for (GameObject gameObject : state.map.gameObjects) {
            if (gameObject.isFull) {
                if (Math.abs(gameObject.mainLoc.locX - mainLoc.locX - x) < 90 && Math.abs(gameObject.mainLoc.locY - mainLoc.locY - y) < 90) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * this method is for attacking by tank
     * @param g2d
     * @param loc is the location of attacking tank
     */
    private void attack(Graphics2D g2d, Location loc) {
        time = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis());
        if (time - time2 > damage) {
            time2 = time;
            time = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis());
            if (damage == 1) {
                try {
                    Sound.playEnemyShotSound();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                bullets.add(new Bullet(loc, theta, false));
            } else {
                try {
                    Sound.playMachineGunSound();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                bullets.add(new Bullet(loc, theta, true));
                try {
                    Sound.playEnemyShotSound();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * this method for attacke to the tank
     * @param damage is the amount that need to decrees from health
     */
    @Override
    public void attacked(int damage) {
        try {
            Sound.playRecosh();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(damage);
        super.attacked(damage);
        health -= damage;
        if (health <= 0)
            isAlive = false;
    }
}
