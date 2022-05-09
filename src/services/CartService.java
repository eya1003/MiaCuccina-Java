/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Cart;
import entities.Produit;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import utils.MyDB;

/**
 *
 * @author Zeineb Hamdi
 */
public class CartService {

    Connection cnx;
    Statement ste;

    public CartService() {
        cnx = MyDB.getInstance().getConnexion();
    }

    public void ajouter(Cart p) {
        String req = "INSERT INTO `cart`(`idProduit`,`nomProduit`, `prix`,`quantite` ,`image`) "
                + "VALUES ( ?,?, ?,?, ?) ";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, p.getIdProduit());
            System.out.println(p.getIdProduit());
            ps.setString(2, p.getNomProduit());
            ps.setFloat(3, p.getPrix());
            ps.setInt(4, p.getQuantite());
            ps.setString(5, p.getImage());

            ps.executeUpdate();
            System.out.println("produit ajoutée !");
        } catch (SQLException err) {
            System.out.println(err.getMessage());
        }
    }

    public ObservableList<Cart> getAll() throws SQLException {
        ObservableList<Cart> list = FXCollections.observableArrayList();
        // String req = "select * from order o inner join user u on u.id=o.id_user";
        String req = "select * from `cart` ";

        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
System.out.println(rs.getString("image"));
                Cart tmp = new Cart(rs.getString("nomProduit"), rs.getFloat("prix"), rs.getInt("quantite"), rs.getString("image"));
                File file = new File(tmp.getImage());
                javafx.scene.image.Image ima = new javafx.scene.image.Image(file.toURI().toString());

                ImageView imageView = new ImageView(ima);
                imageView.setImage(ima);
                imageView.setFitHeight(100);
                imageView.setFitWidth(100);

                tmp.setImageView(imageView);
                list.add(tmp);
                // new ImageView( new Image(this.getClass().getResourceAsStream( "C:\\xampp\\htdocs\\PI-DEV\\public\\uploads\\images\\"+rs.getString("image"))))));
            }
        } catch (SQLException err) {
            System.out.println(err.getMessage());
        }
        return list;
    }

    public void Update(Cart c, int id_c) {
        try {
            String req = " update cart set quantite='" + c.getQuantite() + "' where id ='" + id_c + "' ";

            PreparedStatement pre = cnx.prepareStatement(req);

            pre.executeUpdate();
            System.out.println(" publication modifiè !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public ArrayList<Cart> TrierParId() {

        ArrayList<Cart> List = new ArrayList<>();
        try {

//            String requete = "select * from membre where role != 'Admin' ORDER BY id DESC ";
            String req = "select * from cart ORDER BY nomProduit";
            PreparedStatement pre = cnx.prepareStatement(req);

            ResultSet rs = pre.executeQuery(req);

            while (rs.next()) {
                Cart m = new Cart();

                m.setNomProduit(rs.getString("nomProduit"));
                m.setPrix(rs.getInt("prix"));
                m.setQuantite(rs.getInt("quantite"));
                m.setImage(rs.getString("image"));

                List.add(m);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return List;
    }

    public List<Cart> RechercherProduit(String x) {
        ArrayList<Cart> List = new ArrayList<>();
        try {
            String req = "Select * from cart where nomProduit like '%" + x + "%' or prix like '%" + x + "%' or prix like '%" + x + "%'  ";
            System.out.println("aa: " + x);
            PreparedStatement pre = cnx.prepareStatement(req);

            ResultSet rs = pre.executeQuery(req);
            while (rs.next()) {
                Cart m = new Cart();

                m.setNomProduit(rs.getString("nomProduit"));
                m.setPrix(rs.getInt("prix"));
                m.setQuantite(rs.getInt("quantite"));
                m.setImage(rs.getString("image"));
                System.out.println("x:" + x);
                List.add(m);
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
