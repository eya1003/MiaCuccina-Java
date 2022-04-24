/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author Zeineb Hamdi
 */
public class Categorie {
    int id_categorie ;
   String nom ;
   String description_cat ;

    public Categorie(int id_categorie, String nom, String description_cat) {
        this.id_categorie = id_categorie;
        this.nom = nom;
        this.description_cat = description_cat;
    }
    public Categorie( String nom, String description_cat) {
        this.nom = nom;
        this.description_cat = description_cat;}

    public Categorie() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public int getId_categorie() {
        return id_categorie;
    }

    public void setId_categorie(int id_categorie) {
        this.id_categorie = id_categorie;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription_cat() {
        return description_cat;
    }

    public void setDescription_cat(String description_cat) {
        this.description_cat = description_cat;
    }
    public int hashCode() {
        int hash = 7;
        return hash;
    }
}