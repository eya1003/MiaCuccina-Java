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
        try {
 String req = "INSERT INTO `produit`(`nom`, `quantite_produit`,`prix_produit` ,`description_produit`,`photo_pro`,`like_dislike`) "
                + "VALUES ( ?, ?,?, ?,?,?) ";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setString(1, p.getNom());
        ps.setDouble(2, p.getQuantite_produit());
        ps.setFloat(3, p.getPrix_produit());
        ps.setString(4, p.getDescription_produit());
        ps.setString(5, p.getPhoto_pro());
        ps.setInt(6, p.getLike_dislike());
        ps.executeUpdate();
         } catch (SQLException err) {
            System.out.println(err.getMessage());
        }
    }



    @Override
    public boolean delete(int id_produit)  {
        try {
            String req = "delete from produit where id_produit = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, id_produit);
            ps.executeUpdate();
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

    @Override
    public void Update(Produit p,int id) throws SQLException {
        String req = "UPDATE produit SET   nom = ?, quantite_produit = ? , prix_produit = ? , description_produit = ?, photo_pro= ?, like_dislike = ? where id_produit= " + id;
        PreparedStatement pre = cnx.prepareStatement(req);
        pre.setString(1, p.getNom());
        pre.setDouble(2, p.getQuantite_produit());
         pre.setFloat(3, p.getPrix_produit());
       pre.setString(4, p.getDescription_produit());
       pre.setString(5, p.getPhoto_pro());
       pre.setInt(6, p.getLike_dislike());
        pre.executeUpdate();
    }
}