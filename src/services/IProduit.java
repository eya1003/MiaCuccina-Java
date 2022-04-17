/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Produit;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author NADA_USER
 */
public interface IProduit <Produit> {
    
    
              public void ajouter(Produit P);
                         // public void ajouter1(Individu I)throws SQLException;

        public void Update(Produit p, int id) throws SQLException;
        public boolean delete(int id_produit);
        public List<Produit> afficherProduit() ;
}

