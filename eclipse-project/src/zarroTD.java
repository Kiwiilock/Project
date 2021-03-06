import java.awt.Button;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;




public class zarroTD extends Frame implements ActionListener{ //the main container of the GUI inherits from class Frame

	private final Font GUIFont;
	private BufferedImage alien;
	private BufferedImage alien2;
	private BufferedImage tower;

	public zarroTD() throws FontFormatException, IOException{ //throw an exception is the chosen font is not found
		GUIFont = fontHandler();
		alien = null;
		alien2 = null;
		tower = null;
		imageManager();
		frameProperties();
		addLabels();
		addTextField();
		addPanel();
		addPlayButton();
	}


	public void frameProperties(){
		setSize(500, 300);
		setTitle("The Aliens are here!");
		setVisible(true);
		Color bgColor = new Color(255,102,153);//(214,255,141); //choose a RGB background color
		setBackground(bgColor); //set the chosen color as frame background
		Image iconImage = Toolkit.getDefaultToolkit().createImage("other/tower.png"); //choose an icon for the game
		setIconImage(iconImage); //set the icon
		setResizable(false); //set the frame such that the user can't change its size
		addWindowListener(new WindowAdapter() { //QUIT the starting GUI when the users clicks the window close button
	        public void windowClosing(WindowEvent we) {
	        	System.exit(0);
	         }
	    }
		);
	}

	public Font fontHandler() throws FontFormatException, IOException{ //throw an exception is the chosen font is not found
		     Font font = Font.createFont(Font.TRUETYPE_FONT, new File("other/Capture_it.ttf"));
		     return font.deriveFont(20f); //derive the font to 20pt
	}


	public void actionPerformed(ActionEvent click){
		String[] args = {};
            TD.main(args);
	}

	public void addLabels(){
		Label description = new Label("Hi user, I'm your CPU and I am talking to you", Label.CENTER);
		description.setFont(GUIFont);
		description.setBounds(25, 35, 450, 25); //position and size of the button on the frame (x, y, width, height)
		add(description);

		Label enterName = new Label("Enter your name if you wish", Label.CENTER);
		enterName.setFont(GUIFont);
		enterName.setBounds(-5, 110, 500, 25);
		add(enterName);

		Label clickExplanation = new Label("Now click \"Play\"", Label.CENTER);
		clickExplanation.setFont(GUIFont);
		clickExplanation.setBounds(100, 200, 300, 25);
		add(clickExplanation);
	}


	public void addTextField(){
		TextField writeName = new TextField("Pinco Pallino ul Geek ticines", 15);
		writeName.setBounds(150, 135, 191, 25);
		add(writeName);
	}

	//public void paint(Graphics container){
		//Image img = Toolkit.getDefaultToolkit().createImage("smilingCow.jpg");
		//container.drawImage(img, 0, 0, null);
	//}

	public void addPanel(){
		Panel tryPanel = new Panel();
		tryPanel.setBackground(Color.BLACK);
		add(tryPanel);
	}


	public void addPlayButton(){
		Button playButton = new Button("Play");
		playButton.setFont(GUIFont);
		playButton.addActionListener(this);
		playButton.setBounds(200, 225, 100, 50); //position and size of the button on the frame (x, y, width, height)
		add(playButton);
	}

	public void paint(Graphics g) { //paint a row aliens under the description label
        g.drawImage(alien, 25, 60, null);
        g.drawImage(alien, 65, 60, null);
        g.drawImage(alien, 105, 60, null);
        g.drawImage(alien, 145, 60, null);
        g.drawImage(alien, 185, 60, null);
        g.drawImage(alien, 225, 60, null);
        g.drawImage(alien, 265, 60, null);
        g.drawImage(alien, 305, 60, null);
        g.drawImage(alien, 345, 60, null);
        g.drawImage(alien, 385, 60, null);
        g.drawImage(alien, 425, 60, null);
        
        g.drawImage(tower, 390, 190, null); //paint 2 towers which sandwich the play button
        g.drawImage(tower, 10, 190, null);
    }
	
	
	public void imageManager(){
		try{
			alien = ImageIO.read(new File("other/alien.png")); //load an image from file (take external format png and convert into Java2D format BufferedImage
		}
		catch(IOException e){}
		
		try{
			alien2 = ImageIO.read(new File("other/alien2.png")); //load an image from file (take external format png and convert into Java2D format BufferedImage
		}
		catch(IOException e){}
		
		try{
			tower = ImageIO.read(new File("other/tower.png")); //load an image from file (take external format png and convert into Java2D format BufferedImage
		}
		catch(IOException e){}
	}
	
	
	

	public static void main(String[] args) throws FontFormatException, IOException{ //throw an exception is the chosen font is not found
		new zarroTD(); //call the constructor to instantiate a GUI object
	}

	








}
