
package Terrain;

import Terrain.joueurArme.*;
import entites.Contenue;
import Terrain.initStrategie.StrategieCombattant;
import entites.Position;
import java.util.ArrayList;

/**
 *
 * @author cyrus
 */
public abstract class Combattant implements Contenue{

    private StrategieCombattant strategie;
    private static final int COUT_DEPLACEMENT = 2;
    private static final int COUT_BOUCLIER = 5;

    protected String pseudo;
    protected int energie;
    protected Bouclier bouclier;
    protected Position position;
    protected Arme[] armes;
    private ArrayList<Explosable> explosifsDepose;
    protected int[] minution;
    /**
     * 
     * @param strategie Spécifie la strategie du joueur dans le jeu
     * @param pseudo Nom du joueur
     * @param position Position du joueur dans la grille
     */
    public Combattant(StrategieCombattant strategie, String pseudo, Position position){
        
        this.strategie = strategie;        
        this.pseudo = pseudo;
        this.bouclier = new Bouclier();
        this.position = position;
        explosifsDepose = new ArrayList<>();
    }
     /** 
     * @param champ
     * 
     */
    public void action(ChampDeBataille champ){
        this.strategie.action(champ, this);
        
    }
    public String decision(){
        return this.strategie.getDecision();
    }
    /**
     * Permet de changer la position du joueur et de soustraire le cout
     * @param position Position vers laquelle le joueur se deplace
     */
    public void seDeplace(Position position){
        this.setPosition(position);
        this.perteEnergie(COUT_DEPLACEMENT);
    }

    /**
     * Permet d'activer le bouclier du combattant
     */
    public void activeBouclier(){
        this.bouclier.setEtat(true);
        this.energie -= COUT_BOUCLIER;
    }
    /**
     * Liste contenant l'ensemble des explosifs déposés sur le jeu par le combattant
     * @return L'ensemble des explosifs déposés par le combattant
     */
    public ArrayList<Explosable> getExplosifsDepose() {
        return explosifsDepose;
    }
    /**
     * 
     * @param visibilite Visibilité de la bombe
     * @param p Position de la bombe dans la grille
     * @return La bombe déposée dans le jeu
     */
    public Bombe deposerBombe(boolean visibilite,Position p){
        Bombe b=null;
        
        if(minution[0]>0){
          
            this.minution[0] -= 1;
            
            if(visibilite==true)
                b = new BombeVisible();
            else
                b = new BombeInvisible();
            
            b.activerExplosif(p);
            
            return b;
        
        }
        
        return null;
    }
    /**
     * 
     * @param visibilite Visibilité de la bombe
     * @param p Position de la bombe
     * @return La bombe déposée dans le jeu
     */
    public Mine deposerMine(boolean visibilite, Position p){
        Mine m = null;
        
        if(minution[1]>0){
        
            this.minution[1] -= 1;
           
            if(visibilite==true)
                m = new MineVisible();
            else if(visibilite==false)
                m = new MineInvisible();
            
            m.activerExplosif(p);
            
            return m;
        
        }
     
        return null;
    }
    /**
     * 
     */
    public void tirer(){
        minution[2] -= 1;
    }
    /**
     * Change la position du joueur par la position passée en paramètre
     * @param position Position du joueur
     */
    public void setPosition(Position position) {
        this.position = position;
    }
    /**
     * 
     * Permet de charger les armes du joueur.Chaque arme avec sa quantité de minution.
     */
    public abstract void chargerArme();
    /**
     * 
     * @param energie Nouvelle quantité d'energie du joueur
     */
    public abstract void gainEnergie(int energie);
    /**
     * Permet de diminuer l'energie du joueur par la variable passée en paramètre.
     * @param perte Quantité d'energie que le joueur a perdu
     */   
    public void perteEnergie(int perte){
        if(this.bouclier.getEtat()==false){
            if(this.energie<=0)
                this.energie = 0;
            else
                this.energie -= perte;
        }
    }

    /**
     * Donne le pseudo du joueur
     * @return Le pseudo du joueur
     */
    public String getPseudo() {
        return pseudo;
    }

    /**
     * Donne la quantité d'energie courante du joueur
     * @return La quantité d'energie vourante du joueur
     */
    public int getEnergie() {
        return energie;
    }

    /**
     * 
     * @return Un tableau contenant la liste des armes disponibles pour un joueur
     */
    public Arme[] getArme() {
        return this.armes;
    }
    /**
     * 
     * @return Un tableau contenant la liste des minutions pour chaque arme
     */
    public int[] getMinution(){
        return this.minution;
    }
    /**
     * Le bouclier du joueur pour se proteger des attaques adverses
     * @return le bouclier du joueur
     */
    public Bouclier getBouclier() {
        return bouclier;
    }
    
    /**
     * 
     * @return La position courante du joueur
     */
    public Position getPosition() {
        return position;
    }
    /**
     * 
     * @return Une chaine représentant la quantité d'energie, les armes du joueur et leur minution
     */
    public static String caracteristique(){
        System.out.println("------------------------------------------------------");
        return "CARACTERISTIQUES \tEnergie\tBombes\tMines\tBalles\n";
    }
    /**
     * Retourne les caractéristiques courantes du joueur
     * @return Compléte la chaine retournée par la methode caracteristique
     */
    public abstract String caracteristiqueCourant();   
}
