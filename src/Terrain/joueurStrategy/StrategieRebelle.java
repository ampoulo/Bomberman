
package Terrain.joueurStrategy;

import Terrain.initStrategie.StrategieCombattant;
import Terrain.joueurArme.Explosable;
import entites.Pastille;
import Terrain.ChampDeBataille;
import Terrain.Combattant;
import Terrain.Ia;
import entites.Position;
import java.util.Random;

/**
 *
 * @author cyrus
 */
public class StrategieRebelle implements StrategieCombattant{
   

    String decision;

    public String getDecision() {
        return decision;
    }
    
    public void deplacement(ChampDeBataille champ, Combattant c) {

        Position p = new Position(0,0);
        
        Random r=new Random();
        
        Position[] pos = new Position[4];
        pos[0] = new Position(c.getPosition().getX()+1,c.getPosition().getY());
        pos[1] = new Position(c.getPosition().getX()-1,c.getPosition().getY());
        pos[2] = new Position(c.getPosition().getX(),c.getPosition().getY()+1);
        pos[3] = new Position(c.getPosition().getX(),c.getPosition().getY()-1);
   
        for( int i = 0;i<pos.length; i++){
            if(champ.getGrille()[pos[i].getX()][pos[i].getY()].getContenue() instanceof Explosable){
                switch(i){
                    case 0: p = new Position(pos[i].getX()-1,pos[i].getY());break;
                    case 1: p = new Position(pos[i].getX()+1,pos[i].getY());break;
                    case 2: p = new Position(pos[i].getX(),pos[i].getY()-1);break;
                    case 3: p = new Position(pos[i].getX(),pos[i].getY()+1);break;
                }
               
                break;
            }if(champ.getGrille()[pos[i].getX()][pos[i].getY()].getContenue() instanceof Pastille){
                
                p = pos[i];
                break;
            }
            else{
                if( champ.getGrille()[pos[i].getX()][pos[i].getY()].getContenue()==null){
                
                   
                 int k=  r.nextInt(4);
               
                  while  ( !(champ.getGrille()[pos[k].getX()][pos[k].getY()].getContenue()==null)){
                     k=  r.nextInt(4);  
                  };
                  p = pos[k];
                   
                     
                }
            }
        }
        champ.deplacerVers(p);
       
    }
    
    public void combat(ChampDeBataille champ, Combattant c){
       
        Position[] positionAutour = new Position[9];
        positionAutour = champ.caseVoisine(c.getPosition());
       
        Position[] pos = new Position[9];
        Position bonnePosition;
        
        int nbCombattant = 0;
        
        for(Position p : positionAutour){
            if(champ.getGrille()[p.getX()][p.getY()].getContenue() instanceof Combattant){
                pos[nbCombattant] = p;
                nbCombattant++;
            }
        }
        Position p;
        if(nbCombattant>=1){
            //deposer bombe si reste minution
            if(c.getMinution()[0]>0){
                //on cherche la bonne position
                for(int i=0;i<pos.length;i++){
                    if(champ.getGrille()[pos[i].getX()+1][pos[i].getY()].getContenue()==null){
                        champ.placerExplosif(false, new Position(pos[i].getX()+1,pos[i].getY()), "bombe");
                        break;
                    }else if(champ.getGrille()[pos[i].getX()-1][pos[i].getY()].getContenue()==null){
                        champ.placerExplosif(false, new Position(pos[i].getX()-1,pos[i].getY()), "bombe");
                        break;
                    }else if(champ.getGrille()[pos[i].getX()][pos[i].getY()+1].getContenue()==null){
                        champ.placerExplosif(false, new Position(pos[i].getX(),pos[i].getY()+1), "bombe");
                        break;
                    }else{
                        champ.placerExplosif(false, new Position(pos[i].getX(),pos[i].getY()-1), "bombe");
                        break; 
                    }
                }
                
            }
            //sinon deposer mine si reste minution
            else if(c.getMinution()[1]>0){
               //on cherche la bonne position
                for(int i=0;i<pos.length;i++){
                    if(champ.getGrille()[pos[i].getX()+1][pos[i].getY()].getContenue()==null){
                        champ.placerExplosif(false, new Position(pos[i].getX()+1,pos[i].getY()), "mine");
                        break;
                    }else if(champ.getGrille()[pos[i].getX()-1][pos[i].getY()].getContenue()==null){
                        champ.placerExplosif(false, new Position(pos[i].getX()-1,pos[i].getY()), "mine");
                        break;
                    }else if(champ.getGrille()[pos[i].getX()][pos[i].getY()+1].getContenue()==null){
                        champ.placerExplosif(false, new Position(pos[i].getX(),pos[i].getY()+1), "mine");
                        break;
                    }else{
                        champ.placerExplosif(false, new Position(pos[i].getX(),pos[i].getY()-1), "mine");
                        break; 
                    }
                }
            }
        }else{
            //sinon deplacer
            this.deplacement(champ,c);
        } 
    }

    @Override
    public void action(ChampDeBataille champ, Combattant c) {
        Ia ia = new Ia(champ);
          decision = ia.applicationDecisionRebelle(champ, c);
           System.out.println("Decision Rebelle "+decision);
        switch(decision){
            case "deplacer":
                this.deplacement(champ, c);
                break;
            case "explosif":
                this.combat(champ, c);
                break;
            case "bouclier":
                champ.bouclier();
                c.activeBouclier();
                break;
        }
    }
}
