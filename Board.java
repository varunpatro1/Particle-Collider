// Varun Patro

import javax.swing.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.Polygon;
import java.awt.Font;
import java.awt.Color;


// Board class will inherit from JPanel
public class Board extends JPanel implements MouseListener, MouseMotionListener, KeyListener
{
    // Creates an instance of the Striker class
    public static Striker striker = new Striker(new Position 
    (385 + Striker.HALFWIDTH, 618 + Striker.HALFHEIGHT));
    
    // Keep time for MouseListener methods
    private static long start;
    private static long end;
    private static double difference;
    private double startTime;

    // Fields used in the implementation of the KeyListener
    public boolean strikerShifted = false;
    public String whichKey;
    private boolean keyHeldDown;
    private KeyEvent lastKey;
    private boolean strikerRightBound = false;
    private boolean strikerLeftBound = false;

    // Fields used in the implementation of the MouseListener
    public boolean newShot = false;
    private boolean mouseDown = false;
    private boolean isAiming = false;
    private boolean isGrowing = false;
    private boolean hasShot = false;
    private int mouseX = 0;
    private int mouseY = 0;
    private int strikerX = 403;
    private int strikerY = 636;
    
    // Creates a Polygon object with the coordinates of an arrow
    public static final Polygon arrow = new Polygon
    (new int[]{405, 395, 395, 395, 405, 415, 415, 415, 405}, 
    new int[]{610, 610, 575, 575, 560, 575, 575, 610, 610}, 9);
    
    // Fields used in the implementation of the AffineTransformation
    public Vector direction = new Vector(0,0);
    private double magnitude = 0;
    private final double MAX_MAGNITUDE = 5;
    private double oX = 0; //default offset x
    private double oY = 0; //default offset y
    private Vector strikerDirection;
    
    // Used to keep track of which JButton to execute the functionality for
    public static int startScreen = 0;

    
    public Board()
    {
        super();
        oX = arrow.xpoints[0] - striker.getXPos();
        oY = arrow.ypoints[0] - striker.getYPos();

        for (int rep = 0; rep < 10; rep++)
        {
            Collision.coinList.add(makeCoin());
        }
    }

    // Called when RESTART JButton is pressed to re-initialize 
    //the fields of the Coin objects in coinList and draw them again
    public static void setCoins()
    {
        for (Coin c: Collision.coinList)
        {
            c.reset((int)((48 - WIDTH/2) + (Math.random()*(699 + WIDTH/2))), 
            (int)((56 - WIDTH/2) + (Math.random()*(703 + WIDTH/2))),
            new Color ((int)(Math.random()*255), 
                (int)(Math.random()*255),
                (int)(Math.random()*255), 200),
                ((Math.random() * 6) - 3), 
            ((Math.random() * 6) - 3));
            
        }
    }
    
    
    public Coin makeCoin()
    {
        return new Coin ((int)((48 - WIDTH/2) + (Math.random()*(699 + WIDTH/2))), 
            (int)((56 - WIDTH/2) + (Math.random()*(703 + WIDTH/2))), 
            new Color ((int)(Math.random()*255), 
                (int)(Math.random()*255), 
                (int)(Math.random()*255)), 
                ((Math.random() * 12) - 3), 
            ((Math.random() * 12) - 3));
    }

