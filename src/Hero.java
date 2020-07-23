import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Hero here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Hero extends Actor

{

    private int speed = 5;
    private int life = 10;
    private int invFrames=1;
    private GreenfootImage[] imagesR;
    private GreenfootImage[] imagesL;
    private int heading,frame,counter;
    private Bubble b;

    public Hero(){
        b=new Bubble(this);
        frame=0;
        heading=1;
        imagesR=new GreenfootImage[4];
        imagesL=new GreenfootImage[4];
        for(int i=0;i<imagesR.length;i++){
            imagesR[i]=new GreenfootImage("images/Hero/right/walk_"+(i+1)+".png");
            imagesL[i]=new GreenfootImage("images/Hero/left/walk_"+(i+1)+".png");

        }
    }

    /**
     * Act - do whatever the Hero wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
        atCeiling();
        checkKeys();
        if(heading==1)
            animateLeft();
        else if(heading == -1)
            animateRight();
        if(invFrames>0){ //invincibility frames

            invFrames--;
            if(invFrames<30)
                b.setBubbleImageRed();
        }
        else{
            b.deActivate();

        }
        gameOver();

    }   
    public void checkKeys(){
        heading=0;
        if(Greenfoot.isKeyDown("w")){

            setLocation(getX(),getY() - speed);
            heading=1;
        }
        if(Greenfoot.isKeyDown("s")){
            setLocation(getX(),getY() + speed);
            heading=1;
        }
        if(Greenfoot.isKeyDown("a")){
            setLocation(getX() - speed,getY());
            heading=1;
        }
        if(Greenfoot.isKeyDown("d")){
            setLocation(getX() + speed,getY());
            heading=-1;
        }
    }

    public int getLife(){

        return life;

    }

    
    //receive damage
    public void damage(int dmg){
        if(invFrames<=0){ 
            getWorldOfType(GameWorld.class).resetComboCounter();
            life -=dmg;
            getWorldOfType(GameWorld.class).setlifeCounter(life);
            invFrames=120;
            b.activate();
        }
    }

    private void animateRight(){
        if(counter<=0){
            frame++;
            if(frame>=4){
                frame=0;
            }          

            counter=10;
        }
        setImage(imagesR[frame]);
        counter--;
    }

    private void animateLeft(){
        if(counter<=0){
            frame++;
            if(frame>=4){
                frame=0;
            }          

            counter=10;
        }

        setImage(imagesL[frame]);
        counter--;

    }

    public void atCeiling()
    {        
        if(getY() < 100 )
            setLocation(getX(),getY()+speed);
    } 
    
    public Bubble getBubble(){
        return b;
    }
    
    private void gameOver(){
        if(life<=0){
            b.deActivate();
            getWorldOfType(GameWorld.class).removeObject(b);
            getWorldOfType(GameWorld.class).gameOver();
            
        }
        
    }
}
