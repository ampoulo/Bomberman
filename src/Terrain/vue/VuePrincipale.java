
package Terrain.vue;

import Terrain.GroupeCombattantImpl;
import Terrain.GroupeCombattant;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;
import javax.swing.*;



/**
 *
 * @author ibrahima
 */
public class VuePrincipale  extends JPanel  implements Observer{
   
   
   private VueChampDeBataille p;
   private JTable table;
   private GroupeCombattantToTableModelAdapter modelAdapter;
   private JPanel right,north,south,left;
   GroupeCombattant gc;
   
    public VuePrincipale(VueChampDeBataille p){
         
        this.p = p;
         gc = new GroupeCombattantImpl();
        
        for(int i=0;i<p.getChampdebataille().getNbreCombattant();i++){
            gc.add(p.getChampdebataille().getCombattant()[i]);
        }
        modelAdapter = new GroupeCombattantToTableModelAdapter(gc);
        
        table = new JTable(modelAdapter);
        JScrollPane scrollpane = new JScrollPane(table);
        scrollpane.setSize(100, 100);
        table.setGridColor(Color.GREEN);
        table.setBackground(Color.WHITE);
        
        this.setLayout(new BorderLayout(10,25)); 
        
        init();
    }
    
    public void updateTable(){
        modelAdapter.gc = new GroupeCombattantImpl();
        for(int i=0;i<p.getChampdebataille().getNbreCombattant();i++){
            modelAdapter.gc.add(p.getChampdebataille().getCombattant()[i]);
        }
        
        
    }
    public void init(){
       
        north = new JPanel();                                                
        south = new JPanel();
        right = new JPanel();
        left = new JPanel();
        left.setSize(100,100);
        
        south.setBackground(Color.GREEN);
        north.setBackground(Color.GREEN); 
        right.setBackground(Color.GREEN);
        right.setLayout(new GridLayout(2,1));
        
        JPanel panel1 = new JPanel();
        table.getSelectionModel().addSelectionInterval(0, 0);
        table.setFont(new Font("Calibri", Font.PLAIN, 16));
        table.setRowSelectionAllowed(true);
        JScrollPane scroll = new JScrollPane(table);
        scroll.setBackground(Color.GREEN);
        
        panel1.add(table.getTableHeader());
        panel1.add(scroll);
        
        JPanel panel2 = new JPanel();
        panel2.setBackground(new Color(14,209,69));
        panel2.setLayout(new GridLayout(4,4));
        
        JLabel l0 = new JLabel("Légende");
        l0.setFont(new Font("Arial",Font.ITALIC+Font.BOLD,15));
        panel2.add(l0);
        panel2.add(new JLabel(""));
        panel2.add(new JLabel(""));
        panel2.add(new JLabel(""));
        panel2.add(new JLabel(""));
        panel2.add(new JLabel(""));
        
        
        JLabel l1 = new JLabel();
        JLabel l2 = new JLabel();
        JLabel l3 = new JLabel();
        JLabel l4 = new JLabel();
        JLabel l5 = new JLabel();
        JLabel l6 = new JLabel();
        JLabel l7 = new JLabel();
        JLabel l8 = new JLabel();
        JLabel l9 = new JLabel();
        
        l1.setIcon(new ImageIcon(getClass().getResource("../../image/rebelle2.png")));
        l2.setIcon(new ImageIcon(getClass().getResource("../../image/sniper1.png")));
        l3.setIcon(new ImageIcon(getClass().getResource("../../image/soldat2.png")));
        l4.setIcon(new ImageIcon(getClass().getResource("../../image/pastille1.png")));
        l5.setIcon(new ImageIcon(getClass().getResource("../../image/bombe1.png")));
        l6.setIcon(new ImageIcon(getClass().getResource("../../image/bouclier1.png")));
        l7.setIcon(new ImageIcon(getClass().getResource("../../image/mine1.png")));
        l8.setIcon(new ImageIcon(getClass().getResource("../../image/explosion.png")));
        l9.setIcon(new ImageIcon(getClass().getResource("../../image/explosionmine.png")));
        
        panel2.add(l1);
        panel2.add(new JLabel("Rebelle"));
        
        panel2.add(l2);
        panel2.add(new JLabel("Sniper"));
        
        panel2.add(l3);
        panel2.add(new JLabel("Soldat"));
        
        panel2.add(l4);
        panel2.add(new JLabel("Pastille"));
        
        panel2.add(l5);
        panel2.add(new JLabel("Bombe"));
        
        panel2.add(l6);
        panel2.add(new JLabel("Bouclier"));
        
        panel2.add(l7);
        panel2.add(new JLabel("Mine"));
        
        panel2.add(l8);
        panel2.add(new JLabel("Explosion"));
        
        panel2.add(l9);
        panel2.add(new JLabel("Bléssé"));
        
        right.add(panel2);
        right.add(panel1);
        
        right.setPreferredSize(new Dimension(450,450));
        
        north.add(new JLabel("Jeu de Strategie au tour par tour"));
        this.add(p,BorderLayout.CENTER);
        this.add(right,BorderLayout.EAST);
        this.add(north,BorderLayout.NORTH);
        this.add(south,BorderLayout.SOUTH);
        this.add(left,BorderLayout.WEST);
       
    }
    
    @Override
    public void update(Observable o, Object arg) {
       
       table.getSelectionModel().clearSelection();
       table.getSelectionModel().addSelectionInterval(p.getChampdebataille().getIdxJoueurCourant(), p.getChampdebataille().getIdxJoueurCourant());
       updateTable();
       
       repaint();
       
    }

    
}