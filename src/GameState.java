import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

/**
 * This class holds the state of game and all of its elements.
 * This class also handles user inputs, which affect the game state.
 *
 * @author Seyed Mohammad Ghaffarian
 */
public class GameState implements Serializable{

    public int locX, locY, prLocX, prLocY, prLocXmap, prLocYmap, diam;
    public int backLocX, backLocY, prBLX, prBLY;
    public boolean gameOver;
    public String cheat = "";


    private boolean keyUP, keyDOWN, keyRIGHT, keyLEFT;
    private boolean mousePress;
    private int mouseX, mouseY;
    private KeyHandler keyHandler;
    private MouseHandler mouseHandler;
    public double thetaTube;
    public OwnTank ownTank;
    public ArrayList<Bullet> myBullets;
    public ArrayList<EnemyTanks> enemies;
    private int i = 0;
    private int limit;
    boolean mapChange;
    boolean change;
    public Map map;
    private Iterator<Bullet> iterator;
    private ArrayList<EnemyTanks> enemiesAttacked;
    private ArrayList<Bullet> turretBullRemoved;
    private Bullet emtyBull;
    Long time;
    Long time2;
    public Upgrader upgrader;
    public Turret turret;
    public RedButtun redButtun;
    public  int counter;

    /**
     * the constructor of class that initialize
     */
    public GameState() {
        counter = 1;
        redButtun = new RedButtun(new Location(1900, 3700));
        turretBullRemoved = new ArrayList<>();
        turret = new Turret(new Location(800, 900));
        upgrader = null;
        time = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis());
        time2 = -time;
        emtyBull = new Bullet(new Location(0, 0), 0, true);
        map = new Map(counter);
        prLocX = 600;
        locX = 600;
        prLocY = 350;
        locY = 350;
        backLocX = locX;
        backLocY = locY;
        diam = 300;
        gameOver = false;
        //
        keyUP = false;
        keyDOWN = false;
        keyRIGHT = false;
        keyLEFT = false;
        //
        mousePress = false;
        mouseX = 0;
        mouseY = 0;
        //
        keyHandler = new KeyHandler();
        mouseHandler = new MouseHandler();
        ownTank = new OwnTank(Startup.getMoodOfGame());
        myBullets = new ArrayList<>();
        enemies = new ArrayList<>();
        enemiesAttacked = new ArrayList<>();
        mapChange = true;
        enemies.add(new EnemyTanks(new Location(1400 - map.getX(), 400 - map.getY()), 1));
        enemies.add(new EnemyTanks(new Location(200 - map.getX(), 1400 - map.getY()), 2));
        change = false;
    }

    /**
     * this class check the movement of our tank
     * @param x map x
     * @param y map y
     * @return if our tank can go there return true
     */
    private boolean check(int x, int y) {
        for (GameObject gameObject : map.gameObjects) {
            if (gameObject.isFull) {
                if (Math.abs(gameObject.mainLoc.locX - locX - x) < 90 && Math.abs(gameObject.mainLoc.locY - locY - y) < 90) {
                    if (gameObject instanceof RepairFood) {
                        ((RepairFood) gameObject).eated();
                        ownTank.reciveAidKit();
                    } else if (gameObject instanceof CanonFood) {
                        ((CanonFood) gameObject).eated();
                        ownTank.reciveFirstBullet();
                    } else if (gameObject instanceof mashinFood) {
                        ((mashinFood) gameObject).eated();
                        ownTank.reciveSecondBullet();
                    } else if (gameObject instanceof Mine) {
                        ((Mine) gameObject).bang();
                        ownTank.attacked(5);
                    }   else if (gameObject instanceof  Shield) {
                        ((Shield) gameObject).eated();
                        ownTank.reciveShield();
                    }
                    return false;
                }
            }
        }

        if (Math.abs(turret.mainLoc.locX - locX - x) < 90 && Math.abs(turret.mainLoc.locY - locY - y) < 90) {
            return false;
        }
        for (EnemyTanks enemyTanks : enemies) {
            if (Math.abs(enemyTanks.mainLoc.locX - locX - x) < 90 && Math.abs(enemyTanks.mainLoc.locY - locY - y) < 90) {
                return false;
            }
        }
        return true;
    }

    /**
     * this class for checking that if our bullets hit the an objects or not
     * @param bullet is our bullet
     * @return if our bullet hit any objects return true
     */
    private boolean bulletHit(Bullet bullet) {
        for (GameObject gameObject : map.gameObjects) {
            if (!gameObject.canBulletGo) {
                if (Math.abs(gameObject.mainLoc.locX - bullet.location.locX) < 75 && Math.abs(gameObject.mainLoc.locY - bullet.location.locY) < 75) {
                    if (gameObject instanceof SoftWall) {
                        ((SoftWall) gameObject).attacked(ownTank.attackingDamage);
                    } else {
                        try {
                            Sound.playRecosh();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    return true;
                }
            }
        }
        if (Math.abs(turret.mainLoc.locX - bullet.location.locX) < 75 && Math.abs(turret.mainLoc.locY - bullet.location.locY) < 75 && turret.isAlive) {
            turret.attacked(ownTank.attackingDamage);
            if (turret.isAlive) {
                try {
                    Sound.playRecosh();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    Sound.playDestroyedSound();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return true;
        }

        if (Math.abs(turret.mainLoc.locX - bullet.location.locX) < 75 && Math.abs(turret.mainLoc.locY - bullet.location.locY) < 75)
            return true;
        if (Math.abs(redButtun.mainLoc.locX - bullet.location.locX) < 75 && Math.abs(redButtun.mainLoc.locY - bullet.location.locY) < 75) {
            redButtun.attacked(ownTank.attackingDamage);
            return true;
        }

        return false;
    }

    /**
     * this method check that if our bullet hit any tank or not
     * @param bullet is out bullet
     * @return if bullet hit any tank return true
     */
    private boolean bulletTankHit(Bullet bullet) {
        for (EnemyTanks gameObject : enemies) {
            if (Math.abs(gameObject.mainLoc.locX - bullet.location.locX) < 100 && Math.abs(gameObject.mainLoc.locY - bullet.location.locY) < 100) {
                gameObject.attacked(ownTank.attackingDamage);
                try {
                    Sound.playRecosh();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (!gameObject.isAlive) {
                    try {
                        Sound.playDestroyedSound();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    enemiesAttacked.add(gameObject);
                }
                return true;
            }
        }
        for (EnemyTanks enemyTanks : enemiesAttacked) {
            if (upgrader == null) {
                upgrader = new Upgrader(enemyTanks.location);
            }
            enemies.remove(enemyTanks);
        }
        return false;
    }
    /**
     * this class for checking that if enemy's bullets hit the an objects or not
     * @param bullet is our bullet
     * @return if enemy's bullet hit any objects return true
     */
    private boolean enemysBullets(Bullet bullet) {
        for (GameObject gameObject : map.gameObjects) {
            if (!gameObject.canBulletGo) {
//                iterator = turret.bullets.iterator();
//                while (iterator.hasNext()){
//                    if (Math.abs(gameObject.mainLoc.locX - iterator.next().location.locX ) < 100  && Math.abs(gameObject.mainLoc.locY - iterator.next().location.locY) < 100) {
//                        iterator.remove();
//                    }
//                    if (Math.abs(locX - iterator.next().location.locX ) < 100  && Math.abs(locY - iterator.next().location.locY) < 100) {
//                            ownTank.attacked(5);
//                            iterator.remove();
//                    }
//                }
                if (Math.abs(gameObject.mainLoc.locX - bullet.location.locX) < 100 && Math.abs(gameObject.mainLoc.locY - bullet.location.locY) < 100) {
                    return true;
                }
            }
        }
        if (Math.abs(locX - bullet.location.locX) < 50 && Math.abs(locY - bullet.location.locY) < 50) {
            ownTank.attacked(bullet.damage);
            try {
                Sound.playEnemyBulletToMyTank();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return true;
        }

        return false;
    }

    /**
     * this method check the location of turret and its bullets place
     * and if its bullet hit any objects show to us
     */
    private void turretCheck() {

        if (upgrader != null) {
            if (Math.abs(upgrader.mainLoc.locX - locX) < 90 && Math.abs(upgrader.mainLoc.locY - locY) < 90 && upgrader.isFull) {
                upgrader.eated();
                ownTank.updateGun();
            }
        }
        for (Bullet bullet : turret.bullets) {
            for (GameObject gameObject : map.gameObjects) {
                if (!gameObject.canBulletGo) {
                    if (Math.abs(gameObject.mainLoc.locX - bullet.location.locX) < 75 && Math.abs(gameObject.mainLoc.locY - bullet.location.locY) < 75) {
                        turretBullRemoved.add(bullet);
                    }
                }
            }
            if (Math.abs(locX - bullet.location.locX) < 75 && Math.abs(locY - bullet.location.locY) < 75) {
                turretBullRemoved.add(bullet);
                ownTank.attacked(5);
                try {
                    Sound.playEnemyBulletToMyTank();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        for (Bullet bullet : turretBullRemoved) {
            turret.bullets.remove(bullet);
        }
    }

    /**
     * this method update the state of game
     */
    public void update() {
        if (cheat.equals("mamad")) {
            ownTank.numberOfFirstBullet += 100;
            cheat = "";
        } else if (cheat.equals("ali")) {
            ownTank.numberOfSecondBullet += 100;
            cheat = "";
        } else if (cheat.equals("hasan")) {
            ownTank.setHealth(25);
            cheat = "";
        }
        turretCheck();
        iterator = myBullets.iterator();
        while (iterator.hasNext()) {
            if (bulletHit(iterator.next())) {
                iterator.remove();
            }
        }
        iterator = myBullets.iterator();
        while (iterator.hasNext()) {
            if (bulletTankHit(iterator.next())) {
                iterator.remove();
            }
        }
        bulletTankHit(emtyBull);

        for (EnemyTanks enemyTanks : enemies) {
            iterator = enemyTanks.bullets.iterator();
            while (iterator.hasNext()) {
                if (enemysBullets(iterator.next())) {
                    iterator.remove();
                }
            }
        }

        iterator = myBullets.iterator();
        while (iterator.hasNext()) {
            if (bulletTankHit(iterator.next())) {
                iterator.remove();
            }
        }

        if (mousePress) {
            if (i < limit) {
                if (ownTank.isFirstDamage && ownTank.numberOfFirstBullet > 0) {
                    myBullets.add(new Bullet(new Location(locX, locY), thetaTube, ownTank.isFirstDamage));
                    ownTank.numberOfFirstBullet--;
                }
                if (!ownTank.isFirstDamage && ownTank.numberOfSecondBullet > 0) {
                    myBullets.add(new Bullet(new Location(locX, locY), thetaTube, ownTank.isFirstDamage));
                    ownTank.numberOfSecondBullet--;
                }
            }
            i++;
        }
        if (keyUP) {//prLocXmap = map.getX();
            if (check(0, -10)) {
                map.setY(map.getY() - 8);
                //mapChange = true;
                prLocX = locX;
                locY -= 8;
                // mapChange = false;
                for (Bullet bullet : myBullets) {
                    bullet.setLocation(new Location(bullet.location.locX, bullet.location.locY + 8));
                }
                for (EnemyTanks enemyTanks : enemies) {
                    for (Bullet bullet : enemyTanks.bullets) {
                        bullet.setLocation(new Location(bullet.location.locX, bullet.location.locY + 8));
                    }
                }
                for (Bullet bullet : turret.bullets) {
                    bullet.setLocation(new Location(bullet.location.locX, bullet.location.locY + 8));
                }
            }
        }
        if (keyDOWN) {//prLocXmap = map.getX();
            if (check(0, 10)) {
                map.setY(map.getY() + 8);
                //mapChange = true;
                prLocX = locX;
                locY += 8;
                // mapChange = false;

                for (Bullet bullet : myBullets) {
                    bullet.setLocation(new Location(bullet.location.locX, bullet.location.locY - 8));
                }
                for (EnemyTanks enemyTanks : enemies) {
                    for (Bullet bullet : enemyTanks.bullets) {
                        bullet.setLocation(new Location(bullet.location.locX, bullet.location.locY - 8));
                    }
                }
                for (Bullet bullet : turret.bullets) {
                    bullet.setLocation(new Location(bullet.location.locX, bullet.location.locY - 8));
                }
            }
        }
        if (keyLEFT) {
            if (check(-10, 0)) {
                //prLocXmap = map.getX();
                map.setX(map.getX() - 8);
                //mapChange = true;
                prLocX = locX;
                locX -= 8;
                // mapChange = false;

                for (Bullet bullet : myBullets) {
                    bullet.setLocation(new Location(bullet.location.locX + 8, bullet.location.locY));
                }
                for (EnemyTanks enemyTanks : enemies) {
                    for (Bullet bullet : enemyTanks.bullets) {
                        bullet.setLocation(new Location(bullet.location.locX + 8, bullet.location.locY));
                    }
                }
                for (Bullet bullet : turret.bullets) {
                    bullet.setLocation(new Location(bullet.location.locX + 8, bullet.location.locY));
                }
            }
        }
        if (keyRIGHT) {
            if (check(10, 0)) {
                //prLocXmap = map.getX();
                map.setX(map.getX() + 8);
                //mapChange = true;
                prLocX = locX;
                locX += 8;
                // mapChange = false;

                for (Bullet bullet : myBullets) {
                    bullet.setLocation(new Location(bullet.location.locX - 8, bullet.location.locY));
                }
                for (EnemyTanks enemyTanks : enemies) {
                    for (Bullet bullet : enemyTanks.bullets) {
                        bullet.setLocation(new Location(bullet.location.locX - 8, bullet.location.locY));
                    }
                }
                for (Bullet bullet : turret.bullets) {
                    bullet.setLocation(new Location(bullet.location.locX - 8, bullet.location.locY));
                }
            }
        }

//            check();
        if (map.getX() > 1100 || map.getY() > 3150)
            diam = 50;
        else
            diam = 200;

        locX = Math.max(locX, 140);
        locX = Math.min(locX, GameFrame.GAME_WIDTH - 2 * diam);
        map.setX(Math.min(map.getX(), 1220));
        map.setX(Math.max(map.getX(), 0));
        //Map.setX(locX );
        locY = Math.max(locY, 140);
        locY = Math.min(locY, GameFrame.GAME_HEIGHT - 2 * diam);
        map.setY(Math.min(map.getY(), 3280));
        map.setY(Math.max(map.getY(), 100));
        //System.out.println(map.getX());
        //Map.setY(locY);
//        Map.setY(Map.getY() + -(mouseY - locY)/ 1000);
//        Map.setX(Map.getX() + -(mouseX - locX) / 1000);
//        locX -= (mouseX / 1000);
//        locY -= (mouseY / 1000);

        setThetaTube();
    }

    /**
     * this method is for get us to the second chapter
     */
    public void nextCHapter(){
        //map = null;
        counter++;
        map.nextChapter();
        redButtun = new RedButtun(new Location(1900, 3700));
        turretBullRemoved = new ArrayList<>();
        turret = new Turret(new Location(1300, 600));
        upgrader = null;
        time = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis());
        time2 = -time;
        emtyBull = new Bullet(new Location(0, 0), 0, true);
        prLocX = 600;
        locX = 600;
        prLocY = 350;
        locY = 350;
        backLocX = locX;
        backLocY = locY;
        diam = 300;
        gameOver = false;
        //
        keyUP = false;
        keyDOWN = false;
        keyRIGHT = false;
        keyLEFT = false;
        //
        mousePress = false;
        mouseX = 0;
        mouseY = 0;
        //
        keyHandler = new KeyHandler();
        mouseHandler = new MouseHandler();
        myBullets = new ArrayList<>();
        enemies = new ArrayList<>();
        enemiesAttacked = new ArrayList<>();
        mapChange = true;
        enemies.add(new EnemyTanks(new Location(1000 - map.getX(), 600 - map.getY()), 1));
        enemies.add(new EnemyTanks(new Location(300 - map.getX(), 1000 - map.getY()), 2));
        change = false;
    }

    /**
     * this method calculate the thata of gun of out tank
     */
    private void setThetaTube() {
        thetaTube = Math.atan2((mouseY - locY - 40 - 6), (mouseX - locX - 35 - 16));
    }

    public KeyListener getKeyListener() {
        return keyHandler;
    }

    public MouseListener getMouseListener() {
        return mouseHandler;
    }

    public MouseMotionListener getMouseMotionListener() {
        return mouseHandler;
    }


    /**
     * The keyboard handler.
     */
    class KeyHandler extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_UP:
                    cheat = "";
                    keyUP = true;
                    change = true;
                    break;
                case KeyEvent.VK_DOWN:
                    cheat = "";
                    keyDOWN = true;
                    change = true;
                    break;
                case KeyEvent.VK_LEFT:
                    cheat = "";
                    keyLEFT = true;
                    change = true;
                    break;
                case KeyEvent.VK_RIGHT:
                    cheat = "";
                    keyRIGHT = true;
                    change = true;
                    break;
                case KeyEvent.VK_ESCAPE:
                    cheat = "";
                    gameOver = true;
                    break;
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_UP:
                    keyUP = false;
                    change = false;
                    break;
                case KeyEvent.VK_DOWN:
                    keyDOWN = false;
                    change = false;
                    break;
                case KeyEvent.VK_LEFT:
                    keyLEFT = false;
                    change = false;
                    break;
                case KeyEvent.VK_RIGHT:
                    keyRIGHT = false;
                    change = false;
                    break;
                default:
                    cheat += e.getKeyChar();
            }
        }


    }

    /**
     * The mouse handler.
     */
    class MouseHandler implements MouseListener, MouseMotionListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getModifiers() == MouseEvent.BUTTON3_MASK)
                ownTank.changeAttack();
        }

        @Override
        public void mousePressed(MouseEvent e) {
            mouseX = e.getX();
            mouseY = e.getY();
            mousePress = true;
            if (ownTank.isFirstDamage)
                limit = 1;
            else
                limit = 5;
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            mousePress = false;
            i = 0;
        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {
            mouseX = e.getX();
            mouseY = e.getY();
            setThetaTube();

        }

        @Override
        public void mouseDragged(MouseEvent e) {
            mouseX = e.getX();
            mouseY = e.getY();
        }

        @Override
        public void mouseMoved(MouseEvent e) {
            mouseX = e.getX();
            mouseY = e.getY();
            setThetaTube();
        }
    }
}