    // Calls paintComponent method
    public void update()
    {
        repaint();
    }
    
    
    public void paintComponent (Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;        

        // Concept adapted from Andrew Thompson - stackoverflow.com
        /*BufferedImage image = null;
        //try
        //{
          //  image = ImageIO.read(new File("CarromBoard.jpg"));
        //}
        //catch (IOException ex) {
        }
        
        //g2d.drawImage(image, 0,0, 800, 800, null);
        //*/
        
        // Executed when the first JButton is pressed
        if (startScreen == 1)
        {
            g2d.setColor(Color.black);
            g2d.fillRect(0, 0, 800, 800);

            //Draws Queen
            //g2d.setColor(new Color (250, 0, 0));
            //g2d.fillOval(385, 391, 28, 28);

            //Draws striker
            g2d.setColor(new Color (50, 240, 75));
            g2d.fillOval(striker.getXPos() - Striker.HALFWIDTH, striker.getYPos() - Striker.HALFHEIGHT, Striker.HALFWIDTH*2, Striker.HALFHEIGHT*2);

        
            //Draws the rest of the 18 coins
            //for(Coin coin : Collision.coinList){

            //g2d.setColor(coin.getColor());
            //g2d.fillOval(coin.getXPos() - Coin.WIDTH/2, coin.getYPos() - Coin.HEIGHT/2, Coin.WIDTH, Coin.HEIGHT);

            
            g2d.setColor(new Color (50, 240, 75));
            g2d.fillRect(striker.sPoint.x-5, striker.sPoint.y-5, 10, 10);

            if(isAiming || isGrowing)
            {
                if (isAiming)
                {
                    direction = new Vector(mouseX-striker.getXPos(),mouseY-striker.getYPos());
                    if(direction.getY() < 0)
                    {
                        direction = direction.scaledBy(-1);
                    }
                    direction = direction.scaledBy(1.0/direction.getMagnitude());
                }
                magnitude = 1;
                if (isGrowing)
                {
                    //end = System.currentTimeMillis();
                    magnitude = getTime() - startTime;
                    int sign = -1*((((int)(magnitude/MAX_MAGNITUDE)) % 2) * 2 - 1);
                    if (sign == -1)
                    {
                        magnitude = MAX_MAGNITUDE - (magnitude % MAX_MAGNITUDE);
                    }
                    else 
                    {
                        magnitude = magnitude % MAX_MAGNITUDE;
                    }
                }

                AffineTransform at = new AffineTransform();
                at.translate(striker.getXPos(), striker.getYPos());
                at.rotate(direction.getX(), direction.getY());
                at.scale(magnitude, 1);
                at.rotate(0,-1);
                at.translate(-striker.getXPos(), -striker.getYPos());
                at.translate(oX - (arrow.xpoints[0] - striker.getXPos()), oY - (arrow.ypoints[0] - striker.getYPos()));

                AffineTransform save = g2d.getTransform();

                g2d.transform(at);
                g2d.setColor(Color.blue);
                g2d.fillPolygon(arrow);

                g2d.setTransform(save);

            } 
            
            // Set to true once striker is fired
            if (hasShot)
            {
                striker.sPoint.x += striker.xVel;
                striker.sPoint.y += striker.yVel;
                if ((striker.sPoint.x <= 18) || (striker.sPoint.y <= 77) || (striker.sPoint.x >= 728) || (striker.sPoint.y >= 728))
                {
                    hasShot = false;
                }            
            }
            // Modifies the striker's velocity vector in the event
            // that it collides with a wall
            else
            {
                collisionStrikerWall();
                striker.sPoint.x += striker.xVel;
                striker.sPoint.y += striker.yVel;
                hasShot = true;                
            }
            
            // Key Demo - Translates striker
            if (strikerShifted)
            {
                if (whichKey.equals("LEFT_ARROW"))
                {
                    striker.sPoint.x -= 6;
                    if (striker.getXPos() < 193)
                    {
                        striker.sPoint.x = 193;
                    }
                }
                if (whichKey.equals("RIGHT_ARROW"))
                {
                    striker.sPoint.x += 6;
                    if (striker.getXPos() > 603)
                    {
                        striker.sPoint.x = 603;
                    }
                }
            }

            // Loops through coinList to check if Coin 
            // objects collide with each other, and swaps
            // velocity and color if they do
            for (int rep = 0; rep < Collision.coinList.size(); rep++){
                Coin a = Collision.coinList.get(rep);
                Coin otherCoin = new Coin();    
                boolean colliding = false;
                
                for (int sub = 0; sub < Collision.coinList.size(); sub++)
                {
                    if (rep != sub){
                        colliding = Detection.collisionDetectorCoinCoin(a, Collision.coinList.get(sub));                       
                    }
                    if (colliding){
                        //rebound(a, Collision.coinList.get(sub));
                        // note the coin that it is colliding with
                        otherCoin = Collision.coinList.get(sub);
                        break;
                    }                    
                }                                    
                a.setIsColliding(colliding);               
                if (a.getIsColliding()){                                          
                     rebound(a, otherCoin);    
                }
                a.updatePosition(a.xVel, a.yVel);
                                
                collisionCoinWall(a);
                               
                a.updatePosition(a.xVel, a.yVel);
                a.draw(g2d);
            }
        }
    }

    
    public void mouseClicked(MouseEvent e)
    {
        System.out.println();
        System.out.println("X-Coord" + ":" + e.getX());
        System.out.println("Y-Coord" + ":" + e.getY());
        System.out.println();
        if(((e.getX() - (striker.getXPos())) * 
            (e.getX() - (striker.getXPos()))) + 
        ((e.getY() - (striker.getYPos())) * 
            (e.getY() - (striker.getYPos())))
        <= (Striker.HALFWIDTH) * (Striker.HALFWIDTH))
        {
            newShot = true;
            repaint();
        }
    }

