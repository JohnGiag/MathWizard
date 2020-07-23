import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class NewGameMenu here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class NewGameMenu extends World
{
    private Button add,mult,sub,mix;
    private CalculationGenerator calculationgenerator;
    private String goal;
    private TextBox ui;
    private Label prompt;
    private String min1="";
    private  String min2="";
    private String max1="";
    private String max2="";
    /**
     * Constructor for objects of class NewGameMenu.
     * 
     */
    public NewGameMenu()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(900, 720, 1); 
        prepare();
    }

    public void prepare(){

        ui = new TextBox();

        //setup background
        setBackground("images/"+(Greenfoot.getRandomNumber(4)+1)+".jpg");
        GreenfootImage menu=new GreenfootImage("images/menuBG.png");
        GreenfootImage select=new GreenfootImage("images/select.png");
        menu.scale(800,600);
        getBackground().drawImage(menu, 50,50);
        getBackground().drawImage(select, 350,60);
        add=new Button("Addition");
        sub=new Button("Subtraction");
        mult=new Button("Multiplication");
        mix=new Button("Mixed");
        addObject(add,10,10);
        addObject(sub,10,10);
        addObject(mult,10,10);
        addObject(mix,10,10);

        add.setLocation(253,297);
        sub.setLocation(552,302);
        mult.setLocation(261,454);
        mix.setLocation(554,461);
    }

    public void act(){
        if(Greenfoot.mousePressed(add)){

            Greenfoot.setWorld(new GameWorld(new CalculationGenerator("add",getUserInput()),goal));
        }
        if(Greenfoot.mousePressed(sub)){

            Greenfoot.setWorld(new GameWorld(new CalculationGenerator("sub",getUserInput()),goal));
        }
        if(Greenfoot.mousePressed(mult)){

            Greenfoot.setWorld(new GameWorld(new CalculationGenerator("mult",getUserInput()),goal));
        }
        if(Greenfoot.mousePressed(mix)){

            Greenfoot.setWorld(new GameWorld(new CalculationGenerator("mix",getUserInput()),goal));
        }

    }

    private String[] getUserInput(){
        removeObjects(getObjects(Button.class));
        boolean notBigger=true;

        min1=getInput("Minimum value for the first operand?");
        while(notBigger){
            max1=getInput("Maximum value for the first operand?");
            if(Integer.parseInt(max1)>=Integer.parseInt(min1))
                notBigger=false;
        }
        notBigger=true;
        min2=getInput("Minimum value for the second operand?");

        while(notBigger){
            max2=getInput("Maximum value for the second operand?");
            if(Integer.parseInt(max2)>=Integer.parseInt(min2))
                notBigger=false;
        }
        
        goal=getInput("Number of questions you want to answer? (0=infinite) ");
        
        MainMenu.stopMusic();

        String[] range={min1,max1,min2,max2};
        
        return range;
        

    }

    private String getInput(String prompt){
        String n="";
        boolean tryAgain=true;
        while(tryAgain) {
            try{

                n=Greenfoot.ask(prompt);

                Integer.parseInt(n);
                tryAgain=false;
            }
            catch(Exception e){
                continue;
            }
        }

        return n;

    }

}
