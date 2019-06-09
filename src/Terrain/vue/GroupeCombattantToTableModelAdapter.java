/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Terrain.vue;

import Terrain.GroupeCombattant;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author cyrus
 */
public class GroupeCombattantToTableModelAdapter extends AbstractTableModel{
    
    
    private static final int NBCHAMPS = 5;
    
    private static final int PSEUDO = 0;
    private static final int ENERGIE = 1;
    private static final int BOMBE = 2;
    private static final int MINE = 3;
    private static final int BALLE = 4;
    
    private final static String[] COLONNE;

 
    static {
        COLONNE = new String[NBCHAMPS];
        COLONNE[PSEUDO] = "Pseudo";
        COLONNE[ENERGIE] = "Energie";
        COLONNE[BOMBE] = "Bombe";
        COLONNE[MINE] = "Mine";
        COLONNE[BALLE] = "Balle";
    }
    
    

    GroupeCombattant gc;
    
   
    public GroupeCombattantToTableModelAdapter(GroupeCombattant gc){
        this.gc = gc;
    }

    @Override
    public int getRowCount() {
        return gc.getSize();
    }

    @Override
    public int getColumnCount() {
        return NBCHAMPS;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex){
            case PSEUDO : return gc.getCombattant(rowIndex).getPseudo();
            case ENERGIE: return gc.getCombattant(rowIndex).getEnergie();
            case BOMBE: return gc.getCombattant(rowIndex).getMinution()[0]; //bombe
            case MINE: return gc.getCombattant(rowIndex).getMinution()[1]; //mine
            case BALLE: return gc.getCombattant(rowIndex).getMinution()[2]; //pistolet
            default : return null;
        }
    }
    
    @Override
    public String getColumnName(int col) {
        return COLONNE[col];
    }
    
    public void removeRow(int indice){
        this.gc.remove(indice);
        this.fireTableRowsDeleted(indice, indice);
    }
    public void somethingHasChanged(Object source) {
        
        fireTableDataChanged();
        
    }
    
}
