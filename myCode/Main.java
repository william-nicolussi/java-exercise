package myCode;

import myCode.src.*;
import myCode.game.*;

import javax.swing.*;

public class Main {

    public static final int windowWidth = 400;
    public static final int windowHeight = 600;

    private GamePanel gameWindow;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame sharedFrame = new JFrame("myCode");
            sharedFrame.setSize(windowWidth, windowHeight);
            sharedFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            sharedFrame.setLocationRelativeTo(null); // Center the window
            sharedFrame.setVisible(true);

            TitlePanel title = new TitlePanel(sharedFrame);
            sharedFrame.setContentPane(title);
            sharedFrame.revalidate();
            sharedFrame.repaint();

            title.launch();

            // Wait for space to be pressed
            new Thread(() -> {
                while (!title.isStarted()) {
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                SwingUtilities.invokeLater(() -> {
                    try {
                        new Main().launchGame(sharedFrame);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                });
            }).start();
        });
    }

    public void launchGame(JFrame frame) throws Exception {
        gameWindow = new GamePanel(frame); // this sets its own content pane
        frame.setContentPane(gameWindow);
        frame.revalidate();
        frame.repaint();
        frame.requestFocusInWindow();
    }
}
