/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekt;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.lang.Object;
import java.awt.PointerInfo;
import java.awt.*; 
import java.awt.event.*; 
import javax.swing.*; 
import java.util.ArrayList;
import java.util.*;
import java.lang.Math.*;
 import java.awt.Graphics;
 import java.awt.Font;
 import java.util.Date;
import javax.swing.Timer;

public class Roztwory extends JPanel
        implements ActionListener{
    
    Timer timer;
    static final int DELAY=40;
    
    String tekst1;
    String tekst2;
    
    boolean [] podniesiono_nacz= new boolean[3];
    boolean wode_do_kwasu;
    boolean uj_punkt_kw=false;
    
    int wysokosc_zdj[] = new int[3];
    int szerokosc_zdj[] = new int[3];
    float prog_kwas;
    float stez_wynik;
    
    int [] ilosc_wody={100,0,500};  
    int [] ilosc_kwasu={0,100,0};
    
    int [] punk_pol_x=new int [8];
    int [] punk_pol_y=new int [8];
    int [] punk_wz_nacz_x=new int [8];
    int [] punk_wz_nacz_y=new int [8];
    int liczba_punkt=8;
    int licz_animac=2;
            
    Polygon wzor_naczynia1= new Polygon();
    Polygon wzor_naczynia2=new Polygon();
    Rectangle [] granice_nacz =new Rectangle [3] ;
  
    ArrayList<Point> punk_poz_n =new ArrayList<>();
    
    int odstep_tekstu=50;

    Point pozycja_myszy;
    
    public Roztwory(){
        timer = new Timer(DELAY, this);
        timer.start();
        
        wode_do_kwasu=false;
        prog_kwas=1/100;
        stez_wynik=7/100;
        
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
        
        wysokosc_zdj[0]=Tlo.naczynie1.getHeight();
        wysokosc_zdj[1]=Tlo.naczynie2.getHeight();
        wysokosc_zdj[2]=Tlo.naczynie3.getHeight();

        szerokosc_zdj[0]=Tlo.naczynie1.getHeight();
        szerokosc_zdj[1]=Tlo.naczynie2.getHeight();
        szerokosc_zdj[2]=Tlo.naczynie3.getHeight();

        for(int j=0;j<podniesiono_nacz.length;j++){
            podniesiono_nacz[j]=false;
            granice_nacz[j]=new Rectangle();
        }

     

        
    
    }
    @Override
    protected void paintComponent(Graphics g){
       
        int x,y;
        int ilosc_roz;
        double ep=0.001;
        float procent_kw;
        
        PointerInfo pi=MouseInfo.getPointerInfo();
        Point p = pi.getLocation();
        
        Graphics2D g2 = (Graphics2D) g;
        
        g2.drawImage(Tlo.tlo2,0,0,null);
        
       
        
         for(int i=0;i<3;i++){
            if(podniesiono_nacz[i]){
               punk_poz_n.get(i).x=p.x-szerokosc_zdj[i]/2;
               punk_poz_n.get(i).y=p.y-wysokosc_zdj[i]/2;
            }
            ilosc_roz=ilosc_kwasu[i]+ilosc_wody[i];
            procent_kw=(float)ilosc_kwasu[i]/(float)ilosc_roz;
            
            if(ilosc_kwasu[i]==0){
              tekst1="wody";
              tekst2="";
            }
            else if(procent_kw>1-ep){
              tekst1="kwasu";
              tekst2="siarkowego";
            }
            else {
                tekst1="roztworu";
                tekst2="kwasu siarkowego";
            }

            x=punk_poz_n.get(i).x;
            y=punk_poz_n.get(i).y+odstep_tekstu+wysokosc_zdj[i];
            
            
            tekst1=ilosc_roz+" ml "+tekst1+" "+tekst2;
            g.drawString(tekst1, x, y);
            System.out.println(tekst1);
                
        }
        
        for(int i=0;i<punk_wz_nacz_x.length;i++){
           punk_wz_nacz_x[i]=punk_pol_x[i]+punk_poz_n.get(0).x;
           punk_wz_nacz_y[i]=punk_pol_y[i]+punk_poz_n.get(0).y;
        }
        wzor_naczynia1=new Polygon(punk_wz_nacz_x,punk_wz_nacz_y,liczba_punkt);
        
        for(int i=0;i<punk_wz_nacz_x.length;i++){
           punk_wz_nacz_x[i]=punk_pol_x[i]+punk_poz_n.get(1).x;
           punk_wz_nacz_y[i]=punk_pol_y[i]+punk_poz_n.get(1).y;
        }
        wzor_naczynia2=new Polygon(punk_wz_nacz_x,punk_wz_nacz_y,liczba_punkt);
        
        
        
        
        
        //g2.drawImage(Tlo.naczynie1,punk_poz_n.get(0).x,punk_poz_n.get(0).y,null);
        //g2.drawImage(Tlo.naczynie2,punk_poz_n.get(1).x,punk_poz_n.get(1).y,null);
        
        
        //nacz1
        boolean narys_dwa_nacz=false;
        for(int i=0;i<2;i++){
            //wybrane naczynie musi być narysowane ostatnie
            if(!podniesiono_nacz[0] ^ narys_dwa_nacz){
                granice_nacz[0]=wzor_naczynia1.getBounds();
                TexturePaint textura_nacz1 = new TexturePaint(Tlo.naczynie1,granice_nacz[0]);
                g2.setPaint(textura_nacz1);
                g.fillPolygon(wzor_naczynia1);
                
            }

            if(!podniesiono_nacz[1] ^ narys_dwa_nacz){//nacz2
                granice_nacz[1]=wzor_naczynia2.getBounds();
                TexturePaint textura_nacz2 = new TexturePaint(Tlo.naczynie2,granice_nacz[1]);
                g2.setPaint(textura_nacz2);
                g.fillPolygon(wzor_naczynia2);
                
            }
            
            if(!podniesiono_nacz[2] ^ narys_dwa_nacz){
                g2.drawImage(Tlo.naczynie3,punk_poz_n.get(2).x,punk_poz_n.get(2).y,null);
                granice_nacz[2]=new Rectangle(punk_poz_n.get(2).x,punk_poz_n.get(2).y,szerokosc_zdj[2],wysokosc_zdj[2]);
            }
            narys_dwa_nacz=true;
        }
            //nacz3
            //TexturePaint textura_nacz3 = new TexturePaint(Tlo.naczynie3,new Rectangle(punk_poz_n.get(0).x,punk_poz_n.get(0).y,szerokosc_zdj[2],wysokosc_zdj[2]));
        g.setColor(Color.BLACK);
        g2.draw(granice_nacz[0]);
        g2.draw(granice_nacz[1]);
        g2.drawRect(p.x,p.y,10,10);
        if(wode_do_kwasu && licz_animac>1)wlano_wod_do_kw(g);
        System.out.println("0,0 pol "+wzor_naczynia1.contains(78, 146));
            
        
        
        
        
       
    }
    void kliknietolewy (Point p){
        boolean kliknieto []= new boolean [3];
        boolean podniesiono=false;  //podniesiono jakies naczynie
        //kliknieci powoduje wyb naczynia
        //klikniecie ponowne powoduje odz naczynia
        
        for(int i=0;i<granice_nacz.length;i++){//sprawdzay co kliknieto i czy jest wybrane jakies naczynie
            podniesiono=podniesiono_nacz[i]||podniesiono;
            
            if(granice_nacz[i].contains(p)){
                kliknieto[i]=true;
                break;
            }
            
        }
        

        for(int j=0;j<granice_nacz.length;j++){
            
            if(podniesiono==false){//jak nie podniesiono naczynia
                podniesiono_nacz[j]=kliknieto[j];//to klikniete jest wybierane 
            }
            
            else if(kliknieto[j]&&podniesiono_nacz[j]) {//jak podniesiono jakies naczynie i kliknieto wybrane naczynie
                podniesiono_nacz[j]=false;
            }
            
        }
           
    }
    
    
    
    
    void kliknietoprawy (Point p){
        
        System.out.println("prawy");
        wode_do_kwasu=false;
        boolean kliknieto []= new boolean [3];
        boolean pod_dow=false;
        boolean klik_dow=false;
        boolean czy_kw_do=false;
        boolean czy_kw_od=false;
        int nalewam_do=0;
        int nalewam_od=0;
        //kliknieci prawym powoduje przelewanie z naczynia
 
        for(int i=0;i<granice_nacz.length;i++){//sprawdzay co kliknieto i czy jest wybrane jakies naczynie
            pod_dow=podniesiono_nacz[i]||pod_dow;
            
            if(granice_nacz[i].contains(p)){
                kliknieto[i]=true;
                
                if(kliknieto[i]&&podniesiono_nacz[i]){//jesli kliknalem podniesione naczynie to nie dolewm go do samgeo siebie
                    kliknieto[i]=false;
                    continue;
                }
                
                nalewam_do=i;    
            }
        }
        
        
        
        for(int j=0;j<podniesiono_nacz.length;j++){
            klik_dow=klik_dow || kliknieto[j];
            if(podniesiono_nacz[j]){
                nalewam_od=j;
            }
        }
        
        if((klik_dow) && (pod_dow)){
            czy_kw_do=spr_procent(prog_kwas,nalewam_do);
            czy_kw_od=spr_procent(prog_kwas,nalewam_od);
            System.out.println("czy od to kwas "+czy_kw_od+" czy do to kwas "+czy_kw_do);
            if((czy_kw_od==false)&&(czy_kw_do==true)){// nie wlewaj wody do kwasu
              
                wode_do_kwasu=true;
                uj_punkt_kw=true;
                licz_animac=100;
            }
            
            przelewanie(nalewam_od,nalewam_do);
        }
    }
    
    void przelewanie (int odktr, int doktr){
       
        if(ilosc_wody[odktr]>5){
            ilosc_wody[odktr]-=5;
            ilosc_wody[doktr]+=5;
        }
        
        if(ilosc_kwasu[odktr]>5){
            ilosc_kwasu[doktr]+=5;
            ilosc_kwasu[odktr]-=5;
        }
    }
    
    
    
    
    
    boolean spr_procent (float pr, int ktr){
        float procent_kwasu=pr;
        procent_kwasu=(float)ilosc_kwasu[ktr]/((float)ilosc_kwasu[ktr]+(float)ilosc_wody[ktr]);
        
        if(procent_kwasu>pr){
         return true;
        }
        
        return false;
    }
    
    
    
    
    
    void wlano_wod_do_kw (Graphics g){
        int w=Tlo.szerokosc_okna;
        int h=Tlo.wysokosc_okna;
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
    
    int zatwierdz(){
        int suma=0;
        
        for(int i=0;i<3;i++){
            if(spr_procent(stez_wynik,i)){
                suma=3;
            }
        }
        
        if(uj_punkt_kw){
            suma-=2;
            
        }
        
        if(suma<0) suma=0;
        
        System.out.println("suma pnkt "+suma);
        return suma;
    }
}

/*
 for(int i=0;true;i++){
            if(i>1000){i=0;}
            if(i==1000){
                PointerInfo pi=MouseInfo.getPointerInfo();
                Point p = pi.getLocation(); 
                System.out.println(p.getX());
            }
        
        }
*/