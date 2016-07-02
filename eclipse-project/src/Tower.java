import java.awt.image.BufferedImage;
import java.awt.*;
import java.awt.geom.Line2D;



/**
 * Write a description of class Tower here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Tower extends Sprite
{

    
    public Tower(int x, int y) {
        super(x, y);
        
        initTower();
        
        
    }

    private void initTower() {

        loadImage("tower.png");
        //loadImage("green.png");
        getImageDimensions();
    }

        

    
    public void fire(Alien a){
    	
    	
    	a.hurtAlien();
    	a.updateHealthbar();
    	
    }
}

