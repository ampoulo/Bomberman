
package Terrain.vue;

import Terrain.Plateau;
import javax.swing.JFrame;

/**
 *
 * @author ibrahima
 */
public class Fenetre  extends JFrame {
    VuePrincipale vueBomberman;
    Plateau champdebataille;
    
    public Fenetre(VuePrincipale vueBomberman,Plateau champdebataille){ 
        
        
        this.setTitle("TERRAIN");
        
        this.champdebataille=champdebataille;
        
        this.vueBomberman=vueBomberman;
        
        this.setContentPane(this.vueBomberman);
        
        this.setSize(1100, 700);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        champdebataille.addObserver(vueBomberman);
        
        
        
        //vueBomberman.setFocusable(true);
        //vueBomberman.addKeyListener(ctr);
        
        this.setVisible(true);
    }

}

    

