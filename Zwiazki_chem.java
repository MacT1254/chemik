/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekt;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JPanel;




public class Zwiazki_chem extends JPanel{
    
    Rectangle pojemnik;
    Point [] trojkat;
    Point [] p_lyzki;
    Point [] p_pipety;
    
    int [] buf1x;
    int [] buf1y;
    int [] buf2x;
    int [] buf2y;
    int [] buf3x;
    int [] buf3y;

    Skladnik siarka;
    Skladnik NaOh;
    Skladnik wegiel;
    Skladnik manganian;
    Skladnik HCl;
    Skladnik puste_nacz;
    ArrayList<Skladnik> Substancje;
    
    PoruszajacyObiekt lyzka;
    PoruszajacyObiekt pipeta;
    ArrayList<PoruszajacyObiekt> narzedzia;
    
    public Zwiazki_chem(){
        
        this.buf1y = new int [11];
        this.buf1x = new int [11];
        this.buf2y = new int [14];
        this.buf2x = new int [14];
        this.buf3y = new int [11];
        this.buf3x = new int [11];
        
        this.p_pipety = new Point [14];
        this.p_lyzki = new Point [11];
        this.pojemnik = new Rectangle(513,415,95,95);
        this.trojkat=new Point[3];
        
        this.siarka = new Skladnik(166,570,"lyzka",false,"siarka",Color.YELLOW);
        this.NaOh = new Skladnik(338,570,"lyzka",false,"NaOh",Color.WHITE);
        this.wegiel = new Skladnik(515,571,"lyzka",false,"wegiel",Color.BLACK);
        this.manganian = new Skladnik(673,570,"lyzka",false,"manganian",Color.MAGENTA);
        this.HCl = new Skladnik(847,512,"pipeta",false,"HCl",Color.BLUE);
        this.puste_nacz = new Skladnik(847,512,"pipeta",false,"HCl",Color.BLUE);
        this.Substancje = new ArrayList<>();
        
        this.lyzka = new PoruszajacyObiekt(0,0,true,"lyzka");
        this.pipeta = new PoruszajacyObiekt(0,0,false,"pipeta");
        this.narzedzia = new ArrayList<>();
        
        System.out.println("krelok was here");
        //dodanie skladnikow do listy
        
        Substancje.add(siarka);
        Substancje.add(NaOh);
        Substancje.add(wegiel);
        Substancje.add(manganian);
        Substancje.add(HCl);
        //dodanie narzedzdo listy
        narzedzia.add(lyzka);
        narzedzia.add(pipeta);
        //kszztalt pipety

       
        
        //kszztalt lyzki
        p_lyzki[0]=new Point(0,9);//0 koncowka
        p_lyzki[1]=new Point(9,0);
        p_lyzki[2]=new Point(152,143);
        p_lyzki[3]=new Point(161,139);
        p_lyzki[4]=new Point(174,139);
        p_lyzki[5]=new Point(188,146);
        p_lyzki[6]=new Point(188,158);
        p_lyzki[7]=new Point(175,168);
        p_lyzki[8]=new Point(157,168);
        p_lyzki[9]=new Point(144,156);
        p_lyzki[10]=new Point(145,154);
        
        p_pipety[0]=new Point(144,144);
        p_pipety[1]=new Point(135, 144);//1
        p_pipety[2]=new Point(15, 24);//2
        p_pipety[3]=new Point(9, 28);//3
        p_pipety[4]=new Point(4, 23);//4
        p_pipety[5]=new Point(7, 19);//5
        p_pipety[6]=new Point(0,12);//6
        p_pipety[7]=new Point(1,1);//7 rog
        
        trojkat[0]=new Point(0,17);
        trojkat[1]=new Point(34,17);
        trojkat[2]=new Point(17,0);
        
        for(int i=6;i>0;i--){//pipeta jest symetryczna
            int x=p_pipety[i].y;
            int y=p_pipety[i].x;
            p_pipety[14-i]= new Point(x,y);
        }
        
        for(Skladnik skl: Substancje){
            Rectangle prost =new Rectangle (skl.x,skl.y,120,80);
            if("HCL".equals(skl.nazwa)){
                prost =new Rectangle (skl.x,skl.y,125,80);
            }
            skl.granice=prost;
            System.out.println(skl.x);
            System.out.println(skl.granice);
            
        }

        lyzka.wysokosc=Tlo.lyzka.getHeight();
        lyzka.szerokosc=Tlo.lyzka.getWidth();

        pipeta.wysokosc=Tlo.pipeta.getHeight();
        pipeta.szerokosc=Tlo.pipeta.getWidth();
            
 
        
    }
    
@Override
protected void paintComponent(Graphics g){

    Graphics2D g2 = (Graphics2D) g;

    PointerInfo pi=MouseInfo.getPointerInfo();
    Point p = pi.getLocation();

    g2.drawImage(Tlo.tlo3,0,0,null);

    for(PoruszajacyObiekt narz:narzedzia){
        if(narz.wybrane){
           System.out.println("wybrane");
           narz.x=p.x-narz.szerokosc/2;
           narz.y=p.y-narz.wysokosc/2;

           System.out.println("wybx "+narz.x);
           System.out.println("wyby "+narz.y);
        }
    }

    for(int i=0; i<p_lyzki.length;i++){
        buf1x[i]=lyzka.x+p_lyzki[i].x;
        buf1y[i]=lyzka.y+p_lyzki[i].y;             
    }

    pipeta.koncowka=new Point (144+pipeta.x,144+pipeta.y);
    lyzka.koncowka=new Point (156+lyzka.x,144+lyzka.y);

    lyzka.polygon=new Polygon(buf1x,buf1y,p_lyzki.length);
    lyzka.granice=lyzka.polygon.getBounds();

    for(int i=0; i<p_pipety.length;i++){
        buf2x[i]=pipeta.x+p_pipety[i].x;
        buf2y[i]=pipeta.y+p_pipety[i].y;             
    }
    pipeta.polygon=new Polygon(buf2x,buf2y,p_pipety.length);
    pipeta.granice=pipeta.polygon.getBounds();


    TexturePaint textura_lyzki = new TexturePaint(Tlo.lyzka,lyzka.granice);
    g2.setPaint(textura_lyzki);
    g.fillPolygon(lyzka.polygon);
    g.setColor(Color.RED);
    g.drawPolygon(lyzka.polygon);
    g2.draw(lyzka.granice);


    for(Skladnik skl:Substancje){
        if(skl.wybrane && "lyzka".equals(skl.podnosi)){
           g.setColor(skl.kolor);
           g2.fillOval(lyzka.koncowka.x,lyzka.koncowka.y,20,20);
        }
        if(skl.w_poj){
            System.out.println("++++++++++wpoj+++++");
            g.setColor(skl.kolor);

            for(int i=0;i<trojkat.length;i++){

                buf3x[i]=trojkat[i].x+skl.xs;
                buf3y[i]=trojkat[i].y+skl.ys;

            }

            Polygon buf=new Polygon(buf3x,buf3y,3);
            g2.fillPolygon(buf);
        }
        if(skl.wybrane && "pipeta".equals(skl.podnosi)){
            TexturePaint textura_pipety2 = new TexturePaint(Tlo.pipeta2,pipeta.granice);
            g2.setPaint(textura_pipety2);
        }
        else{
            TexturePaint textura_pipety = new TexturePaint(Tlo.pipeta,pipeta.granice);
            g2.setPaint(textura_pipety);
        }

    }

    g.fillPolygon(pipeta.polygon);
    g.setColor(Color.RED);
    g.drawPolygon(pipeta.polygon);
    g2.draw(pipeta.granice);   





}
        void kliknietolewy (Point KM){
            
            System.out.println("++++++++++++++++++++++");

            int ilosc_narz=narzedzia.size();
            boolean podniesione_narz=false;
            boolean kliknieto_narz []= new boolean [ilosc_narz];
            
            for(int i=0;i<ilosc_narz;i++){
                kliknieto_narz[i]=false;
            }
            
            
            int iter2=0;
            for(PoruszajacyObiekt narz: narzedzia){//sprawdzay co kliknieto
                podniesione_narz=narz.wybrane || podniesione_narz;
                System.out.println("kliknoles w cos "+narz.granice.contains(KM));
                if(narz.granice.contains(KM)){
                    System.out.println("klik narz iter="+iter2);
                    
                    kliknieto_narz[iter2]=true;
                    System.out.println(narz.nazwa);
                    break;
                } 
                iter2++;
            }
            
            int iter4=0;
            for(PoruszajacyObiekt narz: narzedzia){
                
                System.out.println("czu jest cos podniesione" +podniesione_narz);
                System.out.println("czy kliknieto" +narz.nazwa +" | "+kliknieto_narz[iter4]);
                
                if(podniesione_narz==false && kliknieto_narz[iter4]){//jak nie jest podniesione narzedzie
                    narz.wybrane=true;//to klikniete jest wybierane 
                    System.out.println("podniesiono"+narz.nazwa);
                    podniesione_narz=true;
                }
                
                else if(kliknieto_narz[iter4]&&narz.wybrane) {//jak podniesiono jakies naczynie i  kliknieto to narzedzie
                    narz.wybrane=false;
                    System.out.println("opuszczono"+narz.nazwa);
                }
            iter4++;
            }
        }
        
    
        void kliknietoprawy (){
        System.out.println("==========================");
        int ilosc_sk=Substancje.size();
        
        boolean kliknieto_sk []= new boolean [ilosc_sk];

        boolean podniesione_sk=false;
       
        
        int iter1=0;
        
        for(Skladnik skl: Substancje){//sprawdzay co kliknieto
            
            podniesione_sk=skl.wybrane || podniesione_sk;
            
            for(PoruszajacyObiekt narz: narzedzia){
                
               if( skl.granice.contains(narz.koncowka) && narz.nazwa.equals(skl.podnosi) ){
                    System.out.println(skl.nazwa);
                    System.out.println("iter: "+iter1);
                    kliknieto_sk[iter1]=true;
                    
               }
               
               if( pojemnik.contains(narz.koncowka) && skl.wybrane && !skl.w_poj ){
                    skl.xs=narz.koncowka.x;
                    skl.ys=narz.koncowka.y;
                    skl.w_poj=true;
                    skl.wybrane=false;
                    System.out.println(skl.nazwa);         
               }
               
            }
            iter1++;
        }
        
    
        int iter3=0;
        for(Skladnik skl: Substancje){
            
            if(podniesione_sk==false){//jak nie podniesiono naczynia
                skl.wybrane=kliknieto_sk[iter3];//to klikniete jest wybierane 
            }
            else if(kliknieto_sk[iter3]&&skl.wybrane) {//jak podniesiono jakies naczynie i kliknieto wybrane naczynie
                skl.wybrane=false;
            }
            
        iter3++;    
        }
       
    }
}
    

