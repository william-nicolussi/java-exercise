/*
The 'GameEngine' handles user interactions (using the keyboard). In a more ambitious project,
it would also load levels, manage game overs, etc.
*/

package myZelda.src;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.awt.geom.Rectangle2D;
import myZelda.game.LevelManager;
import myZelda.game.Camera;

public class GameEngine implements Engine, KeyListener
{
    public boolean upPressed=false, downPressed=false, leftPressed=false, rightPressed=false, controlPressed=false;
    Player hero;
    private ArrayList<Sprite> environment;
    private RenderEngine renderEngine;
    private PhysicEngine physicEngine;
    private LevelManager levelManager;
    Camera camera;

	//----- CONSTRUCTOR -----
	//Set the reference to the hero
    public GameEngine(Player hero, RenderEngine renderEngine, PhysicEngine physicEngine)
    {
        camera = new Camera(400, 600);
        this.hero = hero;
        this.renderEngine = renderEngine;
        this.renderEngine.setCamera(camera);
        this.physicEngine = physicEngine;
        this.levelManager = new LevelManager();
    }

    public void setEnvironment(ArrayList<Sprite> environment)
    {
        this.environment = environment;
    }

    @Override
    public void update()
    {
        // Update hero and game logic
        checkLevelCompletion();
        camera.update(hero.getX(), hero.getY(), levelManager.getWidth(), levelManager.getHeight(), renderEngine.getWidth(), renderEngine.getHeight());
    }

    private void checkLevelCompletion()
    {
        if (isHeroOverDot())
        {
            if (levelManager.hasNextLevel())
            {
                try
                {
                    loadNextLevel();
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
            else
            {
                System.out.println("Game complete!");
                System.exit(0);
            }
        }
    }

private boolean isHeroOverDot()
{
    if (environment == null) return false;

    for (Sprite sprite : environment)
    {
        if (sprite instanceof SolidSprite)
        {
            SolidSprite solidSprite = (SolidSprite) sprite;
            if (solidSprite.isType("nextLevel"))
            {
                //System.out.println("Hero HitBox: " + hero.getHitBox());
                //System.out.println("Dot HitBox: " + solidSprite.getHitBox());
                //System.out.println("cond 1: " + Math.abs(hero.getX() - solidSprite.getX() + hero.getWidth()) +" cond 2: "+ Math.abs(hero.getY() - solidSprite.getY()));

                // Check collision and then check if distance is less than 10px
                Rectangle2D dotHitBox = solidSprite.getHitBox();
                Rectangle2D heroHitBox = hero.getHitBox();
                if (heroHitBox.intersects(dotHitBox))
                {
                    //System.out.println("Hero is on the nextLevel tile!");
                    return true;
                }
                if (Math.abs(hero.getX() - solidSprite.getX() + hero.getWidth()) < 10 && Math.abs(hero.getY() - solidSprite.getY())< 10)
                {
                    //System.out.println("Hero is within buffer zone of nextLevel tile!");
                    return true;
                }
            }
        }
    }
    return false;
}

    private void loadNextLevel() throws Exception
    {
        levelManager.goToNextLevel();
        String nextLevelPath = levelManager.getCurrentLevel();

        //Load new level data
        Playground nextLevel = new Playground(nextLevelPath);
        ArrayList<Sprite> newEnvironment = nextLevel.getEnvironment();

        //Update environment in render engine and physic engine
        hero.setX(hero.getWidth()*2);
        ArrayList<Displayable> displayableEnvironment = new ArrayList<>();
        for (Sprite sprite : newEnvironment) {
            displayableEnvironment.add(sprite);
        }
        renderEngine.clearRenderList();
        renderEngine.addToRenderList(displayableEnvironment);
        renderEngine.addToRenderList(hero);

        physicEngine.setEnvironment(newEnvironment);
        physicEngine.addToMovingSpriteList(hero);

        //Update local environment reference
        setEnvironment(newEnvironment);

        System.out.println("Loaded next level: " + nextLevelPath);
    }

    @Override
    public void keyTyped(KeyEvent e) {}

	//----- Set the direction of the hero if key is pressed -----
    //do all the methods just at the lowering edge, otherwise it is too heavy to hande
    @Override
    public void keyPressed(KeyEvent e)
	{
        switch(e.getKeyCode())
		{
            case KeyEvent.VK_UP :
                if(!upPressed)
                {
                    upPressed = true;
                    hero.setDirection(Direction.NORTH);
                    hero.walks(true);
                }
                break;
            case KeyEvent.VK_DOWN:
                if(!downPressed)
                {
                    downPressed = true;
                    hero.setDirection(Direction.SOUTH);
                    hero.walks(true);
                }
                break;
            case KeyEvent.VK_LEFT:
                if(!leftPressed)
                {
                    leftPressed = true;
                    hero.setDirection(Direction.WEST);
                    hero.walks(true);
                }
                break;
            case KeyEvent.VK_RIGHT:
                if(!rightPressed)
                {
                    rightPressed = true;
                    hero.setDirection(Direction.EAST);
                    hero.walks(true);
                }
                
                break;
            case KeyEvent.VK_CONTROL:
                if(!controlPressed)
                {
                    controlPressed = true;
                    hero.setSpeed(10);
                   // System.out.println("KeyEvent.VK_CONTROL");
                }
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e)
    {
        switch(e.getKeyCode())
		{
            case KeyEvent.VK_UP :
                if(upPressed)
                {
                    upPressed = false;
                    hero.walks(false);
                }
                break;
            case KeyEvent.VK_DOWN:
                if(downPressed)
                {
                    downPressed = false;
                    hero.walks(false);
                }
            case KeyEvent.VK_LEFT:
                if(leftPressed)
                {
                    leftPressed = false;
                    hero.walks(false);
                }
            case KeyEvent.VK_RIGHT:
                if(rightPressed)
                {
                    rightPressed = false;
                    hero.walks(false);
                }
                break;
            case KeyEvent.VK_CONTROL:
                if(controlPressed)
                {
                    controlPressed = false;
                    hero.setSpeed(5);
                }
                break;
        }
    }
}
