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


/**
 *
 * @author Mac
 */
public class SilnikGry {
    static int odl_min=35000;
    static int krok_chodz=8;
    static int odchylka_x=0;
    static int odchylka_y=0;
    static int stol_A_pos_x=63+odchylka_x;
    static int stol_A_pos_y=59+odchylka_y;
    static int stol_B_pos_x=65+odchylka_x;
    static int stol_B_pos_y=530+odchylka_y;
    static int stol_C_pos_x=566+odchylka_x;
    static int stol_C_pos_y=529+odchylka_y;
    static int stol_sz=388;
    static int stol_wy=143;
    
    int pozycja_zatx=661;
    int pozycja_zaty=101;
    int szerokosc_przyc=879-661;
    int wysokosc_przyc=222-101;
    Font czcinka=new Font("URW Chancery L", Font.BOLD, 21);
    
    Point KM;

//tworzenie obiektów
    Obiekt stolA=new Obiekt(stol_A_pos_x, stol_A_pos_y, stol_sz, stol_wy);
    Obiekt stolB=new Obiekt(stol_B_pos_x, stol_B_pos_y, stol_sz, stol_wy);
    Obiekt stolC=new Obiekt(stol_C_pos_x, stol_C_pos_y, stol_sz, stol_wy);
    Obiekt ściana_gor=new Obiekt(0, 54, 1024, 1);
    Obiekt ściana_dol=new Obiekt(0, 679, 1024, 1);
    Obiekt ściana_lewo=new Obiekt(59, 0, 1, 768);
    Obiekt ściana_prawo=new Obiekt(950, 0, 1, 768);
    
    
    ArrayList<Stanowisko> lista_stan = new ArrayList<>();
    ArrayList<Obiekt> lista_obiekt = new ArrayList<>();
    Stanowisko stol_z_papierami=new Stanowisko(324,127);
    Stanowisko stol_z_roztw=new Stanowisko(242,600);
    
    Gra nowagra= new Gra(lista_obiekt,lista_stan);
    Okno okno_gr=new Okno("Chemik"); 

    Wzory minigra1= new Wzory();
    Okno okno_wz=new Okno("Wzory");
    Button zatwierdz_wz=new Button("Zatwierdż");


    Roztwory minigra2= new Roztwory();
    Okno okno_roz=new Okno("Roztwory");
    Button zatwierdz_roz=new Button("Zatwierdż");
    
    Zwiazki_chem minigra3= new Zwiazki_chem();
    Okno okno_zwi=new Okno("Zwiazki chemiczne");
    Button zatwierdz_zwi=new Button("Zatwierdż");
       
        
        