    public void mouseReleased(MouseEvent e)
    {        
        if(e.getButton() == MouseEvent.BUTTON1)
        {
            mouseDown = false;
            end = System.currentTimeMillis();
            System.out.println("Mouse held for: " + (getTime() - startTime) + " seconds.");
        }
        //System.out.println("\f");
    }
    
    public void mousePressed(MouseEvent e)
    {
        if(e.getButton() == MouseEvent.BUTTON1)
        {
            mouseDown = true;
            mouseX = e.getX();
            mouseY = e.getY();
            repaint();            
        }

        if(e.getButton() == MouseEvent.BUTTON1)
        {
            if(!isAiming && !isGrowing)
            {
                mouseX = e.getX();
                mouseY = e.getY();
                isAiming = true;
            }

            else if(isAiming && !isGrowing)
            {
                //startTime = mousePressTime();
                startTime = getTime();
                isAiming = false;
                isGrowing = true;
            } 
            else if(!isAiming && isGrowing)
            {
                isGrowing = false;
                hasShot = true;
                strikerDirection = direction.scaledBy(-magnitude);
                striker.xVel = strikerDirection.getX();
                striker.yVel = strikerDirection.getY();
            }
        }
    }

    public double getTime()
    {
        return System.nanoTime()/1_000_000_000.0;
    }

    public void keyPressed(KeyEvent e)
    { 
        keyHeldDown = true;
        lastKey = e;
        if ( lastKey.getKeyCode() == KeyEvent.VK_LEFT )
        {
            strikerShifted = true;
            whichKey = "LEFT_ARROW";
            if (striker.sPoint.x == 196)
            {
                strikerLeftBound = true;
            }
        }
        else if ( lastKey.getKeyCode() == KeyEvent.VK_RIGHT )
        {
            strikerShifted = true;
            whichKey = "RIGHT_ARROW";
            if (striker.sPoint.x == 603)
            {
                strikerRightBound = true;
            }
        }
        else
        {
            System.out.println("Invalid key. Try again.");
        }
        repaint();
    }

    public void keyReleased(KeyEvent e)
    { 
        keyHeldDown = false; 
        strikerShifted = false;
    }

    @Override
    public void keyTyped(KeyEvent e)
    {

    }

    public void mouseDragged(MouseEvent e)
    {
        mouseX = e.getX();
        mouseY = e.getY();
        repaint();
    }

    public void mouseMoved(MouseEvent e)
    {
        mouseX = e.getX();
        mouseY = e.getY();
        repaint();
    }

    @Override
    public void mouseEntered(MouseEvent e)
    {

    }

    @Override
    public void mouseExited(MouseEvent e)
    {

    }

    public static void collisionStrikerWall()
    {
        int x = striker.sPoint.x;
        int y = striker.sPoint.y;

        if (x <= 18)
        {
            striker.xVel = -1 * striker.xVel;
        }

        else if (y >= 782)
        {
            striker.yVel = -1 * striker.yVel;
        }

        else if (x >= 782)
        {
            striker.xVel = -1 * striker.xVel;
        }

        else if (y <= 77)
        {
            striker.yVel = -1 * striker.yVel;
        }
    }

