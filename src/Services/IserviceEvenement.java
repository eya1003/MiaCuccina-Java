/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Evenement;
import java.sql.SQLException;
import javafx.collections.ObservableList;

/**
 *
 * @author User
 */
public interface IserviceEvenement {
    public void AjouterEvenement(Evenement c);
    public ObservableList<Evenement>AfficherEvenement();
    public void supprimerevenement(int id);
    public void ModifierEvenement(Evenement c);
}