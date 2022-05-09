/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Artiste;
import java.sql.SQLException;
import javafx.collections.ObservableList;

/**
 *
 * @author User
 */
public interface IserviceArtiste {
    public void AjouterArtiste(Artiste c);
    public ObservableList<Artiste>AfficherArtiste();
    public void supprimerartiste(int id);
    public void ModifierArtiste(Artiste c);
}