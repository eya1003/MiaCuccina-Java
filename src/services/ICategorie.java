/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Categorie;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author ACER
 */
public interface ICategorie{ 
public interface ICategories<t> {
        public void ajouter(Categorie c)throws SQLException;
        public void Update(Categorie c , int id)throws SQLException;
        public List<Categorie> afficherCategorie() throws SQLException;
    

}
}
