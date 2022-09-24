package Dao;

import enity.user;
import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @title: UserDao
 * @Author Xu
 * @Date: 2022/9/20 23:05
 * @Version 1.0
 */
public class UserDao implements Dao<user> {
    private static Connection connection = null;
    private static PreparedStatement statement = null;
    private static ResultSet resultSet = null;

    @Override
    public void insert(user user) {
        try {
            connection = DBUtil.getConnection();
            String sql = "insert into user values(null,?,?)";
            statement = connection.prepareStatement(sql);
            statement.setString(1, "userName");
            statement.setString(2, "passWord");
            int ret = statement.executeUpdate();
            if (ret == 1) {
                System.out.println("插入成功");
            } else {
                System.out.println("插入失败");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection, statement, resultSet);
        }
    }

    @Override
    public List<user> selectAll() {
        try {
            connection = DBUtil.getConnection();
            String sql = "slect * from user";
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            List<user> list = new ArrayList<>();
            while (resultSet.next()) {
                user user = new user();
                user.setUserId(resultSet.getInt("userId"));
                user.setPassword(resultSet.getString("passWord"));
                user.setUsername(resultSet.getString("userName"));
                list.add(user);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection, statement, resultSet);
        }
        return null;
    }

    @Override
    public user selectById(int Id) {
        try {
            connection = DBUtil.getConnection();
            String sql = "select * from user where userId = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, Id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user user = new user();
                user.setUserId(resultSet.getInt("userId"));
                user.setPassword(resultSet.getString("passWord"));
                user.setUsername(resultSet.getString("userName"));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection, statement, resultSet);
        }
        return null;
    }

    @Override
    public void delete(int blogId) {
        try {
            connection = DBUtil.getConnection();
            String sql = "delete from user whre blogId = ?";
            statement = connection.prepareStatement(sql);
            int ret = statement.executeUpdate();
            if (ret == 1){
                System.out.println("删除成功");
            }else{
                System.out.println("删除失败");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection, statement, resultSet);
        }
    }

    public user selectByName(String username) {
        try {
            connection = DBUtil.getConnection();
            String sql = "select * from user where userName = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user user = new user();
                user.setUserId(resultSet.getInt("userId"));
                user.setPassword(resultSet.getString("passWord"));
                user.setUsername(resultSet.getString("userName"));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection, statement, resultSet);
        }
        return null;
    }
}