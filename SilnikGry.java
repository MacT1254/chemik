/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekt;

import java.awt.Font;
import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyEvent;
import java.awt.EventQueue;
import java.awt.Button;
import javax.swing.JFrame;


import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 *Klasa obsługująca wydarzenia wywoływane przez myszkę i klawiaturę oraz poruszanie się postaci.
 *Obsługuje takżę zamykanie i otwieranie okien. 
 */
public class SilnikGry {
    
    static int odl_min=35000;   //odległość, od której aktywuje sie akcja (np.resetowanie) 
    static int krok_chodz=8;    //odległość pojedynczrgo kroku postaci
    static int stol_A_pos_x=63;
    static int stol_A_pos_y=59;
    static int stol_B_pos_x=65;
    static int stol_B_pos_y=530;
    static int stol_C_pos_x=566;
    static int stol_C_pos_y=529;
    static int stol_sz=388;
    static int stol_wy=143;
    
    /** Stała przechowująca nazwę pliku z tekstem pomocy do gry*/
    static final String pomoc_gra="pomoc_gra";
    /** Stała przechowująca nazwę pliku z tekstem pomocy do mini gry
     "Wzory"*/
    static final String pomoc_wz="pomoc_wz";
    /** Stała przechowująca nazwę pliku z tekstem pomocy do mini gry
     *"Roztwory*/
    static final String pomoc_roz="pomoc_roz";
    /** Stała przechowująca nazwę pliku z tekstem pomocy do mini gry
     * "Związki chemiczne"*/
    static final String pomoc_zwi="pomoc_zwi";
    
   
    
    boolean minigra1_uruch;
    boolean minigra2_uruch;
    boolean minigra3_uruch;
    boolean tekst_wys;
     /** Zmienna stanu określająca, czy wyświetlnić tekst końcowy*/
    public boolean czy_wyjscie;
    
    
    Font czcionka=new Font("URW Chancery L", Font.BOLD, 21);
    
    Point KM_wz;
    Point KM_roz;
    Point KM_zwi;
    
    //tworzenie obiektów

    private ArrayList<Stanowisko> lista_stan =new ArrayList<>();;
    private ArrayList<Obiekt> lista_obiekt = new ArrayList<>();
    private Stanowisko stol_z_papierami;
    private Stanowisko stol_z_roztw;
    private Stanowisko stol_z_zwi;
    private Stanowisko wyjscie;
    private Stanowisko reset;
    
    private Gra nowagra;
    private Okno okno_gr; 

    private Wzory minigra1;
    private Okno okno_wz;
    private Button zatwierdz_wz;


    private Roztwory minigra2;
    private Okno okno_roz;
    private Button zatwierdz_roz;
    
    private Zwiazki_chem minigra3; 
    private Okno okno_zwi;
    private Button zatwierdz_zwi;
    
    private Wysw_tekstu wysw_tekstu;
    private Okno okno_tesku;    

    /**
     * Konstruktor - inicjalizowanie zmiennych
    */
    
