/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package projekt;


import javax.swing.JPanel;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.TexturePaint;
import java.awt.Rectangle;
import java.awt.Color;

 /**
 Panel graficzny głównej gry
 */
public class Gra extends JPanel{
    
    private int zmx;
    private int zmy;
    private TexturePaint pos;
    private Font czcionka=new Font("URW Chancery L", Font.BOLD, 21);
   
    @Override
    protected void paintComponent(Graphics g){
            
        zmx=Postac.srodek_x-Postac.srednica_ciala/2;    //ustawianie punktu początku obrazu
        zmy=Postac.srodek_y-Postac.srednica_ciala/2;
        Graphics2D g2 = (Graphics2D) g;
        
        g.drawImage(Zasoby.tlo0,0,0,null);
      
        pos = new TexturePaint(Zasoby.postac, new Rectangle(zmx, zmy, 98, 105));
        g2.setPaint(pos);
        g.fillOval(zmx,zmy,Postac.srednica_ciala,Postac.srednica_ciala);

        g.setColor(Color.BLACK);
        g.setFont(czcionka);
        g.drawString("Punkty: "+Zasoby.sumapunkt+"/10",812,35);
        
        
    }
    

}
