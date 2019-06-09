
package entites;



/**
 *Cette classe permet de creer des instances de pastilles
 * @author cyrus
 */
public class Pastille implements Contenue{
    
    public static final int ENERGIE_MAX = 60;
    private Position position;
    /**
     * 
     * @param position Position de la pastille dans la grille 
     */
    public Pastille(Position position) {
        this.position = position;
    }
}
