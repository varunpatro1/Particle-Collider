// Varun Patro

import java.awt.Color;
import java.awt.Graphics2D;
public class Coin
{
    public static final int WIDTH = 28;
    public static final int HEIGHT = 28;
    public static final int MASS = 6;
    
    private Color originalColor;
    private Color currentColor;
    private String color;
    
    public Position point;
    public double xVel;
    public double yVel;
    private boolean isColliding;
    
    // Constucts a Coin object with the following parameters
    
    public Coin(){
        
    }
    
    public Coin (int xcoord, int ycoord, Color color, double xVel, double yVel){
        this.point = new Position(xcoord, ycoord);
        
        this.originalColor = color;
        this.currentColor = color; // made a change here, RHS used to be originalColor
        this.xVel = xVel;
        this.yVel = yVel;
    }   
    
    public void reset(int xcoord, int ycoord, Color color, double xVel, double yVel){
        this.point = new Position(xcoord, ycoord);
        this.currentColor = color;
        this.xVel = xVel;
        this.yVel = yVel;
    }
    
    public void draw(Graphics2D g2d){
        
        g2d.setColor(currentColor);
        g2d.fillArc( (int) getXPos() - WIDTH, (int) getYPos() - HEIGHT, WIDTH, HEIGHT, 0, 360 );
        
    }
    
    public Position getPosition(){
        return this.point;
    }
    
    public void setPosition(Position p){
        this.point.setX(p.getX());
        this.point.setY(p.getY());
    }
    
    public double getXPos(){
        return this.point.getX();
    }
    
    public void setXPos(int x){
        this.point.setX(x);
    }
    
    public double getYPos(){
        return this.point.getY();
    }
    
    public void setYPos(int y){
        this.point.setY(y);
    }
    
    public void updatePosition(double x, double y){
        this.point.x += x;
        this.point.y += y;
    }

    public double getXVel(){
        return this.xVel;
    }
    
    public void setXVel(double xVelocity){
        this.xVel = xVelocity;
    }
    
    public double getYVel(){
        return this.yVel;
    }
        
    public void setYVel(double yVelocity){
        this.yVel = yVelocity;
    }
    
    public Color getColor(){    
        return this.currentColor;
    }
    
    public void setColor(Color c){
        this.currentColor = c;
    }
    
    public boolean getIsColliding(){
        return this.isColliding;
    }
    
    public void setIsColliding(boolean b){
        this.isColliding = b;
    }
    
    public Color getOriginalColor(){
        return this.originalColor;
    }
    
    
    /*public static int numBlackCoins()
    {
        int totalBlackCoins = 0;
        for (Coin counter: Collision.coinList)
        {
            if (counter.color.equalsIgnoreCase("black"))
            {
                totalBlackCoins++;
            }
        }
        
        return totalBlackCoins;
    }
    
    public static int numBrownCoins()
    {
        int totalBrownCoins = 0;
        for (Coin counter: Collision.coinList)
        {
            if (counter.color.equalsIgnoreCase("brown"))
            {
                totalBrownCoins++;
            }
        }
        
        return totalBrownCoins;
    }*/
}

