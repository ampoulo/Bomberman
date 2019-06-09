
package Terrain.vue;

import entites.Position;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *Représente la vue pour une bombe dans l'interface graphique
 * @author ibrahima
 */
public class VueBombe extends JLabel{
   private Image bombe;
   private ImageIcon iconbombe;
   
   public VueBombe(Position p){
           super();
           iconbombe = new ImageIcon(getClass().getResource("../../image/bombe1.png"));
           this.bombe = this.iconbombe.getImage();
           this.setIcon(iconbombe);
        }

        public Image getBombe(){
            return this.bombe;
   }
    
}
