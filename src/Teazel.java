import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * this class is a teazel  class that has the image
 * and the health and the location and can not destroyed
 * by our tank
 */
public class Teazel extends GameObject {
    public Location location;
    public Location mainLoc;
    public BufferedImage image;

    /**
     * @param location is the location of class
     */
    public Teazel(Location location) {
        super(location);
        super.setFull(true);
        super.setCanBulletGo(true);
        this.location = location;
        try {
            image = ImageIO.read(new File("teazel2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * draw the teazel
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
