/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekt;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.util.ArrayList;
import javax.swing.JPanel;




public class Zwiazki_chem extends JPanel{
    
    Polygon kszt_lyzki= new Polygon();
    Polygon kszt_pipety=new Polygon();
    Skladnik siarka=new Skladnik(0,0,true,false,"siarka");
    Skladnik wegiel=new Skladnik(0,0,true,false,"wegiel");
    Skladnik manganian=new Skladnik(0,0,true,false,"manganian");
    Skladnik NaOh=new Skladnik(0,0,true,false,"NaOh");
    Skladnik HCl=new Skladnik(0,0,false,false,"HCl");
    ArrayList<Skladnik> Substancje =new ArrayList<>();
    
    PoruszajacyObiekt lyzka = new PoruszajacyObiekt(0,0,false);
    PoruszajacyObiekt pipeta = new PoruszajacyObiekt(0,0,false);
    Rectangle [] granice_obiekt =new Rectangle [2];
    
    public Zwiazki_chem(){
        
        Substancje.add(siarka);
        Substancje.add(wegiel);
        Substancje.add(manganian);
        Substancje.add(NaOh);
        Substancje.add(HCl);
        
        for(Skladnik skl: Substancje){
            Rectangle prost =new Rectangle (skl.x,skl.y,120,80);
            skl.granice=prost;
        }
        
        granice_obiekt[0]=lyzka.polygon.getBounds();
        granice_obiekt[1]=pipeta.polygon.getBounds();
        
    }
    
    @Override
    protected void paintComponent(Graphics g){
        
        Graphics2D g2 = (Graphics2D) g;
        
        PointerInfo pi=MouseInfo.getPointerInfo();
        Point p = pi.getLocation();
        
        g2.drawImage(Tlo.tlo3,0,0,null);
        
        TexturePaint textura_lyzki = new TexturePaint(Tlo.naczynie1,granice_obiekt[0]);
        g2.setPaint(textura_lyzki);
        g.fillPolygon(lyzka.polygon);
        
        TexturePaint textura_pipety = new TexturePaint(Tlo.naczynie1,granice_obiekt[0]);
        g2.setPaint(textura_pipety);
        g.fillPolygon(pipeta.polygon);
        
        
        
        
        
    }
        void kliknietolewy (Point p){
        int ilosc_sk=Substancje.size();
        boolean kliknieto []= new boolean [ilosc_sk];
        boolean podniesiono=false;
        int iter=0;//podniesiono jakies naczynie
        //kliknieci powoduje wyb naczynia
        //klikniecie ponowne powoduje odz naczynia
        
        for(Skladnik skl: Substancje){//sprawdzay co kliknieto i czy jest wybrane jakies naczynie
            podniesiono=skl.wybrane || podniesiono;
            
            if(skl.granice.contains(p)){
                kliknieto[iter]=true;
                break;
            }
            iter++;
        }
        
        int licznik=0;
        for(Skladnik skl: Substancje)
            
            if(podniesiono==false){//jak nie podniesiono naczynia
                skl.wybrane=kliknieto[licznik];//to klikniete jest wybierane 
            }
            
            else if(kliknieto[licznik]&&skl.wybrane) {//jak podniesiono jakies naczynie i kliknieto wybrane naczynie
                skl.wybrane=false;
            }
        licznik++;    
        }
           
    }
    

