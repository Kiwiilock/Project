import java.awt.Color;
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

public class Board extends JPanel implements ActionListener {

    private Timer timer;
    private ArrayList<Alien> aliens;

    private Image tower;
    private boolean ingame;
    private final int B_WIDTH = 1000;
    private final int B_HEIGHT = 500;
    private final int DELAY = 15;

    private final int[][] pos = {
        {1600, 150}, {1570, 150}, {1540, 150},
        {1510, 150}, {1480, 150}, {1450, 150},
        {1420, 150}, {1390, 150}, {1360, 150},
        {1330, 150}, {1300, 150}, {1270, 150},
        {1240, 150}, {1210, 150}, {1080, 150},
        {1050, 150}, {1020, 150}, {990, 150},
        {960, 150}, {930, 150}, {900, 150},
        {870, 150}, {840, 150}, {810, 150},
        {780, 150}, {750, 150}, {620, 150}
    };

    public Board() {
        initBoard();
    }

    private void initBoard() {
        loadTowerImage();
        int w = tower.getWidth(this);
        int h =  tower.getHeight(this);
        setPreferredSize(new Dimension(w, h));  
        
        setFocusable(true);
        setBackground(Color.BLACK);
        ingame = true;

        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));

        initAliens();


        timer = new Timer(DELAY, this);
        timer.start();
    }
    
    private void loadTowerImage() {
        
        ImageIcon ii = new ImageIcon("tower.png");
        tower = ii.getImage();        
    }
    
   


    public void initAliens() {
        aliens = new ArrayList<>();

        for (int[] p : pos) {
            aliens.add(new Alien(p[0], p[1]));

        }
    }
    
    

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (ingame) {

            drawObjects(g);

        } else {

            drawGameOver(g);
        }

        Toolkit.getDefaultToolkit().sync();
    }

    private void drawObjects(Graphics g) {

            g.drawImage(tower, 500, 250, this); 
            
   
        for (Alien a : aliens) {
            if (a.isVisible()) {
                g.drawImage(a.getImage(), a.getX(), a.getY(), this);
                
                
            }
        }
       


        g.setColor(Color.WHITE);
        //g.drawString("Aliens left: " + aliens.size(), 5, 15);
    }

    private void drawGameOver(Graphics g) {

        String msg = "Game Over";
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

      
        updateAliens();


        checkCollisions();

        repaint();
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
            if (a.isVisible()) {
                a.move();
            } else {
                aliens.remove(i);
            }
        }
    }
    
    



    
    
    

    public void checkCollisions() {  
        for (Alien alien : aliens){
            int alienHorizontalPosn = alien.getX();
            if(alienHorizontalPosn == 500){
                alien.setVisible(false);
                
            }
        }
       
    }
}