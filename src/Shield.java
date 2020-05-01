import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * this class is the shield class
 * and if our tank eat it no body can attack to it anymore
 * and has image like other foods in the game
 */
public class Shield extends GameObject {

    public Location location;
    public Location mainLoc;
    public BufferedImage image;
    boolean ate;

    /**
     * @param location is the location os class
     */
    public Shield(Location location) {
        super(location);
        super.setFull(true);
        super.setCanBulletGo(false);
        ate = false;
        this.location = location;
        try {
            image = ImageIO.read(new File("shield.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * draw the shield
     * @param g2d
     * @param x map x
     * @param y map y
     * @param state game state
     */
    public void draw(Graphics2D g2d, int x, int y, GameState state) {
        g2d.drawImage(image, location.locX - x, location.locY - y, null);
        mainLoc = new Location(location.locX - x, location.locY - y);
        //System.out.println(location.locX/100 - x);
        super.setMainLoc(mainLoc);
    }

    /**
     * When player tank eat machine food, its image must change to area and bullet must can pass by from that position
     * This method do these tasks and at last it calls playSound to play repair sound
     */
    public void eated() {
        super.setFull(false);
        super.setCanBulletGo(true);
        try {
            image = ImageIO.read(new File("Area.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            playSound();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method play lightgun sound for eating machine gun food
     * I don't have the exact sound so i replace it with a appropriate sound called lightgun.wav
     *
     * @throws IOException
     */
    public void playSound() throws IOException {
//        InputStream in = new FileInputStream("lightgun.wav");
//        AudioStream audioStream = new AudioStream(in);
//        AudioPlayer.player.start(audioStream);
    }
}
