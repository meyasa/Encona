/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package koneksi;
import java.sql.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author ACER
 */
public class koneksi {
    public Connection koneksi;
    public Connection connect(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Berhasil koneksi");
        } catch (ClassNotFoundException ex) {
            System.out.println("Gagal koneksi "+ex);
        }
        String url = "jdbc:mysql://localhost/enconadb";
        try {
            koneksi = DriverManager.getConnection(url,"root","");
            System.out.println("Berhasil koneksi ke DB");
        } catch (SQLException ex) {
            System.out.println("Gagal koneksi ke DB"+ex);
        }
        return koneksi;
    }
    
    public PreparedStatement prepareStatement(String qlogin) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
