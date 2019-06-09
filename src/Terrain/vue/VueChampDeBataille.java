
package Terrain.vue;



import Terrain.joueurArme.MineVisible;
import Terrain.joueurArme.MineInvisible;
import Terrain.joueurArme.BombeVisible;
import Terrain.joueurArme.Bouclier;
import Terrain.joueurArme.BombeInvisible;
import Terrain.Plateau;
import Terrain.Sniper;
import Terrain.Rebelle;
import Terrain.Soldat;
import entites.vue.VueExplosionMine;
import entites.vue.VueTire;
import entites.vue.VueMur;
import entites.vue.VueExplosion;
import entites.vue.VueCaseVide;
import entites.ExplosionMine;
import entites.Explosion;
import entites.Tire;
import entites.Pastille;
import entites.Mur;
import entites.Position;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author cyrus
 */
public class VueChampDeBataille extends JPanel {
    
    private Plateau champdebataille;

 
    public VueChampDeBataille(Plateau champdebataille){
        this.champdebataille = champdebataille;
    }
 
    
    
    public Plateau getChampdebataille() {
        return champdebataille;
    }
    
     @Override
    public void paint(Graphics g){      
        int x=0,y=0;        
        for(int i=0;i<champdebataille.getTaille();i++){
           
            for(int j=0;j<champdebataille.getTaille();j++){                
             
                if(champdebataille.getContenue(new Position(i,j))==null){
                  
                   g.drawImage(new VueCaseVide(new Position(i,j)).getCaseVide(), x, y, this);
                   x+=48;
                }
                else if(champdebataille.getContenue(new Position(i,j)) instanceof Mur){
                    
                    g.drawImage(new VueMur(new Position(i,j)).getMur(),x,y,this);
                    x+=48;
                }
                else if(champdebataille.getContenue(new Position(i,j)) instanceof Pastille){
                    g.drawImage(new VuePastille(new Position(i,j)).getPastille(),x,y,this);
                    x+=48;
                }
                else if(champdebataille.getContenue(new Position(i,j)) instanceof Soldat){
                    g.drawImage(new VueSoldat(new Position(i,j)).getSoldat(),x,y,this);
                    x+=48;
                }
                else if(champdebataille.getContenue(new Position(i,j)) instanceof Rebelle){
                    g.drawImage(new VueRebelle(new Position(i,j)).getRebelle(),x,y,this);
                    x+=48;
                }
                else if(champdebataille.getContenue(new Position(i,j)) instanceof Sniper){
                    g.drawImage(new VueSniper(new Position(i,j)).getSniper(),x,y,this);
                    x+=48;
                }
                else if(champdebataille.getContenue(new Position(i,j)) instanceof BombeVisible){
                    g.drawImage(new VueBombe(new Position(i,j)).getBombe(),x,y,this);
                    x+=48;
                }
                else if(champdebataille.getContenue(new Position(i,j)) instanceof MineVisible){
                    g.drawImage(new VueMine(new Position(i,j)).getMine(),x,y,this);
                    x+=48;
                }
                else if(champdebataille.getContenue(new Position(i,j)) instanceof BombeInvisible){
                    g.drawImage(new VueBombe(new Position(i,j)).getBombe(),x,y,this);
                    x+=48;
                }
                else if(champdebataille.getContenue(new Position(i,j)) instanceof MineInvisible){
                    g.drawImage(new VueMine(new Position(i,j)).getMine(),x,y,this);
                    x+=48;
                }
                else if (champdebataille.getContenue(new Position(i,j)) instanceof Explosion){
                    g.drawImage(new VueExplosion(new Position(i,j)).getExplosion(), x, y, this);
                    
                    x+=48;
                }
                else if (champdebataille.getContenue(new Position(i,j)) instanceof Tire){
                    g.drawImage(new VueTire(new Position(i,j)).getTire(), x, y, this);
                    
                    x+=48;
                }
                 else if (champdebataille.getContenue(new Position(i,j)) instanceof ExplosionMine){
                    g.drawImage(new VueExplosionMine(new Position(i,j)).getExplosionMine(), x, y, this);
                    
                    x+=48;
                }
                 else if(champdebataille.getContenue(new Position(i,j)) instanceof Bouclier){
                    g.drawImage(new VueBouclier(new Position(i,j)).getBouclier(), x, y, this);
                    
                    x+=48;
                 }
            }
            y+=48;x=0;
        }   
    }
}
