/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekt;

import java.awt.event.*;
import java.awt.*;
import java.lang.Object;
import java.util.ArrayList;
import javax.swing.JFrame;


/**
 *
 * @author Mac
 */
public class SilnikGry {
    
    long MAX_TIME=Long.MAX_VALUE;
    static int odl_min=35000;
    static int krok_chodz=8;
    static int stol_A_pos_x=63;
    static int stol_A_pos_y=59;
    static int stol_B_pos_x=65;
    static int stol_B_pos_y=530;
    static int stol_C_pos_x=566;
    static int stol_C_pos_y=529;
    static int stol_sz=388;
    static int stol_wy=143;
    
    static final String pomoc_gra="pomoc_gra";
    static final String pomoc_wz="pomoc_wz";
    static final String pomoc_roz="pomoc_roz";
    static final String pomoc_zwi="pomoc_zwi";
    
    int pozycja_zatx=661;
    int pozycja_zaty=101;
    int szerokosc_przyc=879-661;
    int wysokosc_przyc=222-101;
    
    boolean minigra1_uruch;
    boolean minigra2_uruch;
    boolean minigra3_uruch;
    
    
    Font czcinka=new Font("URW Chancery L", Font.BOLD, 21);
    
    Point KM_wz;
    Point KM_roz;
    Point KM_zwi;
    
//tworzenie obiektów
    Obiekt stolA;
    Obiekt stolB;
    Obiekt stolC;
    Obiekt ściana_gor;
    Obiekt ściana_dol;
    Obiekt ściana_lewo;
    Obiekt ściana_prawo;
    
    
    ArrayList<Stanowisko> lista_stan =new ArrayList<>();;
    ArrayList<Obiekt> lista_obiekt = new ArrayList<>();
    Stanowisko stol_z_papierami;
    Stanowisko stol_z_roztw;
    Stanowisko stol_z_zwi;
    Stanowisko wyjscie;
    
    Gra nowagra;
    Okno okno_gr; 

    Wzory minigra1;
    Okno okno_wz;
    Button zatwierdz_wz;


    Roztwory minigra2;
    Okno okno_roz;
    Button zatwierdz_roz;
    
    Zwiazki_chem minigra3; 
    Okno okno_zwi;
    Button zatwierdz_zwi;
       
        
   long startTime = System.currentTimeMillis(); 
   
    SilnikGry(){
        
        minigra1_uruch=false;
        minigra2_uruch=false;
        minigra3_uruch=false;
       
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
        wyjscie = new Stanowisko(1053,160);  
        
        lista_stan.add(stol_z_papierami);
        lista_stan.add(stol_z_roztw);
        lista_stan.add(stol_z_zwi);
        lista_stan.add(wyjscie);
        
        nowagra = new Gra(lista_obiekt,lista_stan);
        okno_gr = new Okno("Chemik");
        okno_gr.setVisible(true);
        okno_gr.add(nowagra);
        okno_gr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
   
    }  
    
