
package entites;

/**
 *Représente une entité éléméntaire de la grille
 * @author cyrus
 */
public class CaseVide extends Case{
    
     
    public CaseVide(Position p){
        super(p);
        contenue = null;
    }
    

    @Override
    public Contenue getContenue() {
        return contenue;
    }

    @Override
    public void setContenue(Contenue contenue) {
        this.contenue = contenue;
    }
}
