package dao;

import dto.Account;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import mylibrary.DBUtils;
import java.sql.PreparedStatement;

public class AccountDao {

    public static ArrayList<Account> getAccounts() throws Exception {
        ArrayList<Account> list = new ArrayList();
        try {
            Connection cn = DBUtils.makeConnection();
            if (cn != null) {

                String sql = "select * from dbo.Accounts ";
                Statement st = cn.createStatement();
                ResultSet table = st.executeQuery(sql);
                if (table != null) {
                    while (table.next()) {
                        int accid = table.getInt("accID");
                        String email = table.getString("email");
                        String password = table.getString("password");
                        String fullname = table.getString("fullname");
                        String phone = table.getString("phone");
                        int status = table.getInt("status");
                        int role = table.getInt("role");
                        Account acc = new Account(accid, email, password, fullname, phone, status, role);
                        list.add(acc);
                    }
                }
                cn.close();
            }

        } catch (Exception e) {

        }
        return list;
    }

    public static ArrayList<Account> getAccounts(int role) throws Exception {
        ArrayList<Account> list = new ArrayList();
        try {
            Connection cn = DBUtils.makeConnection();
            if (cn != null) {

                String sql = "select * from dbo.Accounts where role = ?";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setInt(1, role);
                ResultSet table = pst.executeQuery();
                if (table != null) {
                    while (table.next()) {
                        int accid = table.getInt("accID");
                        String email = table.getString("email");
                        String password = table.getString("password");
                        String fullname = table.getString("fullname");
                        String phone = table.getString("phone");
                        int status = table.getInt("status");
                        //int role=table.getInt("role");
                        Account acc = new Account(accid, email, password, fullname, phone, status, role);
                        list.add(acc);
                    }
                }
                cn.close();
            }

        } catch (Exception e) {

        }
        return list;
    }

    public static ArrayList<Account> getAccounts(String keyword) throws Exception {
        ArrayList<Account> list = new ArrayList();
        try {
            Connection cn = DBUtils.makeConnection();
            if (cn != null) {
                
                  String  sql = "select * from dbo.Accounts where email like ?";
                
                
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, "%" + keyword + "%");
                ResultSet table = pst.executeQuery();
                if (table != null) {
                    while (table.next()) {
                        int accid = table.getInt("accID");
                        String email = table.getString("email");
                        String password = table.getString("password");
                        String fullname = table.getString("fullname");
                        String phone = table.getString("phone");
                        int status = table.getInt("status");
                        int role = table.getInt("role");
                        Account acc = new Account(accid, email, password, fullname, phone, status, role);
                        list.add(acc);
                    }
                }
                cn.close();
            }

        } catch (Exception e) {

        }
        return list;
    }

    public static Account getAccount(String email, String password) throws Exception {
        Account acc = null;

        try {
            Connection cn = DBUtils.makeConnection();
            if (cn != null) {

                String sql = "select * from dbo.Accounts where email = ? and password= ? COLLATE Latin1_General_CS_AS";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, email);
                pst.setString(2, password);
                ResultSet table = pst.executeQuery();
                while (table.next()) {
                    int accid = table.getInt("accID");
                    // String email=table.getString("email");
                    //String password = table.getString("password");
                    String fullname = table.getString("fullname");
                    String phone = table.getString("phone");
                    int status = table.getInt("status");
                    int role = table.getInt("role");
                    acc = new Account(accid, email, password, fullname, phone, status, role);
                }

                cn.close();
            }

        } catch (Exception e) {

        }
        return acc;
        // COLLATE Latin1_General_CS_AS phan biet hoa thuong trong sql

    }

    public static Account getAccount(String token) throws Exception {
        Account acc = null;

        try {
            Connection cn = DBUtils.makeConnection();
            if (cn != null) {

                String sql = "select * from dbo.Accounts where token=?";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, token);

                ResultSet table = pst.executeQuery();
                while (table.next()) {
                    int accid = table.getInt("accID");
                    String email = table.getString("email");
                    String password = table.getString("password");
                    String fullname = table.getString("fullname");
                    String phone = table.getString("phone");
                    int status = table.getInt("status");
                    int role = table.getInt("role");
                    acc = new Account(accid, email, password, fullname, phone, status, role);
                }

                cn.close();
            }

        } catch (Exception e) {

        }
        return acc;
        // COLLATE Latin1_General_CS_AS phan biet hoa thuong trong sql

    }

    public static int insertAccount(String email, String password, String fullname, String phone, String status, String role) {
        int result = 0;
        try {
            Connection cn = DBUtils.makeConnection();
            if (cn != null) {

                String sql = "insert into dbo.Accounts(email,password,fullname,phone,status,role) values(?,?,?,?,?,?)";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, email);
                pst.setString(2, password);
                pst.setString(3, fullname);
                pst.setString(4, phone);
                pst.setString(5, status);
                pst.setString(6, role);

                result = pst.executeUpdate();

                cn.close();
            }

        } catch (Exception e) {

        }
        return result;
    }

    public static int updateStatus(int status, String email) {
        int result = 0;
        try {
            Connection cn = DBUtils.makeConnection();
            if (cn != null) {

                String sql = "update Accounts set status=? where email=?";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setInt(1, status);
                pst.setString(2, email);

                result = pst.executeUpdate();

                cn.close();
            }

        } catch (Exception e) {

        }

        return result;
    }

    public static int updateProfile(String email, String fullname, String phone) {
        int result = 0;
        try {
            Connection cn = DBUtils.makeConnection();
            if (cn != null) {

                String sql = "update Accounts set fullname=? , phone=? where email=?";
                PreparedStatement pst = cn.prepareStatement(sql);

                pst.setString(1, fullname);
                pst.setString(2, phone);
                pst.setString(3, email);
                result = pst.executeUpdate();

                cn.close();
            }

        } catch (Exception e) {

        }

        return result;
    }

    public static int getStatus(String email) throws Exception {
        int status = 0;

        try {
            Connection cn = DBUtils.makeConnection();
            if (cn != null) {

                String sql = "select status from dbo.Accounts where email = ?  ";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, email);

                ResultSet table = pst.executeQuery();
                while (table.next()) {

                    status = table.getInt("status");

                }

                cn.close();
            }

        } catch (Exception e) {

        }
        return status;

    }

    public static int updateToken(String token, String email) {
        int result = 0;
        try {
            Connection cn = DBUtils.makeConnection();
            if (cn != null) {

                String sql = "update Accounts set token=? where email=?";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, token);
                pst.setString(2, email);

                result = pst.executeUpdate();

                cn.close();
            }

        } catch (Exception e) {

        }

        return result;
    }

}
