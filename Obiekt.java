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
     int[] p_x= new int[4];
     int[] p_y=new int[4];
     
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
