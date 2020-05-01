import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class mashinFood extends GameObject {


    public Location location;
    public Location mainLoc;
    public BufferedImage image;
    boolean isEated;

    public mashinFood(Location location) {
        super(location);
        super.setFull(true);
        super.setCanBulletGo(false);
        isEated = false;
        this.location = location;
        try {
            image = ImageIO.read(new File("mashinFood.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2d, int x, int y, GameState state) {
        g2d.drawImage(image, location.locX - x, location.locY - y, null);
        mainLoc = new Location(location.locX - x, location.locY - y);
        //System.out.println(location.locX/100 - x);
        super.setMainLoc(mainLoc);
    }

    public void eated() {
        try {
            Sound.playMachineFoodSound();
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
    /**
     * This method play lightgun sound for eating machine gun food
     * I don't have the exact sound so i replace it with a appropriate sound called lightgun.wav
     *
     * @throws IOException
     */
    public void playSound() throws IOException {
        InputStream in = new FileInputStream("lightgun.wav");
        AudioStream audioStream = new AudioStream(in);
        AudioPlayer.player.start(audioStream);
    }
}
