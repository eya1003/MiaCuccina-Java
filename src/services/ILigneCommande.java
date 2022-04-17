package services;

import entities.Commande;
import entities.LigneCommande;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.List;

/**s
 * @author Mintoua
 * @param <LigneCommande>
 */
public interface ILigneCommande<LigneCommande> {

    void add(entities.LigneCommande panier) throws SQLException;
    boolean delete(int idPanier) throws SQLException;
    boolean update(entities.LigneCommande panier) throws SQLException;
   public List<LigneCommande> readAll() throws SQLException;
    public ObservableList<LigneCommande> getPanier(int idC) ;
}
