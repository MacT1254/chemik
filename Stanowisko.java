/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekt;

/**
 * Klasa przechowująca punkty rozpoczęcia gry, resetu oraz wyjścia
 */
public class Stanowisko {
     /** Współrzędna x stanowiska */
    public int run_point_x;
    /** Współrzędna y stanowiska */
    public int run_point_y;
    /**
     * Konstruktor - ustawienie współrzędnych stanowiska
     * @param run_point_x  współrzędna x
     * @param run_point_y  współrzędna y
     */
    Stanowisko(int run_point_x, int run_point_y){
        
        this.run_point_x=run_point_x;
        this.run_point_y=run_point_y;
    }
}
    
        
        
