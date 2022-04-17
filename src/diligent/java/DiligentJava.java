/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diligent.java;


import services.LigneCommandeService;
import entities.Commande;
import entities.LigneCommande;
import java.sql.Date;
import java.sql.SQLException;
import services.commandeService;

/**
 *
 * @author Zeineb Hamdi
 */
public class DiligentJava {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
            
        Commande c = new Commande(0,"2020-12-03", (float) 12.5);
        
        commandeService sp = new commandeService();
        
        sp.create (c);
        System.out.println("ajout avec succes");
        
        try {
            System.out.println(sp.readAll());
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
//        try {
//           sp.delete(50);
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
//        
//       try {
//            sp.update(49);
//            System.out.println("modifie avec succes");
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }


//           
//        LigneCommande c = new LigneCommande(18, 30, 2);
//        
//        LigneCommandeService sp = new LigneCommandeService();
//        
//        try {
//            sp.add(c);
//            System.out.println("ajout avec succes");
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
//        
//        try {
//            System.out.println(sp.readAll());
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
//        
//        try {
//           sp. delete(37);
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
//        
//        
//         
//             LigneCommande c1 = new LigneCommande(38,18,30,4);         
//       try {
//            sp.update(c1);
//            System.out.println("modifie avec succes");
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }

    }
    
}
