/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Date;


/**
 *
 * @author aziza
 */
public class Evenement {

    private int id;
    private Date date_event;
    private String nom_event;
    private String type_event;
    private String Description_event;

    public Evenement() {
    }

    public Evenement(int id, Date date_event, String nom_event, String type_event, String Description_event) {
        this.id = id;
        this.date_event = date_event;
        this.nom_event = nom_event;
        this.type_event = type_event;
        this.Description_event = Description_event;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate_event() {
        return date_event;
    }

    public void setDate_event(Date date_event) {
        this.date_event = date_event;
    }

    public String getNom_event() {
        return nom_event;
    }

    public void setNom_event(String nom_event) {
        this.nom_event = nom_event;
    }

    public String getType_event() {
        return type_event;
    }

    public void setType_event(String type_event) {
        this.type_event = type_event;
    }

    public String getDescription_event() {
        return Description_event;
    }

    public void setDescription_event(String Description_event) {
        this.Description_event = Description_event;
    }

    

}
