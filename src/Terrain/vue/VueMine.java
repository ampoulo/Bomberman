
package Terrain.vue;


import entites.Position;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *Permet de représenter l'affichage d'une mine au niveau de l'interface graphique
 * @author ibrahima
 */
public class VueMine extends JLabel{
    private Image mine;
    private ImageIcon iconmine;
    
     public VueMine(Position p){
           iconmine = new ImageIcon(getClass().getResource("../../image/mine1.png"));
           this.mine = this.iconmine.getImage();
           this.setIcon(iconmine);
        }

        public Image getMine(){
            return this.mine;
   }
}
