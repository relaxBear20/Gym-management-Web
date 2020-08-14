/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Staff;

/**
 *
 * @author admin
 */
public class StaffDAO {

    private Connection getConnection() {
        try {
            String url = "jdbc:sqlserver://" + serverName + ":" + portNumber + ";databaseName=" + dbName;
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            return DriverManager.getConnection(url, userID, password);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(StaffDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /*Insert your other code right after this comment*/
 /*Change/update information of your database connection, DO NOT change name of instance variables in this class*/
    private final String serverName = "localhost";
    private final String dbName = "WEB_ASS";
    private final String portNumber = "6102";
    private final String userID = "sa";
    private final String password = "longpro123";
    Connection con;

    public StaffDAO() {
        this.con = getConnection();
    }

    public Staff getStaff(String username, String password) {
        try {
            String sql = "SELECT [userName]\n"
                    + "      ,[password]\n"
                    + "  FROM [StaffAcc]\n"
                    + "  WHERE userName = ? AND password = ?";
            PreparedStatement ps = con.prepareCall(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            Staff s = new Staff();
            rs.next();
            s.setPassword(rs.getString("password"));
            s.setUsername("userName");
            if(s.getUsername()== null)return null;
            else return s;
        } catch (SQLException ex) {
            Logger.getLogger(StaffDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public static void main(String[] args) {
        Staff a = new StaffDAO().getStaff("long", "long");
        System.out.println(a.getPassword());
    }
}
