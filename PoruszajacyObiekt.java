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
    
    int x,y;
    boolean lyzka;
    Rectangle granice=new Rectangle();
    Polygon polygon=new Polygon();
    
     public PoruszajacyObiekt(int x, int y, boolean lyzka){
        this.x=x;
        this.y=y;
        this.lyzka=lyzka;


    }
    
}
