import java.awt.image.BufferedImage;
import java.awt.Image;

public class Healthbar extends Sprite
{
    private final int INITIAL_X = 400;
    private int life = 10;


    public Healthbar(int x, int y) {
        super(x, y);

        initHealthbar();
    }

    private void initHealthbar() {

        loadImage("res/green.png");

        //BufferedImage image = new BufferedImage(this.getX(), this.getY(), BufferedImage.TYPE_INT_RGB);
        //image.setColor(Color.GREEN);
        getImageDimensions();
    }

    public void move() {

        if (x < 0) {
            x = INITIAL_X;
        }

        x -= 1;
    }
    
    public void move2() {

        if (x < 0) {
            x = INITIAL_X;
        }

        y += 1;
    }
    
    public void setYellow() {
    loadImage("res/yellow.png");
    }
    
    public void setOrange() {
    loadImage("res/orange.png");
    }
    
    public void setRed() {
    loadImage("res/red.png");
    }
}
