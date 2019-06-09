
package Terrain.vue;


import entites.Position;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *Permet de représenter l'affichage d'un combattant rebelle dans l'interface graphique
 * @author ibrahima
 */
public class VueRebelle extends JLabel{
    private Image rebelle;
    private ImageIcon iconrebelle;
    
    public VueRebelle(Position p){
        iconrebelle = new ImageIcon(getClass().getResource("../../image/rebelle2.png"));
        this.rebelle = this.iconrebelle.getImage();
        this.setIcon(iconrebelle);
    }
          public Image getRebelle(){
            return this.rebelle;
   }
        
    
}
