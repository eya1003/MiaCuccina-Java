/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Table;
import java.sql.SQLException;

/**
 *
 * @author eyaam
 */
public interface ITable<T> {
     public void ajouterTable(T t) throws SQLException;
    
    public void deleteTable(int id) throws SQLException ;
     public void modifierTab(T t, int id) throws SQLException;
}
