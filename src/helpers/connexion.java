/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author wassim
 */
public class connexion {
     public static connexion instance;
	private Connection conx ;
	private final String USERNAME="root";
        private final String PASSWORD="";
        private final String URL="jdbc:mysql://localhost:3306/diligent";
	
	private connexion() {
		try {
                        
			conx = DriverManager.getConnection(URL,USERNAME,PASSWORD);
                        System.out.println("connected to pi!");
			
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
	}
        public static connexion getInstance(){
        if(instance==null)
            instance=new connexion();
            return instance;
        }
        public Connection getCnx(){
        return conx;
        }
    
}
