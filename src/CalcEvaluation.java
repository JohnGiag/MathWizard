import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
/**
 * Write a description of class CalcEvaluation here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CalcEvaluation extends Actor
{
    private String answer="";
    private ArrayList<CalcActor> actors=new  ArrayList<CalcActor>();
    
    public CalcEvaluation(){
        getImage().setTransparency(0);
    }
   
    /**
     * Act - do whatever the CalcEvaluation wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
        checkKey();
    }  

    public void checkKey()
    {
        String key = Greenfoot.getKey();
        //if a key is pressed
        if (key != null)
        {
            if (!("enter".equals(key)))//and it's not enter
            {
                try {                   
                    int a=Integer.parseInt(key);//if it's a number add it to the answer String
                    answer+=key;
                } catch (NumberFormatException e) {
                    //  do nothing
                }             
            }
            else//if enter is presed evaluate the answer
            {
                evaluateAnser(answer);
                answer="";
            }
        }
    }

    //evaluate current answer by comparing it to the answers of all the enemies in the world
    public void evaluateAnser(String ans){

        try {

            int a=Integer.parseInt(ans);
            for(CalcActor e : getWorld().getObjects(CalcActor.class)){
                if(a==e.getResult()){

                    GameWorld w=(GameWorld) getWorld();
                    String type=e.getCalcType();
                    //increase counters
                    switch(type){
                        case "add" :w.incCorrectAdd();
                        break;
                        case "sub" :w.incCorrectSub();
                        break;
                        case "mult" :  w.incCorrectMult();

                    } 
                    e.remove();

                    w.incComboCounter();
                    w.incCorrectCounter();

                }

            }
        } catch (NumberFormatException e) {
            //  do nothing
        }

    }

    public String getAns(){
        return answer;
    }
}
