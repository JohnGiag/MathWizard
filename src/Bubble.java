import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
object used to show player invulnerability
 */
public class Bubble extends Actor
{
    private Hero p;
    public boolean active;
    private GreenfootImage red=new GreenfootImage("images/bubble.png");
    private GreenfootImage green;
    public Bubble(Hero a){        
        p=a;
        getImage().setTransparency(0);
        green=getImage();
        active=false;
    }

    /**
     * Act - do whatever the Bubble wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
        if(active){

            followPlayer();

        }


    }    

    
    private void followPlayer(){
        setLocation(p.getX(),p.getY());

    }
    public void activate(){
        active=true;
        getImage().setTransparency(150);
        

    }

    public void deActivate(){
        active=false;
        getImage().setTransparency(0);
        setImage(green);

    }
    
    public void setBubbleImageRed(){
        setImage(red);
        getImage().setTransparency(150);
        
    }
    
   
}