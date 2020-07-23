import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MainMenu here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MainMenu extends World
{
     private Button playB,exitB,infoB;
     private static GreenfootSound sound;
    /**
     * Constructor for objects of class MainMenu.
     * 
     */
    public MainMenu()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(900, 720, 1); 
        sound = new GreenfootSound("sounds/menu.mp3");
      
        sound.playLoop();
        prepare();
    }
    
    public void prepare(){
        //setup background
        setBackground("images/"+(Greenfoot.getRandomNumber(4)+1)+".jpg");
        GreenfootImage play=new GreenfootImage("images/play.png");
        GreenfootImage exit=new GreenfootImage("images/exit.png");
        GreenfootImage info=new GreenfootImage("images/info.png");
        GreenfootImage title=new GreenfootImage("images/title1.png");
        getBackground().drawImage(title, 200,60);
        
        playB=new Button(play);
        exitB=new Button(exit);
        infoB=new Button(info);
        
        addObject(playB,140,340);
        addObject(exitB,715,340);
        addObject(infoB,420,340);
       
       
        
    }
    
    public void act(){
        if(Greenfoot.mousePressed(infoB)){
            
            
            Greenfoot.setWorld(new InfoMenu(this));
        }
         if(Greenfoot.mousePressed(playB)){
            
            
            Greenfoot.setWorld(new NewGameMenu());
        }        
         if(Greenfoot.mousePressed(exitB)){
            
            
            System.exit(0);
        }
        
    }
    
    public static void stopMusic(){
        sound.stop();
        
    }
}
