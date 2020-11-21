/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekt;

/**
 *
 * @author Mac
 */
public class Obiekt {
     int x=0,y=0;
     int w=0,h=0;
     int[] wieszcholki_x= new int[4];
     int[] wieszcholki_y=new int[4];
     
    public Obiekt( int x,int y,int w,int h){
        this.x=x;
        this.y=y;
        this.w=w;
        this.h=h;
        
        wieszcholki_x[0]=x;
        wieszcholki_y[0]=y;
        
        wieszcholki_x[1]=x+w;
        wieszcholki_y[1]=y;
        
        wieszcholki_x[2]=x;
        wieszcholki_y[2]=y+h;
        
        wieszcholki_x[3]=x+w;
        wieszcholki_y[3]=y+h;
        
        
        
        
    }
    
}
