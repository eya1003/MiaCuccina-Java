/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidevjava;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import entities.Produit;
import java.sql.Date;
import java.sql.SQLException;
import services.ProductServices;

/**
 *
 * @author Zeineb Hamdi
 */
public class Pidev {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        //Produit p = new Produit("zeineb", 1, 3, "description_produit", "photo_pro",0);
        
        ProductServices sp = new ProductServices();
        //sp.ajouter(  new Produit("nour", 1, 3, "description_produit", "photo_pro",0));
        //sp.Update(new Produit("nour",5,3,"fff","azerty",1)); 
       // sp.delete(new Produit("zeineb"));
        
//        
//try {
//       sp.Update(p);
//        System.out.println("modifie avec succes");
//      } catch (SQLException ex) {
//           System.out.println(ex.getMessage());
//       }
//  }
        //System.out.println(sp.afficherProduit());
        //System.out.println("ajout avec succes");
    }}
    
//        try {
//            System.out.println(sp.affichercommande());
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
//        
//    try {
//           sp.deleteCommande(29);
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
     
//    
//}
