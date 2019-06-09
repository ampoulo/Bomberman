
package Terrain.vue;

import entites.Position;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *Permet de représenter l'affichage d'un bouclier au niveau de l'interface graphique
 * @author ibrahima
 */
public class VueBouclier extends JLabel{
    private Image bouclier;
    private ImageIcon iconbouclier;
    
     public VueBouclier(Position p){
           iconbouclier = new ImageIcon(getClass().getResource("../../image/bouclier1.png"));
           this.bouclier = this.iconbouclier.getImage();
           this.setIcon(iconbouclier);
        }

        public Image getBouclier(){
            return this.bouclier;
   }
    
}
