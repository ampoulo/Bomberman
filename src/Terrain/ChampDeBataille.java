
package Terrain;

import Terrain.joueurArme.*;
import entites.Case;
import entites.CaseVide;
import entites.Contenue;
import entites.ExplosionMine;
import entites.Explosion;
import entites.Tire;
import entites.Pastille;
import entites.Mur;
import Terrain.initStrategie.InitChampDeBataille;
import entites.Position;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;


/**
 *Classe centrale représentant la grille principale du jeu
 * @author Mouhamadou Ousmane
 */
public class ChampDeBataille extends Observable implements Plateau{
    
    private final int taille;

    private Combattant[] combattants;
    
    private int nbreCombattant;

    private Case[][] grille ;

    private int idxJoueurCourant;  

    private int nbreTourDeJeu;

    private ArrayList<Observer> listeObserver = new ArrayList<>();

    protected InitChampDeBataille champs;
    
    /**
     * 
     * @param champs 
     * @param taille Représente le nombre de case en ligne et colonne de la grille
     * @param nbreCombattant Représente le nombre de combattant en cours  dans le jeu
     */
    public ChampDeBataille(InitChampDeBataille champs,int taille,int nbreCombattant){
       this.champs = champs;
       this.taille = taille ;
       this.nbreCombattant = nbreCombattant;
       combattants = new Combattant[nbreCombattant];
       this.idxJoueurCourant = 0;
       this.nbreTourDeJeu = 0 ;
       grille = new CaseVide[taille][taille];
       for(int x=0; x<taille ;x++){
            for(int y=0;y<taille;y++){
                grille[x][y] = new CaseVide(new Position(x,y));
            }
        }
       
    }
        
    /**
     * Permet d'initialiser le jeu selon une strategie bien définie
     */
    public void init(){
        champs.init(this);
    }
    @Override
    public Case[][] getGrille(){
        return this.grille;
    }
 
    @Override
    public int getTaille(){
        return this.taille;
    }
 
    @Override
    public Combattant[] getCombattant(){
        return combattants;
    }
    @Override
    public int getNbreCombattant(){
        return nbreCombattant;
    }
    
    @Override
    public int getIdxJoueurCourant(){
        return idxJoueurCourant;
    }
    @Override
    public ArrayList<Observer> getListObserver(){
        return this.listeObserver;
    }

    private void afficheChampDeBataille(){
        for(int i=0; i<taille;i++){
            for(int j=0; j<taille; j++){
                if(this.grille[i][j].getContenue() instanceof Mur)
                    System.out.print("#");
                if(this.grille[i][j].getContenue() instanceof Soldat)
                    System.out.print("m");
                if(this.grille[i][j].getContenue() instanceof Rebelle)
                    System.out.print("r");
                if(this.grille[i][j].getContenue() instanceof Sniper)
                    System.out.print("s");
                if(this.grille[i][j].getContenue() instanceof Pastille)
                    System.out.print("+");
                if(this.grille[i][j].getContenue() instanceof MineInvisible)
                    System.out.print(".");
                 if(this.grille[i][j].getContenue() instanceof MineVisible)
                    System.out.print("M");
                if(this.grille[i][j].getContenue() instanceof BombeVisible)
                    System.out.print("B");
                if(this.grille[i][j].getContenue() instanceof BombeInvisible)
                    System.out.print(".");
                if(this.grille[i][j].getContenue() instanceof Explosion)
                    System.out.print("e");
                if(this.grille[i][j].getContenue() instanceof ExplosionMine)
                    System.out.print("k");
                if(this.grille[i][j].getContenue() instanceof Tire)
                    System.out.print("t");
                if(this.grille[i][j].getContenue() instanceof Bouclier)
                    System.out.print("*");
               if(this.grille[i][j].getContenue()==null)
                    System.out.print(".");
                
            }
            System.out.println();
        }
    }
    @Override
    public String toString(){
        this.afficheChampDeBataille();
        return "";
    }
    /**
     * Retourne la position du combattant ayant le tour
     * @return la position du combattant ayant le tour
     */
    public Position posCombattant(){        
        return this.combattants[idxJoueurCourant].getPosition();
    }    


