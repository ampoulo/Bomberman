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
public class VueTire extends JLabel{
     private Image tire;
      private ImageIcon icontire;
   
   public VueTire(Position p){
           super();
           icontire = new ImageIcon(getClass().getResource("../../image/tire.png"));
           this.tire = this.icontire.getImage();
           //this.setIcon(iconexplosion);
        }

  /*  public VueExplosion() {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
         super();
           iconexplosion = new ImageIcon(getClass().getResource("../../image/explosion.png"));
           this.explosion = this.iconexplosion.getImage();
          // this.setIcon(iconexplosion);
    }*/

        public Image getTire(){
            return this.tire;
   }
}
