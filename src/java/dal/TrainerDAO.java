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
import model.Client;
import model.Pack;
import model.Trainer;
import model.Trainer_Clients;

/**
 *
 * @author admin
 */
public class TrainerDAO {

    private Connection getConnection() {
        try {
            String url = "jdbc:sqlserver://" + serverName + ":" + portNumber + ";databaseName=" + dbName;
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            return DriverManager.getConnection(url, userID, password);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(TrainerDAO.class.getName()).log(Level.SEVERE, null, ex);
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

    public TrainerDAO() {
        this.con = getConnection();
    }

    public boolean addTrainer(String name, String[] spec) {
        try {
            con.setAutoCommit(false);
            String sql = "INSERT INTO [dbo].[Trainer]\n"
                    + "           ([name]\n"
                    + "           ,[isAvl])\n"
                    + "     VALUES\n"
                    + "           (?\n"
                    + "           ,1)";
            PreparedStatement ps = con.prepareCall(sql);
            ps.setString(1, name);
            ps.executeUpdate();
            sql = "SELECT [id]\n"
                    + "     \n"
                    + "  FROM [dbo].[Trainer]\n"
                    + "  WHERE name = ?;";
            ps = con.prepareCall(sql);
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            rs.next();
            int tid = rs.getInt("id");

            for (String pid : spec) {
                sql = "INSERT INTO [dbo].[Trainer_Pack]\n"
                        + "           ([tid]\n"
                        + "           ,[pid])\n"
                        + "     VALUES"
                        + "           (?"
                        + "           ,?)";
                PreparedStatement psInsertSpec = con.prepareStatement(sql);
                psInsertSpec.setInt(1, tid);
                psInsertSpec.setInt(2, Integer.parseInt(pid));
                psInsertSpec.executeUpdate();
            }
            
            con.commit();
            return true;
        } catch (SQLException ex) {
            try {
                con.rollback();
                
            } catch (SQLException ex1) {
                Logger.getLogger(TrainerDAO.class.getName()).log(Level.SEVERE, null, ex1);
            }
            Logger.getLogger(TrainerDAO.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            try {
                con.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(TrainerDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    public ArrayList<Trainer> getAllTrainers() {
        try {
            ArrayList<Trainer> trainers = new ArrayList<>();
            String sql = "SELECT t.[id] as tid\n"
                    + "      ,t.[name] as tname\n"
                    + "      ,[isAvl]\n"
                    + "	  ,p.id as pid\n"
                    + "	  ,p.name as pname\n"
                    + "  FROM [dbo].[Trainer] t\n"
                    + "  INNER JOIN Trainer_Pack tp ON tp .tid = t.id\n"
                    + "  INNER JOIN Pack p ON p.id = tp .pid"
                    +"   WHERE t.isAvl = 1";
            PreparedStatement ps = con.prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Trainer t = new Trainer();
                boolean existed = false;
                int id = rs.getInt("tid");
                for (Trainer trainer : trainers) {
                    if (trainer.getId() == id) {
                        existed = true;
                        t = trainer;
                    }
                }
                if (existed) {
                    t.getSpecs().add(rs.getInt("pid") );
                } else {
                    t.getSpecs().add(rs.getInt("pid") );
                    t.setId(id);
                    t.setIsAval(rs.getBoolean("isAvl"));
                    t.setName(rs.getString("tname").trim());
                    trainers.add(t);
                }

            }
            return trainers;
        } catch (SQLException ex) {
            Logger.getLogger(TrainerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<Trainer_Clients> getListClient_Trainers() {
        try {

            String sql = "SELECT c.[id]\n"
                    + "      ,c.[name]\n"
                    + "      ,[address]\n"
                    + "      ,[tel]\n"
                    + "      ,[Dob]\n"
                    + "      ,[createDate]\n"
                    + "      ,[isVip]\n"
                    + "	  , p.id as pid\n"
                    + "	   ,p.name as pname\n"
                    + "	   ,t.name as tname\n"
                    + "	   ,t.isAvl\n"
                    + "	   ,t.id as tid\n"
                    + "	   FRom CLient c \n"
                    + "	   INNER JOIN  Cli_Pack cp ON c.id = cp.cid\n"
                    + "	   INNER JOIN   Pack p ON p.id = cp.pid\n"
                    + "	   INNER JOIN Trainer t ON cp.tid = t.id";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            ArrayList<Trainer_Clients> trainers = new ArrayList<>();
            while (rs.next()) {
                boolean exited = false;
                Trainer_Clients tc = new Trainer_Clients();
                String tname = rs.getString("tname").trim();

                for (Trainer_Clients trainer : trainers) {

                    if (trainer.getTrainerName().equalsIgnoreCase(tname)) {
                        tc = trainer;
                        exited = true;

                        break;

                    }
                }
                if (exited) {
                    Pack p = new Pack();
                    p.setId(rs.getInt("pid"));
                    p.setName(rs.getString("pname").trim());
                    Client c = new Client();
                    c.setName(rs.getString("name").trim());
                    c.setId(rs.getInt("id"));
                    tc.getClients().add(c);
                    tc.getPacks().add(p);
                    String spec = rs.getString("pname").trim();
                    if (!tc.getSpecs().contains(spec)) {
                        tc.getSpecs().add(spec);
                    }
                } else {
                    tc.setTrainerId(rs.getInt("tid"));
                    tc.setTrainerName(tname);
                    tc.setTrainerAvaliable(rs.getBoolean("isAvl"));

                    Pack p = new Pack();
                    p.setId(rs.getInt("pid"));
                    p.setName(rs.getString("pname").trim());
                    Client c = new Client();
                    c.setName(rs.getString("name").trim());
                    c.setId(rs.getInt("id"));
                    tc.getClients().add(c);
                    tc.getPacks().add(p);

                    tc.getSpecs().add(rs.getString("pname").trim());
                    trainers.add(tc);
                }

            }
            return trainers;
        } catch (SQLException ex) {
            Logger.getLogger(TrainerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static void main(String[] args) {
        String [] a = {1+"",2+"",3+""};
        new TrainerDAO().addTrainer("test2", a);
        
//        ArrayList<Trainer> trainers = new TrainerDAO().getAllTrainers();
//        for (Trainer trainer : trainers) {
//            System.out.println(trainer.getName());
//            for (Integer spec : trainer.getSpecs()) {
//                System.out.println(spec );
//            }
//        }
    }
}
