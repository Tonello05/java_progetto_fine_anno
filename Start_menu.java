/**
 * Prova
 */

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.Flow;

public class Start_menu {

    JFrame frm_start_menu;
    BorderLayout layout;



    public Start_menu(){

        frm_start_menu = new JFrame("startup");
        Container c = frm_start_menu.getContentPane();

        // resize dell'immagine du sfondo
        layout = new BorderLayout(1,1);
        ImageIcon background_imageIcon = new ImageIcon(".\\images\\grass.jpg");
        Image image = background_imageIcon.getImage();
        Image newimg = image.getScaledInstance(1000, 700,  java.awt.Image.SCALE_SMOOTH);
        background_imageIcon.setImage(newimg);

        JLabel background = new JLabel(background_imageIcon);

        c.add(background);

        frm_start_menu.setVisible(true);
        frm_start_menu.setSize(1000, 700);

    }

}