    public static void collisionCoinWall(Coin a)
    {
        double x = a.getXPos();
        double y = a.getYPos();

        if (x <= -30)
        {
            a.xVel = -1 * a.xVel;
        }

        else if (y <= -30)
        {
            a.yVel = -1 * a.yVel;
        }

        else if (x >= 800)
        {
            a.xVel = -1 * a.xVel;
        }

        else if (y >= 800)
        {
            a.yVel = -1 * a.yVel;
        }
    }

    public static void collisionCoinCoin(Coin a, Coin b)
    {       
        Vector normal = new Vector (a.getXPos() - b.getXPos(), a.getYPos() - b.getYPos());
        normal = normal.scaledBy(1.0/normal.getMagnitude());

        Vector tangent = new Vector (-normal.getY(), normal.getX());

        Vector initialAVel = new Vector (a.getXVel(), a.getYVel());
        Vector initialBVel = new Vector (b.getXVel(), b.getYVel());

        double initialTangentialAVel = Vector.getDotProduct(tangent, initialAVel);
        double initialTangentialBVel = Vector.getDotProduct(tangent, initialBVel);

        double initialNormalAVel= Vector.getDotProduct(normal, initialAVel);
        double initialNormalBVel= Vector.getDotProduct(normal, initialBVel);

        double finalTangentialAVel = initialTangentialAVel;
        double finalTangentialBVel = initialTangentialBVel;

        double finalNormalAVel= ((initialNormalAVel * (a.MASS - b.MASS)) + (2 * (b.MASS * initialNormalBVel)))/(a.MASS + b.MASS);
        double finalNormalBVel= ((initialNormalBVel * (b.MASS - a.MASS)) + (2 * (a.MASS * initialNormalAVel)))/(a.MASS + b.MASS);

        Vector finalAVel = new Vector(normal.scaledBy(finalNormalAVel));
        Vector finalBVel = new Vector(normal.scaledBy(finalNormalBVel));

        a.setXVel(finalAVel.getX());
        a.setYVel(finalAVel.getY());

        b.setXVel(finalBVel.getX());
        b.setYVel(finalBVel.getY());
    }

    public static void collisionStrikerCoin(Striker s, Coin a)
    {
        Vector normal = new Vector (a.getXPos() - s.getXPos(), a.getYPos() - s.getYPos());
        normal = normal.scaledBy(1.0/normal.getMagnitude());

        Vector tangent = new Vector (-normal.getY(), normal.getX());

        Vector initialAVel = new Vector (a.getXVel(), a.getYVel());
        Vector initialSVel = new Vector (s.getXVel(), s.getYVel());

        double initialTangentialAVel = Vector.getDotProduct(tangent, initialAVel);
        double initialTangentialSVel = Vector.getDotProduct(tangent, initialSVel);

        double initialNormalAVel= Vector.getDotProduct(normal, initialAVel);
        double initialNormalSVel= Vector.getDotProduct(normal, initialSVel);

        double finalTangentialAVel = initialTangentialAVel;
        double finalTangentialSVel = initialTangentialSVel;

        double finalNormalAVel= ((initialNormalAVel * (a.MASS - s.MASS)) + (2 * s.MASS * initialNormalSVel))/(a.MASS + s.MASS);
        double finalNormalSVel= ((initialNormalSVel * (s.MASS - a.MASS)) + (2 * (a.MASS * initialNormalAVel)))/(a.MASS + s.MASS);

        Vector finalAVel = new Vector(normal.scaledBy(finalNormalAVel));
        Vector finalSVel = new Vector(normal.scaledBy(finalNormalSVel));

        a.setXVel(finalAVel.getX());
        a.setYVel(finalAVel.getY());

        s.setXVel(finalSVel.getX());
        s.setYVel(finalSVel.getY());
    }
    // Swaps velocities of the colliding Coin objects
    public void rebound(Coin a, Coin b)
    {
        double bx = b.getXVel();
        double by = b.getYVel();

        Color bColor = b.getColor();

        b.setXVel(a.getXVel());
        b.setYVel(a.getYVel());

        a.setXVel(bx);
        a.setYVel(by);

        b.setColor(a.getColor());
        a.setColor(bColor);
    }
}
