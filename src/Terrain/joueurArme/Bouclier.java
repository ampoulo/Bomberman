
package Terrain.joueurArme;

import entites.Contenue;

/**
 *Cette classe permet l'utilisation de bouclier par les différents combattants
 * @author cyrus
 */
public class Bouclier implements Contenue{

    private Boolean etat;
    private static final int DUREE_PROTECTION = 5;
    private int duree;
    
    public Bouclier() {
        this.etat = false;
        this.duree = DUREE_PROTECTION;
    }
    /**
     * Permet de connaitre l'etat du bouclier.Si le bouclier est actif cette variable vaudra True et False sinon 
     * @return L'etat du bouclier 
     */
    public Boolean getEtat() {
        return etat;
    }
    /**
     * Permet de modifier l'état du bouclier
     * @param etat Correspond à l'état du bouclier
     */
    public void setEtat(Boolean etat) {
        this.etat = etat;
    }
    /**
     * Permet de décrementer la durée de protection du bouclier lorsque celui-ci est activé
     */
    public void decrementeDuree(){
        this.duree -= 1;
    }
    /**
     * Permet de connaitre la durée de protection restante pour le bouclier
     * @return La durée de protection du bouclier 
     */
    public int getDuree(){
        return duree;
    }
}
