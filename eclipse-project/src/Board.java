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
    private final int B_HEIGHT = 1000;
    private final int DELAY = 15;

    ////

    private final int nrofblocks = 20;
    private final int blocksize = 50;
    private final int scrsize = nrofblocks * blocksize;
    private short[] screendata;
    private Color mazecolor = new Color(200, 100, 5);
    ////

    private final int[][] AlienPos = {
            {1600, 200}, {1570, 200}, {1540, 200},
            {1510, 200}, {1480, 200}, {1450, 200},
            {1420, 200}, {1390, 200}, {1360, 200},
            {1330, 200}, {1300, 200}, {1270, 200},
            {1240, 200}, {1210, 200}, {1080, 200},
            {1050, 200}, {1020, 200}, {1900, 200},
            {1870, 200}, {1840, 200}, {1810, 200},
            {1780, 200}, {1750, 200}, {1720, 200},
            {1690, 200}, {1660, 200}, {1630, 200}
        };

    private final int[][]TowerPos = {{800, 250}, {650, 100}, {500, 250}, {350, 500}};  
    //private final int[][]TowerPos = {{450, 250}, {300, 50}, {350, 250}, {400, 50}};

    private final short gridData[] = {
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,

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
        //
        screendata = new short[nrofblocks * nrofblocks];


        int i;
        for (i = 0; i < nrofblocks * nrofblocks; i++) {
            screendata[i] = gridData[i];
        }
        //
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

        for (int[] p : AlienPos) {
            aliens.add(new Alien(p[0], p[1]));

        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (ingame) {
            //
            Graphics2D g2d = (Graphics2D) g;
            drawGrid(g2d);
            //
            drawObjects(g);

        } else {

            drawGameOver(g);
        }

        Toolkit.getDefaultToolkit().sync();
    }

    private void drawObjects(Graphics g) {

        for (int[] p : TowerPos) {
            g.drawImage(tower, p[0], p[1], this); 

        }

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
    //
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
                
                /*
                if ((screendata[i] & 16) != 0) { 
                    g2d.setColor(dotcolor);
                    g2d.fillRect(x + 11, y + 11, 2, 2);
                }
                */

                i++;
            }
        }
    }
    //

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
                if (a.getX() >= 450)
                {
                a.move();
                a.getHealthbar().move();
                 }
                 else
                 {a.move2();
                    a.getHealthbar().move2();}
                
            } else {

                aliens.remove(i);

            }
        }
    }

    public boolean intersectsTowerRange1(int horizontalPos)
    {
        for (int[] p : TowerPos) 
        {
            if (p[0]==horizontalPos) {return true;}
        }
        return false;
    }
    
    
    public boolean intersectsTowerRange2(int horizontalPos)
    {
        for (int[] p : TowerPos) 
        {
            if (p[1]==horizontalPos) {return true;}
        }
        return false;
    }

    public void checkCollisions() {  
        for (Alien alien : aliens){
            if (alien.getY() <= 300){
            if (intersectsTowerRange1(alien.getX()))
            {
                alien.hurtAlienBadly();
                alien.updateHealthbar();
            }
            }
            else {
            if (intersectsTowerRange2(alien.getY()))
            {
                alien.hurtAlienBadly();
                alien.updateHealthbar();
            }
            }

            if (alien.getX() ==0)
            {alien.setVisible(false);}
        }

    }
}