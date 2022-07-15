// Varun Patro

import java.awt.Polygon;
public class Striker
{    
    public static final int HALFWIDTH = 18;
    public static final int HALFHEIGHT = 18;
    
    public static final int MASS = 15;    
    //public static final Polygon arrow = new Polygon(new int[]{405, 395, 395, 395, 405, 415, 415, 415, 405}, new int[]{610, 610, 575, 575, 560, 575, 575, 610, 610}, 9);
    //385, 405, 375, 405, 375, 410, 410, 385
    //595, 595, 595, 580, 580, 580, 580, 570
    //8
    //public static final double sigma = 8.48;
    
    
    public static Position sPoint;
    //public static int moveCounter = 0;
    
    //public static boolean sPocketed = false;    
    public double xVel = 0;
    public double yVel = 0;
    public Striker(Position p){
        this.sPoint = p;
    }
    
    public int getXPos(){
        return this.sPoint.x;
    }
    
    public void setXPos(int x){
        this.sPoint.x = x;
    }
    
    public int getYPos(){
        return this.sPoint.y;
    }
    
    public void setYPos(int y){
        this.sPoint.y = y;
    }

    public double getXVel(){
        return this.xVel;
    }
    
    public double getYVel(){
        return this.yVel;
    }
        
    public void setXVel(double xVel){
        this.xVel = xVel;
    }
    
    public void setYVel(double yVel){
        this.yVel = yVel;
    }
    
 
    /*public void isPocketed(Striker s)
    {
        if (s.sPoint.getX() <= 92 && s.sPoint.getY() <= 100)
        {
            sPocketed = true;
            Collision.coinList.remove(s);
        }
        
        if (s.sPoint.getX() <= 701 && s.sPoint.getY() <= 100)
        {
            sPocketed = true;
            //Carrom.coinList.remove(s);
        }
        
        if (s.sPoint.getX() <= 92 && s.sPoint.getY() <= 712)
        {
            sPocketed = true;
            //Carrom.coinList.remove(s);
        }
        
        if (s.sPoint.getX() <= 701 && s.sPoint.getY() <= 712)
        {
            sPocketed = true;
            //sCarrom.coinList.set(indexOf(a), null);
        }
        
    }*/
                
    /*public void shoot(double mouseTime)
    {
        moveCounter++;
        
    }
    
    
    public void resetLocation()
    {
        sPoint = new Position(385 + Striker.HALFWIDTH, 618 + Striker.HALFHEIGHT);
    }*/
}
