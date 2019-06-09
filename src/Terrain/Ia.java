package Terrain;



import Terrain.joueurArme.Mine;
import Terrain.joueurArme.Explosable;
import entites.Mur;
import entites.Position;

/**
 *
 * @author cyrus
 */

public class Ia<T> {
    //actions possible
    

    int[][] zoneDeDanger;
    boolean pasDeMine=false;
    
    public Ia(ChampDeBataille champ){

       this.zoneDeDanger= new int[champ.getTaille()][champ.getTaille()];
           for(int i=0;i<champ.getTaille();i++){
            for(int j=0;j<champ.getTaille();j++){
                this.zoneDeDanger[i][j]=0;
            }
           }
        
    }

    
    public int[] decision(ChampDeBataille champ ,Combattant combattant){
          
        int[] tabDecision=new int[4];
        
        Position[] posTire= new Position[champ.getNbreCombattant()];
        
        for(int i=0;i<champ.getTaille();i++){
            for(int j=0;j<champ.getTaille();j++){
                if(champ.getGrille()[i][j].getContenue() instanceof Combattant ){
                    this.zoneDeDanger[i][j]=1;
                } else if(champ.getGrille()[i][j].getContenue() instanceof Explosable){
                    //case qui contient des Explosable
                    if(champ.getGrille()[i][j].getContenue() instanceof Mine){
                        pasDeMine=true;
                    }
                   // Explosable exp=(Explosable) champ.getGrille()[i][j].getContenue();
                   Position[] p =champ.caseVoisine(champ.getGrille()[i][j].getPosition());
                    for (Position p1 : p) {
                        this.zoneDeDanger[p1.getX()][p1.getY()] = 2;
                    }
                }
            }
        }
        
        char[] tab={'z','d','q','s'}; 
       
        if(!(combattant instanceof Rebelle)){
      
            for(char c :tab){ 
                    
                //Position[]  tabPosTire=combattant.tirer(c);
                Position[] tabPosTire = champ.caseSuccessive(combattant.getPosition(), c);
                for(int i=0;i<tabPosTire.length && tabPosTire[i]!=null;i++){
                
             
                    if(tabPosTire[i].getX()<=champ.getTaille()-1 && tabPosTire[i].getY()<=champ.getTaille()-1 &&
                     tabPosTire[i].getX()>=0 && tabPosTire[i].getY()>=0) {  
                 
             
                        if((champ.getGrille()[tabPosTire[i].getX()][tabPosTire[i].getY()].getContenue() instanceof Mur)){
                                break;}
                        else{
             
                            if(champ.getGrille()[tabPosTire[i].getX()][tabPosTire[i].getY()].getContenue() instanceof Combattant){
               
                 
                                tabDecision[3]++;
                 
             
                            }
            
                        }
             
                    }
       
                }
       
            }
       
        }
    
    
  
  
        Position[] p1= champ.caseVoisine(combattant.getPosition());
    
        for(Position p2:p1){
        
            if(this.zoneDeDanger[p2.getX()][p2.getY()]==0){
           
                tabDecision[0]++;
        
            }else if(this.zoneDeDanger[p2.getX()][p2.getY()]==1){
            
                tabDecision[1]++;
        
            }else if(this.zoneDeDanger[p2.getX()][p2.getY()]==2){
           
                tabDecision[2]++;
       
            }
        
        
    
        } 
    
    
        return tabDecision;

    }
     
      public String applicationDecisionSniper(ChampDeBataille champ ,Combattant combattant){
         
         int[] tabDecision=this.decision(champ, combattant);
         
         if(tabDecision[3]>=1 && tabDecision[2]==0){
          
             return "tire";
        
         }
        
         if(tabDecision[1]>=3 && tabDecision[2]==0){ 
      
             return "explosif";     
         
         }else if(tabDecision[2]>=1 && !pasDeMine){  
    
              //return "deplacer";
              return "bouclier";
               
         } 
         else{
             return "deplacer";
             //return "bouclier";
         }
     }
     public String applicationDecisionRebelle(ChampDeBataille champ ,Combattant combattant){
         
         int[] tabDecision=this.decision(champ, combattant);
         
        
        
         if(tabDecision[1]>=2 && tabDecision[2]==0){ 
      
             return "explosif";     
         
         }else if(tabDecision[1]>=5){  
    
              //return "deplacer";
              return "deplacer";
               
         } 
         else if(tabDecision[2]==2){
             return "bouclier";
         }
         else{
             return "deplacer";
             //return "bouclier";
         }
     }
     
     public String applicationDecisionSoldat(ChampDeBataille champ ,Combattant combattant){
         
         int[] tabDecision=this.decision(champ, combattant);
         
         if(tabDecision[3]>=1 && tabDecision[2]==0){
          
             return "tire";
        
         }
        
         if(tabDecision[1]>=3 && tabDecision[2]==0){ 
      
             return "explosif";     
         
         }else if(tabDecision[2]>=1){  
    
              //return "deplacer";
              return "bouclier";
               
         } 
         else{
             return "deplacer";
             //return "bouclier";
         }
     }
       


       
}
     

   



