import java.awt.Color;
import java.awt.Button;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Image;
import javax.swing.ImageIcon;
import java.awt.geom.Rectangle2D;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;

public class Board extends JPanel implements ActionListener {

    private Timer timer;
    private ArrayList<Alien> aliens;
    private ArrayList<Tower> towers;
    private Control mouse;
    private boolean ingame;
    private final int B_WIDTH = 1000;
    private final int B_HEIGHT = 810;
    private final int DELAY = 15;
    private static Integer SCORE = 0;
    private static Integer COINS = 5;
    private static Integer ALIENS_PASSED = 0;

    ////

    private final int nrofblocks = 20;
    private final int blocksize = 50;
    private final int scrsize = nrofblocks * blocksize;
    private short[] screendata;
    private Color mazecolor = new Color(255,102,153);
    ////

    private final int[][] AlienPos = {
            {3000, 200}, {2950, 200}, {2900, 200},
            {2850, 200}, {2700, 200}, {2650, 200},
            {2600, 200}, {2550, 200}, {2500, 200},
            {2450, 200}, {2400, 200}, {2350, 200},
            {2300, 200}, {2250, 200}, {2200, 200},
            {2150, 200}, {2100, 200}, {2050, 200},
            {2000, 200}, {1550, 200}, {1500, 200},
            {1450, 200}, {1400, 200}, {1350, 200},
            {1300, 200}, {1250, 200}, {1200, 200},
            {1150, 200}, {1100, 200}, {1050, 200}
            };

    private final int[][]TowerPos = {{500, 120}, {200, 300}, {880, 500}};  

    private final short gridData[] = {
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,

        };

    Image bgImage;
    
    
    public Board() {
        initBoard();
        bgImage = Toolkit.getDefaultToolkit().createImage("other/BackGround.jpg");
        addMouseListener(mouse);
    }

    private void initBoard() {
        setFocusable(true);
        setBackground(Color.BLACK);
        ingame = true;

        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
        //
        screendata = new short[nrofblocks * nrofblocks];


        int i;
        for (i = 0; i < nrofblocks * nrofblocks; i++) {
            screendata[i] = gridData[i];
        }
        
        initTowers();
        initAliens();
        mouse=new Control();
        timer = new Timer(DELAY, this);
        timer.start();
    }

    private void initTowers() {
    	towers = new ArrayList<>();
    	for (int[] p : TowerPos) {
            towers.add(new Tower(p[0], p[1]));

        }       
    }

