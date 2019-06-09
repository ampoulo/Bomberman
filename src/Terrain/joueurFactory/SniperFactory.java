
package Terrain.joueurFactory;

import Terrain.Combattant;
import Terrain.Sniper;
import Terrain.joueurStrategy.StrategieSniper;
import entites.Position;




/**
 *Fabrique pour un combattant de type Sniper
 * @author cyrus
 */
public class SniperFactory extends CombattantFactory{

    @Override
    public Combattant creeCombattant(String pseudo, Position p) {
        return new Sniper(new StrategieSniper(),pseudo,p);
    }
    
}
