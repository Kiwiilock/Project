import java.awt.image.BufferedImage;
import java.awt.Image;
/**
 * Write a description of class Tower here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Tower extends Sprite
{
    private final int INITIAL_X = 400;
    private static final int RANGE = 50;
    
    public Tower(int x, int y) {
        super(x, y);
        
        initTower();
        
        
    }

    private void initTower() {

        loadImage("tower.png");
        //loadImage("green.png");
        getImageDimensions();
    }

        
    public boolean intersects (int x, int y ) {
    return    x <= this.getX() + RANGE 
           && x >= this.getX() - RANGE 
           && y <= this.getY() + RANGE
           && y >= this.getY() - RANGE;
    }
}

