package myGame.game;

public class Camera
{
    private double offsetX;
    private double offsetY;
    private final int screenWidth;
    private final int screenHeight;

    public Camera(int screenWidth, int screenHeight)
    {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.offsetX = 0;
        this.offsetY = 0;
    }

    //Update the camera to center on the hero
    public void update(double heroX, double heroY, int worldWidth, int worldHeight)
    {
        //Center the camera on the hero and clamp the camera within the bound of the world
        offsetX = heroX - screenWidth / 2.0;
        offsetY = heroY - screenHeight / 2.0;
        offsetX = Math.max(0, Math.min(offsetX, worldWidth - screenWidth));
        offsetY = Math.max(0, Math.min(offsetY, worldHeight - screenHeight));

        //System.out.println("Camera OffsetX: " + offsetX);
        //System.out.println("Camera OffsetY: " + offsetY);

    }

    public double getOffsetX(){return offsetX;}

    public double getOffsetY(){return offsetY;}
}
