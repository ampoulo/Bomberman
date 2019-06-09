/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites.vue;

import entites.CaseVide;
import entites.Position;
import java.awt.Color;
import java.awt.Container;
import java.awt.Event;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MenuComponent;
import java.awt.MenuContainer;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author ibrahima
 */
public class VueCaseVide extends CaseVide{
     

        private Image casevide; 
        private ImageIcon iconcasevide;

        public VueCaseVide(Position p) {
           super(p);
           
           iconcasevide = new ImageIcon(getClass().getResource("../../image/casevide1.png"));
           this.casevide = this.iconcasevide.getImage();
           //this.setEditable(false);
           //this.setBackground(Color.GREEN);
          //this.setIcon(iconcasevide);
        }
        
        public Image getCaseVide(){
            return this.casevide;
        }
        public void setImage(Image img){
            this.casevide=img;
        }


        

	

        //Image image = getToolkit().getImage("logo.jpg");
    	//Llogo = new JLabel(new ImageIcon(image));
    }
    

