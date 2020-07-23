import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
/**
 * Write a description of class Fly here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Troll extends Actor
{
    private static final int INRANGE = 250;
    private boolean timerClear;
    private int timer;
    private int speed;
    private int dir;
    private GreenfootImage[] imagesR;
    private GreenfootImage[] imagesL;
    private GreenfootImage[] imagesAR;
    private GreenfootImage[] imagesAL;
    private int frame,attackFrame;   
    private int counter=10;
    private boolean pathing = false;
    private List<Hero> lse;
    private int dmg;
    private String currently;
    private int heading;
    private boolean isAttacking;
    private int attackCounter;
    private int spawnTimer;

    //constructor
    //takes as params all the images for it's animations
    public Troll(GreenfootImage[] imagesR,GreenfootImage[] imagesL,GreenfootImage[] imagesAR,GreenfootImage[] imagesAL){        
        this.imagesR=imagesR;
        this.imagesL=imagesL;
        this.imagesAR=imagesAR;
        this.imagesAL=imagesAL;
        isAttacking=false;
        spawnTimer=60;
        frame=0;
        attackFrame=0;
        heading=1;        
        dmg=1;
        timerClear=true;
        speed=2;
        timer=0;
        dir=1;
    }

    /**
     * Act - do whatever the Fly wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
        checkTimer();   
        sense();

        
        if(pathing){      //if player in range follow him     
            followPlayer();
        }
        else{            //else move randomly
            moveRandom();
        }        

        atBorder();

        if(spawnTimer>0){//timer to not allow a recently spawned enemy to attack the player
            spawnTimer--;
        }
        else{
            attackEnemy();//check if can attack player
        }

        // System.out.println(timer);

    }

    //method to move randomly
    public void moveRandom(){

        if(timerClear){

            int r=Greenfoot.getRandomNumber(120);
            if(r<15){ 
                moveRight();     
                dir=1;
                heading=1;

            }
            else if(r<30){
                moveUpRight();
                dir=2;
                heading=1;
            }
            else if(r<45){

                moveDownRight();
                dir=3;
                heading=1;
            }
            else if(r<60){ 
                moveDown();   
                dir=4;
            }
            else if(r<75){ 
                moveDownLeft();  
                dir=5;
                heading=-1;
            }
            else if(r<90){ 
                moveLeft();   
                dir=6;
                heading=-1;
            }
            else if(r<105){ 
                moveUpLeft();     
                dir=7;
                heading=-1;
            }
            else { 
                moveUp();   
                dir=8;
            }
            timer=150;
        }
        else{//if direction was changed recently keep moving in the same direction

            timer-=1;

            keepHeading();

        }

    }

    //method to follow the player 
    private void followPlayer(){
        Hero h=lse.get(0);

        if(h.getX()>getX() && h.getY()<getY() ){
            moveUpRight();

        }
        else if(h.getX()<getX() && h.getY()>getY())
        {
            moveDownLeft();       
        }
        else if(h.getX()<getX() && h.getY()<getY() ){
            moveUpLeft();
        }
        else if(h.getX()>getX() && h.getY()>getY())
        {
            moveDownRight();       

        } else if(h.getY()>getY()){
            moveDown();
        }
        else if(h.getY()<getY()){
            moveUp();
        }
        else  if(h.getX()<getX())
        {
            moveLeft();
        }
        else  if(h.getX()>getX())
        {
            moveRight();
        }

    }

    //movement methods

    private void moveRight(){
        if(!isAttacking)
            setLocation(getX()+speed,getY());
        animateRight();

    }

    private void moveUpRight(){
        if(!isAttacking)
            setLocation(getX()+speed,getY() - speed);
        animateRight();

    }

    private void moveDownRight(){
        if(!isAttacking)
            setLocation(getX()+speed,getY() + speed);
        animateRight();

    }

    private void moveDown(){
        if(!isAttacking)
            setLocation(getX(),getY()+speed);
        //if(heading==1)
        animateRight();
        // else
        //    animateLeft();

    }

    private void moveDownLeft(){
        if(!isAttacking)
            setLocation(getX()-speed,getY()+speed);
        animateLeft();

    }

    private void moveLeft(){
        if(!isAttacking)
            setLocation(getX()-speed,getY());
        animateLeft();

    }

    private void moveUpLeft(){
        if(!isAttacking)
            setLocation(getX()-speed,getY()-speed);
        animateLeft();

    }

    private void moveUp(){
        if(!isAttacking)
            setLocation(getX(),getY()-speed);
        animateRight();

    }
    //movement methods end

    private void checkTimer(){
        timerClear = (timer<=0);

    }

    //method to keep heading
    private void keepHeading(){
        switch(dir){
            case 1:moveRight();
            break;
            case 2:moveUpRight();
            break;
            case 3:moveDownRight();
            break;     
            case 4:moveDown();
            break;
            case 5:moveDownLeft();
            break;
            case 6:moveLeft();
            break;    
            case 7:moveUpLeft();
            break;     
            case 8:moveUp();
            break; 
        }

    }

    //if at the edge bounce back
    private void atBorder(){
        if(atWorldEdge()){
            switch(dir){
                case 1:dir=6;
                break;
                case 2:dir=5;
                break;
                case 3:dir=7;
                break;     
                case 4:dir=8;
                break;
                case 5:dir=2;
                break;
                case 6:dir=1;
                break;    
                case 7:dir=3;
                break;     
                case 8:dir=4;
                break; 
            }

        }

    }

    //check if player in range
    protected void sense() {

        lse = getObjectsInRange(INRANGE,Hero.class);
        pathing = ! lse.isEmpty();
    }

    public boolean atWorldEdge()
    {
        if(getX() <  getImage().getWidth()/2  || getX() > getWorld().getWidth() - getImage().getWidth() )
            return true;
        if(getY() < 100 +  getImage().getHeight()/2  || getY() > getWorld().getHeight() - getImage().getHeight() )
            return true;
        else
            return false;
    }

    //animation methods
    private void animateRight(){
        if(!isAttacking){
            if(counter<=0){
                frame++;
                if(frame>=10){
                    frame=0;

                }          

                counter=10;
            }
            setImage(imagesR[frame]);
            counter--;
        }
        else{

            animateAttackRight();
        }
    }

    private void animateLeft(){
        if(!isAttacking){
            if(counter<=0){
                frame++;
                if(frame>=10){
                    frame=0;

                }          

                counter=10;
            }

            setImage(imagesL[frame]);
            counter--;
        }
        else{

            animateAttackLeft();
        }

    }

    private void animateAttackRight(){
        if(attackCounter<=0){
            attackFrame++;
            if(attackFrame>=10){
                attackFrame=0;

            }          

            attackCounter=5;
        }
        setImage(imagesAR[attackFrame]);
        attackCounter--;
    }

    private void animateAttackLeft(){

        if(attackCounter<=0){
            attackFrame++;
            if(attackFrame>=10){
                attackFrame=0;

            }          

            attackCounter=5;
        }

        setImage(imagesAL[attackFrame]);
        attackCounter--;

    }

    //animation methods end

    //check if on top of hero
    public Actor canSee(Class clss)
    {
        Actor actor = getOneObjectAtOffset(heading*getImage().getWidth()/2,0, clss);
        return actor;        
    }

    //attack hero if in range
    private void attackEnemy(){
        Hero h=(Hero)canSee(Hero.class);

        if(h != null){
            isAttacking=true;
            h.damage(dmg);


        }
        else{
            isAttacking=false;
        }

    }
}
