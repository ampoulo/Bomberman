
package entites;

/**
 *Représente une entité éléméntaire de la grille
 * @author cyrus
 */
public abstract class Case implements Conteneur{
    /**
     * Représente la position de la case dans la grille
     */
    protected Position position;
    /**
     * Représente le contenu de la case
     */
    protected Contenue contenue;
    
    /**
     * Crée une case avec la position p et un contenu égal à null
     * @param p position de la case dans la grille 
     */
    public Case(Position p){
        this.position = p;
        this.contenue = null;
    }
    
    /**
     * Retourne la position cette case
     * @return la position de la case 
     */
    public Position getPosition(){
        return this.position;
    }
    
    /**
     * Retourne le contenu de cette case
     * @return le contenu de la case 
     */
    public abstract Contenue getContenue();
    /**
     * Change le contenu cette case avec le contenu specifié en paramètre
     * @param contenue Nouveau contenu de la case
     */
    public abstract void setContenue(Contenue contenue);
}
