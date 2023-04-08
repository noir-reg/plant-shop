/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.Plant;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import mylibrary.DBUtils;

/**
 *
 * @author Admin
 */
public class PlantDao {

    public static ArrayList<Plant> getPlants(String keyWord, String searchBy) {
        ArrayList<Plant> list = new ArrayList();
        Connection cn = null;

        try {
            cn = DBUtils.makeConnection();
            if (cn != null && searchBy != null) {
                String sql = "select PID,PName,price,imgPath,description,status,Plants.CateID as 'CateID',CateName\n"
                        + "                         from Plants join Categories on Plants.CateID=Categories.CateID";

                if (searchBy.equalsIgnoreCase("bycate")) {
                    sql = sql + " where CateName like ?";
                } else {
                    sql = sql + " where Plants.PName like ?";
                }
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, "%" + keyWord + "%");
                ResultSet rs = pst.executeQuery();

                if (rs != null) {
                    while (rs.next()) {
                        int id = rs.getInt("PID");
                        String name = rs.getString("PName");
                        int price = rs.getInt("price");
                        String imgPath = rs.getString("imgPath");
                        String description = rs.getString("description");
                        int status = rs.getInt("status");
                        int cateid = rs.getInt("CateID");
                        String cateName = rs.getString("CateName");
                        Plant plant = new Plant(id, name, price, imgPath, description, status, cateid, cateName);
                        list.add(plant);
                    }
                }
                cn.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;

    }

    public static Plant getPlant(String id) {
        Plant plant = null;
        Connection cn = null;

        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "select PID,PName,price,imgPath,description,status,Plants.CateID as CateID \n"
                        + "from Plants where PID=? ";

                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setInt(1, Integer.parseInt(id));
                ResultSet rs = pst.executeQuery();

                if (rs != null) {
                    while (rs.next()) {

                        String name = rs.getString("PName");
                        int price = rs.getInt("price");
                        String imgPath = rs.getString("imgPath");
                        String description = rs.getString("description");
                        int status = rs.getInt("status");
                        int cateid = rs.getInt("CateID");
                         
                        plant = new Plant(Integer.parseInt(id), name, price, imgPath, description, status, cateid );

                    }
                }
                cn.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return plant;

    }
    public static  ArrayList<Plant>  getPlants( ) {
        ArrayList<Plant> list =new ArrayList();
        Connection cn = null;

        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "select PID,PName,price,imgPath,description,status,Plants.CateID as CateID \n"
                        + "from Plants  ";

                 Statement pst = cn.createStatement();
                 
                ResultSet rs = pst.executeQuery(sql);

                if (rs != null) {
                    while (rs.next()) {
                        int id=rs.getInt("PID");
                        String name = rs.getString("PName");
                        int price = rs.getInt("price");
                        String imgPath = rs.getString("imgPath");
                        String description = rs.getString("description");
                        int status = rs.getInt("status");
                        int cateid = rs.getInt("CateID");
                         
                        list.add(new Plant(id, name, price, imgPath, description, status, cateid ));

                    }
                }
                cn.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;

    }
    public static Plant getPlant(int pid) {
        Plant plant = null;
        Connection cn = null;

        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "select PID,PName,price,imgPath,description,status,Plants.CateID as CateID,CateName \n"
                        + "from Plants,Categories where Plants.CateID=Categories.CateID and PID=? ";

                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setInt(1, pid);
                ResultSet rs = pst.executeQuery();

                if (rs != null) {
                    while (rs.next()) {
                        pid=rs.getInt("PID");
                        String name = rs.getString("PName");
                        int price = rs.getInt("price");
                        String imgPath = rs.getString("imgPath");
                        String description = rs.getString("description");
                        int status = rs.getInt("status");
                        int cateid = rs.getInt("CateID");
                         String cateName = rs.getString("CateName");
                        plant = new Plant(pid, name, price, imgPath, description, status, cateid ,cateName);

                    }
                }
                cn.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return plant;

    }
     public static int updatePrice(int id,int price) {
        int result = 0;
        try {
            Connection cn = DBUtils.makeConnection();
            if (cn != null) {

                String sql = "update Plants set price=? where PID=?";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setInt(1, price);
                pst.setInt(2, id);

                result = pst.executeUpdate();

                cn.close();
            }

        } catch (Exception e) {

        }

        return result;
    }

    public static int updateStatus(int status, String id) {
        int result = 0;
        try {
            Connection cn = DBUtils.makeConnection();
            if (cn != null) {

                String sql = "update Plants set status=? where PID=?";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setInt(1, status);
                pst.setInt(2, Integer.parseInt(id));

                result = pst.executeUpdate();

                cn.close();
            }

        } catch (Exception e) {

        }

        return result;
    }
     public static int insertPlant(String name, int price, String img, String description, int status, int cateID) {
        int result = 0;
        try {
            Connection cn = DBUtils.makeConnection();
            if (cn != null) {

                String sql = "insert into dbo.Plants(PName,price,imgPath,description,status,CateID) values(?,?,?,?,?,?)";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, name);
                pst.setInt(2, price);
                pst.setString(3, img);
                pst.setString(4, description);
                pst.setInt(5, status);
                pst.setInt(6, cateID);

                result = pst.executeUpdate();

                cn.close();
            }

        } catch (Exception e) {

        }
        return result;
    }
}
