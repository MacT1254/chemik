/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekt;
import java.lang.String;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.*;
import java.awt.event.*;
import java.awt.*;
import java.util.ArrayList;
import java.awt.image.BufferedImage;


public class Wzory extends JPanel {
    
    int szerokosc_wz=50;
    int wysokosc_wz=20;
    int pozycja_wz_x1=312;
    int pozycja_wz_x2=388;
    int pozycja_wz_x3=222;
    int pozycja_wz1_y=63;
    int pozycja_wz2_y=92;
    int pozycja_wz3_y=128;
    
    String Wzor1="H20 --> H2 +";
    String Wzor2="MgO+H2CO3 --> MgCO3 +";
    String Wzor3="+ H2SO4 --> K2SO4 + H20";
    String Wzor4="2K + 2H20 --> + ";
    
    Wzory(){
        
        JTextField odp1,odp2,odp3;
        odp1 = new JTextField();
        odp2 = new JTextField();
        odp3 = new JTextField();
        odp1.setBounds(pozycja_wz_x1,pozycja_wz1_y,szerokosc_wz,wysokosc_wz);
        odp2.setBounds(pozycja_wz_x2,pozycja_wz2_y,szerokosc_wz,wysokosc_wz);
        odp3.setBounds(pozycja_wz_x3,pozycja_wz3_y,szerokosc_wz,wysokosc_wz);
        
        /*try{
            BufferedImage myImage = ImageIO.read(new File("src/res/tlo1.jpg"));
            okno_wz.setContentPane(new Tlo(myImage));
        }
            
        catch(IOException e){
            System.out.println("error");
        }
        */
        
        
        setLayout(null);
        add(odp1);
        add(odp2);
        add(odp3);
        setVisible(true);
        
   }
 //wczytywanie grafiki 

 //sprawdzanie tekstu
 //wyświetlanie wyników
    
    
    
    boolean spr_wzoru(String a,String b){
            if(a.equals(b)) return true;
            else return false;
    }
    @Override
    protected void paintComponent(Graphics g){
        int zmx=nowetlo.mando.srodek_x;
        int zmy=nowetlo.mando.srodek_y;
        zmx=nowetlo.mando.srodek_x-nowetlo.mando.srednica_ciala/2;
        zmy=nowetlo.mando.srodek_y-nowetlo.mando.srednica_ciala/2;
        Graphics2D g2 = (Graphics2D) g;
        
        g2.drawImage(nowetlo.tlo0,0,0,null);
            
}
