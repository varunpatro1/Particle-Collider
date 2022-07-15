// Varun Patro

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.ImageIcon.*;
import java.awt.Font.*;
import javax.swing.*;
public class Button implements ActionListener{
    public void actionPerformed(ActionEvent e){
        if (("START").equals(e.getActionCommand())){
            Board.startScreen = 1;
            Collision.on = false;
        }
        if (("RESTART").equals(e.getActionCommand())){
            //Board.boardRedrawn = true;
            //for(Coin coin : Collision.coinList)
            //{
                           
                //g2d.setColor(coin.getColor());
                //g2d.fillOval(coin.defaultXPos - Coin.WIDTH/2, coin.defaultYPos - Coin.HEIGHT/2, Coin.WIDTH, Coin.HEIGHT);
            
            //}
            Board.setCoins();
        }
    }
}
