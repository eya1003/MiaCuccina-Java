/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Cart;
import entities.Commande;
import java.sql.SQLException;
import java.util.List;
import javafx.collections.ObservableList;


/**
 *
 * @author Zeineb Hamdi
 * @param <T>
 */
public interface ICommande<T> {
       void create (Commande commande) ;
    boolean delete(int idCommande) throws  SQLException;
    boolean update(int idCommande) throws  SQLException;
    public List<Commande> readAll() throws SQLException;
    public ObservableList<Commande> getAll() throws SQLException;
    int getLastCommande();
    public Commande getCommande(int idCommande) throws SQLException;
//    public void historique(int idCommande, ObservableList<Cart> panier) throws SQLException, DocumentException, FileNotFoundException;
//    public int[] statistiques() throws SQLException;
//    public ObservableList<PieChart.Data> getdata();
    int orders();
    
    
}
