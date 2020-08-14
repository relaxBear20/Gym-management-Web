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
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Pack;

/**
 *
 * @author admin
 */
public class PackDAO {

    private Connection getConnection() {
        try {
            String url = "jdbc:sqlserver://" + serverName + ":" + portNumber + ";databaseName=" + dbName;
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            return DriverManager.getConnection(url, userID, password);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(PackDAO.class.getName()).log(Level.SEVERE, null, ex);
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

    public PackDAO() {
        this.con = getConnection();
    }

    public boolean delete(int id) {
        try {
            String sql = "DELETE FROM [dbo].[Pack]\n"
                    + "      WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            int c = ps.executeUpdate();
            return c ==0?false:true;
        } catch (SQLException ex) {
            Logger.getLogger(PackDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false ;
    }

    public boolean addPacks(String name, int price, int sale) {
        try {
            ArrayList<Pack> packs = new ArrayList<>();
            String sql = "INSERT INTO [dbo].[Pack]\n"
                    + "           ([name]\n"
                    + "           ,[price]\n"
                    + "           ,[sale])\n"
                    + "     VALUES\n"
                    + "           (?\n"
                    + "           ,?\n"
                    + "           ,?)";
            PreparedStatement ps = con.prepareCall(sql);
            ps.setString(1, name);
            ps.setInt(2, price);
            ps.setInt(3, sale);
            int c = ps.executeUpdate();
            return c == 0 ? false : true;

        } catch (SQLException ex) {
            Logger.getLogger(PackDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public ArrayList<Pack> getAllPacks() {
        try {
            ArrayList<Pack> packs = new ArrayList<>();
            String sql = "SELECT [id]\n"
                    + "      ,[name]\n"
                    + "      ,[price]\n"
                    + "      ,[sale]\n"
                    + "  FROM [dbo].[Pack]";
            PreparedStatement ps = con.prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Pack p = new Pack();
                p.setId(rs.getInt("id"));
                p.setName(rs.getString("name").trim());
                p.setPrice(rs.getInt("price"));
                p.setSale(rs.getInt("sale"));
                packs.add(p);
            }
            return packs;

        } catch (SQLException ex) {
            Logger.getLogger(PackDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

    public static void main(String[] args) {
        ArrayList<Pack> packs = new PackDAO().getAllPacks();
        for (Pack pack : packs) {
            System.out.println(pack.getPrice());
        }
    }
}
