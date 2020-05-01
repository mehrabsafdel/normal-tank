import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * this class is the floor class
 * that like the raal floor and hase location and image
 */
public class Floor extends GameObject{
        public Location location;
        public Location mainLoc;
        public BufferedImage image;

    /**
     * the constructor of class
     * @param location is the location of floor
     */
    public Floor(Location location){
        super(location);
        super.setFull(false);
        super.canBulletGo = true;
        try {
            image = ImageIO.read(new File("Area.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.location = location;

    }

    /**
     * this method draw the floor
     * @param g2d
     * @param x is the x of map
     * @param y is the y of map
     * @param state is the state of game
     */
    public void draw(Graphics2D g2d,int x,int y,GameState state){
        g2d.drawImage(image,location.locX - x,location.locY - y,null);
        mainLoc = new Location(location.locX - x,location.locY - y);
        super.setMainLoc(mainLoc);
    }
}
