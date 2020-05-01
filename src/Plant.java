import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * this class is a plant class that as the
 * needed info of plant object in the game
 * and this class draw after all the
 * objects in the game
 */
public class Plant extends GameObject {
    public Location location;
    public Location mainLoc;
    public BufferedImage image;

    /**
     * @param location is the location of plant
     */
    public Plant(Location location) {
        super(location);
        super.setFull(false);
        super.canBulletGo = true;
        this.location = location;
        try {
            image = ImageIO.read(new File("plant.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * this method for draw a plant
     * @param g2d
     * @param x map x
     * @param y map y
     * @param state game state
     */
    public void draw(Graphics2D g2d, int x, int y, GameState state) {
        g2d.drawImage(image, location.locX - x, location.locY - y, null);
        mainLoc = new Location(location.locX - x, location.locY - y);
        super.setMainLoc(mainLoc);
    }
}
