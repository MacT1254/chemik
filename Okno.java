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
        setSize(1024,768);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
}
