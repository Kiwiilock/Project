import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

public class Sprite {

    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected boolean visible;
    protected Image image;
   
    private static final int RANGE = 50;

    public Sprite(int x, int y) {

        this.x = x;
        this.y = y;
        visible = true;
    }

    protected void getImageDimensions() {

        width = image.getWidth(null);
        height = image.getHeight(null);
    }

    protected void loadImage(String imageName) {

        ImageIcon ii = new ImageIcon(imageName);
        image = ii.getImage();
    }

    public Image getImage() {
        return image;
    }

    public int getX() {
        return x;
    }
    
    public int getCenterX() {
        return x+(width/2);
    }

    public int getY() {
        return y;
    }
    
    public int getCenterY() {
        return y+(height/2);
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
    
    public double distance(Sprite s){
    	return Math.pow (Math.pow((this.getX() - s.getX()), 2) + Math.pow((this.getY() - s.getY()), 2), 0.5);
    }
    
    public boolean intersects (int x, int y ) {
    return    x <= this.getCenterX() + RANGE 
           && x >= this.getCenterX() - RANGE 
           && y <= this.getCenterY() + RANGE
           && y >= this.getCenterY() - RANGE;
    }
}

