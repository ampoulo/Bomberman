
package Terrain.joueurArme;


/**
 *Cette classe permet de représenter les differents types d'armes que possèdent un combattant
 * @author MADINA DIENG
 */
public abstract class Arme {

    protected String type;
    protected int puissance;
    protected int portee;


    /**
     * retourne le type de l'arme
     * @return Le type de l'arme  
     */
    public String getType() {
        return type;
    }

    /**
     * Retourne la puissance de l'arme
     * @return La puissance de l'arme 
     */
    public int getPuissance() {
        return puissance;
    }

    /**
     * Retourne la portée de l'arme
     * @return la portée de l'arme
     */
    public int getPortee() {
        return portee;
    }
    /**
     * Modifie la puissance de l'arme par la puissance passée en paramètre 
     * @param puissance 
     */
    public void setPuissance(int puissance) {
        this.puissance = puissance;
    }

    /**
     * Modifie la portée de l'arme par la portée passée en paramètre
     * @param portee 
     */
    public void setPortee(int portee) {
        this.portee = portee;
    }
}
