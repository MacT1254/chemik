    /*
     * To change this license header, choose License Headers in Project Properties.
     * To change this template file, choose Tools | Templates
     * and open the template in the editor.
     */
    package projekt;
    import javax.swing.*; 
    import java.awt.*;
    import java.awt.Image;
    import java.util.ArrayList;
    import javax.swing.ImageIcon;
    import java.awt.image.BufferedImage;
    import java.io.File;
    import java.io.IOException;
    import javax.imageio.ImageIO;
    /**
     *
     * @author Mac
     */
    public class Tlo {
        static int szerokosc_okna=500;
        static int wysokosc_okna=100;
        public static int sumapunkt=0;
        public static Image tlo0;
        public static Image tlo1;
        public static Image tlo2;
        public static Image tlo3;
        static BufferedImage naczynie1;
        static BufferedImage naczynie2;
        static BufferedImage naczynie3;
        static BufferedImage postac;
        static BufferedImage lyzka;
        static BufferedImage pipeta;
        
        
        

        public static void wczytaj_obrazy(){
            try{
                pipeta=ImageIO.read(new File("src/res/pipeta.png"));
                lyzka=ImageIO.read(new File("src/res/lyzka.png"));
                postac=ImageIO.read(new File("src/res/postac.jpg"));
                naczynie1=ImageIO.read(new File("src/res/naczynie1a.png"));
                naczynie2=ImageIO.read(new File("src/res/naczynie2a.png"));
                naczynie3=ImageIO.read(new File("src/res/naczynie3a.png"));
            }
            catch(IOException e){
               System.out.println("error"); 
            }
            tlo0=wczytaj("src/res/tlo0.jpg");
            tlo1=wczytaj("src/res/tlo1.png");
            tlo2=wczytaj("src/res/tlo2.png");
            tlo3=wczytaj("src/res/tlo3.jpg");
           // =wczytaj("src/res/naczynie1a.png");
           // naczynie2=wczytaj("src/res/naczynie2.png");
            //naczynie3=wczytaj("src/res/naczynie3.png");
            System.out.println("Tlo_konst");
            
        }

        public static Image wczytaj(String sciezka){
            return new ImageIcon(sciezka).getImage();
        }


            
}

 
