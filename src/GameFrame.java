import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * The window on which the rendering is performed.
 * This example uses the modern BufferStrategy approach for double-buffering,
 * actually it performs triple-buffering!
 * For more information on BufferStrategy check out:
 * http://docs.oracle.com/javase/tutorial/extra/fullscreen/bufferstrategy.html
 * http://docs.oracle.com/javase/8/docs/api/java/awt/image/BufferStrategy.html
 *
 * @author Seyed Mohammad Ghaffarian
 */
public class GameFrame extends JFrame {

    public static final int GAME_HEIGHT = 720;                  // 720p game resolution
    public static final int GAME_WIDTH = 16 * GAME_HEIGHT / 9;  // wide aspect ratio
    private BufferedImage image;
    //uncomment all /*...*/ in the class for using Tank icon instead of a simple circle
    /*private BufferedImage image;*/
    private int counter = 0;
    private long lastRender;
    private ArrayList<Float> fpsHistory;

    private BufferStrategy bufferStrategy;
    private BufferedImage image1;


    public GameFrame(String title) {
        super(title);
        setResizable(false);
        setSize(GAME_WIDTH, GAME_HEIGHT);
        lastRender = -1;
        fpsHistory = new ArrayList<>(100);

        Image cursorImage = new ImageIcon("aim.png").getImage();
        Cursor cursor = getToolkit().createCustomCursor(cursorImage, new Point(15, 15), "Aim");
        this.setCursor(cursor);

        try {
            image = ImageIO.read(new File("tank.png"));
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    /**
     * This must be called once after the JFrame is shown:
     * frame.setVisible(true);
     * and before any rendering is started.
     */
    public void initBufferStrategy() {
        // Triple-buffering
        createBufferStrategy(3);
        bufferStrategy = getBufferStrategy();
    }


    /**
     * Game rendering with triple-buffering using BufferStrategy.
     */
    public void render(GameState state) {
        Graphics2D graphics;
        // Render single frame
        do {
            // The following loop ensures that the contents of the drawing buffer
            // are consistent in case the underlying surface was recreated
            do {
                // Get a new graphics context every time through the loop
                // to make sure the strategy is validated
                graphics = (Graphics2D) bufferStrategy.getDrawGraphics();
                try {
                    doRendering(graphics, state);
                    for (Bullet bullet : state.myBullets) {
                        bullet.firstDraw(graphics, image1, new Location(state.locX, state.locY));
                    }

                } finally {
                    // Dispose the graphics
                    graphics.dispose();
                }
                // Repeat the rendering if the drawing buffer contents were restored
            } while (bufferStrategy.contentsRestored());

            // Display the buffer
            bufferStrategy.show();
            // Tell the system to do the drawing NOW;
            // otherwise it can take a few extra ms and will feel jerky!
            Toolkit.getDefaultToolkit().sync();

            // Repeat the rendering if the drawing buffer was lost
        } while (bufferStrategy.contentsLost());

    }

    /**
     * draw our tank
     * @param g2d
     * @param state is the state of game
     */
    private void drawOwnTank(Graphics2D g2d, GameState state) {
        if (state.ownTank.isFirstDamage && !state.ownTank.isUP) {
            try {
                image1 = ImageIO.read(new File("tankGun01.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (state.ownTank.isUP && state.ownTank.isFirstDamage) {
            try {
                image1 = ImageIO.read(new File("tankGun1.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                image1 = ImageIO.read(new File("tankGun02.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        g2d.drawImage(image, state.locX, state.locY, null);
        AffineTransform tx20 = g2d.getTransform();
        AffineTransform tx22 = g2d.getTransform();
        tx20.rotate(state.thetaTube, state.locX + image1.getWidth() / 2, state.locY + 18 + image1.getHeight() / 2);
        g2d.setTransform(tx20);
        g2d.drawImage(image1, state.locX + 20, state.locY + 18, null);
        g2d.setTransform(tx22);
    }

    /**
     * Rendering all game elements based on the game state.
     */
    private void doRendering(Graphics2D g2d, GameState state) {
        state.map.drawMap(g2d, state);
        if (state.upgrader != null && state.upgrader.isFull)
            state.upgrader.draw(g2d, state.map.getX(), state.map.getY(), state);
        drawOwnTank(g2d, state);
        drawEnemiesTanks(g2d, state);
        state.map.drawPlantMap(g2d, state);
        state.turret.draw(g2d, state.map.getX(), state.map.getY(), state);
        state.redButtun.draw(g2d, state.map.getX(), state.map.getY(), state);
        drawDetails(g2d, state);
    }

    /**
     * draw the detals of our tank like our health
     * or our number of bllets
     * @param g2d
     * @param state
     */
    public void drawDetails(Graphics2D g2d, GameState state) {
        drawHealth(g2d, state);
        drawHeavyBulletIcon(g2d);
        drawMachineGunBulletIcon(g2d);
        drawNumber(g2d, state);
    }

    /**
     * Draw health of the player's tank
     * @param g2d Our drawing tool
     */
    public void drawHealth(Graphics2D g2d, GameState state) {
        BufferedImage health = null;
        try {
            health = ImageIO.read(new File("health.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (state.ownTank.getHealth() > 20 && state.ownTank.getHealth() <= 25)
            counter = 5;
        else if (state.ownTank.getHealth() > 15 && state.ownTank.getHealth() <= 20)
            counter = 4;
        else if (state.ownTank.getHealth() > 10 && state.ownTank.getHealth() <= 15)
            counter = 3;
        else if (state.ownTank.getHealth() > 5 && state.ownTank.getHealth() <= 10)
            counter = 2;
        else if (state.ownTank.getHealth() > 0 && state.ownTank.getHealth() <= 5)
            counter = 1;
        else if (state.ownTank.getHealth() <= 0)
            counter = 0;
        for (int i = 0; i < counter; i++)
            g2d.drawImage(health, GAME_WIDTH / 2 - 77 + i * (5 + health.getWidth()), 50, null);
    }

    /**
     * draw the canon icon
     * @param g2d our drawing tool
     */
    public void drawHeavyBulletIcon(Graphics2D g2d) {
        BufferedImage numberOfHeavyBullet = null;
        try {
            numberOfHeavyBullet = ImageIO.read(new File("NumberOfHeavyBullet2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        g2d.drawImage(numberOfHeavyBullet, 20, 50, null);
    }

    /**
     * Draw machine gun bullet icon in north west of the frame
     *
     * @param g2d Our drawing tool
     */
    public void drawMachineGunBulletIcon(Graphics2D g2d) {
        BufferedImage numberOfMachineGun = null;
        try {
            numberOfMachineGun = ImageIO.read(new File("NumberOfMachineGun2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        g2d.drawImage(numberOfMachineGun, 20, 110, null);
    }

    /**
     * draw the number of each bullet
     * @param g2d
     * @param state
     */
    public void drawNumber(Graphics2D g2d, GameState state) {
        Number myNumber = new Number();
        int bullet, j = 100;
        bullet = state.ownTank.numberOfFirstBullet;
        try {
            createNumber(g2d, myNumber, bullet, j, 0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        j = 100;
        bullet = state.ownTank.numberOfSecondBullet;
        try {
            createNumber(g2d, myNumber, bullet, j, 60);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * drae the number of each bullet
     * @param g2d
     * @param myNumber number of bullet
     * @param bullet the bullet
     * @param j
     * @param y
     * @throws IOException
     */
    public void createNumber(Graphics2D g2d, Number myNumber, int bullet, int j, int y) throws IOException {
        for (int i = 0; i < 3; i++) {
            int number = bullet / j;
            switch (number) {
                case 0:
                    myNumber.drawZero(g2d, i * 25, y);
                    break;
                case 1:
                    myNumber.drawOne(g2d, i * 25, y);
                    break;
                case 2:
                    myNumber.drawTwo(g2d, i * 25, y);
                    break;
                case 3:
                    myNumber.drawThree(g2d, i * 25, y);
                    break;
                case 4:
                    myNumber.drawFour(g2d, i * 25, y);
                    break;
                case 5:
                    myNumber.drawFive(g2d, i * 25, y);
                    break;
                case 6:
                    myNumber.drawSix(g2d, i * 25, y);
                    break;
                case 7:
                    myNumber.drawSeven(g2d, i * 25, y);
                    break;
                case 8:
                    myNumber.drawEight(g2d, i * 25, y);
                    break;
                case 9:
                    myNumber.drawNine(g2d, i * 25, y);
                    break;
            }

            bullet %= j;
            j /= 10;
            if (j == 0)
                j = 1;
        }
    }

    /**
     * draw the enemies tank
     * @param g2d
     * @param state is the state of game
     */
    private void drawEnemiesTanks(Graphics2D g2d, GameState state) {
        for (EnemyTanks enemyTanks : state.enemies) {
            enemyTanks.draw(g2d, state.map.getX(), state.map.getY(), state);
        }
    }
}
