///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package services;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import entities.Cart;
//import entities.Produit;
//import utils.MyDB;
//
///**
// *
// * @author Mohamed
// */
//public class PanierService {
//
//    Connection cnx = MyDB.getInstance().getConnexion();
//
//    public void addProduct(int userId, int productId) throws SQLException {
//        System.out.println(testDuplication(userId, productId));
//        System.out.println(getQtOfProduct(userId, productId));
//        if (testDuplication(userId, productId) == true) {
//            String req = "insert into cart (idProdui, idCommande,prix, quantite, image) values(?, ?, ?, ?);";
//            try {
//                PreparedStatement pst = cnx.prepareStatement(req);
//                pst.setInt(1, productId);
//                pst.setInt(2, userId);
//                pst.setInt(3, 1);
//                pst.executeUpdate();
//                System.out.println("produit ajoutée au panier!");
//            } catch (SQLException ex) {
//            }
//        } else {
//            String req = "update panier set quantite = ? where client_id = ? and produit_id = ?";
//            try {
//                PreparedStatement pst = cnx.prepareStatement(req);
//                pst.setInt(1, getQtOfProduct(userId, productId) + 1);
//                pst.setInt(2, userId);
//                pst.setInt(3, productId);
//                pst.executeUpdate();
//                System.out.println("qt mise up");
//            } catch (SQLException ex) {
//            }
//        }
//
//    }
//
//    public boolean testDuplication(int userId, int produitId) throws SQLException {
//        String req = "select * from panier where client_id = ? and produit_id = ?";
//        PreparedStatement pst = cnx.prepareStatement(req);
//        pst.setInt(1, userId);
//        pst.setInt(2, produitId);
//        ResultSet rs = pst.executeQuery();
//        System.out.println("############");
//        if (rs.next()) {
//            return false;
//        }
//        return true;
//    }
//
//    public int getQtOfProduct(int userId, int produitId) throws SQLException {
//        int qt = 0;
//        String req = "select quantite from panier where client_id = ? and produit_id = ?";
//        PreparedStatement pst = cnx.prepareStatement(req);
//        pst.setInt(1, userId);
//        pst.setInt(2, produitId);
//        ResultSet rs = pst.executeQuery();
//        while (rs.next()) {
//            qt = rs.getInt(1);
//
//        }
//        return qt;
//    }
//
//    public ObservableList<Panier> getUserBasket(int userId) {
//
//        ObservableList<Panier> list = FXCollections.observableArrayList();
//
//        String req = "select pa.id, pa.produit_id, pa.client_id, pa.quantite, p.nom from panier as pa join produit as p on pa.produit_id = p.id WHERE pa.client_id = ?";
//
//        try {
//            PreparedStatement pst = cnx.prepareStatement(req);
//            pst.setInt(1, userId);
//            ResultSet rs = pst.executeQuery();
//            while (rs.next()) {
//                Panier p = new Panier(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4));
//                p.setNomProduit(rs.getString(5));
//                list.add(p);
//            }
//        } catch (SQLException ex) {
//        }
//        System.out.println("liste panier recupere");
//        return list;
//    }
//    
//    public List<Panier> getUserBasket2(int userId) {
//
//        List<Panier> list = new ArrayList<>();
//
//        String req = "select * from panier where client_id = ?";
//
//        try {
//            PreparedStatement pst = cnx.prepareStatement(req);
//            pst.setInt(1, userId);
//            ResultSet rs = pst.executeQuery();
//            while (rs.next()) {
//                Panier p = new Panier(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4));
//                list.add(p);
//            }
//        } catch (SQLException ex) {
//        }
//        System.out.println("liste panier recupere");
//        return list;
//    }
//
//    public void removeProductFromBasket(int basketId) {
//        String req = "delete from panier where id = ?;";
//        try {
//            PreparedStatement pst = cnx.prepareStatement(req);
//            pst.setInt(1, basketId);
//            pst.executeUpdate();
//            System.out.println("produit supprimée du panier!");
//        } catch (SQLException ex) {
//        }
//    }
//
//    public void incQt(int basketId) {
//        System.out.println("inc qt");
//        String req = "update panier set quantite = ? where id = ?;";
//        try {
//            System.out.println("0");
//            PreparedStatement pst = cnx.prepareStatement(req);
//            System.out.println("1");
//            pst.setInt(1, getQtOfProduct2(basketId) + 1);
//            pst.setInt(2, basketId);
//            System.out.println("2");
//            pst.executeUpdate();
//            System.out.println("3");
//            System.out.println("qt ++");
//            System.out.println("4");
//        } catch (SQLException ex) {
//        }
//    }
//
//    public void decQt(int basketId) throws SQLException {
//
//        if (getQtOfProduct2(basketId) == 1) {
//            removeProductFromBasket(basketId);
//        } else {
//            String req = "update panier set quantite = ? where id = ?;";
//            try {
//                System.out.println("0");
//                PreparedStatement pst = cnx.prepareStatement(req);
//                System.out.println("1");
//                pst.setInt(1, getQtOfProduct2(basketId) - 1);
//                pst.setInt(2, basketId);
//                System.out.println("2");
//                pst.executeUpdate();
//                System.out.println("3");
//                System.out.println("qt ++");
//                System.out.println("4");
//            } catch (SQLException ex) {
//            }
//        }
//
//    }
//
//    public int getQtOfProduct2(int basketId) throws SQLException {
//        int qt = 0;
//        System.out.println("00");
//        String req = "select quantite from panier where id = ?";
//        System.out.println("11");
//        PreparedStatement pst = cnx.prepareStatement(req);
//        System.out.println("22");
//        pst.setInt(1, basketId);
//        System.out.println("33:" + basketId);
//        ResultSet rs = pst.executeQuery();
//        System.out.println("44");
//        while (rs.next()) {
//            System.out.println("55");
//            qt = rs.getInt(1);
//        }
//        System.out.println("66");
//        return qt;
//    }
//}
