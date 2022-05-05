/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author aziza
 */
public class Artiste {
    private int id_artiste;
    private String nom_artiste;
    private String email_artiste;
    private int num_artiste;
    private String type_artiste;

    public Artiste() {
    }

    public Artiste(int id_artiste, String nom_artiste, String email_artiste, int num_artiste, String type_artiste) {
        this.id_artiste = id_artiste;
        this.nom_artiste = nom_artiste;
        this.email_artiste = email_artiste;
        this.num_artiste = num_artiste;
        this.type_artiste = type_artiste;
    }

    public int getId_artiste() {
        return id_artiste;
    }

    public void setId_artiste(int id_artiste) {
        this.id_artiste = id_artiste;
    }

    public String getNom_artiste() {
        return nom_artiste;
    }

    public void setNom_artiste(String nom_artiste) {
        this.nom_artiste = nom_artiste;
    }

    public String getEmail_artiste() {
        return email_artiste;
    }

    public void setEmail_artiste(String email_artiste) {
        this.email_artiste = email_artiste;
    }

    public int getNum_artiste() {
        return num_artiste;
    }

    public void setNum_artiste(int num_artiste) {
        this.num_artiste = num_artiste;
    }

    public String getType_artiste() {
        return type_artiste;
    }

    public void setType_artiste(String type_artiste) {
        this.type_artiste = type_artiste;
    }
    
    
    
    
}
