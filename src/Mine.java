import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * this class is a mine that attack to the enemies tank
 * and can save the information like location and image
 * of a mine and can draw min by proper method in this class
 * the image of mine change when mine attacked somebody
 */
public class Mine extends GameObject {
    //prper fields
    public Location location;
    public Location mainLoc;
    public BufferedImage image;
    boolean isEated;

    /**
     * this constructor initialize the field
     * and read the image of mine
     *
     * @param location is the location of mine in the map
     */
    public Mine(Location location) {
        super(location);
        super.setFull(true);
        super.setCanBulletGo(true);
        isEated = false;
        this.location = location;
        try {
            image = ImageIO.read(new File("mine.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * this method draw a min
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

    /**
     * this method for explosion of mine
     */
    public void bang() {
        try {
            Sound.playBibBibSound();
        } catch (IOException e) {
            e.printStackTrace();
        }

        super.setFull(false);
        super.setCanBulletGo(true);
        try {
            image = ImageIO.read(new File("destroyed.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            Sound.playBoomSound();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
