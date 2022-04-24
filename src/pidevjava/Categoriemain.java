/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidevjava;



import entities.Categorie;
import java.sql.SQLException;
import services.CategorieServices;

/**
 *
 * @author ACER
 */
public class Categoriemain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        // TODO code application logic here
        Categorie c = new Categorie("DINNER", "dinner");
        Categorie c1 = new Categorie("LUNCH", "lunch");
   CategorieServices ps = new CategorieServices();
   try {
            ps.Update(c1,19); 
            System.out.println("modification de categorie avec succes");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
   try {
            ps.ajouter(c);
            System.out.println("ajoutation de produit avec succes");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}
