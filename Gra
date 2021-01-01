/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package projekt;

/**
 *
 * @author Mac
 */
import javax.swing.*;

import java.awt.event.*;
import java.awt.*;
import java.util.ArrayList;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
 
public class Gra extends JPanel
{  
    BufferedImage buf_zdj;
    Tlo nowetlo= new Tlo();
   
public Gra (Tlo n){  
  
   nowetlo=n;   
   System.out.println();
  }  
    
   
   @Override
    protected void paintComponent(Graphics g){
        int zmx=nowetlo.mando.srodek_x;
        int zmy=nowetlo.mando.srodek_y;
        zmx=nowetlo.mando.srodek_x-nowetlo.mando.srednica_ciala/2;
        zmy=nowetlo.mando.srodek_y-nowetlo.mando.srednica_ciala/2;
        Graphics2D g2 = (Graphics2D) g;
        
        g2.drawImage(nowetlo.tlo0,0,0,null);
        //g2.drawImage(nowetlo.obrazpos,nowetlo.mando.srodek_x,nowetlo.mando.srodek_y,null);
        TexturePaint pos = new TexturePaint(nowetlo.obrazpos, new Rectangle(zmx, zmy, 98, 105));
        g2.setPaint(pos);
        g.fillOval(zmx,zmy,nowetlo.mando.srednica_ciala,nowetlo.mando.srednica_ciala);
        g.setColor(Color.RED);
        g2.drawRect(nowetlo.lista_obiekt.get(0).x,nowetlo.lista_obiekt.get(0).y,nowetlo.lista_obiekt.get(0).w,nowetlo.lista_obiekt.get(0).h );
        g2.drawRect(nowetlo.lista_obiekt.get(1).x,nowetlo.lista_obiekt.get(1).y,nowetlo.lista_obiekt.get(1).w,nowetlo.lista_obiekt.get(1).h );
        g2.drawRect(nowetlo.lista_obiekt.get(2).x,nowetlo.lista_obiekt.get(2).y,nowetlo.lista_obiekt.get(2).w,nowetlo.lista_obiekt.get(2).h );
        //g2.drawRect(nowetlo.lista_obiekt.get(0).x,nowetlo.lista_obiekt.get(0).y,nowetlo.lista_obiekt.get(0).w,nowetlo.lista_obiekt.get(0).h );
        g.setColor(Color.BLACK);
        //Ellipse2D.Double circle = new Ellipse2D.Double(nowetlo.mando.srodek_x, nowetlo.mando.srodek_y, nowetlo.mando.srednica_ciala, nowetlo.mando.srednica_ciala);
        
        
    }
    

}
 /* try{
           okno_gr.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("src/res/tlo0.jpg")))));
    }
            
    catch(IOException e){
            System.out.println("error_tlo0");
    }
    */
/* public void paint(Graphics g) {
           
           
        Graphics2D g2 = (Graphics2D) g;
        
        g2.setColor(Color.white);                   //tło
        g2.fillRect(0,0, 1024, 768);
        
        g2.setColor(Color.black);
        g2.drawRect(nowetlo.lista_obiekt.get(0).x,nowetlo.lista_obiekt.get(0).y,nowetlo.lista_obiekt.get(0).w,nowetlo.lista_obiekt.get(0).h );         //stanowisko do wypełniania formuł chemicznych
        g2.drawRect(nowetlo.lista_obiekt.get(3).x,nowetlo.lista_obiekt.get(3).y,nowetlo.lista_obiekt.get(3).w,nowetlo.lista_obiekt.get(3).h );   
            
        
        //rysowanie chemika
        drawCenteredCircle(g2,mando.srodek_x, mando.srodek_y, Postac.srednica_ciala);
        drawCenteredCircle(g2,mando.srodek_x, mando.srodek_y-Postac.odleglosc_glowy_od_srodka, Postac.srednica_glowy);
        drawCenteredCircle(g2,1,2,3);

        for (int l=0; l<3;l++){
              drawCenteredCircle(g2,mando.srodek_x, mando.srodek_y+l*Postac.odstep_guzikow, Postac.srednica_guzika); 
        }
          
        
   
    }
    */


     
     
