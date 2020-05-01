import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {
        // Initialize the global thread-pool
        ThreadPool.init();
        Startup startup = new Startup();
        startup.showFirstStartup();
        while (true) {
            if (startup.isButtonSelected()) {
                if (!Startup.isMapeditor || !Startup.isNetwork)
                    startup.showSecondStartup();
                break;
            }
            System.out.print("");
        }
        startup.closeFirstStartup();
        if (!Startup.isMapeditor ) {
            startup.showSecondStartup();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            startup.closeSecondStartup();

            startup.showThirdStartup();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            startup.closeThirdStartup();
        }

        // Show the game menu ...

        // After the player clicks 'PLAY' ...
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                GameFrame frame = new GameFrame("normal tanks");
                frame.setLocationRelativeTo(null); // put frame at center of screen
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
                frame.initBufferStrategy();
                // Create and execute the game-loop
                GameLoop game = new GameLoop(frame);
                game.init();
                ThreadPool.execute(game);
                // and the game starts ...
            }
        });
    }
}
