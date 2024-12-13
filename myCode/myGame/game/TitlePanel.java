package myGame.game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TitlePanel extends JPanel
{
	JPanel titleScreen;
	
	public TitlePanel(JFrame jFrame)
	{		
		System.out.println("Just entered TitleWindow()");
		
		titleScreen = new JPanel();
        this.setLayout(new BorderLayout());

        JLabel title = new JLabel("TitleWindow", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 32));
        this.add(title, BorderLayout.CENTER);

        JButton playButton = new JButton("Play");
        playButton.setFont(new Font("Arial", Font.PLAIN, 24));
		
        playButton.addActionListener(new ActionListener()
		{
            @Override
            public void actionPerformed(ActionEvent e) {
                //Change to game screen
                System.out.println("Change screen");
            }
        });
		
        this.add(playButton, BorderLayout.SOUTH);
	}
}