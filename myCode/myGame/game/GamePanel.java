package myGame.game;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import myGame.src.*;
import myGame.game.*;

public class GamePanel
{
	RenderEngine renderE;
    GameEngine gamE;
    PhysicEngine phyE;
    LevelManager levelManager;
	
	public GamePanel(JFrame finestraDiGioco) throws Exception
	{
		Player hero = new Player(200,300, ImageIO.read(new File("myGame/img/heroTileSheetLowRes.png")),48,50);
        levelManager = new LevelManager();

		//----- Add the three engines -----
        renderE = new RenderEngine(finestraDiGioco);
        phyE = new PhysicEngine();
        gamE = new GameEngine(hero, renderE, phyE);


		//----- Timers to do the update() -----
        Timer renderTimer = new Timer(50,(time)-> renderE.update());
        Timer gameTimer = new Timer(50,(time)-> gamE.update());
        Timer physicTimer = new Timer(50,(time)-> phyE.update());

		//----- start the timers -----
        renderTimer.start();
        gameTimer.start();
        physicTimer.start();

		//----- Add the JPanel renderE to the JFrame -----
        finestraDiGioco.getContentPane().add(renderE);
        finestraDiGioco.setVisible(true);

		//----- Upload the level -----
        Playground level = new Playground(levelManager.getCurrentLevel()); //add path first level
        renderE.addToRenderList(level.getSpriteList());
        gamE.setEnvironment(level.getEnvironment());
        renderE.addToRenderList(hero);
        phyE.addToMovingSpriteList(hero);
        phyE.setEnvironment(level.getSolidSpriteList());

		//----- Add the components able to detect keyboard events -----
        finestraDiGioco.addKeyListener(gamE);
	}
}