    /**
     * Permet de faire une correspondance entre le caractère passé en paramètre et la position finale en fonction de la position courante
     * @param c Caractère correspondant à la position de destination par rapport à la position courante
     * @return La position de destination par raport à celle du départ
     */
    public Position posDestination(char c){
        Position p = posCombattant();
        switch(c){
            case 'z': return new Position(p.getX()-1,p.getY());  
            case 's': return new Position(p.getX()+1,p.getY());
            case 'd': return new Position(p.getX(),p.getY()+1);
            case 'q': return new Position(p.getX(),p.getY()-1);
        }
        return null;
    }   

     
    /**
     *
     * Teste si un déplacement du joueur courant est possible vers une destination de position posDest
     * @param posDest Position finale vers laquelle le joueur veut se déplacer
     * @return True si le deplacement vers la position postDest est possible et false sinon
     */
    public boolean estDeplacable(Position posDest){        
        return !((this.grille[posDest.getX()][posDest.getY()].getContenue() instanceof Mur) 
                ||
                (this.grille[posDest.getX()][posDest.getY()].getContenue() instanceof Combattant));    
    }
    
    /**
     * 
     * Permet de deplacer un joueur vers la position passée en paramètre
     * @param posDest Position finale vers laquelle le joueur veut se déplacer
     */
    public void deplacerVers(Position posDest){        
        
        int i = idxJoueurCourant;
        
        if(estDeplacable(posDest)){
            
            //teste si dans la case destination il y'a une pastille
            if(this.grille[posDest.getX()][posDest.getY()].getContenue() instanceof Pastille){                
                //le combattant recupere l'energie de la pastille
                this.combattants[i].gainEnergie(Pastille.ENERGIE_MAX);
                //le nouveau contenu de la case d'arrivée change 
                this.grille[posDest.getX()][posDest.getY()].setContenue(this.combattants[i]);
                //le contenu de la case de depart change (à null ne contient plus rien)
                this.grille[this.combattants[i].getPosition().getX()][this.combattants[i].getPosition().getY()].setContenue(null);
                //Appel de la methode deplacer du joueur
                combattants[i].seDeplace(posDest);
            }
            //teste si la case d'arrivée contient une mine
            else if(this.grille[posDest.getX()][posDest.getY()].getContenue() instanceof Mine){
                    //Si oui le combattant perd une quantite d'energie egale au puissance de la mine
                    this.combattants[i].perteEnergie(Mine.PUISSANCE_MAX);                                        
                    //le nouveau contenu de la case d'arrivée change
                    this.grille[posDest.getX()][posDest.getY()].setContenue(new ExplosionMine());
                    //le contenue de la case de depart change à null
                    this.grille[this.combattants[i].getPosition().getX()][this.combattants[i].getPosition().getY()].setContenue(null);
                    //Appel de la methode deplacer du joueur
                    combattants[i].seDeplace(posDest);
            }
            //teste si dans la case de destination il y'a une bombe
            else if(this.grille[posDest.getX()][posDest.getY()].getContenue() instanceof Bombe){
                    //Si oui le combattant perd une quantite d'energie egale au puissance de la bombe
                    //this.combattants[i].perteEnergie(Bombe.PUISSANCE_MAX);                        
                    //change le contenu de la case d'arrivée 
                    this.grille[posDest.getX()][posDest.getY()].setContenue(new Explosion());
                    //gére l'affichage de l'explosion 
                    this.afficheExplosion(posDest);
                    //Appel de la methode deplacer du joueur
                    combattants[i].seDeplace(posDest);
            }
            //teste si la case de destination est une case vide
            else if(this.grille[posDest.getX()][posDest.getY()].getContenue()==null){
                    //si Oui le contenu de cette case change     
                    this.grille[posDest.getX()][posDest.getY()].setContenue(this.combattants[i]);
                    //le contenue de la case de depart change à null     
                    this.grille[this.combattants[i].getPosition().getX()][this.combattants[i].getPosition().getY()].setContenue(null);
                    //Appel de la methode du joueur
                    combattants[i].seDeplace(posDest);
            }     
        }
        
       
    }
    
