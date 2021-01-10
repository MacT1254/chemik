/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekt;
import java.awt.*; 
/**
 *
 * @author Mac
 */
public class PoruszajacyObiekt {
    
    int x,y,szerokosc,wysokosc;
    boolean wybrane;
    String nazwa;
    Rectangle granice=new Rectangle();
    Polygon polygon=new Polygon();
    Point koncowka=new Point();
    
    
     public PoruszajacyObiekt(int x, int y, boolean wybrane,String nazwa){
        this.x=x;
        this.y=y;
        this.nazwa=nazwa;
        this.wybrane=wybrane;
        
    }
    
}
