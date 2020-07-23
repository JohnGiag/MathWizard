import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class TextBox here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TextBox extends Actor
{

    private String ans;
    private  GreenfootImage bg;

    public TextBox(){
        getImage().scale(100,50);
        bg=getImage();
        ans="";
    }

    /**
     * Act - do whatever the TextBox wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
        draw();
    }    

    
    //draw text image and scale it
    private void draw(){

        GreenfootImage image=new GreenfootImage(bg);
        GreenfootImage text = new GreenfootImage(ans, 35, new Color(0,0,0), new Color(0, 0, 0, 0));        
       
        image.scale(100,50);
        if (text.getWidth() > image.getWidth() - 20)
        {
            image.scale(text.getWidth() + 20, image.getHeight());
        }

        image.drawImage(text, (image.getWidth()-text.getWidth())/2, 
            (image.getHeight()-text.getHeight())/2);
        setImage(image);  

    }
    
    //set String to draw
    public void setAns(String s){
        ans=s;

    }
}
