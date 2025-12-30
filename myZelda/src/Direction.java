/*
	The head of the charachter must point in the right direction when moving.
	This is an enum, it defines the directions the image of the charachter must point.
*/

package myZelda.src;

public enum Direction
{
	//normal walk
    NORTH(2),SOUTH(0),EAST(3),WEST(1);
	
	//moonwalk
	//NORTH(0),SOUTH(2),EAST(1),WEST(3);
    private int frameLineNumber;

    Direction(int frameLineNumber)
	{
        this.frameLineNumber = frameLineNumber;
    }

    public int getFrameLineNumber()
	{
        return frameLineNumber;
    }
}
