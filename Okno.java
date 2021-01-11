/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekt;
import javax.swing.JFrame;

/**
 * Klasa obiektów okno, w której będą wyświetlane elementy graficzne
 */
public class Okno extends JFrame{
    /**
     * Konstruktor - ustawienie nazwy okna
     * @param nazwa nazwa okna
     */
    
    Okno(String nazwa){
        
        super(nazwa);
        setSize(Zasoby.szerokosc_okna,Zasoby.wysokosc_okna);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    
}
