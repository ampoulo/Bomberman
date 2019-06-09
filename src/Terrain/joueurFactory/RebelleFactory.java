
package Terrain.joueurFactory;

import Terrain.Combattant;
import Terrain.Rebelle;
import Terrain.joueurStrategy.StrategieRebelle;
import entites.Position;



/**
 * Fabrique pour un combattant rebelle
 * @author cyrus
 */
public class RebelleFactory extends CombattantFactory{

    @Override
    public Combattant creeCombattant(String pseudo, Position p) {
        return new Rebelle(new StrategieRebelle(),pseudo,p);
    }
    
}
