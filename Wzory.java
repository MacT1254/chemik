/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekt;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;



 /**
 Panel graficzny mini gry "Wzory"
 */
public class Wzory extends JPanel {
    
    private int szerokosc_wz=110;   //szerokość pola do wpisywania wzoru
    private int wysokosc_wz=33;
   
    private int pozycja_wz1_x=542;  //punkt x pola do wpisywania wzoru
    private int pozycja_wz1_y=156;
    
    private int pozycja_wz2_x=542;
    private int pozycja_wz2_y=224;
    
    private int pozycja_wz3_x=100;
    private int pozycja_wz3_y=298;
    
    private int pozycja_wz4_x=542;
    private int pozycja_wz4_y=368;
    
    private Font czcionka=new Font("URW Chancery L", Font.ROMAN_BASELINE , 16);
    private String str_odp[]=new String [4];    //tablica łańcuchów z odpowiedziami
    private String rozw[]=new String [4];       //tablica łańcuchów z rozwiązaniami
    
    private JTextField odp1 = new JTextField();
    private JTextField odp2 = new JTextField();
    private JTextField odp3 = new JTextField();
    private JTextField odp4 = new JTextField();
    
    
    
    
    
    
    /**
     * Konstruktor - inicjalizowanie zmiennych
     */
    public Wzory(){
        
        rozw[0]="O";
        rozw[1]="H2O";
        rozw[2]="K2O";
        rozw[3]="NaO";

        odp1.setBounds(pozycja_wz1_x,pozycja_wz1_y,szerokosc_wz,wysokosc_wz);
        odp2.setBounds(pozycja_wz2_x,pozycja_wz2_y,szerokosc_wz,wysokosc_wz);
        odp3.setBounds(pozycja_wz3_x,pozycja_wz3_y,szerokosc_wz,wysokosc_wz);
        odp4.setBounds(pozycja_wz4_x,pozycja_wz4_y,szerokosc_wz,wysokosc_wz);
        
        odp1.setFont(czcionka);
        odp2.setFont(czcionka);
        odp3.setFont(czcionka);
        odp4.setFont(czcionka);
        
        setLayout(null);
        
        add(odp1);
        add(odp2);
        add(odp3);
        add(odp4);
        setVisible(true);
        requestFocusInWindow(true);
        
   }
    
    

    
   /** metoda zatwierdzająca wynik, liczaca punkty do oraz tworząca tekst z  wynikiem końcowym
      @return zwraca liczbe zdobytych punktów int*/
    int zatwierdz(){
        
        int suma=0;
        str_odp[0]=odp1.getText();
        str_odp[1]=odp2.getText();
        str_odp[2]=odp3.getText();
        str_odp[3]=odp4.getText();
        
        Zasoby.wyjscie_text+="\n\nWynik mini gry \"Wzory\"\n";
        
        for(int n=0;(n<str_odp.length && n<rozw.length);n++){
            
            int lp=n+1;
            Zasoby.wyjscie_text+="Wynik równania nr "+lp;
            
            if(str_odp[n].equals(rozw[n])){
                 Zasoby.wyjscie_text+=" jest prawidłowy  (+1 punkt)\n";
                suma++;
            }
            
            else{
                Zasoby.wyjscie_text+=" jest błędny\n";
            }
            
        }
        
        return suma;
    }
    
    
    
    
    
    @Override
    protected void paintComponent(Graphics g){
        
        Graphics2D g2 = (Graphics2D) g;
        
        g2.drawImage(Zasoby.tlo1,0,0,null);
    }        
}
