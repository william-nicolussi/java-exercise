/*
The "PhysicEngine" is tasked with modeling the game’s physics.
For us, this is relatively simple: we only manage constant speed and collisions.
*/
package myZelda.src;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class PhysicEngine implements Engine
{
    private ArrayList<DynamicSprite> movingSpriteList; //contains all the Sprites to be moved
    private ArrayList <Sprite> environment; //contains all the solid sprites

	//----- CONSTRUCTOR -----
    public PhysicEngine()
	{
        movingSpriteList = new ArrayList<>();
        environment = new ArrayList<>();
    }

	//----- Add a Sprite to the environment -----
    public void addToEnvironmentList(Sprite sprite)
	{
        if (!environment.contains(sprite)) //add only if there is not
		{
            environment.add(sprite);
        }
    }

	//----- SETTER -----
    public void setEnvironment(ArrayList<Sprite> environment)
	{
        this.environment=environment;
    }

    public void addToMovingSpriteList(DynamicSprite sprite)
	{
        if (!movingSpriteList.contains(sprite))
		{
            movingSpriteList.add(sprite);
        }
    }

	//moveIfPossible for each dynamic sprite in movingSpriteList
    @Override
    public void update()
	{
        for(DynamicSprite dynamicSprite : movingSpriteList)
		{
            dynamicSprite.moveIfPossible(environment);
        }
    }
}
