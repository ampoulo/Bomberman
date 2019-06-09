
package Terrain.vue;

import entites.Position;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *Permet de représenter l'affichage d'un sniper dans l'interface graphique
 * @author ibrahima
 */
public class VueSniper extends JLabel {
    private Image sniper;
    private ImageIcon iconsniper;
    
    public VueSniper(Position p){
        super();
        iconsniper = new ImageIcon(getClass().getResource("../../image/sniper1.png"));
        this.sniper = this.iconsniper.getImage();
        this.setIcon(iconsniper);
    }
          public Image getSniper(){
            return this.sniper;
   }
        
    
        
    
    
}