    private void afficheExplosion(Position posDest){
        if(!(this.grille[posDest.getX()+1][posDest.getY()].getContenue() instanceof Mur))
            this.grille[posDest.getX()+1][posDest.getY()].setContenue(new Explosion());
                       
        if(!(this.grille[posDest.getX()-1][posDest.getY()].getContenue() instanceof Mur))               
            this.grille[posDest.getX()-1][posDest.getY()].setContenue(new Explosion());
                      
        if(!(this.grille[posDest.getX()][posDest.getY()+1].getContenue() instanceof Mur))
            this.grille[posDest.getX()][posDest.getY()+1].setContenue(new Explosion());
                       
        if(!(this.grille[posDest.getX()][posDest.getY()-1].getContenue() instanceof Mur))
            this.grille[posDest.getX()][posDest.getY()-1].setContenue(new Explosion());
                       
        if(!(this.grille[posDest.getX()+1][posDest.getY()+1].getContenue() instanceof Mur))
            this.grille[posDest.getX()+1][posDest.getY()+1].setContenue(new Explosion());
                       
        if(!(this.grille[posDest.getX()+1][posDest.getY()-1].getContenue() instanceof Mur))
            this.grille[posDest.getX()+1][posDest.getY()-1].setContenue(new Explosion());
                       
        if(!(this.grille[posDest.getX()-1][posDest.getY()-1].getContenue() instanceof Mur))
            this.grille[posDest.getX()-1][posDest.getY()-1].setContenue(new Explosion());
                      
        if(!(this.grille[posDest.getX()-1][posDest.getY()+1].getContenue() instanceof Mur))
            this.grille[posDest.getX()-1][posDest.getY()+1].setContenue(new Explosion());
                                             
    }
    @Override
    public void nextPlayer(){   
        
        if(this.idxJoueurCourant<(this.nbreCombattant-1)){
            this.idxJoueurCourant = this.idxJoueurCourant+1;
        }
        else
            this.idxJoueurCourant=0;
    }
    
