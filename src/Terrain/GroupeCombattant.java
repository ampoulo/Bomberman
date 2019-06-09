
package Terrain;

/**
 *
 * @author cyrus
 */
public interface GroupeCombattant {
    int getSize();
    Combattant getCombattant(int i);
    public void add(Combattant c);
    public void remove(int i);
}
