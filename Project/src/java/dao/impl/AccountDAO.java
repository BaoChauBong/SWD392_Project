/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.impl;

import dao.DBContext;
import dao.IAccountDAO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;

/**
 *
 * @author Thai Tran
 */
public class AccountDAO extends DBContext implements IAccountDAO {

    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;
    private String query;

    /**
     * getAllAccount method implement from IAccountDAO
     *
     * @return chapters. <code>java.util.ArrayList</code> object
     */
    @Override
    public ArrayList<Account> getAllAccount() {
        ArrayList<Account> accountList = new ArrayList<>();
        try {
            /*Set up connection and Sql statement for Query*/
            query = "SELECT * FROM dbo.Account";
            con = new DBContext().getConnection();
            ps = con.prepareStatement(query);

            /*Query and save in ResultSet*/
            rs = ps.executeQuery();

            /*Assign data to an arraylist of Account*/
            while (rs.next()) {
                accountList.add(new Account(
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("firstname"),
                        rs.getString("lastname"),
                        rs.getString("email"),
                        rs.getInt("roleId")
                ));
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
        return accountList;
    }

    /**
     * getAccountByRoleId method implement from IAccountDAO Get all accounts by
     * an identify id
     *
     * @return chapters. <code>java.util.ArrayList</code> object
     */
    @Override
    public ArrayList<Account> getAccountByRoleId(int roleId) {
        ArrayList<Account> accountList = new ArrayList<>();
        try {
            /*Set up connection and Sql statement for Query*/
            query = "SELECT * FROM dbo.Account where roleId=?";
            con = new DBContext().getConnection();
            ps = con.prepareStatement(query);
            ps.setInt(1, roleId);

            /*Query and save in ResultSet*/
            rs = ps.executeQuery();

            /*Assign data to an Account*/
            while (rs.next()) {
                accountList.add(new Account(
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("firstname"),
                        rs.getString("lastname"),
                        rs.getString("email"),
                        rs.getInt("roleId")
                ));
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
        return accountList;
    }

    /**
     * getAccountByUserName method implement from IAccountDAO
     *
     * @param username is primary key of the Account. String object
     * @return Account object
     */
    //check xem t??i kho???n c?? t???n t???i ko
    @Override
    public Account getAccountByUsername(String username) {
        try {
            /*Set up connection and Sql statement for Query*/
            query = "SELECT * FROM dbo.Account where username=?";
            con = new DBContext().getConnection();
            ps = con.prepareStatement(query);
            ps.setString(1, username);

            /*Query and save in ResultSet*/
            rs = ps.executeQuery();

            /*Assign data to an Account*/
            while (rs.next()) {
                Account a = new Account(
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("firstname"),
                        rs.getString("lastname"),
                        rs.getString("email"),
                        rs.getInt("roleId")
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

    /**
     * getAccountBySubUserName method implement from IAccountDAO
     *
     * @param accountsubUserName String
     * @return <List>Account
     */
    @Override
    public ArrayList<Account> getUserAccountBySubUsername(String accountsubUsername, int pageIndex) {
        ArrayList<Account> list = new ArrayList<>();
        try {
            /*Set up connection and Sql statement for Query*/
            query = "SELECT *\n"
                    + "FROM\n"
                    + "(\n"
                    + "SELECT *,\n"
                    + "ROW_NUMBER() OVER (ORDER BY username) AS Seq\n"
                    + "FROM dbo.Account\n"
                    + "where roleId = 3 and username like ?)t\n"
                    + "WHERE Seq BETWEEN ? AND ?";
            con = new DBContext().getConnection();
            ps = con.prepareStatement(query);
            ps.setString(1, "%" + accountsubUsername.trim() + "%");
            ps.setInt(2, (pageIndex - 1) * 5 + 1);
            ps.setInt(3, (pageIndex - 1) * 5 + 5);


            /*Query and save in ResultSet*/
            rs = ps.executeQuery();

            /*Assign data to an arraylist of Movie*/
            while (rs.next()) {
                list.add(new Account(
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("firstname"),
                        rs.getString("lastname"),
                        rs.getString("email"),
                        rs.getInt("roleId")
                ));
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
        return list;
    }
    
    /**
     * getTotalAccountByUsername method implement from IAccountDAO
     *
     * @return boolean object to know it executed or not
     */
    @Override
    public int getTotalAccountByUsername(String username) {
        try {
            /*Set up connection and Sql statement for Query*/
            query = "select count(*) from Account where roleId=3 and username like ?";
            con = new DBContext().getConnection();
            ps = con.prepareStatement(query);
            ps.setString(1, "%"+username+"%");

            /*Excute query and store it to check*/
            rs = ps.executeQuery();

            while (rs.next()) {
                return rs.getInt(1);
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
        return 0;
    }


    /**
     * insertAccount1 method implement from IAccountDAO
     *
     * @param Account. Account object
     * @return boolean object to know it executed or not
     */
    @Override
    public boolean insertAccount1(Account a) {

        int check = 0;
        try {
            query = "insert into account values(?,?,?,?,?,?)";
            con = new DBContext().getConnection();
            ps = con.prepareStatement(query);

            ps.setString(1, a.getUsername());
            ps.setString(2, a.getPassword());
            ps.setString(3, a.getFirstname());
            ps.setString(4, a.getLastname());
            ps.setString(5, a.getEmail());
            ps.setInt(6, a.getRoleId());
            check = ps.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            closeConnection(con);
            closePreparedStatement(ps);
            closeResultSet(rs);
        }
        return check > 0;
    }

    /**
     * editAccount method implement from IAccountDAO
     *
     * @param Account. Account object
     * @return boolean object to know it executed or not
     */
    @Override
    public boolean editAccount(Account a) {
        int check = 0;
        try {
            /*Set up connection and Sql statement for Query*/
            query = "UPDATE [Account] SET [password] = ?,[firstname]=?,[lastname]=?,[email]=?,[roleId]=? WHERE [username] = ?";
            con = new DBContext().getConnection();
            ps = con.prepareStatement(query);

            /*Set params for Query*/
            ps.setString(1, a.getPassword());
            ps.setString(2, a.getFirstname());
            ps.setString(3, a.getLastname());
            ps.setString(4, a.getEmail());
            ps.setInt(5, a.getRoleId());
            ps.setString(6, a.getUsername());

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

    /**
     * deleteAccount method implement from IAccountDAO
     *
     * @param username String
     * @return boolean object to know it executed or not
     */
    @Override
    public boolean deleteAccount(String username) {
        int check = 0;
        try {
            /*Set up connection and Sql statement for Query*/
            query = "delete from Account where username = ?";
            con = new DBContext().getConnection();
            ps = con.prepareStatement(query);

            /*Set params for Query*/
            ps.setString(1, username);

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

    /**
     * getTotalAccountByRole method implement from IAccountDAO
     *
     * @param roleId int
     * @return boolean object to know it executed or not
     */
    @Override
    public int getTotalAccountByRole(int roleId) {
        try {
            /*Set up connection and Sql statement for Query*/
            query = "select count(*) from Account where roleId = ?";
            con = new DBContext().getConnection();
            ps = con.prepareStatement(query);
            ps.setInt(1, roleId);

            /*Excute query and store it to check*/
            rs = ps.executeQuery();

            while (rs.next()) {
                return rs.getInt(1);
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
        return 0;
    }

    /**
     * pagingAccount implement from IAccountDAO
     *
     * @param pageIndex int, roleId int
     * @return boolean object to know it executed or not
     */
    @Override
    public List<Account> pagingAccount(int pageIndex, int roleId) {
        List<Account> accountList = new ArrayList<>();
        try {
            /*Set up connection and Sql statement for Query*/
            query = "SELECT *\n"
                    + "FROM\n"
                    + "(\n"
                    + "SELECT *,\n"
                    + "ROW_NUMBER() OVER (ORDER BY username) AS Seq\n"
                    + "FROM dbo.Account\n"
                    + "where roleId=?)t\n"
                    + "WHERE Seq BETWEEN ? AND ?";
            con = new DBContext().getConnection();
            ps = con.prepareStatement(query);
            int a = roleId;
            int b = pageIndex;
            int c = (pageIndex - 1) * 5;
            int d = pageIndex + 5;
            ps.setInt(1, roleId);
            ps.setInt(2, (pageIndex - 1) * 5 + 1);
            ps.setInt(3, (pageIndex - 1) * 5 + 5);

            /*Excute query and store it to check*/
            rs = ps.executeQuery();

            while (rs.next()) {
                accountList.add(new Account(
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("firstname"),
                        rs.getString("lastname"),
                        rs.getString("email"),
                        rs.getInt("roleId")
                ));
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
        return accountList;
    }

    public static void main(String[] args) {
        AccountDAO dao = new AccountDAO();
        List<Account> accountList = dao.pagingAccount(0, 3);
        for (Account a : accountList) {
            System.out.println(a);
        }

    }

    //           h??m ????ng nh???p
    @Override
    public Account getAccountByUsernameAndPassword(String user, String pass) {

        //c??u truy v???n l???y ra account theo username va password
        query = "select * from account where username = ? and password = ?";
        try {
            con = new DBContext().getConnection();
            ps = con.prepareStatement(query); // th???c hi???n truy v???n tham s??? trong c??u truy v???n
            ps.setString(1, user);
            ps.setString(2, pass);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new Account(rs.getString(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getString(5), rs.getInt(6));
            }
        } catch (SQLException e) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            closeConnection(con);
            closePreparedStatement(ps);
            closeResultSet(rs);
        }
        return null;
    }

    //h??m ????ng k??
    @Override
    public void insertAccount(String user, String pass, String firstname, String lastname, String email, int roleId) {
        //c??u l???nh insert account
        query = "insert into account values(?,?,?,?,?,6)";
        try {
            con = new DBContext().getConnection(); //k???t n???i vs database
            ps = con.prepareStatement(query);
            ps.setString(1, user);
            ps.setString(2, pass);
            ps.setString(3, firstname);
            ps.setString(4, lastname);
            ps.setString(5, email);
            //ps.setInt(6, roleId);
            ps.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            closeConnection(con);
            closePreparedStatement(ps);
            closeResultSet(rs);
        }
    }

    //h??m thay ?????i m???t kh???u
    @Override
    public void updatePassword(String newpass, String oldpass, String user) {
        query = "update account set password=? where username=? and password=? ";
        try {
            con = new DBContext().getConnection();
            ps = con.prepareStatement(query);
            ps.setString(1, newpass);
            ps.setString(2, user);
            ps.setString(3, oldpass);
            ps.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            closeConnection(con);
            closePreparedStatement(ps);
            closeResultSet(rs);
        }
    }

    // h??m check email v?? user c?? t???n t???i hay kh??ng
    public Account checkEmail(String user, String email) {

        try {
            query = "SELECT * FROM dbo.Account where username=? and email = ?";
            con = new DBContext().getConnection();
            ps = con.prepareStatement(query);
            ps.setString(1, user);
            ps.setString(2, email);
            rs = ps.executeQuery();

            while (rs.next()) {
                Account a = new Account(
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("firstname"),
                        rs.getString("lastname"),
                        rs.getString("email"),
                        rs.getInt("roleId")
                );
                return a;
            }
        } catch (SQLException e) {
        }
        return null;
    }
}
