//import sun.audio.AudioPlayer;
//import sun.audio.AudioStream;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

/**
 * @author Alireza Khosrojerdi & Mehrab Safdel
 */
public class Startup {
    private boolean buttonSelected;
    private JFrame firstStartup;
    private JFrame secondStartup;
    private JFrame thirdStartup;
    private JFrame gameOver;
    private static int moodOfGame;
    public static boolean isMapeditor = false;
    public static boolean isNetwork = false;
    public static int[][] map;
    public static int IP_ADDRESS;

    public Startup() {
        firstStartup = new JFrame("Normal Tanks - Menu");
        secondStartup = new JFrame("Normal Tanks - Guide");
        thirdStartup = new JFrame("Normal Tanks - Level Details");
        buttonSelected = false;
        moodOfGame = 1;
        map = new int[25][40];
    }

    public Startup(String string) {
        gameOver = new JFrame("Normal Tanks - " + string);
    }

    public boolean isButtonSelected() {
        return buttonSelected;
    }

    public void showFirstStartup() {
        firstStartup.setLayout(new BorderLayout());
        JPanel mainPanel = new JPanel(new BorderLayout());


        JPanel eastPanel = new JPanel(new BorderLayout());
        JLabel ne = new JLabel(new ImageIcon("Startup NE.png"));
        JLabel e = new JLabel(new ImageIcon("Startup E.png"));
        JLabel se = new JLabel(new ImageIcon("Startup SE.png"));
        eastPanel.add(ne, BorderLayout.NORTH);
        eastPanel.add(e, BorderLayout.CENTER);
        eastPanel.add(se, BorderLayout.SOUTH);


        JPanel centerPanel = new JPanel(new BorderLayout());
        JLabel n = new JLabel(new ImageIcon("Startup N.png"));
        JLabel s = new JLabel(new ImageIcon("Startup S.png"));
        JLabel easyButton = new JLabel(new ImageIcon("Easy.png"));
        JLabel normalButton = new JLabel(new ImageIcon("Normal.png"));
        JLabel hardButton = new JLabel(new ImageIcon("Hard.png"));
        JLabel networkButton = new JLabel(new ImageIcon("Network.png"));
        JLabel mapEditor = new JLabel(new ImageIcon("mapEditor.png"));


        JPanel c = new JPanel(new GridLayout(5, 1));
        c.setBackground(Color.BLACK);
        c.add(easyButton);
        c.add(normalButton);
        c.add(hardButton);
        c.add(networkButton);
        c.add(mapEditor);
        centerPanel.add(n, BorderLayout.NORTH);
        centerPanel.add(c, BorderLayout.CENTER);
        centerPanel.add(s, BorderLayout.SOUTH);


        JPanel westPanel = new JPanel(new BorderLayout());
        JLabel nw = new JLabel(new ImageIcon("Startup NW.png"));
        JLabel w = new JLabel(new ImageIcon("Startup W.png"));
        JLabel sw = new JLabel(new ImageIcon("Startup SW.png"));
        westPanel.add(nw, BorderLayout.NORTH);
        westPanel.add(w, BorderLayout.CENTER);
        westPanel.add(sw, BorderLayout.SOUTH);


        mainPanel.add(westPanel, BorderLayout.WEST);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(eastPanel, BorderLayout.EAST);


        firstStartup.add(mainPanel);
        firstStartup.setResizable(false);
        firstStartup.setSize(1280, 720);
        firstStartup.setLocationRelativeTo(null);
        firstStartup.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        firstStartup.setVisible(true);


        easyButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                buttonSelected = true;
                playSelect();
                moodOfGame = 1;
            }
        });
        normalButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                buttonSelected = true;
                playSelect();
                moodOfGame = 2;
            }
        });
        hardButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                buttonSelected = true;
                playSelect();
                moodOfGame = 3;
            }
        });
        networkButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                buttonSelected = true;
                isNetwork = true;
                System.out.println("choose : 1 server    2 client");
                int serverMood = new Scanner(System.in).nextInt();
                if (serverMood == 1) {
                    Network network = new Network(true);
                }
                else {
                    Network network = new Network(false);
                }
                IP_ADDRESS = new Scanner(System.in).nextInt();
                //playSelect();

            }
        });
        mapEditor.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                buttonSelected = true;
                isMapeditor = true;
                moodOfGame = 1;
                playSelect();
                System.out.println("make your MAP");
                System.out.println("0 for floor");
                System.out.println("1 for hard wall");
                System.out.println("2 for plant");
                System.out.println("3 for teazel");
                System.out.println("5 for soft wall");
                System.out.println("6 for repair food");
                System.out.println("7 for canon food");
                System.out.println("8 for mashin food");
                System.out.println("9 for mine");
                System.out.println("10 for turret");
                System.out.println("11 for red buttun");
                System.out.println("12 for shield");
                System.out.println("make your map :");
                Scanner scanner = new Scanner(System.in);
                for (int i = 0; i < 25; i++) {
                    for (int j = 0; j < 40; j++) {
                        map[i][j] = scanner.nextInt();
                    }
                }
            }
        });
    }

    public void showSecondStartup() {
        secondStartup.setLayout(new BorderLayout());
        ImageIcon imageIcon = new ImageIcon("Startup 2.png");
        Image image = imageIcon.getImage();
        Image newImage = image.getScaledInstance(1280, 720, java.awt.Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newImage);
        JLabel label = new JLabel(imageIcon);

        secondStartup.add(label);
        secondStartup.setResizable(false);
        secondStartup.setSize(1280, 720);
        secondStartup.setLocationRelativeTo(null);
        secondStartup.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        secondStartup.setVisible(true);
    }

    public void showThirdStartup() {
        thirdStartup.setLayout(new BorderLayout());
        ImageIcon imageIcon = new ImageIcon("Stage1.png");
        Image image = imageIcon.getImage();
        Image newImage = image.getScaledInstance(1280, 720, java.awt.Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newImage);
        JLabel label = new JLabel(imageIcon);

        thirdStartup.add(label);
        thirdStartup.setResizable(false);
        thirdStartup.setSize(1280, 720);
        thirdStartup.setLocationRelativeTo(null);
        thirdStartup.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        thirdStartup.setVisible(true);
    }

    public void closeFirstStartup() {
        firstStartup.setVisible(false);
        playAgree();
    }

    public void playAgree() {
        InputStream in = null;
        try {
            in = new FileInputStream("agree.wav");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        AudioStream audioStream = null;
        try {
            audioStream = new AudioStream(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        AudioPlayer.player.start(audioStream);
    }

    public void closeSecondStartup() {
        secondStartup.setVisible(false);
    }

    public void closeThirdStartup() {
        thirdStartup.setVisible(false);
    }

    public void showGameOverFrame() {
        gameOver.setLayout(new BorderLayout());
        ImageIcon imageIcon = new ImageIcon("gameOver.png");
        Image image = imageIcon.getImage();
        Image newImage = image.getScaledInstance(1280, 720, java.awt.Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newImage);
        JLabel label = new JLabel(imageIcon);

        gameOver.add(label);
        gameOver.setResizable(false);
        gameOver.setSize(1280, 720);
        gameOver.setLocationRelativeTo(null);
        gameOver.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        gameOver.setVisible(true);
    }

    public void playSelect() {
        try {
            Sound.playSelectSound();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public static int getMoodOfGame() {
        return moodOfGame;
    }
}
