
package Terrain.joueurArme;


/**
 *Cette classe materialise une arme de type pistolet 
 * @author MADINA DIENG
 */
public class Pistolet extends Arme{
    
    public static final int PUISSANCE_MAX = 10;
    
    public static final int PORTEE_MAX = 5;
    
    
    public Pistolet(){
        
        type = "Pistolet";
        
        puissance = PUISSANCE_MAX;
        
        portee = PORTEE_MAX;
    }

}
