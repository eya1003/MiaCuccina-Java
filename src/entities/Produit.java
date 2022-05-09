/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Date;
import java.util.Objects;
import javafx.collections.ObservableList;

/**
 *
 * @author Zeineb Hamdi
 */
public class Produit {
    
    int id_produit;
   int id_categorie ;
   String nom ;
   double quantite_produit ;
   float prix_produit ; 
   String  description_produit ;
   String photo_pro;
   int like_dislike;  
   String nom_cat;

 public Produit(int id_produit, int id_categorie, String nom, Double quantite_produit, float prix_produit, String description_produit, String photo_pro, int like_dislike) {
        this.id_produit = id_produit;
        this.id_categorie = id_categorie;
        this.nom = nom;
        this.quantite_produit = quantite_produit;
        this.prix_produit = prix_produit;
        this.description_produit = description_produit;
        this.photo_pro = photo_pro;
        this.like_dislike = like_dislike;
    }
      public Produit() {
       
    } 

    public Produit(int id_produit, String nom, double quantite_produit, float prix_produit, String description_produit, String photo_pro) {
        this.id_produit = id_produit;
        this.nom = nom;
        this.quantite_produit = quantite_produit;
        this.prix_produit = prix_produit;
        this.description_produit = description_produit;
        this.photo_pro = photo_pro;
    }
      

    public Produit(String nom, float prix_produit, String description_produit, String photo_pro, String nom_cat) {
        this.nom = nom;
        this.prix_produit = prix_produit;
        this.description_produit = description_produit;
        this.photo_pro = photo_pro;
        this.nom_cat = nom_cat;
    }
      

    public Produit(int id_categorie, String nom, float prix_produit,String description_produit , Double quantite_produit, String photo_pro) {
        this.id_categorie = id_categorie;
        this.nom = nom; 
        this.prix_produit = prix_produit;
        this.quantite_produit = quantite_produit;
        this.prix_produit = prix_produit;
        this.description_produit = description_produit;
        this.photo_pro = photo_pro;
    }
      
      

    public Produit(String nom) {
        this.nom = nom;
    }

    public String getNom_cat() {    
        return nom_cat;
    }

//    public Produit(int id_produit, String nom, double quantite_produit, float prix_produit, String description_produit, String photo_pro, int like_dislike) {
//        this.id_produit = id_produit;
//        this.nom = nom;
//        this.quantite_produit = quantite_produit;
//        this.prix_produit = prix_produit;
//        this.description_produit = description_produit;
//        this.photo_pro = photo_pro;
//        this.like_dislike = like_dislike;
//    }
//      
    public void setNom_cat(String nom_cat) {
        this.nom_cat = nom_cat;
    }

    public Produit(String nom, float prix_produit, String description_produit, double quantite_produit, String photo_pro) {
        this.nom = nom;
        this.prix_produit = prix_produit;
        this.description_produit = description_produit;
        this.quantite_produit = quantite_produit;
        this.photo_pro=photo_pro;
    }

    public Produit(String nom, float prix_produit, String description_produit) {
        this.nom = nom;
        this.prix_produit = prix_produit;
        this.description_produit = description_produit;
    }

    public Produit(String nom, float prix_produit, String description_produit, String photo_pro) {
        this.nom = nom;
        this.prix_produit = prix_produit;
        this.description_produit = description_produit;
        this.photo_pro = photo_pro;
    }

    
   

  

    public void setId_produit(int id_produit) {
        this.id_produit = id_produit;
    }

    public void setId_categorie(int id_categorie) {
        this.id_categorie = id_categorie;
    } 
    

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setQuantite_produit(Integer quantite_produit) {
        this.quantite_produit = quantite_produit;
    }

    public void setPrix_produit(float prix_produit) {
        this.prix_produit = prix_produit;
    }

    public void setDescription_produit(String description_produit) {
        this.description_produit = description_produit;
    }

    public void setPhoto_pro(String photo_pro) {
        this.photo_pro = photo_pro;
    }

    public void setLike_dislike(int like_dislike) {
        this.like_dislike = like_dislike;
    }

    public int getId_produit() {
        return id_produit;
    }

    public int getId_categorie() {
        return id_categorie;
    }

    public String getNom() {
        return nom;
    }

    public double getQuantite_produit() {
        return quantite_produit;
    }

    public float getPrix_produit() {
        return prix_produit;
    }

    public String getDescription_produit() {
        return description_produit;
    }

    public String getPhoto_pro() {
        return photo_pro;
    }

    public int getLike_dislike() {
        return like_dislike;
    }

    @Override
    public String toString() {
        return "Produit{" + "id_produit=" + id_produit + ", id_categorie=" + id_categorie + ", nom=" + nom + ", quantite_produit=" + quantite_produit + ", prix_produit=" + prix_produit + ", description_produit=" + description_produit + ", photo_pro=" + photo_pro + ", like_dislike=" + like_dislike + '}';
    }

    public ObservableList<Produit> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    

    

   
   
   
}

