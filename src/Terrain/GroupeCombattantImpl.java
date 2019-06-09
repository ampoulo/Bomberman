
package Terrain;

import java.util.ArrayList;

/**
 *Permet de représenter un groupe de combattant
 * @author cyrus
 */
public class GroupeCombattantImpl implements GroupeCombattant{

    ArrayList<Combattant> listeCombattant;
    
    public GroupeCombattantImpl(){
        listeCombattant = new ArrayList<>();
    }
    @Override
    public int getSize() {
        return listeCombattant.size();
    }

    @Override
    public Combattant getCombattant(int i) {
        return listeCombattant.get(i);
    }
    
    @Override
    public void add(Combattant c){
        this.listeCombattant.add(c);
    }

    @Override
    public void remove(int indice) {
        this.listeCombattant.remove(indice);
    }
    
}
