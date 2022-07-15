// Varun Patro


import javax.swing.*;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.Font;
import java.awt.Color;

public class Collision 
{
    //public static Coin redCoin = new Coin (385, 392, new Color (250, 0, 0));
    //public static Coin [] brownCoins = new Coin [9];
    //public static Coin [] blackCoins = new Coin [9];

    public static ArrayList <Coin> coinList = new ArrayList <Coin> (19);
    public static boolean on = true;
    public static void main (String [] args) throws InterruptedException
    {
        
        JFrame window = new JFrame("PARTICLE COLLISIONS"); // caption at the top
        window.setVisible(true); // displays frame
        window.setResizable(false); // prevent user from resizing
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // This will be the area in which the simulation is displayed
        Board b = new Board();
        b.setPreferredSize(new Dimension (800,800));
        window.add(b);
        window.pack();

        
        b.setFocusable(true);
        b.addKeyListener(b);
        b.requestFocusInWindow();
        b.requestFocus();
        b.addMouseListener(b);
        b.addMouseMotionListener(b);
        JButton startButton = new JButton("CLICK ANYWHERE TO BEGIN");
        Font f = new Font ("Calibri", Font.BOLD, 36);
        
        while (on)
        {
            //button.setBackground(Color.blue);
            startButton.setBounds(0, 0, 799, 799);
            startButton.setActionCommand("START");
            startButton.addActionListener(new Button());
            startButton.setForeground(Color.white);
            startButton.setOpaque(true);
            startButton.setBorderPainted(false);
            startButton.setBackground(new Color(100, 175, 250));
            startButton.setFont(f);
            b.add(startButton);
            b.repaint();
        }
        b.remove(startButton);
        
        Font j = new Font ("Calibri", Font.BOLD, 26);
        JButton restartButton = new JButton("RESTART SIMULATION");
        restartButton.setBounds(140, 8, 350, 46);
        restartButton.setActionCommand("RESTART");
        restartButton.addActionListener(new Button());
        restartButton.setForeground(Color.white);
        restartButton.setOpaque(true);
        restartButton.setBorderPainted(false);
        restartButton.setBackground(Color.BLUE);
        restartButton.setFont(j);
        b.add(restartButton);

        //fillCoins();
        Thread.sleep(100);
        while (true)
        {
            b.update();
            Thread.sleep(20);
        }        

    }

    public static void fillCoins()
    {
        /*coinList.add(new Coin (385, 420, "Brown"));
        coinList.add(new Coin (385, 449, "Brown"));
        coinList.add(new Coin (436, 424, "Brown"));
        coinList.add(new Coin (436, 367, "Brown"));
        coinList.add(new Coin (335, 424, "Brown"));
        coinList.add(new Coin (335, 367, "Brown"));
        coinList.add(new Coin (360, 380, "Brown"));
        coinList.add(new Coin (411, 380, "Brown"));
        coinList.add(new Coin (385, 335, "Brown"));

        coinList.add(new Coin (411, 438, "Blue"));
        coinList.add(new Coin (411, 409, "Blue"));
        coinList.add(new Coin (436, 396, "Blue"));
        coinList.add(new Coin (360, 409, "Blue"));
        coinList.add(new Coin (360, 438, "Blue"));
        coinList.add(new Coin (335, 396, "Blue"));
        coinList.add(new Coin (385, 363, "Blue"));
        coinList.add(new Coin (411, 351, "Blue"));
        coinList.add(new Coin (360, 351, "Blue"));

        for(Coin c : coinList){

        c.setPosition(new Position(c.getXPos() + Coin.WIDTH/2, c.getYPos() + Coin.HEIGHT/2 ));

        }*/

    }

}
