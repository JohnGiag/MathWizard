import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class InfoMenu here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class InfoMenu extends World
{
    private MainMenu p;
    private Button backB;
    /**
     * Constructor for objects of class InfoMenu.
     * 
     */
    public InfoMenu(MainMenu parent)
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(900, 720, 1); 
        p=parent;
        prepare();
    }

    public void prepare(){
        setBackground("images/"+(Greenfoot.getRandomNumber(4)+1)+".jpg");
        GreenfootImage menu=new GreenfootImage("images/infoPanel.png");
        GreenfootImage header=new GreenfootImage("images/attention.png");
        GreenfootImage back=new GreenfootImage("images/back.png");
        back.scale(100,100);
        getBackground().drawImage(menu, 150,0);
        getBackground().drawImage(header, 300,30);
        backB=new Button(back);
        addObject(backB,600,660);

    }
    
    public void act(){
         if(Greenfoot.mousePressed(backB)){
            
            
            Greenfoot.setWorld(p);
        }
        
    }
}
