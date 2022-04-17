/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Date;

/**
 *
 * @author Zeineb Hamdi
 */
public class Commande {
    
    private int id_com;
    private int id_user;
    private int etatcommande;
    private String datecommande;
    private float prixtotal;

    public Commande(int id_com, int id_user, int etatcommande, String datecommande, float prixtotal) {
        this.id_com = id_com;
        this.id_user = id_user;
        this.etatcommande = etatcommande;
        this.datecommande = datecommande;
        this.prixtotal = prixtotal;
    }

    public Commande() {
    }

    public Commande(int id_com,int etatcommande, String datecommande, float prixtotal) {
        this.id_com = id_com;
        this.etatcommande = etatcommande;
        this.datecommande = datecommande;
        this.prixtotal = prixtotal;
    }

    public Commande(int etatcommande, String datecommande, float prixtotal) {
        this.etatcommande = etatcommande;
        this.datecommande = datecommande;
        this.prixtotal = prixtotal;
    }

    public Commande(int i, String dateCommande, int Total, int i0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getId_com() {
        return id_com;
    }

    public void setId_com(int id_com) {
        this.id_com = id_com;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getEtatcommande() {
        return etatcommande;
    }

    public void setEtatcommande(int etatcommande) {
        this.etatcommande = etatcommande;
    }

    public String getDatecommande() {
        return datecommande;
    }

    public void setDatecommande(String datecommande) {
        this.datecommande = datecommande;
    }



  
    public float getPrixtotal() {
        return prixtotal;
    }

    public void setPrixtotal(float prixtotal) {
        this.prixtotal = prixtotal;
    }

    @Override
    public String toString() {
        return "Commande{" + "id_com=" + id_com + ", id_user=" + id_user + ", etatcommande=" + etatcommande + ", datecommande=" + datecommande + ", prixtotal=" + prixtotal + '}';
    }
    
     
    
    
}
