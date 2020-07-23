/**
 * Write a description of class Calculation here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Calculation  
{
    // instance variables - replace the example below with your own
    private int result;
    private String calc;
    private String type;

    /**
     * Constructor for objects of class Calculation
     */
    public Calculation(String c,int r,String t)
    {
        calc=c;
        result=r;
        type=t;
    }

    /**
     * An example of a method - replace this comment with your own
     * 
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y 
     */
    public int getResult()
    {
        // put your code here
        return result;
    }
    
    public String getCalcText()
    {
        return calc;
    }
    
    
    public String getType(){
        return type;
    }
}