    public void initAliens() {
        aliens = new ArrayList<>();

        for (int[] p : AlienPos) {
            aliens.add(new Alien(p[0], p[1]));

        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(bgImage, 0, 0, null); //set a background image
        
        if (ingame) {
            //
            Graphics2D g2d = (Graphics2D) g;
            drawGrid(g2d);
            //
            drawObjects(g);
            updateTowers(g);
            

        } else {
        	if (ALIENS_PASSED > 3) {
        		drawGameOver(g);
        		}
        	else {
        		drawVictory(g);
        	}
        	}

        Toolkit.getDefaultToolkit().sync();
    }

    private void drawObjects(Graphics g) {
    	
    	g.setColor(Color.white);
    	String msg = "Score: " + SCORE.toString();
    	g.drawString(msg, 50, 14);
    	String  msg2 = "You have " + COINS.toString() + " coins.";
    	g.drawString(msg2,  50,  29);
    	String msg3 = "Aliens that reached the city: " + ALIENS_PASSED;
    	g.drawString(msg3, 50, 44);

        for (Tower t : towers) {
            g.drawImage(t.getImage(), t.getX(), t.getY(), this);

        }
        
        /*
        for (Bullet b : bullets) {
            g.drawImage(b.getImage(), b.getX(), b.getY(), this); 

        }
        */

       
        for (Alien a : aliens) {
            if (a.isVisible()) {
                g.drawImage(a.getImage(), a.getX(), a.getY(), this);
                g.drawImage(a.getHealthbar().getImage(), 

                    a.getHealthbar().getX(),
                    a.getHealthbar().getY(), this);

            }
        }
        
        
        

        g.setColor(Color.WHITE);
        //g.drawString("Aliens left: " + aliens.size(), 5, 15);
    }
    
    private void drawGrid(Graphics2D g2d) {

        short i = 0;
        int x, y;

        for (y = 0; y < scrsize; y += blocksize) {
            for (x = 0; x < scrsize; x += blocksize) {

                g2d.setColor(mazecolor);
                g2d.setStroke(new BasicStroke(2));

                if ((screendata[i] & 1) != 0) { 
                    g2d.drawLine(x, y, x, y + blocksize - 1);
                }

                if ((screendata[i] & 2) != 0) { 
                    g2d.drawLine(x, y, x + blocksize - 1, y);
                }

                if ((screendata[i] & 4) != 0) { 
                    g2d.drawLine(x + blocksize - 1, y, x + blocksize - 1,
                        y + blocksize - 1);
                }

                if ((screendata[i] & 8) != 0) { 
                    g2d.drawLine(x, y + blocksize - 1, x + blocksize - 1,
                        y + blocksize - 1);
                }
                
                // 5 --> vertical line
                // 10 --> horizontal line
                // 3 --> top-left corner
                // 9 --> bottom-left corner
                // 12 --> bottom-right corner
                // 6 --> top-right corner
                
                
//                if ((screendata[i] & 16) != 0) { 
//                    g2d.setColor(dotcolor);
//                    g2d.fillRect(x + 11, y + 11, 2, 2);
//                }
                

                i++;
            }
        }
    }
    

    private void drawGameOver(Graphics g) {


        String msg = "The aliens took over the World! Your final score is: " + SCORE.toString();
        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics fm = getFontMetrics(small);

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(msg, (B_WIDTH - fm.stringWidth(msg)) / 2,
            B_HEIGHT / 2);
    }
    private void drawVictory(Graphics g) {

        String msg = "You stopped the invasion! Your final score is: " + SCORE.toString();
        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics fm = getFontMetrics(small);

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(msg, (B_WIDTH - fm.stringWidth(msg)) / 2,
            B_HEIGHT / 2);
    }
    

    @Override
    public void actionPerformed(ActionEvent e) {
    	inGame();
    	repaint();
    	updateAliens();
    	checkCollisions();
    	
    	}

    private void inGame() {

        if (!ingame) {
            timer.stop();
        }
    }

    private void updateAliens() {
        if (aliens.isEmpty()) {
            ingame = false;
            return;
        }

        for (int i = 0; i < aliens.size(); i++) {

            Alien a = aliens.get(i);
            boolean discesa = true;
            if (a.isVisible()) {
                if (a.getX()>700 && a.getY()==200)
                {
                a.moveLeft();
                a.getHealthbar().moveLeft();
                 }
                if (a.getX()==700){
                	a.moveDown();
                    a.getHealthbar().moveDown();
                }
                if (a.getY()==700 && discesa && a.getX()>50){
                	a.moveLeft();
                    a.getHealthbar().moveLeft();
                }
                if (a.getX()==350 && discesa){
                	discesa =  false;
                	a.moveUp();
                    a.getHealthbar().moveUp();
                }
                if (a.getY()==150 && !discesa){
                	a.moveLeft();
                    a.getHealthbar().moveLeft();
                }
                if (a.getX()<400 && a.getY()==150){
                	a.moveLeft();
                    a.getHealthbar().moveLeft();
                }
                if (a.getX()==50){
                	discesa = true;
                	a.moveDown();
                    a.getHealthbar().moveDown();
                }
                if (a.getX()==50 && a.getY()>150){
                	discesa = true;
                	a.moveDown();
                    a.getHealthbar().moveDown();
                }
                
            } 
            else {
            	
            	if (a.getX()<10) {
            		aliens.remove(i);
            		
            	}
            	else {

                aliens.remove(i);
                SCORE += 1;
                COINS += 5;
            	}

            }
        }
    }

    public void updateTowers(Graphics g){
    	for (Tower tower : towers){
    		double min = 1000000;
    		Alien minAlien = aliens.get(0);
    		for (Alien alien : aliens){
    			if (tower.distance(alien) < min){
    				min = tower.distance(alien);
    				minAlien = alien;
    			}
    		}
    		tower.fire(minAlien);
    		if (minAlien.getX()<975 && minAlien.getY()<775){
    		g.setColor(Color.lightGray);
    		g.drawLine(tower.getCenterX(), tower.getCenterY(), minAlien.getCenterX(), minAlien.getCenterY());
    		}
    	}
    	
    }
    
    public void checkCollisions() {  
        for (Alien alien : aliens){
            for (Tower tower: towers){   
                if (tower.intersects(alien.getX(), alien.getY()))
                {
                    alien.hurtAlienBadly();
                    alien.updateHealthbar();
                }
            }
            if (alien.getY()>775)
            	{
            		alien.setVisible(false);
            		ALIENS_PASSED += 1;
            	}   
        }
    }
    
    
    public class Control implements MouseListener{

        public void mouseClicked (MouseEvent e){

        }
        public void mouseEntered(MouseEvent e){}

        public void mouseExited(MouseEvent e){}

        public void mousePressed (MouseEvent e) {
 
        }

        public void mouseReleased(MouseEvent e){
        	

        		Tower newtower = new Tower(e.getX() - 50, e.getY() - 50);
        		boolean collision = false;
        	
        		for(Tower tower : towers){
        			if(tower.intersects(newtower.getCenterX(), newtower.getCenterY()) || newtower.getCenterY()<100	|| COINS<5){
        				collision = true;
        				break;
        			}
        		}
        	
        	
        	
        	
        		if(!collision){
        			towers.add(newtower);
        			COINS -= 5;
        	}
        	}
    }
}

        

    

        
        
        
        