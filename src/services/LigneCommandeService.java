package services;

import entities.LigneCommande;
//import entities.Produit;
import utils.MyDB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Mintoua
 */
public class LigneCommandeService implements ILigneCommande {
     Connection cnx;
      Statement ste;

    public LigneCommandeService(){
       cnx = MyDB.getInstance().getConnexion();
    }
    
    public void add(LigneCommande p) throws SQLException {
        String req = "insert into ligne_commande (id_produit,id_commande,quantitydemande) values (?,?,?)";

        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, p.getProduit_id());
            pst.setInt(2, p.getCommande_id());
            pst.setInt(3, p.getQuantityDemande());
            pst.executeUpdate();//uniqument avec l'ajout,la suppression et la modification dans la base de données
            System.out.println("panier mis à jour dans la base de données");
        } catch (SQLException err) {
            System.out.println(err.getMessage());
        }
    }

 
    public boolean delete(int idPanier) throws SQLException {
        String req = "delete from ligne_commande where id=?";
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, idPanier);
            pst.executeUpdate();
            System.out.println("Produit supprime de la commande");
            return true;
        } catch (SQLException err) {
            System.out.println(err.getMessage());
        }
        return false;
    }

 
    public boolean update(LigneCommande p) throws SQLException {
        String req = "update ligne_commande set quantitydemande=? where id=?";
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, p.getQuantityDemande());
            pst.setInt(2, p.getId());
            pst.executeUpdate();//uniqument avec l'ajout,la suppression et la modification dans la base de données
            System.out.println("Commande mis à jour avec success");
            return true;
        } catch (SQLException err) {
            System.out.println(err.getMessage());
        }
        return false;
    }

   
    public List<LigneCommande> readAll() throws SQLException {
        List<LigneCommande> list = new ArrayList();
        String req = "select * from ligne_commande ";
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
               LigneCommande p = new LigneCommande(rs.getInt("id_produit"),rs.getInt("id_commande"),rs.getInt("quantitydemande"));
                list.add(p);
            }

        } catch (SQLException err) {
            System.out.println(err.getMessage());
        }
        return list;
    }

   
    public ObservableList<LigneCommande> getPanier(int idC) {
        ObservableList<LigneCommande> Oblist = FXCollections.observableArrayList();
        String requete = "select * from ligne_commande where id_commande=?";
        try{

            PreparedStatement preparedStatement = cnx.prepareStatement(requete);
            preparedStatement.setInt(1, idC);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                LigneCommande panier = new LigneCommande(resultSet.getInt("id"),resultSet.getInt("quantitydemande"),
                        resultSet.getInt("id_commande"),resultSet.getInt("id_produit"));

                Oblist.add(panier);
            }

        } catch (SQLException err){
            System.out.println(err.getMessage());
        }
        return Oblist;
    }

 
}
