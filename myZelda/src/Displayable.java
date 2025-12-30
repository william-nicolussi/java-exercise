/*
	This is an interface.
	The classes that implements this interface are Sprite, SolidSprite and DynamicSprite.
*/

package myZelda.src;

import java.awt.*;

public interface Displayable
{
    public void draw (Graphics g);
	public void draw(Graphics g, double offsetX, double offsetY); // New method with offsets
}