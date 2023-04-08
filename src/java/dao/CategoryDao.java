/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.Category;
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
public class CategoryDao {

    public static ArrayList<Category> getCategories() {
        ArrayList<Category> list = new ArrayList();
        Connection cn = null;

        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "select *"
                        + "from Categories  ";

                Statement pst = cn.createStatement();

                ResultSet rs = pst.executeQuery(sql);

                if (rs != null) {
                    while (rs.next()) {
                        int id = rs.getInt("CateID");
                        String name = rs.getString("CateName");

                        list.add(new Category(id, name));

                    }
                }
                cn.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;

    }

    public static int insertCategory(String name) {
        int result = 0;
        try {
            Connection cn = DBUtils.makeConnection();
            if (cn != null) {

                String sql = "insert into dbo.Categories(CateName ) values(? )";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, name);

                result = pst.executeUpdate();

                cn.close();
            }

        } catch (Exception e) {

        }
        return result;
    }
    public static int updateName(int id,String name) {
        int result = 0;
        try {
            Connection cn = DBUtils.makeConnection();
            if (cn != null) {

                String sql = "update Categories set CateName=? where CateID=?";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, name);
                pst.setInt(2, id);

                result = pst.executeUpdate();

                cn.close();
            }

        } catch (Exception e) {

        }

        return result;
    }

}
