/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author hanna
 */
public class Livraison {

    public Livraison(String description_livraison, int etat_livraison, String Adresse_livraison) {
        this.description_livraison = description_livraison;
        this.etat_livraison = etat_livraison;
        this.Adresse_livraison = Adresse_livraison;
    }
 private int id_livraison;
 private String description_livraison;
 private  int etat_livraison;
 private int id_livreur;
 private String Adresse_livraison;


    public Livraison(String description_livraison, int etat_livraison, int id_livreur, String Adresse_livraison) {
        this.description_livraison = description_livraison;
        this.etat_livraison = etat_livraison;
        this.id_livreur = id_livreur;
        this.Adresse_livraison = Adresse_livraison;
    }

    public Livraison(int id_livraison, String description_livraison,int etat_livraison, int id_livreur, String Adresse_livraison ) {
        this.id_livraison = id_livraison;
        this.description_livraison = description_livraison;
        this.etat_livraison = etat_livraison;  
        this.id_livreur = id_livreur;
        this.Adresse_livraison = Adresse_livraison;
        
        
    }

    public Livraison(String Adresse_livraison, int id_livreur, String description_livraison, int etat_livraison) {
        this.description_livraison = description_livraison;
        this.etat_livraison = etat_livraison;
        this.id_livreur = id_livreur;
        this.Adresse_livraison = Adresse_livraison;
        
        
        
    }

    public Livraison(String Adresse_livraison, String description_livraison) {
        this.Adresse_livraison = Adresse_livraison;
        this.description_livraison = description_livraison;
    }

 
    public Livraison(String Adresse_livraison, String description_livraison, int etat_livraison) {
        this.Adresse_livraison = Adresse_livraison;
        this.description_livraison = description_livraison;
        this.etat_livraison = etat_livraison;
    }
    public Livraison(int id_livraison, String Adresse_livraison, String description_livraison, int etat_livraison) {
        this.id_livraison = id_livraison;
        this.Adresse_livraison = Adresse_livraison;
        this.description_livraison = description_livraison;
        this.etat_livraison = etat_livraison;
    }

    public Livraison() {
    }

    public int getId_livraison() {
        return id_livraison;
    }

    public void setId_livraison(int id_livraison) {
        this.id_livraison = id_livraison;
    }

    public String getAdresse_livraison() {
        return Adresse_livraison;
    }

    public void setAdresse_livraison(String Adresse_livraison) {
        this.Adresse_livraison = Adresse_livraison;
    }

    public int getId_livreur() {
        return id_livreur;
    }

    public void setId_livreur(int id_livreur) {
        this.id_livreur = id_livreur;
    }

    public String getDescription_livraison() {
        return description_livraison;
    }

    public void setDescription_livraison(String description_livraison) {
        this.description_livraison = description_livraison;
    }

    public int getEtat_livraison() {
        return etat_livraison;
    }

    public void setEtat_livraison(int etat_livraison) {
        this.etat_livraison = etat_livraison;
    }

 @Override
    public String toString() {
        return "Livraison{" + "id_livraison=" + id_livraison + ", Adresse_livraison=" + Adresse_livraison + ", description_livraison=" + description_livraison + ", etat_livraison=" + etat_livraison + '}';
    }
}
