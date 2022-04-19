/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Cart;
import entities.Commande;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.MyDB;

/**
 *
 * @author Zeineb Hamdi
 */
public class CartService {
    
         Connection cnx;
     Statement ste;

    public CartService(){
        cnx = MyDB.getInstance().getConnexion();
    }
    
    public ObservableList<Cart> getAll() throws SQLException {
        ObservableList <Cart> list = FXCollections.observableArrayList();
        // String req = "select * from order o inner join user u on u.id=o.id_user";
        String req = "select * from `cart` ";

        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            ResultSet rs = pst.executeQuery();
            while (rs.next()){
                list.add(new Cart(rs.getString("nomProduit"), rs.getFloat("prix"),rs.getInt("quantite"), rs.getString("image")));
            }
        }catch (SQLException err){
            System.out.println(err.getMessage());
        }
        return list;
    }
    
    
    
    public void Update(Cart c, int id_c) {
        try {
        String req = " update cart set quantite='"+c.getQuantite()+"' where id ='"+id_c+"' ";
        
            PreparedStatement pre = cnx.prepareStatement(req);
       
        pre.executeUpdate();
        System.out.println(" publication modifiè !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
    }
        
    }
    
    
}
