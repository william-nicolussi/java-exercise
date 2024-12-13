/*
	This class models decorative elements with a hitbox, such as trees or rocks.
	The physics engine uses a list of SolidSprite
*/

package myGame.src;
//package src;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class SolidSprite extends Sprite
{
	//----- CONSTRUCTORs -----
    public SolidSprite(double x, double y, Image image, double width, double height)
	{
        super(x, y, image, width, height);
    }
    public SolidSprite(double x, double y, Image image, double width, double height, String strType)
	{
        super(x, y, image, width, height, strType);
    }

	//this method returns the sprite as a rectangle
    public Rectangle2D getHitBox()
	{
        return new Rectangle2D.Double(x,y,(double) width,(double) height);
    }

	//return true if the passed rectange hitBox intersects with this Solidsprite
    public boolean intersect(Rectangle2D.Double hitBox)
	{
        return this.getHitBox().intersects(hitBox);
    }
}