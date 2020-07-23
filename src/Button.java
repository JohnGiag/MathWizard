import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Button here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Button extends Actor
{
    private String bText;
    private GreenfootImage green;
    public Button(String bText){
        this.bText=bText;
 
        green=getImage();
        setButtonImage(green);
    }

    public Button(GreenfootImage img){
        setImage(img);

    }

    /**
     * Act - do whatever the Button wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {

    }    
    private void setButtonImage(GreenfootImage img){
        GreenfootImage image=img;
        GreenfootImage text = new GreenfootImage(bText, 35, new Color(0,0,0), new Color(0, 0, 0, 0));        
        image.scale(200,100);

        image.drawImage(text, (image.getWidth()-text.getWidth())/2, 
            (image.getHeight()-text.getHeight())/2);
        setImage(image);  
    }
}
