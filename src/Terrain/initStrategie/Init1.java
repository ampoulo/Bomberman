
package Terrain.initStrategie;


import Terrain.ChampDeBataille;
import entites.Mur;
import Terrain.Combattant;
import Terrain.joueurFactory.CombattantFactory;
import entites.Pastille;
import Terrain.joueurFactory.SniperFactory;
import Terrain.joueurFactory.SoldatFactory;
import Terrain.joueurFactory.RebelleFactory;
import entites.Position;

import java.util.Random;


/**
 *
 * @author cyrus
 */
public class Init1 implements InitChampDeBataille{
    
    @Override
    public void init(ChampDeBataille champ) {
             
        Random r = new Random();
        
        CombattantFactory cf;
        
        int i=0,j=0;
        
        int cote = 0;
        
        int player;
        
     
        //Cloture de murs
        
            for(int z=0;z<champ.getTaille();z++){
                champ.getGrille()[0][z].setContenue(new Mur(new Position(0,z)));
                 champ.getGrille()[champ.getTaille()-1][z].setContenue(new Mur(new Position(champ.getTaille()-1,z)));
            }
             for(int z=1;z<champ.getTaille()-1;z++){
                champ.getGrille()[z][0].setContenue(new Mur(new Position(z,0)));
                champ.getGrille()[z][champ.getTaille()-1].setContenue(new Mur(new Position(z,champ.getTaille()-1)));
            }
        
         //mise en place des murs
             
             for(int k=3;k<champ.getTaille()-3;k++){
                 if(k!=((champ.getTaille()-2)/2+1) &&  k!=((champ.getTaille()-2)/2) && k!=((champ.getTaille()-2)/2+2)){
                 champ.getGrille()[3][k].setContenue(new Mur(new Position(3,k)));
                 }
                 if(k!=((champ.getTaille()-2)/2+1 )&&  k!=((champ.getTaille()-2)/2) && k!=((champ.getTaille()-2)/2+2) ){
                 champ.getGrille()[k][3].setContenue(new Mur(new Position(k,3)));
                 }
                 
                 
                  if(k!=((champ.getTaille()-2)/2+1)&&  k!=((champ.getTaille()-2)/2) && k!=((champ.getTaille()-2)/2+2)){
                 champ.getGrille()[champ.getTaille()-4][k].setContenue(new Mur(new Position(champ.getTaille()-4,k)));
                 }
                 if(k!=((champ.getTaille()-2)/2+1)&&  k!=((champ.getTaille()-2)/2) && k!=((champ.getTaille()-2)/2+2)){
                 champ.getGrille()[k][champ.getTaille()-4].setContenue(new Mur(new Position(k,champ.getTaille()-4)));
                 }
                 
             }
              for(int k=5;k<champ.getTaille()-5;k++){
                
                 champ.getGrille()[k][5].setContenue(new Mur(new Position(k,5)));
                 
               champ.getGrille()[k][champ.getTaille()-6].setContenue(new Mur(new Position(k,champ.getTaille()-6)));
                 
                 
             }
        
        //ajouter aléatoirement les combattants dans le tableau
        //champ.affichageChoix();
        
        for(int k=0; k<champ.getNbreCombattant();k++){
            Position pos;
            i = r.nextInt(champ.getTaille());
            j = r.nextInt(champ.getTaille());
            while(champ.getGrille()[i][j].getContenue() instanceof Mur ){
                        i = r.nextInt(champ.getTaille());
                        j = r.nextInt(champ.getTaille());

            }
            pos = new Position(i,j);
         
            player = r.nextInt(3);
            
            switch(player){
                case 0:
                    cf = new SniperFactory();
                    //cf = new RebelleFactory();
                    champ.getCombattant()[k] = cf.creeCombattant("Sniper"+(k+1), pos);
                    champ.getGrille()[pos.getX()][pos.getY()].setContenue(champ.getCombattant()[k]);break;
                case 1:
                    cf = new SoldatFactory();
                    //cf = new RebelleFactory();
                    champ.getCombattant()[k] = cf.creeCombattant("Soldat"+(k+1), pos);
                    champ.getGrille()[pos.getX()][pos.getY()].setContenue(champ.getCombattant()[k]);break;
                case 2:
                    cf = new RebelleFactory();
                    champ.getCombattant()[k] = cf.creeCombattant("Rebelle"+(k+1), pos);
                    champ.getGrille()[pos.getX()][pos.getY()].setContenue(champ.getCombattant()[k]);break;
            }
           
          
            
        }
        //placer de facon aléatoire les pastilles
        int nombrePastille = champ.getNbreCombattant();
        for(int k=0; k<nombrePastille;k++){
           i = r.nextInt(champ.getTaille());
           j = r.nextInt(champ.getTaille());
            while((champ.getGrille()[i][j].getContenue() instanceof Mur ) || (champ.getGrille()[i][j].getContenue() instanceof Combattant ) ){
                i = r.nextInt(champ.getTaille());
                j = r.nextInt(champ.getTaille());                
            }
             champ.getGrille()[i][j].setContenue(new Pastille(new Position(i,j)));
               
               
            
        }
    }

}
