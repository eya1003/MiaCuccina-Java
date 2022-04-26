/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Cart;
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
            System.out.println("aa: "+x);
       PreparedStatement pre = cnx.prepareStatement(req);

            ResultSet rs = pre.executeQuery(req);
                while (rs.next()) {
                    Cart m = new Cart();

                 m.setNomProduit(rs.getString("nomProduit"));
                m.setPrix(rs.getInt("prix"));
                m.setQuantite(rs.getInt("quantite"));
                m.setImage(rs.getString("image"));
                    System.out.println("x:" +x);
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