    void poruszanie_postacia(){   

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
                               inic_minigry_wzory();
                               minigra1_uruch=true;
                           }
                           if(m==1 && minigra2_uruch==false) {
                               inic_minigry_roztw();
                               minigra1_uruch=true;
                           }
                           if(m==2 && minigra3_uruch==false){
                               inic_minigry_zwi();
                               minigra1_uruch=true;
                           }
                           if (m==3){
                               wyjscie();
                           }
                       }
                   }  
               }
               
               if(ke.getKeyCode()==ke.VK_P){
                   inic_pomoc(pomoc_gra);
               }
               
   
           }
       });
    }
    void reset(){
        Postac.srodek_x=500;
        Postac.srodek_y=100;
        minigra1_uruch=false;
        minigra2_uruch=false;
        minigra3_uruch=false;
        Tlo.sumapunkt=0;
    }
    
    void inic_pomoc(String plik){
      
        Pomoc pomoc = new Pomoc(plik);
        Okno okno_pomocy = new Okno("Pomoc");
        pomoc.setBackground(Color.white);
        okno_pomocy.add(pomoc);
        okno_pomocy.setVisible(true);
    }



    void inic_minigry_wzory(){

        minigra1 = new Wzory();
        okno_wz = new Okno("Wzory");
        zatwierdz_wz = new Button("Zatwierdż");
        okno_wz.add(minigra1);

        zatwierdz_wz.setBounds(pozycja_zatx,pozycja_zaty,szerokosc_przyc,wysokosc_przyc);
        zatwierdz_wz.setFont(czcinka);
        zatwierdz_wz.addActionListener(new B1());
        minigra1.add(zatwierdz_wz);

        okno_wz.setVisible(true);
        
        okno_wz.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent mewz){
            KM_wz=mewz.getPoint();

            if(mewz.getButton()==MouseEvent.BUTTON2){//kliknieto lewy
                 inic_pomoc(pomoc_wz);
            }
            }
        });
            
        okno_wz.addKeyListener(new KeyAdapter(){
            
        @Override
        public void keyPressed(KeyEvent kewz){
              System.out.println("jhbkgbhjbhjhjb");
               if(kewz.VK_P==kewz.getKeyCode()){
                   inic_pomoc(pomoc_wz);
               }
           }
        });


    }
    
    
    
    
    
    void inic_minigry_roztw(){

        minigra2 = new Roztwory();
        okno_roz = new Okno("Roztwory");
        zatwierdz_roz = new Button("Zatwierdż");
        okno_roz.add(minigra2);
        zatwierdz_roz.setBounds(679,8,szerokosc_przyc,wysokosc_przyc);
        zatwierdz_roz.setFont(czcinka);
        zatwierdz_roz.addActionListener(new B2());
        minigra2.add(zatwierdz_roz);
        minigra2.setLayout(null);
        

        EventQueue.invokeLater(() -> {
             okno_roz.repaint();

         });


        okno_roz.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent meroz){
            KM_roz=meroz.getPoint();

            if(meroz.getButton()==MouseEvent.BUTTON1){//kliknieto lewy
                minigra2.kliknietolewy(KM_roz);
                okno_roz.repaint();
            }
            
            if(meroz.getButton()==MouseEvent.BUTTON2){//kliknieto srod
                inic_pomoc(pomoc_roz);
                
            }
            
            if(meroz.getButton()==MouseEvent.BUTTON3){//kliknieto prawy
                minigra2.kliknietoprawy(KM_roz);
                okno_roz.repaint();
            }
            }
        });
        
        okno_wz.addKeyListener(new KeyAdapter(){
        @Override   
        public void keyPressed(KeyEvent ke){

            if(ke.getKeyCode()==ke.VK_P){
                inic_pomoc(pomoc_roz);
            }
        }
        });
        
        okno_roz.setVisible(true);
}




        
    void inic_minigry_zwi(){

        minigra3=new Zwiazki_chem();
        okno_zwi = new Okno("Zwiazki chemiczne");
        zatwierdz_zwi = new Button("Zatwierdż");
        zatwierdz_zwi.setBounds(679,8,szerokosc_przyc,wysokosc_przyc);
        zatwierdz_zwi.setFont(czcinka);
        zatwierdz_zwi.addActionListener(new B3());
        okno_zwi.add(minigra3);
        minigra3.add(zatwierdz_zwi);
        minigra3.setLayout(null);
        
        EventQueue.invokeLater(() -> {
                okno_zwi.repaint();

       });

        okno_zwi.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent mezwi){
            KM_zwi=mezwi.getPoint();


            if(mezwi.getButton()==MouseEvent.BUTTON1){//kliknieto lewy
                minigra3.kliknietolewy(KM_zwi);
                okno_zwi.repaint();

            }
            
            if(mezwi.getButton()==MouseEvent.BUTTON2){//kliknieto prawy
               inic_pomoc(pomoc_zwi);
            }
            
            if(mezwi.getButton()==MouseEvent.BUTTON3){//kliknieto prawy
                minigra3.kliknietoprawy();
                okno_zwi.repaint();
            }
            }
        });
        
        okno_zwi.addKeyListener(new KeyAdapter(){
        @Override   
        public void keyPressed(KeyEvent ke){

               if(ke.getKeyCode()==ke.VK_M){
                   inic_pomoc(pomoc_zwi);
               }
        }
        });
        
        okno_zwi.setVisible(true);
     } 

    void wyjscie(){
        okno_gr.setVisible(false);
        okno_gr.dispose();
        System.exit(0);
    }



    private class B1 implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                Tlo.sumapunkt=+minigra1.zatwierdz();

                okno_wz.setVisible(false);
                okno_wz.dispose();
            }
    }
    
    
    
    
    private class B2 implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                Tlo.sumapunkt=+minigra2.zatwierdz();

                okno_roz.setVisible(false);
                okno_roz.dispose();
            }
    }
    
    
    
    
    
    private class B3 implements ActionListener {
        
            @Override
            public void actionPerformed(ActionEvent e) {
                Tlo.sumapunkt=+minigra3.zatwierdz();

                okno_zwi.setVisible(false);
                okno_zwi.dispose();
            }
    }
    
    
    
    public int odlegloscdokwa (int x1,int y1,int x2,int y2){
        
        return ((x1-x2)*(x1-x2)+(y1-y2)*(y1-y2));
        
    }
    
    
    
    
    
    public boolean sprawdzkolizje (boolean gora, boolean dol, boolean lewo, boolean prawo){
        int odl_do_s=0;
        int min_do_s=(Postac.srednica_ciala/2)*(Postac.srednica_ciala/2);
        

        
        if(gora){
            

            
            for(int i=0;i<lista_obiekt.size();i++){
                
                 if( ( lista_obiekt.get(i).p_x[2]<( Postac.srodek_x))&& (Postac.srodek_x < lista_obiekt.get(i).p_x[3] )){

                     
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
