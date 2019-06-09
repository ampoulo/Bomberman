/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites.vue;

import entites.Mur;
import entites.Position;
import java.awt.Color;
import java.awt.Container;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
/**
 *
 * @author ibrahima
 */
    public class VueMur extends Mur{

        private Image mur; 
        private ImageIcon iconMur;

        public VueMur(Position p) {
           super(p);
           iconMur = new ImageIcon(getClass().getResource("../../image/mur1.png"));
           this.mur = this.iconMur.getImage();
           //this.setIcon(iconMur);
           //this.setBackground(Color.BLACK);
           //this.setEnabled(false);
          
        }

        public Image getMur(){
            return this.mur;
        }
    }

    
