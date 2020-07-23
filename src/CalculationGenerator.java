import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class CalculationGenerator here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CalculationGenerator extends Actor
{

    private static int activeCount;
    private GreenfootImage[] imagesR;
    private GreenfootImage[] imagesL;
    private GreenfootImage[] imagesAR;
    private GreenfootImage[] imagesAL;
    private int maxEnemies,spawnDelay;
    private String calc,mode;
    private int result,max1,max2,min1,min2;
    private String type;
    private Point[] spawnPoints;

    
    //constructor
    public CalculationGenerator(String mode,String[] range){
        getImage().setTransparency(0);
        spawnPoints=new Point[4];
        spawnPoints[0]=new Point(110,610);
        spawnPoints[1]=new Point(700,610);
        spawnPoints[2]=new Point(700,150);
        spawnPoints[3]=new Point(130,150);
        calc="";
        result=0;
        this.mode=mode;
        this.min1=Integer.parseInt(range[0]);
        this.max1=Integer.parseInt(range[1]);
        this.min2=Integer.parseInt(range[2]);
        this.max2=Integer.parseInt(range[3]);
        spawnDelay=20;
        maxEnemies=5;
        activeCount=0;
       
        imagesR=new GreenfootImage[10];
        imagesL=new GreenfootImage[10];
        imagesAR=new GreenfootImage[10];
        imagesAL=new GreenfootImage[10];
        
        //init enemy images
        for(int i=0;i<imagesR.length;i++){
            imagesR[i]=new GreenfootImage("images/troll/right/Walk_00"+i+".png");
            imagesL[i]=new GreenfootImage("images/troll/left/Walk_00"+i+".png");
            imagesAR[i]=new GreenfootImage("images/troll/attackRight/Attack_00"+i+".png");
            imagesAL[i]=new GreenfootImage("images/troll/attackLeft/Attack_00"+i+".png");
        
        }
    }

    /**
     * Act - do whatever the CalculationGenerator wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
        activeCount=getWorld().getObjects(Troll.class).size();
        if(Greenfoot.getRandomNumber(100)<5 && activeCount<maxEnemies && spawnDelay<=0){
            generateCalc();

        }
        spawnDelay--;
    }    

    
    //method to generate a calculation of a type (addition,subtraction,multiplication,mixed)
    private void generateCalc(){
        switch(mode){
            case "add" :generateAdd();
            break;
            case "sub" :generateSub();
            break;
            case "mult" :  generateMult();
            break;
            case "mix": generateMixed();

        } 

       
        Troll e=new Troll(imagesR,imagesL,imagesAR,imagesAL);
        CalcActor ca=new CalcActor(e,new Calculation(calc,result,type));
        World w=getWorld();
        Point tmp=spawnPoints[Greenfoot.getRandomNumber(4)];
        int x =tmp.getX();
        int y =tmp.getY(); 
        w.addObject(e,x,y);
        w.addObject(ca,x,y);
        spawnDelay=50;
    }

    
    //method to get random number in a range
    public int getRandomNumberInRange(int start,int end)
    {
        int normal = Greenfoot.getRandomNumber(end-start+1);
        return normal+start;
    }

    
    private void generateAdd(){
        int n1=getRandomNumberInRange(min1,max1);
        int n2=getRandomNumberInRange(min2,max2);
        calc=n1 + " + " + n2;
        result=n1+n2;
        type="add";

    }

    private void generateSub(){
        int n1=getRandomNumberInRange(min1,max1);
        int n2=getRandomNumberInRange(min2,max2);
        calc =  n1 > n2 ? (n1+"-"+n2) : (n2+"-"+n1);
        result=Math.abs(n1-n2);
        type="sub";
    }

    private void generateMult(){
        int n1=getRandomNumberInRange(min1,max1);
        int n2=getRandomNumberInRange(min2,max2);
        calc=n1 + "*" + n2;
        result=n1*n2;
        type="mult";
    }

    private void generateMixed(){       
        int o=Greenfoot.getRandomNumber(3);

        switch(o){
            case 0 :generateAdd();
            break;
            case 1 :generateSub();
            break;
            case 2 :  generateMult();

        } 

    }
}

