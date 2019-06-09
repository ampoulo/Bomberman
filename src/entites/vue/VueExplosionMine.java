/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites.vue;


import entites.Position;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author ibou1
 */
public class VueExplosionMine extends JLabel{
       private Image explosionmine;
      private ImageIcon iconexplosionmine;
   
   public VueExplosionMine(Position p){
           super();
           iconexplosionmine = new ImageIcon(getClass().getResource("../../image/explosionmine.png"));
           this.explosionmine = this.iconexplosionmine.getImage();
           //this.setIcon(iconexplosionmine);
        }

 

        public Image getExplosionMine(){
            return this.explosionmine;
   }
}
