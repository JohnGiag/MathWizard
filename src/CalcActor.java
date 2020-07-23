import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class CalculationsGenerator here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CalcActor extends Actor
{

    private int result;
    private Troll e;
    private Calculation calculation;
    private GreenfootImage bg;

    public CalcActor(Troll e,Calculation calculation){
        this.e=e;
        this.calculation=calculation;
        result=calculation.getResult();
        bg=new GreenfootImage("images/green.png");
        setImage(bg);
        getImage().scale(80, 40);
        drawCalc(calculation); 

    }

    /**
     * Act - do whatever the CalculationsGenerator wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {

        followPlayer();

    }    
    //draw text image
    private void drawCalc(Calculation calc){

        Color textColor;
        GreenfootImage image=new GreenfootImage(bg);
        GreenfootImage text = new GreenfootImage(calc.getCalcText(), 35, new Color(0,0,0), new Color(0, 0, 0, 0));        
        image.scale(80,40);
        if (text.getWidth() > image.getWidth() - 20)
        {
            image.scale(text.getWidth() + 20, image.getHeight());
        }

        image.drawImage(text, (image.getWidth()-text.getWidth())/2, 
            (image.getHeight()-text.getHeight())/2);
        setImage(image);  

    }

    //remove from the world
    public void remove(){
        getWorld().removeObject(e);
        getWorld().removeObject(this);

    }
    private void followPlayer(){
        setLocation(e.getX()+5,e.getY()-80);

    }

    public int getResult(){
        return result;
    }

    public String getCalcType(){
        return calculation.getType();

    }

}
