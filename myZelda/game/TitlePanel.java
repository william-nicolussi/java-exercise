package myZelda.game;

import myZelda.src.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * TitlePanel is the class that handles the title screen.
 */
public class TitlePanel extends JPanel implements KeyListener, Engine {

    private final JFrame parentFrame;
    private volatile boolean isStarted = false;

    private final int width = 576;
    private final int height = 576;

    private BufferedImage linkAsleep;
    private BufferedImage titleImage;

    private int index = 0;
    private long timeBetweenFrame = 5000;
    private final int spriteSheetColumns = 7;
    private long lastFrameTime = System.currentTimeMillis();

    private final int[] spriteX = {266, 842, 1418, 1994, 2570, 3146, 3722};
    private final int sourceY = 256;
    private final int spriteWidth = 42;
    private final int spriteHeight = 53;

    public TitlePanel(JFrame parentFrame) {
        this.parentFrame = parentFrame;

        setPreferredSize(new Dimension(width, height));
        setFocusable(true);
        requestFocusInWindow();
        addKeyListener(this);

        try {
            linkAsleep = ImageIO.read(new File("myZelda/img/LinkAsleep.png"));
            titleImage = ImageIO.read(new File("myZelda/img/Title.png"));
        } catch (IOException e) {
            throw new RuntimeException("Could not load title images", e);
        }

        new Thread(() -> {
            while (!isStarted) {
                repaint();
                Thread.onSpinWait();
            }
        }).start();
    }

    public void launch() {
        parentFrame.setContentPane(this);
        parentFrame.revalidate();
        parentFrame.repaint();
        requestFocusInWindow();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        int panelWidth = getWidth();
        int panelHeight = getHeight();

        try {
            BufferedImage grassTile = ImageIO.read(new File("myZelda/img/grass.png"));
            for (int x = 0; x < panelWidth; x += grassTile.getWidth()) {
                for (int y = 0; y < panelHeight; y += grassTile.getHeight()) {
                    g.drawImage(grassTile, x, y, null);
                }
            }
        } catch (IOException e) {
            g.setColor(Color.GREEN);
            g.fillRect(0, 0, panelWidth, panelHeight);
        }

        int titleW = titleImage.getWidth() / 2;
        int titleH = titleImage.getHeight() / 2;
        int titleX = (panelWidth - titleW) / 2;
        int titleY = (int) (panelHeight * 0.15);
        g.drawImage(titleImage, titleX, titleY, titleW, titleH, null);

        int scale = 1;
        int drawWidth = spriteWidth * scale;
        int drawHeight = spriteHeight * scale;

        int heroX = (panelWidth - drawWidth) / 2;
        int heroY = (int) (panelHeight * 0.45);

        int sx1 = spriteX[index];
        int sx2 = sx1 + spriteWidth;
        int sy1 = sourceY;
        int sy2 = sy1 + spriteHeight;

        g.drawImage(linkAsleep,
                heroX, heroY, heroX + drawWidth, heroY + drawHeight,
                sx1, sy1, sx2, sy2,
                null);

        String message = "PRESS SPACE TO START";
        g.setFont(new Font("Algerian", Font.BOLD, panelWidth / 30));
        g.setColor(Color.WHITE);
        FontMetrics fm = g.getFontMetrics();
        int msgWidth = fm.stringWidth(message);
        int msgX = (panelWidth - msgWidth) / 2;
        int msgY = heroY + drawHeight * 3;
        g.drawString(message, msgX, msgY);

        long now = System.currentTimeMillis();
        if (index == 0 && (now - lastFrameTime) >= timeBetweenFrame) {
            index++;
            timeBetweenFrame = 2000;
            lastFrameTime = now;
        }
        if (index <= 2 && index != 0 && (now - lastFrameTime) >= timeBetweenFrame) {
            index++;
            timeBetweenFrame = 500;
            lastFrameTime = now;
        }
        if (index > 2 && (now - lastFrameTime) >= timeBetweenFrame) {
            index++;
            lastFrameTime = now;
        }
        if (index == spriteSheetColumns) {
            index = 2;
            lastFrameTime = now;
        }
    }

    public boolean isStarted() {
        return isStarted;
    }

    @Override
    public void update() {
        // No update logic yet
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            isStarted = true;
        }
    }

    @Override public void keyReleased(KeyEvent e) {}
    @Override public void keyTyped(KeyEvent e) {}
}
