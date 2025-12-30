package myCode.src;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import myCode.game.Camera;

public class RenderEngine extends JPanel implements Engine {
    private ArrayList<Displayable> renderList; // list of elements displayed
    private Camera camera;
    private Image imageBackground;

    // ----- CONSTRUCTOR -----
    public RenderEngine(JFrame jFrame) {
        renderList = new ArrayList<>();

        // Load default background image (tree)
        try {
            imageBackground = ImageIO.read(new File("myCode/img/tree.png"));
        } catch (IOException e) {
            e.printStackTrace();
            imageBackground = null;
        }
    }

    public void setCamera(Camera camera) {
        this.camera = camera;
    }

    public void setImageBackground(Image image) {
        this.imageBackground = image;
    }

    // ----- Add to renderList passing an object or an ArrayList -----
    public void addToRenderList(Displayable displayable) {
        if (!renderList.contains(displayable)) {
            renderList.add(displayable);
        }
    }

    public void addToRenderList(ArrayList<Displayable> displayables) {
        for (Displayable d : displayables) {
            if (!renderList.contains(d)) {
                renderList.add(d);
            }
        }
    }

    public void clearRenderList() {
        renderList.clear();
    }

    // ----- Override of a method in JPanel -----
    @Override
    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D g2d = (Graphics2D) g;
        double offsetX = camera != null ? camera.getOffsetX() : 0;
        double offsetY = camera != null ? camera.getOffsetY() : 0;

        // Draw tiled background image that scrolls with camera
        if (imageBackground != null) {
            int tileWidth = imageBackground.getWidth(null);
            int tileHeight = imageBackground.getHeight(null);

            int startX = (int) (-offsetX % tileWidth);
            if (startX > 0) startX -= tileWidth;

            int startY = (int) (-offsetY % tileHeight);
            if (startY > 0) startY -= tileHeight;

            for (int x = startX; x < getWidth(); x += tileWidth) {
                for (int y = startY; y < getHeight(); y += tileHeight) {
                    g2d.drawImage(imageBackground, x, y, null);
                }
            }
        }

        // Draw game objects with camera offset
        for (Displayable renderObject : renderList) {
            if (renderObject instanceof Sprite) {
                ((Sprite) renderObject).draw(g, offsetX, offsetY);
            } else {
                renderObject.draw(g);
            }
        }
    }

    // ----- repaint generates the graphic parameters -----
    @Override
    public void update() {
        this.repaint();
    }
}
