package trypResources;

public class Vector2 
{
    double x, y;
    public Vector2(double x, double y)
    {
        this.x=x;
        this.y=y;
    }
    
    public Vector2 add(Vector2 toAdd)
    {
        if(toAdd != null)
        {
            x+=toAdd.getX();
            y+=toAdd.getY();
        }
        return this;
    }
    
    public int getXInt()
    {
        return (int)Math.round(x);
    }
    
    public int getYInt()
    {
        return (int)Math.round(y);
    }
    
    public double getX()
    {
        return x;
    }
    
    public double getY()
    {
        return y;
    }
}
