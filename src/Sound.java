import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import java.io.*;

/**
 * This class has no fields and just have methods. It doesn't have constructor too.
 * Methods just play relevant sound and all of the methods are static.
 *
 * @author Alireza Khosrojerdi & Mehrab Safdel
 */
public class Sound {

    /**
     * Play enemy shot sound when enemy shots a bullet
     *
     * @throws IOException
     */
    public static void playEnemyShotSound() throws IOException {
        InputStream in = new FileInputStream("enemyshot.wav");
        AudioStream audioStream = new AudioStream(in);
        AudioPlayer.player.start(audioStream);
    }

    /**
     * Play destroying sound when an enemy destroyed
     *
     * @throws IOException
     */
    public static void playDestroyedSound() throws IOException {
        InputStream in = new FileInputStream("enemydestroyed.wav");
        AudioStream audioStream = new AudioStream(in);
        AudioPlayer.player.start(audioStream);
    }

    /**
     * Play a sound when an enemy hits us
     *
     * @throws IOException
     */
    public static void playEnemyBulletToMyTank() throws IOException {
        InputStream in = new FileInputStream("EnemyBulletToMyTank.wav");
        AudioStream audioStream = new AudioStream(in);
        AudioPlayer.player.start(audioStream);
    }

    /**
     * Play recosh sound for hitting enemy and wall
     *
     * @throws IOException
     */
    public static void playRecosh() throws IOException {
        InputStream in = new FileInputStream("recosh.wav");
        AudioStream audioStream = new AudioStream(in);
        AudioPlayer.player.start(audioStream);
    }

    /**
     * Play cannon sound for shot heavy bullets
     *
     * @throws IOException
     */
    public static void playCannonSound() throws IOException {
        InputStream in = new FileInputStream("cannon.wav");
        AudioStream audioStream = new AudioStream(in);
        AudioPlayer.player.start(audioStream);
    }

    /**
     * Play machine gun sound for shot machine gun bullets
     *
     * @throws IOException
     */
    public static void playMachineGunSound() throws IOException {
        InputStream in = new FileInputStream("machineGun.wav");
        AudioStream audioStream = new AudioStream(in);
        AudioPlayer.player.start(audioStream);
    }

    /**
     * Play cannon sound when player eat the cannon food
     *
     * @throws IOException
     */
    public static void playCannonFoodSound() throws IOException {
        InputStream in = new FileInputStream("repair.wav");
        AudioStream audioStream = new AudioStream(in);
        AudioPlayer.player.start(audioStream);
    }

    /**
     * Play a music when game over
     *
     * @throws IOException
     */
    public static void playEndOfGameSound() throws IOException {
        InputStream in = new FileInputStream("endOfGame.wav");
        AudioStream audioStream = new AudioStream(in);
        AudioPlayer.player.start(audioStream);
    }

    /**
     * This method play lightgun sound for eating machine gun food
     * I don't have the exact sound so i replace it with a appropriate sound called lightgun.wav
     *
     * @throws IOException
     */
    public static void playMachineFoodSound() throws IOException {
        InputStream in = new FileInputStream("repair.wav");
        AudioStream audioStream = new AudioStream(in);
        AudioPlayer.player.start(audioStream);
    }

    /**
     * Play boom sound for exploding mine
     *
     * @throws IOException
     */
    public static void playBoomSound() throws IOException {
        InputStream in = new FileInputStream("mineBoom.wav");
        AudioStream audioStream = new AudioStream(in);
        AudioPlayer.player.start(audioStream);
    }

    /**
     * Play bib bib sound for activating mine
     *
     * @throws IOException
     */
    public static void playBibBibSound() throws IOException {
        InputStream in = new FileInputStream("mine.wav");
        AudioStream audioStream = new AudioStream(in);
        AudioPlayer.player.start(audioStream);
    }

    /**
     * Play repair sound when player eat repair food
     *
     * @throws IOException
     */
    public static void playRepairFoodSound() throws IOException {
        InputStream in = new FileInputStream("repair.wav");
        AudioStream audioStream = new AudioStream(in);
        AudioPlayer.player.start(audioStream);
    }

    /**
     * Play damaging to soft wall sound when bullets hit to them
     *
     * @throws IOException
     */
    public static void playSoftWallSound() throws IOException {
        InputStream in = new FileInputStream("softWall.wav");
        AudioStream audioStream = new AudioStream(in);
        AudioPlayer.player.start(audioStream);
    }

    /**
     * Play selection sound when player select an option in startup
     *
     * @throws IOException
     */
    public static void playSelectSound() throws IOException {
        InputStream in = new FileInputStream("select.wav");
        AudioStream audioStream = new AudioStream(in);
        AudioPlayer.player.start(audioStream);
    }

    /**
     * Play repair.wav for upgrading player's tank
     *
     * @throws IOException
     */
    public static void playUpgradeSound() throws IOException {
        InputStream in = new FileInputStream("repair.wav");
        AudioStream audioStream = new AudioStream(in);
        AudioPlayer.player.start(audioStream);
    }
}