    public SilnikGry(){
        
        minigra1_uruch=false;
        minigra2_uruch=false;
        minigra3_uruch=false;
        tekst_wys=false;
        czy_wyjscie=false;
                
        lista_obiekt.add(new Obiekt(stol_A_pos_x, stol_A_pos_y, stol_sz, stol_wy));
        lista_obiekt.add(new Obiekt(stol_B_pos_x, stol_B_pos_y, stol_sz, stol_wy));
        lista_obiekt.add(new Obiekt(stol_C_pos_x, stol_C_pos_y, stol_sz, stol_wy));
        
        lista_obiekt.add(new Obiekt(0, 54, 1024, 1));//sciany
        lista_obiekt.add(new Obiekt(0, 679, 1024, 1));
        lista_obiekt.add(new Obiekt(59, 0, 1, 768));
        lista_obiekt.add(new Obiekt(950, 0, 1, 768));
        
        stol_z_papierami = new Stanowisko(324,127);
        stol_z_roztw = new Stanowisko(242,600);
        stol_z_zwi = new Stanowisko(760,590);  
        wyjscie = new Stanowisko(1053,170);
        reset = new Stanowisko(1053,361);
        
        lista_stan.add(stol_z_papierami);
        lista_stan.add(stol_z_roztw);
        lista_stan.add(stol_z_zwi);
        lista_stan.add(wyjscie);
        lista_stan.add(reset);
        
        nowagra = new Gra();
        okno_gr = new Okno("Chemik");
        okno_gr.setVisible(true);
        okno_gr.add(nowagra);
        okno_gr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        inic_tekst(pomoc_gra);
   
    }  
    
    
    /**Metoda obsługująca wciskanie klawiszyw panelu głównej gry*/
    void obsługa_klawiatury(){   

        okno_gr.addKeyListener(new KeyAdapter(){

           public void keyPressed(KeyEvent ke){

               if(ke.getKeyCode()==ke.VK_RIGHT){

                    if(sprawdzkolizje(false,false,false,true)){

                    }

                   else { 

                        Postac.srodek_x+=krok_chodz;
                        okno_gr.repaint();
                   }
               }

               if(ke.getKeyCode()==ke.VK_LEFT){

                    if(sprawdzkolizje(false,false,true,false)){

                    }

                    else { 

                        Postac.srodek_x-=krok_chodz;
                        okno_gr.repaint();
                   }
               }

               if(ke.getKeyCode()==ke.VK_UP){

                   if(sprawdzkolizje(true,false,false,false)){

                   }

                   else { 

                        Postac.srodek_y-=krok_chodz;
                        okno_gr.repaint();
                   }
               }

               if(ke.getKeyCode()==ke.VK_DOWN){

                    if(sprawdzkolizje(false,true,false,false)){

                    }

                    else { 

                        Postac.srodek_y+=krok_chodz;
                        okno_gr.repaint();
                    }
               }

               if(ke.getKeyCode()==ke.VK_M){
                   int odl;
                   for(int m=0; m < lista_stan.size(); m++){

                       odl=odlegloscdokwa(Postac.srodek_x,Postac.srodek_y,lista_stan.get(m).run_point_x,lista_stan.get(m).run_point_y);

                       if(odl<odl_min){

                           if(m==0 && minigra1_uruch==false) {
                               minigra1_uruch=true;
                               inic_minigry_wzory();
                               inic_tekst(pomoc_wz);
                           }
                           
                           if(m==1 && minigra2_uruch==false) {
                               minigra2_uruch=true;
                               inic_minigry_roztw();
                               inic_tekst(pomoc_roz);
                           }
                           
                           if(m==2 && minigra3_uruch==false){
                               minigra3_uruch=true;
                               inic_minigry_zwi();
                               inic_tekst(pomoc_zwi);
                           }
                           
                           if (m==3){
                               wyjscie();
                           }
                           
                           if (m==4){
                               reset();
                               okno_gr.repaint();
                           }
                           
                       }
                   }  
               }
               
               if(ke.getKeyCode()==ke.VK_P){
                   inic_tekst(pomoc_gra);
               }
               
   
           }
       });
    }
    
    
    
    
    
    private void reset(){
        
        Postac.srodek_x=505;
        Postac.srodek_y=315;
        minigra1_uruch=false;
        minigra2_uruch=false;
        minigra3_uruch=false;
        Zasoby.sumapunkt=0;
        Zasoby.wyjscie_text="";
    }
    
    
    
    
   //metoda tworząca okno pomocy 
   private void inic_tekst(String plik){
       
     if(!tekst_wys){   
            tekst_wys=true;
            int szer_guzika=400;
            int wysokosc_guzika=70;
            int x=(1013-szer_guzika)/2;
            int y=570;

            okno_tesku = new Okno("Pomoc");
            wysw_tekstu = new Wysw_tekstu(plik,czy_wyjscie);
            Button OK = new Button("OK");

            okno_tesku.add(OK);
            okno_tesku.add(wysw_tekstu);
            okno_tesku.setVisible(true);
            okno_tesku.setLayout(null);

            wysw_tekstu.setBackground(Color.white);


            OK.setBounds(x,y,szer_guzika,wysokosc_guzika);
            OK.setFont(czcionka);
            OK.addActionListener(new B4());
            OK.setVisible(true);
            OK.addActionListener(new B4());

            wysw_tekstu.wys_pomoc();

            if(czy_wyjscie){
                 wysw_tekstu.wys_wyniki();
            }
        }
    }



    
    
