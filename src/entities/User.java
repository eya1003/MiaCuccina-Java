/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;



/**
 *
 * @author Asus
 */
public class User {
  int  id;
  String  username ;
  
  String   localisation;
  String  images;
  String    numero;
  String   emailadresse;
  
  
  String  role ;

  String  password ;

  

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", username=" + username + ",localisation=" + localisation + ", images=" + images + ", numero=" + numero + ", emailadresse=" + emailadresse + ", role=" + role + ", password=" + password + '}';
    }
  

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    

    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getEmailadresse() {
        return emailadresse;
    }

    public void setEmailadresse(String emailadresse) {
        this.emailadresse = emailadresse;
    }

   

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
  
 
  public User( int  id,String  username  ,String   localisation,
  String  images,String numero, String   emailadresse, String  role ,
  String  password)  
 { 
   this.id=id;
 this.username=username ;
 
  this.localisation=localisation;
  this.images=images;
  this.numero=numero;
  this.emailadresse=emailadresse;
  
  this.role=role ;
  
  this.password=password ;
    
 }
 
  public void setId(int id) {
        this.id = id;
    }

public int getId() {
        return id;
    }
}
