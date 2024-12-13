package myGame;

import myGame.src.*;
import myGame.game.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class Main
{
	public static final int windowWidth = 400;
	public static final int windowHeight = 600;
	JFrame jFrame;

	TitlePanel titleWindow;
	GamePanel gameWindow;
	
    RenderEngine renderE;
    GameEngine gamE;
    PhysicEngine phyE;

    public Main() throws Exception
	{	
        //----- Draw Title with button -----
        JFrame titleFrame = new JFrame("Title Screen");
        titleFrame.setSize(windowWidth, windowHeight);
        titleFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        titleFrame.setLayout(null);

        //If button pressed, then launchGame()
        JButton startButton = new JButton("Start Game");
        startButton.setBounds(150, 250, 100, 50);
        startButton.addActionListener(e -> {
            titleFrame.dispose();
            try {
                launchGame();
            } catch (Exception ex) {
               ex.printStackTrace();
            }
        });

        titleFrame.add(startButton);
        titleFrame.setVisible(true);
    }

    public static void main (String[] args) throws Exception
	{
        Main main = new Main();
    }

    public void launchGame() throws Exception
    {
        //----- Create and set JFrame -----
        jFrame = new JFrame("myGame");
        jFrame.setSize(windowWidth,windowHeight);
        jFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);	
		
		//----- Game screen -----
		gameWindow = new GamePanel(jFrame);
    }

}