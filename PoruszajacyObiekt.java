/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekt;
import java.awt.*; 
/**
 * Klasa obiektów bedącymi narzędziami do ponierania składników
 */
public class PoruszajacyObiekt {
    
    /**Współrzędna x początku pojeminka ze składnikiem*/
    public int x;
    /**Współrzędna y początku pojeminka ze składnikiem*/
    public int y;
    /**szerokość obrazu narzędzia*/
    public int szerokosc;
    /**wysokość obrazu narzędzia*/
    public int wysokosc;
    /**ilość składników na stole*/
    public int il_sk;
    /**zmienna stanu określająca, czy narzędzie jest podniesione*/
    public boolean wybrane;
     /**zmienna stanu określająca, czy narzędzie ma w sobie składnik*/
    public boolean zajete;
    /** tablica zmiennych stanu określająca, który składnik jest 
     * podniesiony przez narzędzie*/
    public boolean klik [];
    /**nazwa składnika */
    public String nazwa;
    /** granice obszaru podnoszenia narzędzia*/
    public Rectangle granice=new Rectangle();
    /** granice obszaru wycinania tekstury narzędzia*/
    public Polygon polygon=new Polygon();
    /** pumkt, w którym znajduje się miejsce pobierania składników*/
    public Point koncowka=new Point();
    /**
     * Konstruktor - ustawienie parametrów narzędzi
     * @param x Współrzędna x pojemnika ze składnikiem 
     * @param y Współrzędna y pojemnika ze składnikiem 
     * @param nazwa zmienna okreslająca, nazwe narzedzia 
     * @param wybrane zmienna stanu okreslająca, czy składnik jest podniesiony
     * @param il_sk ilość składników na stole
     */
    
    public PoruszajacyObiekt(int x, int y, boolean wybrane,String nazwa,int il_sk){
         
        this.klik=new boolean [il_sk];
        this.x=x;
        this.y=y;
        this.nazwa=nazwa;
        this.wybrane=wybrane;
        this.zajete=false;
        
    }
    
}
