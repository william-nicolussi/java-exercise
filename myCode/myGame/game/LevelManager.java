package myGame.game;

import myGame.src.*;
import myGame.game.*;

import java.util.ArrayList;
import myGame.src.Playground;

public class LevelManager
{
    private ArrayList<String> levels;
    private int currentLevelIndex;
    private Playground currentPlayground;

    public LevelManager()
    {
        // List of levels
        levels = new ArrayList<>();
        levels.add("myGame/data/level1.txt");
        levels.add("myGame/data/level2.txt");
        
        currentLevelIndex = 0;
        currentPlayground = new Playground(levels.get(currentLevelIndex));
    }

    public String getCurrentLevel()
    {
        return levels.get(currentLevelIndex);
    }

    public boolean hasNextLevel()
    {
        return currentLevelIndex < levels.size() - 1;
    }

    public void goToNextLevel()
    {
        if (hasNextLevel())
        {
            currentLevelIndex++;
            currentPlayground = new Playground(levels.get(currentLevelIndex));
        }
    }

    public int getWidth()
    {
        return currentPlayground.getWidth();
    }

    public int getHeight()
    {
        return currentPlayground.getHeight();
    }
}
