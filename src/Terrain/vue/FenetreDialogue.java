/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Terrain.vue;

import javax.swing.JOptionPane;

/**
 *
 * @author ibrahima
 */
public class FenetreDialogue {
    static public int launch() {
    String[] choix = {"Interface graphique", "Console", "Quitter"};
    JOptionPane jop = new JOptionPane();
    int option = jop.showOptionDialog(null,
      "Choisossez un mode  d'éxécution !",
      "Jeu Strategie",
      JOptionPane.YES_NO_CANCEL_OPTION,
      JOptionPane.INFORMATION_MESSAGE,
      null,
      choix,
      choix[2]);
      return option;
  }
    static public String nombreCombattant(){
        JOptionPane jop = new JOptionPane();
         String nbcom;
         nbcom =jop.showInputDialog(null, "Veuillez entrer le nombre de combattant!", "NOMBRE DE COMBATTANT !",JOptionPane.QUESTION_MESSAGE);
         return nbcom;
    }
    static public int Information(String pseudo){
        String info = "Félicitaion "+pseudo+" a gagné!!!";
        JOptionPane pane = new JOptionPane();
        String[] choix = {"Recommencer","Terminer"};
        int option = pane.showOptionDialog(null,info,"Jeu Strategie",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.INFORMATION_MESSAGE,null,choix,choix[0]);
        
        return option;
    }
}
