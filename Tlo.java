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
    /**
     *
     * @author Mac
     */
    public class Tlo {
        
        static Path p;
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
        static BufferedImage pipeta2;
        static String pomoc_gr;
        

        public static void wczytaj_obrazy(){
            
            try{

                postac=ImageIO.read(new File("src/res/postac.jpg"));
                
                pipeta=ImageIO.read(new File("src/res/pipeta.png"));
                pipeta2=ImageIO.read(new File("src/res/pipeta2.png"));
                lyzka=ImageIO.read(new File("src/res/lyzka.png"));
                
                
                naczynie1=ImageIO.read(new File("src/res/naczynie1a.png"));
                naczynie2=ImageIO.read(new File("src/res/naczynie2a.png"));
                naczynie3=ImageIO.read(new File("src/res/naczynie3a.png"));
            }
            
            catch(IOException e){
               e.printStackTrace(); 
            }
            
            tlo0=wczytaj("src/res/tlo0.jpg");
            tlo1=wczytaj("src/res/tlo1.png");
            tlo2=wczytaj("src/res/tlo2.png");
            tlo3=wczytaj("src/res/tlo3a.png");
            
        }

        public static Image wczytaj(String sciezka){
            return new ImageIcon(sciezka).getImage();
        }
           
}

 
