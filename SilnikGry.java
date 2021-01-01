/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekt;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


/**
 *
 * @author Mac
 */
public class SilnikGry {
   static int odl_min=65000;
   static int krok_chodz=8;
   
   
   Gra nowagra= new Gra();
   Okno okno_gr=new Okno("Chemik");
   
   SilnikGry(){
       okno_gr.setVisible(true);
       okno_gr.add(nowagra);
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
                    for(int m=0; m < Tlo.lista_stan.size(); m++){

                       odl=odlegloscdokwa(Postac.srodek_x,Postac.srodek_y,Tlo.lista_stan.get(m).run_point_x,Tlo.lista_stan.get(m).run_point_y);

                       if(odl<odl_min){
                            System.out.println(":) "+odl+" Postacx "+Postac.srodek_x+" Postacy "+Postac.srodek_y+" runx "+Tlo.lista_stan.get(m).run_point_x+" runy"+Tlo.lista_stan.get(m).run_point_y);
                           if(m==0){ Wzory minigra1= new Wzory();
                                     Okno okno_wz=new Okno("Wzory");
                                     okno_wz.setVisible(true);
                                     okno_wz.add(minigra1);
                           }
                       }

                       
                   }

                   okno_gr.repaint();
               }
            }
           }
        );
    }

     public boolean sprawdzkolizje (boolean gora, boolean dol, boolean lewo, boolean prawo){
        int odl_do_s=0;
        int min_do_s=(Postac.srednica_ciala/2)*(Postac.srednica_ciala/2);
        
         //System.out.println("halo kolizja???");
        
        if(gora){
            
           // System.out.println("kolizja gora???");
            
            for(int i=0;i<Tlo.lista_obiekt.size();i++){
                
                 if( ( Tlo.lista_obiekt.get(i).wieszcholki_x[2]<( Postac.srodek_x))&& (Postac.srodek_x < Tlo.lista_obiekt.get(i).wieszcholki_x[3] )){
                     //System.out.println("kolizja???");
                     
                     if( Postac.srodek_y-Postac.srednica_ciala/2-krok_chodz<Tlo.lista_obiekt.get(i).wieszcholki_y[2]){
                         if(Postac.srodek_y>Tlo.lista_obiekt.get(i).wieszcholki_y[2]){
                           // System.out.println("kolizja!!!!!!!!!!!!!"+Tlo.lista_obiekt.get(i).wieszcholki_y[2]);
                            return true;
                         }
                    
                     }
                 }
                 
                 else if((Postac.srodek_x+Postac.srednica_ciala/2>Tlo.lista_obiekt.get(i).wieszcholki_x[2])&&(Postac.srodek_x-Postac.srednica_ciala/2<Tlo.lista_obiekt.get(i).wieszcholki_x[3])){
                      
                      odl_do_s=odlegloscdokwa (Postac.srodek_x,Postac.srodek_y,Tlo.lista_obiekt.get(i).wieszcholki_x[2],Tlo.lista_obiekt.get(i).wieszcholki_y[2]);
                      System.out.println("kolizjarog???? i:"+i+" odl "+odl_do_s+" min "+min_do_s); 
                      if(odl_do_s <min_do_s){
                          System.out.println("!!!!!!!!!kolizjarog!!!");
                          return true;
                      }
                         
                        
                      else{  
                          
                          odl_do_s=odlegloscdokwa(Postac.srodek_x,Postac.srodek_y,Tlo.lista_obiekt.get(i).wieszcholki_x[3],Tlo.lista_obiekt.get(i).wieszcholki_y[3]);
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
            
            for(int i=0;i<Tlo.lista_obiekt.size();i++){
                
                 if( ( Tlo.lista_obiekt.get(i).wieszcholki_x[0]<( Postac.srodek_x))&& (Postac.srodek_x < Tlo.lista_obiekt.get(i).wieszcholki_x[1] )){
                     System.out.println("kolizja???");
                     
                     if( Postac.srodek_y+Postac.srednica_ciala/2+krok_chodz>Tlo.lista_obiekt.get(i).wieszcholki_y[0]){
                         if(Postac.srodek_y<Tlo.lista_obiekt.get(i).wieszcholki_y[0]){//jak nie przeszedł jeszcze przez linie
                            System.out.println("kolizja!!!!!!!!!!!!!"+Tlo.lista_obiekt.get(i).wieszcholki_y[0]);
                            return true;
                         }
                     
                     }
                 }
                 else if((Postac.srodek_x+Postac.srednica_ciala/2>Tlo.lista_obiekt.get(i).wieszcholki_x[0])&&(Postac.srodek_x-Postac.srednica_ciala/2<Tlo.lista_obiekt.get(i).wieszcholki_x[1])){
                      
                      odl_do_s=odlegloscdokwa (Postac.srodek_x,Postac.srodek_y,Tlo.lista_obiekt.get(i).wieszcholki_x[0],Tlo.lista_obiekt.get(i).wieszcholki_y[0]);
                      System.out.println("kolizjarog???? i:"+i+" odl "+odl_do_s+" min "+min_do_s);
                      
                      if(odl_do_s <min_do_s){
                          System.out.println("!!!!!!!!!kolizjarog!!!");
                          return true;
                      }
                         
                        
                      else{  
                          
                          odl_do_s=odlegloscdokwa(Postac.srodek_x,Postac.srodek_y,Tlo.lista_obiekt.get(i).wieszcholki_x[1],Tlo.lista_obiekt.get(i).wieszcholki_y[1]);
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
            
            for(int i=0;i<Tlo.lista_obiekt.size();i++){
                
                if( ( Tlo.lista_obiekt.get(i).wieszcholki_y[1]<( Postac.srodek_y))&& (Postac.srodek_y < Tlo.lista_obiekt.get(i).wieszcholki_y[3] )){
                     System.out.println("kolizja???");
                     
                    if( Postac.srodek_x-Postac.srednica_ciala/2-krok_chodz<Tlo.lista_obiekt.get(i).wieszcholki_x[1]){
                         if(Postac.srodek_x>Tlo.lista_obiekt.get(i).wieszcholki_x[1]){
                            System.out.println("kolizja!!!!!!!!!!!!!"+Tlo.lista_obiekt.get(i).wieszcholki_x[1]);
                            return true;
                         }
                     
                    }
                }
                
                else if((Postac.srodek_y+Postac.srednica_ciala/2>Tlo.lista_obiekt.get(i).wieszcholki_y[1])&&(Postac.srodek_y-Postac.srednica_ciala/2<Tlo.lista_obiekt.get(i).wieszcholki_y[3])){
                      
                      odl_do_s=odlegloscdokwa (Postac.srodek_x,Postac.srodek_y,Tlo.lista_obiekt.get(i).wieszcholki_x[1],Tlo.lista_obiekt.get(i).wieszcholki_y[1]);
                      System.out.println("kolizjarog???? i:"+i+" odl "+odl_do_s+" min "+min_do_s);
                      
                      if(odl_do_s <min_do_s){
                          System.out.println("!!!!!!!!!kolizjarog!!!");
                          return true;
                      }
                         
                        
                      else{  
                          
                          odl_do_s=odlegloscdokwa(Postac.srodek_x,Postac.srodek_y,Tlo.lista_obiekt.get(i).wieszcholki_x[3],Tlo.lista_obiekt.get(i).wieszcholki_y[3]);
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
            
            for(int i=0;i<Tlo.lista_obiekt.size();i++){
                
                if( ( Tlo.lista_obiekt.get(i).wieszcholki_y[0]<( Postac.srodek_y))&& (Postac.srodek_y < Tlo.lista_obiekt.get(i).wieszcholki_y[2] )){
                     System.out.println("kolizja???");
                     
                    if( Postac.srodek_x+Postac.srednica_ciala/2+krok_chodz>Tlo.lista_obiekt.get(i).wieszcholki_x[0]){
                         if(Postac.srodek_x<Tlo.lista_obiekt.get(i).wieszcholki_x[0]){
                            System.out.println("kolizja!!!!!!!!!!!!!"+Tlo.lista_obiekt.get(i).wieszcholki_x[0]);
                            return true;
                         }
                     
                    }
                }
                else if((Postac.srodek_y+Postac.srednica_ciala/2>Tlo.lista_obiekt.get(i).wieszcholki_y[0])&&(Postac.srodek_y-Postac.srednica_ciala/2<Tlo.lista_obiekt.get(i).wieszcholki_y[2])){
                      
                      odl_do_s=odlegloscdokwa (Postac.srodek_x,Postac.srodek_y,Tlo.lista_obiekt.get(i).wieszcholki_x[0],Tlo.lista_obiekt.get(i).wieszcholki_y[0]);
                      System.out.println("kolizjarog???? i:"+i+" odl "+odl_do_s+" min "+min_do_s);
                      
                      if(odl_do_s <min_do_s){
                          System.out.println("!!!!!!!!!kolizjarog!!!");
                          return true;
                      }
                         
                        
                      else{  
                          
                          odl_do_s=odlegloscdokwa(Postac.srodek_x,Postac.srodek_y,Tlo.lista_obiekt.get(i).wieszcholki_x[2],Tlo.lista_obiekt.get(i).wieszcholki_y[2]);
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
