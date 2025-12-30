package myCode.game;

import javax.imageio.ImageIO;
import java.awt.BorderLayout;
import javax.swing.*;
import java.io.File;

import myCode.src.*;

public class GamePanel extends JPanel {
    private RenderEngine renderE;
    private GameEngine gamE;
    private PhysicEngine phyE;
    private LevelManager levelManager;

    public GamePanel(JFrame gameFrame) throws Exception {
        setLayout(new BorderLayout());

        // Create the player
        Player hero = new Player(200, 300, ImageIO.read(new File("myCode/img/heroTileSheetLowRes.png")), 48, 50);
        levelManager = new LevelManager();

        // Initialize game engines
        renderE = new RenderEngine(gameFrame);
        phyE = new PhysicEngine();
        gamE = new GameEngine(hero, renderE, phyE);

        // Load the current level
        Playground level = new Playground(levelManager.getCurrentLevel());
        renderE.addToRenderList(level.getSpriteList());
        gamE.setEnvironment(level.getEnvironment());
        renderE.addToRenderList(hero);
        phyE.addToMovingSpriteList(hero);
        phyE.setEnvironment(level.getSolidSpriteList());

        // Add render engine panel to this GamePanel
        add(renderE, BorderLayout.CENTER);

        // Start timers for game updates
        new Timer(50, (time) -> renderE.update()).start();
        new Timer(50, (time) -> gamE.update()).start();
        new Timer(50, (time) -> phyE.update()).start();

        // Enable keyboard input
        gameFrame.addKeyListener(gamE);
    }
}
