/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Livreur;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.ObservableList;

/**
 *
 * @author hanna
 */
public interface ILivreur<L>  {
    public void ajouterL(L l) throws SQLException;
    public List<L> afficherlivreur() throws SQLException;
    public void deleteLivreur(int id_livreur) throws SQLException;
    public void modifierL(L l,int id_livreur) throws SQLException;
    public ObservableList<Livreur> getAll() throws SQLException;
    public ArrayList<Livreur> TrierParId();
    public List<Livreur> RechercherLivreurNom(String x);
    public List<Livreur>RechercherLivreurPrenom(String x);
    public List<Livreur>RechercherLivreurNumero(String x);
    
}
