/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package projekt;

/**
 *
 * @author Mac
 */

 
public class Glowny
{  
   

public static void main(String[] args)  
  {   Tlo.wczytaj_obrazy();
      Tlo.szerokosc_okna=1024;
      Tlo.wysokosc_okna=768;
      SilnikGry silnik= new SilnikGry();
      silnik.poruszanie_postacia();
   // Wzory minigra1 = new Wzory();
   // Roztwory minigra2 = new Roztwory();
     
     
     
     
  }  

}