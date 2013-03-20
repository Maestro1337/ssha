import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.swing.JFrame;
import javax.swing.JButton;

public class MainView extends JFrame implements ActionListener {

	public MainView() {
		setSize(1024,768);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new GridLayout());
		setResizable(false);
		
		JButton hurtButton = new JButton("Taking damage");
		hurtButton.setSize(100,50);
		add(hurtButton);
		hurtButton.addActionListener(this);
		
		JButton swordButton = new JButton("Sword");
		swordButton.setSize(100,50);
		add(swordButton);
		swordButton.addActionListener(this);
		
		JButton bowButton = new JButton("Bow");
		bowButton.setSize(100,50);
		add(bowButton);
		bowButton.addActionListener(this);
		
		JButton fireballButton = new JButton("Fireball");
		fireballButton.setSize(100,50);
		add(fireballButton);
		fireballButton.addActionListener(this);
		
		JButton musicButton = new JButton("Music");
		musicButton.setSize(100,50);
		add(musicButton);
		musicButton.addActionListener(this);
		

		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String fileName = "res/154561__ecfike__hurt-argh-1.wav";
		if(e.getActionCommand() == "Taking damage"){
			playSound("res/154561__ecfike__hurt-argh-1.wav");
		} else if(e.getActionCommand() == "Sword"){
			playSound("res/77611__joelaudio__sfx-attack-sword-001.wav");
		} else if(e.getActionCommand() == "Bow"){
			playSound("res/65733__erdie__bow01.wav");
		} else if(e.getActionCommand() == "Fireball"){
			playSound("res/77691__joelaudio__sfx-magic-fireball-001.wav");
		} else if(e.getActionCommand() == "Music"){
			playSound("res/126427__cabeeno-rossley__timer-first-half-loop.wav");
		}
	}
	
	public static synchronized void playSound(String filename) {

		    try
		    {
		        Clip clip = AudioSystem.getClip();
		        clip.open(AudioSystem.getAudioInputStream(new File(filename)));
		        clip.start();
		    }
		    catch (Exception exc)
		    {
		        exc.printStackTrace(System.out);
		    }	
	}
}
