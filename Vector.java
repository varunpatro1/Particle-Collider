// Varun Patro
// Period 3A APCSA
// 05/31/17

public class Vector 
{
    private double x;
    private double y; 
    public Vector(double x, double y)
    {
        this.x = x;
        this.y = y;
    }
    
    public Vector (Vector v)
    {
        x = v.getX();
        y = v.getY();
    }
 
    public Vector scaledBy(double d)
    {
        Vector v = new Vector (x * d, y * d);
        return v;
    }
 
    public double getMagnitude()
    {
        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    }
    
    public static double getDotProduct(Vector a, Vector b)
    {
        double dotProduct = (a.x * b.x) + (a.y * b.y);
        return dotProduct;
    }
 
    public double getX()
    {
        return x;
    }
    
    public void setX(double x)
    {
       this.x = x; 
    }
 
    public double getY()
    {
        return y;
    }
    
    public void setY(double y)
    {
       this.y = y; 
    }
    
    public String toString()
    {
        return ("X-Comp" + ": " + x + "Y-Comp " + ":" + y);
    }
}