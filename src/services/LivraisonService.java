/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Livraison;
import entities.Livreur;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.MyDB;

/**
 *
 * @author hanna
 */
public class LivraisonService implements ILivraison<Livraison>{
    Connection connexion;
    Statement stm;
    

    public LivraisonService() {
        connexion = MyDB.getInstance().getConnexion();
    }

    @Override
    public void ajouterLivraison(Livraison liv) throws SQLException {
        String req = "INSERT INTO `livraison` (`Adresse_livraison`, `description_livraison`, `etat_livraison`) VALUES ( '"
    + liv.getAdresse_livraison()+ "', '" + liv.getDescription_livraison()+ "', '" + liv.getEtat_livraison()+ "') ";
        stm = connexion.createStatement();
        stm.executeUpdate(req);
    }
    
    public void ajouterLivrai(Livraison liv) throws SQLException {
        String req = "INSERT INTO `livraison` ( `description_livraison`, `etat_livraison`,`Adresse_livraison`) "
                + "VALUES (?, ?,?) ";
        PreparedStatement ps = connexion.prepareStatement(req);
        ps.setString(1, liv.getDescription_livraison());
        ps.setInt(2, liv.getEtat_livraison());
//        ps.setInt(3, liv.getId_livreur());
        ps.setString(3, liv.getAdresse_livraison());
 
        ps.executeUpdate();
    }
    
    @Override
    public ObservableList<Livraison> getAll() throws SQLException {
        ObservableList <Livraison> list = FXCollections.observableArrayList();
        // String req = "select * from order o inner join user u on u.id=o.id_user";
        String req = "select * from `livraison` ";

        try {
            PreparedStatement pst = connexion.prepareStatement(req);
            ResultSet rs = pst.executeQuery();
            while (rs.next()){
                list.add(new Livraison(rs.getString("Adresse_livraison"),rs.getInt("id_livreur"),rs.getString("description_livraison"),rs.getInt("etat_livraison")));
            }
        }catch (SQLException err){
            System.out.println(err.getMessage());
        }
        return list;
    }

    @Override
    public List<Livraison> afficherlivraison() throws SQLException {
         List<Livraison> livreurs = new ArrayList<>();
        String req = "select * from livraison";
        stm = connexion.createStatement();
        //ensemble de resultat
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
            Livraison liv = new Livraison(rst.getInt("id_livraison"),//or rst.getInt(1)
                    rst.getString("Adresse_livraison"),
                    rst.getString("description_livraison"),
                    rst.getInt("etat_livraison")
            
            );
            livreurs.add(liv);
        }
        return livreurs;
    }

    @Override
    public void deleteLivraison(int id_livraison) throws SQLException {
    String requete = "DELETE FROM livraison WHERE id_livraison=" + id_livraison;
        Statement ste = connexion.createStatement();
        ste.executeUpdate(requete);
        System.out.println("Livraison supprime avec succes");
    }

    @Override
    public void modifierLiv(Livraison liv, int id_livraison) throws SQLException {
        String req = "UPDATE livraison SET   Adresse_livraison = ?, description_livraison = ?,etat_livraison=?  where id_livraison = " + id_livraison;
        PreparedStatement pre = connexion.prepareStatement(req);
     
        pre.setString(1, liv.getAdresse_livraison());
        pre.setString(2, liv.getDescription_livraison());
        pre.setInt(3, liv.getEtat_livraison());
       
        pre.executeUpdate();
    }

    

    
    
}
