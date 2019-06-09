package Main;


import Terrain.vue.Fenetre;
import Terrain.vue.VueChampDeBataille;
import Terrain.vue.FenetreDialogue;
import Terrain.vue.VuePrincipale;
import Terrain.ChampDeBataille;
import Terrain.ChampDeBatailleProxy;
import Terrain.Plateau;
import Terrain.initStrategie.Init1;
import java.util.Scanner;

/**
 *
 * @author cyrus
 */
public class Main{

    /**
     * @param args the command line arguments
     * @throws java.lang.InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        
        label : do{
            
            int nb=0;
            Scanner sc = new Scanner(System.in);

            Fenetre fen;
            
            

            while(nb<2 || nb>6){
                String nbcom=FenetreDialogue.nombreCombattant();
                nb=Integer.parseInt(nbcom);
            }
            
            Fenetre fenetresProxy[] = new Fenetre[nb];
            
            ChampDeBataille champ = new ChampDeBataille(new Init1(),13,nb); 
            champ.init();
            VueChampDeBataille p = new  VueChampDeBataille(champ);



            VuePrincipale vue = new VuePrincipale(p);


            Plateau[] proxy = new ChampDeBatailleProxy[champ.getNbreCombattant()];

            for(int i =0;i<champ.getNbreCombattant();i++){
                //proxy
                proxy[i] = new ChampDeBatailleProxy(champ,champ.getCombattant()[i]);
            }



            int option = FenetreDialogue.launch();
            String gagnant="";
            switch(option){
                case 1: 

                     champ.playGame();break;

                case 0: 

                    fen = new Fenetre(vue,champ);

                    for(int i =0;i<champ.getNbreCombattant();i++){
                        VuePrincipale vue1 =  new VuePrincipale(new VueChampDeBataille(proxy[i]));

                         fenetresProxy[i] = new Fenetre(vue1,proxy[i]);
                         fenetresProxy[i].setTitle("Combattant "+champ.getCombattant()[i].getPseudo());
                         proxy[i].addObserver(vue1);

                    }
                    
                    champ.playGame();
                    gagnant = champ.getCombattant()[0].getPseudo();
                    option = FenetreDialogue.Information(gagnant); 
                    switch(option){
                        case 0:
                            //on recommence l'execution;
                            ;break;
                        case 1:
                            fen.dispose();
                            for(Fenetre fenetre : fenetresProxy){
                                fenetre.dispose();
                            }
                            break label;
                    }
                    for(Fenetre fenetre : fenetresProxy){
                        fenetre.dispose();
                    }
                    fen.dispose();
                    break;
                default: break;
            }
       }while(true);
    }
}
