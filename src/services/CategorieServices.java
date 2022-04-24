/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Categorie;
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
import services.ICategorie.ICategories;
import utils.MyDB;

/**
 *
 * @author ACER
 */
public class CategorieServices implements ICategories <Categorie> {
    
    Connection cnx;

    public CategorieServices() {
     cnx=MyDB.getInstance().getConnexion();
    }

    @Override
    public void Update(Categorie c, int id) throws SQLException {
        try {

            String req = "Update `categorie` set  nom_cat = ? , description_cat = ? where id_cat ="+id;
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(2, c.getDescription_cat());
            ps.setString(1, c.getNom());
            ps.executeUpdate();
            System.out.println("Type modifié !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    @Override
    public List<Categorie> afficherCategorie() throws SQLException {
        List< Categorie> list = new ArrayList<>();
        try {
            String req ="select * from categorie";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            
            while(rs.next()){
                Categorie  c = new Categorie();
                c.setId_categorie(rs.getInt(1));
                c.setNom(rs.getString("nom_cat"));
                c.setDescription_cat(rs.getString("description_cat"));
                list.add(c);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ProductServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;  }
    @Override
    public void ajouter(Categorie c) throws SQLException {
        String req = "INSERT INTO categorie (nom_cat,`description_cat` ) "
                + "VALUES (?,?) ";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setString(1, c.getNom());
        ps.setString(2, c.getDescription_cat());
        ps.executeUpdate();  }
}