    @Override
    public void playGame(){    
            
        while(!fin()){                
            
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
               
            }
            
            afficheChampDeBataille();

            System.out.println(this.combattants[idxJoueurCourant].caracteristiqueCourant());

            teste();
            combattants[idxJoueurCourant].action(this);
    
            testeMort();
            
            this.notifyObservers();
            
            nextPlayer();
            
            
        }  
        
    }
    /**
     * Teste si le jeu est terminé ou pas
     * @return True si leu est fini et false sinon
     */
     public boolean fin(){
        int k=0;
        for(int i=0;i<nbreCombattant; i++){
            if(this.combattants[i].getEnergie()<=0)
                k++;
        }
        return ((nbreCombattant-k)==1);
    }
    
     /**
      * Gére l'impact du tir dans la grille
      * @param listPositionTirees Tableau contenant l'ensemble des positions impactées par le tir
      */
    public void tireImpact(Position[] listPositionTirees){
        
        for (Position position : listPositionTirees) {
            //if(position.getX()<this.taille && position.getY()<this.taille){
                //teste si la position courante correspond a un mur
                if(this.grille[position.getX()][position.getY()].getContenue() instanceof Mur){
                    break;
                }     
                //teste si cette position position correspond a un combattant dans la grille
                if(this.grille[position.getX()][position.getY()].getContenue() instanceof Combattant){
                    //si oui on recupere le combattant correspondant
                    for(int k=0; k<nbreCombattant;k++){
                        if(this.grille[position.getX()][position.getY()].getContenue().equals(combattants[k])){
                            //le combattant perd une quantite d'energie egale au puissance du pistolet
                            combattants[k].perteEnergie(Pistolet.PUISSANCE_MAX);
                        }
                    }
                }
               else {
                    //on represente l'affichage du tir
                    this.grille[position.getX()][position.getY()].setContenue(new Tire());

                }
            }
      
    }
    /**
     * Gére l'impact d'une explosion dans la grille
     * @param exp Correspond à l'explosif qui a explosé
     * @param listCaseBombarde correspond à l'ensemble des cases impactées
     */
    public void explosionImpact(Explosable exp,Position[] listCaseBombarde){
        
        Position p;

        //On parcours la liste des cases concernées
        for (Position position : listCaseBombarde) {
            //recupere la position de la case courante dans p
            p = position;
            //teste si la case courante contient un mur
            if(this.grille[p.getX()][p.getY()].getContenue() instanceof Mur){
                //explosion touche pas mur
            }
            //teste si la case courante contient un combattant
            else if(this.grille[p.getX()][p.getY()].getContenue() instanceof Combattant){
                //on parcours le tableau contenant tous les combattants
                for(Combattant c : this.combattants){
                    if(c.equals(this.grille[p.getX()][p.getY()].getContenue())){
                        //le combattant perd une quantite d'energie egale au puissance de la bombe
                        c.perteEnergie(Bombe.PUISSANCE_MAX);
                    }
                }
                this.grille[p.getX()][p.getY()].setContenue(new ExplosionMine());
            }
            else{
                this.grille[p.getX()][p.getY()].setContenue(new Explosion());
            }
        }
    }
    
    
    @Override
    public void teste(){

        Bombe b;
        
        //teste si pour le bouclier du combattant qui doit jouer vaut true
        if(combattants[idxJoueurCourant].getBouclier().getEtat()==true){
            //si le bouclier est actif on decremente la duree de protection du bouclier
            combattants[idxJoueurCourant].getBouclier().decrementeDuree();
            //teste si la duree de proection du bouclier est termine
            if(combattants[idxJoueurCourant].getBouclier().getDuree()==0){
                //si oui on desactive le bouclier
                combattants[idxJoueurCourant].getBouclier().setEtat(false);
            }
            //sinon on met à jour le bouclier avec la nouvelle position du combattant
            else if(combattants[idxJoueurCourant].getBouclier().getDuree()>0){
                bouclier();
            }
        }
        //nettoie le champ apres explosion ou tir
        for(int i=0;i<this.taille;i++){
            for(int j=0;j<this.taille;j++){
                if(this.grille[i][j].getContenue() instanceof Explosion){
                    this.grille[i][j].setContenue(null);
                }
                else if(this.grille[i][j].getContenue() instanceof Tire){
                    this.grille[i][j].setContenue(null);
                }
            }
        }
        //affiche l'explosion dans le champ
        for(int i =0;i<this.taille;i++){
            for(int j=0;j<this.taille;j++){
                //teste si  la case courante contient une bombe
                if(this.grille[i][j].getContenue() instanceof Bombe){    
                    b = (Bombe) this.grille[i][j].getContenue();
                    //teste son compte a rebours
                    if(b.getCompteARebours()==0){
                        explosionImpact(b,this.caseVoisine(b.getPosition()));
                    }
                    //sinon on decremente son compte a rebours
                    else{
                        b.decremente();
                    }
                }
            }
        }

        notifyObservers();
    }
    @Override
    public void placerExplosif(boolean visibilite,Position p,String typeExplosif){     
        
         Explosable expl=null;
        
         //teste si la position correspondant à b est autre qu'un mur ou combattant
         if(this.estDeplacable(p)){
   
            //si l'explosif est marquée visible
            if(visibilite==true){     
               if(typeExplosif.equalsIgnoreCase("bombe")){
                   //expl = new BombeVisible();
                   expl = this.combattants[idxJoueurCourant].deposerBombe(visibilite,p);
                   //this.grille[p.getX()][p.getY()].setContenue(expl);
               }     
               else{
                   //expl = new MineVisible();
                   expl = this.combattants[idxJoueurCourant].deposerMine(visibilite, p);
                   //this.grille[p.getX()][p.getY()].setContenue(expl);                                  
               }
               //on affiche l'explosif dans la grille
               this.grille[p.getX()][p.getY()].setContenue(expl);
            }
            else if(visibilite==false){
                if(typeExplosif.equalsIgnoreCase("bombe")){
                   //expl = new BombeInvisible();
                   expl = this.combattants[idxJoueurCourant].deposerBombe(visibilite,p);
                   //this.grille[p.getX()][p.getY()].setContenue(expl);
               }     
               else if(typeExplosif.equalsIgnoreCase("mine")){
                   //expl = new MineInvisible();
                   expl = this.combattants[idxJoueurCourant].deposerMine(visibilite, p);
                  //this.grille[p.getX()][p.getY()].setContenue(expl);                 
               }
                //l'explosif ne sera pas visible dans la grille
                this.grille[p.getX()][p.getY()].setContenue(expl);
            }
                   
         }
         combattants[idxJoueurCourant].getExplosifsDepose().add(expl);
         notifyObservers();                   
    }
    
    @Override
    public void tir(char direction){
        
        if(!(this.combattants[idxJoueurCourant] instanceof Rebelle)){
        
            tireImpact(this.caseSuccessive(this.combattants[idxJoueurCourant].getPosition(), direction));
        }
        else{
             deplacerVers(posDestination(direction));
        }
       
    }
    
    public void bouclier(){
        
        Bouclier bouclier = combattants[idxJoueurCourant].getBouclier();
        
        Position p = combattants[idxJoueurCourant].getPosition();
        
        this.grille[p.getX()][p.getY()].setContenue(bouclier);

        
    }
    @Override
    public void addObserver(Observer obs){
        this.listeObserver.add(obs);
    }
   
    @Override
    public void notifyObservers(){
       for(Observer obs : this.listeObserver){
           obs.update(this, obs);
       }
   }

    @Override
    public void init(ChampDeBataille champ) {
    }
    @Override
    public Contenue getContenue(Position p) {        
        return this.grille[p.getX()][p.getY()].getContenue();
    }
    
    /**
     * Donne la position des 8 cases voisines de la position passée en paramètre
     * @param position Position courante
     * @return Un tableau contenant les 8 cases voisines de la position en paramètre 
     */
     public Position[] caseVoisine(Position position){
        
        Position voisines[] = new Position[9];
        
        voisines[0] = new Position(position.getX(),position.getY());
        voisines[1] = new Position(position.getX()+1,position.getY());
        voisines[2] = new Position(position.getX()-1,position.getY());
        voisines[3] = new Position(position.getX(),position.getY()+1);
        voisines[4] = new Position(position.getX(),position.getY()-1);
        voisines[5] = new Position(position.getX()+1,position.getY()+1);
        voisines[6] = new Position(position.getX()-1,position.getY()-1);
        voisines[7] = new Position(position.getX()+1,position.getY()-1);
        voisines[8] = new Position(position.getX()-1,position.getY()+1);
        
        return voisines;
    }
     
     public Position[] caseSuccessive(Position p,char direction){
        Position[] cases= new Position[Pistolet.PORTEE_MAX];
        int k;     
        if(direction=='z'){
                for(int i=0;i<Pistolet.PORTEE_MAX;i++){
                    
                    cases[i] = new Position(p.getX()-(i+1),p.getY());
                    
                    //System.err.println("pos"+listPositionTirees[k].toString());
                    
                    //k++;
                    
                }
            }
            else if(direction=='s'){
                k=0;
                for(int i=0;i<Pistolet.PORTEE_MAX;i++){
                   
                    cases[i] = new Position(p.getX()+(i+1),p.getY());
                    //System.err.println("pos"+listPositionTirees[k].toString());
                    //k++;
                
                }
            }
            else if(direction=='q'){
                //k=0;
                for(int i=0;i<Pistolet.PORTEE_MAX;i++){
                    
                    cases[i] = new Position(p.getX(),p.getY()-(i+1));
                    
                    //System.err.println("pos"+listPositionTirees[k].toString());
                    
                  //  k++;
                    
                }
            }
                    
            else if(direction=='d'){
                //k=0;
                
                for(int i=0;i<Pistolet.PORTEE_MAX;i++){
                    
                    cases[i] = new Position(p.getX(),p.getY()+(i+1));
                    
                    //System.err.println("pos"+listPositionTirees[k].toString());
                    
                    //k++;
                    
                }
            }

        //}

        return cases;
     }
     
     public void testeMort(){
        //teste combattant
        Combattant[] copie = new Combattant[this.nbreCombattant];
        int k=0,mort=0;
        for (Combattant combattant : this.combattants) {
            if (combattant.getEnergie() > 0) {
                //on recupere le combattant
                copie[k] = combattant;
                k++;
            }
            else if(combattant.getEnergie()<=0){
                mort+=1;
                this.grille[combattant.getPosition().getX()][combattant.getPosition().getY()].setContenue(null);
            }
        }
        if(mort>=1){
            this.nbreCombattant = this.nbreCombattant - mort;
            combattants = new Combattant[this.nbreCombattant];
            for(int i=0;i<combattants.length;i++){
                combattants[i] = copie[i]; 
            }
        }
     }
}

    


