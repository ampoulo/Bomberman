
package Terrain.joueurFactory;

import Terrain.Combattant;
import Terrain.Soldat;
import Terrain.joueurStrategy.StrategieSoldat;
import entites.Position;





/**
 *Fabrique pour combattant de type Soldat
 * @author cyrus
 */
public class SoldatFactory extends CombattantFactory{

    @Override
    public Combattant creeCombattant(String pseudo, Position p) {
        return new Soldat(new StrategieSoldat(),pseudo,p);
    }
    
}
