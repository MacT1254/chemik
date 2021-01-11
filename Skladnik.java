package projekt;

import java.awt.Rectangle;
import java.awt.Color;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *Klasa obiektu składnik zawierająca w sobie dane o miejscu pobierania, granicach miejsca pobierania, oraz zmiennych
 * potrzebnych do obsługi podnoszenia i opuszczania składników 
 */
public class Skladnik{
    /**Współrzędna x początku pojeminka ze składnikiem*/
    public int x;
    /**Współrzędna y początku pojeminka ze składnikiem*/
    public int y;
    /**Współrzędna x miejsca opuszczenia składnika*/
    public int xs;
    /**Współrzędna y miejsca opuszczenia składnika*/
    public int ys;
    /**Zmienna okreslająca, co podnosi dany składnik */
    public String podnosi;
    /**nazwa składnika */
    public String nazwa;
    /** zmienna stanu okreslająca, czy składnik jest podniesiony*/
    public boolean wybrane;
    /**kolor składnika*/
    public Color kolor;
    /** zmienna stanu okreslająca, czy składnik jest w pojemniku*/
    public boolean w_poj;
    /** granice obszaru pobierania składnika*/
    public Rectangle granice;
    /**
     * Konstruktor - ustawienie parametrów składników
     * @param x Współrzędna x pojemnika ze składnikiem 
     * @param y Współrzędna y pojemnika ze składnikiem 
     * @param podnosi zmienna okreslająca, co podnosi dany składnik 
     * @param wybrane zmienna stanu okreslająca, czy składnik jest podniesiony
     * @param nazwa nazwa składnika
     * @param kolor kolor składnika
     */
    
    public Skladnik(int x, int y, String podnosi,boolean wybrane, String nazwa ,Color kolor ){
        
        this.x=x;
        this.y=y;
        this.xs=0;
        this.ys=0;
        this.podnosi=podnosi;
        this.wybrane=wybrane;
        this.nazwa=nazwa;
        this.kolor=kolor;
        granice=new Rectangle();
        w_poj=false;
    }
}
