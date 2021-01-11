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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.Timer;


 /**
 Panel graficzny mini gry "Związki chemiczne"
 */

public class Zwiazki_chem extends JPanel
    implements ActionListener{
    private Timer timer;
    static final int DELAY=40;
    
    private final Rectangle pojemnik;       //obszar opuszczania klikania pustego pojemnika
    private final Point [] trojkat;         //punkty kształtu opuszczonego składnika
    private final Point [] p_lyzki;
    private final Point [] p_pipety;
    
    private int [] buf1x;       //bufory do obliczania poligonów 
    private int [] buf1y;
    private int [] buf2x;
    private int [] buf2y;
    private int [] buf3x;
    private int [] buf3y;

    private Skladnik siarka;    
    private Skladnik NaOH;
    private Skladnik wegiel;
    private Skladnik manganian;
    private Skladnik HCl;
    private Skladnik puste_nacz;
    private ArrayList<Skladnik> substancje;
    
    private PoruszajacyObiekt lyzka;
    private PoruszajacyObiekt pipeta;
    private ArrayList<PoruszajacyObiekt> narzedzia;
    
    /**
     * Konstruktor - inicjalizowanie zmiennych
     */
    public Zwiazki_chem(){
        
        timer = new Timer(DELAY, this);
        timer.start();

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
        this.NaOH = new Skladnik(338,570,"lyzka",false,"NaOH",Color.WHITE);
        this.wegiel = new Skladnik(515,571,"lyzka",false,"wegiel",Color.BLACK);
        this.manganian = new Skladnik(673,570,"lyzka",false,"manganian",Color.MAGENTA);
        this.HCl = new Skladnik(847,512,"pipeta",false,"HCl",Color.BLUE);
        this.puste_nacz = new Skladnik(847,512,"pipeta",false,"HCl",Color.BLUE);
        this.substancje = new ArrayList<>();
        
        
        
        //dodanie skladnikow do listy
        
        substancje.add(siarka);
        substancje.add(NaOH);
        substancje.add(wegiel);
        substancje.add(manganian);
        substancje.add(HCl);
        //dodanie narzedzdo listy
        
        
        int ilosc_sk=substancje.size();
        
        this.lyzka = new PoruszajacyObiekt(297,323,false,"lyzka",ilosc_sk);
        this.pipeta = new PoruszajacyObiekt(123,323,false,"pipeta",ilosc_sk);
        this.narzedzia = new ArrayList<>();
        
        narzedzia.add(lyzka);
        narzedzia.add(pipeta);
        
        //kszztalt lyzki
        p_lyzki[0]=new Point(0,9);  //0 koncowka
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
        
        //kszztalt pipety
        p_pipety[0]=new Point(144,144);     //0 koncowka
        p_pipety[1]=new Point(135, 144);
        p_pipety[2]=new Point(15, 24);
        p_pipety[3]=new Point(9, 28);
        p_pipety[4]=new Point(4, 23);
        p_pipety[5]=new Point(7, 19);
        p_pipety[6]=new Point(0,12);
        p_pipety[7]=new Point(1,1);
        
        trojkat[0]=new Point(0,17);
        trojkat[1]=new Point(34,17);
        trojkat[2]=new Point(17,0);
        
        
        
        for(int i=6;i>0;i--){           //pipeta jest symetryczna
            int x=p_pipety[i].y;
            int y=p_pipety[i].x;
            p_pipety[14-i]= new Point(x,y);
        }
        
        
        for(Skladnik skl: substancje){
            Rectangle prost =new Rectangle (skl.x,skl.y,120,80);
            if("HCL".equals(skl.nazwa)){
                prost =new Rectangle (skl.x,skl.y,125,80);
            }
            skl.granice=prost;
            
        }

        lyzka.wysokosc=Zasoby.lyzka.getHeight();
        lyzka.szerokosc=Zasoby.lyzka.getWidth();

        pipeta.wysokosc=Zasoby.pipeta.getHeight();
        pipeta.szerokosc=Zasoby.pipeta.getWidth();
            
 
        
    }
    
    @Override
    protected void paintComponent(Graphics g){

        Graphics2D g2 = (Graphics2D) g;

        PointerInfo pi=MouseInfo.getPointerInfo();
        Point p = pi.getLocation();

        g2.drawImage(Zasoby.tlo3,0,0,null);
        
        //obliczanie punktu poczatku obrazu podniesionego narzędzia
        for(PoruszajacyObiekt narz:narzedzia){  
            if(narz.wybrane){

               narz.x=p.x-narz.szerokosc/2;
               narz.y=p.y-narz.wysokosc/2-20;

            }
        }
        
        
        //obliczanie granic tekstury obrazu łyżki
        for(int i=0; i<p_lyzki.length;i++){      
            buf1x[i]=lyzka.x+p_lyzki[i].x;
            buf1y[i]=lyzka.y+p_lyzki[i].y;             
        }

        pipeta.koncowka=new Point (144+pipeta.x,144+pipeta.y);
        lyzka.koncowka=new Point (156+lyzka.x,144+lyzka.y);

        lyzka.polygon=new Polygon(buf1x,buf1y,p_lyzki.length);
        lyzka.granice=lyzka.polygon.getBounds();
        
        
        //obliczanie granic tekstury obrazu pipety
        for(int i=0; i<p_pipety.length;i++){        
            buf2x[i]=pipeta.x+p_pipety[i].x;
            buf2y[i]=pipeta.y+p_pipety[i].y;             
        }
        
        pipeta.polygon=new Polygon(buf2x,buf2y,p_pipety.length);
        pipeta.granice=pipeta.polygon.getBounds();
        
        
        //rysowanie skladnikow w pojemniku
        for(Skladnik skl:substancje){       
            if(skl.w_poj){

                    g.setColor(skl.kolor);

                    for(int i=0;i<trojkat.length;i++){

                        buf3x[i]=trojkat[i].x+skl.xs;
                        buf3y[i]=trojkat[i].y+skl.ys;

                    }

                    Polygon buf=new Polygon(buf3x,buf3y,3);
                    g2.fillPolygon(buf);
            }
        }

        TexturePaint textura_lyzki = new TexturePaint(Zasoby.lyzka,lyzka.granice);      //rysowanie lyzki
        g2.setPaint(textura_lyzki);
        g.fillPolygon(lyzka.polygon);
        
        
        //rysowanie skladnikow na lyzce i zmiana tekstury pipety
        for(Skladnik skl:substancje){       
            if(skl.wybrane && "lyzka".equals(skl.podnosi)){
              
               g.setColor(skl.kolor);
               g2.fillOval(lyzka.koncowka.x,lyzka.koncowka.y,20,20);
            }
            
            if(skl.wybrane && "pipeta".equals(skl.podnosi)){
                TexturePaint textura_pipety2 = new TexturePaint(Zasoby.pipeta2,pipeta.granice);
                g2.setPaint(textura_pipety2);
            }
            else{
                TexturePaint textura_pipety = new TexturePaint(Zasoby.pipeta,pipeta.granice);
                g2.setPaint(textura_pipety);
            }

        }
        
        

        g.fillPolygon(pipeta.polygon);
        g.setColor(Color.RED);
        
       

    }
    
    
    
    
    //metoda obsługująca logikę podnoszenia narzędzi  
        void pod_narzedzie (Point KM){
            
            int ilosc_narz=narzedzia.size();
            boolean podniesione_narz=false;
            boolean kliknieto_narz []= new boolean [ilosc_narz];
            
            for(int i=0;i<ilosc_narz;i++){
                kliknieto_narz[i]=false;
            }
            
            
            int iter1=0;
            
            
             //sprawdzanie, czy kliknięto narzędzie
            for(PoruszajacyObiekt narz: narzedzia){     
                podniesione_narz=narz.wybrane || podniesione_narz;

                if(narz.granice.contains(KM)){
                    
                    kliknieto_narz[iter1]=true;
                } 
                iter1++;
            }
            
            
            int iter2=0;
            for(PoruszajacyObiekt narz: narzedzia){
                     
                if(podniesione_narz==false && kliknieto_narz[iter2]){       //jak nie jest podniesione narzedzie
                    narz.wybrane=true;
                    podniesione_narz=true;
                }
                
                else if(kliknieto_narz[iter2]&&narz.wybrane) {      //jak podniesione jest narzędzie oraz kliknieto je
                    narz.wybrane=false;
                }
                
            iter2++;
            }
        }
        
        
        
        
      //metoda obsługująca logikę podnoszenia składników   
       void pod_skladnik (){
 
          
        int iter1=0;
        for(Skladnik skl: substancje){    

            
            for(PoruszajacyObiekt narz: narzedzia){
               //sprawdzanie, czy kliknięto składnik
               if( skl.granice.contains(narz.koncowka) && narz.nazwa.equals(skl.podnosi) && narz.wybrane ){  
                   
                    narz.klik[iter1]=true;  
               }
               
               else  {
                   
                   narz.klik[iter1]=false;
               }
               
               //wkładanie do pojemnika składników
               if( pojemnik.contains(narz.koncowka) && skl.wybrane && !skl.w_poj && narz.zajete && narz.nazwa.equals(skl.podnosi)){
                   
                    skl.xs=narz.koncowka.x;
                    skl.ys=narz.koncowka.y;
                    skl.w_poj=true;
                    skl.wybrane=false;
                    narz.zajete=false;
               }
               
            }
            iter1++;
        }
        
    
        int iter2=0;
        for(Skladnik skl: substancje){
            
            for(PoruszajacyObiekt narz: narzedzia){
                //pobieranie składników
                if(narz.zajete==false && narz.klik[iter2] && !narz.zajete ){        //jak nie w narzedziu nie ma skladnika i pobiera

                    skl.wybrane=true;//to bierzemy skladnik
                    narz.zajete=true;
                }
                //opuszczanie składników
                else if(narz.klik[iter2]&&skl.wybrane && narz.zajete) {     //jak podniesiono jakis skladnik i kliknieto wybrane nzrzędzie
                    skl.wybrane=false;
                    narz.zajete=false;
                }
                
            }
        iter2++;    
        }
       
    }
     /** metoda zatwierdzająca wynik, liczaca punkty do oraz tworząca tekst z  wynikiem końcowym
      @return zwraca liczbe zdobytych punktów int*/
   public int zatwierdz(){
        
        int suma=0;
        boolean spr1=false;
        boolean spr2=false;
        boolean spr3=true;      
        
        
        for(Skladnik skl: substancje){
            if( "NaOH".equals(skl.nazwa) ){
               if(skl.w_poj){
                    spr1=true;
                }
            }
            else if ( "HCl".equals(skl.nazwa) ){
                if(skl.w_poj){
                    spr2=true;
                }
            }
            else{
                if(skl.w_poj){
                    spr3=false;
                }
            }
                
                
        }
        
        Zasoby.wyjscie_text+="\n\nWynik mini gry \"Związki chemiczne\"\n";
        
        if(spr1 && spr2 && spr3){
            suma=3;
            Zasoby.wyjscie_text+="Stworzono o pożądany związek chemiczny  (+3 punkty)\n";
        }
        
        else{
            Zasoby.wyjscie_text+="Nie stworzono pożądanegi związeku chemicznego\n";
        }
        
        return suma;
    }    
        
        
    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
   }
}
    

