import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * For drawing numbers we use these methods of this class
 * For 0 to 9 we have separate methods that called in type drawNumber
 *
 * @author Alireza Khosrojerdi & Mehrab safdel
 */
public class Number {
    /**
     * Draw number one
     *
     * @param g2d Our drawing tool
     * @param x   x coordinate
     * @param y   y coordinate
     * @throws IOException
     */
    public static void drawOne(Graphics2D g2d, int x, int y) throws IOException {
        BufferedImage one = ImageIO.read(new File("1.png"));
        drawNumber(g2d, one, x, y);
    }

    /**
     * Draw number two
     *
     * @param g2d Our drawing tool
     * @param x   x coordinate
     * @param y   y coordinate
     * @throws IOException
     */
    public static void drawTwo(Graphics2D g2d, int x, int y) throws IOException {
        BufferedImage two = ImageIO.read(new File("2.png"));
        drawNumber(g2d, two, x, y);
    }

    /**
     * Draw number three
     *
     * @param g2d Our drawing tool
     * @param x   x coordinate
     * @param y   y coordinate
     * @throws IOException
     */
    public static void drawThree(Graphics2D g2d, int x, int y) throws IOException {
        BufferedImage three = ImageIO.read(new File("3.png"));
        drawNumber(g2d, three, x, y);
    }

    /**
     * Draw number four
     *
     * @param g2d Our drawing tool
     * @param x   x coordinate
     * @param y   y coordinate
     * @throws IOException
     */
    public static void drawFour(Graphics2D g2d, int x, int y) throws IOException {
        BufferedImage four = ImageIO.read(new File("4.png"));
        drawNumber(g2d, four, x, y);
    }

    /**
     * Draw number five
     *
     * @param g2d Our drawing tool
     * @param x   x coordinate
     * @param y   y coordinate
     * @throws IOException
     */
    public static void drawFive(Graphics2D g2d, int x, int y) throws IOException {
        BufferedImage five = ImageIO.read(new File("5.png"));
        drawNumber(g2d, five, x, y);
    }

    /**
     * Draw number six
     *
     * @param g2d Our drawing tool
     * @param x   x coordinate
     * @param y   y coordinate
     * @throws IOException
     */
    public static void drawSix(Graphics2D g2d, int x, int y) throws IOException {
        BufferedImage six = ImageIO.read(new File("6.png"));
        drawNumber(g2d, six, x, y);
    }

    /**
     * Draw number seven
     *
     * @param g2d Our drawing tool
     * @param x   x coordinate
     * @param y   y coordinate
     * @throws IOException
     */
    public static void drawSeven(Graphics2D g2d, int x, int y) throws IOException {
        BufferedImage seven = ImageIO.read(new File("7.png"));
        drawNumber(g2d, seven, x, y);
    }

    /**
     * Draw number eight
     *
     * @param g2d Our drawing tool
     * @param x   x coordinate
     * @param y   y coordinate
     * @throws IOException
     */
    public static void drawEight(Graphics2D g2d, int x, int y) throws IOException {
        BufferedImage eight = ImageIO.read(new File("8.png"));
        drawNumber(g2d, eight, x, y);
    }

    /**
     * Draw number nine
     *
     * @param g2d Our drawing tool
     * @param x   x coordinate
     * @param y   y coordinate
     * @throws IOException
     */
    public static void drawNine(Graphics2D g2d, int x, int y) throws IOException {
        BufferedImage nine = ImageIO.read(new File("9.png"));
        drawNumber(g2d, nine, x, y);
    }

    /**
     * Draw number zero
     *
     * @param g2d Our drawing tool
     * @param x   x coordinate
     * @param y   y coordinate
     * @throws IOException
     */
    public static void drawZero(Graphics2D g2d, int x, int y) throws IOException {
        BufferedImage zero = ImageIO.read(new File("0.png"));
        drawNumber(g2d, zero, x, y);
    }

    /**
     * Draw number with our drawing tool and two coordinates and one image that declare the right image
     *
     * @param g2d    Our drawing tool
     * @param number image of number that we want to draw
     * @param x      x coordinate
     * @param y      y coordinate
     */
    public static void drawNumber(Graphics2D g2d, BufferedImage number, int x, int y) {
        g2d.drawImage(number, 80 + x, 60 + y, null);
    }
}