   private void inic_minigry_wzory(){
       
        int pozycja_zatx=717;       //pozycja przycisku do zatwierdzania Wzorów 
        int pozycja_zaty=121;
        int szerokosc_przyc=251;     
        int wysokosc_przyc=140;
        
        minigra1 = new Wzory();
        okno_wz = new Okno("Wzory");
        zatwierdz_wz = new Button("Zatwierdź");
        okno_wz.add(minigra1);
        
        zatwierdz_wz.setBounds(pozycja_zatx,pozycja_zaty,szerokosc_przyc,wysokosc_przyc);
        zatwierdz_wz.setFont(czcionka);
        zatwierdz_wz.addActionListener(new B1());
        minigra1.add(zatwierdz_wz);

        okno_wz.setVisible(true);

        minigra1.setFocusable(true);
        
        minigra1.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent mewz){
            KM_wz=mewz.getPoint();
            
             if(mewz.getButton()==MouseEvent.BUTTON1){//kliknieto lewy
                 minigra1.requestFocusInWindow();
                 
            }

            }
        });
            
        minigra1.addKeyListener(new KeyAdapter(){
            
        public void keyPressed(KeyEvent kewz){
               if(kewz.VK_P==kewz.getKeyCode()){
                   inic_tekst(pomoc_wz);
               }
           }
        });
        minigra1.setFocusable(true);
        minigra1.grabFocus();


    }
    
    
    
    
    
   private void inic_minigry_roztw(){
       
        int szerokosc_przyc=355;
        int x=(1013-szerokosc_przyc)/2;
        
        minigra2 = new Roztwory();
        okno_roz = new Okno("Roztwory");
        zatwierdz_roz = new Button("Zatwierdź");
        okno_roz.add(minigra2);
        
        
        zatwierdz_roz.setBounds(x,70,szerokosc_przyc,75);
        zatwierdz_roz.setFont(czcionka);
        zatwierdz_roz.addActionListener(new B2());
        
        minigra2.add(zatwierdz_roz);
        minigra2.setLayout(null);
        
        minigra2.setFocusable(true);
        
        EventQueue.invokeLater(() -> {
             okno_roz.repaint();

         });


        okno_roz.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent meroz){
            KM_roz=meroz.getPoint();

            if(meroz.getButton()==MouseEvent.BUTTON1){//kliknieto lewy
                minigra2.setFocusable(true);
                minigra2.pod_naczynie(KM_roz);
                okno_roz.repaint();
            }
            
            if(meroz.getButton()==MouseEvent.BUTTON3){//kliknieto prawy
                minigra2.setFocusable(true);
                minigra2.przelej_z_naczynia(KM_roz);
                okno_roz.repaint();
            }
            }
        });
        
        minigra2.addKeyListener(new KeyAdapter(){
        @Override   
        public void keyPressed(KeyEvent ke){

            if(ke.getKeyCode()==ke.VK_P){
                inic_tekst(pomoc_roz);
            }
        }
        });
        
        okno_roz.setVisible(true);
}




        
    void inic_minigry_zwi(){
        
        int szerokosc_przyc=251;
        int x=(1013-szerokosc_przyc)/2;
        
        minigra3=new Zwiazki_chem();
        okno_zwi = new Okno("Zwiazki chemiczne");
        
        okno_zwi.add(minigra3);
        
        zatwierdz_zwi = new Button("Zatwierdź");
        zatwierdz_zwi.setBounds(x,120,szerokosc_przyc,70);
        zatwierdz_zwi.setFont(czcionka);
        zatwierdz_zwi.addActionListener(new B3());
        
        minigra3.add(zatwierdz_zwi);
        minigra3.setLayout(null);
        minigra3.setFocusable(true);
        
        EventQueue.invokeLater(() -> {
                okno_zwi.repaint();

       });

        okno_zwi.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent mezwi){
            KM_zwi=mezwi.getPoint();


            if(mezwi.getButton()==MouseEvent.BUTTON1){//kliknieto lewy
                minigra3.setFocusable(true);
                minigra3.pod_narzedzie(KM_zwi);
                okno_zwi.repaint();
                
            }
            
            
            if(mezwi.getButton()==MouseEvent.BUTTON3){//kliknieto prawy
                minigra3.setFocusable(true);
                minigra3.pod_skladnik();
                okno_zwi.repaint();
            }
            }
        });
        
        minigra3.addKeyListener(new KeyAdapter(){
        @Override   
        public void keyPressed(KeyEvent ke){

               if(ke.getKeyCode()==ke.VK_P){
                   inic_tekst(pomoc_zwi);
               }
        }
        });
        
        okno_zwi.setVisible(true);
     } 

    
    
    
     
    void wyjscie(){
        okno_gr.setVisible(false);
        okno_gr.dispose();
        czy_wyjscie=true;
        Zasoby.wyjscie_text+="\n\n\nSuma uzyskanych punktów jest równa "+Zasoby.sumapunkt+" na 10 możliwych.";
        inic_tekst(Zasoby.wyjscie_text);
    }

    
  
    
    
    private class B1 implements ActionListener {
        
            @Override
            public void actionPerformed(ActionEvent e) {
                Zasoby.sumapunkt+=minigra1.zatwierdz();
                okno_wz.setVisible(false);
                okno_wz.dispose();
                okno_gr.repaint();
            }
    }
    
    
    
    
    private class B2 implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                Zasoby.sumapunkt+=minigra2.zatwierdz();
                okno_roz.setVisible(false);
                okno_roz.dispose();
                okno_gr.repaint();
            }
    }
    
    
    
    
    
    private class B3 implements ActionListener {
        
            @Override
            public void actionPerformed(ActionEvent e) {
                Zasoby.sumapunkt+=minigra3.zatwierdz();
                okno_zwi.setVisible(false);
                okno_zwi.dispose();
                okno_gr.repaint();
            }
    }
    
    
    
    
    private class B4 implements ActionListener {
        
            @Override
            public void actionPerformed(ActionEvent e) {
                tekst_wys=false;
                okno_tesku.setVisible(false);
                okno_tesku.dispose();
                
                if(czy_wyjscie){
                   okno_tesku.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                   okno_gr.dispose();
                   System.exit(0);
                }
            }
    }
    
    
    
    private int odlegloscdokwa (int x1,int y1,int x2,int y2){
        
        return ((x1-x2)*(x1-x2)+(y1-y2)*(y1-y2));
        
    }
    
    
    
    
    //metoda sprawdzająca, czy postać może iść dalej
    private boolean sprawdzkolizje (boolean gora, boolean dol, boolean lewo, boolean prawo){
        int odl_do_s=0;
        int min_do_s=(Postac.srednica_ciala/2)*(Postac.srednica_ciala/2);
        

        
        if(gora){
            

            //
            for(int i=0;i<lista_obiekt.size();i++){
                 //jeśli środek postaci jest znaduje się pomiędzy wieszchołkami obiektu
                 if( ( lista_obiekt.get(i).p_x[2]<( Postac.srodek_x))&& (Postac.srodek_x < lista_obiekt.get(i).p_x[3] )){

                     //sprawdzamy, czy postać przejdzie w następnym kroku przez linię dolną obiektu
                     if( Postac.srodek_y-Postac.srednica_ciala/2-krok_chodz<lista_obiekt.get(i).p_y[2]){
                         if(Postac.srodek_y>lista_obiekt.get(i).p_y[2]){

                            return true;
                         }
                    
                     }
                 }
                 
                 else if((Postac.srodek_x+Postac.srednica_ciala/2>lista_obiekt.get(i).p_x[2])&&(Postac.srodek_x-Postac.srednica_ciala/2<lista_obiekt.get(i).p_x[3])){
                      
                      odl_do_s=odlegloscdokwa (Postac.srodek_x,Postac.srodek_y,lista_obiekt.get(i).p_x[2],lista_obiekt.get(i).p_y[2]);

                      if(odl_do_s <min_do_s){

                          return true;
                      }
                         
                        
                      else{  
                          
                          odl_do_s=odlegloscdokwa(Postac.srodek_x,Postac.srodek_y,lista_obiekt.get(i).p_x[3],lista_obiekt.get(i).p_y[3]);

                          if ( odl_do_s<min_do_s){

                              return true;
                          };
                      }
                 }
            }
   
        }
        
        if(dol){

            
            for(int i=0;i<lista_obiekt.size();i++){
                
                 if( ( lista_obiekt.get(i).p_x[0]<( Postac.srodek_x))&& (Postac.srodek_x < lista_obiekt.get(i).p_x[1] )){

                     
                     if( Postac.srodek_y+Postac.srednica_ciala/2+krok_chodz>lista_obiekt.get(i).p_y[0]){
                         if(Postac.srodek_y<lista_obiekt.get(i).p_y[0]){//jak nie przeszedł jeszcze przez linie

                            return true;
                         }
                     
                     }
                 }
                 else if((Postac.srodek_x+Postac.srednica_ciala/2>lista_obiekt.get(i).p_x[0])&&(Postac.srodek_x-Postac.srednica_ciala/2<lista_obiekt.get(i).p_x[1])){
                      
                      odl_do_s=odlegloscdokwa (Postac.srodek_x,Postac.srodek_y,lista_obiekt.get(i).p_x[0],lista_obiekt.get(i).p_y[0]);

                      
                      if(odl_do_s <min_do_s){

                          return true;
                      }
                         
                        
                      else{  
                          
                          odl_do_s=odlegloscdokwa(Postac.srodek_x,Postac.srodek_y,lista_obiekt.get(i).p_x[1],lista_obiekt.get(i).p_y[1]);

                          
                          if ( odl_do_s<min_do_s){

                              return true;
                          };
                      }
                 }
            }
        }
        

        if(lewo){

            
            for(int i=0;i<lista_obiekt.size();i++){
                
                if( ( lista_obiekt.get(i).p_y[1]<( Postac.srodek_y))&& (Postac.srodek_y < lista_obiekt.get(i).p_y[3] )){

                     
                    if( Postac.srodek_x-Postac.srednica_ciala/2-krok_chodz<lista_obiekt.get(i).p_x[1]){
                         if(Postac.srodek_x>lista_obiekt.get(i).p_x[1]){

                            return true;
                         }
                     
                    }
                }
                
                else if((Postac.srodek_y+Postac.srednica_ciala/2>lista_obiekt.get(i).p_y[1])&&(Postac.srodek_y-Postac.srednica_ciala/2<lista_obiekt.get(i).p_y[3])){
                      
                      odl_do_s=odlegloscdokwa (Postac.srodek_x,Postac.srodek_y,lista_obiekt.get(i).p_x[1],lista_obiekt.get(i).p_y[1]);

                      
                      if(odl_do_s <min_do_s){

                          return true;
                      }
                         
                        
                      else{  
                          
                          odl_do_s=odlegloscdokwa(Postac.srodek_x,Postac.srodek_y,lista_obiekt.get(i).p_x[3],lista_obiekt.get(i).p_y[3]);

                          
                          if ( odl_do_s<min_do_s){

                              return true;
                          };
                      }
                 }
            }
        }
        
        
         if(prawo){

            
            for(int i=0;i<lista_obiekt.size();i++){
                
                if( ( lista_obiekt.get(i).p_y[0]<( Postac.srodek_y))&& (Postac.srodek_y < lista_obiekt.get(i).p_y[2] )){

                     
                    if( Postac.srodek_x+Postac.srednica_ciala/2+krok_chodz>lista_obiekt.get(i).p_x[0]){
                         if(Postac.srodek_x<lista_obiekt.get(i).p_x[0]){

                            return true;
                         }
                     
                    }
                }
                else if((Postac.srodek_y+Postac.srednica_ciala/2>lista_obiekt.get(i).p_y[0])&&(Postac.srodek_y-Postac.srednica_ciala/2<lista_obiekt.get(i).p_y[2])){
                      
                      odl_do_s=odlegloscdokwa (Postac.srodek_x,Postac.srodek_y,lista_obiekt.get(i).p_x[0],lista_obiekt.get(i).p_y[0]);

                      
                      if(odl_do_s <min_do_s){

                          return true;
                      }
                         
                        
                      else{  
                          
                          odl_do_s=odlegloscdokwa(Postac.srodek_x,Postac.srodek_y,lista_obiekt.get(i).p_x[2],lista_obiekt.get(i).p_y[2]);

                          
                          if ( odl_do_s<min_do_s){

                              return true;
                          };
                      }
                 }
            }
        }
        
    return false;
    }

}
