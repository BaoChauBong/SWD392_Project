/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.impl;

import dao.DBContext;
import dao.ITestProjectDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;
import model.CodeTracker;
import model.IssueTracker;
import model.TestProject;

/**
 *
 * @author Admin
 */
public class TestProjectDAO extends DBContext implements ITestProjectDAO {

    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;
    private String query;

    @Override
    public ArrayList<TestProject> getAllTestProject() {
        ArrayList<TestProject> testProjectList = new ArrayList<>();
        try {
            /*Set up connection and Sql statement for Query*/
            query = "select t.name, t.prefix, t.description, i.Iid, i.Iname, c.Cid, c.Cname, t.isActive, t.isPublic\n"
                    + "from TestProject t, IssueTracker i, CodeTracker c\n"
                    + "where t.issueTracker = i.Iid and t.codeTracker = c.Cid";
            con = new DBContext().getConnection();
            ps = con.prepareStatement(query);

            /*Query and save in ResultSet*/
            rs = ps.executeQuery();

            /*Assign data to an arraylist of Account*/
            while (rs.next()) {
                TestProject t = new TestProject();
                t.setName(rs.getString("name"));
                t.setPrefix(rs.getString("prefix"));
                t.setDescription(rs.getString("description"));
                t.setIsActive(rs.getBoolean("isActive"));
                t.setIsPublic(rs.getBoolean("isPublic"));

                IssueTracker i = new IssueTracker();
                i.setId(rs.getString("Iid"));
                i.setName(rs.getString("Iname"));

                CodeTracker c = new CodeTracker();
                c.setId(rs.getString("Cid"));
                c.setName(rs.getString("Cname"));

                t.setIssueTracker(i);
                t.setCodeTracker(c);

                testProjectList.add(t);
            }
        } catch (SQLException e) {
            /*Exeption Handle*/
            Logger.getLogger(TestProjectDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            /*Close connection, prepare statement, result set*/
            closeConnection(con);
            closePreparedStatement(ps);
            closeResultSet(rs);
        }
        return testProjectList;
    }

    @Override
    public boolean editTestProject(TestProject a) {
        int check = 0;
        try {
            /*Set up connection and Sql statement for Query*/
            query = "UPDATE [TestProject] SET [prefix] = ?,[description]=?,[issueTracker]=?,[codeTracker]=?,[isActive]=?,[isPublic]=? WHERE [name] = ?";
            con = new DBContext().getConnection();
            ps = con.prepareStatement(query);

            /*Set params for Query*/
            ps.setString(1, a.getPrefix());
            ps.setString(2, a.getDescription());
            ps.setString(3, a.getIssueTracker().getId());
            ps.setString(4, a.getCodeTracker().getId());
            ps.setBoolean(5, a.isIsActive());
            ps.setBoolean(6, a.isIsPublic());
            ps.setString(7, a.getName());

            /*Excute query and store it to check*/
            check = ps.executeUpdate();

        } catch (SQLException e) {
            /*Exeption Handle*/
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            /*Close connection, prepare statement, result set*/
            closeConnection(con);
            closePreparedStatement(ps);
            closeResultSet(rs);
        }
        return check > 0;
    }

    @Override
    public boolean deleteTestProject(String name) {
        int check = 0;
        try {
            /*Set up connection and Sql statement for Query*/
            query = "delete from TestProject where [name] = ?";
            con = new DBContext().getConnection();
            ps = con.prepareStatement(query);

            /*Set params for Query*/
            ps.setString(1, name);

            /*Excute query and store it to check*/
            check = ps.executeUpdate();

        } catch (SQLException e) {
            /*Exeption Handle*/
            Logger.getLogger(TestProjectDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            /*Close connection, prepare statement, result set*/
            closeConnection(con);
            closePreparedStatement(ps);
            closeResultSet(rs);
        }
        return check > 0;
    }

    @Override
    public ArrayList<IssueTracker> listIssueTracker() {
        ArrayList<IssueTracker> list = new ArrayList<>();
        try {
            query = "select * from IssueTracker";
            con = new DBContext().getConnection();
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                IssueTracker i = new IssueTracker();
                i.setId(rs.getString("Iid"));
                i.setName(rs.getString("Iname"));
                i.setConfiguration(rs.getString("Iconfiguration"));

                list.add(i);
            }
        } catch (SQLException e) {
            Logger.getLogger(TestProjectDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return list;
    }

    @Override
    public ArrayList<CodeTracker> listCodeTracker() {
        ArrayList<CodeTracker> list = new ArrayList<>();
        try {
            query = "select * from CodeTracker";
            con = new DBContext().getConnection();
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                CodeTracker i = new CodeTracker();
                i.setId(rs.getString("Cid"));
                i.setName(rs.getString("Cname"));
                i.setConfiguration(rs.getString("Cconfiguration"));

                list.add(i);
            }
        } catch (SQLException e) {
            Logger.getLogger(TestProjectDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return list;
    }

    @Override
    public boolean insertTestProject1(TestProject a) {
        int check = 0;
        try {
            query = "INSERT INTO [dbo].[TestProject]\n"
                    + "           ([name]\n"
                    + "           ,[prefix]\n"
                    + "           ,[description]\n"
                    + "           ,[issueTracker]\n"
                    + "           ,[codeTracker]\n"
                    + "           ,[isActive]\n"
                    + "           ,[isPublic])"
                    + " values(?,?,?,?,?,?,?)";
            con = new DBContext().getConnection();
            ps = con.prepareStatement(query);

            ps.setString(1, a.getName());
            ps.setString(2, a.getPrefix());
            ps.setString(3, a.getDescription());
            ps.setString(4, a.getIssueTracker().getId());
            ps.setString(5, a.getCodeTracker().getId());
            ps.setBoolean(6, a.isIsActive());
            ps.setBoolean(7, a.isIsPublic());
            check = ps.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(TestProjectDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            closeConnection(con);
            closePreparedStatement(ps);
            closeResultSet(rs);
        }
        return check > 0;
    }

    public static void main(String[] args) {
        TestProjectDAO dao = new TestProjectDAO();
        TestProject t = dao.getTestProjectByName("project");
        System.out.println(t);
    }

    @Override
    public TestProject getTestProjectByName(String name) {
        try {
            /*Set up connection and Sql statement for Query*/
            query = "SELECT * FROM dbo.TestProject where name=?";
            con = new DBContext().getConnection();
            ps = con.prepareStatement(query);
            ps.setString(1, name);

            /*Query and save in ResultSet*/
            rs = ps.executeQuery();

            /*Assign data to an Account*/
            while (rs.next()) {
                IssueTracker i = new IssueTracker(rs.getString("issueTracker"));
                CodeTracker c = new CodeTracker(rs.getString("codeTracker"));

                TestProject a = new TestProject(
                        rs.getString("name"),
                        rs.getString("prefix"),
                        rs.getString("description"),
                        rs.getBoolean("isActive"),
                        rs.getBoolean("isPublic"),
                        i,
                        c
                );
                return a;
            }
        } catch (SQLException e) {
            /*Exeption Handle*/
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            /*Close connection, prepare statement, result set*/
            closeConnection(con);
            closePreparedStatement(ps);
            closeResultSet(rs);
        }
        return null;
    }

}
