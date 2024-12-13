package myGame.src;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class Playground
{
    private ArrayList<Sprite> environment = new ArrayList<>();
    private int levelWidth = 0;
    private int levelHeight = 0;

    public Playground (String pathName)
	{
        try{
            final Image imageTree = ImageIO.read(new File("myGame/img/tree.png"));
            final Image imageGrass = ImageIO.read(new File("myGame/img/grass.png"));
            final Image imageRock = ImageIO.read(new File("myGame/img/rock.png"));
            final Image imageTrap = ImageIO.read(new File("myGame/img/trap.png"));
            final Image imageNewLevel = ImageIO.read(new File("myGame/img/tileSetTest.png"));

            final int imageTreeWidth = imageTree.getWidth(null);
            final int imageTreeHeight = imageTree.getHeight(null);

            final int imageGrassWidth = imageGrass.getWidth(null);
            final int imageGrassHeight = imageGrass.getHeight(null);

            final int imageRockWidth = imageRock.getWidth(null);
            final int imageRockHeight = imageRock.getHeight(null);
			
			final int imageTrapWidth = imageTrap.getWidth(null);
            final int imageTrapHeight = imageTrap.getHeight(null);

            final int imageNewLevelWidth = imageNewLevel.getWidth(null);
            final int imageNewLevelHeight = imageNewLevel.getHeight(null);

            BufferedReader bufferedReader = new BufferedReader(new FileReader(pathName));
            String line=bufferedReader.readLine();
            int lineNumber = 0;
            int columnNumber = 0;
            while (line!= null)
			{
                levelWidth = Math.max(levelWidth, line.length());
                for (byte element : line.getBytes(StandardCharsets.UTF_8)){
                    switch (element)
					{
                        case 'T' : environment.add(new SolidSprite(columnNumber*imageTreeWidth,
                                lineNumber*imageTreeHeight,imageTree, imageTreeWidth, imageTreeHeight));
                                    break;
                        case ' ' : environment.add(new Sprite(columnNumber*imageGrassWidth,
                                lineNumber*imageGrassHeight, imageGrass, imageGrassWidth, imageGrassHeight));
                            break;
                        case 'R' : environment.add(new SolidSprite(columnNumber*imageRockWidth,
                                lineNumber*imageRockHeight, imageRock, imageRockWidth, imageRockHeight));
                            break;
						case 'F' : environment.add(new Sprite(columnNumber*imageTrapWidth,
                                lineNumber*imageTrapHeight, imageTrap, imageTrapWidth, imageTrapHeight));
                            break;
                        case '.' : environment.add(new SolidSprite(columnNumber*imageNewLevelWidth,
                                lineNumber*imageNewLevelHeight, imageNewLevel, imageNewLevelWidth, imageNewLevelHeight, "nextLevel"));
                            break;
                        case 'a': environment.add(new SolidSprite(columnNumber*imageNewLevelWidth,
                                lineNumber*imageNewLevelHeight, imageNewLevel, imageNewLevelWidth, imageNewLevelHeight));
                                break;
                    }
                    columnNumber++;
                }
                columnNumber = 0;
                lineNumber++;
                line=bufferedReader.readLine();
            }
            // Calculate the level height
            levelHeight = lineNumber;
            // Convert level dimensions to pixels
            levelWidth = (levelWidth) * imageTreeWidth;
            levelHeight = (levelHeight) * imageTreeHeight;
            System.out.println("levelWidth = "+levelWidth+" levelHeight = "+levelHeight);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public ArrayList<Sprite> getSolidSpriteList()
	{
        ArrayList <Sprite> solidSpriteArrayList = new ArrayList<>();
        for (Sprite sprite : environment){
            if (sprite instanceof SolidSprite) solidSpriteArrayList.add(sprite);
        }
        return solidSpriteArrayList;
    }

    public ArrayList<Displayable> getSpriteList()
	{
        ArrayList <Displayable> displayableArrayList = new ArrayList<>();
        for (Sprite sprite : environment)
		{
            displayableArrayList.add((Displayable) sprite);
        }
        return displayableArrayList;
    }

    public ArrayList<Sprite> getEnvironment() {
        return environment;
    }

    public int getWidth() {return levelWidth; }
    public int getHeight() {return levelHeight;}
}
