//import sun.audio.AudioPlayer;
//import sun.audio.AudioStream;

import javax.sound.sampled.*;
import java.io.*;

/**
 * A very simple structure for the main game loop.
 * THIS IS NOT PERFECT, but works for most situations.
 * Note that to make this work, none of the 2 methods
 * in the while loop (update() and render()) should be
 * long running! Both must execute very quickly, without
 * any waiting and blocking!
 * <p>
 * Detailed discussion on different game loop design
 * patterns is available in the following link:
 * http://gameprogrammingpatterns.com/game-loop.html
 *
 * @author Seyed Mohammad Ghaffarian
 */
public class GameLoop implements Runnable {

    /**
     * Frame Per Second.
     * Higher is better, but any value above 24 is fine.
     */
    public static final int FPS = 60;

    private GameFrame canvas;
    private GameState state;

    public GameLoop(GameFrame frame) {
        canvas = frame;
    }

    /**
     * This must be called before the game loop starts.
     */
    public void init() {
        state = new GameState();
        canvas.addKeyListener(state.getKeyListener());
        canvas.addMouseListener(state.getMouseListener());
        canvas.addMouseMotionListener(state.getMouseMotionListener());
    }

    @Override
    public void run() {
        boolean gameOver = false;
        Clip clip = null;
        try {
            clip = AudioSystem.getClip();
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File("gameSound1.wav"));
            clip.open(inputStream);
            clip.loop(99);
        } catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
            e.printStackTrace();
        }
        while (!gameOver && state.ownTank.isAlive ) {
            if (!state.redButtun.isAlive && state.counter == 1) {
                state.nextCHapter();
            }
            if (state.counter == 2 && !state.redButtun.isAlive) break;
            try {
                long start = System.currentTimeMillis();
                //
                state.update();
                canvas.render(state);
                gameOver = state.gameOver;
                //
                long delay = (1000 / FPS) - (System.currentTimeMillis() - start);
                if (delay > 0)
                    Thread.sleep(delay);
            } catch (InterruptedException ex) {
            }
        }
        canvas.render(state);
        clip.stop();
        clip.flush();

        try {
            Sound.playEndOfGameSound();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Startup game_over = new Startup("Game Over");
        game_over.showGameOverFrame();
    }
}
