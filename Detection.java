// Varun Patro
// Period 3A APCSA
// 05/31/17

public class Detection 
{
    public static boolean collisionDetectorStrikerCoin(Striker s, Coin a)
    {
        double xDistance = Math.abs(s.getXPos() - a.getXPos());
        double yDistance = Math.abs(s.getYPos() - a.getYPos());
        
        if (Math.sqrt((Math.pow(xDistance, 2)) + Math.pow(yDistance, 2)) < (Striker.HALFWIDTH + Coin.WIDTH/2))
        {
            return true;
        }
        return false;
    }
    
    public static boolean collisionDetectorCoinCoin(Coin c, Coin d)
    {
        double xDistance = Math.abs(c.getXPos() - d.getXPos());
        double yDistance = Math.abs(c.getYPos() - d.getYPos());
        
        if (Math.sqrt((Math.pow(xDistance, 2)) + Math.pow(yDistance, 2)) < (Coin.WIDTH/2 + Coin.WIDTH/2))
        {
            return true;
        }
        return false;
    }
     
    // right wall @ 729, sRadius must be @ 710, bRadius @ 717
    // left wall @ 32, radii @ 46
    // up wall @ 40, radii @ 52
    // down wall @ 739, sRadius @ 715, bRadius @ 723   
    
}