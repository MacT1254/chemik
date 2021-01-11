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


import java.awt.*;
import java.util.ArrayList;

 
public class Gra extends JPanel
{   int zmx=Postac.srodek_x;
    int zmy=Postac.srodek_y;
    
    Font czcinka=new Font("URW Chancery L", Font.BOLD, 21);
    ArrayList<Stanowisko> lista_stan = new ArrayList<>();
    ArrayList<Obiekt> lista_obiekt = new ArrayList<>();
   
public Gra (ArrayList<Obiekt> listaob,ArrayList<Stanowisko> listas){  
    
   lista_obiekt=listaob;
   lista_stan=listas;
   System.out.println();
   
  }  
    
   
   @Override
    protected void paintComponent(Graphics g){
            
        zmx=Postac.srodek_x-Postac.srednica_ciala/2;
        zmy=Postac.srodek_y-Postac.srednica_ciala/2;
        Graphics2D g2 = (Graphics2D) g;
        
        g.drawImage(Tlo.tlo0,0,0,null);
      
        TexturePaint pos = new TexturePaint(Tlo.postac, new Rectangle(zmx, zmy, 98, 105));
        g2.setPaint(pos);
        g.fillOval(zmx,zmy,Postac.srednica_ciala,Postac.srednica_ciala);
        g.setColor(Color.RED);
        g2.drawRect(lista_obiekt.get(0).x,lista_obiekt.get(0).y,lista_obiekt.get(0).w,lista_obiekt.get(0).h );
        g2.drawRect(lista_obiekt.get(1).x,lista_obiekt.get(1).y,lista_obiekt.get(1).w,lista_obiekt.get(1).h );
        g2.drawRect(lista_obiekt.get(2).x,lista_obiekt.get(2).y,lista_obiekt.get(2).w,lista_obiekt.get(2).h );
      
        g.setColor(Color.BLACK);
        
        System.out.println("ddasdapunkty");
        g.setFont(czcinka);
        g.drawString("Punkty: "+Tlo.sumapunkt+"/10",800,35);
        
        
    }
    

}