    SilnikGry(){

        //?
        lista_stan.add(stol_z_papierami);
        lista_stan.add(stol_z_roztw);
        lista_obiekt.add(stolA);//do usu// do usuniecia
        lista_obiekt.add(stolB);
        lista_obiekt.add(stolC);
        lista_obiekt.add(ściana_gor);
        lista_obiekt.add(ściana_dol);
        lista_obiekt.add(ściana_lewo);
        lista_obiekt.add(ściana_prawo);   
        
        okno_gr.setVisible(true);
        okno_gr.add(nowagra);
        
        okno_wz.add(minigra1);
        zatwierdz_wz.setBounds(pozycja_zatx,pozycja_zaty,szerokosc_przyc,wysokosc_przyc);
        zatwierdz_wz.setFont(czcinka);
        zatwierdz_wz.addActionListener(new B1());
        minigra1.add(zatwierdz_wz);
        
        okno_roz.setVisible(false);
        okno_roz.add(minigra2);
        okno_roz.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent me){
            KM=me.getPoint();
            
            if(me.getButton()==MouseEvent.BUTTON1){//kliknieto lewy
                minigra2.kliknietolewy(KM);
                okno_roz.repaint();
            }
            if(me.getButton()==MouseEvent.BUTTON3){//kliknieto prawy
                minigra2.kliknietoprawy(KM);
                okno_roz.repaint();
            }
            }
        });
            
        
        //})

        
        okno_gr.addKeyListener(new KeyAdapter(){

            public void keyPressed(KeyEvent ke){

                if(ke.getKeyCode()==ke.VK_RIGHT){

                     if(sprawdzkolizje(false,false,false,true)){
                         System.out.println("kolizja"+Postac.srodek_y);
                     }

                    else { 
                         System.out.println("right"+Postac.srodek_y);
                         Postac.srodek_x+=krok_chodz;
                         okno_gr.repaint();
                    }
                }

                if(ke.getKeyCode()==ke.VK_LEFT){

                     if(sprawdzkolizje(false,false,true,false)){
                         System.out.println("kolizja"+Postac.srodek_y);
                     }

                     else { 
                         System.out.println("left"+Postac.srodek_y);
                         Postac.srodek_x-=krok_chodz;
                         okno_gr.repaint();
                    }
                }

                if(ke.getKeyCode()==ke.VK_UP){

                    if(sprawdzkolizje(true,false,false,false)){
                         System.out.println("kolizja"+Postac.srodek_y);
                    }

                    else { 
                         System.out.println("up"+Postac.srodek_y);
                         Postac.srodek_y-=krok_chodz;
                         okno_gr.repaint();
                    }
                }

                if(ke.getKeyCode()==ke.VK_DOWN){

                     if(sprawdzkolizje(false,true,false,false)){
                         System.out.println("kolizja"+Postac.srodek_y);
                     }

                     else { 
                         System.out.println("down"+Postac.srodek_y);
                         Postac.srodek_y+=krok_chodz;
                         okno_gr.repaint();
                     }
                }

                if(ke.getKeyCode()==ke.VK_M){
                    int odl;
                    for(int m=0; m < lista_stan.size(); m++){

                        odl=odlegloscdokwa(Postac.srodek_x,Postac.srodek_y,lista_stan.get(m).run_point_x,lista_stan.get(m).run_point_y);

                        if(odl<odl_min){
                             System.out.println(":) "+odl+" Postacx "+Postac.srodek_x+" Postacy "+Postac.srodek_y+" runx "+lista_stan.get(m).run_point_x+" runy"+lista_stan.get(m).run_point_y);
                            if(m==0) okno_wz.setVisible(true);
                            if(m==1) okno_roz.setVisible(true);
                            if(m==2) okno_zwi.setVisible(true);
                        }
                    }  
                }
            }
        });
    }
    
    private class B1 implements ActionListener {
            public void actionPerformed(ActionEvent e) {
                Tlo.sumapunkt=+minigra1.zatwierdz();
                System.out.println(Tlo.sumapunkt);
                okno_wz.setVisible(false);
            }
    }
    
    
     public boolean sprawdzkolizje (boolean gora, boolean dol, boolean lewo, boolean prawo){
        int odl_do_s=0;
        int min_do_s=(Postac.srednica_ciala/2)*(Postac.srednica_ciala/2);
        
         //System.out.println("halo kolizja???");
        
        if(gora){
            
           // System.out.println("kolizja gora???");
            
            for(int i=0;i<lista_obiekt.size();i++){
                
                 if( ( lista_obiekt.get(i).p_x[2]<( Postac.srodek_x))&& (Postac.srodek_x < lista_obiekt.get(i).p_x[3] )){
                     //System.out.println("kolizja???");
                     
                     if( Postac.srodek_y-Postac.srednica_ciala/2-krok_chodz<lista_obiekt.get(i).p_y[2]){
                         if(Postac.srodek_y>lista_obiekt.get(i).p_y[2]){
                           // System.out.println("kolizja!!!!!!!!!!!!!"+lista_obiekt.get(i).p_y[2]);
                            return true;
                         }
                    
                     }
                 }
                 
                 else if((Postac.srodek_x+Postac.srednica_ciala/2>lista_obiekt.get(i).p_x[2])&&(Postac.srodek_x-Postac.srednica_ciala/2<lista_obiekt.get(i).p_x[3])){
                      
                      odl_do_s=odlegloscdokwa (Postac.srodek_x,Postac.srodek_y,lista_obiekt.get(i).p_x[2],lista_obiekt.get(i).p_y[2]);
                      System.out.println("kolizjarog???? i:"+i+" odl "+odl_do_s+" min "+min_do_s); 
                      if(odl_do_s <min_do_s){
                          System.out.println("!!!!!!!!!kolizjarog!!!");
                          return true;
                      }
                         
                        
                      else{  
                          
                          odl_do_s=odlegloscdokwa(Postac.srodek_x,Postac.srodek_y,lista_obiekt.get(i).p_x[3],lista_obiekt.get(i).p_y[3]);
                          System.out.println("kolizjarog2 i:"+i+" odl "+odl_do_s+" min "+min_do_s);
                          if ( odl_do_s<min_do_s){
                              System.out.println("!!!!!!!!!kolizjarog!!!");
                              return true;
                          };
                      }
                 }
            }
   
        }
        
        if(dol){
            System.out.println("kolizja dol???");
            
            for(int i=0;i<lista_obiekt.size();i++){
                
                 if( ( lista_obiekt.get(i).p_x[0]<( Postac.srodek_x))&& (Postac.srodek_x < lista_obiekt.get(i).p_x[1] )){
                     System.out.println("kolizja???");
                     
                     if( Postac.srodek_y+Postac.srednica_ciala/2+krok_chodz>lista_obiekt.get(i).p_y[0]){
                         if(Postac.srodek_y<lista_obiekt.get(i).p_y[0]){//jak nie przeszedł jeszcze przez linie
                            System.out.println("kolizja!!!!!!!!!!!!!"+lista_obiekt.get(i).p_y[0]);
                            return true;
                         }
                     
                     }
                 }
                 else if((Postac.srodek_x+Postac.srednica_ciala/2>lista_obiekt.get(i).p_x[0])&&(Postac.srodek_x-Postac.srednica_ciala/2<lista_obiekt.get(i).p_x[1])){
                      
                      odl_do_s=odlegloscdokwa (Postac.srodek_x,Postac.srodek_y,lista_obiekt.get(i).p_x[0],lista_obiekt.get(i).p_y[0]);
                      System.out.println("kolizjarog???? i:"+i+" odl "+odl_do_s+" min "+min_do_s);
                      
                      if(odl_do_s <min_do_s){
                          System.out.println("!!!!!!!!!kolizjarog!!!");
                          return true;
                      }
                         
                        
                      else{  
                          
                          odl_do_s=odlegloscdokwa(Postac.srodek_x,Postac.srodek_y,lista_obiekt.get(i).p_x[1],lista_obiekt.get(i).p_y[1]);
                          System.out.println("kolizjarog2 i:"+i+" odl "+odl_do_s+" min "+min_do_s);
                          
                          if ( odl_do_s<min_do_s){
                              System.out.println("!!!!!!!!!kolizjarog!!!");
                              return true;
                          };
                      }
                 }
            }
        }
        

        if(lewo){
            System.out.println("kolizja lewo???");
            
            for(int i=0;i<lista_obiekt.size();i++){
                
                if( ( lista_obiekt.get(i).p_y[1]<( Postac.srodek_y))&& (Postac.srodek_y < lista_obiekt.get(i).p_y[3] )){
                     System.out.println("kolizja???");
                     
                    if( Postac.srodek_x-Postac.srednica_ciala/2-krok_chodz<lista_obiekt.get(i).p_x[1]){
                         if(Postac.srodek_x>lista_obiekt.get(i).p_x[1]){
                            System.out.println("kolizja!!!!!!!!!!!!!"+lista_obiekt.get(i).p_x[1]);
                            return true;
                         }
                     
                    }
                }
                
                else if((Postac.srodek_y+Postac.srednica_ciala/2>lista_obiekt.get(i).p_y[1])&&(Postac.srodek_y-Postac.srednica_ciala/2<lista_obiekt.get(i).p_y[3])){
                      
                      odl_do_s=odlegloscdokwa (Postac.srodek_x,Postac.srodek_y,lista_obiekt.get(i).p_x[1],lista_obiekt.get(i).p_y[1]);
                      System.out.println("kolizjarog???? i:"+i+" odl "+odl_do_s+" min "+min_do_s);
                      
                      if(odl_do_s <min_do_s){
                          System.out.println("!!!!!!!!!kolizjarog!!!");
                          return true;
                      }
                         
                        
                      else{  
                          
                          odl_do_s=odlegloscdokwa(Postac.srodek_x,Postac.srodek_y,lista_obiekt.get(i).p_x[3],lista_obiekt.get(i).p_y[3]);
                          System.out.println("kolizjarog2 i:"+i+" odl "+odl_do_s+" min "+min_do_s);
                          
                          if ( odl_do_s<min_do_s){
                              System.out.println("!!!!!!!!!kolizjarog!!!");
                              return true;
                          };
                      }
                 }
            }
        }
        
        
         if(prawo){
            System.out.println("kolizja prawo???");
            
            for(int i=0;i<lista_obiekt.size();i++){
                
                if( ( lista_obiekt.get(i).p_y[0]<( Postac.srodek_y))&& (Postac.srodek_y < lista_obiekt.get(i).p_y[2] )){
                     System.out.println("kolizja???");
                     
                    if( Postac.srodek_x+Postac.srednica_ciala/2+krok_chodz>lista_obiekt.get(i).p_x[0]){
                         if(Postac.srodek_x<lista_obiekt.get(i).p_x[0]){
                            System.out.println("kolizja!!!!!!!!!!!!!"+lista_obiekt.get(i).p_x[0]);
                            return true;
                         }
                     
                    }
                }
                else if((Postac.srodek_y+Postac.srednica_ciala/2>lista_obiekt.get(i).p_y[0])&&(Postac.srodek_y-Postac.srednica_ciala/2<lista_obiekt.get(i).p_y[2])){
                      
                      odl_do_s=odlegloscdokwa (Postac.srodek_x,Postac.srodek_y,lista_obiekt.get(i).p_x[0],lista_obiekt.get(i).p_y[0]);
                      System.out.println("kolizjarog???? i:"+i+" odl "+odl_do_s+" min "+min_do_s);
                      
                      if(odl_do_s <min_do_s){
                          System.out.println("!!!!!!!!!kolizjarog!!!");
                          return true;
                      }
                         
                        
                      else{  
                          
                          odl_do_s=odlegloscdokwa(Postac.srodek_x,Postac.srodek_y,lista_obiekt.get(i).p_x[2],lista_obiekt.get(i).p_y[2]);
                          System.out.println("kolizjarog2 i:"+i+" odl "+odl_do_s+" min "+min_do_s);
                          
                          if ( odl_do_s<min_do_s){
                              System.out.println("!!!!!!!!!kolizjarog!!!");
                              return true;
                          };
                      }
                 }
            }
        }
        
    return false;
    }
  
    
    
    public int odlegloscdokwa (int x1,int y1,int x2,int y2){
        
        return ((x1-x2)*(x1-x2)+(y1-y2)*(y1-y2));
        
    }
}
