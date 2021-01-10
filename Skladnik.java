package projekt;

import java.awt.Rectangle;
import java.awt.Color;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Mac
 */
public class Skladnik{
    int x,y,xs,ys;
    String podnosi;
    String nazwa;
    boolean wybrane;
    Color kolor;
    boolean w_poj;
    Rectangle granice;
    
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
