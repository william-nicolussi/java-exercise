package myGame.src;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class Player extends DynamicSprite
{
    private boolean isWalking = false;

    //----- CONSTRUCTOR -----
    public Player(double x, double y, Image image, double width, double height)
	{
        super(x, y, image, width, height);
    }


    //----- Only if move is possible, then execute the method move() -----
    @Override
    public void moveIfPossible(ArrayList<Sprite> environment)
	{
        if (super.isMovingPossible(environment) && isWalking)
		{
            super.move();
        }
    }

    //----- If the key is pressed, this is setted to true, if key is released, it becomes false -----
    public void walks(boolean status)
    {
        this.isWalking = status;
    }

    //----- draw the character moving only if is walking -----
    @Override
    public void draw(Graphics g)
	{
        int index;
        if (isWalking)
        {
            //n. of images to display = (current time) / (time between two frames) % (total n. of frames)
            index= (int) (System.currentTimeMillis() / timeBetweenFrame % spriteSheetNumberOfColumn);
        }
		else
        {
            index=0;
        }
        //g.fillRect((int) x, (int) y, (int) (width), (int) (height)); //--Activate to see the dimension of the sprite--
        g.drawImage(image,(int) x, (int) y, (int) (x+width),(int) (y+height),
                (int) (index*this.width), (int) (direction.getFrameLineNumber()*height),
                (int) ((index+1)*this.width), (int)((direction.getFrameLineNumber()+1)*this.height),null);
    }

    // ----- Draw the character with camera offsets -----
    @Override
    public void draw(Graphics g, double offsetX, double offsetY) {
        int index;
        if (isWalking) {
            // Number of images to display = (current time) / (time between two frames) % (total number of frames)
            index = (int) (System.currentTimeMillis() / timeBetweenFrame % spriteSheetNumberOfColumn);
        } else {
            index = 0;
        }
        g.drawImage(image, (int) (x - offsetX), (int) (y - offsetY), (int) (x + width - offsetX), (int) (y + height - offsetY),
                (int) (index * this.width), (int) (direction.getFrameLineNumber() * height),
                (int) ((index + 1) * this.width), (int) ((direction.getFrameLineNumber() + 1) * this.height), null);
    }
}