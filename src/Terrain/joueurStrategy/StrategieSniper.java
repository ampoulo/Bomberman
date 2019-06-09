
package Terrain.joueurStrategy;

import Terrain.initStrategie.StrategieCombattant;
import entites.Mur;

import Terrain.joueurArme.Explosable;
import entites.Pastille;
import entites.CaseVide;
import Terrain.ChampDeBataille;
import Terrain.Combattant;
import Terrain.Ia;
import entites.Position;
import java.util.Random;

/**
 *
 * @author cyrus
 */
public class StrategieSniper implements StrategieCombattant{
   
    String decision;

    public String getDecision() {
        return decision;
    }
    @Override
    public void action(ChampDeBataille champ, Combattant c) {
        Ia ia = new Ia(champ);
           decision = ia.applicationDecisionSniper(champ, c);
           System.out.println("Decisionsniper "+decision);
                
        switch(decision){
        
            case "deplacer":
                this.deplacement(champ, c);
                break;
            case "explosif":
                this.placerMine(champ, c);
                break;
            case "tire":
                this.combat(champ,c);
                break;
            case "bouclier":
                champ.bouclier();
                c.activeBouclier();
            default:break;
        }
    
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
                if( champ.getGrille()[pos[i].getX()][pos[i].getY()] instanceof CaseVide){
                
                   
                 int k=  r.nextInt(4);
               
                  while  ( !(champ.getGrille()[pos[k].getX()][pos[k].getY()] instanceof CaseVide)){
                     k=  r.nextInt(4);  
                  };
                  p = pos[k];
                       
                }
            }
        }
        champ.deplacerVers(p);
       
    }
    
    public void combat(ChampDeBataille champ,Combattant combattant){
        
        //tirer
        if(combattant.getMinution()[2]>0){
            
               char[] tab={'z','d','q','s'};
             for(char c :tab){ 
          
          
                //Position[]  tabPosTire=combattant.tirer(c);
                Position[] tabPosTire = champ.caseSuccessive(combattant.getPosition(), c);
                for(int i=0;i<tabPosTire.length && tabPosTire[i]!=null;i++){
                
             
                    if(tabPosTire[i].getX()<=champ.getTaille()-1 && tabPosTire[i].getY()<=champ.getTaille()-1 &&
                     tabPosTire[i].getX()>=0 && tabPosTire[i].getY()>=0) {  
                 
             
                        if(!(champ.getGrille()[tabPosTire[i].getX()][tabPosTire[i].getY()].getContenue() instanceof Mur)){
        
             
                            if(champ.getGrille()[tabPosTire[i].getX()][tabPosTire[i].getY()].getContenue() instanceof Combattant){
                                    combattant.tirer();
                                    champ.tir(c);
                                    break;
             
                            }else {
                                continue;
                            }
            
                        }
             
                    }
       
                }
       
            }
        
        }else{
            Random r=new Random();
            int k=r.nextInt(2);
            if(k==0){
            this.deplacement(champ, combattant);
            } else{
                this.placerMine(champ, combattant);
            }
        }
    }
    
    public void placerMine(ChampDeBataille champ,Combattant c){
        
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
            //deposer mine si reste minution
            if(c.getMinution()[1]>0){
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
            //tirer sinon
            else if(c.getMinution()[2]>0){
                combat(champ,c);
            }else{
                deplacement(champ,c);
            } 
        }
    }
}
