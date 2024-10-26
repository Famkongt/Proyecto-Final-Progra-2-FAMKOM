/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connectionDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Wiliam
 */
public class connectionDB {

    Connection conn = null;

    public static void main(String[] args) {
        try {
            // The newInstance() call is a work around for some
            // broken Java implementations

            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        } catch (Exception ex) {
            // handle the error
        }
    }

    public Connection Open() {
        try {
            conn
                    = DriverManager.getConnection("jdbc:mysql://autorack.proxy.rlwy.net:53294/dbPlayers?"
                            + "user=root&password=fPjFTsQklvnCdXJeqMrQlETomPVuamlg");

        } catch (SQLException ex) {

            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            conn = null;
        }
        return conn;
    }

}
