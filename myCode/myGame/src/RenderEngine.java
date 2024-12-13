package myGame.src;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import myGame.game.Camera;

public class RenderEngine extends JPanel implements Engine
{
    private ArrayList<Displayable> renderList; //list of elements diplayed
     private Camera camera;
     
	//----- CONSTRUCTOR -----
	//It only initialize the renderList by creating an ArrayList
    public RenderEngine(JFrame jFrame)
	{
        renderList = new ArrayList<>();
    }

    public void setCamera(Camera camera)
    {
        this.camera = camera;
    }

	//----- Add to renderList passing an object or an Arraylist -----
    public void addToRenderList(Displayable displayable)
	{
        if (!renderList.contains(displayable)) //Add only new elements
		{
            renderList.add(displayable);
        }
    }

    public void addToRenderList(ArrayList<Displayable> displayable)
	{
        if (!renderList.contains(displayable)){
            renderList.addAll(displayable);
        }
    }

    public void clearRenderList() {
        renderList.clear(); // Clears all sprites from the render list
    }

	//----- Override of a method in JPanel -----
    @Override
    public void paint(Graphics g)
	{
        super.paint(g);

         // Use camera offsets to adjust rendering
        Graphics2D g2d = (Graphics2D) g;
        double offsetX = camera.getOffsetX();
        double offsetY = camera.getOffsetY();

        for (Displayable renderObject:renderList) //draw every object in the renderList
		{
            if (renderObject instanceof Sprite) {
            // Cast to Sprite to access the draw method with offsets
            ((Sprite) renderObject).draw(g, offsetX, offsetY);
        } else {
            renderObject.draw(g); // Default draw method
        }
        }
    }

	//----- repaint generates the graphic parameters -----
    @Override
    public void update(){
        this.repaint();
    }
}
