public class Alien extends Sprite {

    private final int INITIAL_X = 400;
    private int life = 3;


    public Alien(int x, int y) {
        super(x, y);

        initAlien();
    }

    private void initAlien() {

        loadImage("alien.png");
        //loadImage("green.png");
        getImageDimensions();
    }

    public void move() {

        if (x < 0) {
            x = INITIAL_X;
        }

        x -= 1;
    }
    
    public void hurtAlien() 
    {--life;}
    
    public int getAlienLife()
    {return life;}
    
    
        
}