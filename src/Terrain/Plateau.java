
package Terrain;


import entites.Case;
import entites.Contenue;
import entites.Position;
import java.util.ArrayList;
import java.util.Observer;

/**
 *
 * @author cyrus
 */
public interface Plateau {
    /**
     * Affiche une représentation textuelle de la grille du jeu
     * @return Une représentation textuelle de la grille du jeu 
     */
    @Override
    String toString();//pour le proxy
    
    /**
     * Initaialise la grille selon la strategie choisie
     * @param champ Représente une instance de du plateau de jeu
     */
    void init(ChampDeBataille champ);//pour la strategie de remplissage (pattern strategy)    
    /**
     * Retourne la taille de la grille.
     * @return La taille de la grille 
     */
    int getTaille();
    /**
     * Retourne une matrice représentant la grille
     * @return Matrice représentant la grille 
     */
    Case[][] getGrille();
    /**
     * 
     * @param position
     * @return 
     */
    Contenue getContenue(Position position);
    /**
     * 
     * @return Le nombre de combattant en cours dans le jeu 
     */
    int getNbreCombattant();
    /**
     * Retourne la liste des combattants des combattants du jeu
     * @return Un tableau contenant les combattants en cours dans le jeu 
     */
    Combattant[] getCombattant();
    /**
     * Teste 
     */
    void teste();
    /**
     * Permet de placer un explosif dans la grille
     * @param visibilite Spécifie la visibilité de l'explosif
     * @param position Spécifie la position de l'explosif
     * @param typeExplosif Spécifie le type de l'explosif 
     */
    void placerExplosif(boolean visibilite,Position position,String typeExplosif);
    /**
     * Permet de passer le tour au joueur suivant dans la liste des joueurs
     */
    void nextPlayer();
    /**
     *Permet d'effectuer un tir suivant la direction spécifiée en paramètre 
     * @param direction Représente le sens du tir
     */
    void tir(char direction);
    /**
     * 
     */
    void playGame();
    /**
     * Retourne l'indice du joueur ayant le tour de jeu
     * @return L'indice du joueurs ayant le tour de jeu
     */
    int getIdxJoueurCourant();
    /**
     * Permet d'ajouter l'observer passé en paramètre dans la liste des observers
     * @param obs Instance de Observer 
     */
    void addObserver(Observer obs);
    /**
     * Notifie les observateurs lors d'un changement
     */
    void notifyObservers();
    /**
     * 
     * @return 
     */
    ArrayList<Observer> getListObserver();
}
