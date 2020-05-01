import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * this class is a box that have some canon in it
 * and own tank can eat it
 *  * @author Alireza Khosrojerdi & Mehrab Safdel

 */
public class CanonFood extends GameObject {

    public Location location;
    public Location mainLoc;
    public BufferedImage image;
    boolean isEated;

    /**
     * the constructor of class
     * @param location is the location of canon food
     */
    public CanonFood(Location location) {
        super(location);
        super.setFull(true);
        super.setCanBulletGo(false);
        isEated = false;
        this.location = location;
        try {
            image = ImageIO.read(new File("canonFood.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * this method draw the canon food
     * @param g2d
     * @param x is the x of the map
     * @param y is the y of the map
     * @param state is the a state of the game
     */
    public void draw(Graphics2D g2d, int x, int y, GameState state) {
        g2d.drawImage(image, location.locX - x, location.locY - y, null);
        mainLoc = new Location(location.locX - x, location.locY - y);
        //System.out.println(location.locX/100 - x);
        super.setMainLoc(mainLoc);
    }

    /**
     * this method shows that own tank eat this class and set some fields
     * to proper amount to own tank can not eat it for some times
     */
    public void eated() {
        try {
            Sound.playCannonFoodSound();
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
