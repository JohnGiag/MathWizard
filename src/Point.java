/**
 point in 2D space
 */
public class Point  
{
    private int x,y;

    /**
     * Constructor for objects of class Point
     */
    public Point(int x,int y)
    {
        this.x=x;
        this.y=y;
        
    }

 
    public Point getPoint()
    {
        // put your code here
        return this;
    }
    
    public int getX(){
        return x;
        
    }
      public int getY(){
        return y;
        
    }
}