/*
	This class models decorative elements which are images.
	This class implements Displayable interface, hence it is needed to have the draw method.
	The render engine uses a list of Sprite
*/

package myCode.src;
//package src;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Sprite implements Displayable
{
    public double x;
    public double y;
    protected final Image image;
    public final double width;
    public final double height;
    String type; //type of the sprite, e.g. "nextLevel"

	//----- CONSTRUCTOR -----
	/*The constructor set the image and the dimension*/
    public Sprite(double x, double y, Image image, double width, double height)
	{
        this.x = x;
        this.y = y;
        this.image = image;
        this.width = width;
        this.height = height;
        this.type = "notImportant";
    }
    public Sprite(double x, double y, Image image, double width, double height, String strType)
	{
        this.x = x;
        this.y = y;
        this.image = image;
        this.width = width;
        this.height = height;
        this.type = strType;
    }

	//----- Draw the Sprite by recalling the interface -----
    @Override
    public void draw(Graphics g)
	{
        g.drawImage(image,(int)x,(int)y,null);
    }
	@Override
	public void draw(Graphics g, double offsetX, double offsetY) {
		g.drawImage(image, (int) (x - offsetX), (int) (y - offsetY), null);
	}

    public String getType(){return type;}

    public boolean isType(String strType)
    {
        if (this.type.equals(strType))
        {
			return true;
		}
        return false;
    }

    //this method returns the sprite as a rectangle
    public Rectangle2D getHitBox()
	{
        return new Rectangle2D.Double(x,y,(double) width,(double) height);
    }

	//----- Set -----
    public void setX(double x){this.x=x;}
    public void setY(double y){this.y=y;}

	//----- Get -----
    public double getX(){return this.x;}
    public double getY(){return this.y;}
    public double getWidth(){return this.width;}
    public double getHeight(){return this.height;}
}
