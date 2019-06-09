
package Terrain.vue;

import entites.Position;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *Permet de représenter l'affichage d'un soldat dans l'interface graphique
 * @author ibrahima
 */
public class VueSoldat extends JLabel{
    private Image soldat;
    private ImageIcon iconsoldat;
    
    public VueSoldat(Position p){
       iconsoldat = new ImageIcon(getClass().getResource("../../image/soldat2.png"));
        this.soldat = this.iconsoldat.getImage();
        this.setIcon(iconsoldat);
    }
          public Image getSoldat(){
            return this.soldat;
   }
    
    
    
}
