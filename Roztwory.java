/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekt;

import java.awt.PointerInfo;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.MouseInfo;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.Point;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import java.util.ArrayList;
import java.awt.Graphics;
import java.awt.Font;
import javax.swing.Timer;
import java.awt.Color;
import java.awt.TexturePaint;

 /**
 Panel graficzny mini gry "Roztwory"
 */
public class Roztwory extends JPanel
        implements ActionListener{
    
    private Timer timer;
    static final int DELAY=40;
    
    private String tekst_et1;      //część 1 tekstu etykiety naczynia
    private String tekst_et2;      //część 2 tekstu etykiety naczynia
    
    private boolean [] podniesiono_nacz= new boolean[3];
    private boolean wode_do_kwasu;
    private boolean uj_punkt_kw=false;
    
    private int wysokosc_zdj[] = new int[3];
    private int szerokosc_zdj[] = new int[3];
    
    private final double prog_kwas;     //próg, od którego roztwór jest kwasem
    private final double stez_wynik_gr;
    private final double stez_wynik_dol;

    private double [] ilosc_wody={100,0,1000};  
    private double [] ilosc_kwasu={0,100,0};
    
    private Font czcionka;
    
    private int [] punk_pol_x=new int [8];//punkty polygonu x 
    private int [] punk_pol_y=new int [8];
    private int [] punk_wz_nacz_x=new int [8];//punkty wzoru naczynia x 
    private int [] punk_wz_nacz_y=new int [8];
    private int liczba_punkt=8;
    private int licz_animac=100;    //ile razy ma się wyświetlić rozlany kwas
    
    private Polygon wzor_naczynia1= new Polygon();  //granice do tekstury naczynia1
    private Polygon wzor_naczynia2=new Polygon();   //granice do tekstury naczynia2
    private Rectangle [] granice_nacz =new Rectangle [3] ;
  
    private ArrayList<Point> punk_poz_n =new ArrayList<>();
   
    private int odstep_tekstu=50;
    
    
    /**
     * Konstruktor - inicjalizowanie zmiennych
     */
    public Roztwory(){
        
        timer = new Timer(DELAY, this);
        timer.start();
        
        wode_do_kwasu=false;
        prog_kwas=0.05;
        
        stez_wynik_gr=0.07;
        stez_wynik_dol=0.05;
        
        punk_pol_x[0]=0;
        punk_pol_x[1]=0;
        punk_pol_x[2]=51;
        punk_pol_x[3]=51;
        punk_pol_x[4]=110;
        punk_pol_x[5]=110;
        punk_pol_x[6]=162;
        punk_pol_x[7]=162;
        
        punk_pol_y[0]=244;
        punk_pol_y[1]=219;
        punk_pol_y[2]=111;
        punk_pol_y[3]=0;
        punk_pol_y[4]=0;
        punk_pol_y[5]=111;
        punk_pol_y[6]=219;
        punk_pol_y[7]=244;
        
        punk_poz_n.add(new Point(49,249));
        punk_poz_n.add(new Point(407,269));
        punk_poz_n.add(new Point(674,268));
        
        wysokosc_zdj[0]=Zasoby.naczynie1.getHeight();
        wysokosc_zdj[1]=Zasoby.naczynie2.getHeight();
        wysokosc_zdj[2]=Zasoby.naczynie3.getHeight();

        szerokosc_zdj[0]=Zasoby.naczynie1.getHeight();
        szerokosc_zdj[1]=Zasoby.naczynie2.getHeight();
        szerokosc_zdj[2]=Zasoby.naczynie3.getHeight();

        for(int j=0;j<podniesiono_nacz.length;j++){
            podniesiono_nacz[j]=false;
            granice_nacz[j]=new Rectangle();
        }
        
        czcionka=new Font("URW Chancery L", Font.BOLD , 16);

    }
    
    
    
    
    
    @Override
    protected void paintComponent(Graphics g){
       
        int x=0,y=0;
        double ilosc_roz;
        double ep=0.001;
        double procent_kw;
        
        PointerInfo pi=MouseInfo.getPointerInfo();
        Point p = pi.getLocation();
        
        Graphics2D g2 = (Graphics2D) g;
        
        g2.drawImage(Zasoby.tlo2,0,0,null);
        
       
        //obliczanie punktu poczatku obrazu
         for(int i=0;i<3;i++){      
            if(podniesiono_nacz[i]){
               punk_poz_n.get(i).x=p.x-szerokosc_zdj[i]/2+50;
               punk_poz_n.get(i).y=p.y-wysokosc_zdj[i]/2;
            }
         }
            
         //obliczanie granic tekstury obrazu naczynia1
        for(int i=0;i<punk_wz_nacz_x.length;i++){      
           punk_wz_nacz_x[i]=punk_pol_x[i]+punk_poz_n.get(0).x;
           punk_wz_nacz_y[i]=punk_pol_y[i]+punk_poz_n.get(0).y;
        }
        wzor_naczynia1=new Polygon(punk_wz_nacz_x,punk_wz_nacz_y,liczba_punkt);
        
        
        //obliczanie granic tekstury obrazu naczynia2
        for(int i=0;i<punk_wz_nacz_x.length;i++){       
           punk_wz_nacz_x[i]=punk_pol_x[i]+punk_poz_n.get(1).x;
           punk_wz_nacz_y[i]=punk_pol_y[i]+punk_poz_n.get(1).y;
        }
        wzor_naczynia2=new Polygon(punk_wz_nacz_x,punk_wz_nacz_y,liczba_punkt);
        

        boolean narys_dwa_nacz=false;
        
        for(int i=0;i<2;i++){
            
            //wybrane naczynie musi być narysowane ostatnie
            
            if(!podniesiono_nacz[0] ^ narys_dwa_nacz){      //rysowanie naczynia 1
                granice_nacz[0]=wzor_naczynia1.getBounds();
                TexturePaint textura_nacz1 = new TexturePaint(Zasoby.naczynie1,granice_nacz[0]);
                g2.setPaint(textura_nacz1);
                g.fillPolygon(wzor_naczynia1);
                
            }

            if(!podniesiono_nacz[1] ^ narys_dwa_nacz){      //rysowanie naczynia 2
                granice_nacz[1]=wzor_naczynia2.getBounds();
                TexturePaint textura_nacz2 = new TexturePaint(Zasoby.naczynie2,granice_nacz[1]);
                g2.setPaint(textura_nacz2);
                g.fillPolygon(wzor_naczynia2);
                
            }
            
            if(!podniesiono_nacz[2] ^ narys_dwa_nacz){      //rysowanie naczynia 3
                g2.drawImage(Zasoby.naczynie3,punk_poz_n.get(2).x,punk_poz_n.get(2).y,null);
                granice_nacz[2]=new Rectangle(punk_poz_n.get(2).x,punk_poz_n.get(2).y,szerokosc_zdj[2],wysokosc_zdj[2]);
            }
            narys_dwa_nacz=true;
        }
        
        
        
        //rysowanie etykiet naczyń
        for( int i=0; i<ilosc_kwasu.length && i<ilosc_wody.length ;i++){    
         ilosc_roz=ilosc_kwasu[i]+ilosc_wody[i];
            procent_kw=ilosc_kwasu[i]/ilosc_roz;
         
            if(ilosc_kwasu[i]==0){
              tekst_et1="wody";
              tekst_et2="";
            }
            else if(procent_kw>1-ep){
              tekst_et1="kwasu";
              tekst_et2="siarkowego";
            }
            else {
                tekst_et1="roztworu";
                tekst_et2="kwasu siarkowego";
            }

            x=punk_poz_n.get(i).x;
            y=punk_poz_n.get(i).y+odstep_tekstu+wysokosc_zdj[i];
            
            tekst_et1=(int)ilosc_roz+" ml "+tekst_et1+" "+tekst_et2;
            g.setColor(Color.BLACK);
            g.setFont(czcionka);
            g.drawString(tekst_et1, x, y);

                
        }

        if(wode_do_kwasu && licz_animac>1)wlano_wod_do_kw(g);
        
    }
    
    
    
    
    
    /**metoda sprawdzająca logikę podnoszenia i opuszczania naczyń*/
    void pod_naczynie (Point p){
        
        boolean kliknieto []= new boolean [3];
        boolean podniesiono=false;      //podniesiono jakies naczynie
        
        //klikniecie powoduje wyb naczynia
        //klikniecie ponowne powoduje odznaczenie naczynia
        
        //sprawdzanie, czy kliknieto jakieś naczynie oraz, czy jest podniesione jakies naczynie
        for(int i=0;i<granice_nacz.length;i++){     
            podniesiono=podniesiono_nacz[i]||podniesiono;
            
            if(granice_nacz[i].contains(p)){
                kliknieto[i]=true;
                break;
            }
            
        }
        

        for(int j=0;j<granice_nacz.length;j++){
            //podnoszenie naczynia
            if(podniesiono==false){     //jak nie podniesiono naczynia
                podniesiono_nacz[j]=kliknieto[j];   
            }
            //opuszczanie naczynia
            else if(kliknieto[j]&&podniesiono_nacz[j]) {        //jak podniesiono jakies naczynie i kliknieto wybrane naczynie to je opuszczam
                podniesiono_nacz[j]=false;
            }
            
        }
           
    }
    
    
    
    
    
    /**metoda sprawdzająca logike przelewania*/
    void przelej_z_naczynia (Point p){
        
        wode_do_kwasu=false;
        boolean kliknieto []= new boolean [3];
        boolean pod_dow=false;
        boolean klik_dow=false;
        boolean czy_kw_do=false;
        boolean czy_kw_od=false;
        int nalewam_do=0;
        int nalewam_od=0;
        
        
        //klikniecie prawym powoduje przelewanie z naczynia
        
        //sprawdzamy co kliknieto i czy jest podniesione jakieś naczynie
        for(int i=0;i<granice_nacz.length;i++){
            pod_dow=podniesiono_nacz[i]||pod_dow;
            
            if(granice_nacz[i].contains(p)){
                kliknieto[i]=true;
                
                if(kliknieto[i]&&podniesiono_nacz[i]){  //jesli kliknałem podniesione naczynie to nie dolewm go do samgeo siebie
                    kliknieto[i]=false;
                    continue;
                }
                
                nalewam_do=i;    
            }
        }
        
        //wybranie naczynia, od którego przelewam
        for(int j=0;j<podniesiono_nacz.length;j++){
            
            klik_dow=klik_dow || kliknieto[j];
            if(podniesiono_nacz[j]){
                nalewam_od=j;
            }
        }
        
        if((klik_dow) && (pod_dow)){        //jeśli jakieś naczynie jest podniesione oraz jakieś naczynie kliknięte  
            
            czy_kw_do=spr_procent(prog_kwas,nalewam_do);
            czy_kw_od=spr_procent(prog_kwas,nalewam_od);

            if((czy_kw_od==false)&&(czy_kw_do==true)){      // nie wlewaj wody do kwasu
              
                wode_do_kwasu=true;
                uj_punkt_kw=true;
                licz_animac=100;
            }
            
            przelewanie(nalewam_od,nalewam_do);
        }
    }
    
    
    
    
    
    private void przelewanie (int odktr, int doktr){
       
        double ilosc_roztw=ilosc_wody[odktr]+ilosc_kwasu[odktr];
        double pr_kwasu=ilosc_kwasu[odktr]/ilosc_roztw;
        
        if(ilosc_roztw>5){
            ilosc_wody[odktr]-=5*(1-pr_kwasu);
            ilosc_wody[doktr]+=5*(1-pr_kwasu);
            ilosc_kwasu[doktr]+=5*pr_kwasu;
            ilosc_kwasu[odktr]-=5*pr_kwasu;
        }
        
       
    }
    
    
    
    
    
    // metoda sprawdzająca, czy stężenie kwasu przekroczyło dany próg
    private boolean spr_procent (double pr, int ktr){
        
        double procent_kwasu;
        double ilosc_roztw=ilosc_kwasu[ktr]+ilosc_wody[ktr];

        procent_kwasu=ilosc_kwasu[ktr]/(ilosc_roztw);

        if(procent_kwasu>pr){
            return true;
        }
        
        return false;
    }
    
    
    
    
    
    // metoda powodująca rozlanie kwasu
    private void wlano_wod_do_kw (Graphics g){
        
        int w=Zasoby.szerokosc_okna;
        int h=Zasoby.wysokosc_okna;
        double b;
        
        for(int i=0;i<40;i++){ 
            b=Math.sin(i)*700+300;
            b=Math.abs(b);
            int x= (int)b;
            b=Math.sin(7*i)*500+200;
            b=Math.abs(b);
            int y=(int)b;
            int p=15;
            g.setColor(Color.YELLOW);
            g.fillOval(x+punk_pol_x[1],y+punk_pol_y[1],p,p);
         }
       licz_animac--;
   }
    
    
    
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }
    
    
    
    
    
     /** metoda zatwierdzająca wynik, liczaca punkty do oraz tworząca tekst z  wynikiem końcowym
      @return zwraca liczbe zdobytych punktów int*/
    int zatwierdz(){

        int suma=0;
        boolean spr1=false;
        boolean spr2=false;
        
        Zasoby.wyjscie_text+="\n\nWynik mini gry \"Roztwory\"\n";
        
        for(int i=0;i<3;i++){
            
            spr1=spr_procent(stez_wynik_dol,i);
            spr2=spr_procent(stez_wynik_gr,i);
            spr2=!spr2;

        }
        
        if(spr1&&spr2){
                suma=3;
                Zasoby.wyjscie_text+="Stworzono roztwór o podanym stęzeniu  (+3 punkty)\n";
        }
        
        else{
                Zasoby.wyjscie_text+="Nie stworzono roztworu o podanym stęzeniu\n  ";
        }
        
        if(uj_punkt_kw){
            suma-=2;
            Zasoby.wyjscie_text+="Wlano wodę do kwasu  (-2 punkty)\n";
        }
        
        if(suma<0) suma=0;
        
        return suma;
    }
}
