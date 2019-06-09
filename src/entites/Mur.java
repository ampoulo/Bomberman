
package entites;
/**
 * Permet de materialiser l'affichage des murs dans la grille
 * @author cyrus
 */
public class Mur implements Contenue{

    Position position;
    /**
     * 
     * @param position Position du mur dans la grille 
     */
    public Mur(Position position) {
      this.position = position;
    }

    
}
