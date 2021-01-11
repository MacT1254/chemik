/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package projekt;

//++++Dokumentacja stworzona za pomoca programu javadoc++++++++++

/**
 * Klasa uruchamijąca grę wywołująca wczytanie zasobów oraz ustawiająca wymiary okien
 */
public class Glowny
{  
/**
 * Metoda uruchamiająca grę, wywołująca wczytanie zasobów oraz ustawiająca wymiary okien 
  @param args //
 */
    public static void main(String[] args)  
      {  
          Zasoby.wczytaj_obrazy();
          Zasoby.szerokosc_okna=1024;
          Zasoby.wysokosc_okna=768;
          SilnikGry silnik= new SilnikGry();
          silnik.obsługa_klawiatury();


      }  

}