    /*
     * To change this license header, choose License Headers in Project Properties.
     * To change this template file, choose Tools | Templates
     * and open the template in the editor.
     */
    package projekt;
    import java.awt.Image;
    import javax.swing.ImageIcon;
    import java.awt.image.BufferedImage;
    import java.io.File;
    import java.io.IOException;
    import javax.imageio.ImageIO;
    import java.nio.file.Path;
    import java.awt.image.WritableRaster;
    
    /**
     * Klasa przechowująca zasoby graficzne
     * 
    */ 
    public class Zasoby {
        /**sciezka do pliku tekstowego*/
        static Path p;
        /**zmienna przechowująca szerokość okna   * */
        public static int szerokosc_okna=1024;
        /**zmienna przechowująca wysokość okna  */
        public static int wysokosc_okna=768;
        /**zmienna przechowująca sume punktów  */
        public static int sumapunkt=0;
        /**obraz tła gry   */
        public static Image tlo0;
        /**obraz tła minigry "Wzory"  */
        public static Image tlo1;
        /**obraz tła minigry "Roztwory"  */
        public static Image tlo2;
        /**obraz tła minigry "Związki chemiczne*/
        public static Image tlo3;
        /**buforowany obraz naczynia1*/
        public static BufferedImage naczynie1;
        /**buforowany obraz naczynia2*/
        public static BufferedImage naczynie2;
        /**buforowany obraz naczynia3*/
        public static BufferedImage naczynie3;
         /**buforowany obraz naczynia_do_kolorowania*/  /**  */
        public static BufferedImage nacz_kolor;
        /**buforowany obraz postaci*/
        public static BufferedImage postac;
        /**buforowany obraz łyżki*/
        public static BufferedImage lyzka;
        /**buforowany obraz pipety w wersji pierwszej*/
        public static BufferedImage pipeta;
        /**buforowany obraz pipety w wersji drugiej*/
        public static BufferedImage pipeta2;
        /**raster do kolorowania zdjecia  */
        public static WritableRaster raster;
        /**tekst wyświetlany po wyjściu z gry  */
        public static String wyjscie_text=" ";
        
        
        /**buforowany obraz naczynia do wczytania*/
        private static BufferedImage nacz_buf;
        
        
        
        
        
        /**
         * Metoda ładująca obrazy
         */
        public static void wczytaj_obrazy(){
            
           
            postac=wczytaj_buf_zdjecie("src/res/postac.jpg");

            pipeta=wczytaj_buf_zdjecie("src/res/pipeta.png");
            pipeta2=wczytaj_buf_zdjecie("src/res/pipeta2.png");
            lyzka=wczytaj_buf_zdjecie("src/res/lyzka.png");


            naczynie1=wczytaj_buf_zdjecie("src/res/naczynie1a.png");
            naczynie2=wczytaj_buf_zdjecie("src/res/naczynie2a.png");
            naczynie3=wczytaj_buf_zdjecie("src/res/naczynie3a.png");

            tlo0=wczytaj("src/res/tlo0b.png");
            tlo1=wczytaj("src/res/tlo1a.png");
            tlo2=wczytaj("src/res/tlo2b.png");
            tlo3=wczytaj("src/res/tlo3a.png");
            
        }
        
        
        
        
        
        /**
        * Metoda wczytania obiektu klasy Image na podstawie ścieżki
        * podanej jako String
        * @param sciezka sciezka do pliku z obrazem
        * @return zwraca obiekt Image
        */
        public static Image wczytaj(String sciezka){
            return new ImageIcon(sciezka).getImage();
        }
        
        
        
        
        
        /**
        * Metoda wczytania obiektu klasy BufferedImage na podstawie ścieżki
        * podanej jako String
        * @param sciezka sciezka do pliku z obrazem
        * @return zwraca obiekt BufferedImage
        */
        public static BufferedImage wczytaj_buf_zdjecie(String sciezka){

            try{
                nacz_buf=ImageIO.read(new File(sciezka));
            }
            
            catch(IOException e){
               e.printStackTrace(); 
            }
            
            return (nacz_buf);
        }

}

 
