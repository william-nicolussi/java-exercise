package myCode.game;

public class Camera {
    private double offsetX;
    private double offsetY;

    public Camera(int initialWidth, int initialHeight) {
        this.offsetX = 0;
        this.offsetY = 0;
    }

    // Called from GameEngine with live screen size
    public void update(double heroX, double heroY, int worldWidth, int worldHeight, int screenWidth, int screenHeight) {
        offsetX = heroX - screenWidth / 2.0;
        offsetY = heroY - screenHeight / 2.0;

        offsetX = Math.max(0, Math.min(offsetX, worldWidth - screenWidth));
        offsetY = Math.max(0, Math.min(offsetY, worldHeight - screenHeight));
    }

    public double getOffsetX() { return offsetX; }

    public double getOffsetY() { return offsetY; }
}
