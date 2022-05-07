/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Livraison;
import entities.Livreur;
import java.sql.SQLException;
import java.util.List;
import javafx.collections.ObservableList;


/**
 *
 * @author hanna
 */
public interface ILivraison <A>{
     public void ajouterLivraison(Livraison liv) throws SQLException;
    public List<Livraison> afficherlivraison() throws SQLException;
    public void deleteLivraison(int id_livraison) throws SQLException;
    public void modifierLiv(Livraison liv,int id_livraison) throws SQLException;
    public ObservableList<Livraison> getAll() throws SQLException;
    
    
}
