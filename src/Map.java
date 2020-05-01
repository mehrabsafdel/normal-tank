import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * this class is a map that save the locatioan of 1000 objects
 * this class has 2 map and can change the map
 * and if player choos the map editor map set to what player want
 */
public class Map {
    public int[][] things;
    public int[][] things2;
    int chapter;
    private int x = 0;
    private int y = 0;
    public ArrayList<GameObject> gameObjects;

    /**
     * this constructor initialize the fields
     * @param index is an int for check the map editor in on or not
     */
    public Map(int index) {
        gameObjects = new ArrayList<>();
        if (Startup.isMapeditor)
            things = Startup.map;
        else if (index == 1){
            things = new int[][]{{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, {1, 1, 1, 1, 2, 0, 12, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1}, {1, 1, 0, 0, 0, 0, 0, 0, 0, 9, 0, 0, 0, 0, 0, 0, 0, 1, 2, 1, 1, 1, 1, 1, 1}, {1, 1, 0, 0, 0, 0, 0, 0, 5, 5, 3, 5, 3, 1, 0, 0, 0, 1, 2, 2, 1, 1, 1, 1, 1}, {1, 1, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 1, 0, 0, 0, 1, 2, 2, 0, 0, 0, 0, 1}, {1, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 2, 0, 0, 0, 0, 2, 3, 0, 7, 0, 0, 1}, {1, 1, 0, 0, 0, 0, 0, 0, 1, 0, 6, 0, 0, 2, 0, 0, 0, 0, 5, 5, 0, 0, 0, 0, 1}, {1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 2, 3, 0, 0, 0, 0, 1}, {1, 1, 0, 0, 0, 0, 0, 0, 2, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 1, 0, 0, 8, 0, 1}, {1, 1, 0, 0, 0, 0, 0, 0, 2, 1, 1, 2, 1, 1, 1, 1, 1, 1, 2, 2, 2, 1, 1, 1, 1}, {1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 2, 8, 1}, {1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1}, {1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 2, 0, 1}, {1, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 2, 1, 1, 1, 1}, {1, 1, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1}, {1, 1, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1}, {1, 1, 0, 0, 0, 0, 0, 0, 2, 2, 2, 2, 2, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1}, {1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1}, {1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1}, {1, 1, 0, 0, 0, 0, 1, 1, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1}, {1, 1, 0, 0, 0, 0, 2, 1, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1}, {1, 1, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, {1, 1, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, {1, 1, 0, 0, 0, 0, 1, 2, 1, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, {1, 1, 1, 1, 1, 1, 1, 1, 1, 3, 3, 3, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, {1, 1, 1, 1, 1, 1, 1, 2, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1}, {1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1}, {1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1}, {1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1}, {1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1}, {1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 2, 2, 1, 1, 1, 1}, {1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 1}, {1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 1}, {1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, {1, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1}, {1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1}, {1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1}, {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},};
        }
            things2 = new int[][]{
                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                    {1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 1, 1},
                    {2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1},
                    {2, 1, 2, 2, 2, 0, 12, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 2, 1, 1, 1},
                    {2, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 2, 1, 1, 2, 1, 1, 1},
                    {2, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 2, 0, 1, 2, 2, 1, 1, 1, 1, 1},
                    {2, 1, 8, 9, 0, 0, 0, 0, 0, 0, 0, 0, 0, 10, 1, 0, 0, 1, 2, 2, 0, 0, 0, 0, 1},
                    {2, 1, 12, 9, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 1, 0, 0, 0, 0, 0, 0, 8, 0, 0, 1},
                    {2, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {2, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {2, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1},
                    {2, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 1, 0, 0, 1},
                    {2, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 3, 0, 8, 1},
                    {2, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {2, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 3, 2, 0, 1},
                    {2, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 2, 1, 1, 0, 1},
                    {2, 1, 0, 0, 0, 0, 0, 0, 2, 2, 2, 2, 2, 2, 0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 1},
                    {2, 1, 0, 0, 0, 0, 0, 0, 2, 3, 3, 3, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {2, 1, 0, 0, 0, 0, 0, 0, 2, 3, 2, 3, 2, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {2, 1, 0, 0, 0, 0, 0, 0, 2, 3, 3, 3, 2, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {1, 1, 0, 0, 0, 0, 0, 0, 2, 2, 2, 2, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1},
                    {1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 0, 0, 0, 0, 0, 0, 1, 1, 1},
                    {1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 1, 1, 1, 1},
                    {1, 1, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                    {1, 1, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                    {1, 1, 0, 0, 0, 0, 1, 2, 1, 0, 0, 0, 0, 0, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 3, 3, 3, 0, 0, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                    {1, 1, 1, 1, 1, 1, 1, 2, 1, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 2, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 2, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1},
                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 2, 1, 1, 1, 1, 2, 2, 1, 1, 1, 1},
                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 2, 1, 1, 1, 1, 0, 0, 0, 0, 0, 1},
                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 2, 1, 1, 1, 1, 0, 0, 0, 0, 0, 1},
                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                    {1, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1},
                    {1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1},
                    {1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1},
                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            };

        for (int i = 0; i < 25; i++) {
            for (int j = 0; j < 40; j++) {
                switch (things[j][i]) {
                    case 0:
                        gameObjects.add(new Floor(new Location(i * 100 - x, j * 100 - y)));
                        break;
                    case 1:
                        gameObjects.add(new Wall(new Location(i * 100 - x, j * 100 - y)));
                        break;
                    case 2:
                        gameObjects.add(new Plant(new Location(i * 100 - x, j * 100 - y)));
                        break;
                    case 3:
                        gameObjects.add(new Teazel(new Location(i * 100 - x, j * 100 - y)));
                        break;
                    case 5:
                        gameObjects.add(new SoftWall(new Location(i * 100 - x, j * 100 - y)));
                        break;
                    case 6:
                        gameObjects.add(new RepairFood(new Location(i * 100 - x, j * 100 - y)));
                        break;
                    case 7:
                        gameObjects.add(new CanonFood(new Location(i * 100 - x, j * 100 - y)));
                        break;
                    case 8:
                        gameObjects.add(new mashinFood(new Location(i * 100 - x, j * 100 - y)));
                        break;
                    case 9:
                        gameObjects.add(new Mine(new Location(i * 100 - x, j * 100 - y)));
                        break;
                    case 10:
                        gameObjects.add(new Turret(new Location(i * 100 - x, j * 100 - y)));
                        break;
                    case 11:
                        gameObjects.add(new RedButtun(new Location(i * 100 - x, j * 100 - y)));
                        break;
                    case 12:
                        gameObjects.add(new Shield(new Location(i * 100 - x, j * 100 - y)));
                        break;
                }
            }
        }
    }

    /**
     * this method set the game to the chapter tow and set the map to the second type
     */
    public void nextChapter(){
        x = 0;
        y = 0;
        gameObjects.clear();
        gameObjects = new ArrayList<>();
        for (int i = 0; i < 25; i++) {
            for (int j = 0; j < 40; j++) {
                switch (things2[j][i]) {
                    case 0:
                        gameObjects.add(new Floor(new Location(i * 100 - x, j * 100 - y)));
                        break;
                    case 1:
                        gameObjects.add(new Wall(new Location(i * 100 - x, j * 100 - y)));
                        break;
                    case 2:
                        gameObjects.add(new Plant(new Location(i * 100 - x, j * 100 - y)));
                        break;
                    case 3:
                        gameObjects.add(new Teazel(new Location(i * 100 - x, j * 100 - y)));
                        break;
                    case 5:
                        gameObjects.add(new SoftWall(new Location(i * 100 - x, j * 100 - y)));
                        break;
                    case 6:
                        gameObjects.add(new RepairFood(new Location(i * 100 - x, j * 100 - y)));
                        break;
                    case 7:
                        gameObjects.add(new CanonFood(new Location(i * 100 - x, j * 100 - y)));
                        break;
                    case 8:
                        gameObjects.add(new mashinFood(new Location(i * 100 - x, j * 100 - y)));
                        break;
                    case 9:
                        gameObjects.add(new Mine(new Location(i * 100 - x, j * 100 - y)));
                        break;
                    case 10:
                        gameObjects.add(new Turret(new Location(i * 100 - x, j * 100 - y)));
                        break;
                    case 11:
                        gameObjects.add(new RedButtun(new Location(i * 100 - x, j * 100 - y)));
                        break;
                    case 12:
                        gameObjects.add(new Shield(new Location(i * 100 - x, j * 100 - y)));
                        break;
                }
            }
        }
    }

    /**
     * this class draw the objects of maps
     * @param g2d is the graphycs that draw
     * @param state is the state of game
     */
    public void drawMap(Graphics2D g2d, GameState state) {
        for (GameObject gameObject : gameObjects) {
            if (!(gameObject instanceof Plant)) {
                if (gameObject.location.locX - x < 1650 && gameObject.location.locY - y < 1500) {
                    gameObject.draw(g2d, x, y, state);
                }
            }
        }

    }

    /**
     * this method just draw the plant of map
     * @param g2d is the graphycs that draw
     * @param state is the state of game
     */
    public void drawPlantMap(Graphics2D g2d, GameState state) {
        for (GameObject gameObject : gameObjects) {
            if ((gameObject instanceof Plant)) {
                if (gameObject.location.locX - x < 1650 && gameObject.location.locY - y < 1500) {
                    gameObject.draw(g2d, x, y, state);
                }
            }
        }

    }

    /**
     * @param x is the x of map
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * @param y is the y of map
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * @return the x amount of map
     */
    public int getX() {
        return x;
    }

    /**
     * @return the y amount of map
     */
    public int getY() {
        return y;
    }
}
