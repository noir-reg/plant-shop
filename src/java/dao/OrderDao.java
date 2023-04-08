/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.Account;
import dto.Order;
import dto.OrderDetail;
import dto.ReOrder;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import mylibrary.DBUtils;

/**
 *
 * @author Admin
 */
public class OrderDao {

    public static ArrayList<Order> getOrders(String email) {
        ArrayList<Order> list = new ArrayList();
        try {
            Connection cn = DBUtils.makeConnection();
            if (cn != null) {

                String sql = "select OrderID,OrdDate,shipdate,Orders.status as status,Accounts.accID as accID from Accounts join Orders on Accounts.accID=Orders.AccID where email = ?";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, email);
                ResultSet table = pst.executeQuery();
                if (table != null) {
                    while (table.next()) {
                        int orderID = table.getInt("OrderID");
                        String orderDate = table.getString("OrdDate");
                        String shipDate = table.getString("shipdate");
                        int status = table.getInt("status");
                        int accID = table.getInt("accID");

                        Order or = new Order(orderID, orderDate, shipDate, status, accID);
                        list.add(or);
                    }
                }
                cn.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    public static ArrayList<Order> getOrders() {
        ArrayList<Order> list = new ArrayList();
        try {
            Connection cn = DBUtils.makeConnection();
            if (cn != null) {

                String sql = "select OrderID,OrdDate,shipdate,Orders.status as status,Accounts.accID as accID from Accounts join Orders on Accounts.accID=Orders.AccID  ";
                Statement pst = cn.createStatement();
                 
                ResultSet table = pst.executeQuery(sql);
                if (table != null) {
                    while (table.next()) {
                        int orderID = table.getInt("OrderID");
                        String orderDate = table.getString("OrdDate");
                        String shipDate = table.getString("shipdate");
                        int status = table.getInt("status");
                        int accID = table.getInt("accID");
                        
                        Order or = new Order(orderID, orderDate, shipDate, status, accID);
                        list.add(or);
                    }
                }
                cn.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    public static ArrayList<Order> getOrders(String email,int status) {
        ArrayList<Order> list = new ArrayList();
        try {
            Connection cn = DBUtils.makeConnection();
            if (cn != null) {

                String sql = "select OrderID,OrdDate,shipdate,Orders.status as status,Accounts.accID as accID from Accounts join Orders on Accounts.accID=Orders.AccID where email = ? and Orders.status=? ";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, email);
                pst.setInt(2,status);
                ResultSet table = pst.executeQuery();
                if (table != null) {
                    while (table.next()) {
                        int orderID = table.getInt("OrderID");
                        String orderDate = table.getString("OrdDate");
                        String shipDate = table.getString("shipdate");
                        status = table.getInt("status");
                        int accID = table.getInt("accID");

                        Order or = new Order(orderID, orderDate, shipDate, status, accID);
                        list.add(or);
                    }
                }
                cn.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static ArrayList<Order> getOrders(String email, String from, String to) {
        ArrayList<Order> list = new ArrayList();
        try {
            Connection cn = DBUtils.makeConnection();
            if (cn != null) {

                String sql = "select OrderID,OrdDate,shipdate,Orders.status as status,Accounts.accID as accID from Accounts join Orders on Accounts.accID=Orders.AccID where email = ? "
                        + " and OrdDate between ? and ?";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, email);
                pst.setString(2, from);
                pst.setString(3, to);
                ResultSet table = pst.executeQuery();
                if (table != null) {
                    while (table.next()) {
                        int orderID = table.getInt("OrderID");
                        String orderDate = table.getString("OrdDate");
                        String shipDate = table.getString("shipdate");
                        int status = table.getInt("status");

                        Order or = new Order(orderID, orderDate, shipDate, status);
                        list.add(or);
                    }
                }
                cn.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
   public static ArrayList<Order> getOrders( String from, String to) {
        ArrayList<Order> list = new ArrayList();
        try {
            Connection cn = DBUtils.makeConnection();
            if (cn != null) {

                String sql = "select OrderID,OrdDate,shipdate,Orders.status as status,Accounts.accID as accID from Accounts join Orders on Accounts.accID=Orders.AccID where  "
                        + " OrdDate between ? and ?";
                PreparedStatement pst = cn.prepareStatement(sql);
                 
                pst.setString(1, from);
                pst.setString(2, to);
                ResultSet table = pst.executeQuery();
                if (table != null) {
                    while (table.next()) {
                        int orderID = table.getInt("OrderID");
                        String orderDate = table.getString("OrdDate");
                        String shipDate = table.getString("shipdate");
                        int status = table.getInt("status");
                        int accID=table.getInt("accID");
                        Order or = new Order(orderID, orderDate, shipDate, status,accID);
                        list.add(or);
                    }
                }
                cn.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    public static ArrayList<OrderDetail> getOrderDetail(int orderID) {
        Connection cn = null;
        ArrayList<OrderDetail> list = new ArrayList();
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {

                String sql = "select * from OrderDetails join Plants on OrderDetails.FID=Plants.PID where OrderID = ?";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setInt(1, orderID);
                ResultSet table = pst.executeQuery();
                if (table != null) {
                    while (table.next()) {
                        int detailID = table.getInt("DetailID");
                        int plantID = table.getInt("PID");
                        String plantName = table.getString("PName");
                        int price = table.getInt("price");
                        String imgPath = table.getString("imgPath");
                        int quantity = table.getInt("quantity");

                        //int role=table.getInt("role");
                        OrderDetail ord = new OrderDetail(detailID, orderID, plantID, plantName, price, imgPath, quantity);
                        list.add(ord);
                    }
                }
                cn.close();
            }

        } catch (Exception e) {

        } finally {
            try {
                if (cn != null) {
                    cn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public static boolean insertOrder(String email, HashMap<String, Integer> cart) throws Exception {
        Connection cn = null;
        boolean result = false;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                int accid = 0, orderid = 0;
                cn.setAutoCommit(false);
                String sql = "select accID from Accounts where Accounts.email=?";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, email);
                ResultSet rs = pst.executeQuery();
                if (rs != null && rs.next()) {
                    accid = rs.getInt("accID");
                }
                Date d = new Date(System.currentTimeMillis());
                sql = "insert into Orders(OrdDate,status,AccID) values(?,?,?)";
                pst = cn.prepareStatement(sql);
                pst.setDate(1, d);
                pst.setInt(2, 1);
                pst.setInt(3, accid);
                pst.executeUpdate();
                sql = "select top 1 orderID from Orders order by orderId desc";
                pst = cn.prepareStatement(sql);
                rs = pst.executeQuery();
                if (rs != null && rs.next()) {
                    orderid = rs.getInt("orderID");
                }
                Set<String> pids = cart.keySet();
                for (String pid : pids) {
                    sql = "insert into OrderDetails values(?,?,?)";
                    pst = cn.prepareStatement(sql);
                    pst.setInt(1, orderid);
                    pst.setInt(2, Integer.parseInt(pid.trim()));
                    pst.setInt(3, cart.get(pid));
                    pst.executeUpdate();
                    cn.commit();
                    cn.setAutoCommit(true);
                }
                return true;
            } else {

            }
        } catch (Exception e) {
            try {
                if (cn != null) {
                    cn.rollback();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
            result = false;
        } finally {
            try {
                if (cn != null) {
                    cn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public static boolean insertReOrder( int accID, int FID, int quantity,ArrayList<ReOrder> list) throws Exception {
        Connection cn = null;
        boolean result = false;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                 int orderid=0;
                cn.setAutoCommit(false);
                String sql = "";
                PreparedStatement pst = null;

                Date d = new Date(System.currentTimeMillis());
                sql = "insert into Orders(OrdDate,status,AccID) values(?,?,?)";
                pst = cn.prepareStatement(sql);
                pst.setDate(1, d);

                pst.setInt(2, 1);
                pst.setInt(3, accID);
                pst.executeUpdate();
                sql = "select top 1 orderID from Orders order by orderId desc";
                pst = cn.prepareStatement(sql);
                ResultSet rs = pst.executeQuery();
                if (rs != null && rs.next()) {
                   orderid = rs.getInt("orderID");
                }
                 
                for (ReOrder rO : list) {
                    sql = "insert into OrderDetails values(?,?,?)";
                    pst = cn.prepareStatement(sql);
                    pst.setInt(1, orderid);
                    pst.setInt(2, rO.getPlantID());
                    pst.setInt(3, rO.getQuantity());
                    pst.executeUpdate();
                    cn.commit();
                    cn.setAutoCommit(true);
                }
                return true;
            } else {

            }
        } catch (Exception e) {
            try {
                if (cn != null) {
                    cn.rollback();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
            result = false;
        } finally {
            try {
                if (cn != null) {
                    cn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public static int updateStatus(int orderID, int status) {
        int result = 0;
        try {
            Connection cn = DBUtils.makeConnection();
            if (cn != null) {

                String sql = "update Orders set status=? where  orderID=?";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setInt(1, status);
                pst.setInt(2, orderID);

                result = pst.executeUpdate();

                cn.close();
            }

        } catch (Exception e) {

        }

        return result;
    }

    public static ArrayList<ReOrder> getReOrderDetail(int orderID) {
        Connection cn = null;
        ArrayList<ReOrder> list = new ArrayList();

        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {

                String sql = "select Orders.OrderID as OrderID ,OrdDate, shipdate, status,AccID,DetailId,FID,quantity from Orders join OrderDetails on Orders.OrderID=OrderDetails.OrderID \n"
                        + " where Orders.OrderID= ?";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setInt(1, orderID);
                ResultSet table = pst.executeQuery();
                if (table != null) {
                    while (table.next()) {
                        orderID = table.getInt("OrderID");
                        String ordate = table.getString("OrdDate");
                        String shipdate = table.getString("shipdate");
                        int status = table.getInt("status");
                        int accID = table.getInt("AccID");
                        int detailID = table.getInt("DetailId");

                        int plantID = table.getInt("FID");

                        int quantity = table.getInt("quantity");

                        //int role=table.getInt("role");
                        ReOrder ord = new ReOrder(orderID, ordate, shipdate, status, accID, detailID, plantID, quantity);
                        list.add(ord);
                    }
                }
                cn.close();
            }

        } catch (Exception e) {

        } finally {
            try {
                if (cn != null) {
                    cn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return list;
    }
}
