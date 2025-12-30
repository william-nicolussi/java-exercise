/*
	This class models animated elements (which is only the hero)
	The game engine has a reference to the hero
*/

package myZelda.src;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class DynamicSprite extends SolidSprite
{
    protected Direction direction = Direction.EAST;
    protected double speed = 5;
    protected double timeBetweenFrame = 200; //200ms between two frames
    protected final int spriteSheetNumberOfColumn = 10;

	//----- CONSTRUCTOR -----
    public DynamicSprite(double x, double y, Image image, double width, double height)
	{
        super(x, y, image, width, height);
    }

	//----- Return true if in the (hero.position + speed) there is not a solidsprite -----
    protected boolean isMovingPossible(ArrayList<Sprite> environment)
	{
        Rectangle2D.Double moved = new Rectangle2D.Double();
        switch(direction)
		{
            case EAST: moved.setRect(super.getHitBox().getX()+speed,super.getHitBox().getY(),
                                    super.getHitBox().getWidth(), super.getHitBox().getHeight());
                break;
            case WEST:  moved.setRect(super.getHitBox().getX()-speed,super.getHitBox().getY(),
                    super.getHitBox().getWidth(), super.getHitBox().getHeight());
                break;
            case NORTH:  moved.setRect(super.getHitBox().getX(),super.getHitBox().getY()-speed,
                    super.getHitBox().getWidth(), super.getHitBox().getHeight());
                break;
            case SOUTH:  moved.setRect(super.getHitBox().getX(),super.getHitBox().getY()+speed,
                    super.getHitBox().getWidth(), super.getHitBox().getHeight());
                break;
        }

        for (Sprite s : environment)
		{
            if ((s instanceof SolidSprite) && (s!=this))
			{
                if (((SolidSprite) s).intersect(moved))
				{
                    return false;
                }
            }
        }
        return true;
    }

	//----- setter for attribute direction -----
    public void setDirection(Direction direction)
	{
        this.direction = direction;
    }

	//----- move by adding the speed to the current position -----
	//This methos is protected because it is executed only if the movement is possible
    protected void move()
	{
        switch (direction)
		{
            case NORTH:
                this.y-=speed;
				break;
            case SOUTH:
                this.y+=speed;
				break;
            case EAST:
                this.x+=speed;
				break;
            case WEST:
                this.x-=speed;
				break;
        }
    }

	//----- Only if move is possible, then execute the method move() -----
    public void moveIfPossible(ArrayList<Sprite> environment)
	{
        if (isMovingPossible(environment))
		{
            move();
        }
    }

    @Override
    public void draw(Graphics g)
	{
		//n. of images to display = (current time) / (time between two frames) % (total n. of frames)
        int index= (int) (System.currentTimeMillis() / timeBetweenFrame % spriteSheetNumberOfColumn);

        g.drawImage(image,(int) x, (int) y, (int) (x+width),(int) (y+height),
                (int) (index*this.width), (int) (direction.getFrameLineNumber()*height),
                (int) ((index+1)*this.width), (int)((direction.getFrameLineNumber()+1)*this.height),null);
    }

    //----- SET & GET Speed -----
    public void setSpeed(double speed)
    {
        this.timeBetweenFrame = timeBetweenFrame * this.speed / speed;
        this.speed = speed;
        System.out.println("Speed change");
    }
    protected double getSpeed(){return this.speed;}
}
