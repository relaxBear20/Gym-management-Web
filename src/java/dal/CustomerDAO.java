/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Client;
import model.Pack;
import model.Trainer;

/**
 *
 * @author admin
 */
public class CustomerDAO {

    private Connection getConnection() {
        try {
            String url = "jdbc:sqlserver://" + serverName + ":" + portNumber + ";databaseName=" + dbName;
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            return DriverManager.getConnection(url, userID, password);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
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

    public CustomerDAO() {
        this.con = getConnection();
    }

    public boolean addCustomer(String name, String address, String dob, String tel) {
        try {
            String sql = "INSERT INTO [dbo].[Client]\n"
                    + "           ([name]\n"
                    + "           ,[address]\n"
                    + "           ,[tel]\n"
                    + "           ,[Dob]\n"
                    + "           ,[createDate]\n"
                    + "           ,[isVip])\n"
                    + "     VALUES(\n"
                    + "           ?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,GETDATE()\n"
                    + "           ,0)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, address);
            String[] date = dob.split("-");
            ps.setDate(4, java.sql.Date.valueOf(dob));
            ps.setString(3, tel);
            int c = ps.executeUpdate();
            return c == 0 ? false : true;
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean deletePack(int cid, int pid) {
        try {
            String sql = "DELETE FROM [dbo].[Cli_pack]\n"
                    + "      WHERE tid = ? AND cid =?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, cid);
            ps.setInt(2, pid);
            int c = ps.executeUpdate();
            return c == 0 ? false : true;
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean addPack(int cid, int tid, int pid, int time) {
        try {
            String sql = "INSERT INTO [dbo].[Cli_pack]\n"
                    + "           ([cid]\n"
                    + "           ,[pid]\n"
                    + "           ,[tid]\n"
                    + "           ,[time]\n"
                    + "           ,[startDate])\n"
                    + "     VALUES\n"
                    + "           (?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,getdate())";
            PreparedStatement ps = con.prepareCall(sql);
            ps.setInt(1, cid);
            ps.setInt(2, pid);
            ps.setInt(3, tid);
            ps.setInt(4, time);

            int c = ps.executeUpdate();
            boolean checkVip = checkVIP();
            if (checkVIP()) {
                updateVip(cid);
            }
            boolean checkTrainer = checkTrainer(tid);
            if (checkTrainer) {
                updateTrainer(tid);
            }

            return c == 0 ? false : true;

        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean checkTrainer(int tid) {
        try {
            String sql = "SELECT SUM(t.id) as sum\n"
                    + "  FROM [dbo].[Cli_pack] cp\n"
                    + "  INNER JOIN Trainer t ON t.id = cp.tid\n"
                    + "  WHERE tid = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, tid);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int n = rs.getInt("sum");
                if (n >=5) {
                    return true;
                }
                return false;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public void updateTrainer(int tid) {
        try {
            String sql = "UPDATE [dbo].[Trainer]\n"
                    + "   SET \n"
                    + "      [isAvl] = 0\n"
                    + " WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, tid);
            ResultSet rs = ps.executeQuery();

        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateVip(int cid) {
        try {
            String sql = "UPDATE [dbo].[Client]\n"
                    + "   SET [isVip] = 1\n"
                    + " WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, cid);
            ResultSet rs = ps.executeQuery();

        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public boolean checkVIP() {
        try {
            String sql = "SELECT SUM(p.price) as sum\n"
                    + "  FROM [dbo].[Cli_pack] cp\n"
                    + "  INNER JOIN Pack p ON p.id = cp.pid";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int n = rs.getInt("sum");
                if (n > 2000) {
                    return true;
                }
                return false;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public ArrayList<Client> getClients(int id, String name, String tel) {
        try {
            Hashtable<Object, String> map = new Hashtable<Object, String>();
            Hashtable<Object, Integer> mapIndex = new Hashtable<Object, Integer>();
            String sql = "SELECT [id]\n"
                    + "      ,[name]\n"
                    + "      ,[address]\n"
                    + "      ,[tel]\n"
                    + "      ,[Dob]\n"
                    + "      ,[createDate]\n"
                    + "      ,[isVip]\n"
                    + "  FROM [dbo].[Client]\n"
                    + "  WHERE 1=1";
            int i = 1;
            if (id != 0) {
                map.put(id, "int");
                mapIndex.put(id, i++);
                sql += "AND id = ?";
            }
            if (name != null && !name.equals("")) {
                name = "%" + name + "%";
                map.put(name, "String");
                mapIndex.put(name, i++);
                sql += "AND name LIKE ?";
            }
            if (tel != null && !tel.equals("")) {
                map.put(tel, "String");
                mapIndex.put(tel, i++);
                sql += "AND tel = ?";
            }
            PreparedStatement ps = con.prepareCall(sql);

            map.forEach((t, u) -> {
                if (u.equals("int")) {
                    try {
                        ps.setInt(mapIndex.get(t), Integer.parseInt(t.toString()));
                    } catch (SQLException ex) {
                        Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else if (u.equals("String")) {
                    try {
                        ps.setString(mapIndex.get(t), t.toString());
                    } catch (SQLException ex) {
                        Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
            ResultSet rs = ps.executeQuery();
            ArrayList<Client> clients = new ArrayList<>();
            while (rs.next()) {
                Client c = new Client();
                c.setId(rs.getInt("id"));
                c.setName(rs.getString("name").trim());
                c.setDob(rs.getDate("Dob"));
                c.setAddress(rs.getString("address").trim());
                c.setTel(rs.getString("tel").trim());
                c.setIsVip(rs.getBoolean("isVip"));
                clients.add(c);
            }
            return clients;
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Client getClient(int cid) {
        try {
            ArrayList<Integer> count = new ArrayList<>();
            String sql = "SELECT c.[id]\n"
                    + "      ,c.[name]\n"
                    + "      ,[address]\n"
                    + "      ,[tel]\n"
                    + "      ,[Dob]\n"
                    + "      ,[createDate]\n"
                    + "      ,[isVip]\n"
                    + "	  , p.id as packId\n"
                    + "	   ,p.name as packName\n"
                    + "	   ,t.name as tname\n"
                    + "	   ,t.isAvl\n"
                    + "	   ,t.id as tid\n"
                    + "	   FRom CLient c \n"
                    + "	   INNER JOIN  Cli_Pack cp ON c.id = cp.cid\n"
                    + "	   INNER JOIN   Pack p ON p.id = cp.pid\n"
                    + "	   INNER JOIN Trainer t ON cp.tid = t.id\n"
                    + "	   WHERE c.id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, cid);
            ResultSet rs = ps.executeQuery();

            Client c = getClients(cid, "", "").get(0);
            if (!rs.next()) {
                return c;
            }
            c.setId(rs.getInt("id"));
            c.setName(rs.getString("name").trim());
            c.setDob(rs.getDate("Dob"));
            c.setAddress(rs.getString("address").trim());
            c.setTel(rs.getString("tel").trim());
            c.setIsVip(rs.getBoolean("isVip"));
            c.setCreateDate(rs.getDate("createDate"));
            Pack p = new Pack();
            Trainer t = new Trainer();
            t.setName(rs.getString("tname").trim());
            t.setId(rs.getInt("tid"));

            p.setId(rs.getInt("packId"));
            p.setName(rs.getString("packName").trim());

            c.getPacks().add(p);

            c.getTrainers().add(t);
            while (rs.next()) {
                Pack pc = new Pack();
                Trainer tc = new Trainer();
                tc.setName(rs.getString("tname").trim());
                tc.setId(rs.getInt("tid"));

                pc.setId(rs.getInt("packId"));
                pc.setName(rs.getString("packName").trim());
                c.getPacks().add(pc);
                c.getTrainers().add(tc);
            }
            return c;
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static void main(String[] args) {

        ArrayList<Client> c = new CustomerDAO().getAllClients();
        for (Client client : c) {
            System.out.println(client.isIsVip());
        }
    }

    public ArrayList<Client> getAllClients() {
        try {
            ArrayList<Client> clients = new ArrayList<>();

            String sql = "SELECT c.[id] as cid\n"
                    + "      ,c.[name]\n"
                    + "      ,[address]\n"
                    + "      ,[tel]\n"
                    + "      ,[Dob]\n"
                    + "      ,[createDate]\n"
                    + "      ,[isVip]\n"
                    + "	  , p.id as packId\n"
                    + "	   ,p.name as packName\n"
                    + "	   FRom CLient c \n"
                    + "	   INNER JOIN  Cli_Pack cp ON c.id = cp.cid\n"
                    + "	   INNER JOIN   Pack p ON p.id = cp.pid";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                boolean existed = false;
                int id = rs.getInt("cid");

                Client c = new Client();
                for (Client client : clients) {
                    if (client.getId() == id) {

                        existed = true;
                        c = client;
                        break;
                    }
                }
                if (existed) {
                    Pack p = new Pack();
                    Trainer t = new Trainer();
                    p.setId(rs.getInt("packId"));
                    p.setName(rs.getString("packName").trim());
                    c.getPacks().add(p);
                    c.getTrainers().add(t);

                } else {
                    c.setId(id);
                    c.setName(rs.getString("name").trim());
                    c.setDob(rs.getDate("Dob"));
                    c.setAddress(rs.getString("address").trim());
                    c.setTel(rs.getString("tel").trim());
                    c.setIsVip(rs.getBoolean("isVip"));
                    Pack p = new Pack();
                    Trainer t = new Trainer();
                    p.setId(rs.getInt("packId"));
                    p.setName(rs.getString("packName").trim());

                    c.getPacks().add(p);

                    c.getTrainers().add(t);
                    clients.add(c);
                }
            }
            return clients;
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
