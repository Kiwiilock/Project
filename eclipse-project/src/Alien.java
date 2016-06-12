public class Alien extends Sprite {

    private final int INITIAL_X = 400;
    private int life;
    private Healthbar healthbar;

    public Alien(int x, int y) {
        super(x, y);
        life = 12;

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
    
    public void moveUp(){
    	
    	if (x < 0) {
            x = INITIAL_X;
        }

        y -= 1;
    }
    
    
    
    
    
    
    public void hurtAlien() 
    {--life;}
    
    public void hurtAlienBadly() 
    {life-=3;}
    
    public int getAlienLife()
    {return life;}
    
    public Healthbar getHealthbar() {return healthbar;}
    
    public void updateHealthbar() {
     if(life >= 10){return;}
     else if(life >=7){healthbar.setYellow(); return;}
     else if (life >= 4) {healthbar.setOrange(); return;}
     else if (life >= 1){healthbar.setRed(); return;}
     else{this.setVisible(false);}
    }
        
}