public class Alien extends Sprite {

    private final int INITIAL_X = 400;
    private int life;
    private Healthbar healthbar;

    public Alien(int x, int y) {
        super(x, y);
        life = 1200;

        initAlien();
        
        healthbar = new Healthbar (x, y - 10);
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
    
    public void move2() {

        if (x < 0) {
            x = INITIAL_X;
        }

        y += 1;
    }
    
    public void hurtAlien() 
    {--life;}
    
    public void hurtAlienBadly() 
    {life-=10;}
    
    public int getAlienLife()
    {return life;}
    
    public Healthbar getHealthbar() {return healthbar;}
    
   
    public void updateHealthbar() {
     if(life >= 1000){return;}
     else if(life >=700){healthbar.setYellow(); return;}
     else if (life >= 400) {healthbar.setOrange(); return;}
     else if (life >= 100){healthbar.setRed(); return;}
     else{this.setVisible(false);}
    }
    

    
        
}