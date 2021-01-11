/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekt;
import java.awt.Font;
import javax.swing.JTextPane;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import javax.swing.JPanel;
import java.awt.Insets;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.*;

/**
 * Panel graficzny wyświetlający teskt pomocy oraz wynik końcowy
 */
public class Wysw_tekstu extends JPanel {
    
    private Font czcionka=new Font("URW Chancery L", Font.BOLD, 16);
    private String pomoc_text;
    private Insets ins;
    private Path path;
    private JTextPane textPane;
    private StyledDocument doc;
    
    private SimpleAttributeSet left;
    private SimpleAttributeSet center;
    
    
    
    
    /**metoda wyświetlająca pomoc lub wynik końcowy
     @param nazwa_pomoc nazwa okna pomocy
     @param czy_wyjscie zmienna określająca, czy wyświetlić wynik końcowy*/
    public Wysw_tekstu(String nazwa_pomoc,boolean czy_wyjscie){
        
        textPane= new JTextPane();
        textPane.setSize(Zasoby.szerokosc_okna, Zasoby.wysokosc_okna);
        textPane.setEditable(false);
        textPane.setFont(czcionka);
        ins=new Insets(90,90,90,90);

        textPane.setMargin(ins);
        textPane.setBounds(0,0,Zasoby.szerokosc_okna,Zasoby.wysokosc_okna);
        doc = textPane.getStyledDocument();
  
        left = new SimpleAttributeSet();
        center = new SimpleAttributeSet();

        StyleConstants.setAlignment(left, StyleConstants.ALIGN_LEFT);
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);

        if(!czy_wyjscie){
            
             try{
                 path=Path.of("src/res/"+nazwa_pomoc);
                 pomoc_text=Files.readString(path);
             }

             catch(IOException e){
                     System.out.println("error"); 
             }

        }

        setVisible(true);
        this.add(textPane);
 
    }
    
    
    
    
    /**metoda wyświetlająca pomoc*/
    void wys_pomoc(){
        try {
            doc.insertString(doc.getLength(), "POMOC\n",  center);
            doc.setParagraphAttributes(0, doc.getLength(), center, false);
            doc.insertString(doc.getLength(), pomoc_text,  left);
            } 
       
            catch (BadLocationException ble) {
                 System.err.println("Couldn't insert initial text into text pane.");
            }
    }
    
    
    
    
    /**metoda wyświetlająca wynik końcowy*/
    void wys_wyniki(){
        pomoc_text=Zasoby.wyjscie_text;
              try {
               doc.remove(0, doc.getLength());
               doc.insertString(doc.getLength(), "WYNIKI\n",  center);
               doc.setParagraphAttributes(0, doc.getLength(), center, false);
               doc.insertString(doc.getLength(), pomoc_text,  left);
               } 

               catch (BadLocationException ble) {
                    System.err.println("Couldn't insert initial text into text pane.");
               }
        }
}
