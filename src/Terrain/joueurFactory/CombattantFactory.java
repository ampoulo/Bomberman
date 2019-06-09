
package Terrain.joueurFactory;

import Terrain.Combattant;
import entites.Position;



/**
 *Interface utilisée pour la fabrique des des combattant
 * @author cyrus
 */
public abstract class CombattantFactory {
    /**
     * 
     * @param pseudo Pseudonyme du combattant
     * @param position Position initiale du combattant dans la grille
     * @return Le nouveau combattant crée avec le pseudo et la position passée en paramètre
     */
    public abstract Combattant creeCombattant(String pseudo, Position position);
    
    /**
     * 
     * @param pseudo Pseudonyme du combattant
     * @param position Position du combattant dans la grille
     * @return Le nouveau combattant crée avec le pseudo et la position passée en paramètre
     */
    protected Combattant nouveauCombattant(String pseudo , Position position){
        Combattant combattant = this.creeCombattant(pseudo, position);
        return combattant;
    }
    
}
