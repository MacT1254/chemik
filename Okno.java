/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekt;
import javax.swing.*;
import java.awt.*;

public class Okno extends JFrame{
    Okno(String nazwa){
        
        super(nazwa);
        setSize(Tlo.szerokosc_okna,Tlo.wysokosc_okna);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    
}
