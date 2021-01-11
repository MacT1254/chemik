/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekt;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.lang.Exception;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.JTextArea ;
import javax.swing.JFrame;
import java.awt.Insets;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.*;

/**
 *
 * @author Mac
 */
public class Pomoc extends JPanel {
    Font czcinka=new Font("URW Chancery L", Font.BOLD, 16);
    String pomoc;

    Path path;
    
    public Pomoc(String nazwa_pomoc){
       
       try{
                path=Path.of("src/res/"+nazwa_pomoc);
                pomoc=Files.readString(path);
       }
       
       catch(IOException e){
               System.out.println("error"); 
       }

       
       JTextPane textPane = new JTextPane();
       textPane.setSize(Tlo.szerokosc_okna, Tlo.wysokosc_okna);
       textPane.setEditable(false);
       textPane.setFont(czcinka);

       Insets n =new Insets(90,90,90,90);
       textPane.setMargin(n);
       textPane.setBounds(0,0,Tlo.szerokosc_okna,Tlo.wysokosc_okna);
       StyledDocument doc = textPane.getStyledDocument();
       
       SimpleAttributeSet left = new SimpleAttributeSet();
       SimpleAttributeSet center = new SimpleAttributeSet();
       
       StyleConstants.setAlignment(left, StyleConstants.ALIGN_LEFT);
       StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
       
       
       try {
            doc.insertString(doc.getLength(), "POMOC\n",  center);
            doc.setParagraphAttributes(0, doc.getLength(), center, false);
            doc.insertString(doc.getLength(), pomoc,  left);
       } 
       
       catch (BadLocationException ble) {
            System.err.println("Couldn't insert initial text into text pane.");
       }
       
       setVisible(true);
       this.add(textPane);
       
    }
    
  
}
