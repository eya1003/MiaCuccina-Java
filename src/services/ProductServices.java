/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Produit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.MyDB;

/**
 *
 * @author NADA_USER
 */
public class ProductServices implements IProduit <Produit> {
Connection cnx;

    public ProductServices() {
     cnx=MyDB.getInstance().getConnexion();
     
    }
    @Override
    public void ajouter(Produit p) { 
        String req = "INSERT INTO `produit`(`nom`, `quantite_produit`,`prix_produit` ,`description_produit`,`photo_pro`,`like_dislike`) "
                + "VALUES ( ?, ?,?, ?,?,?) ";
        try {
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setString(1, p.getNom());
        ps.setDouble(2, p.getQuantite_produit());
        ps.setFloat(3, p.getPrix_produit());
        ps.setString(4, p.getDescription_produit());
        ps.setString(5, p.getPhoto_pro());
        ps.setInt(6, p.getLike_dislike());
        ps.executeUpdate();
        System.out.println("produit ajoutée !");
         } catch (SQLException err) {
            System.out.println(err.getMessage());
        }
    }
    @Override
    public void ajouter1(Produit p) { 
        String req = "INSERT INTO `produit`(`id_categorie` , `nom`, `prix_produit`,`description_produit`,`quantite_produit`,`photo_pro`,`like_dislike`) "
                + "VALUES ( ?,?, ?,?, ?,?,"+1+")";
        try {
        PreparedStatement ps = cnx.prepareStatement(req); 
        ps.setInt(1, p.getId_categorie());
        ps.setString(2, p.getNom());
        ps.setDouble(5, p.getQuantite_produit());
        ps.setFloat(3, p.getPrix_produit());
        ps.setString(4, p.getDescription_produit());
        ps.setString(6, p.getPhoto_pro());
     // ps.setInt(6,1);
        ps.executeUpdate();
        System.out.println("produit ajoutée !");
         } catch (SQLException err) {
            System.out.println(err.getMessage());
        }
    }



    @Override
    public boolean delete(String nom)  {
        try {
            String req = "delete from produit where nom=?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1,nom);
            ps.executeUpdate();
            System.out.println("produit supprimé !");
        } catch (SQLException ex) {
            Logger.getLogger(ProductServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    return false;
    }

    @Override
    public List<Produit> afficherProduit()  {
        
        List<  Produit> list = new ArrayList<>();
        try {
            String req ="select * from produit";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            
            while(rs.next()){
                Produit p = new   Produit();
                p.setId_produit(rs.getInt(1));
                p.setNom(rs.getString("nom"));
                p.setQuantite_produit( rs.getInt("quantite_produit"));
                p.setPrix_produit( rs.getFloat("prix_produit"));
                p.setDescription_produit(rs.getString("description_produit"));
                p.setPhoto_pro(rs.getString("photo_pro"));
              
                list.add(p);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ProductServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;

    } 
 public ObservableList<Produit> getAll() {
        ObservableList <Produit> list = FXCollections.observableArrayList();
        // String req = "select * from order o inner join user u on u.id=o.id_user";
        String req = "select * from commande ";

        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            ResultSet rs = pst.executeQuery();
            while (rs.next()){
                list.add(new Produit (rs.getString("nom"), rs.getFloat("prix"),rs.getString("description")));
            }
        }catch (SQLException err){
            System.out.println(err.getMessage());
        } 
        return list; 
    }
    @Override
    public void Update(Produit p, int id_produit) {
        try {
        String req = " update produit set nom='"+p.getNom()+"', prix_produit='"+p.getPrix_produit()+
                            "', description_produit='"+p.getDescription_produit()+"',quantite_produit='"+p.getQuantite_produit()+
                            "', photo_pro='"+p.getPhoto_pro()+"', like_dislike='"+p.getLike_dislike()+"' where id_produit ='"+id_produit+"' ";
        
            PreparedStatement pre = cnx.prepareStatement(req);
       
        pre.executeUpdate();
        System.out.println(" publication modifiè !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
    }
        
    }
    
     public ArrayList<Produit> TrierParId() {

        ArrayList<Produit> List = new ArrayList<>();
        try {

            String req = "select * from produit ORDER BY nom ";
          PreparedStatement pre = cnx.prepareStatement(req);

            ResultSet rs = pre.executeQuery(req);

            while (rs.next()) {
                Produit p = new Produit();

                p.setNom(rs.getString("nom"));
                p.setPrix_produit(rs.getFloat("prix_produit"));
                p.setDescription_produit(rs.getString("description_produit"));
                p.setPhoto_pro(rs.getString("photo_pro")); 
                List.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } 
        return List;
    }
   
     public List<Produit> RechercherProduit (String x) {
        ArrayList<Produit> List = new ArrayList<>();
        try {
            String req = "Select * from produit where  nom like '%" + x + "%'  ";
            System.out.println("aa: "+x);
       PreparedStatement pre = cnx.prepareStatement(req);

            ResultSet rs = pre.executeQuery(req);
                while (rs.next()) {
                 Produit p = new Produit();
                     
               p.setNom(rs.getString("nom"));
                p.setPrix_produit(rs.getFloat("prix_produit"));
                p.setDescription_produit(rs.getString("description_produit"));
                p.setPhoto_pro(rs.getString("photo_pro"));
                
                    List.add(p);
                } 
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        } 
        if (List.isEmpty()) {
            System.out.println("NOT FOUND");
        }
        return List;
    }

}


    