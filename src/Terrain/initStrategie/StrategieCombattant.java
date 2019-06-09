package Terrain.initStrategie;


import Terrain.ChampDeBataille;
import Terrain.Combattant;


/**
 *Strategie desn actions du combattant
 * @author ibrahima
 */
public interface StrategieCombattant {
    void action(ChampDeBataille champ,Combattant c);
    String getDecision();
    
}
