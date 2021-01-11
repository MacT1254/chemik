/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekt;


/**
     * Klasa obiektów  Stol, ktory ma granice i początek układu współrzędnych 
     * 
*/
public class Obiekt {
     /** Współrzędna x stolu */
     public int x=0;
     /** Współrzędna y stolu */
     public int y=0;
     /** Szerokość stolu */
     public int w=0;
     /** Wysokość stolu */
     public int h=0;
     /**Współrzędne x wieszchołków - 0-lewy gorny róg, 1-prawy gorny róg, 2-lewy dolny róg */
     public int[] p_x= new int[4];
     /**Współrzędne y wieszchołków */
     public int[] p_y=new int[4];
     /**
     * Konstruktor - ustawienie parametrów obiekty
     * @param x Współrzędne x wieszchołków
     * @param y Współrzędne y wieszchołków
     * @param w szerokość obiektu
     * @param h wysokość obiektu
     */
    public Obiekt( int x,int y,int w,int h){
        this.x=x;
        this.y=y;
        this.w=w;
        this.h=h;
        
        p_x[0]=x;
        p_y[0]=y;
        
        p_x[1]=x+w;
        p_y[1]=y;
        
        p_x[2]=x;
        p_y[2]=y+h;
        
        p_x[3]=x+w;
        p_y[3]=y+h;
        
        
        
        
    }
    
}
