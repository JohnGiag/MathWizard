import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GameWorld extends World
{
    private Counter correct,life,combo;
    private TextBox ans,correctBox;
    private GreenfootImage text;
    private CalcEvaluation calcevaluation;
    private CalculationGenerator calculationgenerator;
    private int highCombo,correctAdd,correctSub,correctMult,goal;
    private boolean gameOver,won,haveGoal;
    private Hero hero;
    private Button doneB;
    private static GreenfootSound sound;

    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public GameWorld(CalculationGenerator cg,String goal)
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(900, 720, 1); 
        gameOver=false;
        
        sound = new GreenfootSound("sounds/battle.mp3");
      
        sound.playLoop();

        calculationgenerator=cg;
        this.goal=Integer.parseInt(goal);
        haveGoal=true;
        if(this.goal<=0){            
            haveGoal=false;
        }
        won=false;
        highCombo=0;
        correctAdd=0;
        correctSub=0;
        correctMult=0;
        prepare();
    }

    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {

        //setup background

        setBackground("images/"+(Greenfoot.getRandomNumber(4)+1)+".jpg");
        // setBackground("images/4.jpg");
        GreenfootImage side = getBackground();
        int x= 0;
        int y= 0;
        int color= 120 - Greenfoot.getRandomNumber(100);
        side.setColor(new Color(100,100,180));
        side.fillRect(x ,y, 900 ,80);
        side.setColor(new Color(255,255,255));
        side.fillRect(x+15 ,y+10, 870 ,60);
        GreenfootImage done=new GreenfootImage("images/done.png");
        done.scale(150, 150);
        doneB=new Button(done);
        
        //spawn actors
        calcevaluation = new CalcEvaluation();
        addObject(calcevaluation,546,44);

        addObject(calculationgenerator,19,20);
        correct = new Counter("Correct : ");
        life = new Counter("HP : ");
        //life.setBackground("images/red.png");
        addObject(life,784,162);
        combo = new Counter("Combo : ");
        addObject(combo,762,272);

        combo.setLocation(782,226);
        life.setLocation(778,152);

        calculationgenerator.setLocation(460,114);
        calculationgenerator.setLocation(394,108);
        hero = new Hero();
        addObject(hero,408,602);
        setlifeCounter(hero.getLife());
        addObject(hero.getBubble(),0,0);
        life.setLocation(103,43);
  
        combo.setLocation(387,43);
        ans = new TextBox();
        addObject(ans,656,43);
        if(haveGoal){
            correctBox=new TextBox();
            addObject(correctBox,242,43);
        }
        else{
            
            
            addObject(correct,242,43);
        }
    }

    public void incCorrectCounter(){
        correct.add(1);

    }

    public void setlifeCounter(int n){
        life.setValue(n);

    }

    public void incComboCounter(){
        combo.add(1);

    }

    public void resetComboCounter(){
        if(combo.getValue()>highCombo)
            highCombo=combo.getValue();
        combo.setValue(0);

    }

    public void setAns(){
        ans.setAns("Current answer : "+calcevaluation.getAns());

    }
    
    public void setCorrectBox(){
        correctBox.setAns(correct.getValue()+"/"+goal);
    }

    public void act(){
        setAns();
       
        if(haveGoal){
            setCorrectBox();
            if(goal<=correct.getValue()){
                won=true;
                gameOver();
            }
            
        }
        if(gameOver){
            if(Greenfoot.isKeyDown("escape")){
                
                Greenfoot.setWorld(new MainMenu());

            }

        }
        
        if(Greenfoot.mousePressed(doneB)){
            
            sound.stop();
            Greenfoot.setWorld(new MainMenu());
        }
    }

    private int totalScore(){
        return correctAdd+correctSub+correctMult;
    }

    public void gameOver(){
        gameOver=true;
        removeObject(hero);
        removeObjects(getObjects(CalcActor.class));
        removeObjects(getObjects(Troll.class));
        removeObjects(getObjects(Counter.class));

        removeObject(calculationgenerator);
        removeObjects(getObjects(TextBox.class));
        showScoreboard();

    }

    private void showScoreboard(){
        //System.out.println(correctAdd+" "+correctSub+" "+correctMult);
        setBackground("images/1.jpg");
        GreenfootImage menu=new GreenfootImage("images/menuBG.png");
        GreenfootImage star=new GreenfootImage("images/star.png");
        menu.scale(800,600);
        String msg="Game Over ";
        if(won)
            msg="You Win";
        String debrief=msg+ "\n\nCorrect Additions : "+correctAdd+
            "\n\nCorrect Subtractions : "+correctSub+
            "\n\nCorrect Multiplications : "+correctMult+
            "\n\nHighest Combo : "+ highCombo;
           
        GreenfootImage text = new GreenfootImage(debrief, 35, new Color(0,0,0), new Color(0, 0, 0, 0));        
        menu.drawImage(text, 200, 150);
        getBackground().drawImage(menu, 50,50);
        getBackground().drawImage(star, 370,30);
        addObject(doneB,430,630);
    }

    public void incCorrectAdd(){

        correctAdd++;
    }

    public void incCorrectSub(){

        correctSub++;
    }

    public void incCorrectMult(){

        correctMult++;
    }

    // public void setAns(String s){
    //   text.setColor(new Color(255,255,255));
    // text

    //  text = new GreenfootImage("Current answer : "+s, 24, new Color(0,0,0), new Color(0, 0, 0, 0)); 
    //   //Label ans = new Label("Current Answer : ", 24);
    //  getBackground().drawImage(text, 680,326);
    // }
}
