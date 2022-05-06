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
public class Livreur {
    private int id_livreur;

    public Livreur() {
    }
    private String nom_liv,prenom_liv,num_tel_liv,Region,mat_liv,disponibilite_liv;


    @Override
    public String toString() {
        return "Livreur{" + "id_livreur=" + id_livreur + ", nom_liv=" + nom_liv + ", prenom_liv=" + prenom_liv + ", num_tel_liv=" + num_tel_liv + ", Region=" + Region + ", mat_liv=" + mat_liv + ", disponibilite_liv=" + disponibilite_liv + '}';
    }

    public int getId_livreur() {
        return id_livreur;
    }

    public void setId_livreur(int id_livreur) {
        this.id_livreur = id_livreur;
    }

    public String getNom_liv() {
        return nom_liv;
    }

    public void setNom_liv(String nom_liv) {
        this.nom_liv = nom_liv;
    }

    public String getPrenom_liv() {
        return prenom_liv;
    }

    public void setPrenom_liv(String prenom_liv) {
        this.prenom_liv = prenom_liv;
    }

    public String getNum_tel_liv() {
        return num_tel_liv;
    }

    public void setNum_tel_liv(String num_tel_liv) {
        this.num_tel_liv = num_tel_liv;
    }

    public String getRegion() {
        return Region;
    }

    public void setRegion(String Region) {
        this.Region = Region;
    }

    public String getMat_liv() {
        return mat_liv;
    }

    public void setMat_liv(String mat_liv) {
        this.mat_liv = mat_liv;
    }

    public String getDisponibilite_liv() {
        return disponibilite_liv;
    }

    public void setDisponibilite_liv(String disponibilite_liv) {
        this.disponibilite_liv = disponibilite_liv;
    }
    

    public Livreur(int id_livreur, String nom_liv, String prenom_liv, String num_tel_liv, String Region, String mat_liv, String disponibilite_liv) {
        this.id_livreur = id_livreur;
        this.nom_liv = nom_liv;
        this.prenom_liv = prenom_liv;
        this.num_tel_liv = num_tel_liv;
        this.Region = Region;
        this.mat_liv = mat_liv;
        this.disponibilite_liv = disponibilite_liv;
    }
    

    public Livreur(String nom_liv, String prenom_liv, String num_tel_liv, String Region, String mat_liv, String disponibilite_liv) {
        this.nom_liv = nom_liv;
        this.prenom_liv = prenom_liv;
        this.num_tel_liv = num_tel_liv;
        this.Region = Region;
        this.mat_liv = mat_liv;
        this.disponibilite_liv = disponibilite_liv;
    }
   
    
    
}
