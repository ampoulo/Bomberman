package Terrain.joueurArme;


import entites.Contenue;
import entites.Position;



/**
 *Cette classe correspond à un groupe d'armes qui sont capables d'exploser ou d'avoir une position dans la grille.
 * @author MADINA DIENG
 */
public abstract class Explosable extends Arme implements Contenue {
  
    protected Position position;
    protected String type;
   
    /**
     * Permet d'activer l'explosif en question en initialisant sa position dans la grille
     * @param position Position de l'explosif dans la grille
     */
   protected abstract void activerExplosif(Position position);
   
   /**
    * Correspond à la position de l'explosif dans la grille
    * @return La position courante de l'explosif dans la grille
    */
   public Position getPosition(){
       return this.position;
   }
   /**
    * Modifie la position de l'explosif dans la grille
    * @param position Position de l'explosif dans la grille
    */
   public void setPosition(Position position){
       this.position = position;
   }
   

}
