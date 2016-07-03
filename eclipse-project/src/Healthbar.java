
public class Healthbar extends Sprite
{
    private final int INITIAL_X = 400;
 


    public Healthbar(int x, int y) {
        super(x, y);

        initHealthbar();
    }

    private void initHealthbar() {

        loadImage("other/green.png");

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
    
    
    public void moveUp(){
    	
    	if (x < 0) {
            x = INITIAL_X;
        }

        y -= 1;
    }
    
    
    
    
    
    public void setYellow() {
    loadImage("other/yellow.png");
    }
    
    public void setOrange() {
    loadImage("other/orange.png");
    }
    
    public void setRed() {
    loadImage("other/red.png");
    }
}
