/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekt;
import javax.swing.*;
import java.awt.*;


public class Wzory extends JPanel {
    
    int odchylka_x=0;
    int odchylka_y=0;
    
    int szerokosc_wz=50;
    int wysokosc_wz=20;
   
    int pozycja_wz1_x=282+odchylka_x;
    int pozycja_wz1_y=187+odchylka_y;
    
    int pozycja_wz2_x=358+odchylka_x;
    int pozycja_wz2_y=218+odchylka_y;
    
    int pozycja_wz3_x=192+odchylka_x;
    int pozycja_wz3_y=254+odchylka_y;
    
    int pozycja_wz4_x=257+odchylka_x;
    int pozycja_wz4_y=285+odchylka_y;
    
    
    String str_odp[]=new String [4];
    String rozw[]=new String [4];
    

    String Wzor1="H20 --> H2 +";
    String Wzor2="MgO+H2CO3 --> MgCO3 +";
    String Wzor3="+ H2SO4 --> K2SO4 + H20";
    String Wzor4="2K + 2H20 --> + ";
    JTextField odp1 = new JTextField();
    JTextField odp2 = new JTextField();
    JTextField odp3 = new JTextField();
    JTextField odp4 = new JTextField();
    
    
    Wzory(){
        
        rozw[0]="O2";
        rozw[1]="H2O";
        rozw[2]="K2O";
        rozw[3]="NaO";

        odp1.setBounds(pozycja_wz1_x,pozycja_wz1_y,szerokosc_wz,wysokosc_wz);
        odp2.setBounds(pozycja_wz2_x,pozycja_wz2_y,szerokosc_wz,wysokosc_wz);
        odp3.setBounds(pozycja_wz3_x,pozycja_wz3_y,szerokosc_wz,wysokosc_wz);
        odp4.setBounds(pozycja_wz4_x,pozycja_wz4_y,szerokosc_wz,wysokosc_wz);
 
        setLayout(null);
        
        add(odp1);
        add(odp2);
        add(odp3);
        add(odp4);
        setVisible(true);
          
        
   }
    
    

    
    
    int zatwierdz(){
        
        int suma=0;
        str_odp[0]=odp1.getText();
        str_odp[1]=odp2.getText();
        str_odp[2]=odp3.getText();
        str_odp[3]=odp4.getText();
        
        for(int n=0;(n<str_odp.length && n<rozw.length);n++){
            if(str_odp[n].equals(rozw[n])){
                suma++;
                System.out.println("roz "+n);
            }
            
        }
        System.out.println("error "+suma);
        return suma;
    }
    
    
    
    
    
    @Override
    protected void paintComponent(Graphics g){
        
        Graphics2D g2 = (Graphics2D) g;
        
        g2.drawImage(Tlo.tlo1,0,0,null);
    }        
}
