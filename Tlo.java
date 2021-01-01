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
        public static Image cursorImage;

        public static Image tlo0;
        public static Image tlo1;
        public static Image tlo2;
        public static Image tlo3;
        //public static Image obrazpos;
        //static BufferedImage obrazpos=new BufferedImage(98,105,0);
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


        //tworzenie obiektów
        static Obiekt stolA=new Obiekt(stol_A_pos_x, stol_A_pos_y, stol_sz, stol_wy);
        static Obiekt stolB=new Obiekt(stol_B_pos_x, stol_B_pos_y, stol_sz, stol_wy);
        static Obiekt stolC=new Obiekt(stol_C_pos_x, stol_C_pos_y, stol_sz, stol_wy);
        static Obiekt ściana_gor=new Obiekt(0, 54, 1024, 1);
        static Obiekt ściana_dol=new Obiekt(0, 679, 1024, 1);
        static Obiekt ściana_lewo=new Obiekt(59, 0, 1, 768);
        static Obiekt ściana_prawo=new Obiekt(950, 0, 1, 768);


        static ArrayList<Stanowisko> lista_stan = new ArrayList<>(7);
        static ArrayList<Obiekt> lista_obiekt = new ArrayList<>(3);
        static Stanowisko stol_z_papierami=new Stanowisko(120,40);

        Tlo(){lista_stan.add(stol_z_papierami);
            lista_obiekt.add(stolA);//do usu// do usuniecia
            lista_obiekt.add(stolB);
            lista_obiekt.add(stolC);
            lista_obiekt.add(ściana_gor);
            lista_obiekt.add(ściana_dol);
            lista_obiekt.add(ściana_lewo);
            lista_obiekt.add(ściana_prawo);
            tlo0=wczytaj_obraz("src/res/tlo0.jpg");
            tlo1=wczytaj_obraz("src/res/tlo1.jpg");
            tlo2=wczytaj_obraz("src/res/tlo2.jpg");
            tlo3=wczytaj_obraz("src/res/tlo3.jpg");
            System.out.println("Tlo_konst");
        }

        public static Image wczytaj_obraz(String sciezka){
        return new ImageIcon(sciezka).getImage();
    }//koniec loadImage();


            
}

       /* public static Image wczytaj(String sciezka){
            return new ImageIcon(sciezka).getImage();

        }
        */
    



