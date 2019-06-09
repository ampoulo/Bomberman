package Terrain;

import entites.Case;
import entites.Contenue;
import Terrain.joueurArme.BombeInvisible;
import Terrain.joueurArme.MineInvisible;
import entites.Position;
import java.util.ArrayList;
import java.util.Observer;


public class ChampDeBatailleProxy implements Plateau{

    ChampDeBataille champ;
    Combattant c;
    public ChampDeBatailleProxy(ChampDeBataille champ, Combattant c){
        this.champ = champ;
        this.c = c;
    }



    @Override
    public void init(ChampDeBataille champ) {
        this.champ.init();
    }
    
    @Override
    public int getTaille() {
        return champ.getTaille();
    }

    @Override
    public Case[][] getGrille() {
        return champ.getGrille();
    }
    @Override
    public Contenue getContenue(Position p){
        if(champ.getGrille()[p.getX()][p.getY()].getContenue() instanceof BombeInvisible || 
                champ.getGrille()[p.getX()][p.getY()].getContenue() instanceof MineInvisible){
            for(int i=0; i<champ.getNbreCombattant();i++){
                if(!champ.getCombattant()[i].equals(c)){
                    if(champ.getCombattant()[i].getExplosifsDepose().contains(champ.getGrille()[p.getX()][p.getY()].getContenue())){
                        return null;                                        
                    }                 
                }
            }
        }
        return champ.getContenue(p);
    }

   
    @Override
    public int getNbreCombattant() {
        return champ.getNbreCombattant();
    }

    @Override
    public Combattant[] getCombattant() {
        return champ.getCombattant();
    }
        
    @Override
    public void nextPlayer() {
        this.champ.nextPlayer();
    }

    @Override
    public void tir(char direction) {
        this.champ.tir(direction);
    }
    
    @Override
    public void addObserver(Observer obs){
        this.champ.addObserver(obs);
    }
   
    @Override
    public void notifyObservers(){
       for(Observer obs : this.champ.getListObserver()){
           obs.update(this.champ, obs);
       }
   }

    @Override
    public void teste() {
        this.champ.teste();
    }

    @Override
    public void placerExplosif(boolean visibilite, Position position, String typeExplosif)
    {
        this.champ.placerExplosif(visibilite, position, typeExplosif);
    }

    @Override
    public void playGame()
    {
        this.champ.playGame();
    }

    @Override
    public int getIdxJoueurCourant()
    {
        return this.champ.getIdxJoueurCourant();
    }

    @Override
    public ArrayList<Observer> getListObserver()
    {
        return this.champ.getListObserver();
    }
}