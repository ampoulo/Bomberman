
package Terrain.vue;

import entites.Position;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *Permet de représenter l'affichage de la pastille dans l'interface graphique
 * @author ibrahima
 */
public class VuePastille extends JLabel {
    private Image pastille;
    private ImageIcon iconpastille;
    
    public VuePastille(Position p){
        iconpastille = new ImageIcon(getClass().getResource("../../image/pastille1.png"));
        this.pastille = this.iconpastille.getImage();
        this.setIcon(iconpastille);
    }
          public Image getPastille(){
            return this.pastille;
   }
    
}
