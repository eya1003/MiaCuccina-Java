/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Artiste;
import services.IserviceArtiste;
import utils.Maconnexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.Date;

import java.util.Optional;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

/**
 *
 * @author User
 */
public class ServiceArtiste implements IserviceArtiste {

    Connection cnx;

    public ServiceArtiste() {
        cnx = Maconnexion.getInstance().getConnection();
    }

    @Override
    public void AjouterArtiste(Artiste c) {
        try {
            Statement stm = cnx.createStatement();

            String query = "INSERT INTO artiste(nom_artiste,email_artiste,num_artiste,type_artiste) VALUES ('" + c.getNom_artiste() + "','" + c.getEmail_artiste() + "','" + c.getNum_artiste() + "','" + c.getType_artiste() + "')";
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Dialog");
            alert.setHeaderText("Confirmation ");
            alert.setContentText("Etes vous sur de vouloir ajouter cet artiste ?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                stm.executeUpdate(query);
                Alert alert2 = new Alert(AlertType.INFORMATION);
                alert2.setTitle("Ajout");
                alert2.setHeaderText("Artiste ajouté");
                alert2.setContentText("L'artiste a été ajouté avec success!");
                alert2.showAndWait();
            }

        } catch (SQLException ex) {
            Logger.getLogger(ServiceArtiste.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public ObservableList<Artiste> AfficherArtiste() {
        ObservableList<Artiste> artistes = FXCollections.observableArrayList();
        try {
            Statement stm;

            stm = cnx.createStatement();

            String query = "SELECT * from `artiste`";
            ResultSet rst = stm.executeQuery(query);

            while (rst.next()) {
                Artiste c = new Artiste();
                c.setId_artiste(rst.getInt("id_artiste"));
                c.setNom_artiste(rst.getString("nom_artiste"));
                c.setEmail_artiste(rst.getString("email_artiste"));
                c.setNum_artiste(rst.getInt("num_artiste"));
                c.setNom_artiste(rst.getString("type_artiste"));
                artistes.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceArtiste.class.getName()).log(Level.SEVERE, null, ex);
        }

        return artistes;
    }

    @Override
    public void supprimerartiste(int id) {
        try {
            Statement stm = cnx.createStatement();

            String query = " Delete FROM artiste where id_artiste='" + id + "'";
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Dialog");
            alert.setHeaderText("Confirmation ");
            alert.setContentText("Etes vous sur de vouloir supprimer cet artiste ?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                stm.executeUpdate(query);
                Alert alert2 = new Alert(AlertType.INFORMATION);
                alert2.setTitle("Suppression");
                alert2.setHeaderText("Artiste Supprimé");
                alert2.setContentText("L'artiste a été supprimé avec success!");
                alert2.showAndWait();
            }

        } catch (SQLException ex) {
            Logger.getLogger(ServiceArtiste.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void ModifierArtiste(Artiste c) {
        try {

            PreparedStatement ps;

            ps = cnx.prepareStatement("UPDATE  artiste set `nom_artiste`=?,`email_artiste`=?,`num_artiste`=?,`type_artiste`=? where id_artiste=" + c.getId_artiste());
            ps.setString(1, c.getNom_artiste());
            ps.setString(2, c.getEmail_artiste());
            ps.setInt(3, c.getNum_artiste());
            ps.setString(4, c.getType_artiste());
            ps.executeUpdate();
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Update");
            alert.setHeaderText("Artiste Modifié");
            alert.setContentText("L'Artiste a été modifié avec success!");
            alert.showAndWait();

            alert.showAndWait();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceArtiste.class.getName()).log(Level.SEVERE, null, ex);
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Ajout");
            alert.setHeaderText("Artiste Ajouté");
            alert.setContentText("L'Artiste a été Ajouté avec success!");
            alert.showAndWait();
        }
    }

    public ObservableList<Artiste> search(String input) {
        ObservableList<Artiste> artistes = FXCollections.observableArrayList();
        try {
            Statement stm;
            stm = cnx.createStatement();

            String query = "SELECT * from artiste where type_artiste like '%" + input + "%'";
            ResultSet rst = stm.executeQuery(query);
            Artiste form;
            while (rst.next()) {
                Artiste c = new Artiste();
                c.setId_artiste(rst.getInt("id_artiste"));
                c.setNom_artiste(rst.getString("nom_artiste"));
                c.setEmail_artiste(rst.getString("email_artiste"));
                c.setNum_artiste(rst.getInt("num_artiste"));
                c.setType_artiste(rst.getString("type_artiste"));
                form = new Artiste(rst.getInt("id_artiste"), rst.getString("nom_artiste"), rst.getString("email_artiste"), rst.getInt("num_artiste"), rst.getString("type_artiste"));
                artistes.add(form);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ServiceArtiste.class.getName()).log(Level.SEVERE, null, ex);
        }

        return artistes;
    }

    public ObservableList<Artiste> triasc() {
        ObservableList<Artiste> artistes = FXCollections.observableArrayList();
        try {
            Statement stm = cnx.createStatement();
            String query = "SELECT * from artistes ORDER by type_artiste ASC";
            ResultSet rst = stm.executeQuery(query);
            Artiste form;
            while (rst.next()) {
                Artiste c = new Artiste();
                c.setId_artiste(rst.getInt("id_artiste"));
                c.setNom_artiste(rst.getString("nom_artiste"));
                c.setEmail_artiste(rst.getString("email_artiste"));
                c.setNum_artiste(rst.getInt("num_artiste"));
                c.setType_artiste(rst.getString("type_artiste"));
                form = new Artiste(rst.getInt("id_artiste"), rst.getString("nom_artiste"), rst.getString("email_artiste"), rst.getInt("num_artiste"), rst.getString("type_artiste"));
                artistes.add(form);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceArtiste.class.getName()).log(Level.SEVERE, null, ex);
        }

        return artistes;
    }

    public ObservableList<Artiste> triadsc() {
        ObservableList<Artiste> artistes = FXCollections.observableArrayList();
        try {
            Statement stm = cnx.createStatement();
            String query = "SELECT * from artiste ORDER by type_artiste DESC";
            ResultSet rst = stm.executeQuery(query);
            Artiste form;
            while (rst.next()) {
                Artiste c = new Artiste();
                c.setId_artiste(rst.getInt("id_artiste"));
                c.setNom_artiste(rst.getString("nom_artiste"));
                c.setEmail_artiste(rst.getString("email_artiste"));
                c.setNum_artiste(rst.getInt("num_artiste"));
                c.setType_artiste(rst.getString("type_artiste"));
                form = new Artiste(rst.getInt("id_artiste"), rst.getString("nom_artiste"), rst.getString("email_artiste"), rst.getInt("num_artiste"), rst.getString("type_artiste"));
                artistes.add(form);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceArtiste.class.getName()).log(Level.SEVERE, null, ex);
        }

        return artistes;
    }

}
